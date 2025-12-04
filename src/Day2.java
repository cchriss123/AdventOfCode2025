import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

    public static void main(String[] args) throws IOException {
//        String input = getTestInput();
      String input = Files.readString(Path.of("src/input02.txt"));
        List<String> inputLines = List.of(input.split(","));

        long totalNumbers = 0;


        List<List<String>> ranges = new ArrayList<>();

        for (String s : inputLines) {
            String[] parts = s.split("-");
            String start = parts[0];
            String end = parts[1];

            List<String> rangeList = new ArrayList<>();
            for (long i = Long.parseLong(start); i <= Long.parseLong(end); i++) {
                rangeList.add(String.valueOf(i));
            }
            ranges.add(rangeList);
        }



        for (List<String> range : ranges) {
            for(String numStr : range) {

                if (!isValid(numStr)) {
                    totalNumbers += Long.parseLong(numStr);
                }
            }
        }
        System.out.println("Total sum of invalid numbers: " + totalNumbers);

    }

    private static boolean isValid(String numStr) {
        if (numStr.length() % 2 != 0) {
            return true;
        }
        int len = numStr.length();
        int midIndex = len / 2;
        String firstHalf = numStr.substring(0, midIndex);
        String secondHalf = numStr.substring(midIndex , len);

        return !firstHalf.equals(secondHalf);

    }


    private static String getTestInput() {
        return "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,"
                + "1698522-1698528,446443-446449,38593856-38593862,565653-565659,"
                + "824824821-824824827,2121212118-2121212124";
    }
}








