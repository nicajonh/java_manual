package com.tida.manual.aop; /**
 * Created by Nica on 2016/10/5.
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//抽象主题类
interface AbstractSubject{
    public abstract void request();
}

//直接主题类
class RealSubject implements  AbstractSubject{
    public void request(){
        System.out.println("real subject");
    }
}

//动态代理类,实现InvocationHandler接口
class DynamicProxy implements InvocationHandler{
    //被代理的实例
    Object obj=null;
    //构造函数，将被代理的实例传动态代理
    public DynamicProxy(Object obj){
        this.obj=obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       //类似AOP编程
        Object result= method.invoke(this.obj,args);
        return result;
    }
}

public class DynamicProxyTest{

    public static void main(String[] arg){
        //被代理的实例
        AbstractSubject realSubject=new RealSubject();
        //获取得被代理类加载器
        ClassLoader loader=realSubject.getClass().getClassLoader();
        // 获得被代理类已实现的所有接口interface,使得动态代理类的实例
        Class<?>[] interfaces=realSubject.getClass().getInterfaces();
        //代理类的实例创建动态代理的实例.用于真正调用处理
        InvocationHandler handler=new DynamicProxy(realSubject);


        /*
         * loader : 被代理类的类加载器
         * interfaces ：被代理类已实现的所有接口，而这些是动态代理类要实现的接口列表
         * handler ： 用被代理类的实例创建动态代理类的实例，用于真正调用处理程序
         *
         * return ：返回实现了被代理类所实现的所有接口的Object对象，即动态代理，需要强制转型
         */
        //获得代理的实例
        AbstractSubject proxy=(AbstractSubject)Proxy.newProxyInstance(loader,interfaces,handler);
        proxy.request();
        //打印出该代理实例的名称
        System.out.println(proxy.getClass().getName());
    }
}