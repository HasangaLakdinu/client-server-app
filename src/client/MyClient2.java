package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient2 {
    public static void main(String[] args)
    {
        try
        {
            Socket socket = new Socket("127.0.0.1", 9999);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader con = new BufferedReader(new InputStreamReader(System.in));
            String line;
            do
            {
                line = bufferedReader.readLine();
                if ( line != null )
                    System.out.println(line);
                line = con.readLine();
                printWriter.println(line);
            }
            while ( !line.trim().equals("bye") );
        }
        catch (Exception err)
        {
            System.err.println(err);
        }
    }
}
