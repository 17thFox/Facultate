$(document).ready(function () {
	var leftMenu = document.getElementById("leftMenu");
	var menuItems = document.getElementsByClassName("fields");

	//hide the menu items
	for(i = 1; i < menuItems.length; i++)
	{
		//so the 1st list item is not hidden but the rest yes
		menuItems[i].style.display = "none";
	}

	//event handler for the menu animation
	var menu = document.querySelector(".leftMenu");
	menu.addEventListener("click", function(e) {
		if(e.target.getAttribute("class")=="title"){
			
			//getting the "titles" form the list
			titles = document.getElementsByClassName("title");

			//hide sub items
			for(i = 0; i < titles.length; i++){
				titles[i].nextSibling.nextSibling.style.display = "none";
			}

			e.target.nextSibling.nextSibling.style.display = "block";
		}
	});
});