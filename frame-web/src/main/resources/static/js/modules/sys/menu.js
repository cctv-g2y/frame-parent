var ztree;
var Menu = {
	id : "menuTable",
	table : null,
	layerIndex : -1
};
var ty = "0";
var menu = {
	parentName : null,
	parentId : 0,
	type : 0,
	orderNum : 0
}
var menuObj = {
	menuName : null,
	orderNum : null,
	url1 : null,
	perms : null,
	icon : null,
	parentId : null,
	type : null,
	menuId : null
}
var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "menuId",
			pIdKey : "parentId",
			rootPId : -1
		},
		key : {
			name : "menuName",
			url : "nourl"
		}
	}
};

/**
 * 删除菜单按钮
 */
function del() {
	var menuId = getMenuId();
	if (menuId == null || menuId == "") {
		return;
	}
	Ewin.confirm({
		message : '确定要删除选中的记录？'
	}).on(function(e) {
		if (!e) {
			return;
		}
		$.ajax({
			type : "POST",
			url : baseURL + "menu/delete",
			data : "menuId=" + menuId,
			success : function(r) {
				if (r.code === 0) {
					$.bsAlert(r.msg).ok();
					setTimeout("f5()", 500);
				} else {
					$.bsAlert(r.msg).error();
				}
			}
		});
	});
}

/**
 * 保存或修改确定按钮
 */
function saveOrUpdate() {
	menuObj.menuName = $("#menu-name input").val();
	menuObj.orderNum = $("#seq-num input").val();
	menuObj.url = $("#menu-url input").val();
	menuObj.perms = $("#authori-sign input").val();
	menuObj.icon = $("#menu-icon input").val();
	menuObj.parentId = menu.parentId;
	menuObj.type = $(".radio-inline :checked").val();
	$('#myForm').data('bootstrapValidator').validate();
	var validState = $("#myForm").data('bootstrapValidator').isValid();
	if (!validState) {
		$("#myForm").data("bootstrapValidator").validate();
		return;
	}
	var url = "";
	if (ty == "0") {
		url = baseURL + "menu/save";
	} else {
		url = baseURL + 'menu/update';
		menuObj.menuId = getMenuId();
	}
	$.ajax({
		type : "post",
		url : url,
		contentType : "application/json",
		data : JSON.stringify(menuObj),
		success : function(r) {
			if (r.code === 0) {
				$.bsAlert(r.msg).ok();
				setTimeout("f5()", 500);
			} else {
				$.bsAlert(r.msg).error();
			}
		}
	});
}

/**
 * 修改菜单按钮
 */
function update() {
	$("#mytitle").text("修改");
	$("#icon").attr("class", "fa fa-pencil-square-o");
	$(".arrows-page-btn-box").show();
	ty = '1';
	Menu.id = getMenuId();
	if (Menu.id == null || Menu.id == "") {
		return false;
	}
	// 显示模态框
	$("#freedetail").show();
	$("#demo form div.nn").hide();
	$(".radio-inline").attr("disabled", true);
	$.ajax({
		url : baseURL + "menu/infobyid",
		data : {
			'menuId' : Menu.id
		},
		success : function(r) {
			$("#demo").show();
			var node = ztree.getNodeByParam("menuId", r.parentId);
			ztree.selectNode(node);
			var parentName = node.menuName;
			menu.parentId = r.parentId;
			$("#menu-name").children().eq(1).children().val(r.menuName);
			$("#sup-menu").children().eq(1).children().val(parentName);
			$("#seq-num").children().eq(1).children().val(r.orderNum);
			$("#menu-url").children().eq(1).children().val(r.url);
			$("#authori-sign").children().eq(1).children().val(r.perms);
			$("#menu-icon").children().eq(1).children().val(r.icon);
			if (r.type == 0) {
				$("#radio_0").prop("checked", "checked");
				$("#menu-name").show();
				$("#sup-menu").show();
				$("#seq-num").show();
				$("#menu-icon").show();
			}
			if (r.type == 1) {
				$("#radio_1").prop("checked", "checked");
				$("#demo form div.form-group").show();
			}
			if (r.type == 2) {
				$("#radio_2").prop("checked", "checked");
				$("#menu-name").show();
				$("#sup-menu").show();
				$("#authori-sign").show();
			}
		}
	});
}

/**
 * 初始化表格的列 {title: '菜单ID', field: 'menuId', visible: false, align: 'center',
 * valign: 'middle', width: '80px'},
 */
Menu.initColumn = function() {
	var columns = [
			{
				field : 'selectItem',
				radio : true
			},
			{
				title : '菜单名称',
				field : 'menuName',
				valign : 'middle',
				sortable : true,
				width : '200px'
			},
			{
				title : '图标',
				field : 'icon',
				valign : 'middle',
				sortable : true,
				width : '120px',
				formatter : function(item, index) {
					return item.icon == null ? '' : '<i class="' + item.icon
							+ ' fa-lg"></i>';
				}
			}, {
				title : '类型',
				field : 'type',
				valign : 'middle',
				sortable : true,
				width : '180px',
				formatter : function(item, index) {
					if (item.type === 0) {
						return '<span class="label label-primary">目录</span>';
					}
					if (item.type === 1) {
						return '<span class="label label-success">菜单</span>';
					}
					if (item.type === 2) {
						return '<span class="label label-warning">按钮</span>';
					}
				}
			}, {
				title : '排序号',
				field : 'orderNum',
				valign : 'middle',
				sortable : true,
				width : '150px'
			}, {
				title : '菜单URL',
				field : 'url',
				valign : 'middle',
				sortable : true,
				width : '200px'
			}, {
				title : '授权标识',
				field : 'perms',
				valign : 'middle',
				sortable : true
			} ]
	return columns;
};

/**
 * 获取选中行的id
 */
function getMenuId() {
	var selected = $('#menuTable').bootstrapTreeTable('getSelections');
	if (selected.length == 0) {
		Ewin.alert("请选择一条记录");
		return false;
	} else {
		return selected[0].id;
	}
}

$(function() {
	getMenu();// 初始化zTree
	var colunms = Menu.initColumn();
	var table = new TreeTable(Menu.id, baseURL + "menu/list", colunms);
	table.setExpandColumn(1);
	table.setIdField("menuId");
	table.setCodeField("menuId");
	table.setParentCodeField("parentId");
	table.setExpandAll(false);
	table.init();
	Menu.table = table;
	selectRadios();
	formValidator();
});

/**
 * 加载菜单树
 */
function getMenu() {
	$.ajax({
		url : baseURL + "menu/select",
		success : function(res) {
			ztree = $.fn.zTree.init($("#menuTree"), setting, res);
		}
	});
}

/**
 * 新增
 */
function add() {
	$("#freedetail").show();
	$(".arrows-page-btn-box").show();
	$("#mytitle").text("新增");
	$("#icon").attr("class", "fa fa-plus");
	$("input[name ='type']").eq(0).prop("checked", "checked");
	$("#menu-name input").val("");
	$("#seq-num input").val("");
	$("#menu-url input").val("");
	$("#authori-sign input").val("");
	$("#menu-icon input").val("");
	$("#sup-menu input").val("");
	$(".radio-inline").attr("disabled", true);
	$("#demo form div.nn").hide();
	$("input[name='menuName']").val("");
	$("#radio_0").attr("checked", true);
	ty = '0';
	$("#demo").show();
	menu = {
		parentName : null,
		parentId : 0,
		type : 1,
		orderNum : 0
	};
	$("#menu-name").show();
	$("#sup-menu").show();
	$("#seq-num").show();
	$("#menu-icon").show();
}

/**
 * 目录，菜单，按钮分类
 */
function selectRadios() {
	$("#smartPlatformApp :radio").click(function() {
		$("#myForm").data('bootstrapValidator').resetForm();
		$("#demo form div.nn").hide();
		var type = $(this).val();
		if (type == 0) {
			$("#menu-name").show();
			$("#sup-menu").show();
			$("#seq-num").show();
			$("#menu-icon").show();
		}
		if (type == 1) {
			$("#demo form div.form-group").show();
		}
		if (type == 2) {
			$("#menu-name").show();
			$("#sup-menu").show();
			$("#authori-sign").show();
		}
	});
}

/**
 * 打开树
 */
function menuTree() {
	$("#menuLayer").modal('show')
}

/**
 * 保存部门树
 */
function saveDept() {
	var node = ztree.getSelectedNodes();
	menu.parentId = node[0].menuId;
	menu.parentName = node[0].menuName;
	$("#sup-menu input").val(node[0].menuName).trigger("input");
	$("#menuLayer").modal('hide')
}

function f5() {
	window.location.href = baseURL + "menu/sysmenu";
}

function formValidator() {
	$("#myForm").bootstrapValidator({
		group : '.form-group',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		live : 'enabled', // 内容有变化就进行第一次验证
		fields : {
			menuName : {
				verbose : false,
				validators : {
					notEmpty : {
						message : '请输入菜单名称'
					},
					stringLength : {
						max : 20,
						message : '菜单名称太长'
					}
				}
			},
			menuURL : {
				verbose : false,
				validators : {
					notEmpty : {
						message : "请输入菜单URL"
					}
				}
			}
		}
	});
}