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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;
import visits.schedule.database.ScheduleVisit;

/**
 * REST Web Service
 *
 * @author manee
 */
@Path("scheduleVisit")
public class ScheduleVisitResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ScheduleVisitResource
     */
    public ScheduleVisitResource() {
    }

    /**
     * PUT method for updating or creating an instance of TechniciansResource
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("scheduleTechnicianVisit")
    public Response addVisitSchedule(final String inputJson) {
        
        try {
            System.out.println(inputJson.toString());
            ScheduleVisit addScheduleVisit = new ScheduleVisit();
            try {
                if(addScheduleVisit.inserVisitSchedule(new JSONObject(inputJson)))
                    return Response.status(200).entity(new JSONObject(new String("{\"success\":\"Successfully inserted visit schedule\"}"))).build();
            } catch (SQLException ex) {
                Logger.getLogger(ScheduleVisitResource.class.getName()).log(Level.SEVERE, "Here", ex);
            }
            
        } catch (JSONException ex) {
            Logger.getLogger(TechniciansResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
