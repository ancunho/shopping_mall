package me.ahn.management.controller.backend;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.annotation.UserLoginToken;
import me.ahn.management.common.Const;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.TB_CATEGORY;
import me.ahn.management.service.CategoryService;
import me.ahn.management.util.Box;
import me.ahn.management.util.HttpUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping(value = "/api/category/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @UserLoginToken
    @RequestMapping(value = "proc", method = RequestMethod.POST)
    public ServerResponse procTB_CATEGORY(HttpServletRequest request, @RequestBody TB_CATEGORY tbCategory) {

        try {
            if(StringUtils.isEmpty(tbCategory.getCATEGORY_NAME())) {
                return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
            }

            if (tbCategory.getPARENT_SEQ() == null) {
                tbCategory.setPARENT_SEQ(0);
            }

            if (tbCategory.getCATEGORY_SEQ() == null) {
                int resultCount = categoryService.countTB_CATEGORYByCATEGORY_NAME(tbCategory);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("已存在相同名字的分类");
                }
                categoryService.insertTB_CATEGORY(tbCategory);
            } else {
                categoryService.updateTB_CATEGORY(tbCategory);
            }
            return ServerResponse.createBySuccessMessage(Const.Message.SAVE_OK);


        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SAVE_ERROR);
        }
    }

    @UserLoginToken
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ServerResponse deleteTB_CATEGORYByPk(@RequestBody TB_CATEGORY tbCategory) {
        if (tbCategory == null) {
            return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
        }
        try {
            categoryService.deleteTB_CATEGORYByPk(tbCategory);
            return ServerResponse.createBySuccessMessage(Const.Message.DELETE_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createBySuccessMessage(Const.Message.DELETE_ERROR);
        }
    }

    @UserLoginToken
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public ServerResponse selectTB_CATEGORYByPk(HttpServletRequest request, @RequestBody TB_CATEGORY tbCategory) {
        try {
            tbCategory = categoryService.selectTB_CATEGORYByPk(tbCategory);
            return ServerResponse.createBySuccess(tbCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

    @UserLoginToken
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ServerResponse selectTB_CATEGORY(@RequestBody TB_CATEGORY tbCategory) {
        try {
            List<TB_CATEGORY> tbCategoryList = categoryService.selectTB_CATEGORY(tbCategory);
            return ServerResponse.createBySuccess(tbCategoryList);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

    @UserLoginToken
    @RequestMapping(value = "list/parent0", method = RequestMethod.POST)
    public ServerResponse selectTB_CATEGORYByPARENT_SEQ0(HttpServletRequest request) {
        Box box = HttpUtility.getBox(request);
        TB_CATEGORY tbCategory = new TB_CATEGORY();
        box.copyToEntity(tbCategory);

        try {
            List<TB_CATEGORY> tbCategoryList = categoryService.selectTB_CATEGORYByPARENT_SEQ0(tbCategory);
            return ServerResponse.createBySuccess(tbCategoryList);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

    @UserLoginToken
    @RequestMapping(value = "children", method = RequestMethod.POST)
    public ServerResponse selectChildren(HttpServletRequest request, @RequestBody TB_CATEGORY tbCategory) {
        try {
            if (StringUtils.isEmpty(tbCategory.getPARENT_SEQ().toString())) {
                return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
            }

            List<TB_CATEGORY> resultList = categoryService.selectTB_CATEGORYByPARENT_SEQ(tbCategory);
            return ServerResponse.createBySuccess(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

    @UserLoginToken
    @RequestMapping(value = "deep", method = RequestMethod.POST)
    public ServerResponse selectTB_CATEGORYAndChidren(Integer CATEGORY_SEQ) {
        TB_CATEGORY tbCategory = new TB_CATEGORY();
        tbCategory.setCATEGORY_SEQ(CATEGORY_SEQ);
        List<Integer> lstCATEGORY_SEQ = null;
        try {
            lstCATEGORY_SEQ = categoryService.selectTB_CATEGORYAndChidren(CATEGORY_SEQ);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createBySuccess(lstCATEGORY_SEQ);
    }



}
