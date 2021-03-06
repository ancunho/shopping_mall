package me.ahn.management.controller;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    public void saveError(HttpServletRequest request, String errCode, Exception e) {
        e.printStackTrace();
    }

}
