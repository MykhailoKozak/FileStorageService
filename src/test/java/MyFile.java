import lab.dao.FileDAO;

public class MyFile {

    public static void main(String[] args) {

        FileDAO.downloadFile("Hello3", "src/test/java");
        FileDAO.allFile();
    }
}
