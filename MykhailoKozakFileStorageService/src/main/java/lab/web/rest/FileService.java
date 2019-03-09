package lab.web.rest;

import lab.web.soap.exeption.ServiceException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/fileREST")
public interface FileService {

    @GET
    @Path("/files")
    @Produces("application/json; charset=UTF-8")
    Response getListAllFiles();

    @POST
    @Path("/write")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response writeFile(@QueryParam("fileName") String name);

    @POST
    @Path("/download")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response downloadFile(@QueryParam("fileName") String name, @QueryParam("filePath") String path);

    @DELETE
    @Path("/delete/{fileName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response deleteFile(@PathParam("fileName") String name) throws ServiceException;
}