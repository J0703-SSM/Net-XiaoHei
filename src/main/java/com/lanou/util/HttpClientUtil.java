package com.lanou.util;

import com.lanou.util.CheckSumBuilder;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import java.io.*;

//import net.sf.json.JSONObject;

/**
 * 发送验证码短信
 */
public class HttpClientUtil {

    private static final String appKey = "80f6c4020997bf05afb37203c42d59f3";

    private static final String appSecret = "166d84821ff4";

//    private CheckSumBuilder csb = new CheckSumBuilder();

    public static String sendMessage(String phoneNumber) throws IOException {

//        String responseContent = null;

        HttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost("https://api.netease.im/sms/sendcode.action");

        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        httpPost.setHeader("AppKey", appKey);

        String nonce = getNonce();

        httpPost.setHeader("Nonce", nonce);

        String curTime = System.currentTimeMillis() + "";

        httpPost.setHeader("CurTime", curTime);

        httpPost.setHeader("CheckSum",CheckSumBuilder.getCheckSum(appSecret, nonce, curTime));

        StringEntity entity = new StringEntity("mobile=" + phoneNumber + "&templateid=3130155");

        httpPost.setEntity(entity);

        HttpResponse response = httpClient.execute(httpPost);

        InputStream inputStream = response.getEntity().getContent();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

        StringBuilder strber = new StringBuilder();

        String line = null;

        while ((line = reader.readLine()) != null){

            strber.append(line + "\n");

        }

        inputStream.close();

        return strber.toString();
    }

    private static String getNonce(){

        return (Math.random()*10000 + 1000) + "";

    }

}
