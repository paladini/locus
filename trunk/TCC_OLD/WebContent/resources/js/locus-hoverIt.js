/*

Locus HoverIt V 0.1
-----------------------

Author: Fernando Paladini
Date: 09/19/2013

-------------------------------------------------------------------
DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE (WTFPL)
                    Version 2, December 2004 

 Copyright (C) 2004 Sam Hocevar <sam@hocevar.net> 

 Everyone is permitted to copy and distribute verbatim or modified 
 copies of this license document, and changing it is allowed as long 
 as the name is changed. 

            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE 
   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION 

  0. You just DO WHAT THE FUCK YOU WANT TO.
--------------------------------------------------------------------

*/

$(document).ready(function(){
    $(".menu-principal")
	.mouseover(function(){
		var path = $(this).attr('src');
		path = path.substring(0, path.length - 4);
		var src = path + "-selecionado.png";
		$(this).attr("src",src);
	})
	.mouseout(function(){
		var src = $(this).attr('src').replace("-selecionado.png", ".png");
		$(this).attr("src",src);
	});
});