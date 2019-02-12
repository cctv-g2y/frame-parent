//这里代码必须是第一行[bootstrapTable默认属性重写]
(function ($) {
    'use strict';
    $.extend($.fn.bootstrapTable.defaults, {
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        dataField:"records",
        pagination:true,
        sidePagination:"server",
        search:true,
        searchAlign:"left"
    });
})(jQuery);
