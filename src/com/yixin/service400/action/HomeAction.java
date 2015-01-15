package com.yixin.service400.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport {

	public String index(){
		return "index";
	}
}
