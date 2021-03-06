package me.ahn.management.controller.portal;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.annotation.PassToken;
import me.ahn.management.common.Const;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.TB_USER_INFO;
import me.ahn.management.service.UserInfoService;
import me.ahn.management.util.Box;
import me.ahn.management.util.HttpUtility;
import me.ahn.management.util.MD5Util;
import me.ahn.management.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping(value = "/api/miniapp/user/")
public class UserInfoMiniappController {

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

//            Map<String, Object> returnMap = new HashMap<>();
//            returnMap.put("token", token);
            return ServerResponse.createBySuccess(tbUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorCodeMessage(98, Const.Message.SELECT_ERROR);
        }
    }

    @PassToken
    @RequestMapping(value = "register")
    public ServerResponse register(HttpServletRequest request, @RequestBody TB_USER_INFO tbUserInfo) {
        log.info(">>>>" + tbUserInfo.toString());

        try {
            if(tbUserInfo.getUSERNAME() == null) {
                return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
            }

            tbUserInfo.setPASSWORD(MD5Util.MD5EncodeUtf8(tbUserInfo.getPASSWORD()));
            tbUserInfo.setROLE(Const.Role.ROLE_USER);
            tbUserInfo.setROLE_NO(Const.RoleNo.ROLE_USER);
            tbUserInfo.setUSE_YN(Const.Status.ACTIVE);

            int resultCount = userInfoService.countTB_USER_INFOByUSERNAMERETURNInt(tbUserInfo);
            if (resultCount > 0) {
                return ServerResponse.createByErrorMessage("此用户名已存在");
            }

            System.out.println(">>>>" + tbUserInfo.toString());
            userInfoService.insertTB_USER_INFOForWechat(tbUserInfo);
            return ServerResponse.createBySuccessMessage(Const.Message.SAVE_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SAVE_ERROR);
        }

    }

}
