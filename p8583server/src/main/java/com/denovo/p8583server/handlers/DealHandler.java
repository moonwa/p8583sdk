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

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by Administrator on 2015/1/26.
 */
public class  DealHandler implements MessageHandler {

    private DealRequestMessage requestMessage;

    public DealHandler(DealRequestMessage requestMessage) {
        this.requestMessage = requestMessage;
    }

    public ResponseMessage handle() throws Exception {
        DealResponseMessage msg = new DealResponseMessage("0210", requestMessage.getTpud());
        msg.setResult(0);
        msg.setRequestMessage(requestMessage);
        KeysEntry key = Globals.GetKeyEntry(requestMessage.getClientId().trim(),requestMessage.getTerminalId().trim());
      String dealType="";
       if(requestMessage.getP8583Pack().get(59).getHasValue()){
           dealType= requestMessage.getP8583Pack().get(59).getString();
       }
       double amount= requestMessage.getDealamount();
        if(requestMessage.getDealType().startsWith("31")){ // 查询
            int[] poss={2, 3, 4, 5,11, 12, 13, 14, 25, 32, 44,49, 53, 54, 60, 61, 62, 63};
            msg.GetResponse(poss,requestMessage.getP8583Pack());
        }else  if(requestMessage.getDealType().startsWith("00")){ // 交易


            if (dealType.equals("") ||dealType.substring(0, 2).equals("22")) {
                int[] poss={3, 4, 11, 12, 13, 14, 25, 32, 38,  44, 49, 52,53, 54,58,59, 60, 61, 62,63};
                msg.GetResponse(poss,requestMessage.getP8583Pack());
            }
            else if (dealType.substring(0, 2).equals("20")) {

                int[] poss={3, 4, 11, 12, 13, 14, 25, 32, 38,  44,  49, 53, 54, 60, 61, 62};
                msg.GetResponse(poss,requestMessage.getP8583Pack());
            }
            if(amount<=0){
                msg.setResult(0x13);
            }
        }else if(requestMessage.getDealType().startsWith("20")){
            if (dealType.substring(0, 2).equals("23")) {
                int[] poss={2, 3, 11, 12, 13, 14, 25, 32, 38, 44,  49, 53, 54, 58, 59, 60, 61, 62};
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

        if(key==null){
            msg.setResult(0x77);
            return msg;
        }
       if(!Ledes.MACDecrypt(requestMessage.getData(), key.getKey2(), requestMessage.getP8583Pack().get(63).getString())){
           msg.setResult(0xA0);
           return msg;
        }





        //退款  执行退款 返回的结果跟扣款一样 所以共用一个 ConsumeResult
       // ConsumeResult refundResult=JsonHelper.GetRefundResult(requestMessage);



      String openCode=requestMessage.getOpenCode();
        if(openCode!=null) {
            msg.setOpenCode(openCode);
          String  cardNumber=requestMessage.getCardNumber();
            if(cardNumber.length()>=13)
                requestMessage.setCardOrPhoneNumberPassWork(msg.GetPassword(cardNumber,requestMessage.getCardOrPhoneNumberPassWork(),key.getKey1()));
           else
                requestMessage.setCardOrPhoneNumberPassWork(msg.GetPassword1(cardNumber, requestMessage.getCardOrPhoneNumberPassWork(),key.getKey1()));

            if (openCode.equals("000000000000000000000000000000REGIST"))//会员注册
            {
                //会员注册
                Result phoneAndCardNumRegResult = JsonHelper.GetPhoneAndCardNumRegResult(requestMessage);

            } else if (openCode.equals("000000000000000000000000000000DETAIL")){//获取交易记录
                //退款时 先 返回交易历史记录
                HistoryMoneyOrderInfoResult  historyMoneyOrderInfoResult=JsonHelper.GetHistoryMoneyOrderInfoResult(requestMessage);

            }else if(openCode.equals("000000000000000000000000000000ONLYSALE")){//会员扣款

                //会员扣款
                ConsumeResult consumeResult=JsonHelper.GetConsumeResult(requestMessage);







                if (consumeResult.getCode().equals("0")) {
                    msg.setSerialNo( consumeResult.getResult().getFlowCode());


                }else {
                    msg.setResultMsg(consumeResult.getErrorCode()+":"+consumeResult.getMsg());
                    msg.setResult(0x88);
                }

        }else if(openCode.equals("000000000000000000000000000000RECHARGE")) {//充值
                //充值
                HistoryMoneyOrderInfoResult rechargeResult=JsonHelper.GetRechargeResult(requestMessage);
                if (rechargeResult.getCode().equals("0")) {
                   msg.setSerialNo( rechargeResult.getResult().getFlowCode());

                }else {
                    msg.setResultMsg(rechargeResult.getErrorCode()+":"+rechargeResult.getMsg());
                    msg.setResult(0x88);
                }

            }else  if(openCode.equals("000000000000000000000000000000QUERY")) {//查询

                //查询余额跟积分
                MemberBalanceResult memberBalanceResult=JsonHelper.GetMemberBalanceResult(requestMessage);

                double basicBalance= new BigDecimal(memberBalanceResult.getResult().getBasicBalance()).doubleValue();
                double integral=  new BigDecimal( memberBalanceResult.getResult().getIntegral()).doubleValue();
                double donateBalance=  new BigDecimal( memberBalanceResult.getResult().getDonateBalance()).doubleValue();


            }
            else
            {

            }












        }




        return msg;
    }
}

