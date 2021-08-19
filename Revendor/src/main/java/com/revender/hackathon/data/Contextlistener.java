package com.revender.hackathon.data;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Contextlistener implements ServletContextListener {
    private SSHConnection sshConnection;

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            sshConnection = new SSHConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sshConnection.closeSSH();
    }
}

//package com.revender.hackathon.data;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
//
//@WebListener
//public class Contextlistener implements ServletContextListener {
//    static Session SSH_SESSION;
//    static JSch jsch = new JSch();
//
//    @Override
//    public void contextDestroyed(ServletContextEvent arg0) {
//        if (SSH_SESSION != null) {
//            SSH_SESSION.disconnect();
//        }
//        if (jsch != null) {
//            jsch = null;
//        }
//    }
//
//    @Override
//    public void contextInitialized(ServletContextEvent arg0) {
//
//        java.util.Properties config = new java.util.Properties();
//        config.put("StrictHostKeyChecking", "no");
//
//        try {
//            SSH_SESSION = jsch.getSession("fintech5", "127.0.0.1", 3306);
//            SSH_SESSION.setPassword("fintech5");
//            SSH_SESSION.setConfig(config);
//            SSH_SESSION.connect();
//            SSH_SESSION.setPortForwardingL(3333, "127.0.0.1", 3306);
//        } catch (JSchException e) {
//            e.printStackTrace();
//        }
//    }
//}