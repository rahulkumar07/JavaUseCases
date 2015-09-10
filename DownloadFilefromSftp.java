import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.IOException;

import java.util.HashMap;
import java.util.Vector;

public class DownloadFilefromSftp {
  
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
        String destinationPath = "C:\\logs\\";
        channel.cd("/home/username/");
        Vector<ChannelSftp.LsEntry> list = channel.ls("*.txt");
        for (ChannelSftp.LsEntry entry : list) {
            channel.get(entry.getFilename(),
                            destinationPath + entry.getFilename());
        }

        channel.disconnect();
        session.disconnect();
    }
}
