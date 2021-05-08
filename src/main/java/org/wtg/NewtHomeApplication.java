package org.wtg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.wtg.dao.OffresRepository;
//import org.wtg.dao.MessageRepository;
//import org.wtg.dao.ContraintesRepository;
//import org.wtg.dao.ServicesRepository;
//import org.wtg.dao.UserInfoRepository;

@SpringBootApplication
public class NewtHomeApplication{
	public static void main(String[] args)
	{
	    SpringApplication.run(NewtHomeApplication.class, args);
	    //ContraintesRepository contrainteDao = ctx.getBean(ContraintesRepository.class);
		//contrainteDao.findAll().forEach(c->System.out.println(c.getNom_contrainte()));
		//ServicesRepository serviceDao = ctx.getBean(ServicesRepository.class);
		//serviceDao.findAll().forEach(c->System.out.println(c.getNom_service()));
	    //MessageRepository messageDao = ctx.getBean(MessageRepository.class);
	    //messageDao.findAll().forEach(c->System.out.println(c.getMsg()));
	    //UserInfoRepository userInfoDao = ctx.getBean(UserInfoRepository.class);
	    //userInfoDao.findAll().forEach(c->System.out.println(c.getNom()));
	    //OffresRepository offresDao = ctx.getBean(OffresRepository.class);
	    //offresDao.findAll().forEach(c->System.out.println(c.getTitre()));	
	}

}
