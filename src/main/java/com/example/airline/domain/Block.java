package com.example.airline.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Block {
    private final int noOfRows;
    private final int noOfColumns;
    private List<Seat> seats;
    public Block(int noOfRows, int noOfColumns) {
        this.noOfRows = noOfRows;
        this.noOfColumns = noOfColumns;
    }

    public int getNoOfRows() {
        return noOfRows;
    }

    public int getNoOfColumns() {
        return noOfColumns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    // get the list of seats in a specific row in the block
    public List<Seat> getSeatsInRow(int rowNumber) {
        return this.seats.stream()
                .filter(seat -> seat.getRowNumber() == rowNumber)
                .collect(Collectors.toList());
    }
}
