package com.tida.manual.pattern;/**
 * Created by Administrator on 2020/2/13.
 * Description ${TEXT}
 */

/**
 * @ClassName StrategyPatternDemo
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/13 13:12
 * @Version 1.0
 **/

interface Strategy{
    public int doOperation(int num1,int num2);
}
class OperationAdd implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
class OperationSubstract implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
class OperationMultiply implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
class SContext{
    private Strategy strategy;
    public SContext(Strategy strategy){
        this.strategy=strategy;
    }
    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}
public class StrategyPatternDemo {

    public static void main(String[] args) {
        SContext scontext = new SContext(new OperationAdd());
        System.out.println("10 + 5 = " + scontext.executeStrategy(10, 5));

        scontext = new SContext(new OperationSubstract());
        System.out.println("10 - 5 = " + scontext.executeStrategy(10, 5));

        scontext = new SContext(new OperationMultiply());
        System.out.println("10 * 5 = " + scontext.executeStrategy(10, 5));
    }
}
