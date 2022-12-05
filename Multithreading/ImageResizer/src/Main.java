import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "\\users\\User\\Desktop\\src";
        String dstFolder = "\\users\\User\\Desktop\\dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int oneThird = files.length / 3;
        int firstPart = oneThird / 2;
        int secondPart = oneThird - firstPart;


        File[] files1 = new File[firstPart];
        System.arraycopy(files, 0, files1, 0, firstPart);
        ImageResizer resizer1 = new ImageResizer(files1, newWidth, dstFolder, start);
        resizer1.start();

        File[] files2 = new File[secondPart];
        System.arraycopy(files, 0, files2, 0, secondPart);
        ImageResizer resizer2 = new ImageResizer(files2, newWidth, dstFolder, start);
        resizer2.start();

        File[] files3 = new File[firstPart];
        System.arraycopy(files, 0, files3, 0, firstPart);
        ImageResizer resizer3 = new ImageResizer(files3, newWidth, dstFolder, start);
        resizer3.start();

        File[] files4 = new File[secondPart];
        System.arraycopy(files, 0, files4, 0, secondPart);
        ImageResizer resizer4 = new ImageResizer(files4, newWidth, dstFolder, start);
        resizer4.start();

        File[] files5 = new File[firstPart];
        System.arraycopy(files, 0, files5, 0, firstPart);
        ImageResizer resizer5 = new ImageResizer(files5, newWidth, dstFolder, start);
        resizer5.start();

        File[] files6 = new File[secondPart];
        System.arraycopy(files, 0, files6, 0, secondPart);
        ImageResizer resizer6 = new ImageResizer(files6, newWidth, dstFolder, start);
        resizer6.start();

    }
}
