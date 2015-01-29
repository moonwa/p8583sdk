package com.denovo.p8583server.handlers;

import com.denovo.p8583.MessageHandler;
import com.denovo.p8583.ResponseMessage;
import com.denovo.p8583server.handlers.handlercommon.JsonHelper;
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

       Result result=JsonHelper.GetPosRegisteredResult(requestMsg);

        if (result.getCode().equals("0")) {

        }else{

            msg.setResultMsg(result.getErrorCode()+":"+result.getMsg());
            msg.setResult(0x88);
        }
        return msg;
    }
}
