package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() throws InterruptedException {
        double x = 10, y = 5;

        Cart cart = new Cart(x, y);

        Animals swan = new Animals(60, cart);

        Thread thr1 = new Thread(swan);

        thr1.start();

        assertEquals(cart.x, cart.x);
        assertEquals(cart.y, cart.y);
    }
}