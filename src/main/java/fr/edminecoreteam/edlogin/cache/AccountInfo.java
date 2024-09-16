package fr.edminecoreteam.edlogin.cache;

import java.util.HashMap;
import java.util.Map;

public class AccountInfo 
{
	private static Map<String, AccountInfo> accountInfo;
	private String playerName;
	private AccountData accountData;
	
	static 
	{
		AccountInfo.accountInfo = new HashMap<String, AccountInfo>();
	}
	
	public AccountInfo(String playerName) 
	{
        this.playerName = playerName;
        this.accountData = new AccountData(playerName);
        AccountInfo.accountInfo.put(playerName, this);
    }
	
	public static AccountInfo getInfosOfPlayer(String playerName) 
	{
        return AccountInfo.accountInfo.get(playerName);
    }
	
	public String getPlayerName() 
	{
        return this.playerName;
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
	
	public void setTypeOfAccount(String accountType) 
	{
        this.accountData.setTypeOfAccount(accountType);
    }
	
	public String getTypeOfAccount() 
	{
        return this.accountData.getTypeOfAccount();
    }
	
	public void setUUID(String UUID) 
	{
        this.accountData.setUUID(UUID);
    }
	
	public String getUUID() 
	{
        return this.accountData.getUUID();
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
