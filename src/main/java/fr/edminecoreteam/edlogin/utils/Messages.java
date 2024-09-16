package fr.edminecoreteam.edlogin.utils;



import fr.edminecoreteam.edlogin.mysql.SettingInfo;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Messages 
{
	
	public static void joinMessage(ProxiedPlayer p) {
		SettingInfo pInfo = new SettingInfo(p);
        if (pInfo.getLang() == 0) {
            p.sendMessage(TextComponent.fromLegacyText(""));
            p.sendMessage(TextComponent.fromLegacyText(" §7Bienvenue chez §6§lEDMINE §f§lNETWORK §7!"));
            p.sendMessage(TextComponent.fromLegacyText(""));
            p.sendMessage(TextComponent.fromLegacyText("   §c§lInformation:"));
            p.sendMessage(TextComponent.fromLegacyText("    §7• §fCe serveur est actuellement"));
            p.sendMessage(TextComponent.fromLegacyText("    §7  §fen phase §6Alpha§f."));
            p.sendMessage(TextComponent.fromLegacyText("   §d§lAstuce:"));
            p.sendMessage(TextComponent.fromLegacyText("    §7• §fUtilisez le §a§lMenu Principal"));
            p.sendMessage(TextComponent.fromLegacyText("    §7  §fpour choisir un des jeux du §bserveur§f."));
            p.sendMessage(TextComponent.fromLegacyText(""));
        }
        else if (pInfo.getLang() == 1) {
            p.sendMessage(TextComponent.fromLegacyText(""));
            p.sendMessage(TextComponent.fromLegacyText(" §7Welcome to §6§lEDMINE §f§lNETWORK §7!"));
            p.sendMessage(TextComponent.fromLegacyText(""));
            p.sendMessage(TextComponent.fromLegacyText("   §c§lInformation:"));
            p.sendMessage(TextComponent.fromLegacyText("    §7• §fThis server is currently"));
            p.sendMessage(TextComponent.fromLegacyText("    §7  §fin §6Alpha§f phase."));
            p.sendMessage(TextComponent.fromLegacyText("   §d§lTrick:"));
            p.sendMessage(TextComponent.fromLegacyText("    §7• §fUse the §a§lMain Menu"));
            p.sendMessage(TextComponent.fromLegacyText("    §7  §fto choose one of the §bserver games§f."));
            p.sendMessage(TextComponent.fromLegacyText(""));
        }
        else if (pInfo.getLang() == 2) {
            p.sendMessage(TextComponent.fromLegacyText(""));
            p.sendMessage(TextComponent.fromLegacyText(" §7Bienvenue chez §6§lEDMINE §f§lNETWORK §7!"));
            p.sendMessage(TextComponent.fromLegacyText(""));
            p.sendMessage(TextComponent.fromLegacyText("   §c§lInformation:"));
            p.sendMessage(TextComponent.fromLegacyText("    §7• §fEste servidor se encuentra actualmente"));
            p.sendMessage(TextComponent.fromLegacyText("    §7  §fen fase §6Alpha§f."));
            p.sendMessage(TextComponent.fromLegacyText("   §d§lAstuce:"));
            p.sendMessage(TextComponent.fromLegacyText("    §7• §fUsa el §a§lMenu Principal"));
            p.sendMessage(TextComponent.fromLegacyText("    §7  §fpara elegir uno de los juegos del §bservidor§f."));
            p.sendMessage(TextComponent.fromLegacyText(""));
        }
        else if (pInfo.getLang() == 3) {
            p.sendMessage(TextComponent.fromLegacyText(""));
            p.sendMessage(TextComponent.fromLegacyText(" §7Bienvenue chez §6§lEDMINE §f§lNETWORK §7!"));
            p.sendMessage(TextComponent.fromLegacyText(""));
            p.sendMessage(TextComponent.fromLegacyText("   §c§lInformation:"));
            p.sendMessage(TextComponent.fromLegacyText("    §7• §fDieser Server befindet sich derzeit"));
            p.sendMessage(TextComponent.fromLegacyText("    §7  §fin der §6Alpha-Phase§f."));
            p.sendMessage(TextComponent.fromLegacyText("   §d§lAstuce:"));
            p.sendMessage(TextComponent.fromLegacyText("    §7• §fVerwenden Sie das §a§lHauptmene§f,"));
            p.sendMessage(TextComponent.fromLegacyText("    §7  §fum eines der §bServerspiele auszuwelen§f."));
            p.sendMessage(TextComponent.fromLegacyText(""));
        }
    }
	
	public static void welcomeMessage(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§7Bienvenu(e) sur §6§lEDMINE §f§lNETWORK §7!"));
		p.sendMessage(TextComponent.fromLegacyText(""));
		p.sendMessage(TextComponent.fromLegacyText(" §f➡ §dInformations:"));
		p.sendMessage(TextComponent.fromLegacyText("  §f▶ §7Utilisez la commande"));
		p.sendMessage(TextComponent.fromLegacyText("  §f  §d/§fregister §b[mdp] §b[mdp]"));
		p.sendMessage(TextComponent.fromLegacyText(""));
	}
	
	public static void succesRegister(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§aSuccès ! Mot de passe enregistrer."));
	}
	
	public static void succesLogin(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§aSuccès ! §fRedirection vers un Lobby..."));
	}
	
	public static void lastStep(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText(""));
		p.sendMessage(TextComponent.fromLegacyText(" §f▶ §7C'est bientôt fini !"));
		p.sendMessage(TextComponent.fromLegacyText(" §f▶ §7Définissez le type de votre"));
		p.sendMessage(TextComponent.fromLegacyText(" §f  §7compte minecraft."));
		p.sendMessage(TextComponent.fromLegacyText(" §f▶ §6⚠ §7Vos données reste les mêmes"));
		p.sendMessage(TextComponent.fromLegacyText(" §f  §7peut importe le type de compte."));
		p.sendMessage(TextComponent.fromLegacyText(""));
		p.sendMessage(TextComponent.fromLegacyText(" §f▶ §7Utilisez §d/§apremium §7ou §d/§ccrack§7."));
		p.sendMessage(TextComponent.fromLegacyText(""));
	}
	
	public static void errorRegister(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§cErreur, utilisez /register [mdp] [mdp]."));
	}
	
	public static void errorNotRegister(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§cErreur, vous n'êtes pas enregistrer, utilisez /register [mdp] [mdp]."));
	}
	
	public static void errorAllradyRegister(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§cErreur, il semble que ce compte est déjà enregistrer..."));
	}
	
	public static void errorWrongSynthax(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§cErreur, mauvaise syntaxe..."));
	}
	
	public static void errorLogin(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§cErreur, utilisez la commande /login [mdp]."));
	}
	
	public static void errorWrongPassword(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§cErreur, mauvais mot de passe..."));
	}
	
	public static void errorNotDefiendTypeOfAccount(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§cErreur, définissez le type de votre compte..."));
	}
	
	public static void errorAllradyLogged(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§cErreur, vous êtes déjà connecter..."));
	}
	
	public static void useLoginCommand(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§fUtilisez la commande §d/§flogin §d[mdp]§f."));
	}
	
	public static void useRegisterCommand(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§fUtilisez la commande §d/§fregister §d[mdp] [mdp]§f."));
	}
	
	public static void successLogPremium(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§aSuccès ! §fConnexion en premium réussie."));
	}
	
	public static void successLogCrack(ProxiedPlayer p)
	{
		p.sendMessage(TextComponent.fromLegacyText("§aSuccès ! §fConnexion en crack réussie."));
	}
	
	public static void kickPlayerForNotLoginServer(ProxiedPlayer p)
	{
		p.disconnect(TextComponent.fromLegacyText("§cErreur de connexion, aucun serveurs d'authentifications n'est disponible..."));
	}
	
	public static void kickPlayerForNotLobbyServer(ProxiedPlayer p)
	{
		p.disconnect(TextComponent.fromLegacyText("§cErreur de connexion, aucun lobbys n'est disponible..."));
	}
	
	public static String errorNotServer()
	{
		return "§cErreur de connexion, aucun serveurs d'authentifications n'est disponible...";
	}
	
	public static void kickForSetUUIDSuccess(ProxiedPlayer p)
	{
		String message = "§aSuccès ! §fVeuillez vous reconnecter...";
		p.disconnect(TextComponent.fromLegacyText(message));
	}
	
	public static void kickHackingAccount(ProxiedPlayer p, String UUID)
	{
		p.disconnect(TextComponent.fromLegacyText("§cErreur de connexion, Hacking ? UUID Utilisé: " + UUID));
	}
}
