 package org.wtg;


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
		 ApplicationContext ctx = SpringApplication.run(NewtHomeApplication.class, args);
	    
		OffresRepository offresDao=ctx.getBean(OffresRepository.class);
		offresDao.save(new Offres((long)1, "Maison secondaire aux pays bas", "Une belle maison","Pays bas",
				"Rotterdam", 1561, "rue des Hollandais volant", Date.valueOf("2021-05-05"),Date.valueOf("2021-05-05"), false));
		offresDao.save(new Offres((long)1, "Maison en Suede", "Une maison au pays du pere noel. Bataille de boule de neige en perspective. N oubliez pas de prendre vos echarpes, et vos mitaines!","Suede",
				"Stockholm", 1561, "avenue du pere noel", Date.valueOf("2021-05-05"),Date.valueOf("2021-05-05"), false));
		offresDao.save(new Offres((long)1, "Maison en Suede", "Une maison au pays du pere noel. Bataille de boule de neige en perspective. N oubliez pas de prendre vos echarpes, et vos mitaines!","Suede",
				"Stockholm", 1561, "avenue du pere noel", Date.valueOf("2021-05-05"),Date.valueOf("2021-05-05"), false));
		offresDao.save(new Offres((long)1, "Maison en INDE", "Une maison pays du the!","Inde",
				"New Delhi", 1561, "avenue de Gandhi", Date.valueOf("2021-05-05"),Date.valueOf("2021-05-05"), false));
		
		
		LiaisonOffreContrainteRepository liaisonOffreContrainteDao=ctx.getBean(LiaisonOffreContrainteRepository.class);
		LiaisonOffreServiceRepository liaisonOffreServiceDao=ctx.getBean(LiaisonOffreServiceRepository.class);

		liaisonOffreContrainteDao.save(new LiaisonOffreContrainte((long)1,(long)1));
		liaisonOffreContrainteDao.save(new LiaisonOffreContrainte((long)1,(long)2));
		liaisonOffreContrainteDao.save(new LiaisonOffreContrainte((long)1,(long)3));
		liaisonOffreContrainteDao.save(new LiaisonOffreContrainte((long)1,(long)4));
		
		liaisonOffreContrainteDao.save(new LiaisonOffreContrainte((long)2,(long)1));
		liaisonOffreContrainteDao.save(new LiaisonOffreContrainte((long)2,(long)2));
		
		UserInfoRepository userDao=ctx.getBean(UserInfoRepository.class);
		userDao.save(new UserInfo("Doe", "John", Date.valueOf("1914-05-05"), "john.doe@gmail.com", "johnjohn", false));
	}

}
