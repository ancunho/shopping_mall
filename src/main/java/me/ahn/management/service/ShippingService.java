package me.ahn.management.service;

import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.TB_SHIPPING;

import java.util.List;

public interface ShippingService {

    public ServerResponse insertTB_SHIPPING(TB_SHIPPING tbShipping) throws Exception;
    public ServerResponse selectDefaultShippingByUserId(Integer USER_SEQ);
    public TB_SHIPPING selectTB_SHIPPINGByPk(Integer SHIPPING_SEQ) throws Exception;
    public List<TB_SHIPPING> selectTB_SHIPPINGByUSER_SEQ(Integer USER_SEQ) throws Exception;

    public void updateTB_SHIPPING(TB_SHIPPING tbShipping) throws Exception;
}
