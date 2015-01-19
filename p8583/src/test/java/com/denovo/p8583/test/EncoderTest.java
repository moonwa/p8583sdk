package com.denovo.p8583.test;

import com.denovo.p8583.Encoder;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by 013495 on 2015/1/19.
 */
public class EncoderTest {
    @Test
    public void fromBcdTest(){
        assertEquals("45", new String(Encoder.fromBcd((byte) 0x45)));
        assertEquals("05", new String(Encoder.fromBcd((byte) 0x5)));
        assertEquals("4567", new String(Encoder.fromBcds(new byte[]{ 0x45, 0x67})));
    }
    @Test
    public void toBcdTest(){
        assertArrayEquals(new byte[]{ 0x45 }, Encoder.toBcd("45".getBytes()));
        assertArrayEquals(new byte[]{ 0x05 }, Encoder.toBcd("05".getBytes()));
        assertArrayEquals(new byte[]{ 0x50 }, Encoder.toBcd("5".getBytes()));
        assertArrayEquals(new byte[]{0x45, 0x67}, Encoder.toBcd("4567".getBytes()));
//        assertEquals(new String(Encoder.fromBcd((byte) 0x45)), "45");
//        assertEquals("4567", new String(Encoder.fromBcds(new byte[]{0x45, 0x67})));
    }
}
