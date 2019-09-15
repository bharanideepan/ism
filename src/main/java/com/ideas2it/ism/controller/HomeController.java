package com.ideas2it.ism.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ideas2it.ism.common.Constant;

@Controller
public class HomeController {
    
	/**
     * When the logo is pressed the home page is called. 
     */
    @RequestMapping(value = Constant.INDEX, method = RequestMethod.GET)  
    private String dispatchIndexPage() {
        return Constant.INDEX_JSP;
    }


}
