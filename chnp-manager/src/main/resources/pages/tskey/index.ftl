<!DOCTYPE html>
<html>
    <head>
        <#include "../common/styles.ftl" />
    </head>
    <body>
        <div class="page">
            <div class="page-head">
                <div class="page-head-left">
                    <span class="page-head-left-title">系统配置</span>
                </div>
                <div class="page-head-right">
                    <button class="btn btn-default" type="button"><i class="fa fa-fw fa-refresh"></i>刷新</button>
                </div>
            </div>

            <div class="page-body">
                <div class="table-search">
                    <div class="table-search-left">
                        <form id="tskey_search_form" class="form-inline">
                            <div class="form-group">
                                <input name="search" class="form-control" placeholder="输入键名、中文名">
                            </div>
                            <button class="btn btn-primary" type="button" onclick="tsKey.searching()"><i class="fa fa-fw fa-search"></i>搜索</button>
                        </form>
                    </div>
                    <div class="table-search-right">
                        <button class="btn btn-primary" type="button" data-url="/tskey/new" onclick="modal($(this))"><i class="fa fa-fw fa-plus"></i>新增</button>
                    </div>
                </div>
                <div>
                    <table id="tTsKey" class="table table-striped">
                        <thead>
                            <tr>
                                <th class="table-check"><input type="checkbox" /></th>
                                <th>字典标识</th>
                                <th>字典名称</th>
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
                                    <button id="tskey_multidel_btn" class="btn btn-danger" type="button"><i class="fa fa-fw fa-trash"></i>批量删除</button>
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
            var tsKey = {};

            tsKey.searching = function() {
                // 重置Table中Checkbox的状态
                $("table.table input[type=checkbox]").prop({checked: false});

                params = $("#tskey_search_form").serializeArray();
                tsKey.dataTablesUtil.searching(params);
            };

            $(function() {

                tsKey.dataTablesUtil = new DatatablesUtil();
                tsKey.dataTablesUtil.init({
                    container: "#tTsKey",
                    serverUrl: "/tskey/page",
                    columnNames: ["name", "chinese"],
                    operate: {
                        "data": "id",
                        "className": "table-operate",
                        "render": function (data, type, full, meta) {
                            return '&nbsp;<button class="btn btn-default" type="button" data-url="/tskey/view?id=' + data + '" onclick="modal($(this))"><i class="fa fa-fw fa-eye"></i>查看</button>' +
                                    '&nbsp;<button class="btn btn-primary" type="button" data-url="/tskeyvalue/rel?keyId=' + data + '" onclick="goto($(this))"><i class="fa fa-fw fa-list"></i>数据列表</button>' +
                                    '&nbsp;<button class="btn btn-primary" type="button" data-url="/tskey/edit?id=' + data + '" onclick="modal($(this))"><i class="fa fa-fw fa-edit"></i>编辑</button>' +
                                    '&nbsp;<button class="btn btn-danger" type="button" data-url="/tskey/del?ids=' + data + '" onclick=""><i class="fa fa-fw fa-trash"></i>删除</button>';
                        }
                    },
                    dataTable: {
                        order: [
                            [1, "desc"]
                        ],
                        columnDefs: [
                            {
                                "orderable": false,
                                "targets": [0, 3]
                            }
                        ]
                    }
                });

                $("#tskey_multidel_btn").on("click", function(e) {
                    e.preventDefault();
                    alert(tsKey.dataTablesUtil.getSelectedItems());
                });
            });
        </script>
    </body>
</html>