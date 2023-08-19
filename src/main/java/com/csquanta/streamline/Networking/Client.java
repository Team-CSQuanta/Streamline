//package com.csquanta.streamline.Networking;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Scanner;
//
//public class Client {
//
//    public Client(String serverAddress, int serverPort) {
//        try {
//
//           String clientEmail = loadClientInfoFromFile();
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
//    private String loadClientInfoFromFile() {
//        String email =null;
//        try (BufferedReader reader = new BufferedReader(new FileReader("client_info.txt"))) {
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length >= 2) {
//                    String name = parts[0];
//                      email = parts[1];
//
//
//                } else {
//                    System.err.println("Invalid line format: " + line);
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading client info file: " + e.getMessage());
//        }
//        return email;
//    }
//
//}
//
