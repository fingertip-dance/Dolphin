package top.dolphin.core;

import java.io.IOException;

/**
 * Created by Cyanqi Guan
 * Time 2018/2/27 16:50
 * Email cyanqi.guan@gmail.com
 * description
 *    static Resource
 */
public class StaticResourceProcessor {

    //handle
    public void process(Request request, Response response) {
        try {
            response.responseToPage();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
