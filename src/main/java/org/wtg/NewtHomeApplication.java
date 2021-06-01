package org.wtg;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.sql.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.wtg.dao.LiaisonOffreContrainteRepository;
import org.wtg.dao.LiaisonOffreServiceRepository;
import org.wtg.dao.OffresRepository;
import org.wtg.dao.UserInfoRepository;
import org.wtg.entities.LiaisonOffreContrainte;
import org.wtg.entities.Offres;
import org.wtg.entities.UserInfo;
//import org.wtg.dao.MessageRepository;
//import org.wtg.dao.ContraintesRepository;
//import org.wtg.dao.ServicesRepository;
//import org.wtg.dao.UserInfoRepository;
import org.wtg.web.FileUploadController;

@SpringBootApplication
public class NewtHomeApplication {
	String uploadDirectory=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\photosAnnonces\\ReceptionFichier";

	public static void main(String[] args) {
	    String OS = System.getProperty("os.name").toLowerCase();
		if(OS.equals("windows 10")) {
			new File(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\photosAnnonces\\ReceptionFichier").mkdir();
		}
		else if(OS.equals("linux")) {
			new File(System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/ReceptionFichier").mkdir();
		}
		else if(OS.equals("linux")) {
			new File(System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/ReceptionFichier").mkdir();
		}
		else {
		}
		SpringApplication.run(NewtHomeApplication.class, args);
	   
	}

}
