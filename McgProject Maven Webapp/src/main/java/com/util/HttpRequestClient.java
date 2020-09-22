package com.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.URIException;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * <p>hhtp工具类</p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author jiangn
 * @version 1.0
 * @since 1.0
 */
public class HttpRequestClient {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestClient.class);
    private static final CloseableHttpClient httpClient;
    private static PoolingHttpClientConnectionManager cm;
    public static final String CHARSET = "UTF-8";

    static {
        cm = new PoolingHttpClientConnectionManager();
        // 将最大连接数增加到200
        cm.setMaxTotal(200);
        // 将每个路由基础的连接增加到20
        cm.setDefaultMaxPerRoute(20);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(6000000).setSocketTimeout(1500000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).setConnectionManager(cm).build();
    }


    /**
     * 发送Get请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String get(String url, List<NameValuePair> params) throws IOException, URISyntaxException {
        String body = null;
        try {
            // Get请求
            HttpGet httpget = new HttpGet(url);
            // 设置参数
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
            httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
            // 发送请求
            HttpResponse httpresponse = httpClient.execute(httpget);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity, "UTF-8");
            if (entity != null) {
                entity.consumeContent();
            }
        } catch (ParseException e) {
            logger.error("ParseException", e);
            throw e;
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
            throw e;
        } catch (IOException e) {
            logger.error("IOException", e);
            throw e;
        } catch (URISyntaxException e) {
            logger.error("URISyntaxException", e);
            throw e;
        }
        return body;
    }

    public static String postRequestJSONObject(String url, JSONObject jSONObject) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            List<NameValuePair> pairs = null;
            if (jSONObject != null && !jSONObject.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(jSONObject.size());
                Set<String> bkeys = jSONObject.keySet();
                for (Iterator<String> itr = bkeys.iterator(); itr.hasNext(); ) {
                    String key = (String) itr.next();
                    Object value = (Object) jSONObject.get(key);
                    if (value instanceof JSONArray) {
                        value = (JSONArray) value;
                        value = ((JSONArray) value).toJSONString();
                    }
                    pairs.add(new BasicNameValuePair(key, value != null ? (String) value : ""));
                }
            }
            HttpPost httpPost = new HttpPost(url);
            // cm.setMaxPerRoute(new HttpRoute(new HttpHost(httpPost)), 50);
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                return null;
                // throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            return result;
        }/*catch (HttpException e) {
             logger.error(e);
    		}*/ catch (IOException e) {
            logger.error("IOException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {//释放资源
            try {
                if (response != null) {
                    response.close();
                }
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                //httpClient.close();
            } catch (Exception e) {
                logger.error("Exception", e);
            }
        }
        return null;
    }

    /**
     * 通过post方式上送数据返回结果
     *
     * @param url 请求地址
     * @return String 返回的内容
     * @throws FileNotFoundException
     * @throws URIException          URl不存在的时候
     */
    public static String postRequest(String url, Map paramMap) throws Exception {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            List<NameValuePair> pairs = null;
            if (paramMap != null && !paramMap.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(paramMap.size());
                Set<String> bkeys = paramMap.keySet();
                for (Iterator<String> itr = bkeys.iterator(); itr.hasNext(); ) {
                    String key = (String) itr.next();
                    Object value = (Object) paramMap.get(key);
                    pairs.add(new BasicNameValuePair(key, value != null ? (value + "") : ""));
                }
            }
            HttpPost httpPost = new HttpPost(url + "?random=" + System.currentTimeMillis() + UUID.randomUUID().toString().replace("-", ""));
            // cm.setMaxPerRoute(new HttpRoute(new HttpHost(httpPost)), 50);
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                logger.info("请求系统返回码："+statusCode);
                httpPost.abort();
                return null;
                // throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            return result;
        }/*catch (HttpException e) {
            logger.error(e);
   		} */ catch (IOException e) {
            logger.error("IOException", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception", e);
            throw e;
        } finally {//释放资源
            try {
                if (response != null) {
                    response.close();
                }
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                //httpClient.close();
            } catch (Exception e) {
                logger.error("Exception", e);
            }
        }
    }

    public static String getRequest(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                return null;
            }
            entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            return result;
        } catch (IOException e) {
            logger.error("IOException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {//释放资源
            try {
                if (response != null) {
                    response.close();
                }
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                //httpClient.close();
            } catch (Exception e) {
                logger.error("Exception", e);
            }
        }
        return null;
    }

    /**
     * 通过post方式上送数据返回结果
     *
     * @param url 请求地址
     * @return String 返回的内容
     * @throws FileNotFoundException
     * @throws URIException          URl不存在的时候
     */
    public static String postRequest(String url, Map paramMap, String token) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            List<NameValuePair> pairs = null;
            if (paramMap != null && !paramMap.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(paramMap.size());
                Set<String> bkeys = paramMap.keySet();
                for (Iterator<String> itr = bkeys.iterator(); itr.hasNext(); ) {
                    String key = (String) itr.next();
                    Object value = (Object) paramMap.get(key);
                    pairs.add(new BasicNameValuePair(key, value != null ? String.valueOf(value) : ""));
                }
            }
            HttpPost httpPost = new HttpPost(url + "?random=" + System.currentTimeMillis() + UUID.randomUUID().toString().replace("-", ""));
            // cm.setMaxPerRoute(new HttpRoute(new HttpHost(httpPost)), 50);
            httpPost.addHeader("appToken", token);
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            response = httpClient.execute(httpPost);

            if (response == null) {
                return null;
            }

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                return EntityUtils.toString(response.getEntity(), "utf-8");
                // throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            return result;
        }/*catch (HttpException e) {
            logger.error(e);
   		} */ catch (IOException e) {
            logger.error("IOException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {//释放资源
            try {
                if (response != null) {
                    response.close();
                }
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                //httpClient.close();
            } catch (Exception e) {
                logger.error("Exception", e);
            }
        }
        return null;
    }

    /**
     * 通过post方式上送数据返回结果
     *
     * @param url 请求地址
     * @return String 返回的内容
     * @throws FileNotFoundException
     * @throws URIException          URl不存在的时候
     */
    public static String postRequest(String url, Map paramMap, String appToken, String serverToken) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            List<NameValuePair> pairs = null;
            if (paramMap != null && !paramMap.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(paramMap.size());
                Set<String> bkeys = paramMap.keySet();
                for (Iterator<String> itr = bkeys.iterator(); itr.hasNext(); ) {
                    String key = (String) itr.next();
                    Object value = (Object) paramMap.get(key);
                    pairs.add(new BasicNameValuePair(key, value != null ? String.valueOf(value) : ""));
                }
            }
            HttpPost httpPost = new HttpPost(url + "?random=" + System.currentTimeMillis() + UUID.randomUUID().toString().replace("-", ""));
            // cm.setMaxPerRoute(new HttpRoute(new HttpHost(httpPost)), 50);
            httpPost.addHeader("appToken", appToken);
            httpPost.addHeader("serverToken", serverToken);
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            response = httpClient.execute(httpPost);
            if (response == null) {
                return null;
            }

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                return response.toString();
                // throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            return result;
        }/*catch (HttpException e) {
            logger.error(e);
   		} */ catch (IOException e) {
            logger.error("IOException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {//释放资源
            try {
                if (response != null) {
                    response.close();
                }
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                //httpClient.close();
            } catch (Exception e) {
                logger.error("Exception", e);
            }
        }
        return null;
    }

    /**
     * 通过post方式上送数据内容返回结果
     *
     * @param body 放到流中字符
     * @param url  请求地址
     * @return String 返回的内容
     * @throws FileNotFoundException
     * @throws URIException          URl不存在的时候
     */
    public static String postRequest(String url, String body) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("body", body));
            HttpPost httpPost = new HttpPost(url + "?random=" + System.currentTimeMillis() + UUID.randomUUID().toString().replace("-", ""));
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                return null;
                // throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            return result;
        }/*catch (HttpException e) {
                logger.error(e);
	   		}*/ catch (IOException e) {
            logger.error("IOException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {//释放资源
            try {
                if (response != null) {
                    response.close();
                }
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                //httpClient.close();
            } catch (Exception e) {
                logger.error("Exception", e);
            }
        }
        return null;
    }

    /**
     * 通过post方式上送数据内容返回结果
     *
     * @param body 放到流中字符
     * @param url  请求地址
     * @return String 返回的内容
     * @throws FileNotFoundException
     * @throws URIException          URl不存在的时候
     */
    public static ByteArrayOutputStream postRequestStream(String url, String body) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("body", body));
            HttpPost httpPost = new HttpPost(url + "?random=" + System.currentTimeMillis() + UUID.randomUUID().toString().replace("-", ""));
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                return null;
            }
            entity = response.getEntity();
            InputStream in = null;
            ByteArrayOutputStream result = null;
            if (entity != null) {
                in = entity.getContent();
                try {
                    result = new ByteArrayOutputStream();
                    int l = -1;
                    byte[] tmp = new byte[1024];
                    while ((l = in.read(tmp)) != -1) {
                        result.write(tmp, 0, l);
                    }
                    result.close();
                } finally {
                    // 关闭低层流。
                    in.close();
                }
            }
            return result;
        }/*catch (HttpException e) {
	        	logger.error(e);
	   		}*/ catch (IOException e) {
            logger.error("IOException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {//释放资源
            try {
                if (response != null) {
                    response.close();
                }
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                //httpClient.close();
            } catch (Exception e) {
                logger.error("Exception", e);
            }
        }
        return null;
    }

    public static ByteArrayOutputStream postRequestStream(String url, Map paramMap) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        if (StringUtils.isBlank(url)) {
            return null;
        }
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            List<NameValuePair> pairs = null;
            if (paramMap != null && !paramMap.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(paramMap.size());
                Set<String> bkeys = paramMap.keySet();
                for (Iterator<String> itr = bkeys.iterator(); itr.hasNext(); ) {
                    String key = (String) itr.next();
                    Object value = (Object) paramMap.get(key);
                    pairs.add(new BasicNameValuePair(key, value != null ? (String) value : ""));
                }
            }
            HttpPost httpPost = new HttpPost(url + "?random=" + System.currentTimeMillis() + UUID.randomUUID().toString().replace("-", ""));
            // cm.setMaxPerRoute(new HttpRoute(new HttpHost(httpPost)), 50);
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                return null;
            }
            entity = response.getEntity();
            InputStream in = null;
            ByteArrayOutputStream result = null;
            if (entity != null) {
                in = entity.getContent();
                try {
                    result = new ByteArrayOutputStream();
                    int l = -1;
                    byte[] tmp = new byte[1024];
                    while ((l = in.read(tmp)) != -1) {
                        result.write(tmp, 0, l);
                    }
                    result.close();
                } finally {
                    // 关闭低层流。
                    in.close();
                }
            }
            return result;
        }/*catch (HttpException e) {
        	logger.error(e);
   		}*/ catch (IOException e) {
            logger.error("IOException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {//释放资源
            try {
                if (response != null) {
                    response.close();
                }
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                //httpClient.close();
            } catch (Exception e) {
                logger.error("Exception", e);
            }
        }
        return null;
    }
    public static List<NameValuePair> mapToPair(Map param) {
        List<NameValuePair> pairs = null;
        if (param != null && !param.isEmpty()) {
            pairs = new ArrayList<NameValuePair>(param.size());
            Set<String> bkeys = param.keySet();
            for (Iterator<String> itr = bkeys.iterator(); itr.hasNext(); ) {
                String key = (String) itr.next();
                Object value = (Object) param.get(key);
                pairs.add(new BasicNameValuePair(key, value != null ? (value + "") : ""));
            }
        }
        return pairs;
    }

    public static String httpClientPost(String urlParam, Map<String, Object> params, String charset) {
        StringBuffer resultBuffer = null;
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(urlParam);
        // 构建请求参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> elem = iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), String.valueOf(elem.getValue())));
        }
        BufferedReader br = null;
        try {
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = client.execute(httpPost);
            // 读取服务器响应数据
            resultBuffer = new StringBuffer();
            br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String temp;
            while ((temp = br.readLine()) != null) {
                resultBuffer.append(temp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                    throw new RuntimeException(e);
                }
            }
        }
        return resultBuffer.toString();
    }
}
