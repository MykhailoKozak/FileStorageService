package lab.dao;

import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {
    public static final String PATH_FILE = "src/main/resources/myResources";
    private static Logger LOGGER = Logger.getLogger(FileDAO.class);


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

    public static List<String> allFileOnServer() {
        LOGGER.info("Method all file");
        java.io.File[] paths;
        java.io.File myfile = new java.io.File(PATH_FILE);
        List<String> list = new ArrayList<>();

        paths = myfile.listFiles();

        if (paths.length != 0) {
            LOGGER.info("List of all file: ");
            for (java.io.File path : paths) {
                LOGGER.info("File: " + path);
                list.add(path.toString());
            }
        } else {
            LOGGER.warn("Server doesn't have file: ");
        }
        return list;
    }

    public static boolean downloadFileFromServer(String name, String newPath) {
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

    public static boolean deleteFileFromServer(String name) {
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

}
