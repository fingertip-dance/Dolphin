package top.dolphin.core;

import top.dolphin.connector.http.HttpRequest;
import top.dolphin.connector.http.HttpRequestFacade;
import top.dolphin.connector.http.HttpResponse;
import top.dolphin.connector.http.HttpResponseFacade;
import top.dolphin.constant.Constants;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by Cyanqi Guan
 * Time 2018/2/27 16:50
 * Email cyanqi.guan@gmail.com
 * description
 *    Servlet processor.get Servelt Name and use UrlClassLoad to load class to new instance call service method,
 * Servlet is also a java file.
 */
public class ServletProcessor {

    public void process(HttpRequest request, HttpResponse response) {

        //get servlet name
        String uri = request.getRequestURI();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);

        //classloader
        URLClassLoader loader = null;
        try {
            // create a URLClassLoader
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString() ;
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        }
        catch (IOException e) {
            System.out.println(e.toString() );
        }
        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        Servlet servlet = null;

        try {
            servlet = (Servlet) myClass.newInstance();
            HttpRequestFacade requestFacade = new HttpRequestFacade(request);
            HttpResponseFacade responseFacade = new HttpResponseFacade(response);
            servlet.service(requestFacade, responseFacade);
            ((HttpResponse) response).finishResponse();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        catch (Throwable e) {
            System.out.println(e.toString());
        }
    }
}
