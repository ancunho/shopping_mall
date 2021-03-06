package me.ahn.management.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.ahn.management.common.Const;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.TB_SHIPPING;
import me.ahn.management.service.ShippingService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private SqlSession sqlSession;

    public ServerResponse insertTB_SHIPPING(TB_SHIPPING tbShipping) throws Exception {
        try {
            if (tbShipping.getUSER_SEQ() == null) {
                return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
            }

            int defaultCount = sqlSession.selectOne("AHNSTUDIO.shipping.selectDefaultCount", tbShipping.getUSER_SEQ());
            if (defaultCount > 0) {
                sqlSession.update("AHNSTUDIO.shipping.updateDefaultShippingByUserId", tbShipping);
            } else {
                tbShipping.setIS_DEFAULT("1");
            }

//            int insertCount = shippingMapper.insert(shipping);
            int insertCount = sqlSession.insert("AHNSTUDIO.shipping.insertTB_SHIPPING", tbShipping);
            if (insertCount > 0) {
                return ServerResponse.createBySuccessMessage(Const.Message.SAVE_OK);
            }
            return ServerResponse.createByErrorMessage(Const.Message.SAVE_ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(Const.Message.SAVE_ERROR);
        }
    }

    public ServerResponse selectDefaultShippingByUserId(Integer USER_SEQ) {
        if (USER_SEQ == null) {
            return ServerResponse.createByErrorMessage(Const.Message.PARAMETER_ERROR);
        }

        TB_SHIPPING tbShipping = sqlSession.selectOne("AHNSTUDIO.shipping.selectDefaultShippingByUserId", USER_SEQ);
        return ServerResponse.createBySuccess(tbShipping);
    }

    public TB_SHIPPING selectTB_SHIPPINGByPk(Integer SHIPPING_SEQ) throws Exception {
        if (SHIPPING_SEQ == null) {
            return null;
        }

        TB_SHIPPING tbShipping = sqlSession.selectOne("AHNSTUDIO.shipping.selectTB_SHIPPINGByPk", SHIPPING_SEQ);
        return tbShipping;
    }

    public List<TB_SHIPPING> selectTB_SHIPPINGByUSER_SEQ(Integer USER_SEQ) throws Exception {
        List<TB_SHIPPING> lstTB_SHIPPING = sqlSession.selectList("AHNSTUDIO.shipping.selectTB_SHIPPINGByUSER_SEQ", USER_SEQ);
        return lstTB_SHIPPING;
    }

    @Transactional
    public void updateTB_SHIPPING(TB_SHIPPING tbShipping) throws Exception {
        int defaultCount = sqlSession.selectOne("AHNSTUDIO.shipping.selectDefaultCount", tbShipping.getUSER_SEQ());
        if (defaultCount > 0) {
            sqlSession.update("AHNSTUDIO.shipping.updateDefaultShippingByUserId", tbShipping);
        } else {
            tbShipping.setIS_DEFAULT("1");
        }
        sqlSession.update("AHNSTUDIO.shipping.updateTB_SHIPPING", tbShipping);
    }

}
