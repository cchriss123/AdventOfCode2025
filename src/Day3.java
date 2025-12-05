import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws IOException {
//        List<String> input = getTestInput();
        List<String> input = Files.readAllLines(Path.of("src/input03.txt"));
        System.out.println(partOne(input));
        System.out.println(partTwo(input));
    }

    private static int partOne(List<String> input) {
        int result = 0;

        for (String s : input) {
            int highestNumberLeft = -1;
            int highestNumberLeftIndex = -1;
            int highestNumberRight = -1;

            for (int i = 0; i < s.length() - 1; i++) {
                int currentNumber = Character.getNumericValue(s.charAt(i));
                if (currentNumber > highestNumberLeft) {
                    highestNumberLeft = currentNumber;
                    highestNumberLeftIndex = i;

                }
            }
            for (int i = highestNumberLeftIndex + 1; i < s.length(); i++) {
                int currentNumber = Character.getNumericValue(s.charAt(i));
                if (currentNumber > highestNumberRight) {
                    highestNumberRight = currentNumber;
                }
            }

            String builtString = "" + highestNumberLeft + highestNumberRight;
            result += Integer.parseInt(builtString);
        }
        return result;
    }

    private static long partTwo(List<String> input) {
        long result = 0;

        for (String s : input) {

            List<ValueIndex> digitsAndIndexes = new ArrayList<>();
            int latestHighest = -1;
            int latestHighestIndex = -1;
            int digitsLeftToPick = 12;

            while (digitsLeftToPick > 0) {

                int startIndex = digitsAndIndexes.isEmpty()
                        ? 0
                        : digitsAndIndexes.getLast().index + 1;

                for (int i = startIndex; i < s.length() - (digitsLeftToPick-1); i++) {

                    int currentNumber = Character.getNumericValue(s.charAt(i));
                    if (currentNumber > latestHighest) {
                        latestHighest = currentNumber;
                        latestHighestIndex = i;
                    }
                }
                digitsAndIndexes.add(new ValueIndex(latestHighest, latestHighestIndex));
                digitsLeftToPick--;
                latestHighest = -1;
                latestHighestIndex = -1;
            }

            StringBuilder builtString = new StringBuilder();
            for (ValueIndex vi : digitsAndIndexes) {
                builtString.append(vi.value);
            }
            result += Long.parseLong(builtString.toString());

        }
        return result;
    }

    public record ValueIndex(long value, int index) {}




    private static List<String> getTestInput() {
        return List.of(
                "987654321111111",
                "811111111111119",
                "234234234234278",
                "818181911112111"
        );
    }



}


//987654321111111
//        811111111111119
//        234234234234278
//        818181911112111








