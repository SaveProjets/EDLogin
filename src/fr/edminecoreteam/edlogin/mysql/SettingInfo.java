package fr.edminecoreteam.edlogin.mysql;

import java.util.HashMap;
import java.util.Map;

import net.md_5.bungee.api.connection.ProxiedPlayer;


public class SettingInfo 
{
	private Map<ProxiedPlayer, SettingInfo> settingInfo;
	private ProxiedPlayer p;
	
	SettingData settingData;
	
	public SettingInfo(ProxiedPlayer p) {
        this.p = p;
        this.settingInfo = new HashMap<ProxiedPlayer, SettingInfo>();
        this.settingData = new SettingData(p);
        this.settingInfo.put(p, this);
    }
	
	public ProxiedPlayer getPlayer() { return p; }
	
	public int getLang() { return settingData.getLang(); }
}
