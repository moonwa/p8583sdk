package com.denovo.p8583;

import com.denovo.p8583.requestMessages.SignInRequestMessage;
import com.denovo.p8583.responseMessages.SignInResponseMessage;

/**
 * Created by moonwa on 15-1-18.
 */
public class SigninHandler implements MessageHandler {
    private SignInRequestMessage requestMessage;

    public SigninHandler(SignInRequestMessage requestMessage) {
        this.requestMessage = requestMessage;
    }

    public ResponseMessage handle() {
        SignInResponseMessage msg = new SignInResponseMessage("0810", requestMessage.getTpud());

        msg.setResult(0);

        String batchno = requestMessage.getBatchNo();
        batchno = batchno.substring(0, 2) + "000000" + batchno.substring(8, batchno.length());
        msg.setBatchNo(batchno);
        msg.setTransportationKey("");
        msg.setRequestMessage(requestMessage);
//        _i8583.settabx_data(61, _posKey1.EncryptKey + _posKey1.Hash + _posKey2.EncryptKey + _posKey2.Hash);
        return msg;
    }
}
