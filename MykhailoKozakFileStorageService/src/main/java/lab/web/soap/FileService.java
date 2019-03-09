package lab.web.soap;

import lab.model.File;
import lab.web.soap.exeption.ServiceException;

import javax.jws.WebService;
import java.util.List;

@WebService
interface FileService {

    boolean writeFile(String name) throws ServiceException;

    boolean downloadFile(String name, String path) throws ServiceException;

    List<String> getListAllFiles();

    boolean deleteFile(String name) throws ServiceException;
}
