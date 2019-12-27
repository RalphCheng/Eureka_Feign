package com.cl.agree.springcloud.feign.config;

import feign.Client;
import feign.Logger;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import sun.misc.BASE64Encoder;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Arrays;


/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/30.
 */
@Configuration
@Slf4j
public class FeignConfig {
    private static final String SERVER_CER_PATH = "D:\\mystore";
    //服务器证书的别名
    private static final String SERVER_CER_ALIAS = "test";
    //服务器证书的密码
    private static final String SERVER_CER_PSW = "123456";
    //服务器的证书
    private static X509Certificate serverCer;
    //服务器主机白名单，用于验证请求的主机名
    private static String[] VERIFY_HOST_NAME_ARRAY = new String[]{"192.9.200.71"};
    
    @Bean
    public Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }
    
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    
    
    @Primary
    @Bean
    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
                              SpringClientFactory clientFactory) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext ctx = SSLContext.getInstance("SSL");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) {
                //该方法检查客户端的证书，若不信任该证书则抛出异常。由于我们不需要对客户端进行认证，
                //因此只需要执行默认的信任管理器的这个方法。
            }
            
            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
                //该方法检查服务器的证书，若不信任该证书同样抛出异常。通过自己实现该方法，
                //可以使之信任我们指定的任何证书。
                log.info("开始校验服务端证书,参数证书链{}, authtype: [{}]", chain, authType);
                if (chain == null || !(chain.length > 0)) {
                    throw new IllegalArgumentException("校验服务端证书: 证书链为空，请检查！");
                }
                if (!("ECDHE_RSA".equalsIgnoreCase(authType))) {
                    throw new CertificateException("checkServerTrusted: AuthType is not ECDHE_RSA");
                }
                int i = 0;
                setCertificate();
                for (X509Certificate cert : chain) {
                    i++;
                    try {
                        cert.checkValidity();
                    } catch (CertificateExpiredException | CertificateNotYetValidException e) {
                        throw new IllegalStateException("证书" + i + "未生效/已过期，请检查！", e);
                    }
//                    System.out.println("服务器的证书：" + serverCer);
                    try {
                        cert.verify(serverCer.getPublicKey());
                    } catch (NoSuchAlgorithmException e) {
                        throw new IllegalStateException("证书" + i + "出现不支持的签名算法，请检查！", e);
                    } catch (InvalidKeyException | NoSuchProviderException e) {
                        throw new IllegalStateException("证书" + i + "键错误/Provider错误", e);
                    } catch (SignatureException e) {
                        throw new IllegalStateException("证书" + i + "签名有误，请检查！", e);
                    }
                }
                
            }
            //返回可信任验证对等体的证书颁发机构证书数组。
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ctx.init(null, new TrustManager[]{tm}, new SecureRandom());
        return new LoadBalancerFeignClient(new Client.Default(
                ctx.getSocketFactory(),
//在实现的HostnameVerifier子类中，需要使用verify函数效验服务器主机名的合法性，否则会导致恶意程序利用中间人攻击绕过主机名效验。
//在握手期间，如果URL的主机名和服务器的标识主机名不匹配，则验证机制可以回调此接口实现程序来确定是否应该允许此连接。
                new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        System.out.println("服务器的hostname: " + hostname);
                        if (StringUtils.isEmpty(hostname)) {
                            System.out.println("服务器主机名为空！");
                            return false;
                        } else if (Arrays.asList(VERIFY_HOST_NAME_ARRAY).contains(hostname)) {
                            System.out.println("服务器主机在白名单中！");
                            return true;
                        } else {
                            System.out.println("服务器主机不在白名单中！");
                            HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                            return hv.verify(hostname, session);
                        }
                    }
                }
        ),
                                           cachingFactory, clientFactory
        );
    }

//主机名验证策略改成严格模式
//SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
//sf.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
    
    
    private void setCertificate(){
        File file = new File("G:\\mystore");
        if (!file.exists()){
            throw new IllegalStateException("文件不存在，请检查！");
        }
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(fis, SERVER_CER_PSW.toCharArray());
            fis.close();
            serverCer = (X509Certificate) keyStore.getCertificate(SERVER_CER_ALIAS);
        } catch (NoSuchAlgorithmException | KeyStoreException | IOException | CertificateException e) {
            throw new IllegalStateException("读取服务器证书出错！");
        }
    }
    
    public static void main(String[] args) throws Exception{
        File file = new File("G:\\mystore");
        if (file.exists()){
            System.out.println(file.getAbsolutePath());
        }
        FileInputStream fis = new FileInputStream(file);
        KeyStore keyStore = KeyStore.getInstance("JKS");
        String storepass="123456";
        keyStore.load(fis, storepass.toCharArray());
        fis.close();
        X509Certificate certificate = (X509Certificate) keyStore.getCertificate("test");
        System.out.println(certificate);
        PublicKey publicKey = certificate.getPublicKey();
        System.out.println("公钥解密前： " + publicKey);
        String base64Str = new BASE64Encoder().encode(publicKey.toString().getBytes("UTF-8"));
        System.out.println("公钥： " + base64Str);
    }
}