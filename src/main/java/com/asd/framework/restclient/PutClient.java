package com.asd.framework.restclient;

import com.asd.framework.error.ErrorMessage;
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

public class PutClient<T> extends AbstractClient<T>{
    public  PutClient(Method method){
        this.method = method;
    }

    public Object execute(String uri, Class clazz) {
        Object obj = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
                conn.setRequestMethod(method.toString());

            conn.setRequestProperty("Content-Type", "application/json");

            headerMap.forEach((k, v) -> conn.setRequestProperty(k, v));

            ObjectMapper objectMapper = new ObjectMapper();
            if (data != null) {
                String input = objectMapper.writeValueAsString(data);
                OutputStream os = conn.getOutputStream();
                os.write(input.getBytes());
                os.flush();
            }

            Boolean isSuccess = false;
            for (Integer codes : successCodes) {
                if (codes == conn.getResponseCode()) {
                    isSuccess = true;
                    break;
                }
            }
            System.out.println("isSuccess:"+isSuccess);
            BufferedReader br = null;
            if (isSuccess){
                br =new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            }else {
                br =new BufferedReader(new InputStreamReader(
                    (conn.getErrorStream())));
            }
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            if (!isSuccess) {
                if (conn.getResponseCode()==406){
                    obj = objectMapper.readValue(response.toString(),
                        TypeFactory.defaultInstance().constructCollectionType(List.class,
                            ErrorMessage.class));
                }else {
                    obj = "Failed : HTTP error code :" + conn.getResponseCode();
                }
                /*throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());*/
                System.out.println("Error:"+obj);
            } else {
                obj = objectMapper.readValue(response.toString(), clazz);
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
