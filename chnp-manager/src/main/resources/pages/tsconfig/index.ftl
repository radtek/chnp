<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/assets/plugins/zTree/css/zTreeStyle.css">
        <#include "../common/styles.ftl" />
    </head>
    <body>
        <div class="page">
            <div class="page-head">
                <div class="page-head-left">
                    <span class="page-head-left-title">系统配置</span>
                </div>
                <div class="page-head-right">
                    <button class="btn btn-default" type="button"><i class="fa fa-refresh"></i>刷新</button>
                </div>
            </div>

            <div class="page-body">
                <div class="table-search">
                    <div class="table-search-left">
                        <form id="tsconfig_search_form" class="form-inline">
                            <div class="form-group">
                                <input name="search" class="form-control" placeholder="输入配置名称、标识或内容">
                            </div>
                            <button class="btn btn-primary" type="button" onclick="tsconfig.searching()"><i class="fa fa-search"></i>搜索</button>
                        </form>
                    </div>
                    <div class="table-search-right">
                        <button class="btn btn-primary" type="button" data-url="/tsconfig/new" onclick="modal($(this))"><i class="fa fa-plus"></i>新增</button>
                    </div>
                </div>
                <div>
                    <table id="tTsConfig" class="table table-striped">
                        <thead>
                            <tr>
                                <th class="table-check"><input type="checkbox" /></th>
                                <th>配置名称</th>
                                <th>配置标识</th>
                                <th>配置内容</th>
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
                                    <button id="tsconfig_multidel_btn" class="btn btn-danger" type="button"><i class="fa fa-trash"></i>批量删除</button>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>

        <#include "../common/scripts.ftl"/>
        <script src="/assets/plugins/zTree/js/jquery.ztree.all-3.min.js"></script>
        <script src="/assets/js/DatatablesUtil.js"></script>
        <script type="text/javascript">
            var tsconfig = {};

            tsconfig.searching = function() {
                // 重置Table中Checkbox的状态
                $("table.table input[type=checkbox]").prop({checked: false});

                params = $("#tsconfig_search_form").serializeArray();
                tsconfig.dataTablesUtil.searching(params);
            };

            $(function() {

                tsconfig.dataTablesUtil = new DatatablesUtil();
                tsconfig.dataTablesUtil.init({
                    container: "#tTsConfig",
                    serverUrl: "/tsconfig/page",
                    columnNames: ["configName", "configKey", "configVal"],
                    operate: {
                        "data": "id",
                        "className": "table-operate",
                        "render": function (data, type, full, meta) {
                            return '&nbsp;<button class="btn btn-default" type="button" data-url="/tsconfig/view?id=' + data + '" onclick="modal($(this))"><i class="fa fa-eye"></i>查看</button>' +
                                    '&nbsp;<button class="btn btn-primary" type="button" data-url="/tsconfig/edit?id=' + data + '" onclick="modal($(this))"><i class="fa fa-edit"></i>编辑</button>' +
                                    '&nbsp;<button class="btn btn-danger" type="button" data-url="/tsconfig/del?ids=' + data + '" onclick=""><i class="fa fa-trash"></i>删除</button>';
                        }
                    },
                    dataTable: {
                        order: [
                            [1, "desc"]
                        ],
                        columnDefs: [
                            {
                                "orderable": false,
                                "targets": [0, 4]
                            }
                        ]
                    }
                });

                $("#tsconfig_multidel_btn").on("click", function(e) {
                    e.preventDefault();
                    alert(tsconfig.dataTablesUtil.getSelectedItems());
                });
            });
        </script>
    </body>
</html>