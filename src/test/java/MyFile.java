import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MyFile {
    private static String pathFile = "src/test/java/myResources";
    private static String name = "Hello.txt";

    private static void downloadFile (File file, File fileNew) throws IOException {
        Files.copy(file.toPath(), fileNew.toPath());
    }

    public static void main(String[] args) {
        File file = null;
        boolean bool = false;
        long size;

        try {
            // create new file
            file = new File(pathFile, name);

            // tries to create new file in the system
            bool = file.createNewFile();
            System.out.println("File created: " + bool);


            size = file.length();
            if (size <= 1048576) {
                System.out.println("File is less than 1MB " + size);
            } else {
                System.out.println("File is more than 1MB " + size);
            }


//            // deletes file from the system
//            file.delete();
//            // delete() is invoked
//            System.out.println("delete() method is invoked");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
