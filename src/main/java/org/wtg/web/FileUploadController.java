package org.wtg.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
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
	//Path path=Paths.get("folder","receptionServeur")
	public static String uploadDirectory=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\photosAnnonces\\ReceptionFichier";
	//----
	@RequestMapping("/posterUneAnnonce")
	public String UploadPage(Model model) {
		return "Utilisateur_ajouterImagePourAnnonce";
	}
	
	@RequestMapping("/annonce/ajoutImage")
	public String upload(Model model,@RequestParam("files") MultipartFile[] files) {
		StringBuilder fileNames=new StringBuilder();
		for(MultipartFile file:files) {
			Path fileNameAndPath=Paths.get(uploadDirectory, file.getOriginalFilename());
			if(isItAuthorized(file.getContentType())) {
				fileNames.append(file.getOriginalFilename());
				try {
					Files.write(fileNameAndPath,file.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				return "Utilisateur_ajouterImagePourAnnonce";
			}
		}
		model.addAttribute("msg","Successfully uploaded files"+fileNames.toString());
		return "Utilisateur_ajouterImagePourAnnonce";
	}
	
	
	@RequestMapping("/updateAll")
	public String update() {
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
		return "Utilisateur_ajouterImagePourAnnonce";
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
