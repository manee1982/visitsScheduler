/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visits.schedule.api;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
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
     * Retrieves representation of an instance of visits.schedule.api.TechniciansResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public String getXml() {
        try {
            //TODO return proper representation object
            return new Technicians().getAll().toString();
        } catch (JSONException ex) {
            Logger.getLogger(TechniciansResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TechniciansResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
