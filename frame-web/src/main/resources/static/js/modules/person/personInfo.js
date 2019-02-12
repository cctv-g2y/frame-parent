/** 初始化 */
$(function(){
	formValidator();
	passwordValidator();
});

/** 个人资料修改start */

/** 表单验证 */
function formValidator() {
    $("#personinfo").bootstrapValidator({
        group: '.form-group',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        live: 'enabled', // 内容有变化就进行第一次验证
        fields: {
        	personName: {
                verbose: false,
                validators: {
                    notEmpty: {
                        message: '请输入昵称'
                    },
                    stringLength: {
                    	min : 2,
						max : 8,
						message : '昵称长度必须在2-8之间'
                    }
                }
            },
            personTel: {
            	verbose : false,
				validators : {
					regexp : {
						regexp : /^1\d{10}$/,
						message : '手机号格式有误'
					}
				}
            },
            personAddress: {
            	verbose: false,
            	validators: {
                    notEmpty: {
                        message: '请输入地址'
                    },
                    stringLength: {
						max : 20,
						message : '地址长度不能超过20位'
                    }
                }
            },
            personEmail : {
				verbose : false,
				validators : {
					emailAddress : {
						message : '个人邮箱格式有误'
					}
				}
			},
			personSign : {
				verbose : false,
				validators : {
					stringLength: {
						max : 50,
						message : '签名长度不能超过20位'
                    }
				}
			}
        }
    });
}

/** 保存或修改 */
function commit() {
    $('#personinfo').data('bootstrapValidator').validate();
    var validState = $("#personinfo").data('bootstrapValidator').isValid();
    if (validState != true) {
        return;
    }
    $.ajax({
        url : null == $("#personId").val() || "" == $("#personId").val() ? baseURL + "person/save" : baseURL + "person/update",
        data : {
        	personId : $("#personId").val(),
    		personName : $("#personName").val(),
    		personHeader : $("#personHeader").val(),
    		personNum : $("#personNum").val(),
    		personTel : $("#personTel").val(),
    		personAddress : $("#personAddress").val(),
    		personEmail : $("#personEmail").val(),
    		personSign : $("#personSign").val(),
    		personSex : $("input[name='sex']:checked").val()
        },
        success : function (r) {
            if (r.code === 0) {
                $.bsAlert(r.msg).ok();
                setTimeout("f5()",500);
            } else {
            	$.bsAlert(r.msg).error();
            }
        }
    });
}

/** 重置表单 */
function reSet(){
	$("#personName").val("");
	$("#personTel").val("");
	$("#personAddress").val("");
	$("#personEmail").val("");	
	$("#personSign").val("");	
	$("#personinfo").data('bootstrapValidator').destroy();
    formValidator();
}

/** 刷新 */
function f5(){
	window.location.href = baseURL + "person/page";
}

/** 上传头像 */
$('#uploadPhoto').fileinput({
	language : 'zh',
	uploadUrl : baseURL + 'person/uploadphoto',
	showUpload : false,
	uploadExtraData : function(previewId, index) {
		var data = {
		}
		return data;
	},
	showPreview : false,
	showRemove : false,
	uploadAsync : false,
	allowedFileExtensions : ['jpg','gif','png'],
	maxFileCount : 1
});

/** 文件选中就上传 */
$("#uploadPhoto").on("filebatchselected", function (event, files) {
	$("#uploadPhoto").fileinput("upload");
});

/** 上传成功回调 */
$("#uploadPhoto").on("filebatchuploadsuccess", function(event, data, previewId, index) {
	if(data.response.code == 0){
		$("#personHeader").val(data.response.doc.filePath);
		var url = baseURL + 'person/loadphoto?path='+data.response.doc.filePath;
		$("#personInfoPhoto").attr('src',url);
	}else{
		$.bsAlert(data.response.msg).error();
	}
});

/** 上传失败回调 */
$('#uploadPhoto').on('fileerror', function(event, data, msg) {
	alert();
	window.location = baseURL + "document/fasterror";
});

/** 点击文件上传input框触发选择文件 */
$(function(){
	$('.kv-fileinput-caption').click(function(){
		$('#uploadPhoto').trigger('click');
	})
});

/** 个人资料修改end */

/** 密码修改start */

/** 密码校验 */
function passwordValidator() {
	$("#updatepasswordform").bootstrapValidator({
		group : '.form-group',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		live : 'enabled', //内容有变化就进行第一次验证
		fields : {
			password : {
				verbose : false,
				validators : {
					notEmpty : {
						message : '请输入旧密码'
					},
					stringLength : {
						min : 6,
						message : '密码长度不能小于6位或超过18位'
					},
					remote : {
						message : "旧密码错误",
						url : baseURL + 'user/checkpassword',
						data : {
							password : $("input[name='password']").val()
						},
						delay : 2000,
						type : 'POST'
					},
					callback : {
						callback : function(value, validator){
							if(!isEmpty($("input[name='newPwd']").val())){
								var bootstrapValidator = $("#updatepasswordform").data('bootstrapValidator');
								bootstrapValidator.updateStatus('newPwd','NOT_VALIDATED').validateField('newPwd');
							}
							return true;
						}
					}
				}
			},
			newPwd : {
				verbose : false,
				validators : {
					callback : {
						callback : function(value, validator){
							if(!isEmpty($("input[name='confirmPwd']").val())){
								var bootstrapValidator = $("#updatepasswordform").data('bootstrapValidator');
								bootstrapValidator.updateStatus('confirmPwd','NOT_VALIDATED').validateField('confirmPwd');
							}
							return true;
						}
					},
					notEmpty : {
						message : '请输入新密码'
					},
					stringLength : {
						min : 6,
						max : 18,
						message : '密码长度不能小于6位或超过18位'
					},
					different : {
						field : 'password',
						message : '新密码不能与旧密码相同'
					}
					
				}
			},
			confirmPwd : {
				validators : {
					notEmpty : {
						message : '请重复输入新密码'
					},
					identical : {
						field : 'newPwd',
						message : '两次输入的密码不一致'
					}
				}
			}
		}
	});
}

/** 修改密码提交 */
function submitpad(){
	$.ajax({
		url : baseURL + 'user/updatepassword',
		data : {
			newPassword : $("#confirmPwd").val()
		},
		success : function(r){
			if(r.code == 0){
				$.bsAlert(r.msg).ok();
				setTimeout("f5()",500);
			}else{
				$.bsAlert(r.msg).error();
			}
		}
	});
}

/** 重置密码表单 */
function restpad(){
	$("#password").val("");
	$("#newPwd").val("");
	$("#confirmPwd").val("");
	$("#updatepasswordform").data('bootstrapValidator').destroy();
	passwordValidator();
}

/** 密码修改end */
