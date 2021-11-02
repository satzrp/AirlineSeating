package com.example.airline;

import com.example.airline.domain.Airline;
import com.example.airline.infra.console.Input;
import com.example.airline.infra.console.Output;

public class AirlineSeating {
    public static void main(String[] args) {
        AirlineSeating as = new AirlineSeating();
        as.runApp();
    }
    public void runApp() {
        // sample input: [[3, 2], [4, 3], [2, 3], [3, 4]]
        // Example: int[][] input = new int[][]{{3,2}, {4,3}, {2,3}, {3,4}};
        int[][] input = Input.getAirlineBlockDetails();
        Airline airline = new Airline();
        airline.build(input);

        int noOfPassengers = Input.getNoOfPassengers();
        boolean isUpdated = airline.updateSeatingArrangement(noOfPassengers);
        if (isUpdated) {
            Output.printSeatArrangement(airline);
        }
    }
}
