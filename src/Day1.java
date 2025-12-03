import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day1 {
    public static void main(String[] args) throws IOException {

//        List<String> input = Files.readAllLines(Path.of("src/input01.txt"));
         List<String> input = getTestInput();
        System.out.println("Day 1 Password: " + partOne(input));
        System.out.println("Day 2 Password: " + partTwo(input));
        
    }

    private static int partOne(List<String> input) {
        int password = 0;
        int dial = 50;
        final int SIZE = 100;

        for (String s : input) {
            char direction = s.charAt(0);
            int number = Integer.parseInt(s.substring(1));
            if (direction == 'L') dial = dial - number;
            else dial = dial + number;


            if (dial > SIZE) {
                dial = dial % SIZE;

            } else if (dial < 0){
                dial = (dial % SIZE) + SIZE;
            }


            if (dial == 100) dial = 0;
            if (dial == 0) password++;
        }
        return password;
    }

    private static int partTwo(List<String> input) {
        final int SIZE = 100;

        int password = 0;
        int rawDial = 50;

        for (String s : input) {
            char direction = s.charAt(0);
            int number = Integer.parseInt(s.substring(1));

            int start = rawDial;

            int end;
            if (direction == 'L') {
                end = start - number;
            } else {
                end = start + number;
            }

            int hits;

            if (direction == 'R') {
                hits = Math.floorDiv(end, SIZE) - Math.floorDiv(start, SIZE);
            } else {
                hits = Math.floorDiv(start - 1, SIZE) - Math.floorDiv(end - 1, SIZE);
            }

            password += hits;
            rawDial = end;
        }

        return password;
    }



    private static List<String> getTestInput() {
        return List.of(
                "L68",
                "L30",
                "R48",
                "L5",
                "R60",
                "L55",
                "L1",
                "L99",
                "R14",
                "L82"
        );
    }
}