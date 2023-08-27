package com.csquanta.streamline.Networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkUtil {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public NetworkUtil(String s, int port) throws IOException {
        this.socket = new Socket(s, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public NetworkUtil(Socket s) throws IOException {
        this.socket = s;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public Object read() throws IOException, ClassNotFoundException {
        return ois.readUnshared();
    }

    public void write(Object o) throws IOException {
        oos.writeUnshared(o);
    }

//    // Method to send image data in chunks
//    public void writeChunk(byte[] data, int length) throws IOException {
//        oos.writeInt(length);
//        oos.write(data, 0, length);
//        oos.flush();
//    }
//
//    // Method to read image data chunks
//    public int readChunk(byte[] buffer) throws IOException {
//        int length = ois.readInt();
//        if (length == -1) {
//            return -1;
//        }
//        ois.readFully(buffer, 0, length);
//        return length;
//    }


    public void closeConnection() throws IOException {
        ois.close();
        oos.close();
    }
}

