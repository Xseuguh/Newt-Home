package org.wtg.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CGUController {
	@RequestMapping(value = "/cgu")
	public String mesCGU() {
		return "CGU";
	}
}
