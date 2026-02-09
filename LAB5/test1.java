import java.io.File;
import java.io.IOException;

public class test1 {
    public static void main(String args[]) {
        File newFile = new File("C:\\Users\\MCA\\Desktop\\240962600\\WEEK5", "myfile.txt");

        try {
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getAbsolutePath());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
        System.out.println(newFile.getAbsolutePath() + "\n" + newFile.getName() + "\n" + newFile.getParent() + "\n"
                + newFile.getPath());
        System.out.println(newFile.exists() + "\n" + newFile.canWrite() + "\n" + newFile.canRead() + "\n"
                + newFile.isDirectory() + "\n" + newFile.isFile() + "\n" + newFile.isAbsolute() + "\n"
                + newFile.lastModified() + "\n" + newFile.length());
        File f = new File("C\\Users\\MCA\\Desktop");
        if (f.mkdir()) {
            System.out.println("Directory is called");
        } else {
            System.out.println("Directory cannot be reached.");
        }
    }
}
// read and write - 4 tables + 2 lab questions