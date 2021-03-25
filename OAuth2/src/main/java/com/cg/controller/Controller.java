package com.cg.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

		@GetMapping("/viewOauth")
		public String view() {
			return "Welcome";
		}
		
		@RequestMapping("user")
		@ResponseBody
		public Principal user(Principal principal) {
			return principal;
		}
	}

