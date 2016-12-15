
package visits.schedule.api;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import visits.schedule.database.Technicians;

/**
 * REST Web Service
 *
 * @author manee
 */
@Path("technicians")
public class TechniciansResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GetTechniciansResource
     */
    public TechniciansResource() {
    }

    /**
     * Retrieves technicians data and work load 
     * on selected data (Clicked data by user)
     * @return Object Response
     */
    @POST
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getXml(@FormParam("visitDate") String visitDate) {
        try {
            //TODO return proper representation object
            return Response.status(200).entity(new Technicians().getAll(visitDate).toString()).build();
        } catch (JSONException ex) {
            Logger.getLogger(TechniciansResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TechniciansResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.status(422).entity(new String(new String(" {\"Error\":\"Erorr Ocured\"}"))).build();
    }
}
