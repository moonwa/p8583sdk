package com.denovo.p8583server.handlers;

import com.denovo.p8583.MessageHandler;
import com.denovo.p8583.ResponseMessage;
import com.denovo.p8583server.requestMessages.SignInRequestMessage;
import com.denovo.p8583server.responseMessages.SignInResponseMessage;
import com.denovo.p8583server.memweb.IMemberWebService;
import com.denovo.p8583server.memweb.IMemberWebServiceService;
import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 * Created by moonwa on 15-1-18.
 */
public class SigninHandler implements MessageHandler {
    private SignInRequestMessage requestMessage;

    public SigninHandler(SignInRequestMessage requestMessage) {
        this.requestMessage = requestMessage;
    }

    public ResponseMessage handle() throws Exception {
        SignInResponseMessage msg = new SignInResponseMessage("0810", requestMessage.getTpud());

        msg.setResult(0);
// ----------------
        IMemberWebService ser = new IMemberWebServiceService().getIMemberWebServicePort();
        SignInEntry model = new SignInEntry();
        model.setBusinessCode(requestMessage.getClientId().replace("F", ""));
        model.setAccountName(requestMessage.getUserName());
        model.setTerminalCode(requestMessage.getTerminalId());
        MessageDigest md5 =  MessageDigest.getInstance("md5");
        model.setPasswd(Hex.encodeHexString(md5.digest(requestMessage.getPassword().getBytes("utf8"))));

        JSONObject obj = JSONObject.fromObject(model);
        String phoneNumRegStr = obj.toString();


        String entryptParams = Hex.encodeHexString(md5.digest((phoneNumRegStr + requestMessage.getMac()).getBytes("utf8")));
        String resp = ser.execute("posAccountLogin", "1.0", phoneNumRegStr, entryptParams);
        obj = JSONObject.fromObject(resp);
        Result result = (Result) JSONObject.toBean(obj, Result.class);

        if (result.getCode() == "0") {
            //return new PosWithShop() { DataKey = "1111111111111111", ShopName = shopName, Authenticated = true };
        }
        else
        {
            // return new PosWithShop() { Authenticated = false, CodeText = _result.errorCode + ":" + _result.msg };
        }
        //------------------
        String batchno = requestMessage.getBatchNo();
        batchno = batchno.substring(0, 2) + "000000" + batchno.substring(8, batchno.length());
        msg.setBatchNo(batchno);
        msg.setTransportationKey("");
        msg.setRequestMessage(requestMessage);
        return msg;
    }

}
