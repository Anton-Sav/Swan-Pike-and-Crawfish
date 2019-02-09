package com.company;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @org.junit.jupiter.api.Test
    void changeLocation() throws InterruptedException {

        Cart cart = new Cart(0,0);
        Animals animals = new Animals(60, cart);

        animals.s = 5.555;

        cart.changeLocation(animals);

        double expectedX = 2.7775000000000003;
        double expectedY = 4.810771118022556;

        assertEquals(expectedX, cart.x);
        assertEquals(expectedY, cart.y);

    }
}