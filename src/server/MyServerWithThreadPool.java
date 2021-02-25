package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MyServerWithThreadPool {
    private ServerSocket server;

    public MyServerWithThreadPool(int portnum) {
        try {
            server = new ServerSocket(portnum);
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void serve() {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            while (true) {
                Socket client = server.accept();
                RequestProcessorThread requestProcessor= new RequestProcessorThread(client);
                System.out.println("RequestProcessor created and handed over the connection. "
                + "Thr ["+requestProcessor.toString()+"] Soc ["+ client.toString()+"]");
                executorService.execute(requestProcessor);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public static void main(String[] args) {
        MyServerWithThreadPool myServerWithThreadPool = new MyServerWithThreadPool(9999);
        try {
            myServerWithThreadPool.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
