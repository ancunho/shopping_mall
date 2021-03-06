package me.ahn.management.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.model.TB_CATEGORY;
import me.ahn.management.service.CategoryService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private SqlSession sqlSession;

    @Transactional
    public void insertTB_CATEGORY(TB_CATEGORY tbCategory) throws Exception {
        sqlSession.insert("AHNSTUDIO.category.insertTB_CATEGORY", tbCategory);
    }

    @Transactional
    public void updateTB_CATEGORY(TB_CATEGORY tbCategory) throws Exception {
        sqlSession.update("AHNSTUDIO.category.updateTB_CATEGORY", tbCategory);
    }

    @Transactional
    public void deleteTB_CATEGORYByPk(TB_CATEGORY tbCategory) throws Exception {
        sqlSession.delete("AHNSTUDIO.category.deleteTB_CATEGORYByPk", tbCategory);
    }

    public Integer countTB_CATEGORYByCATEGORY_NAME(TB_CATEGORY tbCategory) throws Exception {
        return sqlSession.selectOne("AHNSTUDIO.category.countTB_CATEGORYByCATEGORY_NAME", tbCategory);
    }

    public TB_CATEGORY selectTB_CATEGORYByPk(TB_CATEGORY tbCategory) throws Exception {
        return sqlSession.selectOne("AHNSTUDIO.category.selectTB_CATEGORYByPk", tbCategory);
    }

    public List<TB_CATEGORY> selectTB_CATEGORYByPARENT_SEQ0(TB_CATEGORY tbCategory) throws Exception {
        return sqlSession.selectList("AHNSTUDIO.category.selectTB_CATEGORYByPARENT_SEQ0", tbCategory);
    }

    public List<TB_CATEGORY> selectTB_CATEGORY(TB_CATEGORY tbCategory) throws Exception {
        return sqlSession.selectList("AHNSTUDIO.category.selectTB_CATEGORY", tbCategory);
    }

    public List<TB_CATEGORY> selectTB_CATEGORYByPARENT_SEQ(TB_CATEGORY tbCategory) throws Exception {
        return sqlSession.selectList("AHNSTUDIO.category.selectTB_CATEGORYByPARENT_SEQ", tbCategory);
    }

    public List<Integer> selectTB_CATEGORYAndChidren(Integer CATEGORY_SEQ) throws Exception {
        Set<TB_CATEGORY> categorySet =Sets.newHashSet();
        findChildCategory(categorySet,CATEGORY_SEQ);

        List<Integer> lstCATEGORY_SEQ =Lists.newArrayList();
        if (CATEGORY_SEQ != null) {
            for(TB_CATEGORY tbCategoryItem : categorySet) {
                lstCATEGORY_SEQ.add(tbCategoryItem.getCATEGORY_SEQ());
            }
        }

        return lstCATEGORY_SEQ;
    }

    private Set<TB_CATEGORY> findChildCategory(Set<TB_CATEGORY> categorySet, Integer CATEGORY_SEQ) {
        TB_CATEGORY tbCategoryNew = sqlSession.selectOne("AHNSTUDIO.category.selectTB_CATEGORYByPkForSet", CATEGORY_SEQ);
        if (tbCategoryNew != null) {
            categorySet.add(tbCategoryNew);
        }

        List<TB_CATEGORY> lstTB_CATEGORY = sqlSession.selectList("AHNSTUDIO.category.selectTB_CATEGORYByPARENT_SEQForSet", CATEGORY_SEQ);
        for(TB_CATEGORY categoryItem : lstTB_CATEGORY) {
            findChildCategory(categorySet, categoryItem.getCATEGORY_SEQ());
        }

        return categorySet;
    }

    public List<TB_CATEGORY> selectTB_CATEGORYForWechat(TB_CATEGORY tbCategory) throws Exception {
        return sqlSession.selectList("AHNSTUDIO.category.selectTB_CATEGORYForWechat", tbCategory);
    }

}
