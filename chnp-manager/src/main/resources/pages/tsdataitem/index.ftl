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
                        <form id="tsdataitem_search_form" class="form-inline">
                            <input name="dataId" value="${tsDataItemQuery.dataId}" hidden>
                            <div class="form-group">
                                <input name="search" class="form-control" placeholder="">
                            </div>
                            <button class="btn btn-primary" type="button" onclick="tsdataitem.searching()"><i class="fa fa-search"></i>搜索</button>
                        </form>
                    </div>
                    <div class="table-search-right">
                        <button class="btn btn-primary" type="button" data-url="/tsdataitem/new?dataId=${tsDataItemQuery.dataId}" onclick="modal($(this))"><i class="fa fa-plus"></i>新增</button>
                    </div>
                </div>
                <div>
                    <table id="tTsDataItem" class="table table-striped compact">
                        <thead>
                            <tr>
                                <th class="table-check"><input type="checkbox" /></th>
                                    <th>键名</th>
                                    <th>键值</th>
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
                                    <button id="tsdataitem_multidel_btn" class="btn btn-danger" type="button"><i class="fa fa-trash"></i>批量删除</button>
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
            var tsdataitem = {};

            tsdataitem.getFilter = function() {
                return $("#tsdataitem_search_form").serializeArray();
            };

            tsdataitem.searching = function() {
                // 重置Table中Checkbox的状态
                $("table.table input[type=checkbox]").prop({checked: false});

                tsdataitem.dataTablesUtil.searching(tsdataitem.getFilter());
            };
        
            $(function() {
        
                tsdataitem.dataTablesUtil = new DatatablesUtil();
                tsdataitem.dataTablesUtil.init({
                    container: "#tTsDataItem",
                    serverUrl: "/tsdataitem/page",
                    columnNames: ["itemKey","itemValue","itemSort"],
                    operate: {
                    "data": "id",
                        "className": "table-operate",
                        "render": function (data, type, full, meta) {
                            return '&nbsp;<button class="btn-default op-btn-view" type="button" data-url="/tsdataitem/view?id=' + data + '" onclick="modal($(this))"><i class="fa fa-eye"></i>查看</button>' +
                                    '&nbsp;<button class="btn-primary op-btn-edit" type="button" data-url="/tsdataitem/edit?id=' + data + '" onclick="modal($(this))"><i class="fa fa-edit"></i>编辑</button>' +
                                    '&nbsp;<button class="btn-danger op-btn-del" type="button" data-url="/tsdataitem/del?ids=' + data + '" onclick="tsdataitem.del($(this))"><i class="fa fa-trash"></i>删除</button>';
                        }
                    },
                    filter: tsdataitem.getFilter,
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



            tsdataitem.del = function(obj) {
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
                                        tsdataitem.searching();
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