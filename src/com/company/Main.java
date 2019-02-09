package com.company;

import java.text.DecimalFormat;

class Cart {

    double x;
    double y;

    public Cart(double x, double y) {
        this.x = x;
        this.y = y;
    }

    synchronized void changeLocation(Animals animals) throws InterruptedException {
        x = x + animals.s * Math.cos(Math.toRadians(animals.a));
        y = y + animals.s * Math.sin(Math.toRadians(animals.a));
    }

}

class Animals implements Runnable {

    double s;
    int a;
    Cart cart;

    public Animals(int a, Cart cart) {
        this.s = 1 + (Math.random() * 9);
        this.a = a;
        this.cart = cart;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.s = 1 + (Math.random() * 9);
                cart.changeLocation(this);
                Thread.sleep(1000 + (long) (Math.random() * 4999));
            }
        } catch (InterruptedException e) {
            System.out.println("Error!");
        }
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {

        double x = 0;
        double y = 0;

        try{
            if(!args[0].isEmpty() && !args[1].isEmpty()){
                x = Double.parseDouble(args[0]);
                y = Double.parseDouble(args[1]);
            }

        } catch (Exception e){
        }

        Cart cart = new Cart(x, y);

        Animals swan = new Animals(60, cart);
        Animals crayfish = new Animals(180, cart);
        Animals pike = new Animals(300, cart);

        Thread thr1 = new Thread(swan);
        Thread thr2 = new Thread(crayfish);
        Thread thr3 = new Thread(pike);

        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Начальное положение: " + "(" + df.format(cart.x) + " ; " + df.format(cart.y) + ")");

        thr1.setDaemon(true);
        thr2.setDaemon(true);
        thr3.setDaemon(true);

        thr1.start();
        thr2.start();
        thr3.start();

        for (int i = 0; i < 25; i++) {

            if (i % 2 == 0 && i > 1 ) {
                System.out.println("(" + df.format(cart.x) + " ; " + df.format(cart.y) + ")");
            }
            Thread.sleep(1000);

        }

        System.out.println("Конечное положение: " + "(" + df.format(cart.x) + " ; " + df.format(cart.y) + ")");
    }
}
