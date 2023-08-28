package com.oneteam.dormeaseadmin.admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminMapper {

    public int createAccountConfirm(AdminDto adminDto);

}
