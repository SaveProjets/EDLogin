package fr.edminecoreteam.edlogin.cache;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.Map;

public class AccountInfo 
{
	private static Map<ProxiedPlayer, AccountInfo> accountInfo;
	private ProxiedPlayer player;
	private AccountData accountData;
	
	static 
	{
		AccountInfo.accountInfo = new HashMap<ProxiedPlayer, AccountInfo>();
	}
	
	public AccountInfo(ProxiedPlayer player)
	{
        this.player = player;
        this.accountData = new AccountData(player);
        AccountInfo.accountInfo.put(player, this);
    }
	
	public static AccountInfo getInfosOfPlayer(ProxiedPlayer player)
	{
        return AccountInfo.accountInfo.get(player);
    }
	
	public String getPlayerUUID()
	{
        return this.player.getUniqueId().toString();
    }
	
	public void createAccount() 
	{
        this.accountData.createAccount();
    }
	
	public boolean hasAccount() 
	{
        return this.accountData.hasaccount();
    }
	
	public String hasCompleteAccount() 
	{
        return this.accountData.hasCompleteAccount();
    }
	
	public void createPassword(String password) 
	{
        this.accountData.createPassword(password);
    }
	
	public String getPassword() 
	{
        return this.accountData.getPassword();
    }

	
	public void updatePlayerName()
	{
        this.accountData.updatePlayerName();
    }

	public String getDbName(){
		return this.accountData.getDbName();
	}
	
	public void setLastIP(String IPAdress) 
	{
        this.accountData.setLastIP(IPAdress);
    }
	
	public String getLastIP() 
	{
        return this.accountData.getLastIP();
    }
	
	public void setStatut(int Statut) 
	{
        this.accountData.setStatut(Statut);
    }
	
	public int getStatut() 
	{
        return this.accountData.getStatut();
    }
	
	public void setLastAuth(String date) 
	{
        this.accountData.setLastAuth(date);
    }
	
	public String getLastAuth() 
	{
        return this.accountData.getLastAuth();
    }
}
