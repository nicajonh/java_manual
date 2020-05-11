package com.tida.manual.pattern;/**
 * Created by Administrator on 2020/4/21.
 * Description ${TEXT}
 */

/**
 * @ClassName SingTone
 * 以时间换空间，有一定几率存在线程安全问题
 * @Description 双重锁懒汉模式,这种看起来很完美的优化技巧就是double-checked locking，根据JLS规范，上面的代码不可靠！线程有可能得到一个不为null，但是构造不完全的对象,
 * 原因是编译器为了提高执行效率的!!指令重排!!。只要认为在单线程下是没问题的，它就可以进行乱序写入！以保证不要让cpu指令流水线中断
 * @Author Administrator
 * @Date 2020/4/21 21:06
 * @Version 1.0
 **/
class DlcSingTone {
    //dcl双重懒汉模式
    private static DlcSingTone INSTANCE=null;  //使用volatile可以禁止指令的重排序,heppened before原则
    private DlcSingTone(){}
    public static DlcSingTone getDlcSingTone() {
        if (null == INSTANCE) {
            synchronized (DlcSingTone.class) {
                if (null == INSTANCE) INSTANCE = new DlcSingTone();
            }
        }
        return INSTANCE;
    }
}

//原理：一个类只有在被使用时才会初始化，而类初始化过程是非并行的(effective java)推荐,遵循JLS规范
class InstanceHolder{

    private InstanceHolder(){}
    private static class Inner{
        private static final InstanceHolder instanceHolder = new InstanceHolder();
    }
    public InstanceHolder getInstance(){
        return Inner.instanceHolder;
    }
}

/**
 * @Author Administrator
 * @Description 饿汉模式在类被初始化时就已经在内存中创建了对象，以空间换时间，故不存在线程安全问题。
 * @Date 21:36 2020/4/21
 * @Param 
 * @return 
 **/
class HungrySingleTon{
    private static HungrySingleTon INSTANCE = new HungrySingleTon();
    private HungrySingleTon(){}
    public static HungrySingleTon getInstance(){ return INSTANCE; }
}
