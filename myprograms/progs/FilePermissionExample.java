import java.io.File;

public class FilePermissionExample {
    public static void main(String[] args) {

        File file = new File("example.txt");

        // Set permissions
        file.setReadable(true);
        file.setWritable(false);
        file.setExecutable(false);

        System.out.println("Readable: " + file.canRead());
        System.out.println("Writable: " + file.canWrite());
    }
}