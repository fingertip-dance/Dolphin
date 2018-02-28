package top.dolphin.connector.http;

import top.dolphin.constant.Constants;
import top.dolphin.core.HttpProcessor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by GuanQingqi
 * Time 2018/2/27 15:18
 * Email cyanqi.guan@gmail.com
 * description
 *
 */
public class HttpConnector implements Runnable{

    private boolean shutdown = false;

    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(Constants.WEB_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);//exit
        }
        //loop waitting request
        while(true){
            Socket socket = null;
            try {
                socket = serverSocket.accept();

                HttpProcessor httpProcessor = new HttpProcessor();
                httpProcessor.process(socket);

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }

    }
}
