package fr.edminecoreteam.edlogin.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
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
import net.md_5.bungee.event.EventPriority;

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
		ServerInfo target = ProxyServer.getInstance().getServerInfo(ping.getAServerLobbyResponse());
		if (target == null) {
			e.setCancelled(true);
			e.setCancelReason(TextComponent.fromLegacyText(Messages.errorNotServer()));
			return;
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
    public void onLogin(ServerConnectEvent e)
	{
		ProxiedPlayer player = e.getPlayer();
	    PingServers ping = new PingServers();
        AccountInfo accountInfo = new AccountInfo(player);
		if (e.getPlayer() != null)
		{
			if (!accountInfo.hasAccount())
		    {
				boolean online = ping.getAServerLobbyBooleanResponse();

				ServerInfo target = ProxyServer.getInstance().getServerInfo(ping.getAServerLobbyResponse());
				if (online == false)
				{
					Messages.kickPlayerForNotLobbyServer(player);
				}
				else if (online == true)
				{
					System.out.println(player + " connect to server: " + target);
					e.setTarget(target);
					this.main.getLogger().info(player.getName() + " s'est connecté pour la première fois...");
					ProxyServer.getInstance().getScheduler().schedule(Main.getInstance(), new Runnable() {
						public void run(){
							accountInfo.createAccount();
						}
							}, 500, TimeUnit.MILLISECONDS);


					Messages.welcomeMessage(player);
				}
		    }
			if (accountInfo.getStatut() == 0)
			{

			    		boolean online = ping.getAServerLobbyBooleanResponse();
			    		ServerInfo target = ProxyServer.getInstance().getServerInfo(ping.getAServerLobbyResponse());
			    		if (online == false)
			    		{
			    			Messages.kickPlayerForNotLobbyServer(player);
			    		}
			    		else if (online == true)
			    		{

										//
				    			ServerInfo lobby = ProxyServer.getInstance().getServerInfo(ping.getAServerLobbyResponse());

				    				String UUID = player.getName();
									String dbUUID = accountInfo.getDbName();
				    				if (UUID.contentEquals(dbUUID))
						    		{
					    					if (online == true)
					    					{
					    						System.out.println(player + " connect to server: " + lobby.getName());
					    						accountInfo.setStatut(1);
					    						e.setTarget(lobby);
								    			Messages.successLogPremium(player);
								    			Messages.joinMessage(player);
					    					}
					    					if (online == false)
					    					{
					    						Messages.kickPlayerForNotLobbyServer(player);
					    					}

						    		}
						    		if (!UUID.contentEquals(dbUUID))
						    		{
							    		accountInfo.updatePlayerName();
						    		}

				    		}
			    		}

			}
			else if (accountInfo.getStatut() == 1)
			{
				String UUID = player.getName();
				String dbUUID = accountInfo.getDbName();

				if (UUID.contentEquals(dbUUID))
	    		{
	    			return;
	    		}
	    		if (!UUID.contentEquals(dbUUID))
	    		{
					accountInfo.updatePlayerName();
	    		}
			}
		}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = 64)
	public void onPlayerLeave(PlayerDisconnectEvent pde) {
		ProxiedPlayer player = pde.getPlayer();

        AccountInfo accountInfo = new AccountInfo(player);
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
