<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>首页</title>
        <#include "./common/styles.ftl" />
        <link rel="stylesheet" href="/assets/css/chnp-menu.css">
        <style>
            html, body, .frame{
                height: 100%;
                margin: 0;
            }
            .frame{
                display: flex;
                align-items: stretch;
            }
            .frame-menu{
                flex: 0 0 auto;
                display: flex;
                flex-flow: column nowrap;
                align-items: stretch;
                background-color: #293038;
            }
            .frame-menu-user{
                flex: 0 0 auto;
            }
            .frame-menu-list{
                flex: 1 1 auto;
            }
            .frame-borrow{
                flex: 1 1 auto;
                display: flex;
                flex-flow: column nowrap;
                align-items: stretch;
            }
            .frame-borrow-tabs{
                flex: 0 0 auto;
                background-color: #fafafa;
                border-bottom: 2px solid #2f4050;
                overflow-x: hidden;
                display: flex;
                align-items: stretch;
            }
            .frame-borrow-tabs> *{
                flex: 0 0 auto;
                line-height: 40px;
                font-size: 12px;
                color: #999;
                border-right: 1px solid #eee;
                cursor: pointer;
                padding: 0 10px;
                display: flex;
                align-items: center;
            }
            .frame-borrow-tabs> *:hover{
                background-color: #f2f2f2;
            }
            .frame-borrow-tabs> *.active{
                background-color: #293846;
                color: #a7b1c2;
            }
            .frame-borrow-tabs> *.active:hover{
                color: white;
            }
            .frame-borrow-content{
                flex: 1 1 auto;
                position: relative;
                display: flex;
                align-items: stretch;
            }
            .frame-borrow-content> *{
                flex: 1 1 auto;
            }
        </style>
    </head>
    <body>
        <div class="frame">
            <div class="frame-menu">
                <div class="frame-menu-user"></div>
                <div class="frame-menu-list">
                </div>
            </div>
            <div class="frame-borrow">
                <div class="frame-borrow-tabs">
                    <div>
                        <span>模板管理</span>
                        <i class="fa fa-fw fa-times-circle" onclick="this.parentNode.remove();"></i>
                    </div>
                    <div class="active">
                        <span>代码生成</span>
                        <i class="fa fa-fw fa-times-circle"></i>
                    </div>
                </div>
                <div class="frame-borrow-content">
                    <iframe id="page_1" width="100%" height="100%" hidden></iframe>
                    <iframe id="page_2" marginheight="0" marginwidth="0" frameborder="0" src="http://www.w3school.com.cn/cssref/css_selectors.asp"></iframe>
                </div>
            </div>
        </div>

        <#include "./common/scripts.ftl"/>
        <script>

            var CHNPMenu = function (settings) {
                var defSettings = {
                    container: "",
                    data: [],
                    objAttrs: {
                        id: "id",
                        name: "name",
                        pid: "parentId",
                        isParent: "isParent",
                        icon: "icon",
                        url: "url"
                    },
                    bind: function() {}
                };
                $.extend(defSettings, settings);

                var pages = {
                    1: {
                        prev: 0,
                        next: 2
                    }
                };

                $(defSettings.container).html(generate(0, 1, defSettings.data));

                $(defSettings.container).find("label[data-url]").on("click", function() {

                });

                defSettings.bind();

                function goto() {
                    var tabsWrapper = $("div.frame-borrow-tabs"),
                        framesWrapper = $("div.frame-borrow-content");
                    tabsWrapper.find("div.active").removeClass("active");
                    framesWrapper.find("iframe").hide();
                    tabsWrapper.append(
                        '<div class="active">' +
                            '<span>模板管理</span>' +
                            '<i class="fa fa-fw fa-times-circle" onclick="this.parentNode.remove();"></i>' +
                        '</div>');
                    framesWrapper.append('<iframe id="page_3" marginheight="0" marginwidth="0" frameborder="0" src="' + $(this).data("url") + '"></iframe>');
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
                                        '<input id="' + id + '" class="menu-item-active" type="checkbox"/>' +
                                        '<label for="' + id + '" class="menu-title-wrapper menu-item-level' + level + '" ' + (1 === e[defSettings.objAttrs.isParent] ? '': 'data-url="' + e[defSettings.objAttrs.url] + '"') + '>' +
                                            '<span class="menu-title-icon"><i class="' + e[defSettings.objAttrs.icon] + '"></i></span>' +
                                            '<span class="menu-title-text">' + e[defSettings.objAttrs.name] + '</span>' +
                                        '</label>' +
                                        (1 === e[defSettings.objAttrs.isParent] ? '<div class="menu-children-wrapper">' + generate(e[defSettings.objAttrs.id], level + 1, data) + '</div>' : '') +
                                    '</div>';
                        }
                    });
                    return html;
                }
            };

            $(function() {

                var menus = [
                    {id:1, name:"代码生成管理", parentId:0, isParent:1, icon:"fa fa-caret-right"},
                    {id:2, name:"模板管理", parentId:1, isParent:0, icon:"fa fa-file-text-o", url:"http://www.baidu.com"}
                ];

                new CHNPMenu({
                    container: "div.frame-menu-list",
                    data: menus
                });
            })

        </script>
    </body>
</html>