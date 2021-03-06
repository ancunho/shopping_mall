package me.ahn.management.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.ahn.management.model.TB_COMMON_CODE;
import me.ahn.management.service.CommonService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private SqlSession sqlSession;

    public List<TB_COMMON_CODE> selectTB_COMMON_CODEByCODE_ID(TB_COMMON_CODE tbCommonCode) {
        return sqlSession.selectList("AHNSTUDIO.common.selectTB_COMMON_CODEByCODE_ID", tbCommonCode);
    }

    public List<TB_COMMON_CODE> selectTB_COMMON_CODEByOPTION01(TB_COMMON_CODE tbCommonCode) {
        return sqlSession.selectList("AHNSTUDIO.common.selectTB_COMMON_CODEByOPTION01", tbCommonCode);
    }
}