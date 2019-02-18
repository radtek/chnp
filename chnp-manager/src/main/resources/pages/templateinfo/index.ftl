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
                    <span class="page-head-left-title">代码模板管理</span>
                </div>
                <div class="page-head-right">
                    <button class="btn btn-default" type="button"><i class="fa fa-refresh"></i>刷新</button>
                </div>
            </div>

            <div class="page-body">
                <div class="table-search">
                    <div class="table-search-left">
                        <form id="template_search_form" class="form-inline">
                            <div class="form-group">
                                <input name="search" class="form-control" placeholder="输入配置名称、标识或内容">
                            </div>
                            <button class="btn btn-primary" type="button" onclick="templateInfo.searching()"><i class="fa fa-search"></i>搜索</button>
                        </form>
                    </div>
                    <div class="table-search-right">
                        <button class="btn btn-primary" type="button" data-url="/templateinfo/new" onclick="modal($(this))"><i class="fa fa-plus"></i>新增</button>
                    </div>
                </div>
                <div>
                    <table id="tTemplate" class="table table-striped">
                        <thead>
                            <tr>
                                <th class="table-check"><input type="checkbox" /></th>
                                <th>模板名称</th>
                                <th>模板引擎</th>
                                <th>更新时间</th>
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
                                    <button class="btn btn-danger" type="button"><i class="fa fa-trash"></i>批量删除</button>
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
            var templateInfo = {};

            templateInfo.searching = function() {
                // 重置Table中Checkbox的状态
                $("table.table input[type=checkbox]").prop({checked: false});

                params = $("#template_search_form").serializeArray();
                templateInfo.dataTablesUtil.searching(params);
            };

            $(function() {

                templateInfo.dataTablesUtil = new DatatablesUtil();
                templateInfo.dataTablesUtil.init({
                    container: "#tTemplate",
                    serverUrl: "/templateinfo/page",
                    columnNames: ["name", "engine", "updateTimeString"],
                    operate: {
                        "data": "id",
                        "className": "table-operate",
                        "render": function (data, type, full, meta) {
                            return '&nbsp;<button class="btn-default op-btn-view" type="button" data-url="/templateinfo/view?id=' + data + '" onclick="modal($(this))"><i class="fa fa-eye"></i>查看</button>' +
                                    '&nbsp;<button class="btn-primary op-btn-edit" type="button" data-url="/templateinfo/edit?id=' + data + '" onclick="modal($(this))"><i class="fa fa-edit"></i>编辑</button>' +
                                    '&nbsp;<button class="btn-danger op-btn-del" type="button" data-url="/templateinfo/del?ids=' + data + '" onclick=""><i class="fa fa-trash"></i>删除</button>';
                        }
                    },
                    dataTable: {
                        order: [
                            [3, "desc"]
                        ],
                        columnDefs: [
                            {
                                "orderable": false,
                                "targets": [0, 4]
                            }
                        ]
                    }
                });
            });
        </script>
    </body>
</html>