/** 初始化 */
$(function() {
    $('#alreadyTable').bootstrapTable({
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
        url: "alreadydata",
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
                var pageSize = $('#alreadyTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#alreadyTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
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
            field: 'taskDefinitionKey',
            title: '任务节点ID',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
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
            title: '流程定义ID',
            formatter:function(value, row , index){
                if(null != value && '' != value){
                    return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'endTime',
            title: '完成时间',
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
                    return '<span data-toggle="tooltip" title="V:'+ value +'">V:' + value + '</span>';
                }else{
                    return '-';
                }
            }
        },{
            field: 'status',
            title: '跟踪流程',
            formatter:function(value, row , index){
                if("0" == value){
                    return '<span data-toggle="tooltip" title="跟踪流程"><a style="text-decoration:none;" target="_blank" href="' + baseURL + 'diagram-viewer/index.html?processDefinitionId=' + row.processDefinitionId + '&processInstanceId=' + row.processInstanceId + '">跟踪流程</a></span>';
                }else if("1" == value){
                    return '<span data-toggle="tooltip" title="流程已结束">流程已结束</span>';
                }
            }
        }]
    });
    $(".bootstrap-table .search").find("input").attr("placeholder","请输入任务名称").attr("title","请输入任务名称");
});

/** 部署 */
function deploys(){
    var rows = $("#alreadyTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $.bsAlert("请选择一条记录").error();
        return;
    }
    if (rows.length > 1) {
        $.bsAlert("只能选择一条记录").error();
        return;
    }
    var id = rows[0].id;
    $.ajax({
        url : baseURL + 'actmodel/deploys',
        data : {
            'id' : id
        },
        success: function (r) {
            if (r.code === 0) {
                $.bsAlert(r.msg).ok();
                $("#alreadyTable").bootstrapTable('refresh');
            } else {
                $.bsAlert(r.msg).error();
            }
            $(".arrows-page-btn-box").hide();
            guan();
        }
    });
}
