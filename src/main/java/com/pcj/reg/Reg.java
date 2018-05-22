package com.pcj.reg;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reg {


    public static List<String> split(String str,String regx){
        //1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);
        //2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(str);
        //3.String 对象中的matches 方法就是通过这个Matcher和pattern来实现的。

        System.out.println(matcher.matches());
        ArrayList<String> list = new ArrayList();
        //查找符合规则的子串
        while(matcher.find()){
            //获取 字符串
            String s = matcher.group();
             System.out.println(s);
            //获取的字符串的首位置和末位置
            System.out.println(matcher.start()+"--"+matcher.end());
        }
        return list;
    }
}
