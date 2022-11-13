package utez.edu.mx.recuperau2.control;

import utez.edu.mx.recuperau2.model.BeanStudent;
import utez.edu.mx.recuperau2.model.DaoStudent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api")
public class ServiceCal {
    Map<String, Object> response = new HashMap<>();
    @GET
    @Path("/qualification")
    @Produces(value = {"application/json"})
    public List<BeanStudent> getAll(){
        return new DaoStudent().findSC();
    }
}
