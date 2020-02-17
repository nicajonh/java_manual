package com.tida.manual.pattern;/**
 * Created by Administrator on 2020/2/17.
 * Description ${TEXT}
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ObserverPatternDemo
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/17 15:40
 * @Version 1.0
 **/


class Subject{

    private List<Observer> observers=new ArrayList<>();
    private int state;

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }
    public void attach(Observer observer){
        this.observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer:observers){
            observer.update();
        }
    }

    public int getState() {
        return state;
    }

}

abstract class Observer{
    protected Subject subject;
    public abstract void update();
}
class BinaryObserver extends Observer{

    BinaryObserver(Subject subject){
        this.subject=subject;
    }
    @Override
    public void update() {
        System.out.println( "Binary String: "
                + Integer.toBinaryString( subject.getState() ) );
    }
}

class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }

}

class HexaObserver extends Observer{

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}

public class ObserverPatternDemo {
    public static void main(String[] args){
        Subject subject=new Subject();
        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }

}
