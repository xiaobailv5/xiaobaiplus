package com.lv.xiaobaiplus.util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zxw
 * @description
 * @date 2021/12/4
 */
public class AuditUtils {
    static Pattern   premovePun = Pattern.compile("(\\d\\pP)?(\\d\\d\\pP)?");
    static Pattern   premoveNum = Pattern.compile("\\d");

    //去除序号和所有的标点符合
    public static String removePun(String cont){
        //去除序号
        Matcher m = premovePun.matcher(cont);
        String s = m.replaceAll("");
        //去除所有标点符合，保留文本中的数据
        String str = s.replaceAll("[\\pP‘’“”]", "");
        return removNum(str);
    }
    //去除序号和所有的标点符合
    public static List<String>  spilCheckCont(String cont){
        //截取无用内容
        String contSub = cont.substring(cont.indexOf("加班内容")+4, cont.length());

        //按照序号拆分
        String[] sts = contSub.split("((\\pP)?[1-6]+(\\pP))");


        List<String> list = new ArrayList<>();
        for (String str :sts){
            if (str !=null && !"".equals(str)){
                String s =removNum(removePun(str));
                if (s !=null && !"".equals(s) &&  !"\n".equals(s)){
                    list.add(s.replaceAll("\r", ""));
                }

            }
        }
        return list;
    }




    //去除序号、所有的标点符合、和所有数据，只保留纯文本
    public static String removNum(String cont){
        //去除所有数据
        Matcher m = premoveNum.matcher(cont);
        String s = m.replaceAll("");
        //去除所有标点符合
        String str = s.replaceAll("[\\pP‘’“”]", "");
        return str;
    }

    public static void main(String[] args) {
        String s= "1、班组门户1202版本转测进展跟踪.2.班组门户1209版本发布说明书及会议纪要整理 3、班组门户IE8兼容性问题处理；";
        String s2= "周一：19：00-21：00 ，加班2小时，加班内容：编写通信云同步标签号码业务需求方案；\n" +
                "周四：19：00-21：00 ，加班2小时，加班内容：完善客户群数据报表需求文档。\n" +
                "周六：9:00-18:00，加班8小时，加班内容：完善门户登录改造业务方案并输出发布手册。";
        String a = removNum(s);
        System.out.println(a);

       /* String[] sts = s.split("((\\pP)?[1-6]+(\\pP))");
        for (String ss :sts){
            System.out.println(removePun(ss));
        }*/


        System.out.println(spilCheckCont(s2));

    }
}
