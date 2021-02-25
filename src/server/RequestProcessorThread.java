package server;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.sql.*;

public class RequestProcessorThread implements Runnable{
    private Socket socClient;
    Connection connection;
    PreparedStatement statement;

    public RequestProcessorThread(Socket soc) throws  Exception{
        socClient = soc;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sample","user","password");
        String sql = "INSERT INTO Message (content) VALUES (?)";
        statement = connection.prepareStatement(sql);
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
                    statement.setString(1,line);
                int rows = statement.executeUpdate();
                if(rows>0){
                    System.out.println("A row has been updated!");
                }

                printWriter.println("Got: " + line);


            }
            while (!line.trim().equals("bye"));
            socClient.close();
            statement.close();
            connection.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println(this.toString()+" : Thread exiting .. ");
    }


}
