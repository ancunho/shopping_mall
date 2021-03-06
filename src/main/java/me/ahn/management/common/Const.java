package me.ahn.management.common;

public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";

    public static final String USERNAME = "username";

    public static final String CUSTOMER_NAME = "customerName";

    public static final String CUSTOMER_CD = "customerCd";

    public static final String RedirectLoginPage = "login";

    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 上传图片的最大大小： 500K
     * 1KB = 1024Byte
     */
    public static final Integer UPLOAD_IMAGE_MAX_SIZE = 20 * 1024;

    public interface RoleNo{
        String ROLE_USER        = "01";     //普通用户
        String ROLE_EDITOR      = "02";      //组长？
        String ROLE_ADMIN       = "99";        //管理员
    }


    public interface Role{
        String ROLE_USER        = "ROLE_USER";     //普通用户
        String ROLE_EDITOR      = "ROLE_EDITOR";      //组长？
        String ROLE_ADMIN       = "ROLE_ADMIN";        //管理员
    }

    public interface Status {
        String ACTIVE       = "1"; //已激活
        String NOT_ACTIVE   = "0"; //未激活
    }

    public interface BooleanStatus {
        Boolean ACTIVE = true;
        Boolean NOT_ACTIVE = false;
    }

    public interface Cart{
        int CHECKED         = 1;//即购物车选中状态
        int UN_CHECKED      = 0;//购物车中未选中状态

        String LIMIT_NUM_FAIL       = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS    = "LIMIT_NUM_SUCCESS";
    }

    public interface Sex {
        String male     = "01"; // 男
        String female   = "02"; // 女
    }

    public interface ProjectStatus {
        String KICKOFF              = "0";
        String AGREEMENT            = "1";
        String EXPORT_PREPARATION   = "2";
        String CONTRACT_ORDER       = "3";
        String PRODUCTION_EXPORT    = "4";
        String EXPORT_AS            = "5";
        String REJECT               = "99";
    }

    public interface Message {
        String SAVE_OK          = "数据保存成功";
        String SAVE_ERROR       = "数据保存失败";
        String UPDATE_OK        = "更新成功";
        String UPDATE_ERROR     = "更新失败";
        String DELETE_OK        = "删除成功";
        String DELETE_ERROR     = "删除失败";
        String SELECT_OK        = "查询成功";
        String SELECT_ERROR     = "查询失败";
        String PARAMETER_ERROR  = "参数错误";
        String NEED_LOGIN       = "需要登陆";
        String DATA_EXIST       = "已存在记录";
    }

    public interface FileType {
        String AVATAR           = "avatar"; //用户头像
        String PROJECT_FILE     = "pfile"; //项目模块中用的附件지
        String CUSTOMER_AVATAR  = "customerAvatar"; //고객사 아바자이미
    }

    public interface UserAction {
        String CREATE_PROJECT = "创建项目";
        String UPDATE_PROJECT = "修改项目内容";
        String NEW_USER = "加入会员";
    }

    public enum ProductStatusEnum{
        ON_SALE(1,"在线");
        private String value;
        private int code;
        ProductStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum OrderStatusEnum{
        NO_PAY(10,"未支付"),
        PAID(20,"已付款"),
        SHIPPED(40,"已发货"),
        ORDER_SUCCESS(50,"订单完成"),
        ORDER_CLOSE(60,"订单关闭"),
        CANCELED(70,"已取消");


        OrderStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static OrderStatusEnum codeOf(int code){
            for(OrderStatusEnum orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }

    public enum PaymentTypeEnum{
        ONLINE_PAY(1,"在线支付");

        PaymentTypeEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }


        public static PaymentTypeEnum codeOf(int code){
            for(PaymentTypeEnum paymentTypeEnum : values()){
                if(paymentTypeEnum.getCode() == code){
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }

}
