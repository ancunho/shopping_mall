package me.ahn.management.service;

import com.github.pagehelper.PageInfo;
import me.ahn.management.model.TB_USER_INFO;

public interface UserInfoService {

    public TB_USER_INFO login(String USERNAME, String PASSWORD) throws Exception;
    public TB_USER_INFO selectTB_USER_INFOByPk(Integer USER_SEQ) throws Exception;
    public void updateTB_USER_INFO(TB_USER_INFO tbUserInfo) throws Exception;
    public void deleteTB_USER_INFO(TB_USER_INFO tbUserInfo) throws Exception;
    public TB_USER_INFO countTB_USER_INFOByUSERNAME(TB_USER_INFO tbUserInfo) throws Exception;
    public TB_USER_INFO countTB_USER_INFOByEMAIL(TB_USER_INFO tbUserInfo) throws Exception;
    public PageInfo selectTB_USER_INFO(TB_USER_INFO tbUserInfo) throws Exception;
    public void insertTB_USER_INFO(TB_USER_INFO tbUserInfo) throws Exception;
    public void insertTB_USER_INFOForWechat(TB_USER_INFO tbUserInfo) throws Exception;
    public Integer countTB_USER_INFOByUSERNAMERETURNInt(TB_USER_INFO tbUserInfo) throws Exception;

}
