package com.example.airline.domain;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Airline {
    private final Map<Integer, Block> blocks;
    public Airline() {
        this.blocks = new LinkedHashMap<>();
    }

    public Map<Integer, Block> getBlocks() {
        return this.blocks;
    }

    public void addBlock(int blockNo, Block block) {
        this.blocks.put(blockNo, block);
    }

    // build the airline seat blocks with the input array
    public void build(int[][] input) {
        int noOfBlocks = input.length;
        for (int blockNo = 0; blockNo < input.length; blockNo++) {
            int columns = input[blockNo][0];
            int rows = input[blockNo][1];
            Block block = new Block(rows, columns);
            List<Seat> seats = new ArrayList<>();
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    SeatType seatType = getSeatType(noOfBlocks, blockNo, columns, column);
                    seats.add(new Seat(row, seatType));
                }
            }
            block.setSeats(seats);
            this.addBlock(blockNo, block);
        }
    }

    // update the seating arrangements based on the given rules
    public boolean updateSeatingArrangement(int noOfPassengers) {
        if(noOfPassengers > getSeats().size()) {
            System.out.println("Number of passengers cannot be higher than number of seats on the airline");
            return false;
        }
        for (int i = 1; i <= noOfPassengers; i++) {
            Optional<Seat> seat = findNextAvailableSeat();
            if(seat.isPresent()) {
                seat.get().setPassengerNo(i);
                seat.get().setAvailable(false);
            }
        }
        return true;
    }

    // get the maximum number of rows in the airline (considering all the blocks)
    public int getMaxRowInAirline() {
        return this.blocks.values().stream().mapToInt(Block::getNoOfRows).max().orElse(0);
    }

    // identify the seat type
    private SeatType getSeatType(int noOfBlocks, int currentBlock, int noOfColumns, int column) {
        SeatType seatType = SeatType.MIDDLE;
        if ((currentBlock == 0 && column == 0) || (currentBlock == noOfBlocks - 1 && column == noOfColumns - 1)) {
            seatType = SeatType.WINDOW;
        }
        if ((column == 0 || column == noOfColumns - 1) && !seatType.equals(SeatType.WINDOW)) {
            seatType = SeatType.AISLE;
        }
        return seatType;
    }

    // get all seats from the airline, sorted by row number
    private List<Seat> getSeats() {
        return this.blocks.values()
                .stream()
                .map(Block::getSeats)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Seat::getRowNumber))
                .collect(Collectors.toList());
    }

    // find the next available set based on the filling algorithm
    // seats have to be filled starting from left to right
    // aisle should be followed by window seats and then middle seats (middle seats in no specific order)
    private Optional<Seat> findNextAvailableSeat() {
        Optional<Seat> availableSeat = findNextAvailableSeat(Seat::isAisle);
        if(availableSeat.isEmpty()) {
            availableSeat = findNextAvailableSeat(Seat::isWindow);
        }
        if(availableSeat.isEmpty()) {
            availableSeat = findNextAvailableSeat(Seat::isMiddle);
        }
        return availableSeat;
    }

    // find the available seat based on seat type and availability
    private Optional<Seat> findNextAvailableSeat(Predicate<Seat> isMatchingType) {
        return getSeats().stream()
                .filter(isMatchingType)
                .filter(Seat::isAvailable)
                .findFirst();
    }
}
