package com.asd.framework.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GetClient<T> extends AbstractClient<T>{
    public  GetClient(Method method){
        this.method = method;
    }

    public Object execute(String uri, Class clazz) {
        Object obj = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            if (method.equals(Method.GET_ALL)){
                conn.setRequestMethod(Method.GET.toString());
            }else {
                conn.setRequestMethod(method.toString());
            }

            conn.setRequestProperty("Content-Type", "application/json");

            headerMap.forEach((k, v) -> conn.setRequestProperty(k, v));

            ObjectMapper objectMapper = new ObjectMapper();

            Boolean isSuccess = false;
            for (Integer codes : successCodes) {
                if (codes == conn.getResponseCode()) {
                    isSuccess = true;
                    break;
                }
            }
            System.out.println("isSuccess:"+isSuccess);
            if (!isSuccess) {
                obj = "Failed : HTTP error code :" + conn.getResponseCode();
                /*throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());*/
                System.out.println("Error:"+obj);
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

                StringBuilder response = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    response.append(output);
                }

                System.out.println("Response:" + response.toString());

                if (method.equals(Method.GET_ALL)) {
                    obj = objectMapper.readValue(response.toString(),
                        TypeFactory.defaultInstance().constructCollectionType(List.class,
                            clazz));
                } else {
                    obj = objectMapper.readValue(response.toString(), clazz);
                }
            }
            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return obj;
    }
}
