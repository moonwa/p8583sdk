package com.denovo.p8583server.handlers.handlercommon;

import com.denovo.p8583server.handlers.jsonmodel.*;
import com.denovo.p8583server.requestMessages.*;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2015/1/29.
 */
public  class JsonHelper {


    public static Result GetSignInResult(SignInRequestMessage requestMessage) throws Exception {
        SignInEntry model = new SignInEntry();
        model.setBusinessCode(requestMessage.getClientId().replace("F",""));
        model.setAccountName(requestMessage.getUserName());
        model.setTerminalCode(requestMessage.getTerminalId());
        MessageDigest md5 = MessageDigest.getInstance("md5");
        model.setPasswd(Hex.encodeHexString(md5.digest(requestMessage.getPassword().getBytes("utf8"))));
        JSONObject obj = JSONObject.fromObject(model);
        String phoneNumRegStr = obj.toString();
        String entryptParams = Hex.encodeHexString(md5.digest((phoneNumRegStr + requestMessage.getMac()).getBytes("utf8")));
        String resp = Globals.GetMemberWebService().execute("posAccountLogin", "1.0", phoneNumRegStr, entryptParams);
        obj=JSONObject.fromObject(resp);
        return (Result) JSONObject.toBean(obj, Result.class);

    }


    public static Result GetSignInOutResult(SignInOutRequestMessage requestMessage) throws Exception {
        SignInEntry model = new SignInEntry();
        model.setBusinessCode(requestMessage.getClientId().replace("F",""));
        model.setAccountName(requestMessage.getUserName());
        model.setTerminalCode(requestMessage.getTerminalId());
        MessageDigest md5 = MessageDigest.getInstance("md5");
        model.setPasswd(Hex.encodeHexString(md5.digest(requestMessage.getPassword().getBytes("utf8"))));
        JSONObject obj = JSONObject.fromObject(model);
        String phoneNumRegStr = obj.toString();
        String entryptParams = Hex.encodeHexString(md5.digest((phoneNumRegStr + requestMessage.getMac()).getBytes("utf8")));
        String resp = Globals.GetMemberWebService().execute("posAccountLogOut", "1.0", phoneNumRegStr, entryptParams);
        obj=JSONObject.fromObject(resp);
        return (Result) JSONObject.toBean(obj, Result.class);
    }



    public static Result  GetPosRegisteredResult(PosRegisteredRequestMessage requestMsg) throws Exception{
        PosRegisteredEntry model = new PosRegisteredEntry();
        model.setBusinessCode(requestMsg.getClientId().replace(" ", ""));
        model.setTerminalCode(requestMsg.getTerminalId());
        model.setMacAddress(requestMsg.getPosAddress());
        JSONObject obj = JSONObject.fromObject(model);
        String phoneNumRegStr = obj.toString();
        MessageDigest md5 =  MessageDigest.getInstance("md5");
        String entryptParams = Hex.encodeHexString(md5.digest((phoneNumRegStr + requestMsg.getMac()).getBytes("utf8")));
        String resp = Globals.GetMemberWebService().execute("posTerminalRegister", "1.0", phoneNumRegStr, entryptParams);
        obj = JSONObject.fromObject(resp);
        return  (Result) JSONObject.toBean(obj, Result.class);
    }

    public  static  ConsumeResult GetConsumeResult(DealRequestMessage request) throws Exception{
        MessageDigest md5 =  MessageDigest.getInstance("md5");
        ConsumeEntry model = new ConsumeEntry();
        model.setTerminalFlowNum(request.getSerialNo());
        model.setAmount(Double.toString(request.getDealamount()));
        model.setIntegralAmount(Double.toString(request.getDealamount()));
        model.setLoginNum(request.getCardNumber());
        model.setPasswd(Hex.encodeHexString(md5.digest(request.getCardOrPhoneNumberPassWork().getBytes("utf8"))));
        model.setChannel("自助终端");
        model.setBusinessCode(request.getClientId().replace(" ", ""));
        model.setOperator(request.getOperator());
        model.setPosTerminalCode(request.getTerminalId());
        JSONObject obj = JSONObject.fromObject(model);
        String strJson = obj.toString();
        String entryptParams = Hex.encodeHexString(md5.digest((strJson + request.getMac()).getBytes("utf8")));
        String resp =  Globals.GetMemberWebService().execute("consume", "1.0", strJson, entryptParams);
        obj = JSONObject.fromObject(resp);
        return  (ConsumeResult) JSONObject.toBean(obj, ConsumeResult.class);
    }

    public static   MemberBalanceResult  GetMemberBalanceResult(DealRequestMessage requestMessage) throws Exception{
        MemberSelectBalancEntry model=new MemberSelectBalancEntry();
        model.setLoginNum(requestMessage.getCardNumber());
        model.setBusinessCode(requestMessage.getClientId().replace("F", ""));
        model.setIsRequirePwd("1");
        MessageDigest md5 =  MessageDigest.getInstance("md5");
        model.setPasswd(Hex.encodeHexString(md5.digest(requestMessage.getCardOrPhoneNumberPassWork().getBytes("utf8"))));
        JSONObject obj = JSONObject.fromObject(model);
        String strJson = obj.toString();
        String entryptParams = Hex.encodeHexString(md5.digest((strJson + requestMessage.getMac()).getBytes("utf8")));
        String resp =  Globals.GetMemberWebService().execute("getMemberBalance", "1.0", strJson, entryptParams);
        obj = JSONObject.fromObject(resp);
        return  (MemberBalanceResult) JSONObject.toBean(obj, MemberBalanceResult.class);
    }

    public static Result GetHistoryMoneyOrderInfoResult(DealRequestMessage request) throws Exception {
        MessageDigest md5 =  MessageDigest.getInstance("md5");
        HistoryMoneyOrderInfoEntry model=new HistoryMoneyOrderInfoEntry();
       model.setLoginNum(request.getCardNumber());
        model.setIsRequirePwd("1");
        model.setBusinessCode(request.getClientId());
        model.setPasswd(Hex.encodeHexString(md5.digest(request.getCardOrPhoneNumberPassWork().getBytes("utf8"))));
       model.setStartDate(GetDate());
       model.setFlowType("1");
        model.setEndDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        model.setPageSize("3");
        model.setPageNum("1");
        JSONObject obj = JSONObject.fromObject(model);
        String strJson = obj.toString();
        String entryptParams = Hex.encodeHexString(md5.digest((strJson + request.getMac()).getBytes("utf8")));
        Result result=new Result();
        try {
            String resp =  Globals.GetMemberWebService().execute("getHistoryMoneyOrderInfo", "1.0", strJson, entryptParams);
            obj = JSONObject.fromObject(resp);
            Map<String,Class<?>> map  = new HashMap<String,Class<?>>();
            map.put("code",String.class);
            map.put("msg",String.class);
            map.put("result",PayDetail.class);
            map.put("errorCode",String.class);
            map.put("total",Integer.class);
            map.put("rows",HistoryMoneyOrderInfoResultBody.class);
            MemberMoneyOrderPayDetailResult payDetailResult  = (MemberMoneyOrderPayDetailResult)JSONObject.toBean( JSONObject.fromObject(resp), MemberMoneyOrderPayDetailResult.class, map );
            result.setCode(payDetailResult.getCode());
            if (payDetailResult.getCode().equals("0")) {
                PayDetail payDetail = payDetailResult.getResult();
                List<HistoryMoneyOrderInfoResultBody> list = payDetail.getRows();
                String str = "";
                int index = 0;
                for(int i = 0; i < list.size(); i++)
                {
                    HistoryMoneyOrderInfoResultBody body= list.get(i);
                    if (index !=0 ) {
                        str += ",";
                    }
                    String strs[] =  body.getTransactionTime().replace(" ", "-").split("-");
                    str +=  String.format("%1$s|%2$s|%3$s", body.getTransactionNum(), StringUtils.leftPad(Integer.toString((int)(Math.abs(body.getTransactionAmount() * 100))),12, '0'),strs[1]+strs[2]);
                    index++;
                }
                result.setMsg(str);
            }else {
                result.setErrorCode(payDetailResult.getErrorCode());
                result.setMsg(payDetailResult.getMsg());
            }
        }catch (Exception ex){
            result.setCode("96");
            result.setErrorCode("错误信息"+ex.getMessage());
        }
        return  result;
    }



    public   static  ConsumeResult  GetRefundResult(DealRequestMessage request) throws Exception {
        MessageDigest md5 =  MessageDigest.getInstance("md5");
        RefundEntry model=new RefundEntry();
        String[] serialNos=  request.getSerialNo().split("\\|");
        model.setFlowCode(serialNos[1]);
        model.setTerminalFlowNum(serialNos[0]);
        model.setOperator(request.getOperator());
        model.setPosTerminalCode(request.getTerminalId());
        model.setAmount(Double.toString(request.getDealamount()));
        model.setChannel("自助终端");
        model.setBusinessCode(request.getClientId());
        model.setPasswd(Hex.encodeHexString(md5.digest(request.getCardOrPhoneNumberPassWork().getBytes("utf8"))));
        JSONObject obj = JSONObject.fromObject(model);
        String strJson = obj.toString();
        String entryptParams = Hex.encodeHexString(md5.digest((strJson + request.getMac()).getBytes("utf8")));
        String resp =  Globals.GetMemberWebService().execute("refund", "1.0", strJson, entryptParams);
        obj = JSONObject.fromObject(resp);
        return  (ConsumeResult) JSONObject.toBean(obj, ConsumeResult.class);

    }


    public   static  Result  GetPhoneAndCardNumRegResult(DealRequestMessage request) throws Exception {
        MessageDigest md5 =  MessageDigest.getInstance("md5");
        phoneAndCardNumRegEntry model=new phoneAndCardNumRegEntry();

        model.setMobileNum(request.getPhoneNumber());
        model.setLoginPasswd("");
        model.setCardNum(request.getCardNumber());
        model.setOperator(request.getOperator());
        model.setBusinessCode(request.getClientId());
        model.setBirthday("");
        model.setSex("");
        model.setCreditNum("");
        model.setName("");
        model.setPasswd(Hex.encodeHexString(md5.digest(request.getCardOrPhoneNumberPassWork().getBytes("utf8"))));
        JSONObject obj = JSONObject.fromObject(model);
        String strJson = obj.toString();
        String entryptParams = Hex.encodeHexString(md5.digest((strJson + request.getMac()).getBytes("utf8")));
        String resp =  Globals.GetMemberWebService().execute("phoneAndCardNumReg", "1.0", strJson, entryptParams);
        obj = JSONObject.fromObject(resp);
        return  (Result) JSONObject.toBean(obj, Result.class);

    }


    public   static  HistoryMoneyOrderInfoResult  GetRechargeResult(DealRequestMessage request) throws Exception {
        MessageDigest md5 =  MessageDigest.getInstance("md5");
        ChargeEntry model=new ChargeEntry();

        model.setPayWayCode("01");
        model.setPayWayName("现金");
        model.setOperator(request.getOperator());
        model.setPosTerminalCode(request.getTerminalId());
        model.setAmount(Double.toString(request.getDealamount()));
        model.setChannel("自助终端");
        model.setBusinessCode(request.getClientId().trim());
        model.setAccountType("0");
        model.setTerminalFlowNum(request.getSerialNo());
        model.setLoginNum(request.getCardNumber());
        JSONObject obj = JSONObject.fromObject(model);
        String strJson = obj.toString();
        String entryptParams = Hex.encodeHexString(md5.digest((strJson + request.getMac()).getBytes("utf8")));
        String resp =  Globals.GetMemberWebService().execute("charge", "1.0", strJson, entryptParams);
        obj = JSONObject.fromObject(resp);

        return  (HistoryMoneyOrderInfoResult) JSONObject.toBean(obj, HistoryMoneyOrderInfoResult.class);

    }

     public   static  Result  GetRoolbackResult(DealRollbackRequestMessage request) throws Exception {
        MessageDigest md5 =  MessageDigest.getInstance("md5");
         OffsetEmpty model=new OffsetEmpty();
        model.setOperator(request.getOperator());
        model.setChannel("自助终端");
        model.setBusinessCode(request.getClientId().trim());
        model.setTerminalFlowNum(UUID.randomUUID().toString());
        model.setOldTerminalFlowNum(request.getSerialNo());
        JSONObject obj = JSONObject.fromObject(model);
        String strJson = obj.toString();
        String entryptParams = Hex.encodeHexString(md5.digest((strJson + request.getMac()).getBytes("utf8")));
        String resp =  Globals.GetMemberWebService().execute("offset", "1.0", strJson, entryptParams);
        obj = JSONObject.fromObject(resp);
        return  (Result) JSONObject.toBean(obj, Result.class);
    }
    private static String GetDate()  throws Exception{
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 60);
        Date endDate = dft.parse(dft.format(date.getTime()));
        return    dft.format(endDate);
    }



}
