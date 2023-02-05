import java.io.PrintWriter;

public class Loader {

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        PrintWriter writer = new PrintWriter("res/numbers.txt");
        PrintWriter writer2 = new PrintWriter("res/numbers2.txt");
        PrintWriter writer3 = new PrintWriter("res/numbers3.txt");
        PrintWriter writer4 = new PrintWriter("res/numbers4.txt");
        PrintWriter writer5 = new PrintWriter("res/numbers5.txt");

        for (int regionCode = 1; regionCode < 100; regionCode++) {

            new Thread(new NumberGenerator(1, 200, writer, regionCode)).start();
            new Thread(new NumberGenerator(201, 400, writer2, regionCode)).start();
            new Thread(new NumberGenerator(401, 600, writer3, regionCode)).start();
            new Thread(new NumberGenerator(601, 800, writer4, regionCode)).start();
            new Thread(new NumberGenerator(801, 1000, writer5, regionCode)).start();

        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

}
