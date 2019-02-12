var processcategory;
/** 初始化 */
$(function() {
    getprocesscategory();
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
            field: 'deploymentId',
            title: '部署Id',
            formatter:function (value, row, index) {
                return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
           }
        },{
            field: 'deploymentTime',
            title: '部署时间',
            formatter:function (value, row, index) {
                return changeDateFormat(value);
            }
        },{
            field: 'version',
            title: '模型版本',
            formatter:function (value, row, index) {
                return '<span data-toggle="tooltip" title="V:'+ value +'">V:' + value + '</span>';
            }
        },{
            field: 'xmlName',
            title: '流程XML',
            formatter:function (value, row, index) {
                return '<span data-toggle="tooltip" title="'+ value +'"><a style="text-decoration:none;" target="_blank" href="' + baseURL + 'actprocess/resource/read?procDefId='+ row.id +'&resType=xml">' + value + '</a></span>';
            }
        },{
            field: 'imageName',
            title: '流程图片',
            formatter:function (value, row, index) {
                return '<span data-toggle="tooltip" title="'+ value +'"><a style="text-decoration:none;" target="_blank" href="' + baseURL + 'actprocess/resource/read?procDefId='+ row.id +'&resType=image">' + value + '</a></span>';
            }
        },{
            field: 'isSuspended',
            title: '挂起状态',
            formatter:function (value, row, index) {
                if('true' == value || value){
                    return '<span class="label label-danger">已挂起</span>';
                }else{
                    return '<span class="label label-success">已激活</span>';
                }
            }
        }]
    });
    
    $("#processtables .search").find("input").attr("placeholder","请输入流程名称").attr("title","请输入流程名称");
    
});

/** 开始上传 */
function uploadActFiles(){
    $("#uploadActProcess").fileinput("upload");
}

/** 流程文件上传方法 */
$("#uploadActProcess").fileinput({
    allowedFileExtensions: ['zip', 'bar', 'bpmn', 'bpmn20.xml'],//接收的文件后缀zip、bar、bpmn、bpmn20.xml
    language : 'zh',
    uploadUrl : 'deploy',
    uploadExtraData : function(previewId, index) {
        var actcategory = $("#actcategory option:selected").val();
        var data = {
            "category" : actcategory
        }
        return data;
    },
    showUpload : false,
    showPreview : false,
    showRemove : true,
    dropZoneEnabled : true
});

/** 上传结果显示 */
$("#uploadActProcess").on("fileuploaded", function(event, data, msg) {
    if (data.response.code != 0) {
        $.bsAlert(data.response.msg).error();
    }else{
        $.bsAlert(data.response.msg).ok();
        setTimeout("f5()", 700);
    }
});

/** 加载运行中流程列表 */
function runningTable(){
    $('#runningTable').bootstrapTable({
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
        url: "running",
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
                var pageSize = $('#runningTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#runningTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        },{
            field: 'id',
            title: '执行ID',
            formatter:function(value, row , index){
                return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
            }
        },{
            field: 'processDefinitionName',
            title: '流程名称',
            formatter:function(value, row , index){
                return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
            }
        },{
            field: 'processInstanceId',
            title: '流程实例ID',
            formatter:function(value, row , index){
                return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
            }
        },{
            field: 'processDefinitionId',
            title: '流程定义ID',
            formatter:function(value, row , index){
                return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
            }
        },{
            field: 'processDefinitionKey',
            title: '流程定义key',
            formatter:function(value, row , index){
                return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
            }
        },{
            field: 'processDefinitionVersion',
            title: '流程版本',
            formatter:function(value, row , index){
                return '<span data-toggle="tooltip" title="v:'+ value +'">v:' + value + '</span>';
            }
        },{
            field: 'activityId',
            title: '当前环节',
            formatter:function (value, row, index) {
                return '<span data-toggle="tooltip" title="'+ value +'">' + value + '</span>';
            }
        },{
            field: 'isSuspended',
            title: '挂起状态',
            formatter:function (value, row, index) {
                if(value){
                    return '<span class="label label-danger">已挂起</span>';
                }else{
                    return '<span class="label label-success">已激活</span>';
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
    
    $("#runningtables .search").find("input").attr("placeholder","请输入环节名称").attr("title","请输入环节名称");
}

/** 查询流程分类列表 */
function getprocesscategory(){
    $.ajax({
        url : baseURL + 'actprocess/getprocesscategory',
        success: function (r) {
            if(null != r){
                processcategory = r;
                $("#actcategory").html(addoption(r));
            }
        }
    });
}

/** option渲染 */
function addoption(array){
    var opti="";
    for (var i = 0; i < array.length; i++) {
        opti += "<option value='"+array[i].code+"'>"+array[i].name+"</option>";
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

/** 挂起流程 */
function suspends(){
    var rows = $("#processTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $.bsAlert("请选择一条记录").error();
        return;
    }
    if (rows.length > 1) {
        $.bsAlert("只能选择一条记录").error();
        return;
    }
    if(rows[0].isSuspended){
        $.bsAlert("该流程已经处于挂起状态").info();
        return;
    }
    var id = rows[0].id;
    $.ajax({
        url : baseURL + 'actprocess/update/suspend',
        data : {
            'procDefId' : id
        },
        success: function (r) {
            if (r.code === 0) {
                $.bsAlert(r.msg).ok();
                $("#processTable").bootstrapTable('refresh');
            } else {
                $.bsAlert(r.msg).error();
            }
        }
    });
}

/** 激活流程 */
function actives(){
    var rows = $("#processTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $.bsAlert("请选择一条记录").error();
        return;
    }
    if (rows.length > 1) {
        $.bsAlert("只能选择一条记录").error();
        return;
    }
    if(!rows[0].isSuspended){
        $.bsAlert("该流程已经处于激活状态").info();
        return;
    }
    var id = rows[0].id;
    $.ajax({
        url : baseURL + 'actprocess/update/active',
        data : {
            'procDefId' : id
        },
        success: function (r) {
            if (r.code === 0) {
                $.bsAlert(r.msg).ok();
                $("#processTable").bootstrapTable('refresh');
            } else {
                $.bsAlert(r.msg).error();
            }
        }
    });
}

/** 转为模型 */
function tomodels(){
    var rows = $("#processTable").bootstrapTable('getSelections');
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
        url : baseURL + 'actprocess/convert/tomodel',
        data : {
            'procDefId' : id
        },
        success: function (r) {
            if (r.code === 0) {
                $.bsAlert(r.msg).ok();
                $("#processTable").bootstrapTable('refresh');
            } else {
                $.bsAlert(r.msg).error();
            }
        }
    });
}

/** 导出图片 */
function toimgs(){
    var rows = $("#processTable").bootstrapTable('getSelections');
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
        url : baseURL + 'actprocess/export/diagrams',
        data : {
            'procDefId' : id
        },
        success: function (r) {
            if (r.code === 0) {
                $.bsAlert(r.msg).ok();
                $("#processTable").bootstrapTable('refresh');
            } else {
                $.bsAlert(r.msg).error();
            }
        }
    });
}

/** 删除定义流程 */
function dels(){
    var rows = $("#processTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $.bsAlert("至少选择一条记录").error();
        return;
    }
    var deploymentIds = [];
    $.each(rows, function (i, o) {
        deploymentIds.push(o.deploymentId);
    });
    Ewin.confirm({message: '确定要删除选中的记录？'}).on(function (e) {
        if (e) {
            $.ajax({
                type: "post",
                url: baseURL + "actprocess/delete",
                contentType: "application/json",
                data: JSON.stringify(deploymentIds),
                success: function (r) {
                    if (r.code === 0) {
                        $.bsAlert(r.msg).ok();
                        $("#processTable").bootstrapTable('refresh');
                    } else {
                        $.bsAlert(r.msg).error();
                    }
                }
            });
        }
    });
}

/** 删除流程实例 */
function delRunList(){
    var rows = $("#runningTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $.bsAlert("至少选择一条记录").error();
        return;
    }
    var procInsIds = [];
    $.each(rows, function (i, o) {
        procInsIds.push(o.processInstanceId);
    });
    Ewin.confirm({message: '确定要删除选中的记录？'}).on(function (e) {
        if (e) {
            $.ajax({
                type: "post",
                url: baseURL + "actprocess/deleteprocins",
                contentType: "application/json",
                data: JSON.stringify(procInsIds),
                success: function (r) {
                    if (r.code === 0) {
                        $.bsAlert(r.msg).ok();
                        $("#runningTable").bootstrapTable('refresh');
                    } else {
                        $.bsAlert(r.msg).error();
                    }
                }
            });
        }
    });
}

/** 刷新 */
function f5() {
    window.location.href = baseURL + "actprocess/processlistpage";
}

