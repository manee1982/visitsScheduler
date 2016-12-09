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

    public JSONObject getAll() throws JSONException, SQLException {
        try {

            ResultSet rs = super.runQuery("SELECT * from technicians");
            jsonData = new JSONObject();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                
                jsonData.append("id", id);
                jsonData.append("name", name);
                jsonData.append("email", email);
                
                //Display values
                System.out.println("name: " + name);
                System.out.println("email: " + email);
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
