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
                        <form id="tsdata_search_form" class="form-inline">
                            <div class="form-group">
                                <input name="search" class="form-control" placeholder="输入字典名、中文名">
                            </div>
                            <button class="btn btn-primary" type="button" onclick="tsdata.searching()"><i class="fa fa-fw fa-search"></i>搜索</button>
                        </form>
                    </div>
                    <div class="table-search-right">
                        <button class="btn btn-primary" type="button" data-url="/tsdata/new" onclick="modal($(this))"><i class="fa fa-fw fa-plus"></i>新增</button>
                    </div>
                </div>
                <div>
                    <table id="tTsData" class="table table-striped">
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
                                    <button id="tsdata_multidel_btn" class="btn btn-danger" type="button"><i class="fa fa-fw fa-trash"></i>批量删除</button>
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
            var tsdata = {};

            tsdata.getFilter = function() {
                return $("#tsdata_search_form").serializeArray();
            };

            tsdata.searching = function() {
                // 重置Table中Checkbox的状态
                $("table.table input[type=checkbox]").prop({checked: false});

                tsdata.dataTablesUtil.searching(tsdata.getFilter());
            };

            $(function() {

                tsdata.dataTablesUtil = new DatatablesUtil();
                tsdata.dataTablesUtil.init({
                    container: "#tTsData",
                    serverUrl: "/tsdata/page",
                    columnNames: ["dataKey", "dataName"],
                    operate: {
                        "data": "id",
                        "className": "table-operate",
                        "render": function (data, type, full, meta) {
                            return '&nbsp;<button class="btn btn-default" type="button" data-url="/tsdata/view?id=' + data + '" onclick="modal($(this))"><i class="fa fa-fw fa-eye"></i>查看</button>' +
                                    '&nbsp;<button class="btn btn-primary" type="button" data-url="/tsdataitem/rel?dataId=' + data + '" onclick="goto($(this))"><i class="fa fa-fw fa-list"></i>数据列表</button>' +
                                    '&nbsp;<button class="btn btn-primary" type="button" data-url="/tsdata/edit?id=' + data + '" onclick="modal($(this))"><i class="fa fa-fw fa-edit"></i>编辑</button>' +
                                    '&nbsp;<button class="btn btn-danger" type="button" data-url="/tsdata/del?ids=' + data + '" onclick="tsdata.del($(this))"><i class="fa fa-fw fa-trash"></i>删除</button>';
                        }
                    },
                    filter: tsdata.getFilter,
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

                $("#tsdata_multidel_btn").on("click", function(e) {
                    e.preventDefault();
                    alert(tsData.dataTablesUtil.getSelectedItems());
                });
            });

            tsdata.del = function(obj) {
                var url = obj.data('url');

                bootbox.confirm({
                    message: '确定要<strong style="color: red;">删除</strong>选中的记录吗？',
                    buttons: {
                        confirm: {
                            label: '<i class="fa fa-fw fa-trash"></i>删除',
                            className: 'btn btn-danger'
                        },
                        cancel: {
                            label: '取消'
                        }
                    },
                    callback: function(result) {
                        if (result) {
                            $.ajax({
                                type: "post",
                                url: url,
                                dataType: "json",
                                success: function(data) {
                                    if (1 === data.returnCode) {
                                        tsdata.searching();
                                    }else {
                                        bootbox.alert(data.msg);
                                    }
                                },
                                error: function() {
                                    bootbox.alert("请求失败");
                                }
                            })
                        }
                    }
                });
            };
        </script>
    </body>
</html>