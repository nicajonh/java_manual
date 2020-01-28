package com.tida.manual.agrithom;/**
 * Created by nicajonh on 2019/4/27.
 * Description ${TEXT}
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import com.tida.manual.util.PatternUtil;

/**
 * @ClassName ExpressConvert
 * @Description TODO
 * @Author nicajonh
 * @Date 2019/4/27 18:17
 * @Version 1.0
 **/
public class ExpressConvert {

    /**
     * @Author nicajonh
     * @Description 计算后缀表达式的值
     * @Date 18:29 2019/4/27
     * @Param [list]
     * @return int
     **/
    public static int getResutl(List<String> list){
        Stack<String> stack = new Stack<>();
        for(int i=0;i< list.size();i++){
            String str=list.get(i);
            if(PatternUtil.isNumberic(str)) {
                stack.push(list.get(i));//当遇到操作数直接压栈
            }else { //当遇到操作符的时候，从栈中弹出两个元素，然后根据运算符的不同做相应的运算，然后把运算结果压栈
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                if(str.equals("+")) stack.push(a+b+"");
                else if (str.equals("-")) stack.push(b-a+"");
                else if(str.equals("*")) stack.push(a*b+"");
                else stack.push(b/a+"");
            }
        }
        return Integer.parseInt(stack.pop());//计算最终结果
    }
    /**
     * @Author nicajonh
     * @Description 拆分字符串表达式()
     * @Date 20:48 2019/4/27
     * @Param [str]
     * @return java.util.List<java.lang.String>
     **/
    public static String[] splitExpress(String str){
//       String[] list=str.split("\\[\\+|/|-|\\*]");
//       List<String> strList=Arrays.asList(list);
        List<String> strList=new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9' ) {
                sb.append(c);
                if(i==str.length()-1){
                    strList.add(sb.toString());
                    String[] strs=new String[strList.size()];
                    strList.toArray(strs);
                    return strs;
                }
                continue;
            }
            if(sb.length()!=0){
                strList.add(sb.toString());
                sb.delete(0,sb.length());
            }
            strList.add(c+"");

        }
        return (String[]) strList.toArray();
    }
    /**
     * @Author nicajonh
     * @Description 把中缀表达式转为后缀表达式
     * ex:16+32/21*3-2 => (16,32,/,21,3,-,1)
     * 前提条件：操作数的范围为正整数，操作符为+,-,*,/,以及()
     * @Date 18:44 2019/4/27
     * @Param 
     * @return 
     **/
    public static List<String> getExpressList(String s){
        Stack<String> stack = new Stack<>();//保存操作符
        LinkedList<String> linkList= new LinkedList<>();//保存确定优先级后的表达式
        String[] slist=splitExpress(s);
        for (int i = 0; i < slist.length; i++) {
            String str=slist[i];
            if(PatternUtil.isNumberic(str)){
                linkList.add(str);
            }else if(str.equals("+")||str.equals("-")||str.equals("/")||str.equals("*")){
                //把比当前操作符c的优先级高或者等于c的优先级的操作符依次弹出栈并保存到后缀表达式中，直到遇到栈顶操作符的优先级比c低
                while(!stack.isEmpty()) {
                    if(getPriority(stack.peek()) >= getPriority(str)) {
                        linkList.add(stack.pop());
                    }
                    else break;
                }
                stack.push(str);//当前操作符c入栈
            }else if(str.equals("(")) stack.push(str);//右括号直接入栈
            else {
                //当遇到右括号的时候，把栈中的操作符依次弹出并追加到后缀表达式中，直到遇到左括号停止，并把左括号弹出。
                while(!stack.peek().equals("(")) {
                    linkList.add(stack.pop());
                }
                stack.pop();
            }
        }
        //把栈中所有的操作符全部弹出追加到后缀表达式中
        while(!stack.isEmpty()) linkList.add(stack.pop());
        return linkList;
    }

    /**
     * @Author nicajonh
     * @Description 计算运算符的优先级
     * @Date 21:45 2019/4/27
     * @Param [c]
     * @return int
     **/
    public static int getPriority(String c) {
        if(c.equals("+") || c.equals("-")) return 1;
        else if(c.equals("*") || c.equals("/")) return 2;
        else return 0;
    }

    public static void main(String[] args){
        List<String> strList=getExpressList("(16+32)/24*3-2");
        int result = getResutl(strList);
        System.out.println("the result is:"+result);
    }
}
