   //图片上传预览    IE是用了滤镜。
function previewImage(file) {
	var MAXWIDTH  = 200; 
	var MAXHEIGHT = 200;
	var div = document.getElementById('preview');
	if (file.files && file.files[0]) {
		div.innerHTML ='<img  id=imghead>';
		var img = document.getElementById('imghead');
		img.onload = function(){
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
			img.width  =  rect.width;
			img.height =  rect.height;
//             img.style.marginLeft = rect.left+'px';
			img.style.marginTop = rect.top+'px';
		};
		var reader = new FileReader();
		reader.onload = function(evt){
			img.src = evt.target.result;
		};
              reader.readAsDataURL(file.files[0]);
	}
	else  { //兼容IE
		var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
		file.select();
		var src = document.selection.createRange().text;
		div.innerHTML = '<img id=imghead>';
		var img = document.getElementById('imghead');
		img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
		var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
		status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
		div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
	 }
	
	//图片上传
	//var param = new FormData($('#save')[0]);
	var formdata = new FormData();
	var tokenHeader = "Bearer ";
	formdata.append("file",$('#uploadFile')[0].files[0]);
	 $.ajax({
		  url:config.host+'v1/data/personal/avatar',
		  headers :{"Authorization":tokenHeader+token},
		  type:'POST',
		  data:formdata,
		  cache: false,
		  mimeTypes:"multipart/form-data",
		  contentType: false,    //不可缺
		  processData: false,    //不可缺
		  dataType: 'json',
		  success:function(data){
			  if(data.path !== undefined){
				  $(".cus-uploadfile").find("a").next().remove();
				  $(".cus-uploadfile").find("a").after("<p class='color-red' >upload success</p>");
			  }else{
				  $(".cus-uploadfile").find("a").next().remove();
				  $(".cus-uploadfile").find("a").after("<p class='color-red' >upload failed</p>");
			  }
		  	}
		  });
	
//	var req = new XMLHttpRequest();
//	var url = config.host+'v1/data/personal/avatar';
//	req.open('POST', url, false);
//	req.setRequestHeader("Authorization",tokenHeader+" "+token);
//	req.overrideMimeType("multipart/form-data")
////	req.channel.loadFlags |= Components.interfaces.nsIRequest.LOAD_BYPASS_CACHE;
//	req.send(param);
}

function clacImgZoomParam( maxWidth, maxHeight, width, height ){
	var param = {top:0, left:0, width:width, height:height};
	if( width>maxWidth || height>maxHeight ) {
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;
		if( rateWidth > rateHeight )	{
			param.width =  maxWidth;
			param.height = Math.round(height / rateWidth);
		}else {
			param.width = Math.round(width / rateHeight);
			param.height = maxHeight;
		}
	}
            
	param.left = Math.round((maxWidth - param.width) / 2);
	param.top = Math.round((maxHeight - param.height) / 2);
	return param;
 }