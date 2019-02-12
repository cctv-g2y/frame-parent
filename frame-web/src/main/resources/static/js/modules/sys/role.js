/** 初始化 */
$(function() {
	searchPlaceholder("请输入角色名称", "请输入角色名称");
	formValidator();
});

var type = 0;
var formJson = {};

/** 修改 */
function update(){
	var rows = $("#roleTable").bootstrapTable('getSelections');
	if (rows.length < 1) {
		Ewin.alert("请选择一条记录");
		return;
	}
	if (rows.length > 1) {
		Ewin.alert("只能选择一条记录");
		return;
	}
	$("#add-icon").hide();
	$("#update-icon").show();
    $(".arrows-page-btn-box").show();
    var id = rows[0].roleId;
	type = 1;
	$("#freedetail").show();
	resetForm("#myForm");
	$("#myModalLabel").text("修改角色");
    initMenuTree(id);
}

/** 删除 */
function del(){
	var rows = $("#roleTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        Ewin.alert("至少选择一条记录");
        return;
    }
    var roleIds = [];
    $.each(rows, function (i, o) {
    	roleIds.push(o.roleId);
    });
	Ewin.confirm({message: '确定要删除选中的记录？'}).on(function (e) {
        if (e) {
        	$.ajax({
                type: "post",
                url: baseURL + "role/delete",
                contentType: "application/json",
                data: JSON.stringify(roleIds),
                success: function (r) {
                    if (r.code === 0) {
                        $.bsAlert(r.msg).ok();
                        $("#roleTable").bootstrapTable('refresh');
                    } else {
                        $.bsAlert(r.msg).error();
                    }
                    $(".arrows-page-btn-box").hide();
                    guan();
                }
            });
        }
	});
}

/** 新增 */
function add(){
	type = 0;
	$("#freedetail").show();
	$(".arrows-page-btn-box").show();
	$("#add-icon").show();
	$("#update-icon").hide();
	$("#myModalLabel").text("新增角色");
	initMenuTree();
	resetForm("#myForm");
}

/** 重置 */
function reset() {
	initMenuTree();
	resetForm("#myForm");
}

/** 新增/修改提交 */
function saveOrUpdate(){
	if(type==0){
		url=baseURL + "role/save";
	}else{
		url=baseURL + "role/update";
		formJson['roleId'] = $("#roleId").val();
	}
	var nodes = menu_ztree.getCheckedNodes(true);
    var menuIdList = new Array();
    for (var i = 0; i < nodes.length; i++) {
        menuIdList.push(nodes[i].menuId);
    }
    formJson['menuIdList'] = menuIdList.join(",");
    var deptIdList = new Array();
    formJson['roleName'] = $("#roleName").val();
    formJson['mark'] = $("#mark").val();
    var validState = $('#myForm').data('bootstrapValidator').validate();
    if (true == validState.isValid()) {
    	console.log(formJson);
        $.ajax({
        	url : url,
        	data : formJson,
        	success : function(r){
        		if(r.code == 0){
        			$.bsAlert(r.msg).ok();
        			$(".arrows-page-btn-box").hide();
        			formJson = {};
        			$("#roleTable").bootstrapTable('refresh');
        		    guan();
        		}else{
        			$.bsAlert(r.msg).error();
        		}
        	}
        });
    }
}

/** 获取角色权限 */
	function getRole(roleId) {
	$.ajax({
		url : baseURL + "role/infobyid",
		data : {
			"roleId" : roleId
		},
		success : function(r) {
			var role = r.role;
			$("#roleName").val(role.roleName);
			$("#deptId").val(role.deptId);
			$("#deptName").val(role.deptName);
			$("#roleId").val(role.roleId);
			$("#mark").val(role.mark);
			// 勾选角色所拥有的菜单
			var menuIds = role.menuIdList;
			for (var i = 0; i < menuIds.length; i++) {
				var node = menu_ztree.getNodeByParam("menuId", menuIds[i]);
				if (null != node) {
					menu_ztree.checkNode(node, true, false);
				}
			}
		}
	});
}

/** 菜单树 */
var menu_ztree;
var menu_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl",
            name : "menuName"
        }
    },
    check: {
        enable: true,
        nocheckInherit: true
    }
};

/** 加载菜单树 */
function initMenuTree (roleId) {
    $.get(baseURL + "menu/list", function (r) {
        menu_ztree = $.fn.zTree.init($("#menuTree"), menu_setting, r);
        if (roleId != null) {
            getRole(roleId);
        }
    });
}

/** 表单校验 */
function formValidator() {
    $("#myForm").bootstrapValidator({
        group: '.form-group',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        live: 'enabled', // 内容有变化就进行第一次验证
        fields: {
            roleName: {
                verbose: false,
                validators: {
                    notEmpty: {
                        message: '请输入角色名称'
                    },
                    stringLength: {
                        max: 20,
                        message: '角色名称太长'
                    }
                }
            },
            mark: {
            	verbose: false,
            	validators: {
            		stringLength: {
                		max : 40,
                		message : '备注太长'
                	}
            	}
            }
        }
    });
}

/** 关闭操作窗口 */
function guan(){
	$("#freedetail").hide();
	resetForm("#myForm");
}

/** 打开树 */
function deptTree() {
    $("#deptLayer").modal('show');
}

/** 刷新 */
function f5() {
    window.location.href = baseURL + "role/showlist";
}