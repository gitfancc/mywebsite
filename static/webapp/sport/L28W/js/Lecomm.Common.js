﻿  Date.prototype.Format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1,                 //月份   
                "d+": this.getDate(),                    //日   
                "h+": this.getHours(),                   //小时   
                "m+": this.getMinutes(),                 //分   
                "s+": this.getSeconds(),                 //秒   
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
                "S": this.getMilliseconds()             //毫秒   
            };
            if (/(y+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp("(" + k + ")").test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }
            return fmt;
  };
  
  Number.prototype.toFixedEx = function(fractionDigits) {
		var f = parseInt(fractionDigits) || 0;
		if( f < -20 || f > 100 ) { 
			throw new RangeError("Precision of " + f + " fractional digits is out of range");
		}
		var x = Number(this);
					
		if( isNaN(x) ) {
			return "NaN";
		}
		var s = "";
		if(x < 0) {
			s = "-";
			x = -x;
		}
		if( x >= Math.pow(10, 21) ) {
			return s + x.toString();
		}
		var m;

		n = Math.floor(x*1000 * Math.pow(10, f)/1000 );
		
		if( n == 0 ) {
			m = "0";
		}
		else {
			m =  n.toString();
		}
		if( f == 0 ) {
			return s + m;
		}
		var k = m.length;
		if(k <= f) {
			var z = Math.pow(10, f+1-k).toString().substring(1);
			m = z + m;
			k = f+1;
		}
		if(f > 0) {
			var a = m.substring(0, k-f);
			var b = m.substring(k-f);
			m = a + "." + b;
		}
		return s + m;
	};
	
  if (!Lecomm) var Lecomm = {};
  Lecomm.FY = function () {
      var sb = "";
      var _this = this;
      var defaultConfig = {
          oPagePanel: "",
          currentPage: 1,
          pageSize: 10,
          recordCount: 0,
          pageCount: 0,
          displayPages: 10,
          firstText: "First",
          prevText: "Prev",
          nextText: "Next",
          lastText: "Last",
          isShowRecordCount: true,
          isShowPageOfCount: false,
          isShowFirstAndLast: true,
          isShowPrevAndNext: true,
          isShowPageNumber: true,
          cssClass: 'pager',
          start: 1,
          end: 0,
          language: 'cn',
          isAlwaysShowFirstAndLast: true,
          isAlwaysShowPrevAndNext: true
      };
      var c = defaultConfig;
      var InitParam = function (options) {
          c = $.extend(c, options);
          c.pageCount = Math.ceil(c.recordCount / c.pageSize); //计算出PageCount
         // alert("recordCount="+c.recordCount +"  pageSize="+c.pageSize+"   pageCount="+c.pageCount);
          var mid = c.displayPages / 2;
          //下面计算开始和结束下标
          //页数不够，数据不多 情况
          if (c.pageCount <= c.displayPages) {
              c.start = 1;
              c.end = c.pageCount;
          }
          else {
              if (c.currentPage <= mid) {
                  c.end = c.displayPages;
              }
              else if (c.currentPage <= (c.pageCount - mid)) {
                  c.end = parseInt(c.currentPage) + parseInt(mid);
              }
              else {
                  c.end = c.pageCount;
              }
              c.start = parseInt(c.end) - parseInt(c.displayPages) + 1;
          }
          //默认初始化
          switch (c.language) {
              case "en":
                  c.firstText = "First";
                  c.prevText = "Prev";
                  c.nextText = "Next";
                  c.lastText = "Last";
                  break;
              case "cn":
              default:
                  c.firstText = "首页";
                  c.prevText = "上一页";
                  c.nextText = "下一页";
                  c.lastText = "最后一页";
                  break;
          }
          sb = "";

      };
      var RenderBeginTag = function () {

          if (c.isAlwaysShowFirstAndLast) {
              if (c.currentPage > 1) {
                  sb += "<a page='1' class='direct' title='" + c.firstText + "'  >" + c.firstText + "</a>";
                  sb += "<a page='" + (parseInt(c.currentPage) - 1) + "' class='direct' title='" + c.prevText + "'   >" + c.prevText + "</a>";
              }
              else {
                  sb += "<a page='1' class='direct' title='" + c.firstText + "'   >" + c.firstText + "</a>";
                  sb += "<a page='-1' class='direct' title='" + c.prevText + "'  >" + c.prevText + "</a>";
              }

          } else {

              if (c.currentPage > 1) {
                  if (c.isShowFirstAndLast) {
                      sb += "<a page='1' class='direct' title='" + c.firstText + "'  >" + c.firstText + "</a>";
                  }
                  if (c.isShowPrevAndNext) {
                      sb += "<a page='" + (parseInt(c.currentPage) - 1) + "' class='direct' title='" + c.prevText + "'   >" + c.prevText + "</a>";
                  }
              }
          }

      };
      var RenderEndTag = function () {

          if (c.isAlwaysShowPrevAndNext) {
              if (c.currentPage < c.pageCount) {
                  sb += "<a page='" + (parseInt(c.currentPage) + 1) + "' class='direct' title='" + c.nextText + "'   >" + c.nextText + "</a>";
                  sb += "<a page='" + c.pageCount + "'  class='direct' title='" + c.lastText + "'  >" + c.lastText + "</a>";
              }
              else {
                  sb += "<a page='-1' class='direct' title='" + c.nextText + "'>" + c.nextText + "</a>";
                  sb += "<a page='" + c.pageCount + "'  class='direct' title='" + c.lastText + "'  >" + c.lastText + "</a>";
              }
          } else {
              if (c.currentPage < c.pageCount) {
                  if (c.isShowPrevAndNext) {
                      sb += "<a page='" + (parseInt(c.currentPage) + 1) + "' class='direct' title='" + c.nextText + "'   >" + c.nextText + "</a>";
                  }
                  if (c.isShowFirstAndLast) {
                      sb += "<a page='" + c.pageCount + "'  class='direct' title='" + c.lastText + "'  >" + c.lastText + "</a>";
                  }
              }
          }

      };
      var RenderPagingContents = function () {

          // 输出 1，2，3，4，5，6，7，4
          for (var i = c.start; i <= c.end; i++) {
              if (c.end == 1) {
                  return;
              }
              if (i == c.currentPage) {
                  if (c.language == "cn") {
                      sb += "<a page='" + i + "' class='current' title='第" + i + "页'>" + i + "</a>";
                  }
                  else {
                      sb += "<a page='" + i + "' class='current' title='Page Of " + i + "'>" + i + "</a>";
                  }
              }
              else {
                  if (c.language == "cn") {
                      sb += "<a page='" + i + "'  title='第" + i + "页' >" + i + "</a>";
                  }
                  else {
                      sb += "<a page='" + i + "' title='Page Of " + i + "' >" + i + "</a>";
                  }
              }
          }
      };
      this.GetPagerText = function (options, callback) {
          InitParam(options); //初始化start,end,pageCount
          if (c.pageCount == 0 || c.pageCount == 1 || c.currentPage > c.pageCount) {
              return "";
          }
          sb += "<div class='" + c.cssClass + "' ownPagePanel='" + c.oPagePanel + "'>";
          if (c.isShowRecordCount) {
              if (c.language == "cn") {
                  sb += "<span class='count'>共 " + c.recordCount + " 条记录</span>";
              }
              else {
                  sb += "<span class='count'>All Results " + c.recordCount + "</span>";
              }
          }
          if (c.isShowPageOfCount) {
              if (c.language == "cn") {
                  sb += "<span class='count'>当前" + c.currentPage + "/" + c.pageCount + "页</span>";
              }
              else {
                  sb += "<span class='count'>Page " + c.currentPage + " of " + c.pageCount + "</span>";
              }
          }
          RenderBeginTag();
          if (c.isShowPageNumber) {
              RenderPagingContents();
          }
          RenderEndTag();
          sb += "</div>";
          $("#" + c.oPagePanel).html(sb);

          //添加事件
          $("#" + c.oPagePanel).find("a[page]").each(function () {
              var btn = $(this);
              btn.click(function () {
                  c.currentPage = btn.attr("page") || 1;
                  if (typeof callback == "function") {
                      callback.call(null, c.currentPage);
                      _this.GetPagerText(c, callback);
                  }
              });
          });
      };
  }