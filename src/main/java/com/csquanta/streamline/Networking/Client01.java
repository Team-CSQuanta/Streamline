//package com.csquanta.streamline.Networking;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class Client01 {
//
//    public Client01(String serverAddress, int serverPort) {
//        try {
//
//            String clientEmail = loadClientInfoFromFile();
//            NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
//            networkUtil.write(clientEmail);
//
//            ReadThreadClient readThreadClient = new ReadThreadClient(networkUtil, clientEmail);
//            WriteThreadClient writeThreadClient = new WriteThreadClient(networkUtil, clientEmail);
//
//            readThreadClient.start();
//            writeThreadClient.start();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    public static void main(String args[]) {
//        String serverAddress = "127.0.0.1";
//        int serverPort = 8000;
//        Client client = new Client(serverAddress, serverPort);
//    }
//
//
//
//
//}
//
