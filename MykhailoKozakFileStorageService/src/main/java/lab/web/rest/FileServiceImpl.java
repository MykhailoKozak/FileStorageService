package lab.web.rest;

import lab.bo.FileBO;
import lab.web.fault.FaultMessage;
import lab.web.fault.ServiceFaultInfo;
import lab.web.soap.exeption.ServiceException;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;

public class FileServiceImpl implements FileService {
    private Logger LOGGER = Logger.getLogger(FileServiceImpl.class);


    @Override
    public Response getListAllFiles() {
        LOGGER.info("Method get list all files");
        FileBO fileBO = new FileBO();
        String result = fileBO.getListAllFiles().toString();
        //return Response.ok().entity(result).build();
        return Response.status(201).entity(result).build();
    }

    @Override
    public Response writeFile(String name) {

        LOGGER.info("Method write file");
        Response response;
        FileBO fileBO = new FileBO();

        if (fileBO.writeFile(name)) {
            response = Response.status(201).build();
            LOGGER.info("Method write file result:" + true);
        } else {
            ServiceFaultInfo faultInfo = new ServiceFaultInfo(FaultMessage.SUCH_FILE_ALREADY_EXIST, name);
            LOGGER.warn(faultInfo.getMessage());

            response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(faultInfo).build();
        }

        return response;
    }

    @Override
    public Response downloadFile(String name, String path) {
        LOGGER.info("Method download file");
        Response response;
        FileBO fileBO = new FileBO();
        if (!fileBO.downloadFile(name, path)) {
            ServiceFaultInfo faultInfo = new ServiceFaultInfo(FaultMessage.NO_FILE_WITH_NAME, name);
            LOGGER.warn(faultInfo.getMessage());
            response = Response.status(Response.Status.NOT_FOUND).entity(faultInfo).build();
        } else {
            response = Response.status(201).build();
            LOGGER.info("Method download file result:" + true);
        }
        return response;
    }

    @Override
    public Response deleteFile(String name) throws ServiceException {
        LOGGER.info("Method delete file");
        Response response;
        FileBO fileBO = new FileBO();

        if (!fileBO.deleteFile(name)) {
            ServiceFaultInfo faultInfo = new ServiceFaultInfo(FaultMessage.NO_FILE_WITH_NAME, name);

            LOGGER.warn(faultInfo.getMessage());
            response = Response.status(Response.Status.NOT_FOUND).entity(faultInfo).build();
        } else {
            response = Response.status(201).build();
            LOGGER.info("Method delete file result:" + true);
        }
        return response;
    }

}
