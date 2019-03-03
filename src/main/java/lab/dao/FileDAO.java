package lab.dao;

import lab.model.File;
import lab.utills.CSVFileManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class FileDAO {
    public static final String FILE_URl = "src/main/resources/files.csv";
    private static Logger LOGGER = Logger.getLogger(FileDAO.class);

    public static boolean writeFileOnServer(File file) {
        LOGGER.info("Method write file on server");
        List<File> fileList = CSVFileManager.readFile(new java.io.File(FILE_URl));
        boolean isAdded;

        if (!fileList.contains(file)) {
            fileList.add(file);
            CSVFileManager.writeFiles(fileList, new java.io.File(FILE_URl));
            isAdded = true;
        } else {
            isAdded = false;
        }

        return isAdded;
    }

    public static boolean downloadFileFromServer(String name) {
        LOGGER.info("Method download file from server");
        boolean isDownload = false;
        if (!Objects.isNull(findFileByName(name))) {
            isDownload = true;
        }
        return isDownload;
    }

    public static List<File> findAllFiles() {
        LOGGER.info("Method find all files from server");
        return CSVFileManager.readFile(new java.io.File(FILE_URl));
    }

    public static boolean deleteFileFromServer(String name) {
        LOGGER.info("Method deleting file from server");
        List<File> fileList = CSVFileManager.readFile(new java.io.File(FILE_URl));
        ListIterator<File> it = fileList.listIterator();
        boolean deleted = false;

        if (!Objects.isNull(findFileByName(name))) {
            while (it.hasNext()) {
                File file = it.next();
                if (file.getName().equals(name)) {
                    it.remove();
                    CSVFileManager.writeFiles(fileList, new java.io.File(FILE_URl));
                    deleted = true;
                    break;
                }
            }
        }

        return deleted;
    }

    public static File findFileByName(String name) {
        LOGGER.info("findByName method");
        List<File> bookList = CSVFileManager.readFile(new java.io.File(FILE_URl));
        File myFile = null;

        for (File file : bookList) {
            if (file.getName().equals(name)) {
                myFile = file;
                break;
            }
        }
        return myFile;
    }

}
