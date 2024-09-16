package fr.edminecoreteam.edlogin.cache;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.edminecoreteam.edlogin.mysql.MySQL;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class AccountData 
{
	private ProxiedPlayer player;
	
    public AccountData(ProxiedPlayer player)
    {
        this.player = player;
    }
    	
    
    public void createAccount() 
    {
        if (!hasaccount()) 
        {
            try 
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO ed_login (player_uuid, player_password, lastIP, lastAuth, isOnline) VALUES (?, ?, ?, ?, ?)");
                preparedStatement.setString(1, player.getUniqueId().toString());
                preparedStatement.setString(2, null);
                preparedStatement.setString(3, null);
                preparedStatement.setString(4, null);
                preparedStatement.setString(5, null);
                preparedStatement.execute();
                preparedStatement.close();
            }
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
    public boolean hasaccount() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT lastAuth FROM ed_login WHERE player_uuid = ?");
            preparedStatement.setString(1, player.getUniqueId().toString());
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public String hasCompleteAccount() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_password FROM ed_login WHERE player_uuid = ?");
            preparedStatement.setString(1, player.getUniqueId().toString());
            ResultSet rs = preparedStatement.executeQuery();
            String result = "";
            while (rs.next()) {
            	result = rs.getString("player_password");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return "";
        }
    }
    
    public void createPassword(String password) {
    	try {
            final PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_login SET player_password = ? WHERE player_uuid = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, player.getUniqueId().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
    
    public String getPassword() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_password FROM ed_login WHERE player_uuid = ?");
            preparedStatement.setString(1, player.getUniqueId().toString());
            ResultSet rs = preparedStatement.executeQuery();
            String result = "";
            while (rs.next()) {
            	result = rs.getString("player_password");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return "";
        }
    }
    
    public void updatePlayerName() {
    	try {
            final PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_accounts SET player_name = ? WHERE player_uuid = ?");
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getUniqueId().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}

    public String getDbName()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_name FROM ed_accounts WHERE player_uuid = ?");
            preparedStatement.setString(1, player.getUniqueId().toString());
            ResultSet rs = preparedStatement.executeQuery();
            String result = "";
            while (rs.next()) {
                result = rs.getString("player_name");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }
    
    public void setLastIP(String IPAdress) {
    	try {
            final PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_login SET lastIP = ? WHERE player_uuid = ?");
            preparedStatement.setString(1, IPAdress);
            preparedStatement.setString(2, player.getUniqueId().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
    
    public String getLastIP() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT lastIP FROM ed_login WHERE player_uuid = ?");
            preparedStatement.setString(1, player.getUniqueId().toString());
            ResultSet rs = preparedStatement.executeQuery();
            String result = "";
            while (rs.next()) {
            	result = rs.getString("lastIP");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return "";
        }
    }
    
    public void setStatut(int IPAdress) {
    	try {
            final PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_login SET isOnline = ? WHERE player_uuid = ?");
            preparedStatement.setInt(1, IPAdress);
            preparedStatement.setString(2, player.getUniqueId().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
    
    public int getStatut() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT isOnline FROM ed_login WHERE player_uuid = ?");
            preparedStatement.setString(1, player.getUniqueId().toString());
            ResultSet rs = preparedStatement.executeQuery();
            int result = 0;
            while (rs.next()) {
            	result = rs.getInt("isOnline");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return 0;
        }
    }
    
    public void setLastAuth(String date) {
    	try {
            final PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_login SET lastAuth = ? WHERE player_uuid = ?");
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, player.getUniqueId().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
    
    public String getLastAuth() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT lastAuth FROM ed_login WHERE player_uuid = ?");
            preparedStatement.setString(1, player.getUniqueId().toString());
            ResultSet rs = preparedStatement.executeQuery();
            String result = "";
            while (rs.next()) {
            	result = rs.getString("lastAuth");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return "";
        }
    }
}
