package fr.edminecoreteam.edlogin.mysql;

import java.sql.SQLException;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class MySQL 
{
	private String urlBase;
    private String host;
    private String database;
    private String userName;
    private String password;
    private static Connection connection;
    
    public MySQL(String urlBase, String host, String database, String userName, String password) {
        this.urlBase = urlBase;
        this.host = host;
        this.database = database;
        this.userName = userName;
        this.password = password;
        this.connexion();
    }
    
    public static Connection getConnection() {
        return connection;
    }
    
    public void connexion() {
        if (!isOnline()) {
            try {
            	connection = DriverManager.getConnection(String.valueOf(this.urlBase) + this.host + "/" + this.database, this.userName, this.password);
            }
            catch (SQLException e) {
                e.printStackTrace();
                System.out.println("§cErreur de connexion a la base de donnée...");
            }
        }
    }
    
    public void deconnexion() {
        if (isOnline()) {
            try {
            	connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean isOnline() {
        try {
            return connection != null && !connection.isClosed();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void creatingTableLogin() {
        try {
        	PreparedStatement stm = MySQL.connection.prepareStatement("CREATE TABLE IF NOT EXISTS ed_login (`player_name` varchar(255) NOT NULL, `player_uuid` varchar(255), `player_password` varchar(255), `lastIP` varchar(255), `lastAuth` varchar(255), `isPremium` varchar(255), `isOnline` int(11), PRIMARY KEY (`player_name`), UNIQUE(`player_name`), INDEX(`player_name`)) CHARACTER SET utf8");
            stm.execute();
            stm.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    } 
}
