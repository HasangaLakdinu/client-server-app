package server;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class RequestProcessorThread implements Runnable{
    private Socket socClient;

    public RequestProcessorThread(Socket soc){
        socClient = soc;
    }

    @Override
    public void run() {
        System.out.println(this.toString()+"Thread Started. Processing client"+socClient);
        try {
            InputStream inputStream = socClient.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter printWriter = new PrintWriter(socClient.getOutputStream(), true);
            printWriter.println("Welcome to the Java EchoServer.  Type 'bye' to close.");
            String line;
            do {
                line = bufferedReader.readLine();
                if (line != null)
                    printWriter.println("Got: " + line);
            }
            while (!line.trim().equals("bye"));
            socClient.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        System.out.println(this.toString()+" : Thread exiting .. ");
    }
}
