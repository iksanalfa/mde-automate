package runners;

import utils.SFTPUtil;

public class TestSftp {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SFTPUtil ftp = new SFTPUtil();
//		ftp.listDirectory("testlistdir");
//		ftp.listDirectory("testupload");
		ftp.listDirectory("testdownload");

	}

}
