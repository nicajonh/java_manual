package com.tida.manual.pattern;/**
 * Created by Administrator on 2020/2/20.
 * Description ${TEXT}
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CommandPatternDemo
 * @Description CommandPattern
 * @Author Administrator
 * @Date 2020/2/20 17:59
 * @Version 1.0
 **/

interface Order{
    void execute();
}

//create instance for do some things
class Stock {

    private String name = "ABC";
    private int quantity = 10;

    public void buy(){
        System.out.println("Stock [ Name: "+name+",Quantity: " + quantity +" ] bought");
    }
    public void sell(){
        System.out.println("Stock [ Name: "+name+",Quantity: " + quantity +" ] sold");
    }
}
class BuyStock implements Order{
    private Stock adbStock;

    public BuyStock(Stock astock){
        this.adbStock=astock;
    }
    @Override
    public void execute() {
        adbStock.buy();
    }
}
class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.sell();
    }
}
class Broker {
    private List<Order> orderList= new ArrayList<>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders() {
        for (Order order:orderList){
            order.execute();
        }
        orderList.clear();
    }
}
class CommandPatternDemo {
    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }
}