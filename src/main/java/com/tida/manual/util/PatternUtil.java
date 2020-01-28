package com.tida.manual.util;/**
 * Created by nicajonh on 2019/4/27.
 * Description ${TEXT}
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName PatternUtil
 * @Description TODO
 * @Author nicajonh
 * @Date 2019/4/27 18:27
 * @Version 1.0
 **/
public class PatternUtil {
    
   /**
    * @Author nicajonh
    * @Description 判断是否是数字
    * @Date 18:29 2019/4/27
    * @Param
    * @return
    **/
   public static boolean isNumberic(String str){
       Pattern pattern = Pattern.compile("[0-9]*");
       Matcher matcher = pattern.matcher(str);
       if(!matcher.matches()){
           return false;
       }
       return true;
   }


//   public static void main(String[] args){
//       List<String> list=splitExpress("16+32/21*3-2");
//       for (int i = 0; i < list.size(); i++) {
//           System.out.println(list.get(i));
//       }
//   }
}
