package fr.edminecoreteam.edlogin.cache;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.edminecoreteam.edlogin.mysql.MySQL;

public class AccountData 
{
	private String player;
	
    public AccountData(String player) 
    {
        this.player = player;
    }
    	
    
    public void createAccount() 
    {
        if (!hasaccount()) 
        {
            try 
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO ed_login (player_name, player_uuid, player_password, lastIP, lastAuth, isPremium, isOnline) VALUES (?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, player.toString());
                preparedStatement.setString(2, null);
                preparedStatement.setString(3, null);
                preparedStatement.setString(4, null);
                preparedStatement.setString(5, null);
                preparedStatement.setString(6, null);
                preparedStatement.setString(7, null);
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
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT lastAuth FROM ed_login WHERE player_name = ?");
            preparedStatement.setString(1, player.toString());
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
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_password FROM ed_login WHERE player_name = ?");
            preparedStatement.setString(1, player.toString());
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
            final PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_login SET player_password = ? WHERE player_name = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, player.toString());
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
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_password FROM ed_login WHERE player_name = ?");
            preparedStatement.setString(1, player.toString());
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
    
    public void setTypeOfAccount(String accountType) {
    	try {
            final PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_login SET isPremium = ? WHERE player_name = ?");
            preparedStatement.setString(1, accountType);
            preparedStatement.setString(2, player.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
    
    public String getTypeOfAccount() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT isPremium FROM ed_login WHERE player_name = ?");
            preparedStatement.setString(1, player.toString());
            ResultSet rs = preparedStatement.executeQuery();
            String result = "";
            while (rs.next()) {
            	result = rs.getString("isPremium");
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
    
    public void setUUID(String UUID) {
    	try {
            final PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_login SET player_uuid = ? WHERE player_name = ?");
            preparedStatement.setString(1, UUID);
            preparedStatement.setString(2, player.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
    
    public String getUUID() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_uuid FROM ed_login WHERE player_name = ?");
            preparedStatement.setString(1, player.toString());
            ResultSet rs = preparedStatement.executeQuery();
            String result = "";
            while (rs.next()) {
            	result = rs.getString("player_uuid");
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
            final PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_login SET lastIP = ? WHERE player_name = ?");
            preparedStatement.setString(1, IPAdress);
            preparedStatement.setString(2, player.toString());
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
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT lastIP FROM ed_login WHERE player_name = ?");
            preparedStatement.setString(1, player.toString());
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
            final PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_login SET isOnline = ? WHERE player_name = ?");
            preparedStatement.setInt(1, IPAdress);
            preparedStatement.setString(2, player.toString());
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
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT isOnline FROM ed_login WHERE player_name = ?");
            preparedStatement.setString(1, player.toString());
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
            final PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_login SET lastAuth = ? WHERE player_name = ?");
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, player.toString());
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
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT lastAuth FROM ed_login WHERE player_name = ?");
            preparedStatement.setString(1, player.toString());
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
