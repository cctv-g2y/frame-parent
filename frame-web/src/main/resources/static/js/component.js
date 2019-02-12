/**
 * bootstrapPopover 工具提示插件 基于:jQuery 与 bootstrap
 * @author makun
 */
;
(function($) {
	if(!$){throw new Error("$ is not define: 未引入jquery");}
	$.fn.extend({  
    "bsPopover":function(option,submitEvent){  
		var the = this;
		if(!$(the).popover){throw new Error("$(...).popover is not a function:未引入popover");}
		if(option){
			var trigger_ = option['trigger'];
			if(trigger_=='focus'){
				$(the).attr("role","button").attr("tabindex",0);
			}
		}
		var defaultOpt = {
			html : true,
			title : '确定删除这条数据吗?',
			content : "<a class='btn btn-primary btn-xs submit'>确定</a>"
					+ "<a class='btn btn-default btn-xs destroy'>取消</a>",
			container : "body",
			placement : "left",
			trigger : "click"
		};
		// 合并属性到默认属性中
		$.extend(defaultOpt, option);
		//如果popover存在则不执行
		if($(".popover.in").length > 0){
			return false;
		}
		// 设置属性之后显示popover
		$(the)
		.popover(defaultOpt)
		.popover("show").on('shown.bs.popover', function() {
			$(".destroy").unbind("click").one("click", function() {
				$(the).popover("destroy");
			});
			$(".submit").unbind("click").one("click", function() {
				$(the).popover("destroy");
				if(option&& typeof(option) =='function'){
					option();
				}
				else if(submitEvent && typeof(submitEvent) =='function'){
					submitEvent();
				}
			});
		}); 
    },
    "bsModal":function(option,submitEvent){
    	var timeStamp = new Date().getTime();
    	var defaultOpt = {
    			title:'Modal title',
    			url:undefined,
    			large:false,
    			params:{},
    			initSuccess:undefined,
    			saveBtnShow:true,
    			saveBtnText:"保存",
    			closeBtnShow:true,
    			closeBtnText:"关闭",
    			isAtClose:false,
    			appendCls:undefined,
    			appendDialogCls:undefined,
    			content:undefined
    	};
    	// 合并属性到默认属性中
		$.extend(defaultOpt, option);
		var appendClass="",appendDialogClass="";
		if(defaultOpt['large']){
			appendClass = "bs-example-modal-lg";
			appendDialogClass = "modal-lg";
		}
		if(defaultOpt['appendCls']){
			appendClass += " " + defaultOpt['appendCls'];
		}
		if(defaultOpt['appendDialogCls']){
			appendDialogClass += " " + defaultOpt['appendDialogCls'];
		}
    	var template = '<div  data-backdrop="static" class="modal fade '+appendClass+'" id="modal'+timeStamp+'" tabindex="-1" role="dialog" aria-labelledby="modallable'+timeStamp+'">'+
    		  '<div class="modal-dialog '+appendDialogClass+'" role="document">'+
        '<div class="modal-content">'+
          '<div class="modal-header">'+
            '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
            '<h4 class="modal-title" id="modallable'+timeStamp+'">$[title]</h4>'+
          '</div>'+
          '<div class="modal-body">'+
          '</div>'+
          '<div class="modal-footer">';
	        if(defaultOpt['closeBtnShow']){
	          	template += '<button type="button" class="btn btn-default" data-dismiss="modal">'+defaultOpt['closeBtnText']+'</button>';
	        }
	        if(defaultOpt['saveBtnShow']){
	        	template += '<button type="button" class="btn btn-primary submitModel">'+defaultOpt['saveBtnText']+'</button>';
	        }
    		template +=
          '</div>'+
        '</div>'+
      '</div>'+
    '</div>';
	$.each(defaultOpt,function(k,v){
		template = template.replace("$["+k+"]",v);
	});
    $(template).appendTo($("body"));
    var $theModel = $('#modal'+timeStamp);
    if(!$theModel.modal){throw new Error("$(...).modal is not a function:未引入modal");}
    //优先动态请求否则可以传入html字符串
    if(defaultOpt['url']){
    	//如果有url参数  请求url获取内容放置到modal-body中
    	$.ajax({
    		url:defaultOpt['url'],
    		data:defaultOpt['params'],
    		type:'post',
    		dataType:"html",
    		success:function(result){
    			$theModel.find(".modal-body").html(result);
    		},
    		error:function(XMLHttpRequest, textStatus, errorThrown){
    			console.log(errorThrown);
    			$.bsAlert("获取内容失败").error();
    		}
    	});
    }
    else{
    	if(defaultOpt['content']){
    		$theModel.find(".modal-body").html(defaultOpt['content']);
    	}
    }
    var getFormJson = function(){
        var o = {};
        var $form = $theModel.find("form");
        var a = $form.serializeArray();  
        $.each(a, function() {  
            if (o[this.name]) {  
                if (!o[this.name].push) {  
                    o[this.name] = [ o[this.name] ];  
                }  
                o[this.name].push(this.value || '');  
            } else {  
                o[this.name] = this.value || '';  
            }  
        });  
        return o; 
    };
    $theModel.modal('show').on("shown.bs.modal",function(){
    	if(defaultOpt["initSuccess"] && typeof(defaultOpt["initSuccess"]) == 'function'){
    		defaultOpt["initSuccess"]($theModel);
    	}
    	$(".submitModel",$theModel).unbind("click").on("click", function() {
    		var formJson = getFormJson();
    		if(defaultOpt.isAtClose){
    			$theModel.modal("hide");
    			if(option&& typeof(option) =='function'){
    				option(formJson);
    			}
    			else if(submitEvent && typeof(submitEvent) =='function'){
    				submitEvent(formJson);
    			}
    		}
    		else{
    			if(option&& typeof(option) =='function'){
    				option(formJson,$theModel);
    			}
    			else if(submitEvent && typeof(submitEvent) =='function'){
    				submitEvent(formJson,$theModel);
    			}
    		}
			
		});
    }).on("hidden.bs.modal", function() {
    	$theModel.remove();
    	//修复多个modal打开无滚动的情况 
    	if($(".modal","body").length>0){
    		$("body").addClass("modal-open");
    	}
    });
	}
	});
	//alert
    $.extend({  
        "bsAlert":function(msg){
        	var bgColor = {
        			"0":"#dd514c",
        			"1":"#4bad4b",
        			"2":"#0e90d2",
        			"3":"#e56c0c"
        	};
        	var iconCls = {
        			"0":"fa-times-circle",
        			"1":"fa-check-square",
        			"2":"fa-info-circle",
        			"3":"fa-spinner"
        	};
        	var defaultMsg = msg?msg:"消息提示";
    		var template =
    			'<div class="alert fade in" '+
    			'style="z-index:9999;position:fixed;width:100%;border-color:#bce8f1;color:#ffffff; background-color:$[bgColor]; top:1%;text-align:center">' +
    			'<i class="fa $[iconCls]" aria-hidden="true"></i><span>' +defaultMsg +
    			'</span></div>';
    		//根据类型初始化
			var init = function(type){
                template = template
				.replace("$[bgColor]",bgColor[type])
				.replace("$[iconCls]",iconCls[type]);
				$(template).insertBefore($("body"));
				$(".alert").each(function(i,obj){
					setTimeout(function(){
						$(obj).alert("close");
					},2000)
				});
			}
			return {
				error:function(){init(0);},
				ok:function(){init(1);},
				info:function(){init(2);},
				warning:function(){init(3);}
			}
        },
        "bsTemplate":function(option){
        	var defaultOption = [];
        	// 合并属性到默认属性中
    		$.extend(defaultOption, option);
    		//定义一个内部类
        	function defaultOpt(){
        	　　　　var defaultVal = {
            		color:"#ffffff",
            		bgColor:"#FF0000",
            		outerClass:"btn btn-xs",
            		innerClass:"fa fa-trash",
            		text:""
            	};
        		return defaultVal;
        	}
        	$.each(defaultOption,function(i,opt){
        		var defaultVal = defaultOpt();
        		$.extend(defaultVal,opt);
        		defaultOption[i] = defaultVal;
        	});
        	//模板
    		var template =
    			"<a href='#'  class='$[outerClass]' style='color:$[color];background-color:$[bgColor];' >"
    			+"<i class='$[innerClass]'></i>$[text]</a>";
    		var aHtml = [];
    		var sHtml = "";
    		$.each(defaultOption,function(i,opt){
    			sHtml = template;
    			$.each(opt,function(k,v){
    				sHtml = sHtml.replace("$["+k+"]",v);
    			});
    			aHtml.push(sHtml);
    		});
			return aHtml.join(" ");
        }
    }); 
})(jQuery);
