package my.PuttyAppUI;

import com.jcraft.jsch.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class SecureShell {
    
    public static void SSHConnect(String user, String host, String passwd, int port){

        try{
            JSch jsch = new JSch();

            Session session = jsch.getSession(user, host, port);
            session.setPassword(passwd);

            UserInfo ui = new MyUserInfo(){
                public void showMessage(String message){
                  JOptionPane.showMessageDialog(null, message);
                }
                public boolean promptYesNo(String message){
                  Object[] options = { "yes", "no" };
                  int foo = JOptionPane.showOptionDialog(null, 
                                                       message,
                                                       "Warning", 
                                                       JOptionPane.DEFAULT_OPTION, 
                                                       JOptionPane.WARNING_MESSAGE,
                                                       null, options, options[0]);
                  return foo==0;
                }
            };

            session.setUserInfo(ui);
            
            session.connect(30000);   // making a connection with timeout.

            Channel channel = session.openChannel("exec");
            
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);

            channel.connect(3000);  
        }
        catch(Exception e){
          System.err.println(e);
        }
    }
    
    public static abstract class MyUserInfo implements UserInfo, UIKeyboardInteractive{
        public String getPassword(){ return null; }
        public boolean promptYesNo(String str){ return false; }
        public String getPassphrase(){ return null; }
        public boolean promptPassphrase(String message){ return false; }
        public boolean promptPassword(String message){ return false; }
        public void showMessage(String message){ }
        public String[] promptKeyboardInteractive(String destination,
                                                  String name,
                                                  String instruction,
                                                  String[] prompt,
                                                  boolean[] echo){return null;}
    }
    public static void Main() {
    
    }
}
 
