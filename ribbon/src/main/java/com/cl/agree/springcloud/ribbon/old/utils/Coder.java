package com.cl.agree.springcloud.ribbon.old.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/12/3.
 */
public abstract class Coder {
    public static byte[] decryptBASE64(String key) throws Exception {
        return(new BASE64Decoder()).decodeBuffer(key);
    }
    
    public static String encryptBASE64(byte[] key) throws Exception {
        return(new BASE64Encoder()).encodeBuffer(key).replace("\r", "").replace("\n", "");
        
    }
}
