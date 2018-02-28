package top.dolphin.core;

import top.dolphin.connector.http.HttpRequest;
import top.dolphin.connector.http.HttpResponse;

/**
 * Created by Cyanqi Guan
 * Time 2018/2/27 16:50
 * Email cyanqi.guan@gmail.com
 * description
 *    static Resource
 */
public class StaticResourceProcessor {

    //handle
    public void process(HttpRequest request, HttpResponse response) {
        try {
            response.sendStaticResource();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
