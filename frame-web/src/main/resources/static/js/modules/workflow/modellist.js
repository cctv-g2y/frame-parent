var modelcategory;
/** 初始化 */
$(function() {
    formValidator();
    getModelCategory();
    getModelUsers();
    getModelRoles();
    $('#modelTable').bootstrapTable({
        //请求方法
        method: 'get',
        //类型json
        dataType: "json",
        search: true,
        //显示刷新按钮
        showRefresh: true,
        //显示切换手机试图按钮
        showToggle: false,
        //显示 内容列下拉框
        showColumns: true,
        //显示导出按钮
        showExport: false,
        //显示切换分页按钮
        showPaginationSwitch: false,
        //是否显示行间隔色
        striped: true,
        //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        cache: false,
        //是否显示分页（*）
        pagination: true,
        //排序方式
        sortOrder: "asc",
        //初始化加载第一页，默认第一页
        pageNumber:1,
        //每页的记录行数（*）
        pageSize: 5,
        //可供选择的每页的行数（*）
        pageList: [5, 10, 25, 50, 100],
        //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
        url: "listdata",
        clickToSelect:true,
        //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
        //queryParamsType:'',
        //查询参数,每次调用是会带上这个参数，可自定义
        queryParams : function(params) {
            return {//这里的params是table提供的
                offset: params.offset,//从数据库第几条记录开始
                limit: params.limit,//找多少条
                search:params.search//搜索
            };
        },
        //分页方式：client客户端分页，server服务端分页（*）
        sidePagination: "server",
        onDblClickRow: function(row, $el){
            
        },
        columns: [{
            checkbox: true
        },{
            title: '序号',//表的列名
            field: '',//json数据中rows数组中的属性名
            align: 'center',//水平居中
            width: 50,
            formatter : function (val, row, index) {
                var pageSize = $('#modelTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#modelTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        },{
            field: 'id',
            title: '模型Id',
            formatter:function(value, row , index){
                return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
            }
        },{
            field: 'key',
            title: '模型标识',
            formatter:function(value, row , index){
                return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
            }
        },{
            field: 'name',
            title: '模型名称',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'category',
            title: '模型分类',
            formatter:function (value, row, index) {
                for (var i = 0; i < modelcategory.length; i++) {
                    if(value == modelcategory[i].code){
                        return '<span data-toggle="tooltip" title="'+ modelcategory[i].name +'">' + modelcategory[i].name + '</span>';
                    }
                }
            }
        },{
            field: 'createTime',
            title: '创建时间',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'lastUpdateTime',
            title: '最后更新时间',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'version',
            title: '模型版本',
            formatter:function (value, row, index) {
                return '<span data-toggle="tooltip" title="V:'+ value +'">V:' + value + '</span>';
            }
        },{
            field: 'isConfig',
            title: '发起权限',
            formatter:function (value, row, index) {
                if('1' == value){
                    return '<span class="label label-danger">未配置</span>';
                }else if('0' == value){
                    return '<span class="label label-success">已配置</span>';
                }
            }
        }]
    });
    $(".bootstrap-table .search").find("input").attr("placeholder","请输入模型名称").attr("title","请输入模型名称");
});

/** 查询流程分类列表 */
function getModelCategory(){
    $.ajax({
        url : baseURL + 'actmodel/getmodelcategory',
        success: function (r) {
            if(null != r){
                modelcategory = r;
                $("#category").html(addoption(r));
            }
        }
    });
}

/** 获取用户列表 */
function getModelUsers(){
    $.ajax({
        url : baseURL + 'actmodel/getuserlist',
        success: function (r) {
            if(null != r){
                $("#userIds").html(addoptionuser(r));
                $('#userIds').selectpicker('refresh');
                $('#userIds').selectpicker('render');
            }
        }
    });
}

/** 获取角色列表 */
function getModelRoles(){
    $.ajax({
        url : baseURL + 'actmodel/getrolelist',
        success: function (r) {
            if(null != r){
                $("#roleIds").html(addoptionrole(r));
                $('#roleIds').selectpicker('refresh');
                $('#roleIds').selectpicker('render');
            }
        }
    });
}

/** option渲染分类 */
function addoption(array){
    var opti="";
    for (var i = 0; i < array.length; i++) {
        opti += "<option value='"+array[i].code+"'>"+array[i].name+"</option>";
    }
    return opti;
}

/** option渲染用户 */
function addoptionuser(array){
    var opti="";
    for (var i = 0; i < array.length; i++) {
        opti += "<option value='"+array[i].userId+"'>"+array[i].username+"</option>";
    }
    return opti;
}

/** option渲染角色 */
function addoptionrole(array){
    var opti="";
    for (var i = 0; i < array.length; i++) {
        opti += "<option value='"+array[i].roleId+"'>"+array[i].roleName+"</option>";
    }
    return opti;
}

/** 转换日期格式(时间戳转换为datetime格式) */
function changeDateFormat(dateVal) {
    dateVal = dateVal + "";
    if (dateVal != null) {
        var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
        var resTime = date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
        return '<span data-toggle="tooltip"  title="'+ resTime +'">'+ resTime +'</span>';
    }
}

/** 导出 */
function exports(){
    var rows = $("#modelTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $.bsAlert("请选择一条记录").error();
        return;
    }
    if (rows.length > 1) {
        $.bsAlert("只能选择一条记录").error();
        return;
    }
    var id = rows[0].id;
    window.location.href = baseURL + "actmodel/exports?id=" + id;
}

/** 部署 */
function deploys(){
    var rows = $("#modelTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $.bsAlert("请选择一条记录").error();
        return;
    }
    if (rows.length > 1) {
        $.bsAlert("只能选择一条记录").error();
        return;
    }
    var id = rows[0].id;
    if('1' == rows[0].isConfig){
        // 1 表示该model未配置发起权限，则所有人都可发起
        Ewin.confirm({message: '该流程尚未配置发起权限,将会对所有人开放,确定要部署吗？'}).on(function (e) {
            if (e) {
                $.ajax({
                    url : baseURL + 'actmodel/deploys',
                    data : {
                        'id' : id
                    },
                    success: function (r) {
                        if (r.code === 0) {
                            $.bsAlert('部署成功,如果需要更改权限,请配置完成之后重新部署生效').ok();
                            $("#modelTable").bootstrapTable('refresh');
                        } else {
                            $.bsAlert(r.msg).error();
                        }
                        $(".arrows-page-btn-box").hide();
                        guan();
                    }
                });
            }
        });
    }else{
        $.ajax({
            url : baseURL + 'actmodel/deploys',
            data : {
                'id' : id
            },
            success: function (r) {
                if (r.code === 0) {
                    $.bsAlert(r.msg).ok();
                    $("#modelTable").bootstrapTable('refresh');
                } else {
                    $.bsAlert(r.msg).error();
                }
                $(".arrows-page-btn-box").hide();
                guan();
            }
        });
    }
}

/** 编辑 */
function update(){
    var rows = $("#modelTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $.bsAlert("请选择一条记录").error();
        return;
    }
    if (rows.length > 1) {
        $.bsAlert("只能选择一条记录").error();
        return;
    }
    $("#add-icon").hide();
    $("#update-icon").hide();
    $(".arrows-page-btn-box").hide();
    var id = rows[0].id;
    $("#freedetail").hide();
    resetForm("#myForm");
    openEditor(id);
}

/** 删除 */
function del(){
    var rows = $("#modelTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $.bsAlert("至少选择一条记录").error();
        return;
    }
    var modelIds = [];
    $.each(rows, function (i, o) {
        modelIds.push(o.id);
    });
    Ewin.confirm({message: '确定要删除选中的记录？'}).on(function (e) {
        if (e) {
            $.ajax({
                type: "post",
                url: baseURL + "actmodel/delete",
                contentType: "application/json",
                data: JSON.stringify(modelIds),
                success: function (r) {
                    if (r.code === 0) {
                        $.bsAlert(r.msg).ok();
                        $("#modelTable").bootstrapTable('refresh');
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
    $("#myModalLabel").text("新增模型");
    resetForm("#myForm");
}

/** 重置 */
function reset() {
    initMenuTree();
    resetForm("#myForm");
}

/** 新增提交 */
function saveOrUpdate(){
    url=baseURL + "actmodel/saveorupdate";
    var validState = $('#myForm').data('bootstrapValidator').validate();
    if (true == validState.isValid()) {
        $.ajax({
            url : url,
            data : {
                category : $("#category").val(),
                name : $("#name").val(),
                key : $("#key").val(),
                description : $("#description").val()
            },
            success : function(r){
                if(r.code == 0){
                    openEditor(r.modelId);
                }else{
                    $.bsAlert(r.msg).error();
                }
            }
        });
    }
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
            name: {
                verbose: false,
                validators: {
                    notEmpty: {
                        message: '请输入模块名称'
                    },
                    stringLength: {
                        max: 8,
                        message: '模块名称请不要超过8个字'
                    }
                }
            },
            key: {
                verbose: false,
                validators: {
                    notEmpty: {
                        message: '请输入模块标识'
                    },
                    stringLength: {
                        max: 8,
                        message: '模块标识请不要超过8个字'
                    }
                }
            },
            description: {
                verbose: false,
                validators: {
                    notEmpty: {
                        message: '请输入模块描述'
                    },
                    stringLength: {
                        max: 50,
                        message: '模块描述请不要超过50个字'
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

/** 打开设计器 */
function openEditor(id) {
    window.location.href = baseURL + "modeler.html?modelId=" + id;
}

/** 打开模态框事件 */
function configsender(){
    var rows = $("#modelTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $.bsAlert("请选择一条记录").error();
        return;
    }
    if (rows.length > 1) {
        $.bsAlert("只能选择一条记录").error();
        return;
    }
    $('#modalbutton').attr("data-toggle","modal");
    $('#modalbutton').attr("data-target","#myModal");
    $("#modalName").val(rows[0].name);
    $("#modalId").val(rows[0].id);
}

/** 打开模态框加载模型已设置详情 */
$('#myModal').on('shown.bs.modal', function () {
    $('#modalbutton').removeAttr("data-toggle");
    $('#modalbutton').removeAttr("data-target");
    var rows = $("#modelTable").bootstrapTable('getSelections');
    var modelId = rows[0].id;
    $.ajax({
        url : 'showconfig',
        data : {
            'modelId' : modelId
        },
        success : function(r){
            $("#cid").val(r.id);
            var users = r.userList;
            var roles = r.roleList;
            if(null != users){
                $('#userIds').selectpicker('val', users);
            }
            if(null != roles){
                $('#roleIds').selectpicker('val', roles);
            }
        }
    });
})

/** 模型控制设置保存 */
function saveConfig(){
    var userIds = '';
    var roleIds = '';
    var userValue = $('#userIds').val();
    var roleValue = $('#roleIds').val();
    if(null != userValue){
        for (var i = 0; i < userValue.length; i++) {
            userIds += userValue[i] + ',';
        }
    }
    if(null != roleValue){
        for (var i = 0; i < roleValue.length; i++) {
            roleIds += roleValue[i] + ',';
        }
    }
    $.ajax({
        url : 'saveconfig',
        data : {
            'id' : $("#cid").val(),
            'modelId' : $("#modalId").val(),
            'roleIds' : roleIds,
            'userIds' : userIds
        },
        success : function(r){
            if(r.code == 0){
                $.bsAlert(r.msg).ok();
                setTimeout('f5()',700);
            }else{
                $.bsAlert(r.msg).error();
            }
        }
    });
}

/** 刷新 */
function f5(){
    window.location.href = baseURL + "actmodel/modellistpage";
}


