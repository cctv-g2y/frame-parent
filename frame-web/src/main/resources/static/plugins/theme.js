


function theme(color){
	$.cookie('theme', color, { expires: 7, path: '/' });
	window.location.reload();
}

$(function(){
	var theme=$.cookie('theme');
	if(!(theme==null||theme==undefined||theme=="")){
		$(".themeLink").attr("href","../css/bootstrap."+theme+".css");
		$(this).contents().find(".themeLink").attr("href","../css/bootstrap."+theme+".css");
	}
	
});
