package com.denovo.p8583server.responseMessagesEncoders;

import com.denovo.p8583.P8583Fields;
import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.ResponseMessage;
import com.denovo.p8583.fields.BinaryVarLengthP8583Field;
import com.denovo.p8583server.responseMessages.SignInResponseMessage;
import com.denovo.p8583server.responseMessagesEncoders.DefaultResponseMessageParser;

public class SignInResponseMessageEncoder extends DefaultResponseMessageParser {
    @Override
    public void Init(P8583Fields fields) {
        super.Init(fields);
        fields.addP8583ItemAt(62, new BinaryVarLengthP8583Field(72, 3, true));
    }
    @Override
    public void update(ResponseMessage message, P8583Pack pack) throws Exception {
        SignInResponseMessage msg = (SignInResponseMessage) message;
        pack.setString(62, msg.getTransportationKey());
        super.update(message, pack);
    }
}
