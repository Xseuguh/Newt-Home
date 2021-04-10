/*ceci est pour image slider**/
var tableauImage=[
	"cheverny/Cheverny_Chateau_1.jpg",
	"cheverny/Cheverny_Chateau_2.jpg",
	"cheverny/Cheverny_Chateau_3.jpg",
];
var num=0;
function suivant(){
	var galerieImage=document.getElementById("galerieImage");
	num++;
	if(num>=tableauImage.length){
		num=0;
	}
	galerieImage.src=tableauImage[num];
}

function precedent(){
	var galerieImage=document.getElementById("galerieImage");
	num--;
	if(num<0){
		num=tableauImage.length-1;
	}
	galerieImage.src=tableauImage[num];	
}