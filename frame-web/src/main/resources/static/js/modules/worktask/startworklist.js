var processcategory;
/** 初始化 */
$(function() {
    getprocesscategory();
    $('#sentTable').bootstrapTable({
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
        url: "sentdata",
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
                var pageSize = $('#sentTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#sentTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        },{
            field: 'processInstanceId',
            title: '实例ID',
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
            field: 'processDefinitionKey',
            title: '流程key',
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
            field: 'processDefinitionVersion',
            title: '流程版本',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="v:'+ value +'">v:' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'startTime',
            title: '开始时间',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'endTime',
            title: '结束时间',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'status',
            title: '流程状态',
            formatter:function (value, row, index) {
                if('0' == value){
                    return '<span class="label label-success">进行中</span>';
                }else if('1' == value){
                    return '<span class="label label-danger">已结束</span>';
                }
            }
        },{
            field: 'processInstanceId',
            title: '跟踪流程',
            formatter:function (value, row, index) {
                if('0' == row.status){
                    return '<span data-toggle="tooltip" title="跟踪流程"><a style="text-decoration:none;" target="_blank" href="' + baseURL + 'diagram-viewer/index.html?processDefinitionId=' + row.processDefinitionId + '&processInstanceId=' + value + '">跟踪流程</a></span>';
                }else if('1' == row.status){
                    return '流程已结束';
                }
            }
        }]
    });
    $("#sentTables .search").find("input").attr("placeholder","请输入流程名称").attr("title","请输入流程名称");
});

/** 加载运行中流程列表 */
function startWork(){
    $('#processTable').bootstrapTable({
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
        url: "processdata",
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
                var pageSize = $('#processTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#processTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        },{
            field: 'id',
            title: '流程Id',
            formatter:function(value, row , index){
                return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
            }
        },{
            field: 'key',
            title: '流程标识',
            formatter:function(value, row , index){
                return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
            }
        },{
            field: 'name',
            title: '流程名称',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'category',
            title: '流程分类',
            formatter:function (value, row, index) {
                for (var i = 0; i < processcategory.length; i++) {
                    if(value == processcategory[i].code){
                        return '<span data-toggle="tooltip" title="'+ processcategory[i].name +'">' + processcategory[i].name + '</span>';
                    }
                }
            }
        },{
            field: 'deploymentTime',
            title: '更新时间',
            formatter:function (value, row, index) {
                return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
            }
        },{
            field: 'version',
            title: '流程版本',
            formatter:function (value, row, index) {
                return '<span data-toggle="tooltip" title="V:'+ value +'">V:' + value + '</span>';
            }
        },{
            field: 'diagramResourceName',
            title: '流程图片',
            formatter:function (value, row, index) {
                return '<span data-toggle="tooltip" title="'+ value +'"><a style="text-decoration:none;" target="_blank" href="' + baseURL + 'diagram-viewer/index.html?processDefinitionId=' + row.id + '">' + value + '</a></span>';
            }
        }]
    });
    $("#processtables .search").find("input").attr("placeholder","请输入流程名称").attr("title","请输入流程名称");
}

/** 查询流程分类列表 */
function getprocesscategory(){
    $.ajax({
        url : baseURL + 'actprocess/getprocesscategory',
        success: function (r) {
            if(null != r){
                processcategory = r;
            }
        }
    });
}

/** 发起流程 */
function startWorkFlow(){
    var rows = $("#processTable").bootstrapTable('getSelections');
    if (1 > rows.length) {
        $.bsAlert("至少选择一条记录").error();
        return;
    }
    if (1 < rows.length) {
        $.bsAlert("只能选择一条记录").error();
        return;
    }
    var processKey = rows[0].key;
    var procDefId = rows[0].id;
    var procDefName = rows[0].name;
//    window.location.href = baseURL + "work/task/startpage?processKey=" + processKey + "&procDefId=" + procDefId + "&procDefName=" + procDefName;
    $.ajax({
        type: "post",
        url: baseURL + "work/task/start",
        data:{
            'processKey' : processKey
        },
        success: function (r) {
            if (r.code === 0) {
                $.bsAlert(r.msg + "流程实例id为:" + r.procInstId).ok();
                $("#processTable").bootstrapTable('refresh');
                $('#sentTable').bootstrapTable('refresh');
            } else {
                $.bsAlert(r.msg).error();
            }
        }
    });
}

///** 删除任务 */
//function deleteTask(){
//    var rows = $("#processTable").bootstrapTable('getSelections');
//    if (1 > rows.length) {
//        $.bsAlert("至少选择一条记录").error();
//        return;
//    }
//    if (1 < rows.length) {
//        $.bsAlert("只能选择一条记录").error();
//        return;
//    }
//    var processKey = rows[0].key;
//    $.ajax({
//        type: "post",
//        url: baseURL + "work/task/start",
//        data:{
//            'processKey':processKey
//        },
//        success: function (r) {
//            if (r.code === 0) {
//                $.bsAlert(r.msg + "流程实例id为:" + r.procInstId).ok();
//                $("#processTable").bootstrapTable('refresh');
//            } else {
//                $.bsAlert(r.msg).error();
//            }
//        }
//    });
//}

