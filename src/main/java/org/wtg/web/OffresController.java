package org.wtg.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.wtg.dao.ContraintesRepository;
import org.wtg.dao.LiaisonOffreContrainteRepository;
import org.wtg.dao.LiaisonOffreServiceRepository;
import org.wtg.dao.OffresRepository;
import org.wtg.entities.Contraintes;
import org.wtg.entities.LiaisonOffreContrainte;
import org.wtg.entities.LiaisonOffreService;
import org.wtg.entities.Offres;

/****Partie 1 afficher les utilisateurs: lie aux administrateurs***/
@Controller
public class OffresController {
	
	@Autowired
	private OffresRepository offresDao;
	@Autowired
	private ContraintesRepository contrainteDao;
	@Autowired
	private LiaisonOffreContrainteRepository liaisonOffreContrainteDao;
	@Autowired
	private LiaisonOffreServiceRepository liaisonOffreServiceDao;

	//************For User*******************/
	@RequestMapping(value = "/offres/search")
	public String search(Model model, @RequestParam(name = "motCle", defaultValue = "") String mc) {
		List<Offres> offres = offresDao.findByName("%" + mc + "%");
		model.addAttribute("offres", offres);
		model.addAttribute("motC", mc);
		return "listOffres";
	}

	@RequestMapping(value = "/annonce/ajout")
	public String add(Model model,
			@RequestParam(name = "descriptionAnnonce", defaultValue = "") String descriptionAnnonce,
			@RequestParam(name = "titreAnnonce", defaultValue = "") String titreAnnonce,
			@RequestParam(name = "adresseAnnonce", defaultValue = "") String adresseAnnonce,
			@RequestParam(name = "codePostalAnnonce", defaultValue = "") Integer codePostalAnnonce,
			@RequestParam(name = "villeAnnonce", defaultValue = "") String villeAnnonce,
			@RequestParam(name = "paysAnnonce", defaultValue = "") String paysAnnonce,
			@RequestParam(name = "date_debut_string", defaultValue = "") String date_debut_string,
			@RequestParam(name = "date_limite_string", defaultValue = "") String date_limite_string,
			@RequestParam(name= "deuxEnfantsMax",defaultValue="") String deuxEnfantsMax,
			@RequestParam(name="animaux",defaultValue="")String animaux,
			@RequestParam(name="enfants",defaultValue="")String pasDenfantsAutorises,
			@RequestParam(name="bruitVingtTrois",defaultValue="")String pasDeBruitApres23H,
			@RequestParam(name="cigarette",defaultValue="")String pasDeCigarettes,
			@RequestParam(name="arroserPlante",defaultValue="") String arroserPlante,
			@RequestParam(name="garderLesAnimaux",defaultValue="") String garderLesAnimaux,
			@RequestParam(name="nettoyerLaMaison",defaultValue="") String nettoyerLaMaison){
		
		String[] valuesStringInserted= {descriptionAnnonce,titreAnnonce,adresseAnnonce,
				villeAnnonce,paysAnnonce,date_debut_string,date_limite_string};
		
		String[] valuesCheckboxConstraint= {deuxEnfantsMax,animaux,pasDenfantsAutorises,
				pasDeBruitApres23H,pasDeCigarettes};
		
		String[] valuesCheckboxService= {arroserPlante,garderLesAnimaux,
				nettoyerLaMaison};
		
		boolean areTheStringValidated=areTheStringValid(valuesStringInserted);
		boolean isCorrectForConstraintAndService=isThereConstraintAndService(valuesCheckboxConstraint,valuesCheckboxService);
		boolean isInsertable=areTheStringValidated && isCorrectForConstraintAndService;
		/*
		Warning important to avoid to insert null values
		we check everything except the codePostal
		*/
		if (isInsertable) {

			Date date_debut = Date.valueOf(date_debut_string);
			Date date_limite = Date.valueOf(date_limite_string);

			Offres offresAjoutee = new Offres((long) 118218, titreAnnonce, descriptionAnnonce, paysAnnonce,
					villeAnnonce, codePostalAnnonce, adresseAnnonce, date_debut, date_limite, false);
			offresDao.save(offresAjoutee);
			
			////////////////Liaison Contraintes////////////////
			//
			//we get the last id inserted in order to add our service and constraint
			List<Offres> listLastIdOffre=offresDao.findByIdLastOffre("");
			Long numberElement=listLastIdOffre.get(0).getId_offre();
			
			//2 enfants max par logements
			if(!deuxEnfantsMax.equals("")) {
				LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)numberElement,(long)3);
				liaisonOffreContrainteDao.save(liaisonOffreContrainte);
			}
			//pas d animaux
			if(!animaux.equals("")) {
				LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)numberElement,(long)5);
				liaisonOffreContrainteDao.save(liaisonOffreContrainte);
			}
			//pas d enfants autorises
			if(!pasDenfantsAutorises.equals("")) {
				LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)numberElement,(long)4);
				liaisonOffreContrainteDao.save(liaisonOffreContrainte);
			}
			if(!pasDeBruitApres23H.equals("")) {
				LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)numberElement,(long)2);
				liaisonOffreContrainteDao.save(liaisonOffreContrainte);
			}
			if(!pasDeCigarettes.equals("")) {
				LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)numberElement,(long)1);
				liaisonOffreContrainteDao.save(liaisonOffreContrainte);
			}
			
			////////////////Liaison Services////////////////
			if(!arroserPlante.equals("")) {
				LiaisonOffreService liaisonOffreService=new LiaisonOffreService((long)numberElement,(long) 2);
				liaisonOffreServiceDao.save(liaisonOffreService);
			}
			if(!garderLesAnimaux.equals("")) {
				LiaisonOffreService liaisonOffreService=new LiaisonOffreService((long)numberElement,(long) 1);
				liaisonOffreServiceDao.save(liaisonOffreService);
			}
			if(!nettoyerLaMaison.equals("")) {
				LiaisonOffreService liaisonOffreService=new LiaisonOffreService((long)numberElement,(long) 3);
				liaisonOffreServiceDao.save(liaisonOffreService);
			}
			/////////IMAGE UPLOAD/////////////
			writeImagesPermanently(numberElement);
		}
		return "Utilisateur_ajouterAnnonce";
	}
	
	public boolean areTheStringValid(String[] valuesStringInserted) {
		boolean isItValidAd=true;
		for(String value:valuesStringInserted) {
			if(value.equals("")) {
				isItValidAd=false;
			}
		}
		return isItValidAd;
	}
	
	public boolean isThereConstraintAndService(String[] valuesCheckboxConstraint,String[] valuesCheckboxService) {
		boolean isItValidAd=false;
		boolean isThereAnConstraint=false;
		boolean isThereAnService=false;
		for(String value:valuesCheckboxConstraint) {
			if(!value.equals("")){
				isThereAnConstraint=true;
			}
		}
		for(String value:valuesCheckboxService) {
			if(!value.equals("")){
				isThereAnService=true;
			}
		}
		isItValidAd=isThereAnConstraint && isThereAnService;
		return isItValidAd;
	}
	
	
	/*
	 * if we return true we have written the files
	 * If not we return false
	 */
	public boolean writeImagesPermanently(Long numberElement) {
		boolean isItSuccess=true;
	    String OS = System.getProperty("os.name").toLowerCase();
		if(OS.equals("windows 10")) {
			String userDir=System.getProperty("user.dir");
			String whereForUser=userDir+"\\src\\main\\resources\\static\\images\\photosAnnonces\\"+numberElement;
	        File folder = new File(whereForUser);
	        boolean res=folder.mkdir();
			File folderForReceivedFiles = new File(userDir+"\\src\\main\\resources\\static\\images\\photosAnnonces\\ReceptionFichier\\");
	        String listOfNameOfTheReceivedFiles[] = folderForReceivedFiles.list();
	        
			for(String element:listOfNameOfTheReceivedFiles) {
				Path source=Paths.get(userDir+"\\src\\main\\resources\\static\\images\\photosAnnonces\\ReceptionFichier\\"+element);
				Path destination=Paths.get(whereForUser+"\\"+element);
	
				try {
					Files.copy(source, destination,StandardCopyOption.REPLACE_EXISTING);
					Files.delete(source);
				} catch (IOException e) {
					e.printStackTrace();
					isItSuccess=false;
				}
			}
		}
		else if(OS.equals("linux")) {
			String userDir=System.getProperty("user.dir");
			String whereForUser=userDir+"/src/main/resources/static/images/photosAnnonces/"+numberElement;
	        File folder = new File(whereForUser);
	        boolean res=folder.mkdir();
			File folderForReceivedFiles = new File(userDir+"/src/main/resources/static/images/photosAnnonces/ReceptionFichier/");
	        String listOfNameOfTheReceivedFiles[] = folderForReceivedFiles.list();
	        
			for(String element:listOfNameOfTheReceivedFiles) {
				Path source=Paths.get(userDir+"/src/main/resources/static/images/photosAnnonces/ReceptionFichier/"+element);
				Path destination=Paths.get(whereForUser+"/"+element);
	
				try {
					Files.copy(source, destination,StandardCopyOption.REPLACE_EXISTING);
					Files.delete(source);
				} catch (IOException e) {
					e.printStackTrace();
					isItSuccess=false;
				}
			}
		}
		else if(OS.equals("mac os x")) {
			String userDir=System.getProperty("user.dir");
			String whereForUser=userDir+"/src/main/resources/static/images/photosAnnonces/"+numberElement;
	        File folder = new File(whereForUser);
	        boolean res=folder.mkdir();
			File folderForReceivedFiles = new File(userDir+"/src/main/resources/static/images/photosAnnonces/ReceptionFichier/");
	        String listOfNameOfTheReceivedFiles[] = folderForReceivedFiles.list();
	        
			for(String element:listOfNameOfTheReceivedFiles) {
				Path source=Paths.get(userDir+"/src/main/resources/static/images/photosAnnonces/ReceptionFichier/"+element);
				Path destination=Paths.get(whereForUser+"/"+element);
	
				try {
					Files.copy(source, destination,StandardCopyOption.REPLACE_EXISTING);
					Files.delete(source);
				} catch (IOException e) {
					e.printStackTrace();
					isItSuccess=false;
				}
			}
		}
		return isItSuccess;
	}
}
