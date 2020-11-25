package com.house.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HtmlController {

    private static final String MM_DD_YYYY_SECOND = "MM/dd/yyyy HH:mm:ss";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginHome(){
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String password(){
        return "password";
    }


    public static String plus8Hours(LocalDateTime date) {
        if (date == null) {
            return "";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MM_DD_YYYY_SECOND);
        return date.plusHours(8).format(dateTimeFormatter);
    }

    public static void main(String[] args) throws Exception {

        String url = "http://poc-auth2-demo.aswatson.net/admin_api/CAE-AUTHORIZATION/admin/user/record_privileges";
        String Authorization = "";
        AccountCenter(url, Authorization);

    }

    public static void AccountCenter(String url, String Authorization) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet postMethod = new HttpGet(url);
        postMethod.addHeader("Content-type", "text/plain;charset=utf-8");
        postMethod.addHeader("accept", "*/*");
        postMethod.addHeader("connection", "Keep-Alive");
        postMethod.addHeader("Authorization", "Bearer 21d9edef-2282-4712-86e3-6af76bf39ec0");
        HttpResponse response = httpClient.execute(postMethod);
        HttpEntity httpEntity = response.getEntity();
        String reponseContent = EntityUtils.toString(httpEntity);
        System.out.println("========" + reponseContent);
    }

}
