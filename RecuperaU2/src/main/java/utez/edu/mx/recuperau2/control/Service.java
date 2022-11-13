package utez.edu.mx.recuperau2.control;

import utez.edu.mx.recuperau2.model.BeanStudent;
import utez.edu.mx.recuperau2.model.DaoStudent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/api")
public class Service {
    @GET
    @Path("/average")
    @Produces(value = {"application/json"})
    public List<BeanStudent> getAll(){
        return new DaoStudent().findAverage();
    }
}
