package com.denovo.p8583server.handlers;

import com.denovo.p8583.MessageHandler;
import com.denovo.p8583.ResponseMessage;
import com.denovo.p8583server.handlers.jsonmodel.PosRegisteredEntry;
import com.denovo.p8583server.handlers.jsonmodel.Result;
import com.denovo.p8583server.memweb.IMemberWebService;
import com.denovo.p8583server.memweb.IMemberWebServiceService;
import com.denovo.p8583server.requestMessages.PosRegisteredRequestMessage;
import com.denovo.p8583server.responseMessages.PosRegisteredResponseMessage;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;


/**
 * Created by Administrator on 2015/1/21.
 */
public class PosRegisteredHandler implements MessageHandler {
    private  PosRegisteredRequestMessage requestMsg;

    public PosRegisteredHandler(PosRegisteredRequestMessage requestMsg){
     this.requestMsg=requestMsg;
    }

    public ResponseMessage handle() throws Exception {
        PosRegisteredResponseMessage msg = new PosRegisteredResponseMessage("0910", requestMsg.getTpud());
        msg.setResult(0);

        String batchno = requestMsg.getBatchNo();
        batchno = batchno.substring(0, 2) + "000000" + batchno.substring(8, batchno.length());
        msg.setBatchNo(batchno);
        msg.setRequestMessage(requestMsg);

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
        Result result = (Result) JSONObject.toBean(obj, Result.class);

        if (result.getCode().equals("0")) {

        }else{

            msg.setResultMsg(result.getErrorCode()+":"+result.getMsg());
            msg.setResult(0x88);
        }
        return msg;
    }
}
