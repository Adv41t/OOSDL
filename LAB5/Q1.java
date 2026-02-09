import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class Q1 {
    public static void main(String[] args) {

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream("C:\\Users\\MCA\\Desktop\\240962600\\WEEK5\\source.dat");
            fos = new FileOutputStream("destination.dat");

            int byteData;
            while ((byteData = fis.read()) != -1) {
                fos.write(byteData);
            }

            System.out.println("File copied successfully using byte streams.");

        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            try {
                if (fis != null)
                    fis.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                System.out.println("Error closing files.");
            }
        }
    }
}
