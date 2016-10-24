var email_pattern=/(\S)+[@]{1}(\S)+[.]{1}(\w)+/;
var telphone_pattern=/^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/;
var regPwd = /^(\S){6,16}$/;//6-20个字符/下划线/数字/字母都可以
var regUserName =  /^[\u4e00-\u9fa5a-zA-Z0-9_!@#$%^&*()~`]{2,12}$/;//6-20个字符

$(function(){
	$("#regAccount").blur(function(){
		$("#account_error_message").remove();
		var currValue = $(this).val();
		if(!currValue){
    		$(this).after("<p class=\"color-red\" id='account_error_message'>"+mustEmail+"</p>");
    		return false;
    	}
		if(currValue.indexOf("@")!=-1&&!email_pattern.exec(currValue)){
    		$(this).after("<p class=\"color-red\" id='account_error_message'>"+emailFormat+"</p>");
    		return false;
    	}
		valid_reg_account();
		return true;
	});
	
	$("#regPassword").blur(function(){
		var regPwd = /^(\S){6,16}$/;//6-20个字符/下划线/数字/字母都可以
		$("#password_error_message").remove();
		if(!$(this).val()){
			$(this).after("<p class=\"color-red\" id='password_error_message'>"+pwdNotNull+"</p>");
    		return false;
		}else if(!regPwd.exec($(this).val())){
			$(this).after("<p class=\"color-red\" id='password_error_message'>"+pwdLength+"</p>");	
    		return false;	
		}else{
			$(this).after("<p class=\"color-red\" id='password_error_message'></p>");
    		return true;
		}
	});
	
	$("#regConPassword").blur(function(){
		$("#repassword_error_message").remove();
		if(!$(this).val()){
			$(this).after("<p class=\"color-red\" id='repassword_error_message'>"+confirmPwd+"</p>");
    		return false;
		}
		if($(this).val()!=$("#regPassword").val()){
    		$(this).val("");
    		$(this).after("<p class=\"color-red\" id='repassword_error_message'>"+pwdConsistant+" </p>");
    		$("#regPassword").val("");
    		$("#regPassword").focus();
    		return false;
    	}else{
    		$(this).after("<p class=\"color-red\" id='repassword_error_message'></p>");
    		return true;
    	}
	});
	
	$("#regNickName").blur(function(){
		$("#userName_error_message").remove();
		if(!$(this).val()){
			$(this).after("<p class=\"color-red\" id='userName_error_message'>"+regNickname+"</p>");
    		return false;
		}
		if(!regUserName.exec($(this).val())){
			$(this).after("<p class=\"color-red\" id='userName_error_message'>"+regNicknameFormat+"</p>");
    		return false;
		}else{
//			valid_reg_nickname();
			return true;
		}
	});
	
	$("#regHeight").blur(function(){
		$("#height_error_message").html("");
		//if($.trim($(this).val())=="" || /[^\d]/.test($.trim($(this).val()))){
		if($.trim($(this).val())=="" || /[^\d+(\d|(\.\d{1}))$]/.test($.trim($(this).val()))  ){
			$("#height_error_message").html("<p class=\"color-red\" id='height_error_message'>"+regHeightMsg+"</p>");
    		return false;
		}else{
			var h = $.trim($(this).val()) ;
			if(h.substr(h.indexOf('.')).length>2){
				$("#height_error_message").html("<p class=\"color-red\" id='height_error_message'>"+regWeightFormatThreeMsg+"</p>");
				return false;
			}
			
			if(parseInt($.trim($(this).val())) > 240 || parseInt($.trim($(this).val())) < 90){
				$("#height_error_message").html("<p class=\"color-red\" id='height_error_message'>"+regHeightFormatMsg+"</p>");
	    		return false;
			}
		}
	});
	$("#heightFt").blur(function(){
		$("#height_error_message").html("");
		if($.trim($(this).val())=="" || /[^\d]/.test($.trim($(this).val()))){
			$("#height_error_message").html("<p class=\"color-red\" id='height_error_message'>"+regFtMsg+"</p>");
    		return false;
		}else{
			if(parseInt($.trim($(this).val())) > 7 || parseInt($.trim($(this).val())) < 3){
				$("#height_error_message").html("<p class=\"color-red\" id='height_error_message'>"+regFtFormatMsg+"</p>");
	    		return false;
			}
		}
	});
	$("#heightIn").blur(function(){
		$("#height_error_message").html("");
		if($.trim($(this).val())=="" || /[^\d]/.test($.trim($(this).val()))){
			$("#height_error_message").html("<p class=\"color-red\" id='height_error_message'>"+regFtinMsg+"</p>");
    		return false;
		}else{
			if(parseInt($.trim($(this).val())) > 11 || parseInt($.trim($(this).val())) < 0){
				$("#height_error_message").html("<p class=\"color-red\" id='height_error_message'>"+regFtinFormatMsg+"</p>");
	    		return false;
			}
		}
	});
	
	$("#regWeight").blur(function(){
		$("#weight_error_message").remove(); 
		if($.trim($(this).val())=="" || /[^\d+(\d|(\.\d{1}))$]/.test($.trim($(this).val()))  ){
			$(this).next().after("<p class=\"color-red\" id='weight_error_message'>"+regWeightMsg+"</p>");
    		return false;
		}else{
			var w = $.trim($(this).val()) ;
			if(w.substr(w.indexOf('.')).length>2){
				$(this).next().after("<p class=\"color-red\" id='weight_error_message'>"+regWeightFormatThreeMsg+"</p>");
				return false;
			}
			if($("#weightUnit").val()==0){
				if(parseInt($.trim($(this).val())) > 400.9 || parseInt($.trim($(this).val())) < 30){
					$(this).next().after("<p class=\"color-red\" id='weight_error_message'>"+regWeightFormatMsg+"</p>");
		    		return false;
				}
			}else{
				if(parseInt($.trim($(this).val())) > 999.9 || parseInt($.trim($(this).val())) < 70){
					$(this).next().after("<p class=\"color-red\" id='weight_error_message'>"+regWeightFormatTwoMsg+"</p>");
		    		return false;
				}
			}
		}
	});
	

	$("#heightUnit").change(function(){
		if(this.value == 1){
			$("#weightUnit").val(1);
			$("#sftin").attr("style", "");
			$("#shtft").attr("style","");
			$("#sht").attr("style","display:none");
		}else{
			$("#weightUnit").val(0);
			$("#sftin").attr("style", "display:none");
			$("#shtft").attr("style","display:none");
			$("#sht").attr("style","");
		}
	});
	
	$("#weightUnit").change(function(){
		if(this.value == 1){
			$("#heightUnit").val(1);
			$("#sftin").attr("style", "");
			$("#shtft").attr("style","");
			$("#sht").attr("style","display:none");
		}else{
			$("#heightUnit").val(0);
			$("#sftin").attr("style", "display:none");
			$("#shtft").attr("style","display:none");
			$("#sht").attr("style","");
		}
	});
	
	$("#btnRegister").click(function(){
		$("#service_error_message").remove();
		$("#account_error_message").remove();
		$("#password_error_message").remove();
		$("#repassword_error_message").remove();
		$("#userName_error_message").remove();
		var heightErrMsg = $("#height_error_message").text();
		var weightErrMsg = $("#weight_error_message").text();
		
		if($("#regAccount").val().indexOf("@")==-1){
			$("#regAccount").after("<p class=\"color-red\" id='account_error_message'>"+regEmailCheckMsg+" </p>");
			return false;
		}else if($("#regAccount").val().indexOf("@")!=-1&&!email_pattern.exec($("#regAccount").val())){
			$("#regAccount").after("<p class=\"color-red\" id='account_error_message'>"+regEmailCheckMsg+"</p>");
			return false;
		}else if(!$("#regPassword").val() || !regPwd.exec($("#regPassword").val())){
			$("#regPassword").after("<p class=\"color-red\" id='password_error_message' >"+regPwdCheckMsg+"</p>");
			return false;
		}else if(!$("#regConPassword").val() || !regPwd.exec($("#regConPassword").val())){
			$("#regConPassword").after("<p class=\"color-red\" id='repassword_error_message' >"+regRepwdCheckMsg+"</p>");
			return false;
		}else if(!$("#regNickName").val() || !regUserName.exec($("#regNickName").val())){
			$("#regNickName").after("<p class=\"color-red\" id='userName_error_message' >"+regNameCheckMsg+"</p>");
			return false;
		}else if(!$("#readerService").attr("checked")){
			$("#readerService").next().next().after("<p class=\"color-red\" id='service_error_message' >"+regServiceCheckMsg+"</p>");
			return false;
		}else if (heightErrMsg && heightErrMsg!=""){
			return false;
		}else if(weightErrMsg && weightErrMsg!=""){
			return false;
		}
		
		var param = {};
		var personalInfo = {};
		var personal = {};
		var email =$("#regAccount").val();
		var role = "user";
		var passwd = $("#regPassword").val();
		/* --------编辑个人信息---------- */
		var gender = $("input[name='user.gender']").val();
		var userName = $("#regNickName").val();
		if(gender == 0){
			gender = "MALE";
		}else{
			gender = "FEMALE";
		}
		var birthday = $("#alternate").val();
		var height = $("#regHeight").val();
		var weight = $("#regWeight").val();
		var heightunit = $("#heightUnit").find("option:selected").text();
		var weightUnit = $("#weightUnit").find("option:selected").text();
		var country = $("#country").val();;
		
		personalInfo.gender = gender;
		personalInfo.nickname = userName;
		personalInfo.dateOfBirth = birthday;
		personalInfo.height = parseInt(height);
		personalInfo.weight = parseInt(weight);
		personalInfo.heightUnit = "IMPERIAL";
		personalInfo.weightUnit = "METRIC";
		personalInfo.country = country;
		personalInfo.aboutMe = "";
		personalInfo.city = "";
		personalInfo.state = "";
		
		personal.data = personalInfo;
		
		var account_detail = {};
		account_detail.email = email;
		account_detail.role = "user";
		account_detail.locale1="";
		account_detail.tz="";
		account_detail.last_name="";
		account_detail.first_name="";
		param.account_detail=account_detail;
		param.password = passwd;
		param.active = true;
		
		account_detail.password= passwd;
		account_detail.active= true;
		
		$.ajax({
			url:cxtPath+"/newRegister",
			type :"POST",
			dataType: 'json',
			data: account_detail,
			success:function(data){
				if(typeof(data.code) !== undefined && data.code == 409){
					$("#btnRegister").parent().next().remove();
					$("#btnRegister").parent().after("<p class='color-red' id='account_result_message'>"+data.message+"</p>");
					return false;
				}
		    	if (typeof(data.token) !== undefined){
		    		var token = data.token;
		    		localStorage.setItem("jwt", token);
		    		localStorage.setItem("domain",data.domain);
				    $.ajax({
						url:data.domain+"v1/data/personal",
						type :"POST",
						dataType: 'json',
						contentType:"application/json; charset=utf-8",
						data: JSON.stringify(personal),
						headers :{"Authorization":config.tokenHeader+" "+token},
						error: function(XHR,textStatus,errorThrown) {
				            var statusCode = XHR.responseText.code;
				            var msg = XHR.responseText.message;
				            //user exists tip
				            if(statusCode == 409){
				            	$("#btnRegister").parent().next().remove();
				            	$("#btnRegister").parent().after("<p class='color-red' id='account_result_message'>"+data.message+"</p>");
				            }
				            return false;
				        },
						success:function(data){
					    	if (data != null){
					    		$("#btnRegister").parent().next().remove();
				            	$("#btnRegister").parent().after("<p class='color-red' id='account_result_message'>"+data.message+"</p>");
					    		window.location.href=cxtPath+"/user/index/logined";
					    		return false;
							}
						}
				
				});
				}else{
					$("#account_error_message").html("");
					$("#account_error_message").html(data);
				}
			}
		
		});
		
		return false;
	});
	
});




function valid_reg_account(){
//	$("#account_error_message").remove();
//	$.post(cxtPath+'/valid_account',{"user.account":$("#regAccount").val()},function(results){
//		if(!results)
//			$("#regAccount").after("<p class=\"color-red\" id='account_error_message' >"+regAccountReg+"</p>");
//	},'json');
}

function valid_reg_nickname(){
	$("#nickname_error_message").remove();
	$.post(cxtPath+'/valid_nickname',{"user.nickName":$("#regNickName").val()},function(results){
		if(!results){
			$("#regNickName").focus();
			$("#regNickName").attr("nick", "false");
			if($("#regNickName").html()==""){
				$("#regNickName").after("<p class=\"color-red\" id='nickname_error_message' >"+regNameUsed+"</p>");
			}
		}
		else{
			$("#regNickName").attr("nick", "true");
		}
	},'json');
}

function getCheckCode() {
    $("#changeVercode").attr("src",cxtPath+"/authImg.jpg?t="+Math.random());
}

//document.onkeydown = function(e) {
//	var theEvent = window.event || e;
//	var code = theEvent.keyCode || theEvent.which;
//	if (code == 13) {
//		$("#btnRegister").click();
//	}
//};