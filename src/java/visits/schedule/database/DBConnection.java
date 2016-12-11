/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visits.schedule.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import visits.schedule.api.TechniciansResource;

/**
 *
 * @author manee
 */
public class DBConnection {

    private final String DATABSE = "visits_scheduler";
    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String DATABASE_USER = "demo";
    private final String DATABASE_PASSWORD = "demo";
    
    protected Connection connection = null;
    protected Statement stmt = null;
    protected ResultSet rs = null;
     
    
    protected Connection getConnection() throws SQLException, Exception {
        try {
            String connectionURL = "jdbc:mysql://"+ HOST +":" + PORT + "/" + DATABSE;
            connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, DATABASE_USER, DATABASE_PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    protected ResultSet runQuery(String query) throws Exception {
        try {
            
            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(query);
            return rs;
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    protected boolean insert(String query) throws SQLException {
        try {
            
            stmt = getConnection().createStatement();
            stmt.executeUpdate(query);
            // cleanUp();
            return true;
            
        } catch (Exception e) {
            Logger.getLogger(TechniciansResource.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    protected void cleanUp() throws SQLException {
        connection.close();
        stmt.close();
        rs.close();
    }
}
