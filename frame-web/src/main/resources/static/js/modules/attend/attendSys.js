/** 初始化 */
$(function() {
	searchtitle();
	formValidator();
	datetimes();
});

/** 初始化attendDate */
var atte = {
		isWorkingday : "0",
		calendarDate : null,
		mark : null
};

/** 初始化attendTime */
var atime = {
		onTime : null,
		offTime : null,
		mark : null
};

/** 搜索提示 */
function searchtitle(){
	searchPlaceholder("请输入查询日期", "按年查询例如(2018-)/按月查询例如(-02-)/按日查询例如(2018-02-01)");
}

/** 格式化列表 */
function forIswork(value, row, index){
	if(row.isWorkingday == "0"){
		return '<span class="label label-success">休</span>';
	}else{
		return '<span class="label label-danger">班</span>';
	}
}

/** 格式化列表 */
function forMark(val, row, index) { 
	if(row.mark != null && row.mark != ""){
		return '<span data-toggle="tooltip"  title="'+ row.mark +'">'+row.mark+'</span>';
	}else{
		return "-";
	}
}

/** 打开新增界面 */
function add(){
	atte = {};
	$(".arrows-page-btn-box").show();
	$("#panelinfo").show();
	$("#add-title").show();
	$("#update-title").hide();
	resetForm("#day");
}

/** 打开修改界面 */
function update(){
	var rows = $("#attendDatetab").bootstrapTable('getSelections');
	if (rows.length < 1) {
		$.bsAlert("请选择一条记录").error();
		return;
	}
	if (rows.length > 1) {
		$.bsAlert("只能选择一条记录").error();
		return;
	}
	resetForm("#day");
	$(".arrows-page-btn-box").show();
	$("#panelinfo").show();
	$("#update-title").show();
	$("#add-title").hide();
	// 获取时间信息
	getAttendDate(rows[0].attDateid);
	$("#calendarDate").val(atte.calendarDate);
	$("#mark").val(atte.mark);
	// 状态单选框赋值
	if (atte.isWorkingday == "0") {
		$("input[name='status'][value=0]").prop("checked", true);
	} else if (atte.isWorkingday == "1") {
		$("input[name='status'][value=1]").prop("checked", true);
	}
	
}

/** 通过id获取日期详情 */
function getAttendDate(attDateid){
	$.ajax({
		url : baseURL + "attendSys/dateInfo",
		async: false,
		data : {attDateid},
		success : function(r) {
			atte = r.attenddate;
		}
	});
}

/** 保存或者修改日期数据 */
function saveorupdate(){
	$('#day').data('bootstrapValidator').validate();
	var isV = $("#day").data('bootstrapValidator').isValid();
	if (!isV) {
		return;
	}
	atte.calendarDate = $("#calendarDate").val();
	atte.isWorkingday = $("input[name='status']:checked").val();
	atte.mark = $("#mark").val();
	var url = atte.attDateid == null ? "save" : "update";
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		async: false,
		data : JSON.stringify(atte),
		success : function(r) {
			if (r.code === 0) {
				$.bsAlert(r.msg).ok();
				$("#panelinfo").hide();
				$("#attendDatetab").bootstrapTable('refresh');
				$(".arrows-page-btn-box").hide();
			} else {
				$.bsAlert(r.msg).error();
			}
		}
	});
}

/** 批量删除日期 */
function del(){
	var rows = $("#attendDatetab").bootstrapTable('getSelections');
	if (rows.length < 1) {
		$.bsAlert("至少选择一条记录").error();
		return;
	}
	var attDateids = [];
	$.each(rows, function(i, o) {
		attDateids.push(o.attDateid);
	});
	//弹出确认层一
	Ewin.confirm({
		message : "确定要删除选中的日期？"
	}).on(function(t){
		if(t){
			$.ajax({
				type : "POST",
				url : "delete",
				contentType : "application/json",
				data : JSON.stringify(attDateids),
				async: false,
				success : function(r) {
					if (r.code == 0) {
						$.bsAlert(r.msg).ok();
						$("#attendDatetab").bootstrapTable('refresh');
					} else {
						$.bsAlert(r.msg).error();
					}
					$("#panelinfo").hide();
					$(".arrows-page-btn-box").hide();
				}
			});
		}
	});
}

/** 获取时间详情 */
function getattendTime(){
	resetForm("#day");
	$.ajax({
		url : "timeInfo",
		success : function(r) {
			atime = r;
			if(isEmpty(atime)){
				resetForm("#time");
			}else{
				$("#onTime").val(atime.onTime);
				$('#offTime').val(atime.offTime);
				$("#attTimeid").val(atime.attTimeid);
				$("#timeMark").val(atime.mark);
			}
		}
	});
}

/** 修改或者保存时间 */
function saveandupdate(){
	$('#time').data('bootstrapValidator').validate();
	var isV = $("#time").data('bootstrapValidator').isValid();
	if (!isV) {
		return;
	}
	atime = {};
	atime.attTimeid = $("#attTimeid").val();
	atime.onTime = $("#onTime").val();
	atime.offTime = $("#offTime").val();
	atime.mark = $("#timeMark").val();
	var url = atime.attTimeid == "" ? "saveTime" : "updateTime";
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		async: false,
		data : JSON.stringify(atime),
		success : function(r) {
			if(r.code == 0){
				$.bsAlert(r.msg).ok();
				$('#time').data('bootstrapValidator').resetForm();
				getattendTime();
			}else{
				$.bsAlert(r.msg).error();
			}
		}
	});
}

/** 清除签到时间设置 */
function flushall(){
	//弹出确认层一
	Ewin.confirm({
		message : "确定要清空时间的设置？该操作不可恢复！"
	}).on(function(t){
		if(t){
			$.ajax({
				url : "flushall",
				success : function(r){
					if(r.code == 0){
						$.bsAlert(r.msg).ok();
						$('#time').data('bootstrapValidator').resetForm();
						getattendTime();
					}else{
						$.bsAlert(r.msg).error();
					}
				}
			});
		}
	});
}

/** 表单验证 */
function formValidator() {
	$("#day").bootstrapValidator({
		group : '.form-group',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		live : 'enabled', // 内容有变化就进行第一次验证
		fields : {
			calendarDate : {
				verbose : false,
				validators : {
					notEmpty : {
						message : '日期不能为空'
					}
				}
			},
			status: {
                verbose: false,
                validators: {
                    notEmpty: {
                        message: '请选择工作状态'
                    }
                }
            }
		}
	});
	
	$("#time").bootstrapValidator({
		group : '.form-group',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		live : 'enabled', // 内容有变化就进行第一次验证
		fields : {
			onTime : {
				verbose : false,
				validators : {
					notEmpty : {
						message : '上班时间不能为空'
					},
                    callback: {
                        message: '上班时间不能大于或等于下班时间',
                        callback: function () {
                        	var onTime = $("#onTime").val();
                        	var offTime = $("#offTime").val();
                            return onTime < offTime;
                        }
                    }
				}
			},
			offTime : {
				verbose : false,
				validators : {
					notEmpty : {
						message : '下班时间不能为空'
					},
                    callback: {
                        message: '下班时间不能小于或等于上班时间',
                        callback: function () {
                        	var onTime = $("#onTime").val();
                        	var offTime = $("#offTime").val();
                            return onTime < offTime;
                        }
                    }
				}
			},
			timeMark : {
				verbose : false,
				validators : {
					notEmpty : {
						message : '备注不能为空'
					}
				}
			}
		}
	});
}

/** 点击显示 JEUI 时间控件 */
function datetimes(){
	jeDate("#calendarDate",{
	    isinitVal:true, 
	    format:"YYYY-MM-DD",
	    donefun: function(obj){
	    	$("#calendarDate").trigger('input');
	    }
	});
	jeDate("#onTime",{
	    isinitVal:true, 
	    format:"hh:mm:ss",
	    donefun: function(obj){
	    	$("#onTime").trigger('input');
	    	$("#offTime").trigger('input');
	    }
	});
	jeDate("#offTime",{
	    isinitVal:true, 
	    format:"hh:mm:ss",
	    donefun: function(obj){
	    	$("#onTime").trigger('input');
	    	$("#offTime").trigger('input');
	    }
	});
}

/** 重置时间表单 */
function resetTime (){
	$('#onTime').val("");
	$('#offTime').val("");
	$('#timeMark').val("");
	$('#time').data('bootstrapValidator').destroy();
	$('#time').data('bootstrapValidator', null);
	formValidator();
}
