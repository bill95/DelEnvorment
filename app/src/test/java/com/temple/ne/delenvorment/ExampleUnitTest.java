package com.temple.ne.delenvorment;

import android.net.Uri;

import com.temple.ne.basecomponent.retrofit.Api;
import com.temple.ne.basecomponent.retrofit.ApiImpl;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        final Api api = new ApiImpl();
        final Class<?>[] interfaces = new Class[]{Api.class};
        Api o = (Api) Proxy.newProxyInstance(Api.class.getClassLoader(), interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                method.invoke(api, objects);
                return null;
            }
        });
        o.api();
//        URL url=new URL("");
//        HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
//        urlConnection.connect();
//        urlConnection.getResponseCode()
//        OutputStream outputStream = urlConnection.getOutputStream();
//        outputStream.


    }

}