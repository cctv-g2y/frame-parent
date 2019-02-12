/** baseURL */
var baseURL = '/frame/';

/** 回车登录 */
$(document).keyup(function(event) {
    if (event.keyCode == 13) {
        login();
    }
});

/** 初始化 */
$(function() {
    formValidator();
});

/** 登录方法 */
function login() {
    $('#loginer').data('bootstrapValidator').validate();
    var isV = $("#loginer").data('bootstrapValidator').isValid();
    if (!isV) {
        return;
    }
    var username = $("#username").val();
    var password = $("#password").val();
    var data = {
        "username" : username,
        "password" : password
    };
    $.ajax({
        type : 'POST',
        url : baseURL + 'init',
        data : data,
        success : function(r) {
            if (0 == r.code) {
                parent.location.href = baseURL + "index";
            } else {
                $.bsAlert(r.msg).error();
                $("#username").val("");
                $("#password").val("");
            }
        }
    });
}

/** 表单验证 */
function formValidator() {
    $("#loginer").bootstrapValidator({
        group : '.form-group',
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        live : 'enabled', // 内容有变化就进行第一次验证
        fields : {
            username : {
                verbose : false,
                validators : {
                    notEmpty : {
                        message : '用户名不能为空'
                    }
                }
            },
            password : {
                verbose : false,
                validators : {
                    notEmpty : {
                        message : '密码不能为空'
                    }
                }
            }
        }
    });
}