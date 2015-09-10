import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.IOException;

import java.util.HashMap;
import java.util.Vector;

public class UploadMultiFilesToSftp {
  
      public static void main(String[] s) throws IOException, JSchException,
                                               SftpException {
        JSch jsch = new JSch();
        Session session = null;
        session =
                jsch.getSession("username", "machineIp/hostname", 22);
        session.setPassword("userpass");
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        ChannelSftp channel = null;
        channel = (ChannelSftp)session.openChannel("sftp");
        channel.connect();
               String sourcePath = "C:\\logs\\";
        channel.cd("absolute path of the folder where files are to be put");
        String files;
        File folder = new File(sourcePath);
        System.out.println("folder=" + folder);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                files = listOfFiles[i].getAbsolutePath();
                channel.put(new FileInputStream(files),listOfFiles[i].getName());
                System.out.println(files);
            }
        }

        channel.disconnect();
        session.disconnect();
    }
}
