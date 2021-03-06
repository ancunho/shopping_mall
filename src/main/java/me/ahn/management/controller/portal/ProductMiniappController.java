package me.ahn.management.controller.portal;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.annotation.PassToken;
import me.ahn.management.annotation.UserLoginToken;
import me.ahn.management.common.Const;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.TB_PRODUCT;
import me.ahn.management.model.TB_SPEC;
import me.ahn.management.service.ProductService;
import me.ahn.management.util.Box;
import me.ahn.management.util.HttpUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping(value = "/api/miniapp/product/")
public class ProductMiniappController {

    @Autowired
    private ProductService productService;

    @PassToken
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ServerResponse selectTB_PRODUCT(HttpServletRequest request, @RequestBody TB_PRODUCT tbProduct) {

        try {
            List<TB_PRODUCT> lstTB_PRODUCT = productService.selectTB_PRODUCTForWechatOnList(tbProduct);
            return ServerResponse.createBySuccess(lstTB_PRODUCT);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

    @PassToken
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public ServerResponse selectTB_PRODUCTByPkForWechat(HttpServletRequest request) {
        Box box = HttpUtility.getBox(request);
        TB_PRODUCT tbProduct = new TB_PRODUCT();
        box.copyToEntity(tbProduct);

        if ("".equals(box.get("PRODUCT_SEQ"))) {
            return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
        }

        try {
            tbProduct.setPRODUCT_SEQ(Integer.valueOf(box.get("PRODUCT_SEQ")));
            tbProduct = productService.selectTB_PRODUCTByPkForWechat(tbProduct);
            List<TB_SPEC> lstTB_SPEC = productService.selectTB_SPECByPRODUCT_SEQForWechat(tbProduct);
            tbProduct.setLstTB_SPEC(lstTB_SPEC != null ? lstTB_SPEC : new ArrayList<>());
            return ServerResponse.createBySuccess(tbProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

    @PassToken
    @RequestMapping(value = "getSpecListByProductSeq")
    public ServerResponse selectTB_SPECByPRODUCT_SEQForWechat(HttpServletRequest request, Integer PRODUCT_SEQ) {
        TB_PRODUCT tbProduct = new TB_PRODUCT();
        tbProduct.setPRODUCT_SEQ(PRODUCT_SEQ);

        if (tbProduct.getPRODUCT_SEQ() == null) {
            return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
        }

        try {
            List<TB_SPEC> lstTB_SPEC = productService.selectTB_SPECByPRODUCT_SEQForWechat(tbProduct);
            return ServerResponse.createBySuccess(lstTB_SPEC);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

}
