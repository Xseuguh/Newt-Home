<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="java.io.File" %> <%@page
import="java.util.List" %> <%@page import="java.util.ArrayList" %> <%@page
import = "java.nio.file.Files"%> <%@page import = "java.nio.file.Path" %>
<%@page import = "java.nio.file.Paths" %> <%@page import = "java.io.IOException"
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1"
    />
    <title>Ajout d'annonce</title>
    <link
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstr
ap.min.css"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="<%=request.getContextPath()%>/css/Utilisateur_ajouterAnnonce.css"
    />
    <link
      href="<%=request.getContextPath()%>/css/generalCSS.css"
      rel="stylesheet"
    />
  </head>
  <body>
    <jsp:include page="Header.jsp" />
    <div class="tout">
      <h2>Mon annonce 1/2</h2>
      <div class="container">
        <!-- UPLOAD D IMAGE -->
        <% String
        whereFolder=System.getProperty("user.dir")+"/src/main/resources/static/images/photosAnnonces/ReceptionFichier/";
        String OS = System.getProperty("os.name").toLowerCase();
        if(!OS.equals("mac os x") && !OS.equals("linux")) { whereFolder =
        whereFolder.replace("/","\\"); } File folder = new File(whereFolder);
        String listOfTheNameOfTheFiles[] = folder.list();
        if(listOfTheNameOfTheFiles.length==3){ for(String
        element:listOfTheNameOfTheFiles){ request.setAttribute( "element",
        element); %>
        <div>
          <img
            src="<%=request.getContextPath()%>/images/photosAnnonces/ReceptionFichier/${element}"
          />
          ${element}
        </div>
        <% } %>
        <br />
        <div>
          <form action="/annonce/modifierImage" method="post">
            <input
              class="boutonSubmit"
              type="submit"
              onclick="raiseConcernWhenDiscard()"
              value="Modifier les fichiers"
            />
          </form>
        </div>
        <br />
        <button
          class="boutonSubmit"
          onclick="window.location.href='/annonce/ajout'"
        >
          Continuer
        </button>
        <% } else{ %>
        <!-- upload vide -->
        <div id="specificationsUpload">
          ATTENTION VOUS DEVEZ METTRE NECESSAIREMENT 3 IMAGES<br />
          Formats acceptés: jpeg, png Taille maximum: 15Mo
        </div>
        <form
          action="/annonce/ajoutImage"
          method="post"
          enctype="multipart/form-data"
        >
          <input type="file" name="files" id="myFile" class="boutonSubmit" multiple />
          <input class="boutonSubmit" type="submit" value="Upload Files" onclick="isItTheSizeCorrect()"/>
        </form>
        <% } %>
      </div>
    </div>

    <jsp:include page="Footer.jsp" />
    <script src="<%=request.getContextPath()%>/js/Utilisateur_ajouterAnnonce.js"></script>
    <script>
      function raiseConcernWhenDiscard() {
        event.preventDefault();
        var questionResult = confirm("Etes vous sur?");
        if (questionResult == true) {
          window.location.href = "/annonce/modifierImage";
        }
      }
    </script>

    <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script
      id="scriptBTS"
      src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"
    ></script>
    <script>
    	function isItTheSizeCorrect(){
    		var filesInput=document.getElementById('myFile');
    		
    		if(document.getElementById('myFile').files[0].size!=null && document.getElementById('myFile').files[1].size!=null && document.getElementById('myFile').files[2].size!=null){
	    		var a=document.getElementById('myFile').files[0].size;
		    	var b=document.getElementById('myFile').files[1].size;
		    	var c=document.getElementById('myFile').files[2].size;
		    	var result=a+b+c;
		    	alert("Taille des fichiers "+result+" Bytes");
		    	
				var a=confirm("Continuer");
    		}
    	}
    </script>
  </body>
</html>

