/** baseURL */
var baseURL = "/frame/";

/** 时间格式化处理 */
function dateFtt(fmt, date) {
    var o = {
        "M+": date.getMonth() + 1,                 //月份
        "d+": date.getDate(),                    //日
        "h+": date.getHours(),                   //小时
        "m+": date.getMinutes(),                 //分
        "s+": date.getSeconds(),                 //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 * 判断一个元素是否为空
 * 
 * @param obj
 * @returns {boolean}
 */
var isEmpty = function(obj) {
	return undefined == obj || "" == obj || null == obj
			|| typeof (obj) == undefined || "undefined" == obj;
}

/**
 * bootstrapTable刷新;
 * 
 * @param tableId
 * @param fn
 */
function refresh(tableId, fn) {
	$(tableId).bootstrapTable('refresh');
	fn && typeof fn === 'function' && fn();
}

/**
 * 强制修改BootstrapTable search Placeholder的值
 * 
 * @param placeText
 */
function searchPlaceholder(placeText, title) {
	var input = $(".bootstrap-table .search").find("input").attr("placeholder",
			placeText);
	if (!Util.isEmpty(title)) {
		input.attr("title", title)
	}
}

/**
 * 重置表单
 * 
 * @param form
 */
function resetForm(form) {
	$(form)[0].reset();
	$(form).data('bootstrapValidator').destroy();
	$(form).data('bootstrapValidator', null);
	formValidator();
}

/**
 * 获取当前时间
 * 
 * @returns {String}
 */
function getdate() {
	var date = new Date();
	var mon = date.getMonth() + 1; // getMonth()返回的是0-11，则需要加1
	if (mon <= 9) { // 如果小于9的话，则需要加上0
		mon = "0" + mon;
	}
	var day = date.getDate(); // getdate()返回的是1-31，则不需要加1
	if (day <= 9) { // 如果小于9的话，则需要加上0
		day = "0" + day;
	}
	var hours = date.getHours();
	if (hours <= 9) { // 如果小于9的话，则需要加上0
		hours = "0" + hours;
	}
	var minutes = date.getMinutes();
	if (minutes <= 9) { // 如果小于9的话，则需要加上0
		minutes = "0" + minutes;
	}
	return date.getFullYear() + "-" + mon + "-" + day + " " + hours + ":"
			+ minutes;
}

var Util = {
	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @returns {boolean}
	 */
	isEmpty : function(obj) {
		return undefined == obj || "" == obj || null == obj
				|| typeof (obj) == undefined || "undefined" == obj;
	},

	/**
	 * 判断是否为字符串
	 * 
	 * @param o
	 * @returns {boolean}
	 */
	isString : function(o) {
		return Object.prototype.toString.call(o).slice(8, -1) === 'String';
	},

	/**
	 * 判断是否为数字
	 * 
	 * @param o
	 * @returns {boolean}
	 */
	isNumber : function(o) {
		return Object.prototype.toString.call(o).slice(8, -1) === 'Number';
	},
	/**
	 * 判断是否为对象
	 * 
	 * @param obj
	 * @returns {boolean}
	 */
	isObj : function(o) {
		return Object.prototype.toString.call(o).slice(8, -1) === 'Object';
	},

	/**
	 * 判断是否为数组
	 * 
	 * @param o
	 * @returns {boolean}
	 */
	isArray : function(obj) { // 是否数组
		return Object.prototype.toString.call(o).slice(8, -1) === 'Array';
	},
	/**
	 * 判断是否为时间
	 * 
	 * @param o
	 * @returns {boolean}
	 */
	isDate : function(o) {
		return Object.prototype.toString.call(o).slice(8, -1) === 'Date';
	},
	/**
	 * 判断是否为boolean
	 * 
	 * @param o
	 * @returns {boolean}
	 */
	isBoolean : function(o) { // 是否boolean
		return Object.prototype.toString.call(o).slice(8, -1) === 'Boolean'
	},

	/**
	 * 判断是否为函数
	 * 
	 * @param o
	 * @returns {boolean}
	 */
	isFuntion : function(o) {
		return Object.prototype.toString.call(o).slice(8, -1) === 'Function';
	},
	/**
	 * 判断是否为nul
	 * 
	 * @param o
	 * @returns {boolean}
	 */
	isNull : function(o) {
		l
		return Object.prototype.toString.call(o).slice(8, -1) === 'Null'
	},
	/**
	 * 判定是否为undefined
	 * 
	 * @param o
	 * @returns {boolean}
	 */
	isUndefined : function(o) {
		return Object.prototype.toString.call(o).slice(8, -1) === 'Undefined'
	},
	/**
	 * 判断是否为false;
	 * 
	 * @param o
	 * @returns {boolean}
	 */
	isFalse : function(o) {
		if (o == '' || o == undefined || o == null || o == 'null'
				|| o == 'undefined' || o == 0 || o == false || o == NaN)
			return true
		return false
	},
	/**
	 * 判断是否为true;
	 * 
	 * @param o
	 * @returns {boolean}
	 */
	isTrue : function(o) {
		return !this.isFalse(o)
	},

	/**
	 * 判断是否为PC端
	 * 
	 * @returns {boolean}
	 */
	isPC : function() {
		var userAgentInfo = navigator.userAgent;
		var Agents = [ "Android", "iPhone", "SymbianOS", "Windows Phone",
				"iPad", "iPod" ];
		var flag = true;
		for (var v = 0; v < Agents.length; v++) {
			if (userAgentInfo.indexOf(Agents[v]) > 0) {
				flag = false;
				break;
			}
		}
		return flag;
	},

	/**
	 * 判断是否为IE
	 * 
	 * @returns {boolean}
	 */
	isIeBrowser : function() {
		if (!!window.ActiveXObject || "ActiveXObject" in window) {
			return true;
		} else {
			return false;
		}
	},

	/**
	 * 浏览器类型
	 * 
	 * @returns {string}
	 */
	browserType : function() {
		var userAgent = navigator.userAgent; // 取得浏览器的userAgent字符串
		var isOpera = userAgent.indexOf("Opera") > -1; // 判断是否Opera浏览器
		var isIE = userAgent.indexOf("compatible") > -1
				&& userAgent.indexOf("MSIE") > -1 && !isOpera; // 判断是否IE浏览器
		var isEdge = userAgent.indexOf("Edge") > -1; // 判断是否IE的Edge浏览器
		var isFF = userAgent.indexOf("Firefox") > -1; // 判断是否Firefox浏览器
		var isSafari = userAgent.indexOf("Safari") > -1
				&& userAgent.indexOf("Chrome") == -1; // 判断是否Safari浏览器
		var isChrome = userAgent.indexOf("Chrome") > -1
				&& userAgent.indexOf("Safari") > -1; // 判断Chrome浏览器
		if (isIE) {
			var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
			reIE.test(userAgent);
			var fIEVersion = parseFloat(RegExp["$1"]);
			if (fIEVersion == 7)
				return "IE7"
			else if (fIEVersion == 8)
				return "IE8";
			else if (fIEVersion == 9)
				return "IE9";
			else if (fIEVersion == 10)
				return "IE10";
			else if (fIEVersion == 11)
				return "IE11";
			else
				return "IE7以下"// IE版本过低
		}

		if (isFF)
			return "FF";
		if (isOpera)
			return "Opera";
		if (isEdge)
			return "Edge";
		if (isSafari)
			return "Safari";
		if (isChrome)
			return "Chrome";
	},
	/**
	 * 校验
	 * 
	 * @param str
	 * @param type
	 * @returns {boolean}
	 */
	checkStr : function(str, type) {
		switch (type) {
		case 'phone': // 手机号码
			return /^1[3|4|5|7|8][0-9]{9}$/.test(str);
		case 'tel': // 座机
			return /^(0\d{2,3}-\d{7,8})(-\d{1,4})?$/.test(str);
		case 'card': // 身份证
			return /^\d{15}|\d{18}$/.test(str);
		case 'pwd': // 密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线
			return /^[a-zA-Z]\w{5,17}$/.test(str)
		case 'postal': // 邮政编码
			return /[1-9]\d{5}(?!\d)/.test(str);
		case 'QQ': // QQ号
			return /^[1-9][0-9]{4,9}$/.test(str);
		case 'email': // 邮箱
			return /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(str);
		case 'money': // 金额(小数点2位)
			return /^\d*(?:\.\d{0,2})?$/.test(str);
		case 'URL': // 网址
			return /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?/
					.test(str)
		case 'IP': // IP
			return /((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))/
					.test(str);
		case 'date': // 日期时间
			return /^(\d{4})\-(\d{2})\-(\d{2}) (\d{2})(?:\:\d{2}|:(\d{2}):(\d{2}))$/
					.test(str)
					|| /^(\d{4})\-(\d{2})\-(\d{2})$/.test(str)
		case 'number': // 数字
			return /^[0-9]$/.test(str);
		case 'english': // 英文
			return /^[a-zA-Z]+$/.test(str);
		case 'chinese': // 中文
			return /^[\u4E00-\u9FA5]+$/.test(str);
		case 'lower': // 小写
			return /^[a-z]+$/.test(str);
		case 'upper': // 大写
			return /^[A-Z]+$/.test(str);
		case 'HTML': // HTML标记
			return /<("[^"]*"|'[^']*'|[^'">])*>/.test(str);
		default:
			return true;
		}
	},

	/**
	 * 获取网址参数
	 * 
	 * @param name
	 * @returns {*}
	 */
	getURL : function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return r[2];
		return null;
	},
	/**
	 * 获取全部url参数,并转换成json对象
	 * 
	 * @param url
	 * @returns {{}}
	 */
	getUrlAllParams : function(url) {
		var url = url ? url : window.location.href;
		var _pa = url.substring(url.indexOf('?') + 1), _arrS = _pa.split('&'), _rs = {};
		for (var i = 0, _len = _arrS.length; i < _len; i++) {
			var pos = _arrS[i].indexOf('=');
			if (pos == -1) {
				continue;
			}
			var name = _arrS[i].substring(0, pos), value = window
					.decodeURIComponent(_arrS[i].substring(pos + 1));
			_rs[name] = value;
		}
		return _rs;
	},

	/**
	 * 删除url指定参数，返回url
	 * 
	 * @param url
	 * @param name
	 * @returns {*}
	 */
	delParamsUrl : function(url, name) {
		var baseUrl = url.split('?')[0] + '?';
		var query = url.split('?')[1];
		if (query.indexOf(name) > -1) {
			var obj = {}
			var arr = query.split("&");
			for (var i = 0; i < arr.length; i++) {
				arr[i] = arr[i].split("=");
				obj[arr[i][0]] = arr[i][1];
			}
			;
			delete obj[name];
			var url = baseUrl
					+ JSON.stringify(obj).replace(/[\"\{\}]/g, "").replace(
							/\:/g, "=").replace(/\,/g, "&");
			return url
		} else {
			return url;
		}
	}
}

/**
 * 格式化序号列
 * 
 * @param val
 * @param row
 * @param index
 * @returns
 */
function numberColumn(val, row, index) {
	return index + 1;
}

/** 判断cookie */
/** $(function() {
	var c_name = "user";
	getCookie(c_name);
});
function getCookie(c_name) {
	var ck = null;
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=")
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1
			c_end = document.cookie.indexOf(";", c_start)
			if (c_end == -1)
				c_end = document.cookie.length
			ck = unescape(document.cookie.substring(c_start, c_end));
		}
	}
	if (null == ck || "" == ck) {
		window.location.href = "login.html";
	}
}*/

/** ajax配置 */
/**
 * lishibin
 * 公共ajax方法
 * @param url 请求地址
 * @param data 请求附加参数 默认 {}
 * @param callBack 请求成功回调
 * @param erroCallBack 请求错误回调
 * @param type 请求方式 默认post
 * @param dataType 期望返回数据类型默认json
 * @returns
 */
function $ajax(url, data, callBack, erroCallBack, type, dataType) {
    var _url = baseURL + url
        , _dataType = dataType ? dataType : 'json'
        , _data = data ? data : {}
        , _type = type ? type : 'POST';
    $.ajax({
        url: _url,
        dataType: _dataType,
        data: _data,
        type: _type,
        error: function (data) {
            if (erroCallBack && typeof(erroCallBack) == 'function') {
                erroCallBack(data);
            }
        },
        success: function (data) {
            if (callBack && typeof(callBack) == 'function') {
                callBack(data);
            }
        }
    });
}

/**
 * ajax
 * @param param
 * @returns
 */
function req(param) {
    if (!param && !param.url) {
        return
    }
    $.ajax({
        url: baseURL + param.url,
        data: param.data || {},
        header: param.header || {},
        contentType: param.contentType || 'application/x-www-form-urlencoded',
        type: param.type || 'POST',
        cache:param.cache || 'true',
        async: param.async || true,
        success: function (res) {
            //继续在这里加判断
            param.success && typeof param.success === 'function' && param.success(res);
        },
        error: function (e) {
            param.error && typeof param.error === 'function' && param.error(e);
        }
    })
}

/** 确认提示框组件 */
(function($) {

	window.Ewin = function() {
		var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">'
				+ '<div class="modal-dialog modal-sm">'
				+ '<div class="modal-content">'
				+ '<div class="modal-header">'
				+ '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'
				+ '<h4 class="modal-title" id="modalLabel">[Title]</h4>'
				+ '</div>'
				+ '<div class="modal-body">'
				+ '<p>[Message]</p>'
				+ '</div>'
				+ '<div class="modal-footer">'
				+ '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>'
				+ '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>'
				+ '</div>' + '</div>' + '</div>' + '</div>';

		var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">'
				+ '<div class="modal-dialog">'
				+ '<div class="modal-content">'
				+ '<div class="modal-header">'
				+ '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'
				+ '<h4 class="modal-title" id="modalLabel">[Title]</h4>'
				+ '</div>'
				+ '<div class="modal-body">'
				+ '</div>'
				+ '</div>'
				+ '</div>' + '</div>';
		var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
		var generateId = function() {
			var date = new Date();
			return 'mdl' + date.valueOf();
		}
		var init = function(options) {
			options = $.extend({}, {
				title : "操作提示",
				message : "提示内容",
				btnok : "确定",
				btncl : "取消",
				width : 200,
				auto : false
			}, options || {});
			var modalId = generateId();
			var content = html.replace(reg, function(node, key) {
				return {
					Id : modalId,
					Title : options.title,
					Message : options.message,
					BtnOk : options.btnok,
					BtnCancel : options.btncl
				}[key];
			});
			$('body').append(content);
			$('#' + modalId).modal({
				width : options.width,
				backdrop : 'static'
			});
			$('#' + modalId).on('hide.bs.modal', function(e) {
				$('body').find('#' + modalId).remove();
			});
			return modalId;
		}

		return {
			alert : function(options) {
				if (typeof options == 'string') {
					options = {
						message : options
					};
				}
				var id = init(options);
				var modal = $('#' + id);
				modal.find('.ok').removeClass('btn-success').addClass(
						'btn-primary');
				modal.find('.cancel').hide();

				return {
					id : id,
					on : function(callback) {
						if (callback && callback instanceof Function) {
							modal.find('.ok').click(function() {
								callback(true);
							});
						}
					},
					hide : function(callback) {
						if (callback && callback instanceof Function) {
							modal.on('hide.bs.modal', function(e) {
								callback(e);
							});
						}
					}
				};
			},
			confirm : function(options) {
				var id = init(options);
				var modal = $('#' + id);
				modal.find('.ok').removeClass('btn-primary').addClass(
						'btn-success');
				modal.find('.cancel').show();
				return {
					id : id,
					on : function(callback) {
						if (callback && callback instanceof Function) {
							modal.find('.ok').click(function() {
								callback(true);
							});
							modal.find('.cancel').click(function() {
								callback(false);
							});
						}
					},
					hide : function(callback) {
						if (callback && callback instanceof Function) {
							modal.on('hide.bs.modal', function(e) {
								callback(e);
							});
						}
					}
				};
			},
			dialog : function(options) {
				options = $.extend({}, {
					title : 'title',
					url : '',
					width : 800,
					height : 550,
					onReady : function() {
					},
					onShown : function(e) {
					}
				}, options || {});
				var modalId = generateId();

				var content = dialogdHtml.replace(reg, function(node, key) {
					return {
						Id : modalId,
						Title : options.title
					}[key];
				});
				$('body').append(content);
				var target = $('#' + modalId);
				target.find('.modal-body').load(options.url);
				if (options.onReady())
					options.onReady.call(target);
				target.modal();
				target.on('shown.bs.modal', function(e) {
					if (options.onReady(e))
						options.onReady.call(target, e);
				});
				target.on('hide.bs.modal', function(e) {
					$('body').find(target).remove();
				});
			}
		}
	}();
})(jQuery);
