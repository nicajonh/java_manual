package com.tida.manual.pattern;/**
 * Created by Administrator on 2020/2/17.
 * Description ${TEXT}
 */

/**
 * @ClassName DecoratorPatternDemo
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/17 15:53
 * @Version 1.0
 **/

interface Shape {
    void draw();
}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}

abstract class ShapeDecorator implements Shape{
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratorShape) {
        this.decoratedShape = decoratorShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }
}

class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}

class MultiColorShapeDecorator extends ShapeDecorator {

    public MultiColorShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedHeight(decoratedShape);
    }

    private void setRedHeight(Shape decoratedShape){
        System.out.println("Height Color: Green");
    }
}

public class DecoratorPatternDemo {
    public static void main(String[] args){

        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());

        ShapeDecorator multiCircl = new MultiColorShapeDecorator(new Circle());
        ShapeDecorator multiRectangle = new MultiColorShapeDecorator(new Rectangle());
        //Shape redCircle = new RedShapeDecorator(new Circle());
        //Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
