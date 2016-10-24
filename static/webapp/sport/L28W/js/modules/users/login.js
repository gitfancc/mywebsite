var email_pattern=/(\S)+[@]{1}(\S)+[.]{1}(\w)+/;
var telphone_pattern=/^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/;
$(function(){
	$("#account").blur(function(){
		return valid_account();
	});
	$("#password").blur(function(){
		return valid_password();
	});
	
	$("#userLogin").click(function(){
		if(!valid_account()){return false;}
		if(!valid_password()){return false;}
		$("#userLogin").attr('disabled',"true");
		var account = $("#account").val();
		var passwd = $("#password").val();
		var param = {};
		param.account = account;
		param.password = passwd;
		$.ajax({
			url:cxtPath+"/login",
			type :"POST",
			dataType: 'json',
			data: param,
		    success:function(data){
		    	if (typeof(data.token) !== undefined && data.token != null){
		    		localStorage.setItem("jwt", data.token);
		    		localStorage.setItem("domain", data.domain);
					window.location.href=cxtPath+"/user/index/logined";
				}else{
					if(typeof(data.code) !== undefined &&data.code == 403){
						$("#account_error_message").html("");
						$("#account_error_message").html(data.message);
					}else{
						console.log(JSON.stringfy(data));
					}
				}
		   }
			
		});
	});
});

valid_account = function(){
	$("#account_error_message").html("");
	if(!$("#account").val()){
		$("#account_error_message").html(mustEmail);
		return false;
	}else if($("#account").val().indexOf("@")==-1&&!email_pattern.exec($("#account").val())){
		$("#account_error_message").html(mustEmail);
		return false;
	}
	return true;
};

valid_password = function(){
	$("#password_error_message").html("");
	if(!$("#password").val()){
		$("#password_error_message").html(pwdNotNull);
		return false;
	}
	var rePwd = /^(\S){6,20}$/;
	if(!rePwd.exec($("#password").val())){
		$("#password_error_message").html(pwdLength);
		return false;
	}
	return true;
};

document.onkeydown = function(e) { 
	var theEvent = window.event || e;
	var code = theEvent.keyCode || theEvent.which;
	if (code == 13) {
		$("#userLogin").click();
	}
};