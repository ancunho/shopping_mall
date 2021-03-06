package me.ahn.management.service;

import me.ahn.management.model.TB_COMMON_CODE;

import java.util.List;

public interface CommonService {

    public List<TB_COMMON_CODE> selectTB_COMMON_CODEByCODE_ID(TB_COMMON_CODE tbCommonCode);
    public List<TB_COMMON_CODE> selectTB_COMMON_CODEByOPTION01(TB_COMMON_CODE tbCommonCode);


}
