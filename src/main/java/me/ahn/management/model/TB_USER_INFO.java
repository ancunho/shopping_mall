package me.ahn.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TB_USER_INFO {

    @JsonProperty("USER_SEQ")
    private String USER_SEQ;

    @JsonProperty("OPENID")
    private String OPENID;

    @JsonProperty("UNIONID")
    private String UNIONID;

    @JsonProperty("USERNAME")
    private String USERNAME;

    @JsonProperty("PASSWORD")
    private String PASSWORD;

    @JsonProperty("ROLE_NO")
    private String ROLE_NO;

    @JsonProperty("ROLE")
    private String ROLE;

    @JsonProperty("USE_YN")
    private String USE_YN;

    @JsonProperty("REALNAME")
    private String REALNAME;

    @JsonProperty("COMPANY")
    private String COMPANY;


    @JsonProperty("COMPANY_TYPE")
    private String COMPANY_TYPE;

    @JsonProperty("DEVICE_SERIAL")
    private String DEVICE_SERIAL;

    @JsonProperty("DEVICE_MODEL")
    private String DEVICE_MODEL;

    @JsonProperty("DEVICE_COLOR")
    private String DEVICE_COLOR;

    @JsonProperty("PHONE")
    private String PHONE;

    @JsonProperty("EMAIL")
    private String EMAIL;

    @JsonProperty("SEX")
    private String SEX;

    @JsonProperty("BIRTHDAY")
    private String BIRTHDAY;

    @JsonProperty("WECHAT")
    private String WECHAT;

    @JsonProperty("QQ")
    private String QQ;

    @JsonProperty("PROVINCE_CODE")
    private String PROVINCE_CODE;

    @JsonProperty("CITY_CODE")
    private String CITY_CODE;

    @JsonProperty("DISTRICT_CODE")
    private String DISTRICT_CODE;

    @JsonProperty("ADDRESS")
    private String ADDRESS;

    @JsonProperty("QUESTION")
    private String QUESTION;

    @JsonProperty("ANSWER")
    private String ANSWER;

    @JsonProperty("IMAGE_PHOTO")
    private String IMAGE_PHOTO;

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
