package com.denovo.p8583.test;

import com.denovo.p8583.Encoder;
import com.denovo.p8583.Ledes;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by moon.wa on 2015/1/19.
 */
public class LedesTest {
    @Test
    public void encryptTest() throws Exception {
        String encryptedKey = Ledes.encrypt("34534668".getBytes(), Encoder.toBcd("3131313131313131".getBytes()));
        assertEquals( "8E447DCD18B040EC", encryptedKey);
    }
}
