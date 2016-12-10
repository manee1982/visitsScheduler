
package visits.schedule.database;

import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author manee
 */
public class ScheduleVisit extends DBConnection {
    
    public boolean inserVisitSchedule(JSONObject jsonInputObj) throws JSONException, SQLException {
        
        System.out.println("jsonInputObj" + jsonInputObj.toString());
        String visitDate = jsonInputObj.getString("visitDate");
        String visitStartTime = jsonInputObj.getString("visitStartTime");
        String visitEndtime = jsonInputObj.getString("visitEndtime");
        String description = jsonInputObj.getString("description");
        int technician = Integer.parseInt(jsonInputObj.getString("technician"));
        int percentage = jsonInputObj.getInt("percentage");
        
        
        String query = "INSERT INTO `visits_schedule` (`id`, `technician_id`, `title`, "
                    + "`start_date`, `start_time`, `end_time`, `percentage`) "
                    + "VALUES (NULL, '"+technician+"', '"+description+"', '"+visitDate+"', "
                + "'"+visitStartTime+"', '"+visitEndtime+"', "+percentage+");";
        if(super.insert(query))
            return true;
        return false;
    }

}
