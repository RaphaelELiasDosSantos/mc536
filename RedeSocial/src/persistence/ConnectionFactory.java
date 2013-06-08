package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author André Pinheiro Borba
 */
public class ConnectionFactory {
	 private String driverClassName = "com.mysql.jdbc.Driver";
	 private String connectionUrl = "jdbc:mysql://127.0.0.1:3306/aula3";
	 private String dbUser = "root";
	 private String dbPwd = "casa1020";

     private static ConnectionFactory connectionFactory = null;
     private Connection conn = null;

     private ConnectionFactory() {
         try {
              Class.forName(driverClassName);
         } catch (ClassNotFoundException e) {
              e.printStackTrace();              
         }
     }

     public Connection getConnection() {
        if(conn == null){
        	try {
				conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }              
        return conn;
     }
     
     public void closeConnection(){
    	 try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
     }

     public static ConnectionFactory getInstance() {
         if (connectionFactory == null) {
                 connectionFactory = new ConnectionFactory();
         }
         return connectionFactory;
     }
}
