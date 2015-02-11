package com.denovo.p8583server.responseMessagesEncoders;

import com.denovo.p8583.*;
import com.denovo.p8583.fields.BinaryVarLengthP8583Field;
import com.denovo.p8583server.handlers.handlercommon.Globals;
import com.denovo.p8583server.responseMessages.DealRollbackResponseMessage;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Administrator on 2015/1/28.
 */
public class DealRollbackResponseMessageEncoder extends DefaultResponseMessageParser {
    @Override
    public void Init(P8583Fields fields) {
        super.Init(fields);
        fields.addP8583ItemAt(62, new BinaryVarLengthP8583Field(72, 3, true));
    }
    @Override
    public void update(ResponseMessage message, P8583Pack pack) throws Exception {
        DealRollbackResponseMessage msg = (DealRollbackResponseMessage) message;

        int[] nums= msg.getPoss();
        for(int n : nums){
            if(msg.getP8583Fields().get(n-1).getHasValue()){
                pack.setString(n,msg.getP8583Fields().get(n-1).getString());
            }
        }
        pack.setString(12, new SimpleDateFormat("HHmmss").format(new Date()));
        pack.setString(13, new SimpleDateFormat("MMdd").format(new Date()));
        pack.setString(44, "0001");
        if(msg.getResult()!=0) {
            super.update(message, pack);
        }else {

            pack.setMessageType("0410");
            ResponseMessageEncoders d = new ResponseMessageEncoders();
            pack.setString(64, "");
            super.update(message, pack);
            pack.setString(64, Ledes.MACEncrypt(d.pack(pack), Globals.GetKeyEntry(msg.getClientId().trim(), msg.getTerminalId().trim()).getKey2(), 0));
        }

    }
}
