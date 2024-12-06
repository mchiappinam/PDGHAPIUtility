package me.mchiappinam.pdghapiutility;

import org.bukkit.plugin.java.JavaPlugin;

import me.mchiappinam.pdghapiutility.Metodos;

public class Main extends JavaPlugin {

	private Metodos m;

	@Override
	public void onEnable() {
		this.m = new Metodos(this);
		//m.enviarConsole("API Habilitada com sucesso - v0.1");
		//m.sendTweet("Teste v2");
		if(m.getBoolean("Twitter.TweetOnEnable"))
			m.sendTweet(m.getTexto("Twitter.TweetOnEnableText"));
		//m.sendEmail("mchiappinam@gmail.com", "pi", "roca");
		getServer().getConsoleSender().sendMessage("§3[PDGHAPIUtility] §2ativado - Developed by mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHAPIUtility] §2Acesse: http://pdgh.com.br/");
		getServer().getConsoleSender().sendMessage("§3[PDGHAPIUtility] §2Acesse: https://hostload.com.br/");
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHAPIUtility] §2desativado - Developed by mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHAPIUtility] §2Acesse: http://pdgh.com.br/");
		getServer().getConsoleSender().sendMessage("§3[PDGHAPIUtility] §2Acesse: https://hostload.com.br/");
	}

	public Metodos getMetodos() {
		return m;
	}

}
