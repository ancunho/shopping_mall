package me.ahn.management.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.common.Const;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.*;
import me.ahn.management.service.OrderService;
import me.ahn.management.service.ProductService;
import me.ahn.management.service.ShippingService;
import me.ahn.management.util.BigDecimalUtil;
import me.ahn.management.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Transactional
    public ServerResponse createOrder(CartVO cartVO) throws Exception {
        log.info("0:::" + cartVO.toString());
        // 1. 주문 아이템 생성
        List<TB_ORDER_ITEM> orderItemList = this.getOrderItemByCartParameter(cartVO);
        for (TB_ORDER_ITEM item : orderItemList) {
            log.info("1:::" + item.getPRODUCT_SEQ() + "--" + item.getSPEC_SEQ());
        }

        // 2. 주문 총가격 계산
        BigDecimal totalPrice = this.getOrderTotalPrice(orderItemList);
        log.info("2:::" + totalPrice);

        // 3. 주문번호 생성
        TB_ORDER tbOrder = new TB_ORDER();
        tbOrder.setORDER_NO(this.generateOrderNo());
        tbOrder.setSTATUS(String.valueOf(Const.OrderStatusEnum.NO_PAY.getCode()));
        tbOrder.setPAYMENT_TYPE(String.valueOf(Const.PaymentTypeEnum.ONLINE_PAY.getCode()));
        tbOrder.setUSER_SEQ(cartVO.getUSER_SEQ());
        tbOrder.setSHIPPING_SEQ(cartVO.getSHIPPING_SEQ());
        tbOrder.setCOMMENT(cartVO.getCOMMENT());

        TB_SHIPPING tbShipping = shippingService.selectTB_SHIPPINGByPk(cartVO.getSHIPPING_SEQ());
        if(tbShipping == null) {
            return null;
        } else {
            int postageValue = 0;
            for(int i = 0; i < orderItemList.size(); i++) {
                if("01".equals(orderItemList.get(i).getATTRIBUTE())) {
                    TB_SPEC tbSpec = new TB_SPEC();
                    tbSpec = productService.selectTB_SPECByPk(orderItemList.get(i).getSPEC_SEQ());
                    if ("上海市".equals(tbShipping.getRECEIVER_PROVINCE()) || "江苏省".equals(tbShipping.getRECEIVER_PROVINCE()) || "浙江省".equals(tbShipping.getRECEIVER_PROVINCE())) {
                        postageValue += orderItemList.get(i).getQTY() * Integer.parseInt(tbSpec.getWEIGHT());
                    } else {
                        postageValue += (2 * orderItemList.get(i).getQTY() * Integer.parseInt(tbSpec.getWEIGHT()));
                    }
                } else if("02".equals(orderItemList.get(i).getATTRIBUTE())) {
                    postageValue += BigDecimalUtil.mul(orderItemList.get(i).getCURRENT_UNIT_PRICE().doubleValue(),orderItemList.get(i).getQTY()).intValue();
                }
            }
            tbOrder.setPOSTAGE(BigDecimal.valueOf(postageValue));
        }
        tbOrder.setPAYMENT(BigDecimalUtil.add(totalPrice.doubleValue(), tbOrder.getPOSTAGE().doubleValue()));

        System.out.println(">>>>tbOrder:" + tbOrder.toString());
        sqlSession.insert("AHNSTUDIO.order.insertTB_ORDER", tbOrder);

        if(CollectionUtils.isEmpty(orderItemList)){
            return ServerResponse.createByErrorMessage("购物车为空");
        }

        // 4. 주문아이템 데이타 저장
        for(TB_ORDER_ITEM orderItem : orderItemList){
            orderItem.setORDER_NO(tbOrder.getORDER_NO());
            sqlSession.insert("AHNSTUDIO.order.insertTB_ORDER_ITEM", orderItem);
        }

        // 5. 재고감소
        this.reduceProductStock(orderItemList);

        // 6. 주문VO 반환
        OrderVO orderVO = this.assembleOrderVo(tbOrder);
        return ServerResponse.createBySuccess(orderVO);
    }

    @Transactional
    public void pay_success(TB_ORDER tbOrder) throws Exception {
        sqlSession.update("AHNSTUDIO.order.updateTB_ORDERByORDER_NOForPAID", tbOrder);
    }

    @Transactional
    public void insertTB_ORDER(TB_ORDER tbOrder) {
        sqlSession.insert("AHNSTUDIO.order.insertTB_ORDER", tbOrder);
    }

    public List<TB_ORDER> selectTB_ORDERByTB_ORDER(TB_ORDER tbOrder) throws Exception {
        if (tbOrder == null) { return null; }
        return sqlSession.selectList("AHNSTUDIO.order.selectTB_ORDERByTB_ORDER", tbOrder);
    }

    public TB_ORDER selectTB_ORDERByPk(Integer ORDER_SEQ) throws Exception {
        if (ORDER_SEQ == null) { return null; }

        return sqlSession.selectOne("AHNSTUDIO.order.selectTB_ORDERByPk", ORDER_SEQ);
    }

    public List<TB_ORDER_ITEM> selectTB_ORDER_ITEMByTB_ORDER(TB_ORDER tbOrder) throws Exception {
        if (tbOrder == null) { return null; }

        return sqlSession.selectList("AHNSTUDIO.order.selectTB_ORDER_ITEMByTB_ORDER", tbOrder);
    }

    public List<TB_ORDER_ITEM> selectTB_ORDER_ITEMByORDER_NO(String ORDER_NO) throws Exception {
        if (ORDER_NO == null) { return null; }

        return sqlSession.selectList("AHNSTUDIO.order.selectTB_ORDER_ITEMByORDER_NO", ORDER_NO);
    }

    @Transactional
    public void updateTB_ORDERByPk(TB_ORDER tbOrder) throws Exception {
        sqlSession.update("AHNSTUDIO.order.updateTB_ORDERByPk", tbOrder);
    }

    private List<TB_ORDER_ITEM> getOrderItemByCartParameter(CartVO cartVO) {
        List<TB_ORDER_ITEM> orderItemList = new ArrayList<>();
        if (CollectionUtils.isEmpty(cartVO.getCartProductVOList())) {
            return null;
        }

        List<CartProductVO> cartProductVOList = cartVO.getCartProductVOList();
        for(CartProductVO cartProductItem : cartProductVOList) {
            TB_ORDER_ITEM tbOrderItem = new TB_ORDER_ITEM();
            tbOrderItem.setUSER_SEQ(cartVO.getUSER_SEQ());
            tbOrderItem.setPRODUCT_SEQ(cartProductItem.getPRODUCT_SEQ());
            tbOrderItem.setATTRIBUTE(cartProductItem.getATTRIBUTE());
            tbOrderItem.setSPEC_SEQ(cartProductItem.getSPEC_SEQ());
            tbOrderItem.setPRODUCT_NAME(cartProductItem.getPRODUCT_NAME());
            tbOrderItem.setPRODUCT_IMAGE(cartProductItem.getMAIN_IMAGE());
            tbOrderItem.setCURRENT_UNIT_PRICE(cartProductItem.getPRICE());
            tbOrderItem.setQTY(cartProductItem.getQUANTITY());
            BigDecimal totalPrice = BigDecimalUtil.mul(cartProductItem.getPRICE().doubleValue(),cartProductItem.getQUANTITY());
            tbOrderItem.setTOTAL_PRICE(totalPrice);
            log.info(tbOrderItem.toString());
            orderItemList.add(tbOrderItem);
        }

        return orderItemList;
    }

    private BigDecimal getOrderTotalPrice(List<TB_ORDER_ITEM> orderItemList) {
        BigDecimal payment = new BigDecimal("0");
        for(TB_ORDER_ITEM orderItem : orderItemList){
            payment = BigDecimalUtil.add(payment.doubleValue(),orderItem.getTOTAL_PRICE().doubleValue());
        }
        return payment;
    }

    private static final AtomicInteger SEQ = new AtomicInteger(1000);
    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS");
    private static ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    private String generateOrderNo() {
        LocalDateTime dataTime = LocalDateTime.now(ZONE_ID);
        if(SEQ.intValue() > 9990){
            SEQ.getAndSet(1000);
        }
        return  dataTime.format(DF_FMT_PREFIX) + SEQ.getAndIncrement();
    }

    private void reduceProductStock(List<TB_ORDER_ITEM> orderItemList){
        for(TB_ORDER_ITEM orderItem : orderItemList){
            TB_PRODUCT currentProduct = new TB_PRODUCT();
            currentProduct.setPRODUCT_SEQ(orderItem.getPRODUCT_SEQ());
            try {
                TB_PRODUCT product = productService.selectTB_PRODUCTByPk(currentProduct);
                int stockValue = 0;
                stockValue = product.getSTOCK() - orderItem.getQTY();
                if((product.getSTOCK() - orderItem.getQTY()) <= 0) {
                    product.setSTOCK(0);
                } else {
                    product.setSTOCK(stockValue);
                }

                productService.updateTB_PRODUCT(product);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private OrderVO assembleOrderVo(TB_ORDER order) {
        OrderVO orderVO = new OrderVO();
        orderVO.setORDER_NO(order.getORDER_NO());
        orderVO.setPAYMENT(order.getPAYMENT());
        orderVO.setPAYMENT_TYPE(order.getPAYMENT_TYPE());
        orderVO.setPOSTAGE(order.getPOSTAGE());
        orderVO.setSTATUS(order.getSTATUS());
        orderVO.setSTATUS_DESC(Const.OrderStatusEnum.codeOf(Integer.parseInt(order.getSTATUS())).getValue());
        orderVO.setCOMMENT(order.getCOMMENT());
        orderVO.setOPTION01(order.getOPTION01());
        orderVO.setOPTION02(order.getOPTION02());
        orderVO.setOPTION03(order.getOPTION03());
        orderVO.setOPTION04(order.getOPTION04());
        orderVO.setOPTION05(order.getOPTION05());

        orderVO.setSHIPPING_SEQ(order.getSHIPPING_SEQ());

        TB_SHIPPING shipping = null;
        try {
            shipping = shippingService.selectTB_SHIPPINGByPk(order.getSHIPPING_SEQ());
            if(shipping != null){
                orderVO.setRECEIVER_NAME(shipping.getRECEIVER_NAME());
                orderVO.setTbShipping(shipping);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        orderVO.setPAYMENT_TIME(order.getPAYMENT_TIME());
        orderVO.setSEND_TIME(order.getSEND_TIME());
        orderVO.setEND_TIME(order.getEND_TIME());
        orderVO.setCREATE_TIME(order.getCREATETIME());
        orderVO.setCLOSE_TIME(order.getCLOSE_TIME());

        try {
            orderVO.setLstOrderItem(orderService.selectTB_ORDER_ITEMByORDER_NO(order.getORDER_NO()));
            return orderVO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TB_ORDER> selectTB_ORDERByTB_ORDERForWechat(TB_ORDER tbOrder) throws Exception {
        if (tbOrder.getUSER_SEQ() == null) {
            return null;
        }
        return sqlSession.selectList("AHNSTUDIO.order.selectTB_ORDERByTB_ORDERForWechat", tbOrder);
    }

    @Transactional
    public void updateTB_ORDERForDELIVERY_NO(TB_ORDER tbOrder) throws Exception {
        sqlSession.update("AHNSTUDIO.order.updateTB_ORDERForDELIVERY_NO", tbOrder);
    }


}
