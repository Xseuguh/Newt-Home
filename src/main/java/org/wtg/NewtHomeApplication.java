 package org.wtg;


import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewtHomeApplication {

	public static void main(String[] args) {
		String path = "/src/main/resources/static/images/photosAnnonces/ReceptionFichier";
	    String OS = System.getProperty("os.name").toLowerCase();

		if (!OS.equals("mac os x") && !OS.equals("linux")) {
			path = path.replace("/", "\\");
		}

		new File(System.getProperty("user.dir") + path).mkdir();
		SpringApplication.run(NewtHomeApplication.class, args);
	}

}
