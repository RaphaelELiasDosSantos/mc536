package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import application.Constants;
 

/**
 * Class that provide connection to data base.
 * @author André Pinheiro Borba
 */
public class ConnectionFactory {
	 private static String driverClassName;
	 private static String connectionUrl;
	 private static String dbUser;
	 private static String dbPwd;

     private static ConnectionFactory connectionFactory = null;
     private Connection conn = null;
     
     public static ConnectionFactory getInstance() {
         if (connectionFactory == null) {                 
        	 connectionFactory = new ConnectionFactory();
                 
         }
         return connectionFactory;
     }

     private ConnectionFactory() {
         try {
        	 loadConfigFile();
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
     
     /**
      * Recovery informations about Data Base connection.
      */
     private static void loadConfigFile(){
    	 Properties prop = new Properties();
    	 
    	 try{
    		 prop.load(new FileInputStream(Constants.CONFIG_FILE_PATH));
    		 		 
    		 driverClassName = prop.getProperty("driverClassName");
    		 connectionUrl = prop.getProperty("connectionUrl");
    		 dbUser = prop.getProperty("dbUser");
    		 dbPwd = prop.getProperty("dbPwd");
    	 }
    	 catch(IOException e){
    		 
    	 }
    	 
     }
}
