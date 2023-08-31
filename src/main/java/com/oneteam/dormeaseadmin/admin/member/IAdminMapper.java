package com.oneteam.dormeaseadmin.admin.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminMapper {

    public boolean selectDuplicateByID(String id);
    public int createAccountConfirm(AdminDto adminDto);
    public AdminDto loginAccountConfirm(String id);


}
