package server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    private ServerSocket server;

    public MyServer(int portnum) {
        try {
            server = new ServerSocket(portnum);
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void serve() {
        try {
            while (true) {
                Socket client = server.accept();
                RequestProcessorThread thread = new RequestProcessorThread(client);
                Thread t =  new Thread(thread);
                t.start();
            }
        } catch (Exception err) {
            System.err.println(err);
        }
    }

    public static void main(String[] args) {
        MyServer s = new MyServer(9999);
        s.serve();
    }
}
