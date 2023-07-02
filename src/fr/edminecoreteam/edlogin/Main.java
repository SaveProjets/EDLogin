package fr.edminecoreteam.edlogin;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

import fr.edminecoreteam.edlogin.authentication.Crack;
import fr.edminecoreteam.edlogin.authentication.Login;
import fr.edminecoreteam.edlogin.authentication.Premium;
import fr.edminecoreteam.edlogin.authentication.Register;
import fr.edminecoreteam.edlogin.mysql.MySQL;
import fr.edminecoreteam.edlogin.utils.Listeners;
import fr.edminecoreteam.edlogin.utils.PingServers;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin 
{
	public static Main instance;
	private static Plugin plugin;
	public static MySQL database;
	public static int srvNumber;
	
	@Override
	public void onEnable() 
	{
		databaseConnect();
		if (!Main.database.isOnline()) { return; }
		getProxy().getPluginManager().registerCommand(this, (Command)new Register(this));
	    getProxy().getPluginManager().registerCommand(this, (Command)new Login(this));
	    getProxy().getPluginManager().registerCommand(this, (Command)new Crack(this));
	    getProxy().getPluginManager().registerCommand(this, (Command)new Premium(this));
	    getProxy().getPluginManager().registerListener(this, new Listeners(this, "§cErreur, Nom invalide... Hack ?"));
	    getLogger().info("loaded");
	}

	@Override
	public void onDisable() 
	{
		Main.database.deconnexion();
	}
	
	private void databaseConnect() {
		(Main.database = new MySQL("jdbc:mysql://", "45.140.165.235", "22728-database", "22728-database", "S5bV5su4p9")).connexion();
		if (!database.isOnline()) { return; }
		Main.database.creatingTableLogin();
		PingServers pSrv = new PingServers();
	    Main.srvNumber = pSrv.getServerPerGroup();
		refreshConnexion();
	}
	
	public void sendConsoleMsg(String string) {
	    getLogger().info(string);
	}
	
	public void refreshConnexion() 
    {
        ProxyServer.getInstance().getScheduler().schedule((Plugin)this, (Runnable)new Runnable() 
        {
            @Override
            public void run() 
            {
                if (!Main.database.isOnline()) 
                {
                	Main.database.connexion();
                    run();
                }
                else 
                {
                	Main.database.deconnexion();
                	Main.database.connexion();
                }
            } 
        }, 0L, 120L, TimeUnit.SECONDS);
    }
	
	public static Main getInstance() { return instance; }
	public static Plugin getPlugin() { return plugin; }
	public static Connection getDatabase() { return MySQL.getConnection(); }
}
