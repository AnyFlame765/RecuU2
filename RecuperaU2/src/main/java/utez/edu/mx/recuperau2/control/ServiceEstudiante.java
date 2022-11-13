package utez.edu.mx.recuperau2.control;

import utez.edu.mx.recuperau2.model.BeanStudent;
import utez.edu.mx.recuperau2.model.DaoStudent;
import utez.edu.mx.recuperau2.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/estudiante")
public class ServiceEstudiante {
    Map<String, Object> response = new HashMap<>();
    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanStudent> getAll(){
        return new DaoStudent().findAll();
    }
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> save (BeanStudent student){
        Response<BeanStudent> result = new  DaoStudent().save(student);
        response.put("result", result);
        return response;
    }
    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> update (BeanStudent student){

        Response<BeanStudent> result = new  DaoStudent().update(student);
        response.put("result", result);
        return response;
    }
}
