package com.denovo.p8583server.handlers;


        import com.denovo.p8583.MessageHandler;
        import com.denovo.p8583.ResponseMessage;
        import com.denovo.p8583server.handlers.handlercommon.JsonHelper;
        import com.denovo.p8583server.handlers.jsonmodel.Result;
        import com.denovo.p8583server.requestMessages.DealRollbackRequestMessage;
        import com.denovo.p8583server.responseMessages.DealRollbackResponseMessage;


/**
 * Created by Administrator on 2015/1/28.
 */
public class DealRollbackHandler implements MessageHandler {
    private DealRollbackRequestMessage requestMessage;

    public DealRollbackHandler(DealRollbackRequestMessage requestMessage) {
        this.requestMessage = requestMessage;
    }

    public ResponseMessage handle() throws Exception {
        DealRollbackResponseMessage msg = new DealRollbackResponseMessage("0410", requestMessage.getTpud());
         //冲正
        Result phoneAndCardNumRegResult = JsonHelper.GetRoolbackResult(requestMessage);
        return msg;
    }
}
