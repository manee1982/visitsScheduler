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

/**
 *
 * @author manee
 */
public class DBConnection {

    private final String DATABSE = "visits_scheduler";
    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String DATABASE_USER = "manee";
    private final String DATABASE_PASSWORD = "!@#manee";
    
    protected Connection connection = null;
    protected Statement stmt = null;
    protected ResultSet rs;
     
    
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
    
    protected void cleanUp() throws SQLException {
        connection.close();
        stmt.close();
        rs.close();
    }
}
