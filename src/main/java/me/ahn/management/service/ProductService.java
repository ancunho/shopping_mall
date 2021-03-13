package me.ahn.management.service;

import com.github.pagehelper.PageInfo;
import me.ahn.management.model.TB_PRODUCT;
import me.ahn.management.model.TB_SPEC;

import java.util.List;

public interface ProductService {

    public void insertTB_PRODUCT(TB_PRODUCT tbProduct) throws Exception;
    public void updateTB_PRODUCT(TB_PRODUCT tbProduct) throws Exception;
    public PageInfo selectTB_PRODUCT(TB_PRODUCT tbProduct) throws Exception;
    public TB_PRODUCT selectTB_PRODUCTByPk(TB_PRODUCT tbProduct) throws Exception;

    /**
     * Spec
     */
    public void insertTB_SPEC(TB_SPEC tbSpec) throws Exception;
    public void updateTB_SPECByPk(TB_SPEC tbSpec) throws Exception;
    public List<TB_SPEC> selectTB_SPECByPRODUCT_SEQ(TB_PRODUCT tbProduct) throws Exception;
    public TB_SPEC selectTB_SPECByPk(Integer SPEC_SEQ) throws Exception;
    public void deleteTB_SPECByPk(TB_SPEC tbSpec) throws Exception;

    /************
     * Wechat
     ************/
    public PageInfo selectTB_PRODUCTForWechat(TB_PRODUCT tbProduct) throws Exception;
    public List<TB_PRODUCT> selectTB_PRODUCTForWechatOnList(TB_PRODUCT tbProduct) throws Exception;
    public TB_PRODUCT selectTB_PRODUCTByPkForWechat(TB_PRODUCT tbProduct) throws Exception;
    public List<TB_SPEC> selectTB_SPECByPRODUCT_SEQForWechat(TB_PRODUCT tbProduct) throws Exception;
}
