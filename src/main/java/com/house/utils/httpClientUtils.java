package com.house.utils;

import com.alibaba.fastjson.JSON;
import com.house.dto.LoginUser;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * @Author Tim
 * @Date 2020/11/16 16:22
 */
public class httpClientUtils {

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost("http://login.cdnkt.com/login");
        LoginUser user = new LoginUser();
        user.setAccount("test");
        user.setPassword("test123456");
        String jsonString = JSON.toJSONString(user);

        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        httpPost.setEntity(entity);

        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //    public static void main(String[] args) throws Exception{
    //        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
    //        formparams.add(new BasicNameValuePair("account", "test"));
    //        formparams.add(new BasicNameValuePair("password", "test123456"));
    //        HttpEntity reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");
    //
    //        RequestConfig requestConfig = RequestConfig.custom()
    //            .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
    //            .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
    //            .setConnectionRequestTimeout(5000)
    //            .build();
    //
    //        HttpClient client = new DefaultHttpClient();
    //        HttpPost post = new HttpPost("http://login.cdnkt.com/login");
    //        post.setEntity(reqEntity);
    //        post.setConfig(requestConfig);
    //        HttpResponse response = client.execute(post);
    //
    //        if (response.getStatusLine().getStatusCode() == 200) {
    //            HttpEntity resEntity = response.getEntity();
    //            String message = EntityUtils.toString(resEntity, "utf-8");
    //            JSONObject json = JSONObject.parseObject(message);
    //            String code = json.get("code").toString();
    //            System.out.println("请求成功：" + code + message);
    //        } else {
    //            System.out.println("请求失败");
    //        }
    //    }


//    public static void main(String[] args) {
//
//        // 创建默认的httpClient实例.
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpPost httppost = new HttpPost("http://login.cdnkt.com/login");
//        // 创建参数队列
//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        formparams.add(new BasicNameValuePair("account", "test"));
//        formparams.add(new BasicNameValuePair("password", "test123456"));
//        UrlEncodedFormEntity uefEntity;
//        try {
//            StringEntity content =new StringEntity(formparams.toString(), Charset.forName("UTF-8"));// 第二个参数，设置后才会对，内容进行编码
//            content.setContentType("application/soap+xml; charset=UTF-8");
//            content.setContentEncoding("UTF-8");
//            httppost.setEntity(content);
//            System.out.println("executing request " + httppost.getURI());
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            try {
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    System.out.println("--------------------------------------");
//                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
//                    System.out.println("--------------------------------------");
//                }
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭连接,释放资源
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }


}
