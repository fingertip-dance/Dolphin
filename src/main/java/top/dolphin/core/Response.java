package top.dolphin.core;

import top.dolphin.constant.Constants;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * Created by Cyanqi Guan
 * Time 2018/2/27 16:00
 * Email cyanqi.guan@gmail.com
 * description
 */
public class Response implements ServletResponse{

    private static final int BUFFER_SIZE = 1024;
    private OutputStream output;
    private Request request;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    //send request file content to brower
    public void responseToPage(){
        File file = new File(Constants.WEB_ROOT, request.uri);
        FileInputStream fis = null;
        try {
            if(file.exists()){
                fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int len = fis.read(buffer, 0, BUFFER_SIZE);
                while(len!=-1){
                    output.write(buffer,0,len);
                    len = fis.read(buffer, 0, BUFFER_SIZE);
                }
            }else{
                // file not found
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis!=null)
                   fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /* implementation of the ServletResponse*/
    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        return null;
    }

    public void setCharacterEncoding(String s) {

    }

    public void setContentLength(int i) {

    }

    public void setContentType(String s) {

    }

    public void setBufferSize(int i) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale locale) {

    }

    public Locale getLocale() {
        return null;
    }
}
