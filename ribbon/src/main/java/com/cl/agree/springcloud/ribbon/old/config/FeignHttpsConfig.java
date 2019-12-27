package com.cl.agree.springcloud.ribbon.old.config;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/26.
 */
//@Configuration
//@Component
@Slf4j
public class FeignHttpsConfig {
    
//    @Bean
//    public Retryer feignRetryer() {
//        return Retryer.NEVER_RETRY;
//    }
//
//
//    @Bean
//    public Feign.Builder feignBuilder() {
//        final Client trustSSLSockets = client();
//        return Feign.builder().client(trustSSLSockets);
//    }
//
//    @Bean
//    public Client client(){
//        return new Client.Default(
//                TrustingSSLSocketFactory.get(), new HostnameVerifier() {
//            @Override
//            public boolean verify(String s, SSLSession sslSession) {
//                return false;
//            }
//        });
//    }
//
//
//
//
//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }
//
//
//    public static void main(String[] args) throws Exception{
////        CertificateFactory cf = CertificateFactory.getInstance("X.509");
////        File file = new File("pom-cbp\\target\\classes\\mystore");
////        X509Certificate cert = (X509Certificate)cf.generateCertificate(new FileInputStream(file));
////        PublicKey publicKey = cert.getPublicKey();
////        BASE64Encoder base64Encoder=new BASE64Encoder();
////        String publicKeyString = base64Encoder.encode(publicKey.getEncoded());
////        System.out.println(publicKeyString);
//
//
//
//
//
//
//
//        File file = new File("pom-cbp\\target\\classes\\mystore");
//        FileInputStream fis = new FileInputStream(file);
//        KeyStore keyStore = KeyStore.getInstance("JKS");
//        String storepass="123456";
//        keyStore.load(fis, storepass.toCharArray());
//        fis.close();
//        Certificate certificate = keyStore.getCertificate("123456");
//        System.out.println(certificate);
//        PublicKey publicKey = certificate.getPublicKey();
//        String base64Str = new BASE64Encoder().encode(publicKey.toString().getBytes("UTF-8"));
//        System.out.println(base64Str);
//    }
    
    
    
    /**
     * 跳过认证
     */
//    @Primary
//    @Bean
//    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
//                              SpringClientFactory clientFactory) throws NoSuchAlgorithmException, KeyManagementException {
//        SSLContext ctx = SSLContext.getInstance("SSL");
//        X509TrustManager tm = new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(X509Certificate[] chain,
//                                           String authType) throws CertificateException {
//            }
//            @Override
//            public void checkServerTrusted(X509Certificate[] chain,
//                                           String authType) throws CertificateException {
//            }
//            @Override
//            public X509Certificate[] getAcceptedIssuers() {
//                return null;
//            }
//        };
//        ctx.init(null, new TrustManager[]{tm}, null);
//        return new LoadBalancerFeignClient(new Client.Default(
//                ctx.getSocketFactory(),
//                (hostname, session) -> {
//                    // TODO Auto-generated method stub
//                    return true;
//                }
//        ),
//                                           cachingFactory, clientFactory
//        );
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //增加SSL
//    public static SSLSocketFactory getSSLSocketFactory() {
//        SSLContext sslContext = null;
//        BASE64Decoder decoder = new BASE64Decoder();
//        InputStream keyStoreInput = null;
//        InputStream trustStoreInput = null;
//
//        String alias = "tomcat";
//        String password = "123456";
//
//        String keyStoreBaseStr = "keyStore";
//        String userIdBaseStr = "trustStore";
//        try {
//            byte[] keyStoreBytes = decoder.decodeBuffer(keyStoreBaseStr);
//            byte[] trustStoreBytes = decoder.decodeBuffer(userIdBaseStr);
//            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            keyStoreInput = new ByteArrayInputStream(keyStoreBytes);
//            keyStore.load(keyStoreInput, password.toCharArray());
//
//            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            trustStoreInput = new ByteArrayInputStream(trustStoreBytes);
//            trustStore.load(trustStoreInput, null);
//            sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray())
//                                 .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sslContext.getSocketFactory();
//    }
    
    
//    public static SSLSocketFactory getSSLSocketFactory() {
//        //TODO Exception 需要抛出异常
//        SSLContext sslContext = null;
//        BASE64Decoder decoder = new BASE64Decoder();
//        InputStream keyStoreInput = null;
//        InputStream trustStoreInput = null;
//        String keyStoreBaseStr = keyStore;
//        String userIdBaseStr = trustStore;
//        try {
//            byte[] keyStoreBytes = decoder.decodeBuffer(keyStoreBaseStr);
//            byte[] trustStoreBytes = decoder.decodeBuffer(userIdBaseStr);
//            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            keyStoreInput = new ByteArrayInputStream(keyStoreBytes);
//            keyStore.load(keyStoreInput, password.toCharArray());
//
//            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            trustStoreInput = new ByteArrayInputStream(trustStoreBytes);
//            trustStore.load(trustStoreInput, null);
//            sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray())
//                                 .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//
//            e.printStackTrace();
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (UnrecoverableKeyException e) {
//            e.printStackTrace();
//        }
//        return sslContext.getSocketFactory();
//    }
    
    //方便查看日志，可不用编写
//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }
//
//
//
//    public class NoopHostnameVerifier implements HostnameVerifier {
////        public static final NoopHostnameVerifier INSTANCE = new NoopHostnameVerifier();
//
//        @Override
//        public boolean verify(final String s, final SSLSession sslSession) {
//            return true;
//        }
//
//        @Override
//        public final String toString() {
//            return "NO_OP";
//        }
//    }

//    /**
//     * 增加请求参数加签
//     *
//     * @param webankOpenApiConfig
//     * @return
//     */
//    @Bean
//    WebBankRequestFeignInterceptor webBankRequestFeignInterceptor(@Autowired WebankOpenApiConfig webankOpenApiConfig) {
//        return new WebBankRequestFeignInterceptor(webankOpenApiConfig);
//    }
    
    
    
    /**
     * 负载均衡的client
     * @return
     */
//    @Bean
//    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
//                              SpringClientFactory clientFactory) {
//        return new LoadBalancerFeignClient(new Client.Default(getTrustedSSLSocketFactory(), new HostnameVerifier(){}),
//                                           cachingFactory, clientFactory);
//    }
}
