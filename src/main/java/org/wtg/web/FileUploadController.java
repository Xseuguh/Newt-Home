package org.wtg.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	//----
	@RequestMapping("/annonce/posterUneAnnonce")
	public String uploadPage(Model model) {
		return "Utilisateur_ajouterImagePourAnnonce";
	}
	
	@RequestMapping("/annonce/ajoutImage")
	public String uploadImage(Model model,@RequestParam("files") MultipartFile[] files) {
		int counterImage=countNumberImage(files);
		if(counterImage!=3) {
			return "redirect:/annonce/posterUneAnnonce";
		}
		else {
			boolean hasItWorked=writeTheImageInTheTemporaryFolder(files);
			if(hasItWorked==false) {
				return "redirect:/annonce/posterUneAnnonce";
			}
			return "Utilisateur_ajouterImagePourAnnonce";
		}
	}
	
	
	@RequestMapping("/annonce/modifierImage")
	public String update() {
	    String OS = System.getProperty("os.name").toLowerCase();
		if(OS.equals("windows 10")) {
	        File repertoire = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\photosAnnonces\\ReceptionFichier\\");
	        String liste[] = repertoire.list();
	        for(String fileToDelete:liste) {
	    		Path path=Paths.get(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\photosAnnonces\\ReceptionFichier\\"+fileToDelete);
	    		try {
					Files.delete(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
		else if(OS.equals("linux")) {
	        File repertoire = new File(System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/ReceptionFichier/");
	        String liste[] = repertoire.list();
	        for(String fileToDelete:liste) {
	    		Path path=Paths.get(System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/ReceptionFichier/"+fileToDelete);
	    		try {
					Files.delete(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
		else if(OS.equals("mac os x")) {
	        File repertoire = new File(System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/ReceptionFichier/");
	        String liste[] = repertoire.list();
	        for(String fileToDelete:liste) {
	    		Path path=Paths.get(System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/ReceptionFichier/"+fileToDelete);
	    		try {
					Files.delete(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
		else {
		}
		return "Utilisateur_ajouterImagePourAnnonce";
	}
	
	/*
	 * Allowed format: jpeg & png
	 */
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
	
	public int countNumberImage(MultipartFile[] files) {
		int counterImage=0;
		for(MultipartFile file:files) {
			counterImage++;
		}
		return counterImage;
	}
	
	/*
	 * return true if has written the files in tmp folder
	 * false otherwise
	 */
	public boolean writeTheImageInTheTemporaryFolder(MultipartFile[] files) {
	    String OS = System.getProperty("os.name").toLowerCase();
	    boolean hasItWorked=true;
		if(OS.equals("windows 10")) {
			String uploadDirectory=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\photosAnnonces\\ReceptionFichier";
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
			String uploadDirectory=System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/ReceptionFichier";
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
			String uploadDirectory=System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/ReceptionFichier";
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
}
