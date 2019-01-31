
var CHNPFrame = function (settings) {
    var defSettings = {
        container: "div.view-frame-left-menu",
        isServer: false,
        serverUrl: "#",
        data: [],
        rootPid: 0,
        objAttrs: {
            id: "id",
            name: "name",
            pid: "parentId",
            isParent: "isParent",
            icon: "icon",
            url: "url"
        }
    };
    $.extend(defSettings, settings);

    // 若选择动态生成模式，则须先获取菜单数据，再生成菜单；否则须提供静态数据
    if (defSettings.isServer) {
        $.ajax({
            type: "GET",
            url: defSettings.serverUrl,
            dataType: "json",
            success: function (json) {
                defSettings.data = json;
                $(defSettings.container).html(generate(defSettings.rootPid, 1, defSettings.data));
                bindMenuClick();
            }
        });
    }else {
        $(defSettings.container).html(generate(defSettings.rootPid, 1, defSettings.data));
        bindMenuClick();
    }

    /**递归遍历菜单结构
     *
     * @param pid 父节点
     * @param level 层级
     * @param data 遍历对象
     * @return string 菜单列表HTML字符串
     */
    function generate(pid, level, data) {
        var html = "";
        $.each(data, function(i, e) {
            if (e[defSettings.objAttrs.pid] === pid) {
                var id = 'level' + level + '_' + e[defSettings.objAttrs.id];
                html += '<div class="menu-item-wrapper">' +
                            '<div class="menu-item-title-wrapper menu-item-level' + level + '" ' + (1 === e[defSettings.objAttrs.isParent] ? '': 'data-url="' + e[defSettings.objAttrs.url] + '"') + ' data-text=' + e[defSettings.objAttrs.name] + ' data-id=' + id + '>' +
                                '<span class="menu-item-title-icon"><i class="' + (1 === e[defSettings.objAttrs.isParent] && 1 === level ? 'fa fa-caret-right' : e[defSettings.objAttrs.icon]) + '"></i></span>' +
                                '<span class="menu-item-title-text">' + e[defSettings.objAttrs.name] + '</span>' +
                            '</div>' +
                            (1 === e[defSettings.objAttrs.isParent] ? '<div class="menu-item-children-wrapper">' + generate(e[defSettings.objAttrs.id], level + 1, data) + '</div>' : '') +
                        '</div>';
            }
        });
        return html;
    }


    var pages = {};

    // 绑定菜单的单击事件
    function bindMenuClick() {
        $(defSettings.container).find(".menu-item-title-wrapper").on("click", function (e) {
            e.preventDefault();

            var children = $(this).next("div.menu-item-children-wrapper");
            if (children.length > 0) {
                if ($(this).hasClass("active")) $(this).removeClass("active");
                else $(this).addClass("active");
            } else if ($(this)[0].hasAttribute("data-url")) {
                var tabsWrapper = $("div.view-frame-right-tabs"),
                    pageWrapper = $("div.view-frame-right-page");

                var pageId = $(this).data("id");
                if (typeof pages[pageId] === "undefined") {
                    pages[pageId] = {};

                    // 隐藏其他页面
                    $("div.menu-item-level2.active").removeClass("active");
                    tabsWrapper.find("div.active").removeClass("active");
                    pageWrapper.find("iframe").hide();

                    // 显示当前页面
                    $(this).addClass("active");
                    tabsWrapper.append(
                        '<div class="active" data-id="' + pageId + '">' +
                        '<span>' + $(this).data("text") + '</span>' +
                        '<i class="fa fa-fw fa-times-circle"></i>' +
                        '</div>');
                    pageWrapper.append('<iframe id="page_' + pageId + '" marginheight="0" marginwidth="0" frameborder="0" src="' + $(this).data("url") + '"></iframe>');

                    // 绑定当前页面的关闭事件
                    tabsWrapper.find("div.active .fa-times-circle").on("click", function (e) {
                        e.preventDefault();

                        var parent = $(this)[0].parentNode;
                        delete pages[pageId];
                        parent.remove();
                        pageWrapper.find("#page_" + pageId).remove();

                        var tabList = tabsWrapper.find("div");
                        if (0 < tabList.length) {
                            var lastTab = tabList[tabList.length - 1];
                            if (!$(lastTab).hasClass("active")) {
                                $(lastTab).addClass("active");
                                pageWrapper.find("#page_" + $(lastTab).data("id")).show();
                            }
                        }

                        e.stopPropagation();
                    });

                    // 绑定标签页的单击事件
                    tabsWrapper.find("div.active").on("click", function (e) {
                        e.preventDefault();

                        if (!$(this).hasClass("active")) {
                            tabsWrapper.find("div.active").removeClass("active");
                            pageWrapper.find("iframe").hide();

                            $(this).addClass("active");
                            pageWrapper.find("#page_" + pageId).show();
                        }
                    });
                } else {
                    $("div.menu-item-level2.active").removeClass("active");
                    tabsWrapper.find("div.active").removeClass("active");
                    pageWrapper.find("iframe").hide();

                    $(this).addClass("active");
                    tabsWrapper.find("div[data-id=" + pageId + "]").addClass("active");
                    pageWrapper.find("#page_" + pageId).show();
                }
            }
        });
    }
};