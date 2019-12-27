package com.cl.agree.springcloud.ribbon.old.utils;

import org.apache.commons.lang.StringUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/25.
 */
public class HttpsUtils {
    /**
     * 处理https GET请求
     * 请求地址、请求方法、参数
     *
     * @param requestUrl
     * @param requestMethod
     * @param token
     * @return
     */
    public static String httpsRequestGet(String requestUrl, String requestMethod, String token) {
        StringBuffer buffer = null;
        try {
            //创建SSLContext
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager[] tm = {new MyX509TrustManager()};
            //初始化
            sslContext.init(null, tm, new SecureRandom());
            //获取SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("Authorization", "Bearer " + token);
            //设置当前实例使用的SSLSoctetFactory
            conn.setSSLSocketFactory(ssf);
            conn.connect();
            //读取服务器端返回的内容
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
    
    /**
     * 向指定URL发送GET方法的请求,默认编码UTF-8 * *
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String token) {
        return sendGet(url, param, "utf-8", token);
    }
    
    /**
     * 向指定URL发送GET方法的请求
     * @param url * 发送请求的URL
     * @param param * 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param charSet * 网页编码
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String charSet, String token) {
        String result = "";
        BufferedReader in = null;
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(
                    new KeyManager[0],
                    new TrustManager[]{new MyX509TrustManager()},
                    new SecureRandom()
            );
            SSLContext.setDefault(ctx);
            
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpsURLConnection connection = (HttpsURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty(
                    "user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"
            );
            if (StringUtils.isNotEmpty(token)) {
                connection.addRequestProperty("token", token);
            }
            connection.setHostnameVerifier(new HostnameVerifier() {
                
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), charSet));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    
    /**
     * 向指定 URL 发送POST方法的请求，默认编码UTF-8
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        return sendPost(url, param, "utf-8");
    }
    
    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param charSet 网页编码
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, String charSet) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(
                    new KeyManager[0],
                    new TrustManager[]{new MyX509TrustManager()},
                    new SecureRandom()
            );
            SSLContext.setDefault(ctx);
            
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty(
                    "user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"
            );
            
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            conn.setHostnameVerifier((hostname, session) -> true);
            
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), charSet));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = null;
        try {
            //创建SSLContext
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager[] tm = {new MyX509TrustManager()};
            //初始化
            sslContext.init(null, tm, new SecureRandom());
            //获取SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            //设置当前实例使用的SSLSoctetFactory
            conn.setSSLSocketFactory(ssf);
            conn.connect();
            //往服务器端写内容
            if (null != outputStr) {
                OutputStream os = conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }
            //读取服务器端返回的内容
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
    
    private static class MyX509TrustManager implements TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException { }
        
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException { }
        
        public X509Certificate[] getAcceptedIssuers() { return null; }
    }
    
//    private static class DefaultTrustManager implements X509TrustManager {
//        @Override
//        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
//                throws CertificateException {
//        }
//
//        @Override
//        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
//                throws CertificateException {
//        }
//
//        @Override
//        public X509Certificate[] getAcceptedIssuers() {
//            return null;
//        }
//    }
}
