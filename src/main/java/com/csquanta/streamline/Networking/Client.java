package com.csquanta.streamline.Networking;

import java.util.Scanner;

public class Client {

    public Client(String serverAddress, int serverPort) {
        try {
            System.out.print("Enter client Email address: ");
            Scanner scanner = new Scanner(System.in);
            String email = scanner.nextLine();

            NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(email);

            ReadThreadClient readThreadClient = new ReadThreadClient(networkUtil, email);
            WriteThreadClient writeThreadClient = new WriteThreadClient(networkUtil, email);

            readThreadClient.start();
            writeThreadClient.start();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 8000;
        Client client = new Client(serverAddress, serverPort);
    }
}

