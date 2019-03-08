<!DOCTYPE html>
<html>
    <head>
        <#include "../common/styles.ftl" />
    </head>
    <body>
        <div class="page">
            <div class="page-head">
                <div class="page-head-left">
                    <span class="page-head-left-title">数据列表</span>
                    <button class="btn btn-primary" style="margin-left: 20px;height: auto; padding: 0 5px;" onclick="window.history.back()"><i class="fa fa-fw fa-arrow-circle-left"></i>返回</button>
                </div>
                <div class="page-head-right">
                    <button class="btn btn-default" type="button"><i class="fa fa-refresh"></i>刷新</button>
                </div>
            </div>
        
            <div class="page-body">
                <div class="table-search">
                    <div class="table-search-left">
                        <form id="tskeyvalue_search_form" class="form-inline">
                            <div class="form-group">
                                <input name="search" class="form-control" placeholder="">
                            </div>
                            <button class="btn btn-primary" type="button" onclick="tsKeyValue.searching()"><i class="fa fa-search"></i>搜索</button>
                        </form>
                    </div>
                    <div class="table-search-right">
                        <button class="btn btn-primary" type="button" data-url="/tskeyvalue/new?keyId=${tsKeyValueQuery.keyId}" onclick="modal($(this))"><i class="fa fa-plus"></i>新增</button>
                    </div>
                </div>
                <div>
                    <table id="tTsKeyValue" class="table table-striped compact">
                        <thead>
                        <tr>
                            <th class="table-check"><input type="checkbox" /></th>
                                <th>值</th>
                                <th>中文名</th>
                                <th>排序</th>
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
                                <button id="tskeyvalue_multidel_btn" class="btn btn-danger" type="button"><i class="fa fa-trash"></i>批量删除</button>
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
            var tsKeyValue = {};
        
            tsKeyValue.searching = function() {
                // 重置Table中Checkbox的状态
                $("table.table input[type=checkbox]").prop({checked: false});
        
                params = $("#tskeyvalue_search_form").serializeArray();
            tsKeyValue.dataTablesUtil.searching(params);
            };
        
            $(function() {
        
            tsKeyValue.dataTablesUtil = new DatatablesUtil();
            tsKeyValue.dataTablesUtil.init({
                container: "#tTsKeyValue",
                serverUrl: "/tskeyvalue/page?keyId=${tsKeyValueQuery.keyId}",
                columnNames: ["name","chinese","sort"],
                operate: {
                "data": "id",
                    "className": "table-operate",
                    "render": function (data, type, full, meta) {
                        return ' <button class="btn-default op-btn-view" type="button" data-url="/tskeyvalue/view?id=' + data + '" onclick="modal($(this))"><i class="fa fa-eye"></i>查看</button>' +
                                ' <button class="btn-primary op-btn-edit" type="button" data-url="/tskeyvalue/edit?id=' + data + '" onclick="modal($(this))"><i class="fa fa-edit"></i>编辑</button>' +
                                ' <button class="btn-danger op-btn-del" type="button" data-url="/tskeyvalue/del?ids=' + data + '" onclick=""><i class="fa fa-trash"></i>删除</button>';
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