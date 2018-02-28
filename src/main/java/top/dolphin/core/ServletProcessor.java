package top.dolphin.core;

import top.dolphin.constant.Constants;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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

    public void process(Request request, Response response) {

        //get servletName
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);

        //to load Servlet file
        URLClassLoader loader = null;

        try {
            //locate servlet file
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            // the forming of repository is taken from the createClassLoader method in
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString() ;
            // the code for forming the URL is taken from the addRepository method in
            urls[0] = new URL(null, repository, streamHandler);
            // create a URLClassLoader
            loader = new URLClassLoader(urls);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //servlet class
        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        Servlet servlet = null;
        RequestFacade requestFacade = new RequestFacade(request);
        ResponseFacade responseFacade = new ResponseFacade(response);
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service((ServletRequest) request, (ServletResponse) response);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        catch (Throwable e) {
            System.out.println(e.toString());
        }

    }
}
