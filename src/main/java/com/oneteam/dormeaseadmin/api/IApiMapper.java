package com.oneteam.dormeaseadmin.api;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IApiMapper {

    public int insertSchoolData(List<SchoolInfoDto> schoolInfoDtos);
}


