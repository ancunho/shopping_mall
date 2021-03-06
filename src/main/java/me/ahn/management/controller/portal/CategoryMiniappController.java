package me.ahn.management.controller.portal;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.annotation.PassToken;
import me.ahn.management.common.Const;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.TB_CATEGORY;
import me.ahn.management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping(value = "/api/miniapp/category/")
public class CategoryMiniappController {

    @Autowired
    private CategoryService categoryService;

    @PassToken
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ServerResponse selectTB_CATEGORY(@RequestBody TB_CATEGORY tbCategory) {
        try {
            List<TB_CATEGORY> tbCategoryList = categoryService.selectTB_CATEGORYForWechat(tbCategory);
            return ServerResponse.createBySuccess(tbCategoryList);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

}
