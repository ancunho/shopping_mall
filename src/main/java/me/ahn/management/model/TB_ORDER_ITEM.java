package me.ahn.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class TB_ORDER_ITEM {
    @JsonProperty("ITEM_SEQ")
    private Integer ITEM_SEQ;

    @JsonProperty("USER_SEQ")
    private Integer USER_SEQ;

    @JsonProperty("ORDER_NO")
    private String ORDER_NO;

    @JsonProperty("PRODUCT_SEQ")
    private Integer PRODUCT_SEQ;

    @JsonProperty("SPEC_SEQ")
    private Integer SPEC_SEQ;

    @JsonProperty("ATTRIBUTE")
    private String ATTRIBUTE;

    @JsonProperty("PRODUCT_NAME")
    private String PRODUCT_NAME;

    @JsonProperty("PRODUCT_IMAGE")
    private String PRODUCT_IMAGE;

    @JsonProperty("CURRENT_UNIT_PRICE")
    private BigDecimal CURRENT_UNIT_PRICE;

    @JsonProperty("QTY")
    private Integer QTY;

    @JsonProperty("TOTAL_PRICE")
    private BigDecimal TOTAL_PRICE;

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

    @JsonProperty("tbSpec")
    private TB_SPEC tbSpec;

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
