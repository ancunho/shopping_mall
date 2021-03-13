package me.ahn.management.controller.backend;

import com.github.pagehelper.PageInfo;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.annotation.UserLoginToken;
import me.ahn.management.common.Const;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.TB_PRODUCT;
import me.ahn.management.model.TB_SPEC;
import me.ahn.management.service.ProductService;
import me.ahn.management.util.Box;
import me.ahn.management.util.HttpUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping(value = "/api/product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @UserLoginToken
    @RequestMapping(value = "proc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServerResponse procTB_PRODUCT(HttpServletRequest request, @RequestBody TB_PRODUCT tbProduct) {
        if (tbProduct == null) {
            return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
        }

        try {
            if(tbProduct.getPRODUCT_SEQ() == null) {
                productService.insertTB_PRODUCT(tbProduct);
                if (tbProduct.getPRODUCT_SEQ() != null && tbProduct.getLstTB_SPEC() != null) {
                    for(int i = 0; i < tbProduct.getLstTB_SPEC().size(); i++) {
                        tbProduct.getLstTB_SPEC().get(i).setPRODUCT_SEQ(tbProduct.getPRODUCT_SEQ());
                        tbProduct.getLstTB_SPEC().get(i).setPRODUCT_NAME(tbProduct.getPRODUCT_NAME());
                        tbProduct.getLstTB_SPEC().get(i).setUSE_YN("1");
                        productService.insertTB_SPEC(tbProduct.getLstTB_SPEC().get(i));
                    }
                }
            } else {
                productService.updateTB_PRODUCT(tbProduct);
                if(tbProduct.getLstTB_SPEC() != null) {
                    for(int i = 0; i < tbProduct.getLstTB_SPEC().size(); i++) {
                        if(tbProduct.getLstTB_SPEC().get(i).getSPEC_SEQ() == null) {
                            tbProduct.getLstTB_SPEC().get(i).setPRODUCT_SEQ(tbProduct.getPRODUCT_SEQ());
                            tbProduct.getLstTB_SPEC().get(i).setPRODUCT_NAME(tbProduct.getPRODUCT_NAME());
                            tbProduct.getLstTB_SPEC().get(i).setUSE_YN("1");
                            productService.insertTB_SPEC(tbProduct.getLstTB_SPEC().get(i));
                        } else {
                            productService.updateTB_SPECByPk(tbProduct.getLstTB_SPEC().get(i));
                        }
                    }
                }

            }
            return ServerResponse.createBySuccessMessage(Const.Message.SAVE_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SAVE_ERROR);
        }
    }

    @UserLoginToken
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ServerResponse selectTB_PRODUCT(HttpServletRequest request,
                                           @RequestParam(value = "PAGE_NUM", defaultValue = "1") int PAGE_NUM,
                                           @RequestParam(value = "PAGE_SIZE", defaultValue = "10") int PAGE_SIZE) {
        Box box = HttpUtility.getBox(request);
        TB_PRODUCT tbProduct = new TB_PRODUCT();
        box.copyToEntity(tbProduct);
        tbProduct.setPAGE_NUM(PAGE_NUM);
        tbProduct.setPAGE_SIZE(PAGE_SIZE);

        try {
            PageInfo pageResult = productService.selectTB_PRODUCT(tbProduct);
            return ServerResponse.createBySuccess(pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

    @UserLoginToken
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public ServerResponse selectTB_PRODUCTByPk(HttpServletRequest request) {
        Box box = HttpUtility.getBox(request);
        TB_PRODUCT tbProduct = new TB_PRODUCT();
        box.copyToEntity(tbProduct);

        try {
            tbProduct.setPRODUCT_SEQ(Integer.valueOf(box.get("PRODUCT_SEQ")));
            tbProduct = productService.selectTB_PRODUCTByPk(tbProduct);
            List<TB_SPEC> lstTB_SPEC = productService.selectTB_SPECByPRODUCT_SEQ(tbProduct);
            tbProduct.setLstTB_SPEC(lstTB_SPEC != null ? lstTB_SPEC : new ArrayList<>());
            return ServerResponse.createBySuccess(tbProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

    @UserLoginToken
    @RequestMapping(value = "delete/spec", method = RequestMethod.POST)
    public ServerResponse deleteTB_SPECByPk(HttpServletRequest request, @RequestBody TB_SPEC tbSpec) {
        try {
            if (tbSpec.getSPEC_SEQ() == null) {
                return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
            }
            productService.deleteTB_SPECByPk(tbSpec);
            return ServerResponse.createBySuccessMessage(Const.Message.DELETE_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.DELETE_ERROR);
        }
    }

}
