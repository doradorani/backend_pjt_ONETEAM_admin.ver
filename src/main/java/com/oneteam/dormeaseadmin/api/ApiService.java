package com.oneteam.dormeaseadmin.api;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class ApiService {

    @Autowired
    IApiMapper apiMapper;

    public void conversion(String result) {
        log.info("conversion()");

        List<SchoolInfoDto> schoolInfoDtos = new ArrayList<>();
        SchoolInfoDto schoolInfoDto = null;

        try {

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

            JSONArray array = (JSONArray) jsonObj.get("schoolInfo");
            JSONObject jObj = (JSONObject) array.get(1);

            JSONArray row = (JSONArray) jObj.get("row");

            for (int i = 0; i < row.size(); i++) {
                JSONObject obj = (JSONObject) row.get(i);

                schoolInfoDto = new SchoolInfoDto();
                schoolInfoDto.setAtpt_code((String) obj.get("ATPT_OFCDC_SC_CODE"));
                schoolInfoDto.setAtpt_name((String) obj.get("SD_SCHUL_CODE"));
                schoolInfoDto.setSchool_code((String) obj.get("HS_SC_NM"));
                schoolInfoDto.setSchool_name((String) obj.get("FOAS_MEMRD"));

                System.out.println("a==>" + schoolInfoDto);

                schoolInfoDtos.add(schoolInfoDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("b==>" + schoolInfoDtos);

        int insert_result = apiMapper.insertSchoolData(schoolInfoDtos);

        System.out.println("insert_result => " + insert_result);
    }
}
