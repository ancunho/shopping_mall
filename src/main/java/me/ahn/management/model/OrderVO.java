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
public class OrderVO {

    @JsonProperty("ORDER_NO")
    private String ORDER_NO;

    @JsonProperty("PAYMENT")
    private BigDecimal PAYMENT;

    @JsonProperty("PAYMENT_TYPE")
    private String PAYMENT_TYPE;

    @JsonProperty("PAYMENT_TYPE_DESC")
    private String PAYMENT_TYPE_DESC;

    @JsonProperty("POSTAGE")
    private BigDecimal POSTAGE;

    @JsonProperty("STATUS")
    private String STATUS;

    @JsonProperty("STATUS_DESC")
    private String STATUS_DESC;

    @JsonProperty("PAYMENT_TIME")
    private String PAYMENT_TIME;

    @JsonProperty("SEND_TIME")
    private String SEND_TIME;

    @JsonProperty("END_TIME")
    private String END_TIME;

    @JsonProperty("CLOSE_TIME")
    private String CLOSE_TIME;

    @JsonProperty("CREATE_TIME")
    private String CREATE_TIME;

    @JsonProperty("lstOrderItem")
    private List<TB_ORDER_ITEM> lstOrderItem;

    @JsonProperty("IMAGE_HOST")
    private String IMAGE_HOST;

    @JsonProperty("SHIPPING_SEQ")
    private Integer SHIPPING_SEQ;

    @JsonProperty("RECEIVER_NAME")
    private String RECEIVER_NAME;

    @JsonProperty("tbShipping")
    private TB_SHIPPING tbShipping;

    @JsonProperty("COMMENT")
    private String COMMENT;

    @JsonProperty("CUSTOMER")
    private TB_USER_INFO CUSTOMER;

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



}
