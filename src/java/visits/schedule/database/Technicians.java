package visits.schedule.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author manee
 */
public class Technicians extends DBConnection {

    String name;
    String email;
    
    private final String ERROR_JSON = new String("{\"Error\":\"NO DATA AVAILABLE\"}");

    JSONObject jsonData = null;

    public JSONObject getAll(String visitDate) throws JSONException, SQLException {
        try {

            jsonData = new JSONObject();
            ResultSet rs = super.runQuery("SELECT * from technicians");
            

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                
                jsonData.append("id", id);
                jsonData.append("name", name);
                jsonData.append("email", email);
            }
            super.cleanUp();
            System.out.println("visitDate" + visitDate);
            rs = super.runQuery("SELECT * FROM `visits_schedule` "
                    + "WHERE start_date = '"+visitDate+"' ");
            while (rs.next()) {
                int technician_id = rs.getInt("technician_id");
                int percentage = rs.getInt("percentage");
                
                jsonData.append("technician_id", technician_id);
                jsonData.append("percentage", percentage);
                
                //Display values
                System.out.println("technician_id: " + technician_id);
                System.out.println("percentage: " + percentage);
            }
            
            super.cleanUp();
            return jsonData;
            
        } catch (Exception ex) {
            Logger.getLogger(Technicians.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        super.cleanUp();
        return new JSONObject(ERROR_JSON);
    }

}
