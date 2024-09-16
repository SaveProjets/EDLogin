package fr.edminecoreteam.edlogin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

import fr.edminecoreteam.edlogin.authentication.Login;
import fr.edminecoreteam.edlogin.authentication.Register;
import fr.edminecoreteam.edlogin.mysql.MySQL;
import fr.edminecoreteam.edlogin.utils.Listeners;
import fr.edminecoreteam.edlogin.utils.PingServers;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Main extends Plugin 
{
	public static Main instance;
	private static Plugin plugin;
	public static MySQL database;
	public static int srvNumber;
	private Configuration config;
	
	@Override
	public void onEnable() 
	{
		loadConfig();

		databaseConnect();
		if (!Main.database.isOnline()) { return; }
	    getProxy().getPluginManager().registerListener(this, new Listeners(this, "§cErreur, Nom invalide... Hack ?"));
	    getLogger().info("loaded");
	}

	@Override
	public void onDisable() 
	{
		Main.database.deconnexion();
	}
	
	private void databaseConnect() {
		(Main.database = new MySQL("jdbc:mysql://", config.getString("mysql.host"), config.getString("mysql.database"), config.getString("mysql.user"), config.getString("mysql.password"))).connexion();
		if (!database.isOnline()) { return; }
		Main.database.creatingTableLogin();
		PingServers pSrv = new PingServers();
	    Main.srvNumber = pSrv.getServerPerGroup();
		refreshConnexion();
	}

	private void loadConfig() {
		File ord = new File("plugins/API");
		if (!ord.exists()) {
			ord.mkdir();
		}
		File configFile = new File("plugins/API", "mysql.yml");
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Charger la configuration depuis le fichier
		try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
