/** 初始化 */
$(function() {
	searchPlaceholder("请输入用户名称", "请输入用户名称");
	formValidator();
});

/** 表单验证 */
function formValidator() {
	$("#basic").bootstrapValidator({
		group : '.form-group',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		live : 'enabled', // 内容有变化就进行第一次验证
		fields : {
			userName : {
				validators : {
					notEmpty : {
						message : '用户名不能为空'
					},
					stringLength : {// 检测长度
						min : 2,
						max : 20,
						message : '用户名长度必须在2-20之间'
					}
				}
			},
			parentName : {
				verbose : false,
				validators : {
					notEmpty : {
						message : '部门不能为空'
					},
					callback : {
						message : '一级部门不是有效部门',
						callback : function(value, validator) {
							if (value == "一级部门") {
								return false;
							} else {
								return true;
							}
						}
					}
				}
			},
			mobile : {
				verbose : false,
				validators : {
					regexp : {
						regexp : /^1\d{10}$/,
						message : '手机号格式有误'
					}
				}
			},
			email : {
				verbose : false,
				validators : {
					emailAddress : {
						message : '邮箱地址格式有误'
					}
				}
			},
			passWord : {
				validators : {
					notEmpty : {
						message : '密码不能为空'
					},
					regexp : {
						regexp : /^(\w){6,20}$/,
						message : '密码由6-20个字母、数字、下划线组成'
					}
				}
			}
		}
	});
}

function commitBtn() {// 非submit按钮点击后进行验证，如果是submit则无需此句直接验证
	$('#basic').data('bootstrapValidator').validate();
	if ($("#basic").data('bootstrapValidator').isValid()) {// 获取验证结果，如果成功，执行下面代码
		saveOrUpdate();
	} else {
		return;
	}
}

var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "deptId",
			pIdKey : "parentId",
			rootPId : -1
		},
		key : {
			url : "nourl",
			name : "deptName"
		}
	}
};

var ztree;

var user = {
	status : 1,
	deptId : null,
	deptName : null,
	roleIdList : []
};

/** 新增用户 */
var add = function() {
	user = {};
	getRoleList();
	getDept();
	$("input[name='userName']").val('');
	$("input[name='passWord']").val('');
	$("input[name='email']").val('');
	$("input[name='mobile']").val('');
	$("input[name='parentName']").val('');
	$("input[name='status']:eq(1)").attr("checked", "checked");
	//$("input[name='roleId']").attr("checked", true);
	$("#panelinfo").show();
	$("#password").show();
	$("#update-title").hide();
	$("#add-title").show();
	$(".arrows-page-btn-box").show();
	$('#basic').data('bootstrapValidator').resetForm();
}

/** 修改用户信息 */
var update = function() {
	var rows = $("#usertable").bootstrapTable('getSelections');
	if (rows.length < 1) {
		Ewin.alert("请选择一条记录");
		return;
	}
	if (rows.length > 1) {
		Ewin.alert("只能选择一条记录");
		return;
	}
	$('#basic').data('bootstrapValidator').resetForm();
	// 获取用户信息
	getUser(rows[0].userId);
	// 获取角色信息
	getRoleList();
	// 获取部门信息
	getDept();
	$("input[name='userName']").val(user.username);
	$("input[name='email']").val(user.email);
	$("input[name='mobile']").val(user.tel);
	$.each(user.roleIdList, function(i, o) {
		$("input[name='roleId'][value=" + o + "]").attr("checked", "checked");
	})
	// 状态单选框赋值
	if (user.status == 0) {
		$("input[name='status']:eq(0)").attr("checked", "checked");
	} else if (user.status == 1) {
		$("input[name='status']:eq(1)").attr("checked", "checked");
	}
	if (user.sex == 0) {
		$("input[name='sex']:eq(0)").attr("checked", "checked");
	} else if (user.sex == 1) {
		$("input[name='sex']:eq(1)").attr("checked", "checked");
	}
	$(".arrows-page-btn-box").show();
	$("#panelinfo").show();
	$("#password").hide();
	$("#update-title").show();
	$("#add-title").hide();
}

/** 删除用户 */
var del = function() {
	var rows = $("#usertable").bootstrapTable('getSelections');
	if (rows.length < 1) {
		Ewin.alert("至少选择一条记录");
		return;
	}
	var userIds = [];
	$.each(rows, function(i, o) {
		userIds.push(o.userId);
	});

	Ewin.confirm({
		message : "确定要删除选中的用户？"
	}).on(function(t) {
		if (t) {
			$.ajax({
				type : "POST",
				url : baseURL + "user/delete",
				contentType : "application/json",
				data : JSON.stringify(userIds),
				async: false,
				success : function(r) {
					if (r.code == 0) {
						$.bsAlert(r.msg).ok();
						$("#usertable").bootstrapTable('refresh');
					} else {
						$.bsAlert(r.msg).error();
					}
					$("#panelinfo").hide();
					$(".arrows-page-btn-box").hide();
				}
			});
		}
	})
}

/** 导出用户信息 */
var exportExcel = function() {
	window.top.location.href = baseURL + "user/exportExcel";
}

/** 保存与更新用户信息 */
var saveOrUpdate = function() {
	var _temRoleIdList = [];
	$("input[name='roleId']:checked").each(function(i, o) {
		_temRoleIdList.push(o.value);
	});
	user.username = $("input[name='userName']").val();
	user.password = $("input[name='passWord']").val();
	user.email = $("input[name='email']").val();
	user.tel = $("input[name='mobile']").val();
	user.status = $("input[name='status']:checked").val();
	user.sex = $("input[name='sex']:checked").val();
	user.roleIdList = _temRoleIdList;
	var url = null == user.userId || "" == user.userId ? "user/save" : "user/update";
	$.ajax({
		type : "POST",
		url : baseURL + url,
		contentType : "application/json",
		async: false,
		data : JSON.stringify(user),
		success : function(r) {
			if (r.code === 0) {
				$.bsAlert(r.msg).ok();
				$("#usertable").bootstrapTable('refresh');
				$("#panelinfo").hide();
				$(".arrows-page-btn-box").hide();
			} else {
				$.bsAlert(r.msg).error();
			}
		}
	});
}

/** 通过用户id获取用户信息 */
var getUser = function(userId) {
	$.ajax({
		type : "POST",
		async: false,
		url : baseURL + "user/infobyid",
		data : {
			"userId" : userId
		},
		success : function(r) {
			user = r.user;
			getDept();
		}
	});
}

/** 加载部门树 */
function getDept() {
	// 加载部门树
	$.ajax({
		type : "POST",
		url : baseURL + "dept/select",
		async: false,
		success : function(r) {
			ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
			var node = ztree.getNodeByParam("deptId", user.deptId);
			if (node != null) {
				ztree.selectNode(node);
				$("input[name='parentName']").val(node.deptName);
			}
		}
	});
}

/** 获取到所有的角色 */
function getRoleList() {
	$("#label-roles").html("");
	$.ajax({
		type : "POST",
		url : baseURL + "role/select",
		async: false,
		success : function(r) {
			var roles = r.list;
			var str = "";
			$.each(
				roles,
				function(index, role) {
					str += "<label class='checkbox-inline' style='padding-left:0px;padding-right:20px;'>"+ role.roleName;
					str += "<input type='checkbox' name='roleId' value="+ role.roleId + " style='margin-left:5px;'/>";
					str += "</label>";
			});
			$("#label-roles").html($(str));
		}
	});
}

/** 打开树 */
function deptTree() {
	$("#deptLayer").modal('show')
}

function saveDept() {
	var node = ztree.getSelectedNodes();
	user.deptId = node[0].deptId;
	user.deptName = node[0].deptName;
	$("input[name='parentName']").val(node[0].deptName).trigger('input');
	$("#deptLayer").modal('hide')
}

/** 翻译状态 */
var formatterStatus = function(value, row, index) {
	return row.status == 0 ? '<span class="label label-danger">禁用</span>'
			: '<span class="label label-success">正常</span>';
}

function forId(value, row, index){
	 return '<span data-toggle="tooltip"  title="'+ row.userId +'">'+row.userId+'</span>';
}

function forCrtime(value, row, index){
	return '<span data-toggle="tooltip"  title="'+ row.createTime +'">'+row.createTime+'</span>';
}

function forEmail(value, row, index){
	if(null != row.email){
		return '<span data-toggle="tooltip"  title="'+ row.email +'">'+row.email+'</span>';
	}
}

function forMp(value, row, index){
	if(null != row.tel){
		return '<span data-toggle="tooltip"  title="'+ row.tel +'">'+row.tel+'</span>';
	}
}