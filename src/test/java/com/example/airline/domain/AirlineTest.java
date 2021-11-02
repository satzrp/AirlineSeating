package com.example.airline.domain;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AirlineTest {

    int[][] input = new int[][]{{3,2}, {4,3}, {2,3}, {3,4}};

    @Test
    public void testBuild() {
        Airline airline = new Airline();
        airline.build(input);
        // test number of blocks
        assertEquals(4, airline.getBlocks().size());
        // test maximum number of rows in the airline
        assertEquals(4, airline.getMaxRowInAirline());
    }

    @Test
    public void testSeatingArrangement() {
        Airline airline = new Airline();
        airline.build(input);
        airline.updateSeatingArrangement(30);
        List<Seat> filledSeats = airline.getBlocks().values()
                .stream()
                .map(Block::getSeats)
                .flatMap(Collection::stream)
                .filter(Predicate.not(Seat::isAvailable))
                .collect(Collectors.toList());
        assertEquals(30, filledSeats.size());
        String expectedSeatOrder = "19,25,1,21,29,7,2,26,27,3,8,30,0,9,13,0,0,14,4,5,10,11,15,16,6,28,20,12,0,22,17,0,23,18,0,24";
        String seatOrder = airline.getBlocks().values()
                .stream()
                .map(Block::getSeats)
                .flatMap(Collection::stream)
                .map(seat -> String.valueOf(seat.getPassengerNo()))
                .collect(Collectors.joining(","));
        assertEquals(expectedSeatOrder, seatOrder);
    }

    @Test
    public void testSeatingArrangementNegative() {
        Airline airline = new Airline();
        airline.build(input);
        airline.updateSeatingArrangement(100);
        long filledCount = airline.getBlocks().values()
                .stream()
                .map(Block::getSeats)
                .flatMap(Collection::stream)
                .filter(Predicate.not(Seat::isAvailable))
                .count();
        assertEquals(0, filledCount);
    }
}
