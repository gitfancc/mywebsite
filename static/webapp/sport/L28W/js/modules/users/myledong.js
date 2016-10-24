var stepsTotal = 0, calTotal = 0, distanceTotal = 0, sleepTotal = 0, heart =0;
var stepsTarget = 10000, calTarget=500, distanceTarget=1000, sleepTarget=20, heartMax = 200;
var xArray = [00,01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23];
var stepsArray = [], sleepArray = [], calArray = [], distArray = [], heartRateXArray = [], heartRateArray = [], sleepXArray = [];
var w=weeks.split(",");
var m=months.split(",");
var lowerw=lowerweeks.split(",");
var sleepGageTip="";
var a,b,c,d,e,f;
$(function(){
	var now = new Date();
	now = (now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate());
	Lecomm.DaySport.LoadSportDatas("STEPS",now,token);
	Lecomm.DaySport.LoadSportDatas("DISTANCE",now,token);
	Lecomm.DaySport.LoadSportDatas("CAL",now,token);
	Lecomm.DaySleep.LoadSleepDatas(now,token);
	
	$("#searchDate").val(now);
	//初次加载完成===========================================
	
	$("#previous").click(function(){
		//加载用户目标值
		 //loadSportTarget();
		var currDate = $("#searchDate").val();
		var alterDate = $("#alternate").val();
		var now;
		if(alterDate !== ""){
			now = Date.parse(alterDate.replace(/-/g, "/"));
			$("#searchDate").val(now);
		}else{
			now = Date.parse(currDate.replace(/-/g, "/"));
		}
		var searchDate = new Date(now - 24*60*60*1000);
		searchDate = (searchDate.getFullYear()+"-"+(searchDate.getMonth()+1)+"-"+searchDate.getDate());

		$("#alternate").val(searchDate);
		Lecomm.DaySport.LoadSportDatas("STEPS",searchDate,token);
		Lecomm.DaySport.LoadSportDatas("DISTANCE",searchDate,token);
		Lecomm.DaySport.LoadSportDatas("CAL",searchDate,token);
		Lecomm.DaySleep.LoadSleepDatas(searchDate,token);
		$("#searchDate").val(searchDate);
//		getSleepInfo(searchDate);
	});
	
	$("#next").click(function(){
		var currDate = $("#searchDate").val();
		var alterDate = $("#alternate").val();
		var now;
		if(alterDate !== ""){
			now = Date.parse(alterDate.replace(/-/g, "/"));
			$("#searchDate").val(now);
		}else{
			now = Date.parse(currDate.replace(/-/g, "/"));
		}
		var searchDate = new Date(now + 24*60*60*1000);
		searchDate = (searchDate.getFullYear()+"-"+(searchDate.getMonth()+1)+"-"+searchDate.getDate());
		//加载用户目标值
		
		$("#alternate").val(searchDate);
		Lecomm.DaySport.LoadSportDatas("STEPS",searchDate,token);
		Lecomm.DaySport.LoadSportDatas("DISTANCE",searchDate,token);
		Lecomm.DaySport.LoadSportDatas("CAL",searchDate,token);
		Lecomm.DaySleep.LoadSleepDatas(searchDate,token);
		$("#searchDate").val(searchDate);
	});
	
	$("#datepicker").change(function(){
		var searchDate = $("#alternate").val();
		Lecomm.DaySport.LoadSportDatas("STEPS",searchDate,token);
		Lecomm.DaySport.LoadSportDatas("DISTANCE",searchDate,token);
		Lecomm.DaySport.LoadSportDatas("CAL",searchDate,token);
		Lecomm.DaySleep.LoadSleepDatas(searchDate,token);
		$("#searchDate").val(searchDate);
	});
	
    //steps, distance, calories, and selected
    $("ul.nav-tabs li a").click(function(){
        $(this).parents("ul").find("li").removeClass("active");
        $(this).parent().addClass("active");
    });
    
});

Lecomm.DaySleep = {
	LoadSleepDatas : function(date,token){
    	$.ajax({
    		url:config.host+"v1/data/sleep/"+date+"/1d",
    		type :"get",
    		crossDomain: true,
    		dataType: 'json',
    		contentType:"application/json; charset=utf-8",
    		headers :{"Authorization":config.tokenHeader+token},
    		error: function(XHR,textStatus,errorThrown) {
    			console.log(textStatus);
    			return false;
    		},
    		success:function(data){
    			Lecomm.DaySleep.ParseJSONSleepData(date, data);
    		}
    		
    	});
    },
    
    ParseJSONSleepData : function(currentTime, data){
    	sleepArray = [];
    	sleepXArray = [];
    	sleepTotal = 0;
    	if(data.length!=0){
			$("#main_sleep").removeClass("nodata");
				var lightDuration = data[0].lightDuration;
				var awakeDuration = data[0].awakeDuration;
				var deepDuration = data[0].deepDuration;
				var sleepDuration = data[0].sleepDuration;
				
				var lightDu = lightDuration/60;
				var deepDu = deepDuration/60;
				var sleepDu = sleepDuration/60;
				var awakeDu = awakeDuration/60;
				var totalDu = awakeDu+lightDu+deepDu;
				
				sleepTotal = Math.round(totalDu*1000)/1000;
				sleepArray.push(sleepTotal);
				sleepXArray.push(currentTime);
				$("#lightDuration").text(parseInt((lightDu*60)/60));
				$("#lightDuration_2").text(parseInt((lightDu*60)%60));
				$("#deepDuration").text(parseInt((deepDu*60)/60));
				$("#deepDuration_2").text(parseInt((deepDu*60)%60));
				$("#awakeDuration").text(parseInt(awakeDu*60));
				$("#awakeCount").text(0);
				$("#sleepDuration").text(parseInt(sleepDu*60));
				$("#totalsleep").text(parseInt((totalDu*60)/60));
				$("#totalsleep_2").text(parseInt((totalDu*60)%60));
		}else{
				$("#lightDuration").text(0);
				$("#lightDuration_2").text(0);
				$("#awakeDuration").text(0);
				$("#deepDuration").text(0);
				$("#deepDuration_2").text(0);
				$("#sleepDuration").text(0);
				$("#awakeCount").text(0);
				$("#totalDuration").text(0);
				$("#totalDuration_2").text(0);
				$("#totalsleep").text(0);
				$("#totalsleep_2").text(0);
				$("#main_sleep").html('');
		}
    	loadJustGage(stepsTotal,0,calTotal,distanceTotal, sleepTotal);
    	f.refreshEx(sleepTotal,sleepTotal+"", parseInt(sleepTarget));
    }
}

Lecomm.DaySport = {
	ParseJSONSportData : function(type, currentTime, data){
		if (data.hours!== undefined){
			if(type == 'STEPS'){
				stepsArray = data.hours;
				stepsTotal = Lecomm.DaySport.CountSportData(stepsArray);
				loadJustGage(stepsTotal,0,calTotal,distanceTotal, sleepTotal);
				Lecomm.DaySport.ShowCountData(currentTime);
				$("#stepsA").trigger("click");
			}
			if(type == 'DISTANCE'){
				distArray = Lecomm.DaySport.TranferUnit(data.hours);
				distanceTotal = Lecomm.DaySport.CountSportData(distArray);
				distanceTotal = Math.round(distanceTotal*1000)/1000;
				loadJustGage(stepsTotal,0,calTotal,distanceTotal, sleepTotal);
				Lecomm.DaySport.ShowCountData(currentTime);
			}
			if(type == 'CAL'){
				calArray = Lecomm.DaySport.TranferUnit(data.hours);
				calTotal = Lecomm.DaySport.CountSportData(calArray);
				calTotal = Math.round(calTotal*1000)/1000;
				loadJustGage(stepsTotal,0,calTotal,distanceTotal, sleepTotal);
				Lecomm.DaySport.ShowCountData(currentTime);
			}
		}
	},
	
	ShowCountData : function(currentTime){
		var results = {};
		results.stepsTotal = stepsTotal;
		results.distanceTotal = distanceTotal;
		results.calTotal = calTotal;
		handleSportResult(currentTime, results, currentTime);
	},
	
	//转换数据
	TranferUnit : function(arr){
    	var len = arr.length;
    	for(var i=0;i<len;i++){
    		if(arr[i] > 0){
    			arr[i]=Math.round(arr[i])/1000;
    		}
    	}
    	return arr;
    },
	
	CountSportData : function(arr){
		var total = 0;
		var len = arr.length;
		for(var i=0;i<len;i++){
			total+=arr[i];
		}
		return total;
	},
	
    LoadSportDatas : function(type,date,token){
    	$.ajax({
    		url:config.host+"v1/data/tracking/activities/hourly/"+type+"/"+date,
    		type :"get",
    		crossDomain: true,
    		dataType: 'json',
    		contentType:"application/json; charset=utf-8",
    		headers :{"Authorization":config.tokenHeader+token},
    		error: function(XHR,textStatus,errorThrown) {
    			console.log(textStatus);
    			return false;
    		},
    		success:function(data){
    			Lecomm.DaySport.ParseJSONSportData(type, date, data);
    		}
    		
    	});
    }
},

handleSportResult = function(newDate, results,latelyTime){
	showDate(newDate,  latelyTime);
	
	stepsTotal = results.stepsTotal;
	distanceTotal = results.distanceTotal;
	calTotal = results.calTotal;
	
	refreshJustGage(stepsTotal, distanceTotal, calTotal);
};

refreshJustGage = function(stepsTotal, distanceTotal, calTotal){
	a.refreshEx(stepsTotal,"", parseInt(stepsTarget));
	d.refreshEx(calTotal,"", parseInt(calTarget));
	e.refreshEx(distanceTotal,"", parseInt(distanceTarget));
};

dateTopTitle = function(curDate){
	if(curDate == 1 || curDate == 21 || curDate == 31){
		$("#currentDate").append("<sup>st</sup>");
	}else if(curDate == 2 || curDate == 22){
		$("#currentDate").append("<sup>nd</sup>");
	}else if(curDate == 3 || curDate == 23){
		$("#currentDate").append("<sup>rd</sup>");
	}
	else{
	   	$("#currentDate").append("<sup>th</sup>");
	}	
};

showDate = function(searchDate,  latelyTime){
	var currentDate = new Date();
	var newCurrentDate = (currentDate.getFullYear()+"-"+(currentDate.getMonth()+1)+"-"+currentDate.getDate()).replace(/-/g,"/");
	var newSearchDate = searchDate.replace(/-/g,"/");
	var nsd = searchDate.split("-");
	var sd = new Date(nsd[0],nsd[1]-1,nsd[2]);
	
	$("#currentDate").text("");
	if(Date.parse(newCurrentDate)-Date.parse(newSearchDate)==0){
		//$("#next").attr("class","btn-arrowright disable");
		$("#next").attr("class","btn-arrowr trans-zero");
		$("#datepicker").val(" "+myledongToday+" ");
		if(lang=="english"){
			$("#currentDate").append(lowerw[currentDate.getDay()]+", "+m[currentDate.getMonth()]+" "+currentDate.getDate()); //searchDate.substring(0,4));
			dateTopTitle(currentDate.getDate());
		}else{
			$("#currentDate").append(lowerw[currentDate.getDay()]+" "+currentDate.getDate()+" "+m[currentDate.getMonth()]); 
		}
		$("#currentDate").append(" " +currentDate.getFullYear());
	}else if(Date.parse(newCurrentDate)-Date.parse(newSearchDate)>0){
		$("#next").attr("class","btn-arrowright");
		if(Date.parse(newCurrentDate)-Date.parse(newSearchDate)==86400000){
			$("#datepicker").val(w[sd.getDay()]);
			if(lang=="english"){
				$("#currentDate").append(m[sd.getMonth()]+" "+sd.getDate());
				dateTopTitle(sd.getDate());
			}else{
				$("#currentDate").append(sd.getDate()+" "+m[sd.getMonth()]);
			}
			$("#currentDate").append(" " +sd.getFullYear());

		}else{
			//$("#datepicker").val(" "+searchDate+" ");
			$("#datepicker").val(w[sd.getDay()]);
			if(lang=="english"){
				$("#currentDate").append(m[sd.getMonth()]+" "+sd.getDate());
				dateTopTitle(sd.getDate());
			}else{
				$("#currentDate").append(sd.getDate()+" "+m[sd.getMonth()]);
			}
			$("#currentDate").append(" " +sd.getFullYear());
			
		}
	}else if(Date.parse(newCurrentDate)-Date.parse(newSearchDate)<0){
		$("#next").attr("class","btn-arrowr trans-zero");
	}
};

showStepsDiv = function(){
	$('#main_sleep').attr("style","display:none");
	$('#chart').attr("style","margin-left:-23px");
	showCharts(xArray, stepsArray,'','',myledongSteps);
};
showDistanceDiv = function(){
	var time = $("#searchDate").val();
	$('#main_sleep').attr("style","display:none");
	$('#chart').attr("style","margin-left:-23px");
	showCharts(xArray, distArray,'','',myledongKm);
};


showCalDiv = function(){
	var time = $("#searchDate").val();
	$('#main_sleep').attr("style","display:none");
	$('#chart').attr("style","margin-left:-23px");
	showCharts(xArray, calArray, '', '', myledongKcal);
};

showSleepDiv = function(){
	if(sleepTotal!=0){
		$('#main_sleep').attr("style","display:none");
		$('#chart').attr("style","margin-left:-23px");
		showCharts (sleepXArray, sleepArray,'','',myledongHour);
	}else{
		$('#chart').attr("style","display:none");
		$('#main_sleep').attr("style","");
	}
};

function subStr(str){
	if(str.indexOf(".")>0){
		str = str.substring(0, str.indexOf(".")+2);
		if(str == "0.0"){
			str = "0";
		}
	}
	return str;
}

showLines = function(){
	 $('#chart').highcharts({ 
		 chart: { 
			 type: 'line' 
		 },
		 title: { 
			 text: ''
		 }, 
		 subtitle: {
			 text: ''
		 }, 
		 xAxis: { 
			 type: 'datetime',  
             labels: {  
                formatter: function() {  
                	return  Highcharts.dateFormat('%H:%M', this.value);  
                },  
             }
		 }, 
		 yAxis: { 
			 title: { 
				 text: 'bpm' 
			 }, 
			 min : 0,
		        lineWidth: 1,
		        gridLineWidth:0,
		        tickLength: 5,
		        tickWidth: 1 
		 }, 
		 tooltip: { 
			 headerFormat: '<span style="font-size:12px">{point.key}</span><table>',
		     pointFormat: '<tr><td style="padding:0"><b>{point.y} ' + 'bpm' + '</b></td></tr>',
		     footerFormat: '</table>',
		     shared: true,
		     useHTML: true
		 }, 
		 credits: {
			enabled: false
		 },
		 legend: {
			enabled: false
		 },
		 series: [{ 
			data: [
					[Date.UTC(1970, 9, 27), 0 ], 
					[Date.UTC(1970, 10, 10), 0.6 ], 
					[Date.UTC(1970, 10, 18), 0.7 ], 
					[Date.UTC(1970, 11, 2), 0.8 ], 
					[Date.UTC(1970, 11, 9), 0.6 ], 
					[Date.UTC(1970, 11, 16), 0.6 ], 
					[Date.UTC(1970, 11, 28), 0.67], 
					[Date.UTC(1971, 0, 1), 0.81], 
					[Date.UTC(1971, 0, 8), 0.78], 
					[Date.UTC(1971, 0, 12), 0.98], 
					[Date.UTC(1971, 0, 27), 1.84], 
					[Date.UTC(1971, 1, 10), 1.80], 
					[Date.UTC(1971, 1, 18), 1.80], 
					[Date.UTC(1971, 1, 24), 1.92], 
					[Date.UTC(1971, 2, 4), 2.49], 
					[Date.UTC(1971, 2, 11), 2.79], 
					[Date.UTC(1971, 2, 15), 2.73], 
					[Date.UTC(1971, 2, 25), 2.61], 
					[Date.UTC(1971, 3, 2), 2.76], 
					[Date.UTC(1971, 3, 6), 2.82], 
					[Date.UTC(1971, 3, 13), 2.8 ], 
					[Date.UTC(1971, 4, 3), 2.1 ], 
					[Date.UTC(1971, 4, 26), 1.1 ], 
					[Date.UTC(1971, 5, 9), 0.25], 
					[Date.UTC(1971, 5, 12), 0 ]
			     ] 
		 }] 
	});
};

showCharts = function(xArray, dataArray,title,text,unit){
	$('#chart').highcharts({
	    chart: {
	        type: 'column',
	        width: 850,
	        height: 300
	    },
	    title: {
	        text: ''
	    },
	    subtitle: {
	        text: ''
	    },
	    xAxis: {
	        categories: xArray
	    },
	    yAxis: {
	    	min : 0,
	        lineWidth: 1,
	        gridLineWidth:0,
	        tickLength: 5,
	        tickWidth: 1,
	        title: {
	            text: unit
	        }
	    },
	    tooltip: {
	    	headerFormat: '<span style="font-size:12px">{point.key} h</span><table>',
	        pointFormat: '<tr><td style="padding:0"><b>{point.y} ' + unit + '</b></td></tr>',
	        footerFormat: '</table>',
	        shared: true,
	        useHTML: true
	    },
	    plotOptions: {
	        column: {
	            pointPadding: 0.2,
	            borderWidth: 0
	        }
	    },
	    series: [{
	        data: dataArray
	
	    }],
	    credits: {
			enabled: false
		},
		legend: {
			enabled: false
		}
	});
};

var loadJustGage = function(stepsTotal,speedTotal,calTotal,distanceTotal, sleepTotal){
	stepsTotal = subStr(100+"");
	distanceTotal = subStr(distanceTotal+"");
	calTotal = subStr(calTotal+"");
	sleepTotal = subStr(sleepTotal+"");
	a = new JustGage({
	    id: "stepCount", 
	    value: stepsTotal, 
	    min: 0,
	    max:stepsTarget,
	    title: myledongTitleStep,
	    levelColorsGradient: false,
	    showMinMax: true
	  });
	d = new JustGage({
	    id: "calories", 
	    value: calTotal, 
	    min: 0,
	    max: calTarget,
	    title: myledongTitleCalories,
	    levelColorsGradient: false,
	    showMinMax: true
	  });
	e = new JustGage({
	    id: "distance", 
	    value: distanceTotal,
	    min: 0,
	    max: distanceTarget,
	    title: myledongTitleDistance,
	    showMinMax: true,
	    levelColorsGradient: false
	  });
	f = new JustGage({
		id: "sleep",
		value: sleepTotal,
		min: 0,
		max: sleepTarget,
		title:myledongTitleSleep,
		showMinMax: true,
		levelColorsGradient: false
	});
//	g = new JustGage({
//		id: "heartRate",
//		value: heart,
//		min: 0,
//		max: heartMax,
//		title:'HEARTRATE',
//		showMinMax: true,
//		levelColorsGradient: false
//	});

};

/*点击日志分析  直接查询加载对应时间的chart、表格数据*/
function logAnalysis(){
	var currentTime = $("#searchDate").val();
	var url = cxtPath+'/user/sport/log?time='+currentTime;
	window.location.href = url; 
} 