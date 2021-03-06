package me.ahn.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TB_COMMON_CODE extends BaseModel{

    @JsonProperty("CODE_SEQ")
    private String CODE_SEQ;

    @JsonProperty("CODE_ID")
    private String CODE_ID;

    @JsonProperty("CODE_TYPE")
    private String CODE_TYPE;

    @JsonProperty("CODE_NAME")
    private String CODE_NAME;

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




}
