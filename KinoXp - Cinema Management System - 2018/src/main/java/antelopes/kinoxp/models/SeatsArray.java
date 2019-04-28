package antelopes.kinoxp.models;

import java.util.LinkedList;
import java.util.List;

public class SeatsArray {
    private static List<String> seats = new LinkedList<>();
    private static SeatsArray seatsArray = new SeatsArray();

    private SeatsArray(){
        for(int i = 1; i <= 6; i++){
            for(int y = 65; y<= 75; y++){
                StringBuilder str = new StringBuilder();
                str.append(String.valueOf(i) + (char)y);
                seats.add(str.toString());
            }
        }
    }

    public static SeatsArray getInstance(){
        return seatsArray;
    }

    public static List<String> getSeats() {
        return seats;
    }
}
