package com.denovo.p8583server.handlers;

import com.denovo.p8583.MessageHandler;
import com.denovo.p8583.ResponseMessage;
import com.denovo.p8583server.handlers.handlercommon.JsonHelper;
import com.denovo.p8583server.handlers.jsonmodel.Result;
import com.denovo.p8583server.requestMessages.SignInOutRequestMessage;
import com.denovo.p8583server.responseMessages.SignInOutResponseMessage;

/**
 * Created by Administrator on 2015/2/9.
 */
public class SignInOutHandler  implements MessageHandler {
        private SignInOutRequestMessage requestMessage;

        public SignInOutHandler(SignInOutRequestMessage requestMessage) {
            this.requestMessage = requestMessage;
        }

        public ResponseMessage handle() throws Exception {
            SignInOutResponseMessage msg = new SignInOutResponseMessage("0830", requestMessage.getTpud());
            msg.setRequestMessage(requestMessage);
            Result result= JsonHelper.GetSignInOutResult(requestMessage);
            if (result.getCode().equals("0")) {
                msg.setResult(0);
            }
            else
            {
                msg.setResultMsg(result.getErrorCode()+":"+result.getMsg());
                msg.setResult(0x88);
            }
            return msg;
        }
}
