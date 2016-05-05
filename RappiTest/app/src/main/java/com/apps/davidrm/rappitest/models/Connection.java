package com.apps.davidrm.rappitest.models;

import com.apps.davidrm.rappitest.utils.Constants;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by DRM on 02/05/16.
 */
public class Connection {


    public static String ResponseService(int numItems){



        String CODE = "";
        InputStream in;
        HttpsURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(Constants.BASEURL+numItems+"/json");
            urlConnection = (HttpsURLConnection) urlToRequest.openConnection();

            // handle POST parameters
            urlConnection.setDoOutput(false);
            urlConnection.setRequestMethod("GET");

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == 200) {
                in = new BufferedInputStream(urlConnection.getInputStream());
                CODE = getStringFromInputStream(in);

            } else {
                in=new BufferedInputStream(urlConnection.getErrorStream());
                CODE = String.valueOf(statusCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return CODE;

    }

    public static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
