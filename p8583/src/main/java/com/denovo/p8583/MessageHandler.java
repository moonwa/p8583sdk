package com.denovo.p8583;

import java.security.NoSuchAlgorithmException;

/**
 * Created by moonwa on 15-1-18.
 */
public interface MessageHandler {
    ResponseMessage handle() throws Exception;
}
