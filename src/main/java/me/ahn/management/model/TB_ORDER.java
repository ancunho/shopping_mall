package me.ahn.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
public class TB_ORDER {

    @JsonProperty("ORDER_SEQ")
    private Integer ORDER_SEQ;

    @JsonProperty("ORDER_NO")
    private String ORDER_NO;

    @JsonProperty("USER_SEQ")
    private Integer USER_SEQ;

    @JsonProperty("SHIPPING_SEQ")
    private Integer SHIPPING_SEQ;

    @JsonProperty("PAYMENT")
    private BigDecimal PAYMENT;

    @JsonProperty("VIP_PAYMENT")
    private BigDecimal VIP_PAYMENT;

    @JsonProperty("DELIVERY_TYPE")
    private String DELIVERY_TYPE;

    @JsonProperty("DELIVERY_NO")
    private String DELIVERY_NO;

    @JsonProperty("PAYMENT_TYPE")
    private String PAYMENT_TYPE;

    @JsonProperty("PAYMENT_TYPE_DESC")
    private String PAYMENT_TYPE_DESC;

    @JsonProperty("USE_YN")
    private String USE_YN;

    @JsonProperty("POSTAGE")
    private BigDecimal POSTAGE;

    @JsonProperty("STATUS")
    private String STATUS;

    @JsonProperty("STATUS_DESC")
    private String STATUS_DESC;

    @JsonProperty("COMMENT")
    private String COMMENT;

    @JsonProperty("PAYMENT_TIME")
    private String PAYMENT_TIME;

    @JsonProperty("SEND_TIME")
    private String SEND_TIME;

    @JsonProperty("END_TIME")
    private String END_TIME;

    @JsonProperty("CLOSE_TIME")
    private String CLOSE_TIME;

    @JsonProperty("OPTION01")
    private String OPTION01;

    @JsonProperty("OPTION02")
    private String OPTION02;

    @JsonProperty("OPTION03")
    private String OPTION03;

    @JsonProperty("OPTION04")
    private String OPTION04;

    @JsonProperty("OPTION05")
    private String OPTION05;

    @JsonProperty("CREATETIME")
    private String CREATETIME;

    @JsonProperty("UPDATETIME")
    private String UPDATETIME;

    @JsonProperty("UserInfo")
    private TB_USER_INFO UserInfo;

    @JsonProperty("Shipping")
    private TB_SHIPPING Shipping;

    @JsonProperty("lstOrderItem")
    private List<TB_ORDER_ITEM> lstOrderItem;


    /**
     * 공통코드
     */
    @JsonProperty("START_DATE")
    private String START_DATE =  "";

    @JsonProperty("END_DATE")
    private String END_DATE =  "";

    @JsonProperty("RN")
    private int RN = 0;

    @JsonProperty("PAGE_NO")
    private int PAGE_NO = 0;

    @JsonProperty("START_ROW_POSITION")
    private int START_ROW_POSITION = 0;

    @JsonProperty("PAGE_ROW_COUNT")
    private int PAGE_ROW_COUNT = 0;

    @JsonProperty("ALL_COUNT")
    private int ALL_COUNT = 0;

    @JsonProperty("PAGE_NUM")
    private int PAGE_NUM = 0;

    @JsonProperty("PAGE_SIZE")
    private int PAGE_SIZE = 15;

    @JsonProperty("ERR_CD")
    private String ERR_CD = "";

    @JsonProperty("ERR_MSG")
    private String ERR_MSG = "";

    @JsonProperty("SESSION_ID")
    private String SESSION_ID = "";

    @JsonProperty("IP_ADDR")
    private String IP_ADDR = "";

    @JsonProperty("USER_AGENT")
    private String USER_AGENT = "";

    @JsonProperty("REFERER")
    private String REFERER = "";

}
