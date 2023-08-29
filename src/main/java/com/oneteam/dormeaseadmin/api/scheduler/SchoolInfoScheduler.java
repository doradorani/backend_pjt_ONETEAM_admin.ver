package com.oneteam.dormeaseadmin.api.scheduler;

import com.oneteam.dormeaseadmin.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class SchoolInfoScheduler {

    @Autowired
    ApiService apiService;

    @Scheduled(cron = "30 41 21 29 8 *")
    public void schoolInfoScheduler(){
        StringBuilder result = new StringBuilder();

        String str = "%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90";        //고등학교 아스키코드

        try {
            String apiUrl = "https://open.neis.go.kr/hub/schoolInfo?" +
                    "KEY=419ec70ec0d54de6bf489ffa5afe25cf" +
                    "&Type=json" +
                    "&pIndex=1" +
                    "&pSize=500" +
                    "&SCHUL_KND_SC_NM=" + str;

            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            BufferedReader br;

            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine);

            }
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        apiService.conversion(result.toString());

    }

}
