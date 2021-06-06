package org.wtg.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.wtg.dao.ContraintesRepository;
import org.wtg.dao.LiaisonOffreContrainteRepository;
import org.wtg.dao.LiaisonOffreServiceRepository;
import org.wtg.dao.OffresPostuleesRepository;
import org.wtg.dao.OffresRepository;
import org.wtg.dao.ServicesRepository;
import org.wtg.dao.UserRepository;
import org.wtg.entities.Annonce;
import org.wtg.entities.Contraintes;
import org.wtg.entities.JoinOffresUsers;
import org.wtg.entities.LiaisonOffreContrainte;
import org.wtg.entities.LiaisonOffreService;
import org.wtg.entities.Offres;
import org.wtg.entities.Services;
import org.wtg.entities.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class AnnonceController {
	@Autowired
	OffresRepository offresDao;
	@Autowired
	LiaisonOffreContrainteRepository contrainteDao;
	@Autowired
	LiaisonOffreServiceRepository serviceDao;
	@Autowired
	ContraintesRepository listeContrainteRechercheDao;
	@Autowired
	ServicesRepository listeServiceRechercheDao;
	@Autowired
	OffresPostuleesRepository offresPostuleesDao;
	@Autowired
	UserRepository userDao;
	
	
	
	private List<String> getImagesPath(File[] repertoires, List<String> listOfImages) {

		for (File imagePaths : repertoires) {

			listOfImages.add(imagePaths.getName());
		}
		return listOfImages;
	}
	@RequestMapping(value = "/Accueil")
	public String search(Model model, @RequestParam(name = "motCle", defaultValue = "") String mc) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Boolean connected = false;
		Long USER_ID=0L;
		
		if(principal  != "anonymousUser") {
			connected = true;
			String username = ((UserDetails)principal).getUsername();
			UserInfo user = userDao.findByMail(username);
			USER_ID = user.getId_user();
		}
		
		
		List<Contraintes> listeContrainteRecherche = listeContrainteRechercheDao.findAll();
		List<Services> listeServiceRecherche = listeServiceRechercheDao.findAll();
		List<Offres> offres = offresDao.findOffreByNameOrDescription("%" + mc + "%");
		List<Annonce> contraintes = new ArrayList<Annonce>();
		List<Annonce> services = new ArrayList<Annonce>();
		List<JoinOffresUsers> infosProprio = new ArrayList<JoinOffresUsers>();
		
		List<File> repertoires = new ArrayList<File>();

		Map<Long, List<String>> imagesPaths = new HashMap<Long, List<String>>();

		for (Offres value : offres) {
			contraintes.addAll(contrainteDao.findContraintesByIdOffre(value.getId_offre()));
			services.addAll(serviceDao.findServicesByIdOffre(value.getId_offre()));
			infosProprio.addAll(offresDao.findUserByIdOffre(value.getId_offre()));
			
			String OS = System.getProperty("os.name").toLowerCase();
			
			if(OS.equals("windows 10")) {
			repertoires.add(new File(System.getProperty("user.dir")
					+ "\\src\\main\\resources\\static\\images\\photosAnnonces\\" + value.getId_offre()));
			}
			else {
				repertoires.add(new File(System.getProperty("user.dir")+
				"/src/main/resources/static/images/photosAnnonces/"+value.getId_offre()));
			}

			for (File repertoire : repertoires) {

				imagesPaths.put(value.getId_offre(), getImagesPath(repertoire.listFiles(), new ArrayList<String>()));
				
			}

		}
		
		
		Object listPaths;
		
		if(imagesPaths.values().size() == 1) {
			
			listPaths = imagesPaths.values().stream()
			        .flatMap(List::stream)
			        .collect(Collectors.toList());
			
			}
			
			else {
				listPaths = imagesPaths;
			}
			
		

		
		model.addAttribute("listeContrainteRecherche", listeContrainteRecherche);
		model.addAttribute("listeServiceRecherche", listeServiceRecherche);
		model.addAttribute("offres", offres);
		model.addAttribute("listPaths", listPaths);
		model.addAttribute("imagesPaths", imagesPaths);
		model.addAttribute("contraintes", contraintes);
		model.addAttribute("services", services);
		model.addAttribute("infosProprio", infosProprio);
		model.addAttribute("motC", mc);
		model.addAttribute("connected", connected);
		model.addAttribute("idUserConnecte", USER_ID);
		return "Accueil";
	}
	
	@RequestMapping(value = "/Accueil/Mes_Annonces")
	public String getMesAnnonces(Model model, @RequestParam(name = "idUserConnecte", defaultValue = "") Long idUserConnecte) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Boolean connected = false;
		Long USER_ID=0L;
		
		if(principal  != "anonymousUser") {
			connected = true;
			String username = ((UserDetails)principal).getUsername();
			UserInfo user = userDao.findByMail(username);
			USER_ID = user.getId_user();
		}
		
		
		List<Contraintes> listeContrainteRecherche = listeContrainteRechercheDao.findAll();
		List<Services> listeServiceRecherche = listeServiceRechercheDao.findAll();
		List<Offres> offres = offresDao.findByUserId(idUserConnecte);
		List<Annonce> contraintes = new ArrayList<Annonce>();
		List<Annonce> services = new ArrayList<Annonce>();
		List<JoinOffresUsers> infosProprio = new ArrayList<JoinOffresUsers>();
		
		List<File> repertoires = new ArrayList<File>();

		Map<Long, List<String>> imagesPaths = new HashMap<Long, List<String>>();

		for (Offres value : offres) {
			contraintes.addAll(contrainteDao.findContraintesByIdOffre(value.getId_offre()));
			services.addAll(serviceDao.findServicesByIdOffre(value.getId_offre()));
			infosProprio.addAll(offresDao.findUserByIdOffre(value.getId_offre()));
			
			String OS = System.getProperty("os.name").toLowerCase();
			
			if(OS.equals("windows 10")) {
			repertoires.add(new File(System.getProperty("user.dir")
					+ "\\src\\main\\resources\\static\\images\\photosAnnonces\\" + value.getId_offre()));
			}
			else {
				repertoires.add(new File(System.getProperty("user.dir")+
				"/src/main/resources/static/images/photosAnnonces/"+value.getId_offre()));
			}

			for (File repertoire : repertoires) {

				imagesPaths.put(value.getId_offre(), getImagesPath(repertoire.listFiles(), new ArrayList<String>()));
				
			}

		}
		
		
		Object listPaths;
		
		if(imagesPaths.values().size() == 1) {
			
			listPaths = imagesPaths.values().stream()
			        .flatMap(List::stream)
			        .collect(Collectors.toList());
			
			}
			
			else {
				listPaths = imagesPaths;
			}
			
		

		
		model.addAttribute("listeContrainteRecherche", listeContrainteRecherche);
		model.addAttribute("listeServiceRecherche", listeServiceRecherche);
		model.addAttribute("offres", offres);
		model.addAttribute("listPaths", listPaths);
		model.addAttribute("imagesPaths", imagesPaths);
		model.addAttribute("contraintes", contraintes);
		model.addAttribute("services", services);
		model.addAttribute("infosProprio", infosProprio);
		model.addAttribute("connected", connected);
		model.addAttribute("idUserConnecte", USER_ID);
		
		return "Accueil";
	}

	@RequestMapping(value = "/Accueil/Recherche_Avancee")
	public String rechercheAvancee(Model model, @RequestParam(name = "motCle", defaultValue = "") String mc,
			@RequestParam(value = "listeContraintes", defaultValue = "") long[] listeContraintes,
			@RequestParam(value = "listeServices", defaultValue = "") long[] listeServices,
			@RequestParam(name = "lieu", defaultValue = "") String lieu) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Boolean connected = false;
		Long USER_ID=0L;
		
		if(principal  != "anonymousUser") {
			connected = true;
			String username = ((UserDetails)principal).getUsername();
			UserInfo user = userDao.findByMail(username);
			USER_ID = user.getId_user();
		}

		List<Contraintes> listeContrainteRecherche = listeContrainteRechercheDao.findAll();
		List<Services> listeServiceRecherche = listeServiceRechercheDao.findAll();
		List<Offres> offresAvancees = new ArrayList<Offres>();
		
		List<File> repertoires = new ArrayList<File>();
		
		Map<Long, List<String>> imagesPaths = new HashMap<Long, List<String>>();
		
		if ((listeContraintes.length > 0) && (listeServices.length > 0) && !mc.equals("") && !lieu.equals("")) {
			offresAvancees.addAll(
					offresDao.findAvanceeTousChamps("%" + mc + "%", listeContraintes, listeServices, "%" + lieu + "%"));
		}

		else if (listeContraintes.length > 0 && listeServices.length == 0 && mc.equals("") && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByContrainte(listeContraintes));
		}

		else if (listeContraintes.length == 0 && listeServices.length > 0 && mc.equals("") && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByService(listeServices));
		}

		else if (listeContraintes.length == 0 && listeServices.length == 0 && mc.equals("") && !(lieu.equals(""))) {
			offresAvancees.addAll(offresDao.findOffreByLocation("%" + lieu + "%"));
		}

		else if (listeContraintes.length == 0 && listeServices.length == 0 && !(mc.equals("")) && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findOffreByNameOrDescription("%" + mc + "%"));

		}

		else if (listeContraintes.length > 0 && listeServices.length > 0 && mc.equals("") && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByContrainteService(listeContraintes, listeServices));
		}

		if (listeContraintes.length > 0 && listeServices.length == 0 && !mc.equals("") && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByContrainteMC("%" + mc + "%", listeContraintes));
		}

		else if (listeContraintes.length > 0 && listeServices.length == 0 && mc.equals("") && !lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByContrainteLocation(listeContraintes, "%" + lieu + "%"));
		}

		else if (listeContraintes.length == 0 && listeServices.length > 0 && !mc.equals("") && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByServiceMC("%" + mc + "%", listeServices));
		}

		else if (listeContraintes.length == 0 && listeServices.length > 0 && mc.equals("") && !lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByServiceLocation("%" + lieu + "%", listeServices));
		}

		else if (listeContraintes.length == 0 && listeServices.length == 0 && !(mc.equals("")) && !(lieu.equals(""))) {
			offresAvancees.addAll(offresDao.findByMCLocation("%" + mc + "%", "%" + lieu + "%"));
		}

		else if (listeContraintes.length == 0 && listeServices.length > 0 && !mc.equals("") && !lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByServiceMcLocation("%" + mc + "%", listeServices, "%" + lieu + "%"));
		}

		else if (listeContraintes.length > 0 && listeServices.length == 0 && !mc.equals("") && !lieu.equals("")) {
			offresAvancees
					.addAll(offresDao.findByContrainteMcLocation("%" + mc + "%", listeContraintes, "%" + lieu + "%"));
		}

		else if (listeContraintes.length > 0 && listeServices.length > 0 && mc.equals("") && !lieu.equals("")) {
			offresAvancees.addAll(
					offresDao.findByContrainteServiceLocation(listeContraintes, listeServices, "%" + lieu + "%"));
		}

		else if (listeContraintes.length > 0 && listeServices.length > 0 && !mc.equals("") && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByContrainteServiceMC(listeContraintes, listeServices, "%" + mc + "%"));
		}

		List<Annonce> contraintes = new ArrayList<Annonce>();
		List<Annonce> services = new ArrayList<Annonce>();

		List<JoinOffresUsers> infosProprio = new ArrayList<JoinOffresUsers>();

		for (Offres value : offresAvancees) {
			contraintes.addAll(contrainteDao.findContraintesByIdOffre(value.getId_offre()));
			services.addAll(serviceDao.findServicesByIdOffre(value.getId_offre()));
			infosProprio.addAll(offresDao.findUserByIdOffre(value.getId_offre()));
			
			repertoires.add(new File(System.getProperty("user.dir")
					+ "\\src\\main\\resources\\static\\images\\photosAnnonces\\" + value.getId_offre()));

			for (File repertoire : repertoires) {

				imagesPaths.put(value.getId_offre(), getImagesPath(repertoire.listFiles(), new ArrayList<String>()));

			}

		}
		
		Object listPaths;
		
		
		if(imagesPaths.values().size() == 1) {
		
		listPaths = imagesPaths.values().stream()
		        .flatMap(List::stream)
		        .collect(Collectors.toList());
		
		}
		
		else {
			listPaths = imagesPaths;
		}
		
		model.addAttribute("listeContrainteRecherche", listeContrainteRecherche);
		model.addAttribute("listeServiceRecherche", listeServiceRecherche);
		model.addAttribute("offres", offresAvancees);
		model.addAttribute("listPaths", listPaths);
		model.addAttribute("imagesPaths", imagesPaths);
		model.addAttribute("contraintes", contraintes);
		model.addAttribute("services", services);
		model.addAttribute("infosProprio", infosProprio);
		model.addAttribute("motC", mc);
		model.addAttribute("lieu", lieu);
		model.addAttribute("listeC", listeContraintes);
		model.addAttribute("listeS", listeServices);
		model.addAttribute("connected", connected);
		model.addAttribute("idUserConnecte", USER_ID);
		return "Accueil";
	}
	
	@RequestMapping(value="/Accueil/Postuler")
	public String postulerAnnonce(Model model, @RequestParam(name = "idUserConnecte", defaultValue = "") Long idUserConnecte, @RequestParam(name = "idOffre", defaultValue = "") Long idOffre) {
		
		offresPostuleesDao.applyToAnOffer(idOffre,idUserConnecte); 
		
		
		return "Accueil";
		
	}
	
	@GetMapping(path = "/ads/editing")
	public String editing(Model model, @RequestParam(name = "id", defaultValue = "") Long id_offre) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = ((UserDetails)principal).getUsername();
		UserInfo user = userDao.findByMail(username);
		long USER_ID = user.getId_user();
	
		Offres offreToEdit = offresDao.findByIdWithUserVerification(id_offre, USER_ID);
		if (offreToEdit == null) {
			return "Error";
		}

		model.addAttribute("offresZoom", offreToEdit);

		List<LiaisonOffreService> listeLiaisonOffresServices = serviceDao.findByIdOffre(id_offre);
		model.addAttribute("nombreDeService", listeLiaisonOffresServices.size());
		if (listeLiaisonOffresServices.size() == 0) {
		} else if (listeLiaisonOffresServices.size() == 1) {
			model.addAttribute("serviceEnvoye1", listeLiaisonOffresServices.get(0).getId_service());
		} else if (listeLiaisonOffresServices.size() == 2) {
			int resultat = 1 + 2 + 3;
			resultat = (int) (resultat - listeLiaisonOffresServices.get(0).getId_service());
			resultat = (int) (resultat - listeLiaisonOffresServices.get(1).getId_service());
			model.addAttribute("serviceEnvoye1", resultat);
		} else if (listeLiaisonOffresServices.size() == 3) {
		}
		// ************
		List<LiaisonOffreContrainte> listeLiaisonOffresContraintes = contrainteDao.findByIdOffre(id_offre);
		model.addAttribute("nombreDeContrainte", listeLiaisonOffresContraintes.size());
		if (listeLiaisonOffresContraintes.size() == 0) {
		} else if (listeLiaisonOffresContraintes.size() == 1) {
			model.addAttribute("contraintesEnvoyees1", listeLiaisonOffresContraintes.get(0).getId_contrainte());
		} else if (listeLiaisonOffresContraintes.size() == 2) {
			int resultat = (int) (listeLiaisonOffresContraintes.get(1).getId_contrainte()
					* listeLiaisonOffresContraintes.get(0).getId_contrainte());
			model.addAttribute("contraintesEnvoyees1", resultat);
		} else if (listeLiaisonOffresContraintes.size() == 3) {
			TreeSet<Integer> myTreeSetOfContraintes = new TreeSet<Integer>();
			int a = (int) (listeLiaisonOffresContraintes.get(0).getId_contrainte() + 0);
			int b = (int) (listeLiaisonOffresContraintes.get(1).getId_contrainte() + 0);
			int c = (int) (listeLiaisonOffresContraintes.get(2).getId_contrainte() + 0);

			myTreeSetOfContraintes.add(a);
			myTreeSetOfContraintes.add(b);
			myTreeSetOfContraintes.add(c);

			int[] listeIdContrainte = new int[5];
			listeIdContrainte[0] = 1;
			listeIdContrainte[1] = 2;
			listeIdContrainte[2] = 3;
			listeIdContrainte[3] = 4;
			listeIdContrainte[4] = 5;

			TreeSet<Integer> myTreeSetOfNotContraintes = new TreeSet<Integer>();
			for (Integer myInteger : listeIdContrainte) {
				if (!(myTreeSetOfContraintes.contains(myInteger))) {
					myTreeSetOfNotContraintes.add((int) myInteger);
				}
			}
			int resultat = 1;
			for (Integer myInteger : myTreeSetOfNotContraintes) {
				int tmp = (int) myInteger;
				resultat = resultat * tmp;
			}
			model.addAttribute("contraintesEnvoyees1", resultat);
		} else if (listeLiaisonOffresContraintes.size() == 4) {
			int resultat = (int) (15 - listeLiaisonOffresContraintes.get(0).getId_contrainte());
			resultat = (int) (resultat - listeLiaisonOffresContraintes.get(1).getId_contrainte());
			resultat = (int) (resultat - listeLiaisonOffresContraintes.get(2).getId_contrainte());
			resultat = (int) (resultat - listeLiaisonOffresContraintes.get(3).getId_contrainte());
			model.addAttribute("contraintesEnvoyees1", resultat);
		} else if (listeLiaisonOffresContraintes.size() == 5) {
		}

		return "ModifierAnnonce";
	}

	@RequestMapping(value = "/ads/ValidationDesModifications")
	public String mettreA_Jour(Model model, @RequestParam(name = "ref", defaultValue = "") Long id_offre,
			@RequestParam(name = "mc", defaultValue = "") String mc,
			@RequestParam(name = "titreAnnonce", defaultValue = "") String titreAnnonce,
			@RequestParam(name = "descriptionAnnonce", defaultValue = "") String descriptionAnnonce,
			@RequestParam(name = "adresseAnnonce", defaultValue = "") String adresseAnnonce,
			@RequestParam(name = "codePostalAnnonce", defaultValue = "") Integer codePostalAnnonce,
			@RequestParam(name = "villeAnnonce", defaultValue = "") String villeAnnonce,
			@RequestParam(name = "paysAnnonce", defaultValue = "") String paysAnnonce,
			@RequestParam(name = "dateDebutAnnonce", defaultValue = "") String dateDebutAnnonce,
			@RequestParam(name = "dateFinAnnonce", defaultValue = "") String dateFinAnnonce,
			@RequestParam(name = "pourvuAnnonce", defaultValue = "") String pourvuAnnonce,
			@RequestParam(name = "deuxEnfantsMax", defaultValue = "") String deuxEnfantsMax,
			@RequestParam(name = "animaux", defaultValue = "") String animaux,
			@RequestParam(name = "enfants", defaultValue = "") String pasDenfantsAutorises,
			@RequestParam(name = "bruitVingtTrois", defaultValue = "") String pasDeBruitApres23H,
			@RequestParam(name = "cigarette", defaultValue = "") String pasDeCigarettes,
			@RequestParam(name = "arroserPlante", defaultValue = "") String arroserPlante,
			@RequestParam(name = "garderLesAnimaux", defaultValue = "") String garderLesAnimaux,
			@RequestParam(name = "nettoyerLaMaison", defaultValue = "") String nettoyerLaMaison) {
		
		String[] valuesStringInserted= {descriptionAnnonce,titreAnnonce,adresseAnnonce,
				villeAnnonce,paysAnnonce,dateDebutAnnonce,dateFinAnnonce};
		
		String[] valuesCheckboxConstraint= {deuxEnfantsMax,animaux,pasDenfantsAutorises,
				pasDeBruitApres23H,pasDeCigarettes};
		
		String[] valuesCheckboxService= {arroserPlante,garderLesAnimaux,
				nettoyerLaMaison};
		
		boolean areTheStringValidated=areTheStringValid(valuesStringInserted);
		boolean isCorrectForConstraintAndService=isThereConstraintAndService(valuesCheckboxConstraint,valuesCheckboxService);
		boolean isInsertable=areTheStringValidated && isCorrectForConstraintAndService;
		
		if (isInsertable) {
			System.out.println(pourvuAnnonce);
			List<Offres> listeDesOffresMisesAJour = offresDao.findByIdOffre(id_offre);
			Offres offreMiseAJour = listeDesOffresMisesAJour.get(0);
			offreMiseAJour.setTitre(titreAnnonce);
			offreMiseAJour.setDescription(descriptionAnnonce);
			offreMiseAJour.setAdresse(adresseAnnonce);
			offreMiseAJour.setCode_postal(codePostalAnnonce);
			offreMiseAJour.setVille(villeAnnonce);
			offreMiseAJour.setPays(paysAnnonce);
			offreMiseAJour.setDate_debut(Date.valueOf(dateDebutAnnonce));
			offreMiseAJour.setDate_limite(Date.valueOf(dateFinAnnonce));
			if (pourvuAnnonce.equals("oui")) {
				offreMiseAJour.setPourvu(true);
				contrainteDao.deleteByIdOffre(id_offre);
				serviceDao.deleteByIdOffre(id_offre);
				if (!deuxEnfantsMax.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte = new LiaisonOffreContrainte((long) id_offre,
							(long) 3);
					contrainteDao.save(liaisonOffreContrainte);
				}
				// pas d animaux
				if (!animaux.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte = new LiaisonOffreContrainte((long) id_offre,
							(long) 5);
					contrainteDao.save(liaisonOffreContrainte);
				}
				// pas d enfants autorises
				if (!pasDenfantsAutorises.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte = new LiaisonOffreContrainte((long) id_offre,
							(long) 4);
					contrainteDao.save(liaisonOffreContrainte);
				}
				if (!pasDeBruitApres23H.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte = new LiaisonOffreContrainte((long) id_offre,
							(long) 2);
					contrainteDao.save(liaisonOffreContrainte);
				}
				if (!pasDeCigarettes.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte = new LiaisonOffreContrainte((long) id_offre,
							(long) 1);
					contrainteDao.save(liaisonOffreContrainte);
				}

				//////////////// Liaison Services////////////////
				if (!arroserPlante.equals("")) {
					LiaisonOffreService liaisonOffreService = new LiaisonOffreService((long) id_offre, (long) 2);
					serviceDao.save(liaisonOffreService);
				}
				if (!garderLesAnimaux.equals("")) {
					LiaisonOffreService liaisonOffreService = new LiaisonOffreService((long) id_offre, (long) 1);
					serviceDao.save(liaisonOffreService);
				}
				if (!nettoyerLaMaison.equals("")) {
					LiaisonOffreService liaisonOffreService = new LiaisonOffreService((long) id_offre, (long) 3);
					serviceDao.save(liaisonOffreService);
				}
				offresDao.save(offreMiseAJour);
			} else {
				offreMiseAJour.setPourvu(false);
				/**** delete tous les elements ici ***/
				contrainteDao.deleteByIdOffre(id_offre);
				serviceDao.deleteByIdOffre(id_offre);
				if (!deuxEnfantsMax.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte = new LiaisonOffreContrainte((long) id_offre,
							(long) 3);
					contrainteDao.save(liaisonOffreContrainte);
				}
				// pas d animaux
				if (!animaux.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte = new LiaisonOffreContrainte((long) id_offre,
							(long) 5);
					contrainteDao.save(liaisonOffreContrainte);
				}
				// pas d enfants autorises
				if (!pasDenfantsAutorises.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte = new LiaisonOffreContrainte((long) id_offre,
							(long) 4);
					contrainteDao.save(liaisonOffreContrainte);
				}
				if (!pasDeBruitApres23H.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte = new LiaisonOffreContrainte((long) id_offre,
							(long) 2);
					contrainteDao.save(liaisonOffreContrainte);
				}
				if (!pasDeCigarettes.equals("")) {
					LiaisonOffreContrainte liaisonOffreContrainte = new LiaisonOffreContrainte((long) id_offre,
							(long) 1);
					contrainteDao.save(liaisonOffreContrainte);
				}

				//////////////// Liaison Services////////////////
				if (!arroserPlante.equals("")) {
					LiaisonOffreService liaisonOffreService = new LiaisonOffreService((long) id_offre, (long) 2);
					serviceDao.save(liaisonOffreService);
				}
				if (!garderLesAnimaux.equals("")) {
					LiaisonOffreService liaisonOffreService = new LiaisonOffreService((long) id_offre, (long) 1);
					serviceDao.save(liaisonOffreService);
				}
				if (!nettoyerLaMaison.equals("")) {
					LiaisonOffreService liaisonOffreService = new LiaisonOffreService((long) id_offre, (long) 3);
					serviceDao.save(liaisonOffreService);
				}
				offresDao.save(offreMiseAJour);
			}
			return "redirect:/profil/";
		}
		else {
			return "redirect:/ads/editing?id="+id_offre;
		}
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
	
	@RequestMapping(value = "/annonce/modifierMesImages/")
	public String modifyMyFilesPart1(Model model,@RequestParam(name = "ref", defaultValue = "") Long id_offre) {
		String OS = System.getProperty("os.name").toLowerCase();
		if(OS.equals("windows 10")) {
	        File repertoire = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\photosAnnonces\\"+id_offre+"\\");
	        String liste[] = repertoire.list();
	        for(String fileToDelete:liste) {
	    		Path path=Paths.get(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\photosAnnonces\\"+id_offre+"\\"+fileToDelete);
	    		try {
					Files.delete(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
		else if(OS.equals("linux")) {
	        File repertoire = new File(System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/"+id_offre);
	        String liste[] = repertoire.list();
	        for(String fileToDelete:liste) {
	    		Path path=Paths.get(System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/"+id_offre+"/"+fileToDelete);
	    		try {
					Files.delete(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
		else if(OS.equals("mac os x")) {
	        File repertoire = new File(System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/"+id_offre);
	        String liste[] = repertoire.list();
	        for(String fileToDelete:liste) {
	    		Path path=Paths.get(System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/"+id_offre+"/"+fileToDelete);
	    		try {
					Files.delete(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
		else {
		}
		return "redirect:/ads/editing?id="+id_offre;
	}
	
	@RequestMapping(value="/annonce/uploaderDeNouvellesImages/")
	public String uploaderDeNouvellesImages(Model model,@RequestParam(name = "ref", defaultValue = "") Long id_offre,
			@RequestParam("files") MultipartFile[] files) {
		ecrireNouveauForNouvellesImages(files,id_offre);
		return "redirect:/ads/editing?id="+id_offre;
	}
	

	public boolean ecrireNouveauForNouvellesImages(MultipartFile[] files,Long numberElement) {
		 String OS = System.getProperty("os.name").toLowerCase();
		    boolean hasItWorked=true;
			if(OS.equals("windows 10")) {
				String uploadDirectory=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\photosAnnonces\\"+numberElement;
				for(MultipartFile file:files) {
					Path fileNameAndPath=Paths.get(uploadDirectory, file.getOriginalFilename());
					if(isItAuthorized(file.getContentType())) {
						try {
							Files.write(fileNameAndPath,file.getBytes());
						} catch (IOException e) {
							e.printStackTrace();
							hasItWorked=false;
						}
					}
				}
			}
			else if(OS.equals("linux")) {
				String uploadDirectory=System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/"+numberElement;
				for(MultipartFile file:files) {
					Path fileNameAndPath=Paths.get(uploadDirectory, file.getOriginalFilename());
					if(isItAuthorized(file.getContentType())) {
						try {
							Files.write(fileNameAndPath,file.getBytes());
						} catch (IOException e) {
							e.printStackTrace();
							hasItWorked=false;
						}
					}
				}
			}
			else if(OS.equals("mac os x")) {
				String uploadDirectory=System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/"+numberElement;
				for(MultipartFile file:files) {
					Path fileNameAndPath=Paths.get(uploadDirectory, file.getOriginalFilename());
					if(isItAuthorized(file.getContentType())) {
						try {
							Files.write(fileNameAndPath,file.getBytes());
						} catch (IOException e) {
							e.printStackTrace();
							hasItWorked=false;
						}
					}
				}
			}
			else {
			}
			return hasItWorked;
	}
	
	public boolean isItAuthorized(String word) {
		String[] typeTable=word.split("/");
		String typeOfTheFile=typeTable[1];
		ArrayList<String> allowedFormat=new ArrayList<String>();
		allowedFormat.add("jpeg");
		allowedFormat.add("jpg");
		allowedFormat.add("JPEG");
		allowedFormat.add("JPG");
		allowedFormat.add("png");
		allowedFormat.add("PNG");
		if(allowedFormat.contains(typeOfTheFile)) {
			return true;
		}
		else {
			return false;
		}
	}

}