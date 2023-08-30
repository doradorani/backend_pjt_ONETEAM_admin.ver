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

    public int countSchoolInfo(String result) {
        log.info("countSchoolInfo()");

        List<SchoolInfoDto> schoolInfoDtos = new ArrayList<>();
        SchoolInfoDto schoolInfoDto = null;
        int count = 0;

        try {

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

            JSONArray data = (JSONArray) jsonObj.get("schoolInfo");
            JSONObject getRow = (JSONObject) data.get(0);

            JSONArray getArray = (JSONArray) getRow.get("head");
            JSONObject getCountArray = (JSONObject) getArray.get(0);

            count = (int) ((long) getCountArray.get("list_total_count"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    //테이블의 학교 수
    public int countTable() {
        log.info("countTable()");

        return apiMapper.countTable();
    }

    public void insertSchoolInfo(String result) {
        log.info("insertSchoolInfo()");

        List<SchoolInfoDto> schoolInfoDtos = new ArrayList<>();
        SchoolInfoDto schoolInfoDto = null;

        try {

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

            JSONArray array = (JSONArray) jsonObj.get("schoolInfo");

            JSONObject jObj = (JSONObject) array.get(1);

            JSONArray row = (JSONArray) jObj.get("row");

            for (int i = 0; i < row.size()-1; i++) {
                JSONObject obj = (JSONObject) row.get(i);

                schoolInfoDto = new SchoolInfoDto();
                schoolInfoDto.setAtpt_code((String) obj.get("ATPT_OFCDC_SC_CODE"));
                schoolInfoDto.setAtpt_name((String) obj.get("ATPT_OFCDC_SC_NM"));
                schoolInfoDto.setZip_code((String) obj.get("ORG_RDNZC"));
                schoolInfoDto.setSchool_code((String) obj.get("SD_SCHUL_CODE"));
                schoolInfoDto.setSchool_name((String) obj.get("SCHUL_NM"));
                schoolInfoDto.setSchool_knd((String) obj.get("SCHUL_KND_SC_NM"));
                schoolInfoDto.setStatus(1);

                schoolInfoDtos.add(schoolInfoDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        apiMapper.insertSchoolData(schoolInfoDtos);
    }


    public int updateSchoolInfoStatusFalse() {
        log.info("updateSchoolInfoStatusFalse()");

        return apiMapper.updateSchoolInfoStatusFalse();
    }


    public void updateSchoolInfo(String result) {
        log.info("updateSchoolInfo()");

        List<SchoolInfoDto> schoolInfoDtos = new ArrayList<>();
        SchoolInfoDto schoolInfoDto = null;

        try {

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

            JSONArray array = (JSONArray) jsonObj.get("schoolInfo");

            JSONObject jObj = (JSONObject) array.get(1);

            JSONArray row = (JSONArray) jObj.get("row");

            for (int i = 0; i < row.size()-1; i++) {
                JSONObject obj = (JSONObject) row.get(i);

                schoolInfoDto = new SchoolInfoDto();
                schoolInfoDto.setAtpt_code((String) obj.get("ATPT_OFCDC_SC_CODE"));
                schoolInfoDto.setAtpt_name((String) obj.get("ATPT_OFCDC_SC_NM"));
                schoolInfoDto.setZip_code((String) obj.get("ORG_RDNZC"));
                schoolInfoDto.setSchool_code((String) obj.get("SD_SCHUL_CODE"));
                schoolInfoDto.setSchool_name((String) obj.get("SCHUL_NM"));
                schoolInfoDto.setSchool_knd((String) obj.get("SCHUL_KND_SC_NM"));
                schoolInfoDto.setStatus(1);



                schoolInfoDtos.add(schoolInfoDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        apiMapper.updateSchoolData(schoolInfoDtos);
    }
}
