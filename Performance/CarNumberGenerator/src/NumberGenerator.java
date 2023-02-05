import java.io.PrintWriter;

public class NumberGenerator implements Runnable {

    private int fromNumber;
    private int toNumber;
    PrintWriter writer;
    private int regionCode;

    public NumberGenerator(int fromNumber, int toNumber, PrintWriter inputWriter, int inputRegionCode) {
        this.fromNumber = fromNumber;
        this.toNumber = toNumber;
        this.writer = inputWriter;
        this.regionCode = inputRegionCode;
    }

    char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    StringBuilder builder = new StringBuilder();

    @Override
    public void run() {

        for (int number = fromNumber; number <= toNumber; number++) {
            for (char firstLetter : letters) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        builder.append(firstLetter);
                        builder.append(padNumber(number, 3));
                        builder.append(secondLetter);
                        builder.append(thirdLetter);
                        builder.append(padNumber(regionCode, 2));
                        builder.append("\n");
                    }
                }
            }
        }
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        StringBuilder builder = new StringBuilder();

            builder.append("0".repeat(Math.max(0, padSize)));
            builder.append(numberStr);

        return builder.toString();
    }
}
