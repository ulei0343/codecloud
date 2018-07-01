package club.codecloud.base.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.*;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

/**
 * @author ulei
 * @date 2018/4/13
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 默认超时时间
     */
    private static final int DEFAULT_TIME_OUT = 3 * 1000;

    /**
     * 最大header数量
     */
    private static final int MAX_HEADER_COUNT = 200;

    /**
     * 最大行数限制
     */
    private static final int MAX_LINE_LENGTH = 20000;

    /**
     * 设置整个连接池最大连接数
     */
    private static final int MAX_CONN_TOTAL = 2000;

    /**
     * 设置每个主机的最大连接数
     */
    private static final int MAX_PER_ROUTE = 200;

    /**
     * 请求头
     */
    private static Map<String, String> requestHeaders;

    private static PoolingHttpClientConnectionManager connManager;
    private static CloseableHttpClient httpClient;

    static {


        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", new SSLConnectionSocketFactory(createDefaultSSLContext()))
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .build();

        connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        // Create socket configuration
        SocketConfig socketConfig = SocketConfig.custom()
                // 设置socket 超时时间
                .setSoTimeout(DEFAULT_TIME_OUT)
                .build();
        connManager.setDefaultSocketConfig(socketConfig);


        // Create message constraints
        MessageConstraints messageConstraints = MessageConstraints.custom()
                .setMaxHeaderCount(MAX_HEADER_COUNT)
                .setMaxLineLength(MAX_LINE_LENGTH)
                .build();

        // Create connection configuration
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(Consts.UTF_8)
                .setMessageConstraints(messageConstraints)
                .build();

        connManager.setDefaultConnectionConfig(connectionConfig);
        connManager.setMaxTotal(MAX_CONN_TOTAL);
        connManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);

        // 开启重试机制，处理重定向
        httpClient = HttpClients.custom()
                .setConnectionManager(connManager)
                .setRetryHandler(new DefaultHttpRequestRetryHandler())
                .disableAutomaticRetries()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();

        initDefaultHeader();

    }

    /**
     * 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
     *
     * @return
     */
    private static SSLContext createDefaultSSLContext() {
        SSLContext sslContext = SSLContexts.createDefault();
        try {
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, null);
        } catch (KeyManagementException e) {
            logger.error("HttpUtils createDefaultSSLContext] error", e);
        }
        return sslContext;
    }

    /**
     * 初始化默认请求头
     */
    private static void initDefaultHeader() {
        requestHeaders = Maps.newHashMap();
        requestHeaders.put("Accept", "*/*");
        requestHeaders.put("Accept-Encoding", "gzip, deflate");
        requestHeaders.put("Accept-Language", "zh-CN,zh;q=0.8");
        requestHeaders.put("Upgrade-Insecure-Requests", "1");
        requestHeaders.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
    }

    /**
     * 设置请求头
     *
     * @param headers
     */
    private static void addHeaders(Map<String, String> headers) {
        requestHeaders.putAll(headers);
    }

    public static String get(String url, Map<String, String> params) {
        return get(url, DEFAULT_TIME_OUT, null, params, StandardCharsets.UTF_8);
    }

    public static String get(String url) {
        return get(url, null);
    }

    /**
     * get请求
     *
     * @param url      请求地址
     * @param timeout  超时时间
     * @param headers  请求头
     * @param params   请求参数
     * @param encoding 请求编码
     * @return
     */
    public static String get(String url, int timeout, Map<String, String> headers,
                             Map<String, String> params, Charset encoding) {


        // 设置请求参数
        String requestUrl = URLUtils.urlWithForm(url, params);
        logger.info("[HttpUtils Get] begin execute:{}", requestUrl);
        HttpGet httpGet = new HttpGet(requestUrl);

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .build();

        httpGet.setConfig(requestConfig);

        //设置请求头
        if (headers != null) {
            addHeaders(headers);
        }
        for (String key : requestHeaders.keySet()) {
            httpGet.setHeader(key, requestHeaders.get(key));
        }
        return execute(httpGet, encoding);
    }

    public static String post(String url, Map<String, String> params) {
        return post(url, DEFAULT_TIME_OUT, null, params, StandardCharsets.UTF_8);
    }

    /**
     * post请求
     *
     * @param url      请求地址
     * @param timeout  超时时间
     * @param headers  请求头
     * @param params   请求参数
     * @param encoding 请求编码
     * @return
     */
    public static String post(String url, int timeout, Map<String, String> headers,
                              Map<String, String> params, Charset encoding) {


        List<NameValuePair> pairs = Lists.newArrayList();
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
            }
        }

        UrlEncodedFormEntity formEntity = null;
        try {
            formEntity = new UrlEncodedFormEntity(pairs, encoding);
        } catch (Exception e) {
            logger.error("[HttpUtils Post] encode error url:{},params:{}", url, pairs, e);
        }
        if (formEntity == null) {
            return null;
        }

        HttpPost httpPost = new HttpPost(url);
        //设置请求头
        if (headers != null) {
            addHeaders(headers);
        }
        for (String key : requestHeaders.keySet()) {
            httpPost.setHeader(key, requestHeaders.get(key));
        }
        httpPost.setEntity(formEntity);

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setExpectContinueEnabled(false).build();
        httpPost.setConfig(requestConfig);
        logger.info("[HttpUtils Post] begin invoke url:{},params:{}", url, pairs);
        return execute(httpPost, encoding);
    }

    private static String execute(HttpRequestBase request, Charset encoding) {
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                int code = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                try {
                    if (code == HttpStatus.SC_OK && entity != null) {
                        return EntityUtils.toString(entity, encoding);
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } catch (Exception e) {
                logger.error("[HttpUtils] get response error, url:{}", request.getURI(), e);
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (Exception e) {
            logger.error("[HttpUtils] execute error, url:{}", request.getURI(), e);
        } finally {
            request.releaseConnection();
        }
        return null;
    }
}
