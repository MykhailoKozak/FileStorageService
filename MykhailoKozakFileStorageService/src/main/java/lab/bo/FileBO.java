package lab.bo;

import lab.dao.FileDAO;

import java.util.List;

public class FileBO {

    public boolean writeFile(String name) {
        return FileDAO.createFileOnServer(name);
    }

    public boolean downloadFile(String name, String path) {
        return FileDAO.downloadFileFromServer(name, path);
    }

    public List<String> getListAllFiles() {
        return FileDAO.allFileOnServer();
    }

    public boolean deleteFile(String name) {
        return FileDAO.deleteFileFromServer(name);
    }

}
