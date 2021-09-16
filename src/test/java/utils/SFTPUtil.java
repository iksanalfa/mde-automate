package utils;

import static org.junit.Assert.fail;

import java.util.UUID;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import io.cucumber.java.Scenario;

public class SFTPUtil {

	public void listDirectory(String cmd) {

		say("SFTP start");

		String username = "EN-USER1";
		String host = "10.132.133.252";
		Integer port = 22;
		String pass = "EN-USER1";
//		String khfile = "/Telkom Sigma/SeleniumTutorial/mde-automate/src/test/java/utils/auth/known_hosts";
		String userDir = System.getProperty("user.dir");
		String khfile = userDir + "/src/test/java/utils/auth/known_hosts";
//	    String identityfile = "/zmde-auto/SFTP/key.pem";
		String dir = "/";
		UUID randUUID = UUID.randomUUID();
		String diff = randUUID.toString().substring(0, 8);

		JSch jsch = null;
		Session session = null;
		Channel channel = null;
		ChannelSftp c = null;

		try {
			jsch = new JSch();
			jsch.setKnownHosts(khfile);
			session = jsch.getSession(username, host, port);
			session.setPassword(pass);
			jsch.setKnownHosts(khfile);
//	        jsch.addIdentity(identityfile);
			session.connect();
			// check server key
			/*
			 * HostKey hk=session.getHostKey(); System.out.println("HostKey: "+
			 * hk.getHost()+" "+ hk.getType()+" "+ hk.getFingerPrint(jsch));
			 */

			channel = session.openChannel("sftp");
			channel.connect();
			c = (ChannelSftp) channel;

			// List Dir
			if (cmd.equalsIgnoreCase("testlistdir")) {
				say("-list directory");
				c.cd("/Ren/Jalin/Link/Recon");
				Vector<?> filelist = c.ls("*");
				for (int i = 0; i < filelist.size(); i++) {
					LsEntry entry = (LsEntry) filelist.get(i);
					System.out.println(entry.getFilename());
					/// System.out.println(filelist.get(i).toString());
				}
			}
			// ---End List Dir

			// Upload file
			if (cmd.equalsIgnoreCase("testupload")) {
				say("-upload");
				String localFile = "/SFTP/testfiletoupload.txt";
				String remoteDir = "/";
				c.put(localFile, remoteDir + diff + "-testfiletoupload.txt");
				c.exit();
			}
			// ---End Upload file

			// Download file
			if (cmd.equalsIgnoreCase("testdownload")) {
				say("-download");
				String remoteFile = "/Ren/Jalin/Link/Recon/QRX_RECON_360004_000008_210710_ACQ_1";
				String localDir = "/workflowreport/";
				c.get(remoteFile, localDir + "QRX_RECON_360004_000008_210710_ACQ_1");
				c.exit();
			}
			// ---End Download file

		} catch (Exception e) {
			e.printStackTrace();
		}

		c.disconnect();
		session.disconnect();

		say("---SFTP end");

	}

	public void downloadFile(Scenario scenario, String folderName, String fileName) {

		say("SFTP start");

		String username = "EN-USER1";
		String host = "10.132.133.252";
		Integer port = 22;
		String pass = "EN-USER1";
//		String khfile = "/Telkom Sigma/SeleniumTutorial/mde-automate/src/test/java/utils/auth/known_hosts";
		String userDir = System.getProperty("user.dir");
		String khfile = userDir + "/src/test/java/utils/auth/known_hosts";
		String dir = "/";

		JSch jsch = null;
		Session session = null;
		Channel channel = null;
		ChannelSftp c = null;
		try {
			jsch = new JSch();
			session = jsch.getSession(username, host, port);
			session.setPassword(pass);
			jsch.setKnownHosts(khfile);
			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();
			c = (ChannelSftp) channel;

			// Download file
			say("-download");
			String remoteFile = "/Ren/Jalin/Link/" + folderName + "/" + fileName;
//			String remoteFile = "/Ren/Jalin/Link/" + folderName + "/" + "QRX_RECON_360004_000008_210710_ACQ_1";
			String localDir = "/workflowreport/";
			c.get(remoteFile, localDir + fileName);
			c.exit();
			// ---End Download file

		} catch (Exception e) {
			e.printStackTrace();
			fail("File/Folder Not Found");
		}

		c.disconnect();
		session.disconnect();

	}

	private void say(Object tosay) {
		System.out.println(tosay);
	}

}
