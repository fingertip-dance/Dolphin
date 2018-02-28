package top.dolphin.core;

import top.dolphin.constant.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by GuanQingqi
 * Time 2018/2/27 15:18
 * Email cyanqi.guan@gmail.com
 * description
 *
 */
public class HttpServer {

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.process();
    }

    public void process(){
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
            InputStream input = null;
            OutputStream output = null;
            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                Request request = new Request(input);
                request.prase();

                Response response = new Response(output);
                response.setRequest(request);

                //a request for a servlet begins with "/servlet/"
                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor processor = new ServletProcessor();
                    processor.process(request, response);
                }
                else {
                    //static file
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }

    }

}
