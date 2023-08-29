package com.oneteam.dormeaseadmin.admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminMapper {

    public int isAdmin(String id);
    public int createAccountConfirm(AdminDto adminDto);
    public AdminDto loginAccountConfirm(String id);
}
