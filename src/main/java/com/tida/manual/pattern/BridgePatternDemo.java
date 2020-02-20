package com.tida.manual.pattern;/**
 * Created by Administrator on 2020/2/20.
 * Description ${TEXT}
 */

/**
 * @ClassName BridgePatternDemo
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/20 18:12
 * @Version 1.0
 **/

interface DrawAPI {
    public void drawCircle(int radius, int x, int y);
}

class RedCircle implements DrawAPI{
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}
class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}

class BlueCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: blue, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}

abstract class XShape{
    protected DrawAPI drawAPI;

    protected XShape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }
    abstract void draw();
}

class RCircle extends XShape{
    private int x,y,radius;
    public RCircle(int x,int y,int radius,DrawAPI drawAPI) {
        super(drawAPI);
        this.x=x;
        this.y=y;
        this.radius=radius;
    }


    @Override
    public void draw() {
        drawAPI.drawCircle(radius,x,y);
    }
}

public class BridgePatternDemo {
    public static void main(String[] args){
        XShape redCircle = new RCircle(100,100,8,new RedCircle());
        XShape greenCircle = new RCircle(100,100, 15, new GreenCircle());
        XShape blueCircle = new RCircle(60,60, 20, new BlueCircle());
        redCircle.draw();
        greenCircle.draw();
        blueCircle.draw();
    }
}
