package me.ahn.management.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.model.TB_PRODUCT;
import me.ahn.management.model.TB_SPEC;
import me.ahn.management.service.ProductService;
import org.apache.ibatis.session.SqlSession;
import org.bouncycastle.jce.exception.ExtCertificateEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SqlSession sqlSession;

    @Transactional
    public void insertTB_PRODUCT(TB_PRODUCT tbProduct) throws Exception {
        sqlSession.insert("AHNSTUDIO.product.insertTB_PRODUCT", tbProduct);
    }

    @Transactional
    public void updateTB_PRODUCT(TB_PRODUCT tbProduct) throws Exception {
        sqlSession.update("AHNSTUDIO.product.updateTB_PRODUCT", tbProduct);
    }

    public PageInfo selectTB_PRODUCT(TB_PRODUCT tbProduct) throws Exception {
        PageHelper.startPage(tbProduct.getPAGE_NUM(), tbProduct.getPAGE_SIZE());
        List<TB_PRODUCT> lstTB_PRODUCT = sqlSession.selectList("AHNSTUDIO.product.selectTB_PRODUCT", tbProduct);
        PageInfo pageInfo = new PageInfo(lstTB_PRODUCT);
        pageInfo.setList(lstTB_PRODUCT);
        return pageInfo;
    }

    public TB_PRODUCT selectTB_PRODUCTByPk(TB_PRODUCT tbProduct) throws Exception {
        return sqlSession.selectOne("AHNSTUDIO.product.selectTB_PRODUCTByPk", tbProduct);
    }

    @Transactional
    public void insertTB_SPEC(TB_SPEC tbSpec) throws Exception {
        sqlSession.insert("AHNSTUDIO.product.insertTB_SPEC", tbSpec);
    }

    @Transactional
    public void updateTB_SPECByPk(TB_SPEC tbSpec) throws Exception {
        sqlSession.update("AHNSTUDIO.product.updateTB_SPECByPk", tbSpec);
    }

    public TB_SPEC selectTB_SPECByPk(Integer SPEC_SEQ) throws Exception {
        return sqlSession.selectOne("AHNSTUDIO.product.selectTB_SPECByPk", SPEC_SEQ);
    }

    public List<TB_SPEC> selectTB_SPECByPRODUCT_SEQ(TB_PRODUCT tbProduct) throws Exception {
        return sqlSession.selectList("AHNSTUDIO.product.selectTB_SPECByPRODUCT_SEQ", tbProduct);
    }

    @Transactional
    public void deleteTB_SPECByPk(TB_SPEC tbSpec) throws Exception {
        sqlSession.delete("AHNSTUDIO.product.deleteTB_SPECByPk", tbSpec);
    }


    /*********************************************
     * Wechat
     ********************************************/

    public PageInfo selectTB_PRODUCTForWechat(TB_PRODUCT tbProduct) throws Exception {
        PageHelper.startPage(tbProduct.getPAGE_NUM(), tbProduct.getPAGE_SIZE());
        List<TB_PRODUCT> lstTB_PRODUCT = sqlSession.selectList("AHNSTUDIO.product.selectTB_PRODUCTForWechat", tbProduct);
        PageInfo pageInfo = new PageInfo(lstTB_PRODUCT);
        pageInfo.setList(lstTB_PRODUCT);
        return pageInfo;
    }

    public List<TB_PRODUCT> selectTB_PRODUCTForWechatOnList(TB_PRODUCT tbProduct) throws Exception {
        List<TB_PRODUCT> lstTB_PRODUCT = sqlSession.selectList("AHNSTUDIO.product.selectTB_PRODUCTForWechat", tbProduct);
        return lstTB_PRODUCT;
    }

    public TB_PRODUCT selectTB_PRODUCTByPkForWechat(TB_PRODUCT tbProduct) throws Exception {
        return sqlSession.selectOne("AHNSTUDIO.product.selectTB_PRODUCTByPkForWechat", tbProduct);
    }

    public List<TB_SPEC> selectTB_SPECByPRODUCT_SEQForWechat(TB_PRODUCT tbProduct) throws Exception {
        return sqlSession.selectList("AHNSTUDIO.product.selectTB_SPECByPRODUCT_SEQForWechat", tbProduct);
    }

}
