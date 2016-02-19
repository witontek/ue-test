package ls.demon.test;

/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: PostFormTest.java, v 0.1 2015年8月13日 下午2:59:13 song.li@witontek.com Exp $
 */
public class PostFormTest {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(PostFormTest.class);

    @Test
    public void test_url_encode() {
        String cs = "gbk";
        String cs2 = "utf-8";

        String appId = "中文1";
        String appSecret = "中文2";
        try {
            String url = "http://localhost:8080/WebApp/tool/at.htm";

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("appId", appId));
            params.add(new BasicNameValuePair("appSecret", appSecret));

            UrlEncodedFormEntity e = new UrlEncodedFormEntity(params, cs);
            logger.info("{}", EntityUtils.toString(e, cs));
            logger.info("{}", EntityUtils.toString(e, cs2));

            logger.info("{}?appId={}&appSecret={}", url, URLEncoder.encode(appId, cs),
                URLEncoder.encode(appSecret, cs));
            logger.info("{}?appId={}&appSecret={}", url, URLEncoder.encode(appId, cs2),
                URLEncoder.encode(appSecret, cs2));
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Test
    public void test_post_params_gbk() {

        String cs = "gb2312";
        test_http_post(cs);

    }

    @Test
    public void test_post_params_utf8() {

        String cs = "utf-8";
        test_http_post(cs);

    }

    /**
     * 
     * @param charset
     */
    private void test_http_post(String charset) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
            httpclient.getParams().setParameter("http.protocol.content-charset", charset);

            HttpPost httppost = new HttpPost("http://localhost:8080/WebApp/tool/at.htm");

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("appId", "中文1"));
            params.add(new BasicNameValuePair("appSecret", "中文12222222222222222"));

            httppost.setEntity(new UrlEncodedFormEntity(params, charset));
            logger.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            logger.info("1={}", EntityUtils.toString(httppost.getEntity(), "UTF-8"));
            logger.info("2={}", EntityUtils.toString(httppost.getEntity(), "gbk"));
            logger.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

            HttpResponse response = httpclient.execute(httppost);

            logger.info("H={}", response.getEntity().getContentType());

            String responseBody = EntityUtils.toString(response.getEntity(), charset);
            logger.info("c={}", responseBody);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Test
    public void test_httpClient_post_b() {
        // 浏览器兼容模式，属性name值都可以被正确解析
        // 但是，不同编码格式下的value无法解析

        String gbk = "gbk";
        String utf8 = "utf-8";
        at_test(gbk, utf8, HttpMultipartMode.BROWSER_COMPATIBLE);
    }

    @Test
    public void test_httpClient_post_s() {
        // strict模式，属性value值都可以被正确解析，因为每一个数据项都是独立编码
        // 但是，属性名存在无法正确解析的问题，是由于name被使用ascii码进行传输

        String gbk = "gbk";
        String utf8 = "utf-8";
        at_test(gbk, utf8, HttpMultipartMode.STRICT);
    }

    /**
     * 
     * @param cs
     * @param cs2
     */
    private void at_test(String cs, String cs2, HttpMultipartMode mode) {

        try {
            Charset defaultCS = Charset.forName(cs);
            String url = "http://localhost:8080/WebApp/tool/at.htm";

            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
            httpclient.getParams().setParameter("http.protocol.content-charset", defaultCS.name());

            HttpPost httpPost = new HttpPost(url);
            logger.info("post url:" + url);
            httpPost.setHeader("User-Agent", "SOHUWapRebot");
            httpPost.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
            httpPost.setHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.7");
            httpPost.setHeader("Connection", "keep-alive");

            MultipartEntity mutiEntity = new MultipartEntity(mode, null, defaultCS);
            mutiEntity.addPart("test中文", new StringBody("美丽的西双版纳", defaultCS));
            mutiEntity.addPart("appId", new StringBody("美丽的西双版纳", Charset.forName(cs)));
            mutiEntity.addPart("appSecret", new StringBody("美丽的西双版纳2", Charset.forName(cs2)));

            httpPost.setEntity(mutiEntity);
            HttpResponse httpResponse = httpclient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String content = EntityUtils.toString(httpEntity);

            logger.info("{}", content);
        } catch (ParseException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    @Test
    public void test_httpClient_post_test_utf8() {
        String url = "http://localhost:8080/WebApp/test_post";
        test_xx(Charset.forName("utf-8"), url, HttpMultipartMode.BROWSER_COMPATIBLE);
    }

    @Test
    public void test_httpClient_post_test_utf8_s() {
        String url = "http://localhost:8080/WebApp/test_post";
        test_xx(Charset.forName("utf-8"), url, HttpMultipartMode.STRICT);
    }

    @Test
    public void test_httpClient_post_test_gbk() {
        String url = "http://localhost:8080/WebApp/test_post";
        test_xx(Charset.forName("gbk"), url, HttpMultipartMode.BROWSER_COMPATIBLE);
    }

    @Test
    public void test_httpClient_post_test_big5() {
        String url = "http://localhost:8080/WebApp/test_post";
        test_xx(Charset.forName("big5"), url, HttpMultipartMode.BROWSER_COMPATIBLE);
    }

    @Test
    public void test_httpClient_post_test_big5_s() {
        String url = "http://localhost:8080/WebApp/test_post";
        test_xx(Charset.forName("big5"), url, HttpMultipartMode.STRICT);
    }

    private void test_xx(Charset charset, String url, HttpMultipartMode model) {
        logger.info("post url:{}, cs={}", url, charset);
        String gbk = "gbk";
        String utf8 = "utf-8";

        try {

            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
            httpclient.getParams().setParameter("http.protocol.content-charset", charset.name());

            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("User-Agent", "SOHUWapRebot");
            httpPost.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
            httpPost.setHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.7");
            httpPost.setHeader("Connection", "keep-alive");

            MultipartEntity mutiEntity = new MultipartEntity(model, null, charset);

            mutiEntity.addPart("app1中文", new StringBody("美丽的西双版纳1", Charset.forName(gbk)));

            mutiEntity.addPart("app2中文", new StringBody("美丽的西双版纳2", Charset.forName(utf8)));

            mutiEntity.addPart("app3中文", new StringBody("中文3", charset));

            httpPost.setEntity(mutiEntity);
            HttpResponse httpResponse = httpclient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String content = EntityUtils.toString(httpEntity);

            logger.info("{}", content);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

}
