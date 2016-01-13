var leftMenu = document.getElementById("leftMenu");
var menuItems=document.getElementsByClassName("fields");

//hide the menu items
for(i = 1; i < menuItems.length; i++){
	//so the 1st list item is not hidden but the rest yes
	menuItems[i].style.display="none";
}

//setting a attribute for each title for future use
menuItems=document.getElementsByClassName("title");
for(i=0;i<menuItems.length;i++){
	menuItems[i].setAttribute("titleCount",i);
}

//event handler for the menu animation
var menu = document.querySelector(".leftMenu");
menu.addEventListener("click", function(e){
	if(e.target.getAttribute("class")=="title"){
	
		//getting the "titles" form the list
		menuItems=document.getElementsByClassName("title");
		//get the position of the clicked title
		var pos = e.target.getAttribute("titleCount");
		
		//animate the menu
		if(menuItems[pos].nextSibling.nextSibling.style.display=="none"){
			menuItems[pos].nextSibling.nextSibling.style.display="block";
			
			for(i=0;i<menuItems.length;i++){
				if(i==pos) continue;
				menuItems[i].nextSibling.nextSibling.style.display="none";
			}
		}
	}
	
});