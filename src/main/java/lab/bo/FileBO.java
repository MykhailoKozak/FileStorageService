package lab.bo;

import lab.dao.FileDAO;
import lab.model.File;

import java.util.ArrayList;
import java.util.List;

public class FileBO {

    public boolean writeFile(File file) {
        return FileDAO.writeFileOnServer(file);
    }

    public boolean downloadFile(String name) {
        return FileDAO.downloadFileFromServer(name);
    }

    public List<File> getListAllFiles() {
        return FileDAO.findAllFiles();
    }

    public boolean deleteFile(String name) {
        return FileDAO.deleteFileFromServer(name);
    }

}
