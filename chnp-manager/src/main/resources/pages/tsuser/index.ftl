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
                    <span class="page-head-left-title">用户管理</span>
                </div>
                <div class="page-head-right">
                    <button class="btn btn-default" type="button"><i class="fa fa-refresh"></i>刷新</button>
                </div>
            </div>

            <div class="page-body">
                <div class="table-search">
                    <div class="table-search-left">
                        <form id="tsuser_search_form" class="form-inline">
                            <div class="form-group">
                                <input name="search" class="form-control" placeholder="输入用户名">
                            </div>
                            <button class="btn btn-primary" type="button" onclick="tsuser.searching()"><i class="fa fa-search"></i>搜索</button>
                        </form>
                    </div>
                    <div class="table-search-right">
                        <button class="btn btn-primary" type="button" data-url="/tsuser/new" onclick="modal($(this))"><i class="fa fa-plus"></i>新增</button>
                    </div>
                </div>
                <div>
                    <table id="tTsUser" class="table table-striped">
                        <thead>
                            <tr>
                                <th class="table-check"><input type="checkbox" /></th>
                                <th>登陆账号</th>
                                <th>用户昵称</th>
                                <th>常用邮箱</th>
                                <th>注册时间</th>
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
            var tsuser = {};
            var params = [];

            tsuser.getFilter = function() {
                return $("#tsuser_search_form").serializeArray();
            };

            tsuser.searching = function() {
                // 重置Table中Checkbox的状态
                $("table.table input[type=checkbox]").prop({checked: false});

                tsuser.dataTablesUtil.searching(tsuser.getFilter());
            };

            $(function() {

                tsuser.dataTablesUtil = new DatatablesUtil();
                tsuser.dataTablesUtil.init({
                    container: "#tTsUser",
                    serverUrl: "/tsuser/page",
                    columnNames: ["userName", "userNick", "linkEmail", "registerTimeString"],
                    operate: {
                        "data": "id",
                        "className": "table-operate",
                        "render": function (data, type, full, meta) {
                            return '&nbsp;<button class="btn btn-default" type="button" data-url="/tsuser/view?id=' + data + '" onclick="modal($(this))"><i class="fa fa-eye"></i>查看</button>' +
                                    '&nbsp;<button class="btn btn-primary" type="button" data-url="/tsuser/edit?id=' + data + '" onclick="modal($(this))"><i class="fa fa-edit"></i>编辑</button>' +
                                    '&nbsp;<button class="btn btn-danger" type="button" data-url="/tsuser/del?ids=' + data + '" onclick="tsuser.del($(this))"><i class="fa fa-trash"></i>删除</button>';
                        }
                    },
                    filter: tsuser.getFilter,
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
            });



            tsuser.del = function(obj) {
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
                                        tsuser.searching();
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