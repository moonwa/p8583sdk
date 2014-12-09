package com.denovo.p8583server.handlers;

import com.denovo.p8583.Encoder;
import com.denovo.p8583.Ledes;
import com.denovo.p8583.MessageHandler;
import com.denovo.p8583.ResponseMessage;
import com.denovo.p8583server.handlers.handlercommon.Globals;
import com.denovo.p8583server.handlers.handlercommon.JsonHelper;
import com.denovo.p8583server.handlers.jsonmodel.Result;
import com.denovo.p8583server.requestMessages.SignInRequestMessage;
import com.denovo.p8583server.responseMessages.SignInResponseMessage;

import java.util.UUID;

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

        String shop_key = "1111111111111111";

        String batchno = requestMessage.getBatchNo();
        batchno = batchno.substring(0, 2) + "000000" + batchno.substring(8, batchno.length());
        msg.setBatchNo(batchno);
        msg.setRequestMessage(requestMessage);

        Result result=JsonHelper.GetSignInResult(requestMessage);

        if (result.getCode().equals("0")) {
            String key1 = UUID.randomUUID().toString().substring(0, 8);
            String encryptKey1 = Ledes.encrypt(key1.getBytes(), Encoder.toBcd(shop_key.getBytes()));
            String hash1 = Ledes.encrypt(new byte[8], key1.getBytes()).substring(0, 8);
            String key2 = UUID.randomUUID().toString().substring(0, 8);
            String encryptKey2 = Ledes.encrypt(key2.getBytes(), Encoder.toBcd(shop_key.getBytes()));
            String hash2= Ledes.encrypt(new byte[8], key2.getBytes()).substring(0, 8);
            msg.setTransportationKey(encryptKey1 + hash1 + encryptKey2 + hash2);
           Globals.SetKeyEntry(requestMessage.getClientId().trim(),requestMessage.getTerminalId().trim() ,key1,key2);
        }
        else
        {
            msg.setResultMsg(result.getErrorCode()+":"+result.getMsg());
            msg.setResult(0x88);
        }
        return msg;
    }

}
