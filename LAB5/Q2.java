import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Q2 {
    public static void main(String[] args) {

        FileReader fr = null;
        FileWriter fw = null;

        try {
            fr = new FileReader("source.txt");
            fw = new FileWriter("destination.txt");

            int charData;
            while ((charData = fr.read()) != -1) {

                fw.write(charData);
            }

            System.out.println("Text file copied successfully using character streams.");

        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            try {
                if (fr != null)
                    fr.close();
                if (fw != null)
                    fw.close();
            } catch (IOException e) {
                System.out.println("Error closing files.");
            }
        }
    }
}
