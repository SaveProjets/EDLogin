package fr.edminecoreteam.edlogin.authentication;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.edminecoreteam.edlogin.Main;
import fr.edminecoreteam.edlogin.cache.AccountInfo;
import fr.edminecoreteam.edlogin.utils.Messages;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Login extends Command 
{
	public Login(Main main) 
	{
		super("AllowCrack", "", new String[] { "login" });
	}
	  
	public void execute(CommandSender sender, String[] args) {
		
		new AccountInfo(sender.getName().toString());
        AccountInfo accountInfo = new AccountInfo(sender.getName().toString());
        ServerInfo target = ProxyServer.getInstance().getServerInfo("Lobby1");
        ProxiedPlayer player = (ProxiedPlayer)sender;
        
        if (accountInfo.hasCompleteAccount() == null)
	    {
        	Messages.errorNotRegister(player);
        	return;
	    }
        else if (accountInfo.hasCompleteAccount() != null)
        {
        	if (accountInfo.getStatut() == 0)
        	{
        		if (accountInfo.getTypeOfAccount() != null)
        		{
        			if (args.length <= 0) 
        	        {
                		Messages.errorLogin(player);
        	        }  
                	else 
        	        { 
                		try 
                		{
        		            byte[] passBytes = args[0].getBytes();
        		            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        		            algorithm.reset();
        		            algorithm.update(passBytes);
        		            MessageDigest md = MessageDigest.getInstance("SHA-256");
        		            byte[] messageDigest = md.digest(passBytes);
        		            BigInteger number = new BigInteger(1, messageDigest);
        		            String code = number.toString(16);
        		            if (code.equals(accountInfo.getPassword())) 
        		            {
        		            	if (accountInfo.getStatut() == 0)
        				    	{
        				    		accountInfo.setStatut(1);
        				    	}
        		            	System.out.println(player + " connect to server: " + target.getName());
        		            	Messages.succesLogin(player);
        		            	player.connect(target);
        		            	Messages.joinMessage(player);
        		            } else 
        		            {
        		            	Messages.errorWrongPassword(player);
        		            } 
                		} catch (NoSuchAlgorithmException e) {
                			throw new Error("invalid JRE: have not 'MD5' impl.", e);
                		} 
        	        }
        		}
        		else if (accountInfo.getTypeOfAccount() == null)
        		{
        			Messages.errorNotDefiendTypeOfAccount(player);
        		}
        	}
        	else if (accountInfo.getStatut() == 1)
        	{
        		Messages.errorAllradyLogged(player);
        	}
        	 
        }
	 }
}
