package com.revender.hackathon.data;

import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHConnection {
    private final static String HOST = "127.0.0.1";
    private final static Integer PORT = 3306;
    private final static String SSH_USER = "fintech5";
    private final static String SSH_PW = "fintech5";

    private Session session;

    public void closeSSH(){
        session.disconnect();
    }

    public SSHConnection() {
        try {
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session = jsch.getSession(SSH_USER, HOST, PORT);
            session.setPassword(SSH_PW);
            session.setConfig(config);
            session.connect();
            session.setPortForwardingL(3306, "221.168.38.225", 3333);
        } catch(JSchException e){
            e.printStackTrace();
        }
    }
}