package fr.edminecoreteam.edlogin.authentication;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.edminecoreteam.edlogin.Main;
import fr.edminecoreteam.edlogin.cache.AccountInfo;
import fr.edminecoreteam.edlogin.utils.Messages;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Register extends Command 
{
	public Register(Main main) 
	{
	    super("AllowCrack", null, new String[] { "register" });
	}
	
	public void execute(CommandSender sender, String[] args) {
		
		ProxiedPlayer p = (ProxiedPlayer)sender;
		new AccountInfo(sender.getName().toString());
        AccountInfo accountInfo = new AccountInfo(sender.getName().toString());
	    if (accountInfo.hasCompleteAccount() == null)
	    {
	    	if (args.length <= 1) 
	    	{
	    		Messages.errorRegister(p);
	    	} 
	    	else if (!args[0].equals(args[1])) 
	    	{
	    		Messages.errorWrongSynthax(p);
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
			        accountInfo.createPassword(code);
			        Messages.succesRegister(p);
			        Messages.lastStep(p);
	    		} catch (NoSuchAlgorithmException e) {
	    			throw new Error("invalid JRE: have not 'MD5' impl.", e);
	    		}
		    }
	    }
	    else if (accountInfo.hasCompleteAccount() != null)
	    {
	    	Messages.errorAllradyRegister(p);
	    }
	}
}
