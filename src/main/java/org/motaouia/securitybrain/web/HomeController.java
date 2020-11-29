package org.motaouia.securitybrain.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping(value = "/")
	public String homePage() {
		System.out.println("KoKOO");
		return ("<h1>Welcome</h1>");
	}

	@GetMapping(value = "/admin")
	public String homePageAdmin() {
		return ("<h1>Welcome ADMIN</h1>");
	}

	@GetMapping(value = "/user")
	public String homePageUser() {
		return ("<h1>Welcome User</h1>");
	}

}
