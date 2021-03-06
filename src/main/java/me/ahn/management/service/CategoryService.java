package me.ahn.management.service;

import me.ahn.management.model.TB_CATEGORY;

import java.util.List;

public interface CategoryService {

    public void insertTB_CATEGORY(TB_CATEGORY tbCategory) throws Exception;
    public void updateTB_CATEGORY(TB_CATEGORY tbCategory) throws Exception;
    public void deleteTB_CATEGORYByPk(TB_CATEGORY tbCategory) throws Exception;
    public Integer countTB_CATEGORYByCATEGORY_NAME(TB_CATEGORY tbCategory) throws Exception;
    public TB_CATEGORY selectTB_CATEGORYByPk(TB_CATEGORY tbCategory) throws Exception;
    public List<TB_CATEGORY> selectTB_CATEGORYByPARENT_SEQ0(TB_CATEGORY tbCategory) throws Exception;
    public List<TB_CATEGORY> selectTB_CATEGORY(TB_CATEGORY tbCategory) throws Exception;
    public List<TB_CATEGORY> selectTB_CATEGORYByPARENT_SEQ(TB_CATEGORY tbCategory) throws Exception;

    public List<Integer> selectTB_CATEGORYAndChidren(Integer CATEGORY_SEQ) throws Exception;

    public List<TB_CATEGORY> selectTB_CATEGORYForWechat(TB_CATEGORY tbCategory) throws Exception;
}
