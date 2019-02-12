//表单设计js
/**
 * 删除
 * @returns
 */
function del() {
	var rows = $("#formDesignTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        Ewin.alert("至少选择一条记录");
        return;
    }
    var formids = [];
    $.each(rows, function (i, o) {
    	formids.push(o.id);
    });

    Ewin.confirm({message: '确认要删除该表单吗？'}).on(function (r) {
        if (r) {
            $.ajax({
                type: "POST",
                url: baseURL + "workflowform/delete",
                contentType: "application/json",
                data: JSON.stringify(formids),
                success: function (r) {
                    if (r.code == 0) {
                        $.bsAlert('删除成功').success();
                        f5();
                    } else {
                    	$.bsAlert('删除失败').error()
                    }
                }
            });
        } else {
            return false;
        }
    })
}

/**
 * 修改
 * @returns
 */
function update() {
	var rows = $("#formDesignTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        Ewin.alert("请选择一条记录");
        return;
    }
    if (rows.length > 1) {
        Ewin.alert("只能选择一条记录");
        return;
    }
    var id = rows[0].id;
    window.location.href = baseURL + "workflowform/formnew?id=" + id;
}

/**
 * 新增
 * @returns
 */
function add(){
	window.location.href = baseURL + "workflowform/formnew";
}

/**
 * 预览
 * @returns
 */
function show(){
	var rows = $("#formDesignTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        Ewin.alert("请选择一条记录");
        return;
    }
    if (rows.length > 1) {
        Ewin.alert("只能选择一条记录");
        return;
    }
    var id = rows[0].id;
    window.location.href = baseURL + "workflowform/showform/?id=" + id;
}

/**
 * 刷新表
 * @returns
 */
function f5(){
	$("#formDesignTable").bootstrapTable('refresh');
}