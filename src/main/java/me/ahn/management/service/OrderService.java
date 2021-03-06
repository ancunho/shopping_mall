package me.ahn.management.service;

import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.CartVO;
import me.ahn.management.model.TB_ORDER;
import me.ahn.management.model.TB_ORDER_ITEM;

import java.util.List;
import java.util.Map;

public interface OrderService {

    public ServerResponse createOrder(CartVO cartVO) throws Exception;
    public void pay_success(TB_ORDER tbOrder) throws Exception;
    public List<TB_ORDER> selectTB_ORDERByTB_ORDER(TB_ORDER tbOrder) throws Exception;
    public TB_ORDER selectTB_ORDERByPk(Integer ORDER_SEQ) throws Exception;

    public List<TB_ORDER_ITEM> selectTB_ORDER_ITEMByTB_ORDER(TB_ORDER tbOrder) throws Exception;
    public List<TB_ORDER_ITEM> selectTB_ORDER_ITEMByORDER_NO(String ORDER_NO) throws Exception;

    public void updateTB_ORDERByPk(TB_ORDER tbOrder) throws Exception;

    public List<TB_ORDER> selectTB_ORDERByTB_ORDERForWechat(TB_ORDER tbOrder) throws Exception;
}
