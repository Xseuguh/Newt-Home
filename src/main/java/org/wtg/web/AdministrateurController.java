package org.wtg.web;

import java.util.List;
import java.util.TreeSet;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.LiaisonOffreContrainteRepository;
import org.wtg.dao.LiaisonOffreServiceRepository;
import org.wtg.dao.OffresRepository;
import org.wtg.dao.UserInfoRepository;
import org.wtg.entities.LiaisonOffreContrainte;
import org.wtg.entities.LiaisonOffreService;
import org.wtg.entities.Offres;
import org.wtg.entities.UserInfo;

@Controller
public class AdministrateurController {
	@Autowired
	private OffresRepository offresDao;
	@Autowired
	private LiaisonOffreContrainteRepository liaisonOffreContrainteDao;
	@Autowired
	private LiaisonOffreServiceRepository liaisonOffreServiceDao;

	@RequestMapping(value = "/Admin/Gestion")
	public String lienAdminGestion() {
		return "Admin_interface";
	}
	
	

	@RequestMapping(value = "/Admin/Gestion/Annonce")
	public String lienAdminGestionAnnonce(Model model, @RequestParam(name = "motCle", defaultValue = "") String mc) {
		List<Offres> offres = offresDao.findByName("%" + mc + "%");
		model.addAttribute("offres", offres);
		model.addAttribute("motC", mc);
		return "Admin_interfaceAnnonce";
	}
	
	@RequestMapping(value = "/Admin/Supprimer/Annonce")
	public String delete(Model model, @RequestParam(name = "ref", defaultValue = "") Long id_offre,
			@RequestParam(name = "mc", defaultValue = "") String mc) {
		offresDao.deleteById(id_offre);
		return "redirect:/Admin/Gestion/Annonce?motCle=" + mc;
	}	
	
	@RequestMapping(value = "/Admin/Zoom/Annonce")
	public String zoom(Model model, @RequestParam(name = "ref", defaultValue = "") Long id_offre,
			@RequestParam(name = "mc", defaultValue = "") String mc) {
		//Integer integerIdValue=Integer.parseInt(id_offre);
		//Long longIdValue=(long)integerIdValue;
		List<Offres> offresZoom = offresDao.findByIdOffre(id_offre);
		model.addAttribute("offresZoom", offresZoom);
		return "Admin_interfaceZoomAnnonce";
	}
	
	@RequestMapping(value = "/Admin/Modifier/Annonce")
	public String editer(Model model, @RequestParam(name = "ref", defaultValue = "") Long id_offre,
			@RequestParam(name = "mc", defaultValue = "") String mc) {
		List<Offres> offresZoom = offresDao.findByIdOffre(id_offre);
		model.addAttribute("offresZoom", offresZoom);
		
		List <LiaisonOffreService> listeLiaisonOffresServices= liaisonOffreServiceDao.findByIdOffre(id_offre);
		model.addAttribute("nombreDeService",listeLiaisonOffresServices.size());
		if(listeLiaisonOffresServices.size()==0) {
		}
		else if(listeLiaisonOffresServices.size()==1) {
			model.addAttribute("serviceEnvoye1",listeLiaisonOffresServices.get(0).getId_service());
		}
		else if(listeLiaisonOffresServices.size()==2) {
			int resultat=1+2+3;
			resultat=(int)(resultat-listeLiaisonOffresServices.get(0).getId_service());
			resultat=(int)(resultat-listeLiaisonOffresServices.get(1).getId_service());
			model.addAttribute("serviceEnvoye1",resultat);
		}
		else if(listeLiaisonOffresServices.size()==3) {
		}
		//************
		List<LiaisonOffreContrainte> listeLiaisonOffresContraintes=liaisonOffreContrainteDao.findByIdOffre(id_offre);
		model.addAttribute("nombreDeContrainte",listeLiaisonOffresContraintes.size());
		if(listeLiaisonOffresContraintes.size()==0) {
		}
		else if(listeLiaisonOffresContraintes.size()==1) {
			model.addAttribute("contraintesEnvoyees1",listeLiaisonOffresContraintes.get(0).getId_contrainte());
		}
		else if(listeLiaisonOffresContraintes.size()==2) {
			int resultat=(int)(listeLiaisonOffresContraintes.get(1).getId_contrainte()*listeLiaisonOffresContraintes.get(0).getId_contrainte());
			model.addAttribute("contraintesEnvoyees1",resultat);
		}
		else if(listeLiaisonOffresContraintes.size()==3){
			TreeSet<Integer> myTreeSetOfContraintes=new TreeSet<Integer>();
			int a=(int) (listeLiaisonOffresContraintes.get(0).getId_contrainte()+0);
			int b=(int) (listeLiaisonOffresContraintes.get(1).getId_contrainte()+0);
			int c=(int) (listeLiaisonOffresContraintes.get(2).getId_contrainte()+0);

			myTreeSetOfContraintes.add(a);
			myTreeSetOfContraintes.add(b);
			myTreeSetOfContraintes.add(c);
			
			int[] listeIdContrainte=new int[5];
			listeIdContrainte[0]= 1;
			listeIdContrainte[1]= 2;
			listeIdContrainte[2]= 3;
			listeIdContrainte[3]= 4;
			listeIdContrainte[4]= 5;
			
			TreeSet<Integer> myTreeSetOfNotContraintes=new TreeSet<Integer>();
			for(Integer myInteger:listeIdContrainte) {
				if(!(myTreeSetOfContraintes.contains(myInteger))){
					myTreeSetOfNotContraintes.add((int) myInteger);
				}
			}
			int resultat=1;
			for(Integer myInteger:myTreeSetOfNotContraintes) {
				int tmp=(int) myInteger;
				resultat=resultat*tmp;
			}
			model.addAttribute("contraintesEnvoyees1",resultat);
		}
		else if(listeLiaisonOffresContraintes.size()==4){
			int resultat= (int) (15-listeLiaisonOffresContraintes.get(0).getId_contrainte());
			resultat= (int) (resultat-listeLiaisonOffresContraintes.get(1).getId_contrainte());
			resultat=(int) (resultat-listeLiaisonOffresContraintes.get(2).getId_contrainte());
			resultat=(int) (resultat-listeLiaisonOffresContraintes.get(3).getId_contrainte());
			model.addAttribute("contraintesEnvoyees1",resultat); 
		}
		else if(listeLiaisonOffresContraintes.size()==5){
		}

		return "Admin_interfaceModifierAnnonce";
	}
	
	@RequestMapping(value = "/Admin/Modifier/Annonce/ValidationDesModifications")
	public String mettreA_Jour(Model model, @RequestParam(name = "ref", defaultValue = "") Long id_offre,
			@RequestParam(name = "mc", defaultValue = "") String mc,
			@RequestParam(name="titreAnnonce", defaultValue="")String titreAnnonce,
			@RequestParam(name="descriptionAnnonce", defaultValue="")String descriptionAnnonce,
			@RequestParam(name="adresseAnnonce", defaultValue="")String adresseAnnonce,
			@RequestParam(name="codePostalAnnonce", defaultValue="")Integer codePostalAnnonce,
			@RequestParam(name="villeAnnonce", defaultValue="")String villeAnnonce,
			@RequestParam(name="paysAnnonce", defaultValue="")String paysAnnonce,
			@RequestParam(name="dateDebutAnnonce", defaultValue="")String dateDebutAnnonce,
			@RequestParam(name="dateFinAnnonce", defaultValue="")String dateFinAnnonce,
			@RequestParam(name="pourvuAnnonce",defaultValue="")String pourvuAnnonce,
			@RequestParam(name= "deuxEnfantsMax",defaultValue="") String deuxEnfantsMax,
			@RequestParam(name="animaux",defaultValue="")String animaux,
			@RequestParam(name="enfants",defaultValue="")String pasDenfantsAutorises,
			@RequestParam(name="bruitVingtTrois",defaultValue="")String pasDeBruitApres23H,
			@RequestParam(name="cigarette",defaultValue="")String pasDeCigarettes,
			@RequestParam(name="arroserPlante",defaultValue="") String arroserPlante,
			@RequestParam(name="garderLesAnimaux",defaultValue="") String garderLesAnimaux,
			@RequestParam(name="nettoyerLaMaison",defaultValue="") String nettoyerLaMaison){
		if(!(titreAnnonce.equals(""))){
			System.out.println(pourvuAnnonce);
			List<Offres> listeDesOffresMisesAJour = offresDao.findByIdOffre(id_offre);
			Offres offreMiseAJour=listeDesOffresMisesAJour.get(0);
			offreMiseAJour.setTitre(titreAnnonce);
			offreMiseAJour.setDescription(descriptionAnnonce);
			offreMiseAJour.setAdresse(adresseAnnonce);
			offreMiseAJour.setCode_postal(codePostalAnnonce);
			offreMiseAJour.setVille(villeAnnonce);
			offreMiseAJour.setPays(paysAnnonce);
			offreMiseAJour.setDate_debut(Date.valueOf(dateDebutAnnonce));
			offreMiseAJour.setDate_limite(Date.valueOf(dateFinAnnonce));
			if(pourvuAnnonce.equals("oui")) {
				offreMiseAJour.setPourvu(true);
				liaisonOffreContrainteDao.deleteByIdOffre(id_offre);
				liaisonOffreServiceDao.deleteByIdOffre(id_offre);
				if(!deuxEnfantsMax.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)id_offre,(long)3);
					liaisonOffreContrainteDao.save(liaisonOffreContrainte);
				}
				//pas d animaux
				if(!animaux.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)id_offre,(long)5);
					liaisonOffreContrainteDao.save(liaisonOffreContrainte);
				}
				//pas d enfants autorises
				if(!pasDenfantsAutorises.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)id_offre,(long)4);
					liaisonOffreContrainteDao.save(liaisonOffreContrainte);
				}
				if(!pasDeBruitApres23H.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)id_offre,(long)2);
					liaisonOffreContrainteDao.save(liaisonOffreContrainte);
				}
				if(!pasDeCigarettes.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)id_offre,(long)1);
					liaisonOffreContrainteDao.save(liaisonOffreContrainte);
				}
				
				////////////////Liaison Services////////////////
				if(!arroserPlante.equals("")) {
					LiaisonOffreService liaisonOffreService=new LiaisonOffreService((long)id_offre,(long) 2);
					liaisonOffreServiceDao.save(liaisonOffreService);
				}
				if(!garderLesAnimaux.equals("")) {
					LiaisonOffreService liaisonOffreService=new LiaisonOffreService((long)id_offre,(long) 1);
					liaisonOffreServiceDao.save(liaisonOffreService);
				}
				if(!nettoyerLaMaison.equals("")) {
					LiaisonOffreService liaisonOffreService=new LiaisonOffreService((long)id_offre,(long) 3);
					liaisonOffreServiceDao.save(liaisonOffreService);
				}
				offresDao.save(offreMiseAJour);
			}
			else {
				offreMiseAJour.setPourvu(false);
				/****delete tous les elements ici***/
				liaisonOffreContrainteDao.deleteByIdOffre(id_offre);
				liaisonOffreServiceDao.deleteByIdOffre(id_offre);
				if(!deuxEnfantsMax.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)id_offre,(long)3);
					liaisonOffreContrainteDao.save(liaisonOffreContrainte);
				}
				//pas d animaux
				if(!animaux.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)id_offre,(long)5);
					liaisonOffreContrainteDao.save(liaisonOffreContrainte);
				}
				//pas d enfants autorises
				if(!pasDenfantsAutorises.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)id_offre,(long)4);
					liaisonOffreContrainteDao.save(liaisonOffreContrainte);
				}
				if(!pasDeBruitApres23H.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)id_offre,(long)2);
					liaisonOffreContrainteDao.save(liaisonOffreContrainte);
				}
				if(!pasDeCigarettes.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)id_offre,(long)1);
					liaisonOffreContrainteDao.save(liaisonOffreContrainte);
				}
				
				////////////////Liaison Services////////////////
				if(!arroserPlante.equals("")) {
					LiaisonOffreService liaisonOffreService=new LiaisonOffreService((long)id_offre,(long) 2);
					liaisonOffreServiceDao.save(liaisonOffreService);
				}
				if(!garderLesAnimaux.equals("")) {
					LiaisonOffreService liaisonOffreService=new LiaisonOffreService((long)id_offre,(long) 1);
					liaisonOffreServiceDao.save(liaisonOffreService);
				}
				if(!nettoyerLaMaison.equals("")) {
					LiaisonOffreService liaisonOffreService=new LiaisonOffreService((long)id_offre,(long) 3);
					liaisonOffreServiceDao.save(liaisonOffreService);
				}
				offresDao.save(offreMiseAJour);
			}
		}
		return "redirect:/Admin/Gestion/Annonce";
	}
	
}