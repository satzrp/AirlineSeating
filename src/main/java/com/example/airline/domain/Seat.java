package com.example.airline.domain;

public class Seat {
    private final int rowNumber;
    private final SeatType type;
    private boolean available;
    private int passengerNo;
    public Seat(int rowNumber, SeatType type) {
        this.rowNumber = rowNumber;
        this.type = type;
        this.available = true;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public SeatType getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getPassengerNo() {
        return passengerNo;
    }

    public void setPassengerNo(int passengerNo) {
        this.passengerNo = passengerNo;
    }

    public boolean isAisle() {
        return getType().equals(SeatType.AISLE);
    }

    public boolean isMiddle() {
        return getType().equals(SeatType.MIDDLE);
    }

    public boolean isWindow() {
        return getType().equals(SeatType.WINDOW);
    }
}
