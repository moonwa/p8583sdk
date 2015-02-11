package com.denovo.p8583server.handlers;


import com.denovo.p8583.Ledes;
import com.denovo.p8583.MessageHandler;
import com.denovo.p8583.ResponseMessage;
import com.denovo.p8583server.handlers.handlercommon.Globals;
import com.denovo.p8583server.handlers.handlercommon.JsonHelper;
import com.denovo.p8583server.handlers.handlercommon.KeysEntry;
import com.denovo.p8583server.handlers.jsonmodel.*;
import com.denovo.p8583server.requestMessages.DealRequestMessage;
import com.denovo.p8583server.responseMessages.DealResponseMessage;
import org.apache.commons.lang3.StringUtils;
import java.math.BigDecimal;
import java.text.DecimalFormat;


/**
 * Created by Administrator on 2015/1/26.
 */
public class  DealHandler implements MessageHandler {

    private DealRequestMessage requestMessage;

    public DealHandler(DealRequestMessage requestMessage) {
        this.requestMessage = requestMessage;
    }

    public ResponseMessage handle() throws Exception {
        DealResponseMessage  msg=new DealResponseMessage("0210",requestMessage.getTpud());

        KeysEntry key = Globals.GetKeyEntry(requestMessage.getClientId().trim(),requestMessage.getTerminalId().trim());
      String dealType="";
       if(requestMessage.getP8583Pack().get(59).getHasValue()){
           dealType= requestMessage.getP8583Pack().get(59).getString();
       }
       double amount= requestMessage.getDealamount();
        if(requestMessage.getDealType().startsWith("31")){ // 查询
            int[] poss={2, 3, 4, 5,11, 12, 13, 14, 25,  39, 41, 42, 44, 47,49, 53, 54, 60, 61, 62, 63,64};
            msg.GetResponse(poss,requestMessage.getP8583Pack());
        }else  if(requestMessage.getDealType().startsWith("00")){ // 交易
            if (dealType.equals("") ||dealType.substring(0, 2).equals("22")) {
               int[]poss={2, 3,4, 11, 12, 13, 14, 25};//59
                msg.GetResponse(poss,requestMessage.getP8583Pack());
            }
            else if (dealType.substring(0, 2).equals("20")) {

                int[] poss={2, 3, 11, 12, 13, 14, 25, 38, 39, 41, 42, 44, 47,49, 53, 54, 60, 61, 62, 64};
                msg.GetResponse(poss,requestMessage.getP8583Pack());
            }
            if(amount<=0){
                msg.setResult(0x13);
            }
        }else if(requestMessage.getDealType().startsWith("20")){
            if (dealType.substring(0, 2).equals("23")) {
                int[] poss={2, 3, 11, 12, 13, 14, 25,  38, 39, 41, 42, 44, 47,49, 53, 54, 58,60, 61, 62, 64};
                msg.GetResponse(poss,requestMessage.getP8583Pack());
            }
            if (dealType.substring(0, 2).equals("21")) {
                int[] poss={2, 3, 4, 11, 25, 38, 49, 52, 53, 60, 61, 62};

                msg.GetResponse(poss,requestMessage.getP8583Pack());
            }
            if(amount<=0){
                msg.setResult(0x13);
            }
        }
        msg.setRequestMessage(requestMessage);
        if(key==null){
            msg.setResult(0x77);
            return msg;
        }
       if(!Ledes.MACDecrypt(requestMessage.getData(), key.getKey2(), requestMessage.getP8583Pack().get(63).getString())){
           msg.setResult(0xA0);
           return msg;
        }
        msg.setIsQuery(false);

      String openCode=requestMessage.getOpenCode();
        if(openCode!=null) {
            msg.setOpenCode(openCode);
            requestMessage.setCardOrPhoneNumberPassWork(msg.GetPassword1(requestMessage.getCardOrPhoneNumberPassWork(),key.getKey1()));
            if (openCode.equals("000000000000000000000000000000REGIST"))//会员注册
            {
                msg.setIsQuery(true);
                Result phoneAndCardNumRegResult = JsonHelper.GetPhoneAndCardNumRegResult(requestMessage);
                if (phoneAndCardNumRegResult.getCode().equals("0")) {
                    msg.setData("0210156C" + StringUtils.leftPad("0", 12, '0'));
                    msg.setMac(requestMessage.getMac());
                    msg.setAmount( new BigDecimal(0));
                    msg.setPoint(0);
                    msg.setResult(0);
                }else {
                    msg.setResultMsg(phoneAndCardNumRegResult.getErrorCode()+":"+phoneAndCardNumRegResult.getMsg());
                    msg.setResult(0x88);
                }


            } else if (openCode.equals("000000000000000000000000000000DETAIL")){//获取交易记录
                 Result  result=JsonHelper.GetHistoryMoneyOrderInfoResult(requestMessage);
                msg.setIsQuery(true);
                if (result.getCode().equals("0")) {
                    msg.setCodeText( result.getMsg());
                    msg.setData("0210156C" + StringUtils.leftPad("0", 12, '0'));
                    msg.setMac(requestMessage.getMac());
                    msg.setAmount( new BigDecimal(0));
                    msg.setPoint(0);
                    msg.setResult(0);
                }else {
                    msg.setResultMsg(result.getErrorCode()+":"+result.getMsg());
                    msg.setResult(0x88);
                }
            }else if(openCode.equals("000000000000000000000000000000REFUND")) {//退款
                //退款  执行退款 返回的结果跟扣款一样 所以共用一个 ConsumeResult
                 ConsumeResult refundResult=JsonHelper.GetRefundResult(requestMessage);
                if (refundResult.getCode().equals("0")) {
                    msg.setSerialNo( refundResult.getResult().getFlowCode());
                    msg.setResult(0);
                }else {
                    msg.setResultMsg(refundResult.getErrorCode()+":"+refundResult.getMsg());
                    msg.setResult(0x88);
                }

            }else if(openCode.equals("000000000000000000000000000000ONLYSALE")){//会员扣款
                ConsumeResult consumeResult=JsonHelper.GetConsumeResult(requestMessage);
                if (consumeResult.getCode().equals("0")) {
                    msg.setSerialNo( consumeResult.getResult().getFlowCode());
                    msg.setResult(0);
                }else {
                    msg.setResultMsg(consumeResult.getErrorCode()+":"+consumeResult.getMsg());
                    msg.setResult(0x88);
                }
             }else if(openCode.equals("000000000000000000000000000000RECHARGE")) {//充值
                HistoryMoneyOrderInfoResult rechargeResult = JsonHelper.GetRechargeResult(requestMessage);
                if (rechargeResult.getCode().equals("0")) {
                    msg.setSerialNo(rechargeResult.getResult().getFlowCode());
                    msg.setResult(0);
                }else {
                    msg.setResultMsg(rechargeResult.getErrorCode()+":"+rechargeResult.getMsg());
                    msg.setResult(0x88);
                }
            }else  if(openCode.equals("000000000000000000000000000000QUERY")) {//查询

                //查询余额跟积分
                MemberBalanceResult memberBalanceResult = JsonHelper.GetMemberBalanceResult(requestMessage);
                msg.setIsQuery(true);
                if (memberBalanceResult.getCode().equals("0")) {
                    msg.setResult(0);
                    double basicBalance = new BigDecimal(memberBalanceResult.getResult().getBasicBalance()).doubleValue();
                    double integral = new BigDecimal(memberBalanceResult.getResult().getIntegral()).doubleValue();
                    double donateBalance = new BigDecimal(memberBalanceResult.getResult().getDonateBalance()).doubleValue();
                    String amont=new DecimalFormat("0.00").format(msg.add(basicBalance, donateBalance));
                    msg.setAmount(new BigDecimal(amont));
                    msg.setData("0210156C" + StringUtils.leftPad(amont.replace(".",""), 12, '0'));
                    msg.setMac(requestMessage.getMac());
                    msg.setPoint(integral);
                }
                else {
                    msg.setResultMsg(memberBalanceResult.getErrorCode()+":"+memberBalanceResult.getMsg());
                    msg.setResult(0x88);
                }
            }

        }
        return msg;
    }
}

