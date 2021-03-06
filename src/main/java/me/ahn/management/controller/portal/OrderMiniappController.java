package me.ahn.management.controller.portal;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.annotation.PassToken;
import me.ahn.management.common.Const;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.*;
import me.ahn.management.service.OrderService;
import me.ahn.management.service.ProductService;
import me.ahn.management.service.ShippingService;
import me.ahn.management.util.Box;
import me.ahn.management.util.HttpUtility;
import me.ahn.management.wxpay.MyConfig;
import me.ahn.management.wxpay.WXPay;
import me.ahn.management.wxpay.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping(value = "/api/miniapp/order/")
public class OrderMiniappController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private ProductService productService;

    @PassToken
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ServerResponse create_order(HttpServletRequest request, @RequestBody CartVO cartVO) {
        try {
            return orderService.createOrder(cartVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("生成订单失败：" + cartVO.toString());
        }
    }

    @PassToken
    @RequestMapping(value = "pay")
    public ServerResponse doUnifiedOrder(HttpServletRequest request, @RequestBody Map<String, Object> paramMap) {
        System.out.println(">>>>>>>>>>>paramMap:::::" + paramMap.toString());
        Box box = HttpUtility.getBox(request);
        String openId = (String) paramMap.get("openId");
        String orderNo = (String) paramMap.get("orderNo");
//        Integer totalPayment_origin = (Integer) paramMap.get("totalPayment");
        String moneyNew = String.valueOf(Math.round(Double.parseDouble(String.valueOf(paramMap.get("totalPayment")))*100));
        String productName = (String) paramMap.get("productName");
//        @RequestParam("openId") String openId, @RequestParam("orderNo") String orderNo,@RequestParam("totalPayment") Integer totalPayment, String productName
        MyConfig config = new MyConfig();
        WXPay wxpay= null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map resultMap = new HashMap<>();

        //生成的随机字符串
        String nonce_str = WXPayUtil.generateNonceStr();
        //获取客户端的ip地址
        //获取本机的ip地址
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        String spbill_create_ip = addr.getHostAddress();
        //支付金额，需要转成字符串类型，否则后面的签名会失败
//        int  total_fee = totalPayment_origin;
//        String  total_fee = totalPayment;
//        System.out.println(">>>>>" + total_fee);
        //商品描述
        String body = "Stronghold咖啡豆产品";
        //商户订单号
//        String out_trade_no= WXPayUtil.generateNonceStr();
        String out_trade_no = orderNo;
        //统一下单接口参数
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", config.getAppID());
        data.put("mch_id", config.getMchID());
        data.put("nonce_str", nonce_str);
        data.put("body", body);
        data.put("out_trade_no",out_trade_no);
//        data.put("total_fee", String.valueOf(total_fee));
        data.put("total_fee", moneyNew);
        data.put("spbill_create_ip", spbill_create_ip);
        data.put("notify_url", "https://strongholdcoffeemall.cn/"); //TODO
//        data.put("notify_url", "https://localhost"); //TODO
        data.put("trade_type","JSAPI");
        data.put("openid", openId); //TODO

        String sign = null;
        try {
            sign = WXPayUtil.generateSignature(data, config.getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        data.put("sign", sign);
        log.info(data.toString());
        try {
            Map<String, String> rMap = wxpay.unifiedOrder(data);
            System.out.println("统一下单接口返回: " + rMap);
            String return_code = (String) rMap.get("return_code");
            String result_code = (String) rMap.get("result_code");
            String nonceStr = WXPayUtil.generateNonceStr();
            resultMap.put("nonceStr", nonceStr);
            Long timeStamp = System.currentTimeMillis() / 1000;
            if ("SUCCESS".equals(return_code) && return_code.equals(result_code)) {
                String prepayid = rMap.get("prepay_id");
                resultMap.put("package", "prepay_id="+prepayid);
                resultMap.put("signType", "MD5");
                //这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                resultMap.put("timeStamp", timeStamp + "");
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                resultMap.put("appId",config.getAppID());
                sign = WXPayUtil.generateSignature(resultMap, config.getKey());
                resultMap.put("paySign", sign);
                System.out.println("生成的签名paySign : "+ sign);

                return ServerResponse.createBySuccess(resultMap);
            }else{
                return  ServerResponse.createBySuccess("???", resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("ERROR ERROR ERROR ERROR");
        }

    }

    @PassToken
    @RequestMapping(value = "pay_success", method = RequestMethod.POST)
    public ServerResponse pay_success(HttpServletRequest request, @RequestParam("ORDER_NO") String ORDER_NO) {
        if ("".equals(ORDER_NO)) {
            return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
        }

        TB_ORDER tbOrder = new TB_ORDER();
        tbOrder.setORDER_NO(ORDER_NO);
        tbOrder.setSTATUS(String.valueOf(Const.OrderStatusEnum.PAID));

        try {
            orderService.pay_success(tbOrder);
            return ServerResponse.createBySuccessMessage(Const.Message.UPDATE_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("支付状态修改失败， 请联系管理员");
        }
    }

    @PassToken
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ServerResponse list(HttpServletRequest request, @RequestBody TB_ORDER tbOrder) {
        List<TB_ORDER> lstTB_ORDER;
        TB_SHIPPING tbShipping;
        try {
            if ("99".equals(tbOrder.getSTATUS())) {
                //在小程序上选择全部的时候 - 99
                tbOrder.setSTATUS(""); // 设置空值, 为了在后面的mapper上全部查询
            }
            lstTB_ORDER = orderService.selectTB_ORDERByTB_ORDERForWechat(tbOrder);
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
            return ServerResponse.createBySuccess(lstTB_ORDER);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

}
