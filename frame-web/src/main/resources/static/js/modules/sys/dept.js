var ztree;
var zTreeObj;

/**
 * 初始化
 */
$(function() {
	formValidator();
	getDept();
});

// 弹出框树配置
var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "deptId",
			pIdKey : "parentId",
			rootPId : 0
		},
		key : {
			url : "nourl",
			name : "deptName"
		}
	}
};

// 左边树配置
var settingleft = {
	callback : {
		onClick : zTreeOnClick
	},
	dataType : "json",
	data : {
		simpleData : {
			enable : true,
			idKey : "deptId",
			pIdKey : "parentId",
			rootPId : 0
		},
		key : {
			name : "deptName",
			url : "nourl"
		}
	}
};

/**
 * 搜索部门
 * 
 * @returns
 */
function search() {
	zTreeObj.cancelSelectedNode();// 先取消所有的选中状态
	var name = $("#searchname").val();
	if (isEmpty(name)) {
		return;
	}
	var nodes = zTreeObj.transformToArray(zTreeObj.getNodes());
	for (var i = 0; i < nodes.length; i++) {
		if (nodes[i].deptName.indexOf(name) != -1) {
			zTreeObj.selectNode(nodes[i], true);// 将指定ID的节点选中
			zTreeObj.expandNode(nodes[i], true, false);// 将指定ID节点展开
			break;
		}
	}
}

/**
 * ztree单击事件
 */
function zTreeOnClick(event, treeId, treeNode) {
	$('#basic').data('bootstrapValidator').resetForm();
	if (treeNode.deptId == "1") {
		$.bsAlert("根部门不能修改").error();
	} else {
		$.ajax({
			type : "POST",
			url : baseURL + "dept/getdept",
			data : {
				deptId : treeNode.deptId
			},
			success : function(r) {
				$("#name").val(r.dept.deptName);
				$("#id").val(r.dept.deptId);
				$("#parentName").val(r.dept.parentName);
				$("#parentId").val(r.dept.parentId);
				$("#orderNum").val(r.dept.orderNum);
			}
		});
	}
}

/**
 * 加载部门树
 * 
 * @param parentId
 */
function getDept() {
	// 加载部门树
	$.get(baseURL + "dept/select", function(r) {
		ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
		zTreeObj = $.fn.zTree.init($("#tree"), settingleft, r.deptList);
		zTreeObj.expandAll(true);
	})
}

/**
 * 打开树
 */
function deptTree() {
	$("#deptLayer").modal('show');
}

// 新建
function save() {
	$('#basic').data('bootstrapValidator').resetForm();
	$("#name").val("");
	$("#id").val("");
	$("#parentName").val("");
	$("#parentId").val("");
	$("#orderNum").val("");
}

/**
 * 选择上级部门
 */
function saveDept() {
	var node = ztree.getSelectedNodes();
	if (node.length < 1 && isEmpty(node.deptId)) {
		return false;
	}
	$("#parentName").val(node[0].deptName);
	$("#parentId").val(node[0].deptId);
	$("#deptLayer").modal('hide')
}

/**
 * 删除
 */
function del() {
	var deptId = $("#id").val();
	if (isEmpty(deptId)) {
		Ewin.alert("请选择一个部门");
		return;
	}
	Ewin.confirm({
		message : "确定要删除选中的部门？"
	}).on(function(t) {
		if (t) {
			$.ajax({
				url : baseURL + 'dept/delete',
				data : {"deptId" : deptId},
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
	})
}

/**
 * 表单验证
 */
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
			name : {
				verbose : false,
				validators : {
					notEmpty : {
						message : '请输入部门名称'
					},
					stringLength : {
						max : 8,
						message : '名称太长'
					}
				}
			},
			parentName : {
				verbose : false,
				validators : {
					notEmpty : {
						message : '请选择上级部门'
					},
					callback : {
						message : '上级部门不能为本部门',
						callback : function(value, validator) {
							if ($("#name").val() == $("#parentName").val()) {
								return false;
							}
							return true;
						}
					}
				}
			},
			orderNum : {
				verbose : false,
				validators : {
					notEmpty : {
						message : '请输入排序号'
					},
					regexp : {
						regexp : /^[0-9]\d*$/,
						message : '排序号只能是正整数'
					},
					stringLength : {
						max : 5,
						message : '排序号太长'
					}
				}
			}
		}
	});
}

/**
 * 保存或修改
 */
function saveOrUpdate() {
	$('#basic').data('bootstrapValidator').validate();
	var validState = $("#basic").data('bootstrapValidator').isValid();
	if (validState != true) {
		return;
	}
	var url = null == $("#id").val() || "" == $("#id").val() ? baseURL + "dept/save" : baseURL + "dept/update";
	var data1 = {
		deptName : $("#name").val(),
		deptId : $("#id").val(),
		parentId : $("#parentId").val(),
		orderNum : $("#orderNum").val()
	}
	$.ajax({
		url : url,
		data : data1,
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

function f5() {
	window.location.href = baseURL + "dept/sysdept";
}