package com.denovo.p8583server.responseMessagesEncoders;

import com.denovo.p8583.*;
import com.denovo.p8583.fields.AsciiFixLengthP8583Field;
import com.denovo.p8583.fields.AsciiVarLengthP8583Field;
import com.denovo.p8583.fields.BinaryVarLengthP8583Field;
import com.denovo.p8583server.handlers.handlercommon.Globals;
import com.denovo.p8583server.responseMessages.DealResponseMessage;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2015/1/26.
 */
public class DealResponseMessageEncoder  extends DefaultResponseMessageParser{
    @Override
    public void Init(P8583Fields fields) {
        super.Init(fields);
    }
    @Override
    public void update(ResponseMessage message, P8583Pack pack) throws Exception {
        DealResponseMessage msg = (DealResponseMessage) message;

        if(msg.getResult()!=0) {
            pack.setString(47, msg.getResultMsg());
            super.update(message, pack);
        }else {
            if(msg.getIsQuery()){
                if(msg.getCodeText()!=null) {
                   String codeText= msg.getCodeText();
                    pack.setString(48,codeText);
                }else {
                    pack.setString(47, msg.getMac());
                    pack.setString(54,msg.getData());
                    pack.setString(62, msg.SetAccount(msg.GetTheCode(msg.getOpenCode()), msg.getAmount().doubleValue(), 0, msg.getPoint()));
                }
            }else {
                int[] nums= msg.getPoss();
                for(int n : nums){
                    if(msg.getP8583Fields().get(n-1).getHasValue()){
                        pack.setString(n,msg.getP8583Fields().get(n-1).getString());
                    }
                }
                pack.setString(62,msg.SetAccount(msg.GetTheCode(msg.getOpenCode()), 0, 0, 0));
                pack.setString(47, msg.getSerialNo());

            }
            pack.setString(12, new SimpleDateFormat("HHmmss").format(new Date()));
            pack.setString(13, new SimpleDateFormat("MMdd").format(new Date()));
            pack.setString(44, "0001");
            pack.setMessageType("0210");
            super.update(message, pack);
            ResponseMessageEncoders d = new ResponseMessageEncoders();
            pack.setString(64,"");
            pack.setString(64, Ledes.MACEncrypt(d.pack(pack), Globals.GetKeyEntry(msg.getClientId().trim(), msg.getTerminalId().trim()).getKey2(), 0));
            String text = pack.display();

        }
        }

}
