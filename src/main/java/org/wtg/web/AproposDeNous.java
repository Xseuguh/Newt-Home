package org.wtg.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wtg.entities.MessageInfoAdd;

@Controller
public class AproposDeNous {
	@RequestMapping(value = "/aPropos")
	public String aProposDeNewtHome() {
		return "aPropos";
	}
}
