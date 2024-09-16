package fr.edminecoreteam.edlogin.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import fr.edminecoreteam.edlogin.Main;
import fr.edminecoreteam.edlogin.cache.AccountInfo;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.connection.InitialHandler;
import net.md_5.bungee.event.EventHandler;

public class Listeners implements Listener 
{
	private final Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{2,16}$");
	private final Main main;
	private final String kick_invalid_name;
	
	public Listeners(Main main, String invalid) {
	    this.main = main;
	    this.kick_invalid_name = ChatColor.translateAlternateColorCodes('&', invalid);
	}
	
	@EventHandler(priority = 64)
	public void onPreLogin(PreLoginEvent e) 
	{
	    if (e.isCancelled())
	    	return; 
	    if (e.getConnection().getName().length() > 16) 
	    {
	    	main.getLogger().info(e.getConnection().getName() + " a un pseudo trop long.");
	    	e.setCancelReason(TextComponent.fromLegacyText(kick_invalid_name));
	    	e.setCancelled(true);
	    	return;
	    } 
	    if (e.getConnection().getName().length() < 3) 
	    {
	    	main.getLogger().info(e.getConnection().getName() + " a un pseudo trop court.");
	    	e.setCancelReason(TextComponent.fromLegacyText(kick_invalid_name));
	    	e.setCancelled(true);
	    	return;
	    } 
	    if (!validate(e.getConnection().getName())) 
	    {
	    	main.getLogger().info("Le pseudo de " + e.getConnection().getName() + " a un charactère invalide.");
	    	e.setCancelReason(TextComponent.fromLegacyText(kick_invalid_name));
	    	e.setCancelled(true);
	    	return;
	    } 
	    PingServers ping = new PingServers();
	    new AccountInfo(e.getConnection().getName());
        AccountInfo accountInfo = new AccountInfo(e.getConnection().getName());
	    if (!accountInfo.hasAccount())
	    {
	    	ServerInfo target = ProxyServer.getInstance().getServerInfo(ping.getAServerLoginResponse());
	    	InitialHandler handler = (InitialHandler)e.getConnection();
	    	if (target == null) {
	    		e.setCancelled(true);
	    		e.setCancelReason(TextComponent.fromLegacyText(Messages.errorNotServer()));
	    		return;
	    	}
	    	handler.setOnlineMode(false);
	    }
	    if (accountInfo.hasAccount())
	    {
	    	if (accountInfo.hasCompleteAccount() == null)
	    	{
	    		ServerInfo target = ProxyServer.getInstance().getServerInfo(ping.getAServerLoginResponse());
		    	InitialHandler handler = (InitialHandler)e.getConnection();
		    	if (target == null) {
		    		e.setCancelled(true);
		    		e.setCancelReason(TextComponent.fromLegacyText(Messages.errorNotServer()));
		    		return;
		    	}
		    	handler.setOnlineMode(false);
	    	}
	    	if (accountInfo.hasCompleteAccount() != null)
	    	{
	    		if (accountInfo.getTypeOfAccount() == null)
	    		{
	    			ServerInfo target = ProxyServer.getInstance().getServerInfo(ping.getAServerLoginResponse());
			    	InitialHandler handler = (InitialHandler)e.getConnection();
			    	if (target == null) {
			    		e.setCancelled(true);
			    		e.setCancelReason(TextComponent.fromLegacyText(Messages.errorNotServer()));
			    		return;
			    	}
			    	handler.setOnlineMode(false);
			    	return;
	    		}
	    		if (accountInfo.getTypeOfAccount().contains("Crack"))
	    		{
	    			ServerInfo target = ProxyServer.getInstance().getServerInfo(ping.getAServerLoginResponse());
			    	InitialHandler handler = (InitialHandler)e.getConnection();
			    	if (target == null) {
			    		e.setCancelled(true);
			    		e.setCancelReason(TextComponent.fromLegacyText(Messages.errorNotServer()));
			    		return;
			    	}
			    	handler.setOnlineMode(false);
	    		}
	    		if (accountInfo.getTypeOfAccount().contains("Premium"))
	    		{
	    			ServerInfo target = ProxyServer.getInstance().getServerInfo(ping.getAServerLoginResponse());
			    	InitialHandler handler = (InitialHandler)e.getConnection();
			    	if (target == null) {
			    		e.setCancelled(true);
			    		e.setCancelReason(TextComponent.fromLegacyText(Messages.errorNotServer()));
			    		return;
			    	}
			    	handler.setOnlineMode(true);
	    		}
	    	}
	    }
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = 64)
    public void onLogin(ServerConnectEvent e) 
	{
		ProxiedPlayer player = e.getPlayer();
	    PingServers ping = new PingServers();
		new AccountInfo(player.getName().toString());
        AccountInfo accountInfo = new AccountInfo(player.getName().toString());
		if (e.getPlayer() != null) 
		{
			if (!accountInfo.hasAccount())
		    {
				boolean online = ping.getAServerLoginBooleanResponse();

				ServerInfo target = ProxyServer.getInstance().getServerInfo(ping.getAServerLoginResponse());
				if (online == false)
				{
					Messages.kickPlayerForNotLoginServer(player);
				}
				else if (online == true)
				{
					System.out.println(player + " connect to server: " + target);
					e.setTarget(target);
					this.main.getLogger().info(player.getName() + " c'est connecté avec un compte non définie...");
					accountInfo.createAccount();
					Messages.welcomeMessage(player);
				}
		    }
			if (accountInfo.getStatut() == 0)
			{
				if (accountInfo.hasAccount())
			    {
			    	if (accountInfo.hasCompleteAccount() == null)
			    	{
			    		boolean online = ping.getAServerLoginBooleanResponse();
			    		ServerInfo target = ProxyServer.getInstance().getServerInfo(ping.getAServerLoginResponse());
			    		if (online == false)
			    		{
			    			Messages.kickPlayerForNotLoginServer(player);
			    		}
			    		else if (online == true)
			    		{
			    			System.out.println(player + " connect to server: " + target.getName());
			    			e.setTarget(target);
			    			Messages.useRegisterCommand(player);
			    		}
			    	}
			    	else if (accountInfo.hasCompleteAccount() != null)
			    	{
			    		ServerInfo target = ProxyServer.getInstance().getServerInfo(ping.getAServerLoginResponse());
			    		boolean online = ping.getAServerLoginBooleanResponse();
			    		boolean lobbyonline = ping.getAServerLobbyBooleanResponse();
			    		if (online == false)
			    		{
			    			Messages.kickPlayerForNotLoginServer(player);
			    		}
			    		else if (online == true)
			    		{
			    			if (accountInfo.getTypeOfAccount() == null)
				    		{
				    			e.setTarget(target);
				    			Messages.lastStep(player);
				    			return;
				    		}
			    			if (accountInfo.getTypeOfAccount().contains("Crack"))
				    		{
			    				ServerInfo lobby = ProxyServer.getInstance().getServerInfo(ping.getAServerLobbyResponse());
			    				if (accountInfo.getUUID() == null)
						    	{
									String UUID = player.getUniqueId().toString().replace("-", "");
						    		accountInfo.setUUID(UUID);
						    	}
			    				
			    				if (accountInfo.getStatut() == 0)
			    				{
				    				String IPAdress = player.getAddress().getHostString().toString().replace(".", "");
									String dbIPAdress = accountInfo.getLastIP().toString().replace(".", "");
				    				if (IPAdress.contentEquals(dbIPAdress))
				    				{
				    					if (lobbyonline == true)
				    					{
				    						System.out.println(player + " connect to server: " + lobby.getName());
					    					accountInfo.setStatut(1);
					    					e.setTarget(lobby);
								    		Messages.successLogCrack(player);
								    		Messages.joinMessage(player);
				    					}
				    					if (lobbyonline == false)
				    					{
				    						Messages.kickPlayerForNotLobbyServer(player);
				    					}
				    				}
				    				else if (!IPAdress.contentEquals(dbIPAdress))
				    				{
				    					System.out.println(player + " connect to server: " + target.getName());
				    					accountInfo.setStatut(0);
							    		e.setTarget(target);
							    		Messages.useLoginCommand(player);
				    				}
			    				}
				    		}
			    			if (accountInfo.getTypeOfAccount().contains("Premium"))
				    		{
				    			ServerInfo lobby = ProxyServer.getInstance().getServerInfo(ping.getAServerLobbyResponse());
				    			if (accountInfo.getUUID() == null)
						    	{
				    				String UUID = player.getUniqueId().toString().replace("-", "");
						    		accountInfo.setUUID(UUID);
						    	}
								
				    			if (accountInfo.getStatut() == 0)
			    				{
				    				String UUID = player.getUniqueId().toString().replace("-", "");
									String dbUUID = accountInfo.getUUID().toString();
				    				if (UUID.contentEquals(dbUUID))
						    		{
				    					String IPAdress = player.getAddress().getHostString().toString().replace(".", "");
										String dbIPAdress = accountInfo.getLastIP().toString().replace(".", "");
				    					if (IPAdress.contentEquals(dbIPAdress))
				    					{
					    					if (lobbyonline == true)
					    					{
					    						System.out.println(player + " connect to server: " + lobby.getName());
					    						accountInfo.setStatut(1);
					    						e.setTarget(lobby);
								    			Messages.successLogPremium(player);
								    			Messages.joinMessage(player);
					    					}
					    					if (lobbyonline == false)
					    					{
					    						Messages.kickPlayerForNotLobbyServer(player);
					    					}
				    					}
				    					else if (!IPAdress.contentEquals(dbIPAdress))
				    					{
				    						System.out.println(player + " connect to server: " + target.getName());
				    						accountInfo.setStatut(0);
							    			e.setTarget(target);
							    			Messages.useLoginCommand(player);
				    					}
						    		}
						    		if (!UUID.contentEquals(dbUUID))
						    		{
							    		Messages.kickHackingAccount(player, UUID);
						    		}
			    				}
				    		}
			    		}
			    	}
			    }
			}
			else if (accountInfo.getStatut() == 1)
			{
				String UUID = player.getUniqueId().toString().replace("-", "");
				String dbUUID = accountInfo.getUUID().toString();
				
				if (UUID.contentEquals(dbUUID))
	    		{
	    			return;
	    		}
	    		if (!UUID.contentEquals(dbUUID))
	    		{
	    			Messages.kickHackingAccount(player, UUID);
	    		}
			}
		} 
    }
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = 64)
	public void onPlayerLeave(PlayerDisconnectEvent pde) {
		ProxiedPlayer player = pde.getPlayer();
		
		new AccountInfo(player.getName().toString());
        AccountInfo accountInfo = new AccountInfo(player.getName().toString());
        String IPAdress = player.getAddress().getHostString();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy  hh:mm:ss", Locale.FRANCE);  
        Date resultdate = new Date();
        String date = sdf.format(resultdate);
		
		if (accountInfo.getStatut() == 1)
    	{
			accountInfo.setStatut(0);
    		accountInfo.setLastIP(IPAdress);
    		accountInfo.setLastAuth(date);
    	}
	}
	
	
	public void addPlayerInDataBase(String name)
	{
		 Main.getInstance().getProxy().getScheduler().runAsync(Main.getPlugin(), () -> {
             try {
               PreparedStatement st = Main.getDatabase().prepareStatement("INSERT INTO `ed_login` (`player_name`, `player_uuid`, `player_password`, `lastIP`, `lastAuth`, `isPremium`, `isOnline`) VALUES(?, ?, ?, ?, ?, ?, ?)");
               st.setString(1, name);
               st.setString(2, (String)null);
               st.setString(3, (String)null);
               st.setString(4, (String)null);
               st.setString(5, (String)null);
               st.setString(6, (String)null);
               st.setInt(7, (int)0);
               st.executeUpdate();
               st.close();
             } catch (SQLException e) {
               e.printStackTrace();
             } 
         });
	}
	
	public boolean validate(String username) 
	{
		return (username != null && this.pattern.matcher(username).matches());
	}
}
