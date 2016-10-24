
var g_minrate = "MINRATE";
var g_maxrate = "MAXRATE";
var g_avgrate = "AVGRATE";
var g_dayType;
var times = new Array();
var datas = new Array();
var sportDayUrl = config.host+"v1/data/tracking/activities/hourly/"
var sportUrl = config.host+"v1/data/tracking/activities/daily/";
var sleepUrl = config.host+"v1/data/sleep/";
var sportDateRangeUrl = config.host+"v1/data/tracking/activities/daily/";

var m=months.split(",");
var w=lowerweeks.split(",");
var opt;
var startTime;
var endTime;

$(function () {
	 startTime = new Date(), endTime = new Date();
     var df = "dd/MM/yyyy";
     $("#dateStart").val(startTime.Format(df));
     $("#dateEnd").val(endTime.Format(df));
     $("#LoadToday").trigger("click");
     
     $("ul.nav-tabs line l li a").click(function () {
         $(this).parents("ul").find("li").removeClass("active");
         $(this).parent().addClass("active");
     });
     //steps, distance, calories, and selected
     $("ul.nav-tabs li a").click(function () {
         $(this).parents("ul").find("li").removeClass("active");
         $(this).parent().addClass("active");
     });
 });

//将dd/mm/yyyy格式转为yyyy-mm-dd
function dateCovert2(curDate){
	var st = curDate;
    var a = st.split(" ");
    var b = a[0].split("/");
    return b[2]+"-"+b[1]+"-"+b[0];
}

Lecomm.Sport = {
		
	LoadToday:function(){
		g_dayType = "1d";
		opt = 1;
		endTime = new Date();
		startTime = new Date();
		var df = "dd/MM/yyyy";
		$("#dateStart").val(startTime.Format(df));
		$("#dateEnd").val(endTime.Format(df));
		$("#ViewSteps").click();
	},
	LoadLastWeek:function(){
		g_dayType = "7d";
		opt = 2;
		endTime = new Date();
		startTime = new Date();
		var df = "dd/MM/yyyy";
		startTime.setTime(endTime.getTime()-7*24*60*60*1000);
		$("#dateStart").val(startTime.Format(df));
		$("#dateEnd").val(endTime.Format(df));
		$("#ViewSteps").click();
    },
    LoadLastMonth:function(){
    	g_dayType = "1m";
    	opt = 3;
    	endTime = new Date();
		startTime = new Date();
		var df = "dd/MM/yyyy";
		startTime.setTime(endTime.getTime()-30*24*60*60*1000);
    	$("#dateStart").val(startTime.Format(df));
    	$("#dateEnd").val(endTime.Format(df));
		$("#ViewSteps").click();
	},
	LoadLastYear:function(){
		g_dayType = "1y";
		opt = 4;
		endTime = new Date();
		startTime = new Date();
		var df = "dd/MM/yyyy";
		startTime.setTime(endTime.getTime()-365*24*60*60*1000);
		$("#dateStart").val(startTime.Format(df));
		$("#dateEnd").val(endTime.Format(df));
		$("#ViewSteps").click();
	},
    ViewSteps:function(){
    	var isToday = $("#LoadToday").parent().hasClass("active");
    	var isLastWeek = $("#LoadLastWeek").parent().hasClass("active");
    	var isLastMonth = $("#LoadLastMonth").parent().hasClass("active");
    	var isLastYear = $("#LoadLastYear").parent().hasClass("active");
    	if(isToday || isLastWeek || isLastMonth || isLastYear){
    		var thisTime = new Date();
    		var g_url_time = thisTime.Format("yyyy-MM-dd");
    		var url = sportUrl + "STEPS/" + g_url_time + "/" + g_dayType;
    		if(opt == 1){
    			url = sportDayUrl + "STEPS/" + g_url_time;
    		}
    		Lecomm.Sport.LoadSprotData(url, "steps");
    	}else{
    		var url = sportDateRangeUrl+"STEPS/range/"+startTime+"/"+endTime;
    	    Lecomm.Sport.LoadSportDataByDateRange(url,"steps");
    	    
    	}
    },
    ViewDistance:function(){
    	var isToday = $("#LoadToday").parent().hasClass("active");
    	var isLastWeek = $("#LoadLastWeek").parent().hasClass("active");
    	var isLastMonth = $("#LoadLastMonth").parent().hasClass("active");
    	var isLastYear = $("#LoadLastYear").parent().hasClass("active");
    	if(isToday || isLastWeek || isLastMonth || isLastYear){
    		var thisTime = new Date();
    		var g_url_time = thisTime.Format("yyyy-MM-dd");
    		var url = sportUrl + "DISTANCE/" + g_url_time + "/" + g_dayType;
    		if(opt == 1){
    			url = sportDayUrl + "DISTANCE/" + g_url_time;
    		}
    		Lecomm.Sport.LoadSprotData(url, "m");
    	}else{
    		var url = sportDateRangeUrl+"DISTANCE/range/"+startTime+"/"+endTime;
    	    Lecomm.Sport.LoadSportDataByDateRange(url,"m");
    	    
    	}
    },
    ViewCalories:function(){
    	var isToday = $("#LoadToday").parent().hasClass("active");
    	var isLastWeek = $("#LoadLastWeek").parent().hasClass("active");
    	var isLastMonth = $("#LoadLastMonth").parent().hasClass("active");
    	var isLastYear = $("#LoadLastYear").parent().hasClass("active");
    	if(isToday || isLastWeek || isLastMonth || isLastYear){
    		var thisTime = new Date();
    		var g_url_time = thisTime.Format("yyyy-MM-dd");
    		var url = sportUrl + "CAL/" + g_url_time + "/" + g_dayType;
    		if(opt == 1){
    			url = sportDayUrl + "CAL/" + g_url_time;
    		}
    		Lecomm.Sport.LoadSprotData(url, "cal");
    	}else{
    		var url = sportDateRangeUrl+"CAL/range/"+startTime+"/"+endTime;
    	    Lecomm.Sport.LoadSportDataByDateRange(url,"cal");
    	    
    	}
    },
    ViewSleeps:function(){
    	var isToday = $("#LoadToday").parent().hasClass("active");
    	var isLastWeek = $("#LoadLastWeek").parent().hasClass("active");
    	var isLastMonth = $("#LoadLastMonth").parent().hasClass("active");
    	var isLastYear = $("#LoadLastYear").parent().hasClass("active");
    	if(isToday || isLastWeek || isLastMonth || isLastYear){
    		var thisTime = new Date();
    		var g_url_time = thisTime.Format("yyyy-MM-dd");
    		var url = sleepUrl + g_url_time + "/" + g_dayType;
    		Lecomm.Sport.LoadSleepData(url, "minutes");
    	}
    },
    //自定义搜索
    LoadFromTo:function(){
    // $('ul.nav-tabs line l').children('li').removeClass('active');
    	 $("#LoadToday").parent().removeClass("active");
    	 $("#LoadLastWeek").parent().removeClass("active");
    	 $("#LoadLastMonth").parent().removeClass("active");
    	 $("#LoadLastYear").parent().removeClass("active");
//    	 $("#LoadALL").parent().removeClass("active");
    	 $("#ViewSteps").parent().removeClass("active");
    	 $("#ViewDistance").parent().removeClass("active");
    	 $("#ViewCalories").parent().removeClass("active");
    	 $("#ViewSleeps").parent().removeClass("active");
      startTime=dateCovert2($("#dateStart").val());
      endTime=dateCovert2($("#dateEnd").val());
      var endTime1 = new Date(Date.parse(endTime.replace(/-/g, "/")));
      var startTime1 = new Date(Date.parse(startTime.replace(/-/g, "/")));
      var range = endTime1.getTime()-startTime1.getTime();
      if( range <= 7*24*60*60*1000){
    	  opt = 2;
      }else if(range > 7*24*60*60*1000 && range <= 30*24*60*60*1000 ){
    	  opt = 3;
      }else{
    	  opt = 4;
      }
      
      $("#ViewSteps").click();
      
    },
    //分页数据显示
    
    //加载图表数据
    LoadSprotData: function (url, unit) {
        jQuery.ajax({
            url: url,
            type: "get",
            crossDomain: true,
			dataType: 'json',
			contentType:"application/json; charset=utf-8",
			headers :{"Authorization":config.tokenHeader+token},
            beforeSend: function () {
            	$("#page").html("");
            	$("#pagesleep").html("");
                $("#chart").html("<p class='loading'><img src='"+staticPath+"/images/loading.gif'/></p>");
            },
            success: function (data, textStatus) {
            	$("#chart").html("");
            	$("#action").val(action);
                Lecomm.Sport.ParseJSONSportData(data);
                Lecomm.Sport.CreateChart(times,datas,"","",unit);
            },
            error: function (XMLHttpRequest, textStatus) {
            	 $("#chart").html("");
                 console.log(textStatus);
            }
        });
    },
    
    LoadSportDataByDateRange : function(url,unit){
        jQuery.ajax({
            url: url,
            type: "get",
            crossDomain: true,
			dataType: 'json',
			contentType:"application/json; charset=utf-8",
			headers :{"Authorization":config.tokenHeader+token},
            beforeSend: function () {
            	$("#page").html("");
            	$("#pagesleep").html("");
                $("#chart").html("<p class='loading'><img src='"+staticPath+"/images/loading.gif'/></p>");
            },
            success: function (data, textStatus) {
            	$("#chart").html("");
            	$("#action").val(action);
                Lecomm.Sport.ParseJSONSportDataByDateRange(data);
                Lecomm.Sport.CreateChart(times,datas,"","",unit);
            },
            error: function (XMLHttpRequest, textStatus) {
            	 $("#chart").html("");
                console.log(textStatus);
            }
        });
    },
    
    LoadSleepData: function (url, unit) {
        jQuery.ajax({
            url: url,
            type: "get",
            crossDomain: true,
			dataType: 'json',
			contentType:"application/json; charset=utf-8",
			headers :{"Authorization":config.tokenHeader+token},
            beforeSend: function () {
            	$("#page").html("");
            	$("#pagesleep").html("");
                $("#chart").html("<p class='loading'><img src='"+staticPath+"/images/loading.gif'/></p>");
            },
            success: function (data, textStatus) {
            	$("#chart").html("");
            	$("#action").val(action);
                Lecomm.Sport.ParseJSONSleepData(data);
                Lecomm.Sport.CreateChart(times,datas,"","",unit);
            },
            error: function (XMLHttpRequest, textStatus) {
            	 $("#chart").html("");
                console.log(textStatus);
            }
        });
    },
  
    //解析图表JSON
    ParseJSONSportData: function (data) {
    	times = [];
    	datas = [];
    	if(opt == 1){
    		for(var i=0; i<23; i++){
    			times[i] = i;
    		}
    		datas = data.hours;
    	}
    	if(opt == 2){
    		var length = data.length;
        	for(var i=0; i<length; i++){
        		var day = new Date(data[i].date.replace("-", "/"));
    			times[i] = w[day.getDay()];
        		datas[i] = data[i].value;
        	}
    	}
		if(opt == 3){
			var length = data.length;
        	for(var i=0; i<length; i++){
        		var day = new Date(data[i].date);
    			times[i] = day.Format("MM-dd");
        		datas[i] = data[i].value;
        	}
		}
		if(opt == 4){
			var map = {};
			var length = data.length;
        	for(var i=0; i<length; i++){
        		var day = new Date(data[i].date.replace("-", "/"));
        		var month = m[day.getMonth()];
        		if(map.hasOwnProperty(month)){
        			map[month] = map[month] + data[i].value;
        		}else{
        			map[month] = data[i].value;
        		}
        	}
        	var j = 0;
        	for(var key in map){
        		times[j] = key;
        		datas[j] = map[key];
        		j++;
        	}
		}
    },
    ParseJSONSportDataByDateRange: function (data) {
    	times = [];
    	datas = [];
    	var len = data.length;
    	if(len > 0){
    		for(var i=0;i<len;i++){
    			times[i] = data[i].date;
    			datas[i] = data[i].value;
    			
    			if(opt == 2){
    	    		var length = data.length;
    	        	for(var i=0; i<length; i++){
    	        		var day = new Date(data[i].date.replace("-", "/"));
    	    			times[i] = w[day.getDay()];
    	        		datas[i] = data[i].value;
    	        	}
    	    	}
    			if(opt == 3){
    				var length = data.length;
    	        	for(var i=0; i<length; i++){
    	        		var day = new Date(data[i].date);
    	        		console.log("time in week:"+day);
    	    			times[i] = day.Format("MM-dd");
    	        		datas[i] = data[i].value;
    	        	}
    			}
    			if(opt == 4){
    				var map = {};
    				var length = data.length;
    				var year = startTime.split("-")[0];
    	        	for(var i=0; i<length; i++){
    	        		var currentYear = data[i].date.split("-")[0];
    	        		var day = new Date(data[i].date);
    	        		var month = m[day.getMonth()];
    	        		if(map.hasOwnProperty(month) && currentYear == year){
    	        			map[month] = map[month] + data[i].value;
    	        		}else if(map.hasOwnProperty(month) && currentYear != year){
    	        			continue;
    	        		}else{
    	        			map[month] = data[i].value;
    	        		}
    	        	}
    	        	var j = 0;
    	        	for(var key in map){
    	        		times[j] = key;
    	        		datas[j] = map[key];
    	        		j++;
    	        	}
    			}
    			
    		}
    	}
    },
    ParseJSONSleepData: function (data) {
    	times = [];
    	datas = [];
    	
    	if(opt == 1){
    		if(data.length == 1){
    			var day = new Date(data[0].endTime.replace("-", "/"));
    			times[0] = day.Format("MM-dd");
    			datas[0] = data[0].awakeDuration+data[0].lightDuration+data[0].deepDuration;
    		}
    	}
    	if(opt == 2){
    		var length = data.length;
        	for(var i=0; i<length; i++){
        		var day = new Date(data[i].endTime.replace("-", "/"));
    			times[i] = w[day.getDay()];
        		datas[i] = data[i].awakeDuration+data[i].lightDuration+data[i].deepDuration;
        	}
    	}
		if(opt == 3){
			var length = data.length;
        	for(var i=0; i<length; i++){
        		var day = new Date(data[i].endTime);
    			times[i] = day.Format("MM-dd");
        		datas[i] = data[i].awakeDuration+data[i].lightDuration+data[i].deepDuration;
        	}
		}
		if(opt == 4){
			var map = {};
			var length = data.length;
        	for(var i=0; i<length; i++){
        		var day = new Date(data[i].endTime.replace("-", "/"));
        		var month = m[day.getMonth()];
        		if(map.hasOwnProperty(month)){
        			map[month] = map[month] + data[i].awakeDuration+data[i].lightDuration+data[i].deepDuration;
        		}else{
        			map[month] = data[i].awakeDuration+data[i].lightDuration+data[i].deepDuration;
        		}
        	}
        	var j = 0;
        	for(var key in map){
        		times[j] = key;
        		datas[j] = map[key];
        		j++;
        	}
		}
    },
    
    //获取倾斜度
    GetRotation:function(){
    	var action=parseInt( $("#action").val());
    	if(!action)action=0;
    	return -45;
    },
    //创建图表
    CreateChart: function (categories,data,title,text,unit) {
		var keyt = "";
		if(opt == 1){
			keyt = "h";
		}
      $('#chart').highcharts(
       {
		chart : {
			type : 'column',
			margin : [ 30, 50, 50, 60 ]//上右下左
		},
		title : {
			text :title
		},
		xAxis : {
			categories :categories,
			labels : {
				rotation :  Lecomm.Sport.GetRotation(),
				align : 'right',
				style : {
					fontSize : '12px',
					fontFamily : 'Microsoft Accor black'
				}
			}
		},
		yAxis : {
			min : 0,
			lineWidth: 1,
		    gridLineWidth:0,
		    tickLength: 5,
		    tickWidth: 1,
			title : {
				text : unit
			}
		},
		legend : {
			enabled : false
		},
		tooltip : {
			headerFormat: '<span style="font-size:12px">{point.key} '+keyt+'</span><table>',
            pointFormat: '<tr><td style="padding:0"><b>{point.y} '+unit+'</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
		},
		series : [ {
			name : text,
			data : data,
			dataLabels : {
				enabled : false,
				rotation : -10,
				color : '#FFFFFF',
				align : 'left',
				x : 4,
				y : -90,
				style : {
					fontSize : '11px',
					fontFamily : 'Microsoft Accor black',
					textShadow : '0 0 3px black'
				}
			}
		} ],
		credits : [ false ]
	});
    }
};