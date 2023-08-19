package com.csquanta.streamline.Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private ServerSocket serverSocket;
    private ConcurrentHashMap<String, NetworkInformation> clientNetworkInformationMap;

    Server() {
        clientNetworkInformationMap = new ConcurrentHashMap<>();
        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("Server has started...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server has accepted a connection...");
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        String clientEmail = (String) networkUtil.read();

        NetworkInformation networkInfo = new NetworkInformation(networkUtil);
        clientNetworkInformationMap.put(clientEmail, networkInfo);

        ReadThreadServer readThreadServer = new ReadThreadServer(clientEmail, networkInfo, clientNetworkInformationMap);
        readThreadServer.start();
    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}