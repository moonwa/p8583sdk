package com.denovo.p8583;

    /**
 * Created by moonwa on 15-1-14.
 */
public class EmptyP8583Field extends P8583Field {
        @Override
        public int writeData(byte[] bytes, int ptr) {
            return ptr;
        }

        @Override
        public boolean getIsIgnore() {
            return true;
        }
    }
