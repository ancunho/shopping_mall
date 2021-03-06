package me.ahn.management.controller.portal;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.annotation.PassToken;
import me.ahn.management.common.Const;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.TB_SHIPPING;
import me.ahn.management.service.ShippingService;
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
@RequestMapping(value = "/api/miniapp/shipping/")
public class PortalShippingController {

    @Autowired
    private ShippingService shippingService;

    @PassToken
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ServerResponse insertTB_SHIPPING(@RequestBody TB_SHIPPING tbShipping) {
        try {
            return shippingService.insertTB_SHIPPING(tbShipping);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SAVE_ERROR);
        }
    }

    @PassToken
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ServerResponse updateTB_SHIPPING(@RequestBody TB_SHIPPING tbShipping) {
        try {
            shippingService.updateTB_SHIPPING(tbShipping);
            return ServerResponse.createBySuccessMessage(Const.Message.UPDATE_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.UPDATE_ERROR);
        }
    }

    @PassToken
    @RequestMapping(value = "default", method = RequestMethod.GET)
    public ServerResponse default_shipping(Integer USER_SEQ) {
        return shippingService.selectDefaultShippingByUserId(USER_SEQ);
    }

    @PassToken
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ServerResponse selectTB_SHIPPINGByUSER_SEQ(Integer USER_SEQ) {
        List<TB_SHIPPING> lstTB_SHIPPING = null;
        if (USER_SEQ == null) {
            return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
        }
        try {
            lstTB_SHIPPING = shippingService.selectTB_SHIPPINGByUSER_SEQ(USER_SEQ);
            return ServerResponse.createBySuccess(lstTB_SHIPPING);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }

    }

    @PassToken
    @RequestMapping(value = "detailByPk", method = RequestMethod.POST)
    public ServerResponse selectTB_SHIPPINGByPk(Integer SHIPPING_SEQ) {
        TB_SHIPPING tbShipping = null;
        try {
            tbShipping = shippingService.selectTB_SHIPPINGByPk(SHIPPING_SEQ);
            return ServerResponse.createBySuccess(tbShipping);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }


}
