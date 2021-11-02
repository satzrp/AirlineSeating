package com.example.airline.infra.console;

import com.example.airline.domain.Airline;
import com.example.airline.domain.Block;
import com.example.airline.domain.Seat;

import java.util.List;
import java.util.stream.Collectors;

public class Output {
    private Output() {}
    public static void printSeatArrangement(Airline airline) {
        StringBuilder str = new StringBuilder();
        for (int row = 0; row < airline.getMaxRowInAirline(); row++) {
            str.append("| ");
            for (int blockNo = 0; blockNo < airline.getBlocks().size(); blockNo++) {
                Block block = airline.getBlocks().get(blockNo);
                str.append(getRowAsString(block, row));
                str.append(" | ");
            }
            str.append("\n");
        }
        System.out.println(str);
    }

    private static String getRowAsString(Block block, int row) {
        StringBuilder str = new StringBuilder();
        List<Seat> seatsInBlock = block.getSeatsInRow(row);
        if(seatsInBlock.isEmpty()) {
            str.append(" ".repeat((block.getNoOfColumns() * 3) + (block.getNoOfColumns() - 2)));
        } else {
            String blockRow = seatsInBlock.stream()
                    .map(Seat::getPassengerNo)
                    .map(passengerNo -> {
                        if (passengerNo == 0) return "--";
                        return "" + (passengerNo >= 10 ? passengerNo : " " + passengerNo)  + "";
                    }).collect(Collectors.joining(", "));
            str.append(blockRow);
        }
        return str.toString();
    }
}
