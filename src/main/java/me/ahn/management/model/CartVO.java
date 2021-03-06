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
public class CartVO {

    @JsonProperty("cartProductVOList")
    private List<CartProductVO> cartProductVOList;

    @JsonProperty("USER_SEQ")
    private Integer USER_SEQ;

    @JsonProperty("SHIPPING_SEQ")
    private Integer SHIPPING_SEQ;

    @JsonProperty("ATTRIBUTE")
    private String ATTRIBUTE;

    @JsonProperty("CART_TOTAL_PRICE")
    private BigDecimal CART_TOTAL_PRICE;

    @JsonProperty("ALL_CHECKED")
    private Boolean ALL_CHECKED;

    @JsonProperty("IMAGE_HOST")
    private String IMAGE_HOST;

    @JsonProperty("COMMENT")
    private String COMMENT;

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
