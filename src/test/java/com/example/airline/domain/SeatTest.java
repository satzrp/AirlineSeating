package com.example.airline.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class SeatTest {
    @Test
    public void testSeatTypes() {
        Seat aisleSeat = new Seat(0, SeatType.AISLE);
        assertTrue(aisleSeat.isAisle());
        Seat windowSeat = new Seat(0, SeatType.WINDOW);
        assertTrue(windowSeat.isWindow());
        Seat middleSeat = new Seat(0, SeatType.MIDDLE);
        assertTrue(middleSeat.isMiddle());
    }

    @Test
    public void testSeatAvailability() {
        Seat seat = new Seat(0, SeatType.AISLE);
        assertTrue(seat.isAvailable());
    }
}
