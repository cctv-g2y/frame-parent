var dict = {};
var ztree;
var setting = {
    async: {
        enable: true
    },
    check: {
        enable: false,
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "dicId",
            pIdKey: "parentId",
            rootPId: 0
        },
        key: {
            name: "name",
            url: "nourl"
        }
    },
    callback: {
        onClick: function (event, treeId, treeNode) {
            var dicId = treeNode.dicId;
            initcode(dicId);
            if (dicId != 0) {
                $("#div-update").show();
                $("#div-update input[name='dictname']").val(treeNode.name).trigger('input');
                $("#div-update input[name='dictcode']").val(treeNode.code);
                $("#div-update input[name='dictvalue']").val(treeNode.value);
                $("#div-update input[name='dictparams']").val(treeNode.params);
                $("#upd-dict-remark").val(treeNode.remark);
                dict.parentId = treeNode.dicId;
                dict.dicId = treeNode.dicId;
            } else {
                dict.parentId = null;
                $("#div-update").hide();
            }
            ztree.expandNode(treeNode);
        }
    }
};

/**
 * 关闭全部节点
 */
function openNodes(nodes) {
    if (nodes.children.length > 0) {
        for (var i = 0; i < nodes.length; i++) { // 设置节点展开
            ztree.expandNode(nodes[i], false, false, true);
        }
    } else {
        return false;
    }

}

/** 初始化 */
$(function () {
    initDictTree();
    initcode();
    formValidatorSave();
    formValidatorUpdate();
});

/** 初始化字典树 */
function initDictTree() {
    $.get(baseURL + "dict/dictlist", function (r) {
        var dicArray = [];
        var root = {
        	dicId: 0,
            name: "全部",
            parentId: null
        };
        dicArray.push(root);
        $.each(r, function (i, o) {
            if (!o.parentId) {
                o.parentId = 0;
            }
            dicArray.push(o);
        })
        ztree = $.fn.zTree.init($("#dicTree"), setting, dicArray);
        var nodesAll = ztree.getNodes();
        for (var i = 0; i < nodesAll.length; i++) { // 设置节点展开
            ztree.expandNode(nodesAll[i], true, false, true);
        }
    })
}

/**
 * 初始化编码
 * 默认传0
 */
function initcode(Id) {
    var dicId = 0;
    if (!isEmpty(Id)) {
    	dicId = Id;
    }
    $.ajax({
        url: baseURL + 'dict/initcode',
        data : {
        	dicId : dicId
        },
        success: function (r) {
            $("#div-add input[name='dictname']").val('').trigger('input');
            $("#div-add input[name='dictparams']").val('');
            $("#add-dict-remark").val('');
            $("#div-add input[name='dictcode']").val(r.code);
            $("#div-add input[name='dictvalue']").val(r.code);
        }
    })
}

/**
 * 修改并保存
 * @param flag
 * 0--新增
 * 1--修改
 */
function savedict(obj) {
    var flag = $(obj).attr("data-type");
    if (flag == 1) {//新增
        dict.dicId = null;
        dict.name = $("#div-add input[name='dictname']").val();
        dict.code = $("#div-add input[name='dictcode']").val();
        dict.value = $("#div-add input[name='dictvalue']").val();
        dict.params = $("#div-add input[name='dictparams']").val();
        dict.remark = $("#add-dict-remark").val();
      
        $('#Save').data('bootstrapValidator').validate();
    	var validState = $("#Save").data('bootstrapValidator').isValid();
    	if (validState != true) {
    		return;
    	}
    } else {//修改
        dict.name = $("#div-update input[name='dictname']").val();
        dict.code = $("#div-update input[name='dictcode']").val();
        dict.value = $("#div-update input[name='dictvalue']").val();
        dict.params = $("#div-update input[name='dictparams']").val();
        dict.remark = $("#upd-dict-remark").val();
        
        $('#Update').data('bootstrapValidator').validate();
    	var validState = $("#Update").data('bootstrapValidator').isValid();
    	if (validState != true) {
    		return;
    	}
    }
    $.ajax({
        url: baseURL + 'dict/savedict',
        data: dict,
        success: function (r) {
        	if(r.code == 0){
        		$.bsAlert(r.msg).ok();
        		setTimeout("f5()", 500);
	            var nodes = ztree.getSelectedNodes();
	            var selectNodes = ztree.getSelectedNodes();
	            if (flag == 1) {
	                ztree.addNodes(nodes[0], r.dict, true);
	                $.bsAlert(r.msg).ok();
	            } else {
	            	$.bsAlert(r.msg).ok();
	                nodes[0].name = r.dict.name;
	                nodes[0].code = r.dict.name;
	                nodes[0].value = r.dict.value;
	                nodes[0].remark = r.dict.remark;
	                ztree.updateNode(nodes[0], true)
	            }
	            initcode(selectNodes[0].dicId);
	            var parentNode = ztree.getNodeByParam("dicId", selectNodes[0].dicId);
	            ztree.cancelSelectedNode();//先取消所有的选中状态
	            ztree.selectNode(parentNode, true);//将指定ID的节点选中
	            ztree.expandNode(parentNode, true, false);//将指定ID节点展开
	            ztree.checkNode(parentNode, true, true);
	            ztree.updateNode(parentNode)
        	}else{
        		$.bsAlert(r.msg).error();
        	}
        }
    })
}

/** 是否删除 */
function isRemovedict() {
    Ewin.confirm({message: "你确定删除该条字典数据吗？"}).on(function (r) {
        if (r) {
            removedictData();
        } else {
            return false;
        }
    })

}

/**
 * 删除字典
 * @returns {boolean}
 */
function removedictData() {
    var nodes = ztree.getSelectedNodes();
    if (isEmpty(nodes[0].getParentNode())) {
        $.bsAlert("父节点不允许删除").error();
        return false;
    }
    var parentId = nodes[0].getParentNode().dicId;
    var _data = {
    	id: nodes[0].dicId
    };
    $.ajax({
        url: baseURL + "dict/deldict",
        data: _data,
        success: function (e) {
        	if (e.code == 0) {
        		$.bsAlert(e.msg).ok();
				ztree.removeNode(nodes[0], true);
				var parentNode = ztree.getNodeByParam("dicId", parentId);
				ztree.cancelSelectedNode();// 先取消所有的选中状态
				ztree.selectNode(parentNode, true);// 将指定ID的节点选中
				ztree.expandNode(parentNode, true, false);// 将指定ID节点展开
				ztree.checkNode(parentNode, true, true);
				ztree.updateNode(parentNode)
				initcode(parentId)
				if (parentId != 0) {
					$("#div-update").show();
					$("#div-update input[name='dictname']")
							.val(parentNode.name);
					$("#div-update input[name='dictcode']")
							.val(parentNode.code);
					$("#div-update input[name='dictvalue']").val(
							parentNode.value);
					$("#div-update input[name='dictparams']").val(
							parentNode.params);
					$("#upd-dict-remark").val(parentNode.remark);
					dict.parentId = parentNode.dicId;
					dict.dicId = parentNode.dicId;
				} else {
					$("#div-update").hide();
				}
			}else{
				$.bsAlert(e.msg).error();
			}
        }
    })
}

/** 刷新 */
function f5() {
	window.location.href = baseURL + "dict/showindex";
}

/** 添加表单验证 */
function formValidatorSave() {
	$("#Save").bootstrapValidator({
		group : '.form-control',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		live : 'enabled', // 内容有变化就进行第一次验证
		fields : {
			dictname : {
				validators : {
					notEmpty : {
						message : '请输入字典名称'
					},
					stringLength : {
						max : 8,
						message : '名称太长'
					}
				}
			}
		}
	});
}

/** 修改表单验证 */
function formValidatorUpdate() {
	$("#Update").bootstrapValidator({
		group : '.form-control',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		live : 'enabled', // 内容有变化就进行第一次验证
		fields : {
			dictname : {
				validators : {
					notEmpty : {
						message : '请输入字典名称'
					},
					stringLength : {
						max : 8,
						message : '名称太长'
					}
				}
			}
		}
	});
}