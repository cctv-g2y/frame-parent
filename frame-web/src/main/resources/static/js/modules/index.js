/** 初始化 */
$(function() {
    showMenu();
    changeFrameHeight();
});

/** 动态改变页面元素的高度 */
function changeFrameHeight() {
    $("#iframepage").height($(window).height() - 167);
    $("#sidebar").height($(window).height() - 45);
}

/** 监听页面大小改变触发事件 */
$(window).resize(function() {
    changeFrameHeight();
});

/** 查询菜单列表 * */
function showMenu() {
    $.ajax({
        type : 'POST',
        url : baseURL + "menu/menuload",
        success : function(r) {
            $('#menulist').append(getMenu(r));
        }
    });
}

/** 渲染菜单 * */
function getMenu(r) {
    var str = '';
    for (var i = 0; i < r.length; i++) {
        str += '<li id="' + r[i].menuId + 'actmenu">';
        str += '<a href="#" class="dropdown-toggle">';
        str += '<i class="'+r[i].icon+'"></i>';
        str += '<span class="menu-text">' +r[i].menuName+ '</span>';
        str += '<b class="arrow fa fa-angle-down"></b>';
        str += '</a>';
        str += '<b class="arrow"></b>';
        str += '<ul class="submenu">';
        for (var j = 0; j < r[i].list.length; j++) {
            str += '<li id="' + r[i].list[j].menuId + 'actmenu">';
            str += '<a href="'+ r[i].list[j].url + '" navname = "' + r[i].list[j].menuName + '" target = "index" pact = "' + r[i].menuId + '" act ="' + r[i].list[j].menuId + '" basedata ="' + r[i].list[j].url + '" onclick = "actmenu(this)">';
            str += '<i class="menu-icon fa fa-caret-right"></i>';
            str += r[i].list[j].menuName;
            str += '</a>';
            str += '<b class="arrow"></b>';
            str += '</li>';
        }
        str += '</ul>';
        str += '</li>';
    }
    return str;
}

/** 单击菜单活动选中 */
function actmenu(obj){
    var actId = "#" + $(obj).attr('act') + "actmenu";
    var pactId = "#" + $(obj).attr('pact') + "actmenu";
    var basedata = $(obj).attr('basedata');
    var navname = $(obj).attr('navname');
    $("#menulist li").removeClass("active");
    $(actId).addClass("active");
    $(pactId).addClass("active");
    var li = $("#mynav").find("li")[1];
    if(undefined != li){
        li.remove();
    }
    $("#mynav").append(getmynav(basedata,navname));
}

/** 渲染快捷导航栏 */
function getmynav(u,n){
    var str = "";
    str += '<li id = "navs">';
    str += '<i class = "glyphicon glyphicon-user"></i>';
    str += '<a href = "' + u + '" target = "index">';
    str += n;
    str += '</a>';
    str += '</li>';
    return str;
}


