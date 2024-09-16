package fr.edminecoreteam.edlogin.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.md_5.bungee.api.connection.ProxiedPlayer;


public class SettingData 
{
	private ProxiedPlayer p;

	public SettingData(ProxiedPlayer p)
	{
		this.p = p;
	}
	
	public int getLang() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_lang FROM ed_settings WHERE player_uuid = ?");
            preparedStatement.setString(1, p.getUniqueId().toString());
            int result = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
            	result = rs.getInt("player_lang");
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
}
