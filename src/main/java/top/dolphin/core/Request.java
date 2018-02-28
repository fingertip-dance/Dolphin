package top.dolphin.core;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Cyanqi Guan
 * Time 2018/2/27 15:38
 * Email cyanqi.guan@gmail.com
 * description
 */
public class Request implements ServletRequest{
    public InputStream input;
    public String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public String getUri() {
        return uri;
    }

    //prase request to get uri
    public void prase(){

        StringBuffer requestString = new StringBuffer();

        byte[] buffer = new byte[1024];
        int len = 0;
        try {
             len = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<len;++i){
            requestString.append((char) buffer[i]);
        }
        System.out.println("request content:");
        System.out.println(requestString.toString());

        //get request uri
        this.uri = this.praseUri(requestString.toString());
    }

    //prase http request to get uri
    /*
    GET /index.html HTTP/1.1
    Host: localhost:8080
    User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0
    Accept: text/html,application/xhtml+xml,application/xml;q=0.9,;q=0.8
    Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
    Accept-Encoding: gzip, deflate
    Connection: keep-alive
    Upgrade-Insecure-Requests: 1
    Cache-Control: max-age=0
    */
    private String praseUri(String requestString) {
        if(!"".equals(requestString)&&null!=requestString){
            int index1,index2;
            index1 = requestString.indexOf(' ');
            if(index1 != -1) {
                index2 = requestString.indexOf(' ', index1 + 1);
                if (index2 > index1) {
                    return requestString.substring(index1, index2);
                }
            }
        }
        return null;
    }

    /* implementation of the ServletRequest*/
    public Object getAttribute(String s) {
        return null;
    }

    public Enumeration getAttributeNames() {
        return null;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

    }

    public int getContentLength() {
        return 0;
    }

    public String getContentType() {
        return null;
    }

    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    public String getParameter(String s) {
        return null;
    }

    public Enumeration getParameterNames() {
        return null;
    }

    public String[] getParameterValues(String s) {
        return new String[0];
    }

    public Map getParameterMap() {
        return null;
    }

    public String getProtocol() {
        return null;
    }

    public String getScheme() {
        return null;
    }

    public String getServerName() {
        return null;
    }

    public int getServerPort() {
        return 0;
    }

    public BufferedReader getReader() throws IOException {
        return null;
    }

    public String getRemoteAddr() {
        return null;
    }

    public String getRemoteHost() {
        return null;
    }

    public void setAttribute(String s, Object o) {

    }

    public void removeAttribute(String s) {

    }

    public Locale getLocale() {
        return null;
    }

    public Enumeration getLocales() {
        return null;
    }

    public boolean isSecure() {
        return false;
    }

    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    public String getRealPath(String s) {
        return null;
    }

    public int getRemotePort() {
        return 0;
    }

    public String getLocalName() {
        return null;
    }

    public String getLocalAddr() {
        return null;
    }

    public int getLocalPort() {
        return 0;
    }
}
