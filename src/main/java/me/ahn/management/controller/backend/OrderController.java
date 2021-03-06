package me.ahn.management.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.annotation.UserLoginToken;
import me.ahn.management.common.Const;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.*;
import me.ahn.management.service.OrderService;
import me.ahn.management.service.ProductService;
import me.ahn.management.service.ShippingService;
import me.ahn.management.util.Box;
import me.ahn.management.util.HttpUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping(value = "/api/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private ProductService productService;

    /**
     * Backend - list
     * @param request
     * @param tbOrder
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ServerResponse selectTB_ORDERByTB_ORDER(HttpServletRequest request, @RequestBody TB_ORDER tbOrder) {
        Box box = HttpUtility.getBox(request);
        box.copyToEntity(tbOrder);

        try {
            PageHelper.startPage(tbOrder.getPAGE_NUM(), tbOrder.getPAGE_SIZE());
            List<TB_ORDER> lstTB_ORDER = orderService.selectTB_ORDERByTB_ORDER(tbOrder);
            TB_SHIPPING tbShipping;
            List<TB_ORDER_ITEM> lstTB_ORDER_ITEM;
            for (int i = 0; lstTB_ORDER != null && i < lstTB_ORDER.size(); i++) {
                tbShipping = shippingService.selectTB_SHIPPINGByPk(lstTB_ORDER.get(i).getSHIPPING_SEQ());
                lstTB_ORDER.get(i).setShipping(tbShipping);

                lstTB_ORDER_ITEM = orderService.selectTB_ORDER_ITEMByORDER_NO(lstTB_ORDER.get(i).getORDER_NO());
                for(int k = 0; lstTB_ORDER_ITEM != null && k < lstTB_ORDER_ITEM.size(); k++) {
                    if(lstTB_ORDER_ITEM.get(k).getSPEC_SEQ() != null) {
                        TB_SPEC tbSpec = new TB_SPEC();
                        tbSpec = productService.selectTB_SPECByPk(lstTB_ORDER_ITEM.get(k).getSPEC_SEQ());
                        lstTB_ORDER_ITEM.get(k).setTbSpec(tbSpec);
                    }
                }
                lstTB_ORDER.get(i).setLstOrderItem(lstTB_ORDER_ITEM);
            }
            PageInfo pageInfo = new PageInfo(lstTB_ORDER);
            pageInfo.setList(lstTB_ORDER);
            return ServerResponse.createBySuccess(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

    /**
     * Backend - detail
     */
    @UserLoginToken
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ServerResponse selectTB_ORDERByPk(HttpServletRequest request, Integer ORDER_SEQ) {
        if (ORDER_SEQ == null) {
            return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
        }
        try {
            TB_ORDER tbOrder = orderService.selectTB_ORDERByPk(ORDER_SEQ);
            TB_SHIPPING tbShipping = shippingService.selectTB_SHIPPINGByPk(tbOrder.getSHIPPING_SEQ());
            List<TB_ORDER_ITEM> lstTB_ORDER_ITEM = orderService.selectTB_ORDER_ITEMByTB_ORDER(tbOrder);
            tbOrder.setShipping(tbShipping);
            tbOrder.setLstOrderItem(lstTB_ORDER_ITEM);

            return ServerResponse.createBySuccess(tbOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

    /**
     * Backend - edit
     */
    @UserLoginToken
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ServerResponse updateTB_ORDER(HttpServletRequest request, @RequestBody TB_ORDER tbOrder) {
        try {
            if (tbOrder.getORDER_SEQ() == null) {
                return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
            }

            if("20".equals(tbOrder.getSTATUS())
                || "40".equals(tbOrder.getSTATUS())
                || "50".equals(tbOrder.getSTATUS())
                || "70".equals(tbOrder.getSTATUS())
            ) {
                return ServerResponse.createByErrorMessage("当前的订单状态下不能修改价格，请确认！");
            }

            orderService.updateTB_ORDERByPk(tbOrder);
            return ServerResponse.createBySuccessMessage(Const.Message.UPDATE_OK);
        } catch(Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.UPDATE_ERROR);
        }
    }

}
