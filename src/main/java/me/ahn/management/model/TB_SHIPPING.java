package me.ahn.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TB_SHIPPING {

    @JsonProperty("SHIPPING_SEQ")
    private Integer SHIPPING_SEQ;

    @JsonProperty("USER_SEQ")
    private Integer USER_SEQ;

    @JsonProperty("RECEIVER_NAME")
    private String RECEIVER_NAME;

    @JsonProperty("RECEIVER_PHONE")
    private String RECEIVER_PHONE;

    @JsonProperty("RECEIVER_MOBILE")
    private String RECEIVER_MOBILE;

    @JsonProperty("RECEIVER_PROVINCE")
    private String RECEIVER_PROVINCE;

    @JsonProperty("RECEIVER_PROVINCE_CODE")
    private String RECEIVER_PROVINCE_CODE;

    @JsonProperty("RECEIVER_CITY")
    private String RECEIVER_CITY;

    @JsonProperty("RECEIVER_CITY_CODE")
    private String RECEIVER_CITY_CODE;

    @JsonProperty("RECEIVER_DISTRICT")
    private String RECEIVER_DISTRICT;

    @JsonProperty("RECEIVER_DISTRICT_CODE")
    private String RECEIVER_DISTRICT_CODE;

    @JsonProperty("RECEIVER_ADDRESS")
    private String RECEIVER_ADDRESS;

    @JsonProperty("RECEIVER_ZIP")
    private String RECEIVER_ZIP;

    @JsonProperty("IS_DEFAULT")
    private String IS_DEFAULT;

    @JsonProperty("USE_YN")
    private String USE_YN;

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
