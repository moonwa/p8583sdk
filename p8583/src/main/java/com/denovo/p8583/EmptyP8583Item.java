package com.denovo.p8583;

    /**
 * Created by moonwa on 15-1-14.
 */
public class EmptyP8583Item extends P8583Item {
        @Override
        public int writeData(byte[] bytes, int ptr) {
            return ptr;
        }
    }
