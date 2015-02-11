package com.denovo.p8583server.handlers;


        import com.denovo.p8583.Ledes;
        import com.denovo.p8583.MessageHandler;
        import com.denovo.p8583.ResponseMessage;
        import com.denovo.p8583server.handlers.handlercommon.Globals;
        import com.denovo.p8583server.handlers.handlercommon.JsonHelper;
        import com.denovo.p8583server.handlers.handlercommon.KeysEntry;
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
        msg.setRequestMessage(requestMessage);
        msg.setResult(0);
        KeysEntry key = Globals.GetKeyEntry(requestMessage.getClientId().trim(), requestMessage.getTerminalId().trim());

        int[] poss={  2, 3, 11, 12, 13, 14, 25, 41, 42, 44, 47,49, 53, 54, 59,60,61,63,64};
        msg.GetResponse(poss,requestMessage.getP8583Pack());
        if(key==null){
            msg.setResult(0x77);
            return msg;
        }
        if(!Ledes.MACDecrypt(requestMessage.getData(), key.getKey2(), requestMessage.getP8583Pack().get(63).getString())){
            msg.setResult(0xA0);
            return msg;
        }
         //冲正
        Result dealRollbackResult = JsonHelper.GetRoolbackResult(requestMessage);


        if (dealRollbackResult.getCode().equals("0")) {
            String batchno = requestMessage.getBatchNo();
            batchno = batchno.substring(0, 2) + "000000" + batchno.substring(8, batchno.length());
            msg.setBatchNo(batchno);
        }

        return msg;

    }
}
