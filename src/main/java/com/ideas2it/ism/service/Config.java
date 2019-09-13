package com.ideas2it.ism.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Configuration
public class Config	implements Filter {
	
	String url;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
    	HttpSession session = ((HttpServletRequest) request).getSession();

    	//System.out.println(req.getServletPath() + "config" + req.getRequestURL() + session.getAttribute("user"));
    	String url = req.getServletPath();
    	System.out.println(url+"url");
    	//res.sendRedirect(url);
    	//access(req,res,url);
    	if(null == session.getAttribute("user")) {
        	//res.sendRedirect("/login");
    		RequestDispatcher dispatcher = req.getRequestDispatcher("/login");
    		//System.out.println(dispatcher+"+++++++");
            dispatcher.forward(req, res);
    		//res.sendRedirect("/login");
    		System.out.println(session.getAttribute("user")+"session");
    		//chain.doFilter(req, res);
    	} 
    	if(url != "/loginUser" &&  url == "/createUser"){
    		//access(req, res, url);
    		System.out.println("login url method");
    		chain.doFilter(req, res);
    	}
    	/*
    	if(url.equals("/login")) {
    		System.out.println("login url method");
    		chain.doFilter(req, res);
    	}
    	*/
    	if(null != session.getAttribute("user")) {
    		System.out.println("DOFILTEr");
    		chain.doFilter(req, res);
    		//access(req, res, url);
    	}

	}

    public ModelAndView access(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
    	System.out.println("Model");
    	String urls = "redirect:" + url;
    	System.out.println(urls+"urlss");
    	return new ModelAndView(urls);
    	/*
    	ModelAndView model = new ModelAndView();
    	System.out.println("sdfsd" +url);
		model.setViewName(url);
		return model;
    	*/
    }
}