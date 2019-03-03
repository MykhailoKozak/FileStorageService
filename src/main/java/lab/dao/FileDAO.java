package lab.dao;

import lab.model.File;
import lab.utills.CSVFileManager;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class FileDAO {
    public static final String FILE_URl = "src/main/resources/files.csv";
    public static final String PATH_FILE = "src/test/java/myResources";
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

    public static boolean createFileOnServer(String name) {
        LOGGER.info("Method write file on server");
        java.io.File myfile = null;
        boolean isAdded = false;
        long size;

        try {
            myfile = new java.io.File(PATH_FILE, name);
            if (!myfile.exists()) {
                isAdded = myfile.createNewFile();
                LOGGER.info("File created: " + isAdded);

                size = myfile.length();
                if (size <= 1048576) {
                    LOGGER.info("File is less than 1MB " + size);
                } else {
                    LOGGER.warn("File is more than 1MB " + size);
                    isAdded = false;
                }
            } else {
                LOGGER.warn("File is already create");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    public static void allFile() {
        LOGGER.info("Method all file");
        java.io.File[] paths;
        java.io.File myfile = new java.io.File(PATH_FILE);

        paths = myfile.listFiles();

        if (paths.length != 0) {
            LOGGER.info("List of all file: ");
            for (java.io.File path : paths) {
                LOGGER.info("File: " + path);
            }
        } else {
            LOGGER.warn("Server doesn't have file: ");
        }
    }

    public static boolean downloadFile(String name, String newPath){
        LOGGER.info("Method downloading file from server");
        boolean isDownload = false;

        java.io.File myfile = null;
        java.io.File copyfile = null;

        try {
            myfile = new java.io.File(PATH_FILE, name);
            copyfile = new java.io.File(newPath, name);
            if (myfile.exists()) {
                LOGGER.info("File is exists");
                Files.copy(myfile.toPath(), copyfile.toPath());
                isDownload = true;
                LOGGER.info("File is download");
            } else {
                LOGGER.warn("File isn't exists");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isDownload;
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

    public static boolean deleteFile(String name) {
        LOGGER.info("Method deleting file from server");
        boolean deleted = false;

        java.io.File myfile = null;

        try {
            myfile = new java.io.File(PATH_FILE, name);
            if (myfile.exists()) {
                LOGGER.info("File is exists");
                deleted = myfile.delete();
                LOGGER.info("File is delete");
            } else {
                LOGGER.warn("File isn't exists");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return deleted;
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
