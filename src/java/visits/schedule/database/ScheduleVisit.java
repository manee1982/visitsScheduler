
package visits.schedule.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author manee
 */
public class ScheduleVisit extends DBConnection {
    
    private final String ERROR_JSON = new String("{\"Error\":\"NO DATA AVAILABLE\"}");
    JSONArray jsonArr = null;
    JSONObject jsonData = null;
    
    public JSONArray getAll() throws JSONException, SQLException {
        try {

            ResultSet rs = super.runQuery("SELECT * from visits_schedule");
            jsonArr = new JSONArray();
            

            while (rs.next()) {
                jsonData = new JSONObject();
                int visitId = rs.getInt("id");
//                int technician_id = rs.getInt("technician_id");
                String title = rs.getString("title");
                String visitDate = rs.getString("start_date");
                String startTime = visitDate + "T" + rs.getString("start_time") + ":00";
                String endtTime = visitDate + "T" + rs.getString("end_time")  + ":00";
//                String percentage = rs.getString("percentage");

                // Display result
                System.out.println(endtTime);
                
//                jsonData.put("technician_id", technician_id);
                jsonData.put("title", title);
                jsonData.put("id", visitId);
//                jsonData.put("startDate", visitDate);
                jsonData.put("start", startTime);
                jsonData.put("end", endtTime);
//                jsonData.put("percentage", percentage);
                
                jsonArr.put(jsonData);
            }
            
            super.cleanUp();
            return jsonArr;
            
        } catch (Exception ex) {
            Logger.getLogger(Technicians.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        super.cleanUp();
        return new JSONArray(ERROR_JSON);
    }
    
    public boolean inserVisitSchedule(JSONObject jsonInputObj) throws JSONException, SQLException {
        
        System.out.println("jsonInputObj ==> " + jsonInputObj.toString());
        String visitDate = jsonInputObj.getString("visitDate");
        String visitStartTime = jsonInputObj.getString("visitStartTime").replaceAll("\\s","");
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
