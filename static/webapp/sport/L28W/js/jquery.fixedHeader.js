// JavaScript Document
$(function(){
	$(window).scroll(function () {
		 var scrolltop = $(this).scrollTop();
		 if(scrolltop >= 200){
			$("#header-small").slideDown(200);
		 }else  if(scrolltop <= 300) {
			$("#header-small").slideUp(10);

		 }
	}); 
})