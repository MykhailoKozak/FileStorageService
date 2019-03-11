import lab.bo.FileBO;

public class Main {
    public static void main(String[] args) {
        FileBO fileBO = new FileBO();
        System.out.println(fileBO.downloadFile("Hello4", "src/main/resources"));
    }
}
