package me.ahn.management.controller.backend;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.annotation.PassToken;
import me.ahn.management.common.Const;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.TB_COMMON_CODE;
import me.ahn.management.service.CommonService;
import me.ahn.management.util.Box;
import me.ahn.management.util.HttpUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping(value = "/api/common/")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @PassToken
    @RequestMapping(value = "listByCodeId", method = RequestMethod.POST)
    public ServerResponse selectTB_COMMON_CODEByCODE_ID(HttpServletRequest request) {
        Box box = HttpUtility.getBox(request);
        TB_COMMON_CODE tbCommonCode = new TB_COMMON_CODE();
        box.copyToEntity(tbCommonCode);

        try {
            List<TB_COMMON_CODE> lstTB_COMMON_CODE = commonService.selectTB_COMMON_CODEByCODE_ID(tbCommonCode);
            return ServerResponse.createBySuccess(lstTB_COMMON_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }

    @PassToken
    @RequestMapping(value = "listByOPTION01", method = RequestMethod.POST)
    public ServerResponse selectTB_COMMON_CODEByOPTION01(HttpServletRequest request) {
        Box box = HttpUtility.getBox(request);
        TB_COMMON_CODE tbCommonCode = new TB_COMMON_CODE();
        box.copyToEntity(tbCommonCode);

        try {
            List<TB_COMMON_CODE> lstTB_COMMON_CODE = commonService.selectTB_COMMON_CODEByOPTION01(tbCommonCode);
            return ServerResponse.createBySuccess(lstTB_COMMON_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SELECT_ERROR);
        }
    }
}
