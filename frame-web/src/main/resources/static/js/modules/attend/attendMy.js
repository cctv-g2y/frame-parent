/** 初始化 */
$(function() {
	searchtitle();
	getTime();
//	formValidator();
});

/** 初始化一个attendInfo对象 */
var attendInfo = {};

/** 搜索提示 */
function searchtitle(){
	searchPlaceholder("请输入查询日期", "按年查询例如(2018-)/按月查询例如(-02-)/按日查询例如(2018-02-01)");
}

/** 查看个人签到详情 */
function show(){
	var rows = $("#attendMy").bootstrapTable('getSelections');
	if (rows.length < 1) {
		$.bsAlert("请选择一条记录").error();
		return;
	}
	if (rows.length > 1) {
		$.bsAlert("只能选择一条记录").error();
		return;
	}
	$("#bid").val("");
	$("#mymark").val("");
	$("#lateMarkb").val("");
	$("#earlyMarkb").val("");
	$("#ont").val("");
	$("#offt").val("");
	$("#latetimess").val("");
	$("#earlytimess").val("");
	$(".arrows-page-btn-box").show();
	$("#panelinfo").show();
	var workDate = rows[0].shouldDate;
	$("#shouldDate").val(workDate);
	$.ajax({
		url : "showmyattend",
		async: false,
		data : {
			"workDate" : workDate
		},
		success : function(r){
			if(isEmpty(r)){
				$("#sayt").html("缺勤");
				$("#nol").show();
				$("#nolate").hide();
				$("#latesay").hide();
				$("#noe").show();
				$("#noearly").hide();
				$("#earlysay").hide();
				$("#ontb").hide();
				$("#offtb").hide();
				$("#latetimes").hide();
				$("#earlytimes").hide();
			}else{
				$("#bid").val(r.attendId);
				$("#mymark").val(r.mark);
				$("#lateMarkb").val(r.lateMark);
				$("#earlyMarkb").val(r.earlyMark);
				$("#ont").val(r.onTime);
				$("#offt").val(r.offTime);
				$("#sayt").html("出勤");
				$("#latetimess").val("迟到了"+r.lateTime+"分钟");
				$("#earlytimess").val("早退了"+r.earlyTime+"分钟");
				if(r.onStatus == "1"){
					$("#latetimes").hide();
					$("#ontb").show();
					$("#nolate").show();
					$("#latesay").hide();
					$("#nol").hide();
				}else	if(r.onStatus == "0"){
					$("#latetimes").show();
					$("#ontb").show();
					$("#nolate").hide();
					$("#latesay").show();
					$("#nol").hide();
				}else{
					$("#latetimes").hide();
					$("#ontb").hide();
					$("#nol").show();
					$("#nolate").hide();
					$("#latesay").hide();
				}
				if(r.offStatus == "1"){
					$("#earlytimes").hide();
					$("#offtb").show();
					$("#noearly").show();
					$("#earlysay").hide();
					$("#noe").hide();
				}else	if(r.offStatus == "0"){
					$("#earlytimes").show();
					$("#offtb").show();
					$("#noearly").hide();
					$("#earlysay").show();
					$("#noe").hide();
				}else{
					$("#earlytimes").hide();
					$("#offtb").hide();
					$("#noe").show();
					$("#noearly").hide();
					$("#earlysay").hide();
				}
			}
		}
	});
}

/** 格式化列 */
function forOff(value, row, index){
	if(row.offStatus == "0"){
		return '<span class="label label-danger">早退</span>';
	}
	if(row.offStatus == "1"){
		return '<span class="label label-success">正常</span>';
	}else{
		return '<span class="label label-danger">未考勤</span>';
	}
}

function forOn(value, row, index){
	if(row.onStatus == "0"){
		return '<span class="label label-danger">迟到</span>';
	}
	if(row.onStatus == "1"){
		return '<span class="label label-success">正常</span>';
	}else{
		return '<span class="label label-danger">未考勤</span>';
	}
}

function forWorkDate(val, row, index) { 
	if(row.workDate != null && row.workDate != ""){
		return '<span class="label label-success">出勤</span>';
	}else{
		return '<span class="label label-danger">缺勤</span>';
	}
}

/** 判断是否需要考勤 */
function isattend(){
	$.ajax({
		url : "isattend",
		success : function(r){
			if(r.code == 0){
				$("#sleep").hide();
				$("#work").show();
				$.bsAlert(r.msg).ok();
			}else{
				$("#work").hide();
				$("#sleep").show();
				$.bsAlert(r.msg).error();
			}
		}
	});
}

/** 去考勤 */
function goattend(){
	$("#isattend").hide();
	$("#actattend").show();
	$.ajax({
		url : baseURL + "attendSys/goattend",
		success : function(r){
			if(isEmpty(r)){
				$("#actm").show();
			}else{
				$("#myattendmark").val(r.mark);
				$("#lateMark").val(r.lateMark);
				$("#earlyMark").val(r.earlyMark);
				$("#attendId").val(r.attendId);
				$("#lateTime").val("迟到了"+r.lateTime+"分钟");
				$("#earlyTime").val("早退了"+r.earlyTime+"分钟");
				if(r.onStatus == null || r.onStatus == ""){
					$("#actm").show();
				}
				if((r.onStatus != null || r.onStatus != "") && (r.offStatus == null || r.offStatus == "")){
					if(r.onStatus == "1"){
						$("#normalm").show();
					}else if(r.onStatus == "0"){
						$("#latem").show();
						$("#lates").show();
					}else{
						$("#actm").show();
						return false;
					}
					$("#acta").show();
					$("#actaa").show();
				}
				if((r.onStatus != null || r.onStatus != "") && (r.offStatus != null || r.offStatus != "")){
					if(r.onStatus == "1"){
						$("#normalm").show();
					}else if(r.onStatus == "0"){
						$("#latem").show();
						$("#lates").show();
					}else{
						$("#actm").show();
						$("#actaa").hide();
						return false;
					}
					$("#actaa").show();
					if(r.offStatus == "1"){
						$("#normala").show();
					}else if(r.offStatus == "0"){
						$("#eara").show();
						$("#ears").show();
					}else{
						$("#acta").show();
					}
				}
			}
		}
	});
}

/** 签到 */
function goup(){
	$.ajax({
		url : "goup",
		success : function(r){
			if(r.code == 0){
				$("#actm").hide();
				goattend();
				$.bsAlert(r.msg).ok();
			}else{
				$.bsAlert(r.msg).error();
			}
		}
	});
}

/** 签退 */
function godown(){
	$.ajax({
		url : "godown",
		success : function(r){
			if(r.code == 0){
				$("#acta").hide();
				goattend();
				$.bsAlert(r.msg).ok();
			}else{
				$.bsAlert(r.msg).error();
			}
		}
	});
}

/** 当天考勤意见提交 */
function gomark(){
	var attendId = $("#attendId").val();
	var lateMark = $("#lateMark").val();
	var earlyMark = $("#earlyMark").val();
	var mark = $("#myattendmark").val();
	$.ajax({
		url : "gomark",
		data : {
			"attendId" : attendId,
			"lateMark" : lateMark,
			"earlyMark" : earlyMark,
			"mark" : mark
		},
		success : function(r){
			if(r.code == 0){
				goattend();
				$.bsAlert(r.msg).ok();
			}else{
				$.bsAlert(r.msg).error();
			}
		}
	});
}

/** 当天考勤意见重置 */
function resetmark(){
	$("#lateMark").val("");
	$("#earlyMark").val("");
	$("#myattendmark").val("");
}

/** 考勤详情意见提交 */
function submitmark(){
	attendInfo = {};
	attendInfo.attendId = $("#bid").val();
	attendInfo.workDate = $("#shouldDate").val();
	attendInfo.mark = $("#mymark").val();
	attendInfo.lateMark = $("#lateMarkb").val();
	attendInfo.earlyMark = $("#earlyMarkb").val();
	var url = attendInfo.attendId == "" ? "savemark" : "updatemark";
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		async: false,
		data : JSON.stringify(attendInfo),
		success : function(r){
			if(r.code == 0){
				$(".arrows-page-btn-box").hide();
				$("#panelinfo").hide();
				$.bsAlert(r.msg).ok();
			}else{
				$.bsAlert(r.msg).error();
			}
		}
	});
}

/** 考勤详情意见重置 */
function resetmarks(){
	$("#mymark").val("");
	$("#lateMarkb").val("");
	$("#earlyMarkb").val("");
}

/** 实时时间显示 */
function getTime() {
    var dateObj = new Date();
    var year = dateObj.getFullYear();//年
    var month = dateObj.getMonth()+1;//月  (注意：月份+1)
    var date = dateObj.getDate();//日
    var day = dateObj.getDay();
    var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
    var week = weeks[day];//根据day值，获取星期数组中的星期数。
    var hours = dateObj.getHours();//小时
    var minutes = dateObj.getMinutes();//分钟
    var seconds = dateObj.getSeconds();//秒
    if(month<10){
        month = "0"+month;
    }
    if(date<10){
        date = "0"+date;
    }
    if(hours<10){
        hours = "0"+hours;
    }
    if(minutes<10){
        minutes = "0"+minutes;
    }
    if(seconds<10){
        seconds = "0"+seconds;
    }
    var newDate = year+"年"+month+"月"+date+"日"+hours+":"+minutes+":"+seconds+"&nbsp &nbsp"+week;
    $("#nowdatetime").html("当前系统时间："+newDate);
    setTimeout('getTime()', 1000);
}