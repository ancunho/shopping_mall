package me.ahn.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TB_FILE {

    @JsonProperty("FILE_SEQ")
    private String FILE_SEQ;

    @JsonProperty("FILE_NAME")
    private String FILE_NAME;

    @JsonProperty("FILE_PATH")
    private String FILE_PATH;

    @JsonProperty("FILE_FULL_PATH")
    private String FILE_FULL_PATH;

    @JsonProperty("USE_TYPE")
    private String USE_TYPE;

    @JsonProperty("USE_YN")
    private String USE_YN;

    @JsonProperty("SORT")
    private String SORT;

    @JsonProperty("PARAM1")
    private String PARAM1;

    @JsonProperty("PARAM2")
    private String PARAM2;

    @JsonProperty("PARAM3")
    private String PARAM3;

    @JsonProperty("PARAM4")
    private String PARAM4;

    @JsonProperty("PARAM5")
    private String PARAM5;

    @JsonProperty("CREATETIME")
    private String CREATETIME;

    @JsonProperty("UPDATETIME")
    private String UPDATETIME;


}
