package fr.edminecoreteam.edlogin.authentication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import fr.edminecoreteam.edlogin.Main;
import fr.edminecoreteam.edlogin.cache.AccountInfo;
import fr.edminecoreteam.edlogin.utils.Messages;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Premium extends Command 
{
	public static boolean isPremium(String player) {
		ProxiedPlayer p = ProxyServer.getInstance().getPlayer(player);
		try {
			// Create a URL for the desired page
			URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + p.getName());
			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			@SuppressWarnings("unused")
			String str;
			while ((str = in.readLine()) != null) {
				return true;
			}
			in.close();
			} catch (MalformedURLException ex) {
			} catch (IOException ex) {
			}
		return false;
	}
	
	public Premium(Main main) 
	{
		super("AllowCrack", "", new String[] { "premium" });
	}
	  
	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer p = ProxyServer.getInstance().getPlayer(sender.getName());
		new AccountInfo(p.getName().toString());
        AccountInfo accountInfo = new AccountInfo(p.getName().toString());
        String IPAdress = p.getAddress().getHostString().toString();
	    if (accountInfo.getPassword() != null) 
	    {
	    	if (args.length == 0)
	    	{
	    		if (isPremium(sender.getName()))
		    	{
	    			if (accountInfo.getTypeOfAccount() != null)
	    			{
	    				if (accountInfo.getTypeOfAccount().contains("Premium"))
			    		{
			    			sender.sendMessage(TextComponent.fromLegacyText("§cErreur, il semble que ce compte sois déjà premium..."));
			    			return;
			    		}
	    				if (accountInfo.getLastIP() == null)
		    			{
		    				accountInfo.setLastIP(IPAdress);
		    			}
		    			accountInfo.setTypeOfAccount("Premium");
		    			accountInfo.setUUID(null);
		    			accountInfo.setStatut(0);
			    		Messages.kickForSetUUIDSuccess(p);
	    			}
	    			else if (accountInfo.getTypeOfAccount() == null)
	    			{
	    				if (accountInfo.getLastIP() == null)
		    			{
		    				accountInfo.setLastIP(IPAdress);
		    			}
		    			accountInfo.setTypeOfAccount("Premium");
		    			accountInfo.setUUID(null);
		    			accountInfo.setStatut(0);
			    		Messages.kickForSetUUIDSuccess(p);
	    			}
		    	}
		    	else
		    	{
		    		sender.sendMessage(TextComponent.fromLegacyText("§cErreur, il semble que ce compte ne sois pas premium..."));
		    	}
	    	}
	    	else
	    	{
	    		return;
	    	}
	    
	    }
	}
}
