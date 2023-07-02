package fr.edminecoreteam.edlogin.utils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.edminecoreteam.edlogin.Main;
import fr.edminecoreteam.edlogin.mysql.MySQL;
import net.md_5.bungee.api.ProxyServer;

public class PingServers  
{
	
	@SuppressWarnings("deprecation")
	public String getAServerLoginResponse() 
	{
		String response;
		for(int i = 1; i < Main.srvNumber + 1; i++)
		{
			if (i == 0) { return null; }
			int ServerNumber = i;
			response = "Login" + ServerNumber;
		    if (getServerStatut("localhost", ProxyServer.getInstance().getServerInfo(response).getAddress().getPort()) == true)
		    {
		    	return response;
		    }
		}
		return "";
	}
	
	@SuppressWarnings("deprecation")
	public boolean getAServerLoginBooleanResponse() 
	{
		String response;
		for(int i = 1; i < Main.srvNumber + 1; i++)
		{
			if (i == 0) { return false; }
			int ServerNumber = i;
			response = "Login" + ServerNumber;
		    if (getServerStatut("localhost", ProxyServer.getInstance().getServerInfo(response).getAddress().getPort()) == true)
		    {
		    	return true;
		    }
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public String getAServerLobbyResponse() 
	{
		String response;
		for(int i = 1; i < Main.srvNumber + 1; i++)
		{
			if (i == 0) { return null; }
			int ServerNumber = i;
			response = "Lobby" + ServerNumber;
		    if (getServerStatut("localhost", ProxyServer.getInstance().getServerInfo(response).getAddress().getPort()) == true)
		    {
		    	return response;
		    }
		}
		return "";
	}
	
	
	@SuppressWarnings("deprecation")
	public boolean getAServerLobbyBooleanResponse() 
	{
		String response;
		for(int i = 1; i < Main.srvNumber + 1; i++)
		{
			if (i == 0) { return false; }
			int ServerNumber = i;
			response = "Lobby" + ServerNumber;
		    if (getServerStatut("localhost", ProxyServer.getInstance().getServerInfo(response).getAddress().getPort()) == true)
		    {
		    	return true;
		    }
		}
		return false;
	}
	
	public int getServerPerGroup() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT server_id FROM ed_servers WHERE server_name = ?");
            preparedStatement.setString(1, "ProxyNetwork");
            int result = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
            	result = rs.getInt("server_id");
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
	
	@SuppressWarnings({ "deprecation", "unused" })
	private static boolean ping(String serverName)
	{
		int getPort = ProxyServer.getInstance().getServerInfo(serverName).getAddress().getPort();
		boolean online = getServerStatut("localhost", getPort);
		if (online == true)
		{
			return true;
		}
		else if (online == false)
		{
			return false;
		}
		return false;
	}
	
	public static boolean getServerStatut(String IPAdress, int port)
	{
		boolean online = false;
		try 
		{
			Socket s = new Socket(IPAdress, port);
			// ONLINE
			s.close();
			online = true;
			return online;
		} 
		catch (UnknownHostException ex) 
		{
			ex.toString();
		} 
		catch (IOException et) 
		{
			et.toString();
		}
		return online;
	}
}
