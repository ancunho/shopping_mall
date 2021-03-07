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
public class TB_PRODUCT {

    @JsonProperty("PRODUCT_SEQ")
    private Integer PRODUCT_SEQ;

    @JsonProperty("CATEGORY_ID")
    private Integer CATEGORY_ID;

    @JsonProperty("PRODUCT_NAME")
    private String PRODUCT_NAME;

    @JsonProperty("PRODUCT_SUBTITLE")
    private String PRODUCT_SUBTITLE;

    @JsonProperty("MAIN_IMAGE")
    private String MAIN_IMAGE;

    @JsonProperty("SUB_IMAGE")
    private String SUB_IMAGE;

    @JsonProperty("SUB_IMAGE2")
    private String SUB_IMAGE2;

    @JsonProperty("SUB_IMAGE3")
    private String SUB_IMAGE3;

    @JsonProperty("ATTRIBUTE")
    private String ATTRIBUTE;

    @JsonProperty("ATTRIBUTE_NAME")
    private String ATTRIBUTE_NAME;

    @JsonProperty("DETAIL")
    private String DETAIL;

    @JsonProperty("PRICE")
    private BigDecimal PRICE;

    @JsonProperty("VIP_PRICE")
    private BigDecimal VIP_PRICE;

    @JsonProperty("SPEC")
    private String SPEC;

    @JsonProperty("STOCK")
    private Integer STOCK;

    @JsonProperty("USE_YN")
    private String USE_YN;

    @JsonProperty("STATUS")
    private String STATUS;

    @JsonProperty("COUNTRY")
    private String COUNTRY;

    @JsonProperty("CITY")
    private String CITY;

    @JsonProperty("VARIETY")
    private String VARIETY;

    @JsonProperty("TREATMENT")
    private String TREATMENT;

    @JsonProperty("FLAVOR")
    private String FLAVOR;

    @JsonProperty("COUNT_SPEC")
    private String COUNT_SPEC;

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

    @JsonProperty("lstTB_SPEC")
    private List<TB_SPEC> lstTB_SPEC;


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
