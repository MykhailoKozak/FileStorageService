package lab.web.soap;

import lab.model.File;
import lab.web.soap.exeption.ServiceException;

import javax.jws.WebService;
import java.util.List;

@WebService
interface FileService {

    boolean writeFile(File file) throws ServiceException;

    boolean downloadFile(String name) throws ServiceException;

    List<File> getListAllFiles();

    boolean deleteFile(String name) throws ServiceException;
}
