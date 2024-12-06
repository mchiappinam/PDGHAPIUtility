package me.mchiappinam.pdghapiutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.bukkit.configuration.file.FileConfiguration;

import me.mchiappinam.pdghapiutility.Main;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Metodos {

	private Main pl;
	//private Server s;
	//private ConsoleCommandSender ccs;
	private Config c;
	private FileConfiguration fc;

	public Metodos(Main pl) {
		this.pl = pl;
		//this.s = pl.getServer();
		//this.ccs = s.getConsoleSender();
		this.c = new Config(this);
		this.fc = c.getConfig();
	}

	public Main getPlugin() {
		return pl;
	}

	public Config getArquivos() {
		return c;
	}

	public FileConfiguration getConfig() {
		return fc;
	}

	public boolean getBoolean(String config) {
		try {
			return fc.getBoolean(config);
		} catch (Exception e) {}
		pl.getServer().getConsoleSender().sendMessage("§3[PDGHAPIUtility] §2Linha inexistente: " + config);
		return false;
	}

	public String getTexto(String config) {
		try {
			if (fc.getString(config) != null)
				return fc.getString(config);
		} catch (Exception e) {
		}
		pl.getServer().getConsoleSender().sendMessage("§3[PDGHAPIUtility] §2Linha inexistente: " + config);
		return "";
	}

	public void sendTweet(String msg) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(getTexto("Twitter.CostumeKey"));
		cb.setOAuthConsumerSecret(getTexto("Twitter.CostumeSecret"));
		cb.setOAuthAccessToken(getTexto("Twitter.Token"));
		cb.setOAuthAccessTokenSecret(getTexto("Twitter.TokenSecret"));
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter t = tf.getInstance();
		try {
			t.updateStatus(calendario()+getTexto("Twitter.Servidor")+" - "+msg);
	    	pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "alert "+getTexto("Twitter.Servidor")+" "+msg);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
    
    public String calendario() {
		Calendar agora = Calendar.getInstance();
		SimpleDateFormat gdf = new SimpleDateFormat("ddMMyy.HHmmss");
        return gdf.format(agora.getTime());
    }

	public void sendEmail(String receptor, String assunto, String msg) {

		Properties pros = new Properties();
		pros.put("mail.smtp.host", getTexto("Email.Host"));
		pros.put("mail.smtp.socketFactory", getTexto("Email.Porta"));
		pros.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		pros.put("mail.smtp.auth", "true");
		pros.put("mail.smtp.port", getTexto("Email.Porta"));
		Session session = Session.getInstance(pros, new javax.mail.Authenticator() {

			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(getTexto("Email.Remetente"), getTexto("Email.Senha"));
			}
		});
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(getTexto("Email.Remetente")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));
			message.setSubject(assunto);
			message.setContent(msg, "text/html; charset=utf-8");
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
