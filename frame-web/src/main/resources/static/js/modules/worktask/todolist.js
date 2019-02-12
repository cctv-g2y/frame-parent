/** 初始化 */
$(function() {
    $('#toclaTable').bootstrapTable({
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
        url: "tocladata",
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
//        onDblClickRow: function(row, $el){
//            alert("双击");
//        },
//        onClickRow: function(row, $el){
//            alert("单击");
//        },
        columns: [{
            checkbox: true
        },{
            title: '序号',//表的列名
            field: '',//json数据中rows数组中的属性名
            align: 'center',//水平居中
            width: 50,
            formatter : function (val, row, index) {
                var pageSize = $('#toclaTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#toclaTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        },{
            field: 'processInstanceId',
            title: '实例ID',
            visible: false,
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'processDefinitionId',
            title: '流程定义Id',
            visible: false,
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'taskId',
            title: '任务ID',
            visible: false,
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'taskName',
            title: '任务名称',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'version',
            title: '流程版本',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="v:'+ value +'">v:' + value + '</span>';
                }else{
                    return '-';
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
            field: 'userNames',
            title: '待签收人',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'processDefinitionName',
            title: '流程名称',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'description',
            title: '流程描述',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'processInstanceId',
            title: '跟踪流程',
            formatter:function (value, row, index) {
                return '<span data-toggle="tooltip" title="跟踪流程"><a style="text-decoration:none;" target="_blank" href="' + baseURL + 'diagram-viewer/index.html?processDefinitionId=' + row.processDefinitionId + '&processInstanceId=' + value + '">跟踪流程</a></span>';
            }
        }]
    });
    $("#toclaTables .search").find("input").attr("placeholder","请输入任务名称").attr("title","请输入任务名称");
});

/** 加载待办列表 */
function todoWork(){
    $('#todoTable').bootstrapTable({
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
        url: "tododata",
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
//        onDblClickRow: function(row, $el){
//            alert("双击");
//        },
//        onClickRow: function(row, $el){
//            alert("单击");
//        },
        columns: [{
            checkbox: true
        },{
            title: '序号',//表的列名
            field: '',//json数据中rows数组中的属性名
            align: 'center',//水平居中
            width: 50,
            formatter : function (val, row, index) {
                var pageSize = $('#todoTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#todoTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        },{
            field: 'processInstanceId',
            title: '实例ID',
            visible: false,
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'processDefinitionId',
            title: '流程定义Id',
            visible: false,
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'taskId',
            title: '任务ID',
            visible: false,
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'taskName',
            title: '任务名称',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'version',
            title: '流程版本',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="v:'+ value +'">v:' + value + '</span>';
                }else{
                    return '-';
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
            field: 'processDefinitionName',
            title: '流程名称',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'description',
            title: '流程描述',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'processInstanceId',
            title: '跟踪流程',
            formatter:function (value, row, index) {
                return '<span data-toggle="tooltip" title="跟踪流程"><a style="text-decoration:none;" target="_blank" href="' + baseURL + 'diagram-viewer/index.html?processDefinitionId=' + row.processDefinitionId + '&processInstanceId=' + value + '">跟踪流程</a></span>';
            }
        }]
    });
    $("#todoTables .search").find("input").attr("placeholder","请输入任务名称").attr("title","请输入任务名称");
}

/** 签收任务 */
function toclaim(){
    var rows = $("#toclaTable").bootstrapTable('getSelections');
    if (1 > rows.length) {
        $.bsAlert("至少选择一条记录").error();
        return;
    }
    if (1 < rows.length) {
        $.bsAlert("只能选择一条记录").error();
        return;
    }
    var taskId = rows[0].taskId;
    $.ajax({
        type: "post",
        url: baseURL + "work/task/claim",
        data:{
            'taskId':taskId
        },
        success: function (r) {
            if (r.code === 0) {
                $.bsAlert(r.msg).ok();
                $("#toclaTable").bootstrapTable('refresh');
                $('#todoTable').bootstrapTable('refresh');
            } else {
                $.bsAlert(r.msg).error();
            }
        }
    });
}

/** 处理任务 */
function todo(){
    var rows = $("#todoTable").bootstrapTable('getSelections');
    if (1 > rows.length) {
        $.bsAlert("至少选择一条记录").error();
        return;
    }
    if (1 < rows.length) {
        $.bsAlert("只能选择一条记录").error();
        return;
    }
    var taskId = rows[0].taskId;
    var processInstanceId = rows[0].processInstanceId;
    $.ajax({
        type: "post",
        url: baseURL + "work/task/complete",
        data:{
            'taskId' : taskId,
            'processInstanceId' : processInstanceId,
            'pass' : '1'
        },
        success: function (r) {
            if (r.code === 0) {
                $.bsAlert(r.msg).ok();
                $("#todoTable").bootstrapTable('refresh');
            } else {
                $.bsAlert(r.msg).error();
            }
        }
    });
}

