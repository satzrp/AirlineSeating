package com.example.airline.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlockTest {
    @Test
    public void testSeatsInRow() {
        int[][] input = new int[][]{{3,2}, {4,3}, {2,3}, {3,4}};
        Airline airline = new Airline();
        airline.build(input);
        Block blockZero = airline.getBlocks().get(0);
        assertEquals(3, blockZero.getSeatsInRow(0).size());
        Block blockThree = airline.getBlocks().get(2);
        assertEquals(2, blockThree.getSeatsInRow(2).size());
    }
}
