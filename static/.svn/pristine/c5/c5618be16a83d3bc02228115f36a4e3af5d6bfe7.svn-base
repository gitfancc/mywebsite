//左侧等级效果
pageonloadFun = function (){
	$.post(cxtPath+'/user/sport/sumStepsByWatchId',function(data){
		var stepspercent = 0;
		stepspercent = stepPercent(data);
		stepspercent = parseInt(stepspercent.substring(0,stepspercent.indexOf("%")));
		$( "#progressbar" ).progressbar({
			value: stepspercent
		});
		
		var level = showlevel(data);
		$("#level").text(spLevel+level);
	},'json');
};

//计算progressbar百分比
stepPercent = function(steps){
	if(0 <= steps && steps <= 9999){
		var s = steps/9999*100;
		return s+"%";
	}else if(10000 <= steps && steps <= 49999){
		var s = steps/49999*100;
		return s+"%";
	}else if(50000 <= steps && steps <= 159999){
		var s = steps/159999*100;
		return s+"%";
	}else if(160000 <= steps && steps <= 499999){
		var s = steps/499999*100;
		return s+"%";
	}else if(500000 <= steps && steps <= 999999){
		var s = steps/999999*100;
		return s+"%";
	}else if(1000000 <= steps && steps <= 1999999){
		var s = steps/1999999*100;
		return s+"%";
	}else if(2000000 <= steps && steps <= 3999999){
		var s = steps/3999999*100;
		return s+"%";
	}else if(4000000 <= steps && steps <= 7999999){
		var s = steps/7999999*100;
		return s+"%";
	}else if(8000000 <= steps && steps <= 15999999){
		var s = steps/15999999*100;
		return s+"%";
	}else if(16000000 <= steps && steps <= 31999999){
		var s = steps/31999999*100;
		return s+"%";
	}else if(32000000 <= steps && steps <= 63999999){
		var s = steps/63999999*100;
		return s+"%";
	}else if(64000000 <= steps && steps <= 127999999){
		var s = steps/127999999*100;
		return s+"%";
	}else if(128000000 <= steps ){
		return "100%";
	}
};

showlevel = function(steps){
	if(0 <= steps && steps <= 9999){
		return " 0";
	}else if(10000 <= steps && steps <= 49999){
		return " 1";
	}else if(50000 <= steps && steps <= 159999){
		return " 2";
	}else if(160000 <= steps && steps <= 499999){
		return " 3";
	}else if(500000 <= steps && steps <= 999999){
		return " 4";
	}else if(1000000 <= steps && steps <= 1999999){
		return " 5";
	}else if(2000000 <= steps && steps <= 3999999){
		return " 6";
	}else if(4000000 <= steps && steps <= 7999999){
		return " 7";
	}else if(8000000 <= steps && steps <= 15999999){
		return " 8";
	}else if(16000000 <= steps && steps <= 31999999){
		return " 9";
	}else if(32000000 <= steps && steps <= 63999999){
		return " 10";
	}else if(64000000 <= steps && steps <= 127999999){
		return " 11";
	}else if(128000000 <= steps){
		return " 12";
	}
};