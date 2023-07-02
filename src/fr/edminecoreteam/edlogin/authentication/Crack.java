package fr.edminecoreteam.edlogin.authentication;

import fr.edminecoreteam.edlogin.Main;
import fr.edminecoreteam.edlogin.cache.AccountInfo;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Crack extends Command 
{

	public Crack(Main main) 
	{
		super("crack");
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
	    		if (accountInfo.getTypeOfAccount() != null)
	    		{
	    			if (accountInfo.getTypeOfAccount().contains("Premium"))
		    		{
		    			sender.sendMessage(TextComponent.fromLegacyText("§cErreur, il semble que ce compte sois premium..."));
		    			return;
		    		}
	    			if (accountInfo.getTypeOfAccount().contains("Crack"))
		    		{
		    			sender.sendMessage(TextComponent.fromLegacyText("§cErreur, il semble que ce compte sois déjà crack..."));
		    			return;
		    		}
	    		}
	    		else if (accountInfo.getTypeOfAccount() == null)
	    		{
	    			if (accountInfo.getLastIP() == null)
	    			{
	    				accountInfo.setLastIP(IPAdress);
	    			}
	    			
			    		accountInfo.setTypeOfAccount("Crack");
			    		accountInfo.setUUID(null);
			    		accountInfo.setStatut(0);
			    		p.disconnect(TextComponent.fromLegacyText("§aSuccès ! §fVeuillez vous reconnecter..."));
	    		}
	    		
	    			
	    		
	    	}
	    	else
	    	{
	    		return;
	    	}
	    
	    }
	}
}
