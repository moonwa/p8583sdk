package com.denovo.p8583server.responseMessagesEncoders;

import com.denovo.p8583.P8583Fields;
import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.ResponseMessage;
import com.denovo.p8583.fields.BinaryVarLengthP8583Field;
import com.denovo.p8583server.responseMessages.DealRollbackResponseMessage;


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
        if(msg.getResult()!=0) {
            pack.setString(47, msg.getResultMsg());
        }

        super.update(message, pack);
    }
}
