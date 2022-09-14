package com.kopo.spring.util;

import org.springframework.lang.Nullable;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class NetworkUtil {


    public static String post(String apiUrl, @Nullable Map<String, String> requestHeaders, String postParams) {
        HttpURLConnection con = connect(apiUrl);


        try {
            con.setRequestMethod("POST");

            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            con.setDoOutput(true);
            try (DataOutputStream stream = new DataOutputStream(con.getOutputStream())) {
                stream.write(postParams.getBytes());
                stream.flush();

                System.out.println(stream.toString());

                int responseCode = con.getResponseCode();

                return responseCode == HttpURLConnection.HTTP_OK ? readBody(con.getInputStream()) : readBody(con.getErrorStream());
            } catch (IOException e) {
                throw new RuntimeException("API 요청과 응답 실패", e);
            } finally {
                con.disconnect();
            }

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpURLConnection connect(final String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL 이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader bufferedReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder("");
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();

        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다", e);
        }
    }
}
