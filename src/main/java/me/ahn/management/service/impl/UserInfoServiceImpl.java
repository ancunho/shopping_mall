package me.ahn.management.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.TB_USER_INFO;
import me.ahn.management.service.UserInfoService;
import me.ahn.management.util.MD5Util;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private SqlSession sqlSession;

    public TB_USER_INFO login(String USERNAME, String PASSWORD) throws Exception {
        TB_USER_INFO tbUserInfo = new TB_USER_INFO();
        tbUserInfo.setUSERNAME(USERNAME);
        tbUserInfo.setPASSWORD(MD5Util.MD5EncodeUtf8(PASSWORD));
        return sqlSession.selectOne("AHNSTUDIO.user.selectTB_USER_INFOForLogin", tbUserInfo);
    }

    public TB_USER_INFO selectTB_USER_INFOByPk(Integer USER_SEQ) throws Exception {
        return sqlSession.selectOne("AHNSTUDIO.user.selectTB_USER_INFOByPk", USER_SEQ);
    }

    @Transactional
    public void updateTB_USER_INFO(TB_USER_INFO tbUserInfo) throws Exception {
        sqlSession.update("AHNSTUDIO.user.updateTB_USER_INFO", tbUserInfo);
    }

    @Transactional
    public void deleteTB_USER_INFO(TB_USER_INFO tbUserInfo) throws Exception {
        sqlSession.delete("AHNSTUDIO.user.deleteTB_USER_INFO", tbUserInfo);
    }

    public TB_USER_INFO countTB_USER_INFOByUSERNAME(TB_USER_INFO tbUserInfo) throws Exception {
        return sqlSession.selectOne("AHNSTUDIO.user.countTB_USER_INFOByUSERNAME", tbUserInfo);
    }

    public TB_USER_INFO countTB_USER_INFOByEMAIL(TB_USER_INFO tbUserInfo) throws Exception {
        return sqlSession.selectOne("AHNSTUDIO.user.countTB_USER_INFOByEMAIL", tbUserInfo);
    }

    public PageInfo selectTB_USER_INFO(TB_USER_INFO tbUserInfo) throws Exception {
        PageHelper.startPage(tbUserInfo.getPAGE_NUM(), tbUserInfo.getPAGE_SIZE());
        List<TB_USER_INFO> lstTB_USER_INFO = sqlSession.selectList("AHNSTUDIO.user.selectTB_USER_INFO", tbUserInfo);
        PageInfo pageInfo = new PageInfo(lstTB_USER_INFO);
        pageInfo.setList(lstTB_USER_INFO);
        return pageInfo;
    }

    @Transactional
    public void insertTB_USER_INFO(TB_USER_INFO tbUserInfo) throws Exception {
        sqlSession.insert("AHNSTUDIO.user.insertTB_USER_INFO", tbUserInfo);
    }


    /**********************************
     * Miniapp
     *********************************/
    @Transactional
    public void insertTB_USER_INFOForWechat(TB_USER_INFO tbUserInfo) throws Exception {
        sqlSession.insert("AHNSTUDIO.user.insertTB_USER_INFOForWechat", tbUserInfo);
    }

    public Integer countTB_USER_INFOByUSERNAMERETURNInt(TB_USER_INFO tbUserInfo) throws Exception {
        return sqlSession.selectOne("AHNSTUDIO.user.countTB_USER_INFOByUSERNAMERETURNInt", tbUserInfo);
    }



}
