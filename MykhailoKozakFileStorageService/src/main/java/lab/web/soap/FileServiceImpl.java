package lab.web.soap;

import lab.bo.FileBO;
import lab.web.fault.FaultMessage;
import lab.web.fault.ServiceFaultInfo;
import lab.web.soap.exeption.ServiceException;
import org.apache.log4j.Logger;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "lab.web.soap.FileService")
public class FileServiceImpl implements FileService {
    private Logger LOGGER = Logger.getLogger(FileService.class);

    @Override
    public boolean writeFile(String name) throws ServiceException {
        LOGGER.info("Method write file");
        FileBO fileBO = new FileBO();
        if (!fileBO.writeFile(name)) {
            ServiceFaultInfo faultInfo = new ServiceFaultInfo(FaultMessage.SUCH_FILE_ALREADY_EXIST, name);
            LOGGER.warn(faultInfo.getMessage());
            throw new ServiceException(faultInfo);
        }

        LOGGER.info("Method write file result:" + true);
        return true;
    }

    @Override
    public boolean downloadFile(String name, String path) throws ServiceException {
        LOGGER.info("Method download file");

        FileBO fileBO = new FileBO();
        if (!fileBO.downloadFile(name, path)) {
            ServiceFaultInfo faultInfo = new ServiceFaultInfo(FaultMessage.NO_FILE_WITH_NAME, name);

            LOGGER.warn(faultInfo.getMessage());
            throw new ServiceException(faultInfo);
        }

        LOGGER.info("Method download file result:" + true);
        return true;
    }

    @Override
    public List<String> getListAllFiles() {
        LOGGER.info("Method get list all files");
        FileBO fileBO = new FileBO();
        return fileBO.getListAllFiles();
    }

    @Override
    public boolean deleteFile(String name) throws ServiceException {
        LOGGER.info("Method delete file");

        FileBO fileBO = new FileBO();
        if (!fileBO.deleteFile(name)) {
            ServiceFaultInfo faultInfo = new ServiceFaultInfo(FaultMessage.NO_FILE_WITH_NAME, name);

            LOGGER.warn(faultInfo.getMessage());
            throw new ServiceException(faultInfo);
        }

        LOGGER.info("Method delete file result:" + true);
        return true;
    }
}
