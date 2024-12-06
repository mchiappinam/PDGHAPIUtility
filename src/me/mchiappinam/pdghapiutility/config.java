package me.mchiappinam.pdghapiutility;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.mchiappinam.pdghapiutility.Main;

public class Config {

	private Main pl;

	public Config(Metodos m) {
		pl = m.getPlugin();
	}

	public File getPasta() {
		File f = pl.getDataFolder();
		if (!f.exists())
			f.mkdirs();
		return f;
	}

	public File getArquivo() {
		File f = new File(getPasta(), "config.yml");
		if (!f.exists())
			pl.saveDefaultConfig();
		return f;
	}

	public FileConfiguration getConfig() {
		return YamlConfiguration.loadConfiguration(getArquivo());
	}

	public void atualizarConfig() {
		try {
			getConfig().save(getArquivo());
			getConfig().load(getArquivo());
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

}
