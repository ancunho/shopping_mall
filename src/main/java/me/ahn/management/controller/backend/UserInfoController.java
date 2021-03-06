package me.ahn.management.controller.backend;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.annotation.PassToken;
import me.ahn.management.annotation.UserLoginToken;
import me.ahn.management.common.Const;
import me.ahn.management.common.ResponseCode;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.TB_USER_INFO;
import me.ahn.management.service.UserInfoService;
import me.ahn.management.util.Box;
import me.ahn.management.util.HttpUtility;
import me.ahn.management.util.MD5Util;
import me.ahn.management.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping(value = "/api/user/")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PassToken
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ServerResponse login(HttpSession session, String USERNAME, String PASSWORD) {
        if (StringUtils.isEmpty(USERNAME) || StringUtils.isEmpty(PASSWORD)) {
            return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
        }

        try {
            TB_USER_INFO tbUserInfo = userInfoService.login(USERNAME, PASSWORD);
            if (tbUserInfo == null) {
                return ServerResponse.createByErrorCodeMessage(99, "登录失败");
            }

            String token = TokenUtil.createToken(tbUserInfo);

            session.setAttribute(Const.CURRENT_USER, tbUserInfo);

            Map<String, Object> returnMap = new HashMap<>();
            returnMap.put("token", token);
            return ServerResponse.createBySuccess(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorCodeMessage(98, Const.Message.SELECT_ERROR);
        }
    }

    @PassToken
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ServerResponse logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccessMessage("成功退出");
    }

    @UserLoginToken
    @RequestMapping(value = "info", method = RequestMethod.POST)
    public ServerResponse selectTB_USER_INFOByPk(Integer USER_SEQ) {
        try {
            TB_USER_INFO tbUserInfo = userInfoService.selectTB_USER_INFOByPk(USER_SEQ);
            if (tbUserInfo == null) {
                return ServerResponse.createByErrorCodeMessage(99, Const.Message.SELECT_ERROR);
            }
            return ServerResponse.createBySuccess(tbUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorCodeMessage(98, Const.Message.SELECT_ERROR);
        }
    }

    @UserLoginToken
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ServerResponse updateTB_USER_INFO(HttpServletRequest request) {
        Box box = HttpUtility.getBox(request);
        TB_USER_INFO tbUserInfo = new TB_USER_INFO();
        box.copyToEntity(tbUserInfo);

        try {
            userInfoService.updateTB_USER_INFO(tbUserInfo);
            return ServerResponse.createBySuccessMessage(Const.Message.UPDATE_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createBySuccessMessage(Const.Message.UPDATE_ERROR);
        }
    }

    @UserLoginToken
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ServerResponse deleteTB_USER_INFO(HttpServletRequest request) {
        Box box = HttpUtility.getBox(request);
        TB_USER_INFO tbUserInfo = new TB_USER_INFO();
        box.copyToEntity(tbUserInfo);
        try {
            userInfoService.deleteTB_USER_INFO(tbUserInfo);
            return ServerResponse.createBySuccessMessage(Const.Message.UPDATE_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createBySuccessMessage(Const.Message.UPDATE_ERROR);
        }
    }

    @PassToken
    @RequestMapping(value = "check_username", method = RequestMethod.POST)
    public ServerResponse countTB_USER_INFOByUSERNAME(HttpServletRequest request) {
        Box box = HttpUtility.getBox(request);
        TB_USER_INFO tbUserInfo = new TB_USER_INFO();
        box.copyToEntity(tbUserInfo);

        try {
            tbUserInfo = userInfoService.countTB_USER_INFOByUSERNAME(tbUserInfo);
            log.info("{}", tbUserInfo.toString());
            if (tbUserInfo.getALL_COUNT() > 0) {
                return ServerResponse.createByErrorMessage("此用户名已存在");
            }
            return ServerResponse.createBySuccessMessage("可以使用此用户名");
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

    @PassToken
    @RequestMapping(value = "check_email", method = RequestMethod.POST)
    public ServerResponse countTB_USER_INFOByEMAIL(HttpServletRequest request) {
        Box box = HttpUtility.getBox(request);
        TB_USER_INFO tbUserInfo = new TB_USER_INFO();
        box.copyToEntity(tbUserInfo);

        try {
            tbUserInfo = userInfoService.countTB_USER_INFOByEMAIL(tbUserInfo);
            if (tbUserInfo.getALL_COUNT() > 0) {
                return ServerResponse.createByErrorMessage("此邮件已存在");
            }
            return ServerResponse.createBySuccessMessage("可以使用此邮件");
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

    @UserLoginToken
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ServerResponse selectTB_USER_INFO(HttpServletRequest request,
                                             @RequestParam(value = "PAGE_NUM", defaultValue = "1") int PAGE_NUM,
                                             @RequestParam(value = "PAGE_SIZE", defaultValue = "10") int PAGE_SIZE) {
        Box box = HttpUtility.getBox(request);
        TB_USER_INFO tbUserInfo = new TB_USER_INFO();
        box.copyToEntity(tbUserInfo);
        tbUserInfo.setPAGE_NUM(PAGE_NUM);
        tbUserInfo.setPAGE_SIZE(PAGE_SIZE);

        try {
            PageInfo pageResult = userInfoService.selectTB_USER_INFO(tbUserInfo);
            return ServerResponse.createBySuccess(pageResult);
        } catch (Exception e) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        }
    }

    @PassToken
    @RequestMapping(value = "create")
    public ServerResponse insertTB_USER_INFO(HttpServletRequest request) {
        Box box = HttpUtility.getBox(request);
        TB_USER_INFO tbUserInfo = new TB_USER_INFO();
        box.copyToEntity(tbUserInfo);

        try {
            if(tbUserInfo == null || tbUserInfo.getUSERNAME() == null) {
                return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
            }

            tbUserInfo.setPASSWORD(MD5Util.MD5EncodeUtf8(tbUserInfo.getPASSWORD()));
            tbUserInfo.setROLE(Const.Role.ROLE_USER);
            tbUserInfo.setROLE_NO(Const.RoleNo.ROLE_USER);
            tbUserInfo.setUSE_YN(Const.Status.ACTIVE);
            userInfoService.insertTB_USER_INFO(tbUserInfo);
            return ServerResponse.createBySuccessMessage(Const.Message.SAVE_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SAVE_ERROR);
        }

    }

}
