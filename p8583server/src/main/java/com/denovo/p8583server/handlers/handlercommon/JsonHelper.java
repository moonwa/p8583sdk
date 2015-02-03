package com.denovo.p8583server.handlers.handlercommon;

import com.denovo.p8583server.handlers.jsonmodel.*;
import com.denovo.p8583server.memweb.IMemberWebService;
import com.denovo.p8583server.memweb.IMemberWebServiceService;
import com.denovo.p8583server.requestMessages.DealRequestMessage;

import com.denovo.p8583server.requestMessages.DealRollbackRequestMessage;
import com.denovo.p8583server.requestMessages.PosRegisteredRequestMessage;
import com.denovo.p8583server.requestMessages.SignInRequestMessage;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import java.util.Date;

/**
 * Created by Administrator on 2015/1/29.
 */
public  class JsonHelper {

    //签到
    public static Result GetSignInResult(SignInRequestMessage requestMessage) throws Exception {
    IMemberWebService ser = new IMemberWebServiceService().getIMemberWebServicePort();
    SignInEntry model = new SignInEntry();
    model.setBusinessCode(requestMessage.getClientId().replace("F",""));
    model.setAccountName(requestMessage.getUserName());
    model.setTerminalCode(requestMessage.getTerminalId());
    MessageDigest md5 = MessageDigest.getInstance("md5");
    model.setPasswd(Hex.encodeHexString(md5.digest(requestMessage.getPassword().getBytes("utf8"))));
    JSONObject obj = JSONObject.fromObject(model);
    String phoneNumRegStr = obj.toString();
    String entryptParams = Hex.encodeHexString(md5.digest((phoneNumRegStr + requestMessage.getMac()).getBytes("utf8")));
    String resp = ser.execute("posAccountLogin", "1.0", phoneNumRegStr, entryptParams);
    obj=JSONObject.fromObject(resp);
   return (Result) JSONObject.toBean(obj, Result.class);
   }

    //终端注册
    public static Result  GetPosRegisteredResult(PosRegisteredRequestMessage requestMsg) throws Exception{
        IMemberWebService ser = new IMemberWebServiceService().getIMemberWebServicePort();
        PosRegisteredEntry model = new PosRegisteredEntry();
        model.setBusinessCode(requestMsg.getClientId().replace(" ", ""));//商户号
        model.setTerminalCode(requestMsg.getTerminalId());//终端号
        model.setMacAddress(requestMsg.getPosAddress());

        JSONObject obj = JSONObject.fromObject(model);
        String phoneNumRegStr = obj.toString();

        MessageDigest md5 =  MessageDigest.getInstance("md5");
        String entryptParams = Hex.encodeHexString(md5.digest((phoneNumRegStr + requestMsg.getMac()).getBytes("utf8")));
        String resp = ser.execute("posTerminalRegister", "1.0", phoneNumRegStr, entryptParams);
        obj = JSONObject.fromObject(resp);
       return  (Result) JSONObject.toBean(obj, Result.class);

    }

    //会员扣款
    public  static  ConsumeResult GetConsumeResult(DealRequestMessage request) throws Exception{
        IMemberWebService ser = new IMemberWebServiceService().getIMemberWebServicePort();
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
        String resp = ser.execute("consume", "1.0", strJson, entryptParams);
        obj = JSONObject.fromObject(resp);
        return  (ConsumeResult) JSONObject.toBean(obj, ConsumeResult.class);

    }


    //查询余额和积分
    public static   MemberBalanceResult  GetMemberBalanceResult(DealRequestMessage requestMessage) throws Exception{
        IMemberWebService ser = new IMemberWebServiceService().getIMemberWebServicePort();
        MemberSelectBalancEntry model=new MemberSelectBalancEntry();
        model.setLoginNum(requestMessage.getCardNumber());
        model.setBusinessCode(requestMessage.getClientId().replace("F", ""));
        model.setIsRequirePwd("1");
        MessageDigest md5 =  MessageDigest.getInstance("md5");
        model.setPasswd(Hex.encodeHexString(md5.digest(requestMessage.getCardOrPhoneNumberPassWork().getBytes("utf8"))));
        JSONObject obj = JSONObject.fromObject(model);
        String strJson = obj.toString();
        String entryptParams = Hex.encodeHexString(md5.digest((strJson + requestMessage.getMac()).getBytes("utf8")));
        String resp = ser.execute("getMemberBalance", "1.0", strJson, entryptParams);
        obj = JSONObject.fromObject(resp);
        return  (MemberBalanceResult) JSONObject.toBean(obj, MemberBalanceResult.class);
    }
    //退款时 先 返回交易历史记录
    public static HistoryMoneyOrderInfoResult GetHistoryMoneyOrderInfoResult(DealRequestMessage request) throws Exception {
        IMemberWebService ser = new IMemberWebServiceService().getIMemberWebServicePort();
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
        String resp = ser.execute("getHistoryMoneyOrderInfo", "1.0", strJson, entryptParams);
        obj = JSONObject.fromObject(resp);
        return  (HistoryMoneyOrderInfoResult) JSONObject.toBean(obj, HistoryMoneyOrderInfoResult.class);
    }

    //退款
    public   static  ConsumeResult  GetRefundResult(DealRequestMessage request) throws Exception {
        IMemberWebService ser = new IMemberWebServiceService().getIMemberWebServicePort();
        MessageDigest md5 =  MessageDigest.getInstance("md5");
        RefundEntry model=new RefundEntry();
        String[] serialNos=  request.getSerialNo().split("|");
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
        String resp = ser.execute("refund", "1.0", strJson, entryptParams);
        obj = JSONObject.fromObject(resp);
        return  (ConsumeResult) JSONObject.toBean(obj, ConsumeResult.class);

    }

    //会员注册
    public   static  Result  GetPhoneAndCardNumRegResult(DealRequestMessage request) throws Exception {
        IMemberWebService ser = new IMemberWebServiceService().getIMemberWebServicePort();
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
        String resp = ser.execute("phoneAndCardNumReg", "1.0", strJson, entryptParams);
        obj = JSONObject.fromObject(resp);
        return  (Result) JSONObject.toBean(obj, Result.class);

    }

    //充值
    public   static  HistoryMoneyOrderInfoResult  GetRechargeResult(DealRequestMessage request) throws Exception {
        IMemberWebService ser = new IMemberWebServiceService().getIMemberWebServicePort();
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
        String resp = ser.execute("charge", "1.0", strJson, entryptParams);
        obj = JSONObject.fromObject(resp);

        return  (HistoryMoneyOrderInfoResult) JSONObject.toBean(obj, HistoryMoneyOrderInfoResult.class);

    }
    //交易冲销   冲正
     public   static  Result  GetRoolbackResult(DealRollbackRequestMessage request) throws Exception {
        IMemberWebService ser = new IMemberWebServiceService().getIMemberWebServicePort();
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
        String resp = ser.execute("offset", "1.0", strJson, entryptParams);
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
