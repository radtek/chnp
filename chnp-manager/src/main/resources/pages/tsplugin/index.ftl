<!DOCTYPE html>
<html>
    <head>
        <#include "../common/styles.ftl" />
    </head>
    <body>
        <div class="page">
            <div class="page-head">
                <div class="page-head-left">
                    <span class="page-head-left-title">插件管理</span>
                </div>
                <div class="page-head-right">
                    <button class="btn btn-default" type="button"><i class="fa fa-refresh"></i>刷新</button>
                </div>
            </div>
        
            <div class="page-body">
                <div class="table-search">
                    <div class="table-search-left">
                        <form id="tsplugin_search_form" class="form-inline">
                            <div class="form-group">
                                <input name="search" class="form-control" placeholder="">
                            </div>
                            <button class="btn btn-primary" type="button" onclick="tsPlugin.searching()"><i class="fa fa-search"></i>搜索</button>
                        </form>
                    </div>
                    <div class="table-search-right">
                        <button class="btn btn-primary" type="button" data-url="/tsplugin/new" onclick="modal($(this))"><i class="fa fa-plus"></i>新增</button>
                    </div>
                </div>
                <div>
                    <table id="tTsPlugin" class="table table-striped compact">
                        <thead>
                        <tr>
                            <th class="table-check"><input type="checkbox" /></th>
                            <th>插件名称</th>
                            <th>插件版本</th>
                            <th>更新时间</th>
                            <th>插件类型</th>
                            <th>插件描述</th>
                            <th class="table-operate">操作</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                        <tfoot>
                        <tr>
                            <td>
                                <input type="checkbox" />
                            </td>
                            <td>
                                <button id="tsplugin_multidel_btn" class="btn btn-danger" type="button"><i class="fa fa-trash"></i>批量删除</button>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
        
        <#include "../common/scripts.ftl"/>
        <script src="/assets/js/DatatablesUtil.js"></script>
        <script type="text/javascript">
            var tsPlugin = {};
        
            tsPlugin.searching = function() {
                // 重置Table中Checkbox的状态
                $("table.table input[type=checkbox]").prop({checked: false});
        
                params = $("#tsplugin_search_form").serializeArray();
                tsPlugin.dataTablesUtil.searching(params);
            };
        
            $(function() {
        
                tsPlugin.dataTablesUtil = new DatatablesUtil();
                tsPlugin.dataTablesUtil.init({
                    container: "#tTsPlugin",
                    serverUrl: "/tsplugin/page",
                    columnNames: ["name","version","updateTime","type","descr",],
                    operate: {
                    "data": "id",
                        "className": "table-operate",
                        "render": function (data, type, full, meta) {
                            return ' <button class="btn-default op-btn-view" type="button" data-url="/tsplugin/view?id=' + data + '" onclick="modal($(this))"><i class="fa fa-eye"></i>查看</button>' +
                                    ' <button class="btn-primary op-btn-edit" type="button" data-url="/tsplugin/edit?id=' + data + '" onclick="modal($(this))"><i class="fa fa-edit"></i>编辑</button>' +
                                    ' <button class="btn-danger op-btn-del" type="button" data-url="/tsplugin/del?ids=' + data + '" onclick=""><i class="fa fa-trash"></i>删除</button>';
                        }
                    },
                    dataTable: {
                        order: [
                            [1, "desc"]
                        ],
                        columnDefs: [
                            {
                                "orderable": false,
                                "targets": [0]
                            }
                        ]
                    }
                });
            });
        </script>
    </body>
</html>