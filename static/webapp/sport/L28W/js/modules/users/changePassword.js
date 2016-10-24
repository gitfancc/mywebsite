$(function(){
		$("#password1").blur(function(){
			var regPwd = /^(\S){6,16}$/;//6-20个字符/下划线/数字/字母都可以
			if(!$(this).val()){
				$(this).next(".color-red").html(changePasswordV3);
			}else if(!regPwd.exec($(this).val())){
				$(this).next(".color-red").html(changePasswordV1);
			}else{
				$(this).next(".color-red").html("");
			}
			
		});
		$("#password2").blur(function(){
			if(!$(this).val()){
				$(this).next(".color-red").html(changePasswordV3);
			}else if($(this).val() != $("#password1").val()){
				$(this).next(".color-red").html(changePasswordV2);
			}else{
				$(this).next(".color-red").html("");
			}
		});
		
		$("#btnChangePwd").click(function(){
			$(this).attr("disabled","true");
			var password = $("#password1").val();
			var password2 = $("#password2").val();
			//var successmsg = "<@s.text name='page.changePassword.success'/>";
    		//$(".content").html(successmsg);
			if(password == null || password.length == 0){
				return false;
			}
			if(password2 == null || password2.length== 0){
				return false;
			}
			
			if(password != password2){
				return false;
			}
			$(".color-red").each(function(){
				$(this).remove();
			});
			var param = {};
			param.password = password;
			var token = $("#changePassword").attr("action");
				$.ajax({
					url:config.host+"v1/account/validate/password/json/"+token,
					type :"POST",
					crossDomain: true,
					dataType: 'json',
					contentType:"application/json; charset=utf-8",
					data: JSON.stringify(param),
					/* headers :{"Authorization":config.tokenHeader+" "+token}, */
					error: function(XHR,textStatus,errorThrown) {
			            var statusCode = XHR.responseText.code;
			            var msg = XHR.responseText.message;
			            //user did't exists
			            if(statusCode == 404){
			            	console.log(msg);
			            }
			            return false;
			        },
				    success:function(data){
				    	if(data.account !== undefined){
				    		var successmsg = "<@s.text name='page.changePassword.success'/>";
				    		$(".content > .ui-grid-row").html(changePasswordsuccess);
				    	}else{
				    		var failmsg = "<@s.text name='page.changePassword.fail'/>";
				    		$(".content > .ui-grid-row").html(changePasswordfail);
				    	}
				   }
					
				});
		});
		
	});