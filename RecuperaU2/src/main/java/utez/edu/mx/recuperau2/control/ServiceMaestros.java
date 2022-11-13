package utez.edu.mx.recuperau2.control;

import utez.edu.mx.recuperau2.model.BeanTeacher;
import utez.edu.mx.recuperau2.model.DaoTeacher;
import utez.edu.mx.recuperau2.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/prof")
public class ServiceMaestros {
    Map<String, Object> response = new HashMap<>();

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanTeacher> getAll(){
        return new DaoTeacher().findAll();
    }
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> save (BeanTeacher teacher){
        Response<BeanTeacher> result = new  DaoTeacher().save(teacher);
        response.put("result", result);
        return response;
    }
    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> update (BeanTeacher teacher){
        System.out.println(teacher.getName());
        Response<BeanTeacher> result = new  DaoTeacher().update(teacher);
        response.put("result", result);
        return response;
    }

}
