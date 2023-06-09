import java.io.File;

public class Main {
    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "\\users\\User\\Desktop\\src";
        String dstFolder = "\\users\\User\\Desktop\\dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int oneThird = files.length / 3; // 2
        int firstPart = oneThird / 2; // 1

        int cores = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < cores; i++) {
            File[] coreFiles = new File[firstPart];
            System.arraycopy(files, (firstPart * i), coreFiles, (0), firstPart);
            ImageResizer resizer = new ImageResizer(coreFiles, newWidth, dstFolder, start);
            new Thread(resizer).start();
        }

    }
}
