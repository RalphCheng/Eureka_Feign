package com.cl.agree.springcloud.ribbon.old.utils;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/12/3.
 */
public class KeyStoreCoder extends Coder {
    public static final String KEY_STORE = "JKS";
    public static final String X509 = "X.509";
    
    private static KeyStore getKeyStore(String keyStorePath, String password)
            throws Exception {
        FileInputStream is = new FileInputStream(keyStorePath);
        KeyStore ks = KeyStore.getInstance(KEY_STORE);
        ks.load(is, password.toCharArray());
        is.close();
        return ks;
    }
    
    private static PrivateKey getPrivateKey(String keyStorePath, String alias, String storePass, String keyPass) throws Exception {
        KeyStore ks = getKeyStore(keyStorePath, storePass);
        PrivateKey key = (PrivateKey) ks.getKey(alias, keyPass.toCharArray());
        return key;
    }
    
    
    private static PublicKey getPublicKey(String keyStorePath, String alias, String storePass) throws Exception {
        KeyStore ks = getKeyStore(keyStorePath, storePass);
        PublicKey key = ks.getCertificate(alias).getPublicKey();
        return key;
    }
    
    public static String getStrPublicKey(String keyStorePath, String alias,String storePass) throws Exception{
        PublicKey key = getPublicKey(keyStorePath, alias, storePass);
        String strKey = Coder.encryptBASE64(key.getEncoded());
        return strKey;
    }
    
    public static String getStrPrivateKey(String keyStorePath, String alias,String storePass, String keyPass) throws Exception{
        PrivateKey key = getPrivateKey(keyStorePath, alias, storePass, keyPass);
        String strKey = Coder.encryptBASE64(key.getEncoded());
        return strKey;
    }
    
    /**
     * 公钥加密数据
     * @param publicKey
     * @param srcData
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String publicKey, String srcData) throws Exception{
        //解密
        byte[] pk = Coder.decryptBASE64(publicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(pk);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        //获取公钥
        PublicKey pubKey = kf.generatePublic(spec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] doFinal = cipher.doFinal(srcData.getBytes());
        return encryptBASE64(doFinal);
    }
    
    /**
     * 私钥解密数据
     * @param privateKey
     * @param data
     * @return
     * @throws Exception
     */
    public static String descryptByPrivateKey(String privateKey, String data) throws Exception{
        // BASE64转码解密私钥
        byte[] pk = Coder.decryptBASE64(privateKey);
        // BASE64转码解密密文
        byte[] text = decryptBASE64(data);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(pk);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        // 获取私钥
        PrivateKey prvKey = kf.generatePrivate(spec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prvKey);
        byte[] doFinal = cipher.doFinal(text);
        return new String(doFinal);
    }
    
}
