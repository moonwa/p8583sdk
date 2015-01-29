package com.denovo.p8583server.requestMessageBuilders;

import com.denovo.p8583.P8583Fields;
import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.RequestMessage;
import com.denovo.p8583.fields.BinaryVarLengthP8583Field;
import com.denovo.p8583server.requestMessages.DealRollbackRequestMessage;

/**
 * Created by Administrator on 2015/1/28.
 */
public class DealRollbackRequestMessageBuilder extends DefaultRequestMessagePackBuilder {
    @Override
    public void Init(P8583Fields fields) {
        super.Init(fields);
        fields.addP8583ItemAt(62, new BinaryVarLengthP8583Field(72, 3, true));
    }

    @Override
    protected RequestMessage createRequestMessage(P8583Pack p8583Pack) throws Exception {
        DealRollbackRequestMessage request = new DealRollbackRequestMessage(p8583Pack.getMessageType(), p8583Pack.getTpud());
        super.update(p8583Pack, request);


        return request;
    }

}
