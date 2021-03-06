package me.ahn.management.controller;

import lombok.extern.slf4j.Slf4j;
import me.ahn.management.annotation.PassToken;
import me.ahn.management.annotation.UserLoginToken;
import me.ahn.management.common.PageConstants;
import me.ahn.management.model.TB_USER_INFO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/")
public class PageURLController {

    @UserLoginToken
    @RequestMapping(value = "")
    public String to_index(HttpSession session) {
        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
        if (currentUser != null) {
            return "redirect:" + PageConstants.URL_BACKEND_HOME;
        } else {
            return "redirect:" + PageConstants.URL_BACKEND_LOGIN;
        }
    }

    @UserLoginToken
    @RequestMapping(value = PageConstants.URL_BACKEND_HOME)
    public String to_backend_home(HttpSession session) {
        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
        if (currentUser != null) {
            return PageConstants.TEMPLATE_BACKEND_HOME;
        } else {
            return "redirect:" + PageConstants.URL_BACKEND_LOGIN;
        }
    }

    @PassToken
    @RequestMapping(value = PageConstants.URL_BACKEND_LOGIN)
    public String to_login_page(HttpSession session) {
//        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
//        if (currentUser != null) {
//            return "redirect:" + PageConstants.URL_BACKEND_HOME;
//        } else {
//            return PageConstants.TEMPLATE_BACKEND_LOGIN;
//        }
        return PageConstants.TEMPLATE_BACKEND_LOGIN;
    }

    @PassToken
    @RequestMapping(value = PageConstants.URL_FRONT_REGIST)
    public String to_regist_page(HttpSession session) {
        return PageConstants.TEMPLATE_FRONT_REGIST;
    }

    /**
     * User
     */
    @UserLoginToken
    @RequestMapping(value = PageConstants.URL_BACKEND_USERINFO_LIST)
    public String to_user_list(HttpSession session, Model model) {
        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
        if(currentUser == null) {
            return "redirect:" + PageConstants.URL_BACKEND_LOGIN;
        }
        model.addAttribute("activeURL", PageConstants.TEMPLATE_BACKEND_USERINFO_LIST);
        return PageConstants.TEMPLATE_BACKEND_USERINFO_LIST;
    }

    @UserLoginToken
    @RequestMapping(value = PageConstants.URL_BACKEND_USERINFO_DETAIL)
    public String to_user_detail(HttpSession session, Model model) {
        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
        if(currentUser == null) {
            return "redirect:" + PageConstants.URL_BACKEND_LOGIN;
        }
        model.addAttribute("activeURL", PageConstants.TEMPLATE_BACKEND_USERINFO_LIST);
        return PageConstants.TEMPLATE_BACKEND_USERINFO_DETAIL;
    }

    @UserLoginToken
    @RequestMapping(value = PageConstants.URL_BACKEND_USERINFO_EDIT)
    public String to_user_edit(HttpSession session, Model model) {
        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
        if(currentUser == null) {
            return "redirect:" + PageConstants.URL_BACKEND_LOGIN;
        }
        model.addAttribute("activeURL", PageConstants.TEMPLATE_BACKEND_USERINFO_LIST);
        return PageConstants.TEMPLATE_BACKEND_USERINFO_EDIT;
    }

    /**
     * Product
     */
    @UserLoginToken
    @RequestMapping(value = PageConstants.URL_BACKEND_PRODUCT_LIST)
    public String to_product_list(HttpSession session, Model model) {
        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
        if(currentUser == null) {
            return "redirect:" + PageConstants.URL_BACKEND_LOGIN;
        }
        model.addAttribute("activeURL", PageConstants.TEMPLATE_BACKEND_PRODUCT_LIST);
        return PageConstants.TEMPLATE_BACKEND_PRODUCT_LIST;
    }



    @UserLoginToken
    @RequestMapping(value = PageConstants.URL_BACKEND_PRODUCT_DETAIL)
    public String to_product_detail(HttpSession session, Model model) {
        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
        if(currentUser == null) {
            return "redirect:" + PageConstants.URL_BACKEND_LOGIN;
        }
        model.addAttribute("activeURL", PageConstants.TEMPLATE_BACKEND_PRODUCT_LIST);
        return PageConstants.TEMPLATE_BACKEND_PRODUCT_DETAIL;
    }

    @UserLoginToken
    @RequestMapping(value = PageConstants.URL_BACKEND_PRODUCT_CREATE)
    public String to_product_create(HttpSession session, Model model) {
        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
        if(currentUser == null) {
            return "redirect:" + PageConstants.URL_BACKEND_LOGIN;
        }
        model.addAttribute("activeURL", PageConstants.TEMPLATE_BACKEND_PRODUCT_LIST);
        model.addAttribute("PRODUCT_SEQ", "");
        return PageConstants.TEMPLATE_BACKEND_PRODUCT_EDIT;
    }

    @UserLoginToken
    @RequestMapping(value = PageConstants.URL_BACKEND_PRODUCT_EDIT + "/{PRODUCT_SEQ}", method = RequestMethod.GET)
    public String to_product_edit(HttpSession session, Model model, @PathVariable("PRODUCT_SEQ") Integer PRODUCT_SEQ) {
        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
        if(currentUser == null) {
            return "redirect:" + PageConstants.URL_BACKEND_LOGIN;
        }
        model.addAttribute("activeURL", PageConstants.TEMPLATE_BACKEND_PRODUCT_LIST);
        model.addAttribute("PRODUCT_SEQ", PRODUCT_SEQ);
        return PageConstants.TEMPLATE_BACKEND_PRODUCT_EDIT;
    }

    /**
     * Order
     */
    @UserLoginToken
    @RequestMapping(value = PageConstants.URL_BACKEND_ORDER_LIST)
    public String to_order_list(HttpSession session, Model model) {
        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
        if(currentUser == null) {
            return "redirect:" + PageConstants.URL_BACKEND_LOGIN;
        }
        model.addAttribute("activeURL", PageConstants.TEMPLATE_BACKEND_ORDER_LIST);
        return PageConstants.TEMPLATE_BACKEND_ORDER_LIST;
    }

    @UserLoginToken
    @RequestMapping(value = PageConstants.URL_BACKEND_ORDER_EDIT + "/{ORDER_SEQ}", method = RequestMethod.GET)
    public String to_order_edit(HttpSession session, Model model, @PathVariable("ORDER_SEQ") Integer ORDER_SEQ) {
        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
        if(currentUser == null) {
            return "redirect:" + PageConstants.URL_BACKEND_LOGIN;
        }
        model.addAttribute("activeURL", PageConstants.TEMPLATE_BACKEND_ORDER_EDIT);
        model.addAttribute("ORDER_SEQ", ORDER_SEQ);
        return PageConstants.TEMPLATE_BACKEND_ORDER_EDIT;
    }

    /**
     * Settings
     */
    @UserLoginToken
    @RequestMapping(value = PageConstants.URL_BACKEND_SETTINGS_CATEGORY, method = RequestMethod.GET)
    public String to_setting_category(HttpSession session, Model model) {
        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
        if(currentUser == null) {
            return "redirect:" + PageConstants.URL_BACKEND_LOGIN;
        }
        model.addAttribute("activeURL", "settings");
        return PageConstants.TEMPLATE_BACKEND_SETTINGS_CATEGORY;
    }

    @UserLoginToken
    @RequestMapping(value = PageConstants.URL_BACKEND_SETTINGS_MAINIMAGE, method = RequestMethod.GET)
    public String to_setting_thumbnail(HttpSession session, Model model) {
        TB_USER_INFO currentUser = (TB_USER_INFO) session.getAttribute("currentUser");
        if(currentUser == null) {
            return "redirect:" + PageConstants.URL_BACKEND_LOGIN;
        }
        model.addAttribute("activeURL", "settings");
        return PageConstants.TEMPLATE_BACKEND_SETTINGS_MAINIMAGE;
    }

}
