<!DOCTYPE html>
<html>
    <head>
        <#include "../common/styles.ftl" />
    </head>
    <body>
        <div class="page">
            <div class="page-head">
                <div class="page-head-left">
                    <span class="page-head-left-title">项目管理</span>
                </div>
                <div class="page-head-right">
                    <button class="btn btn-default" type="button"><i class="fa fa-fw fa-refresh"></i>刷新</button>
                </div>
            </div>
        
            <div class="page-body">
                <div class="table-search">
                    <div class="table-search-left">
                        <form id="gnrproject_search_form" class="form-inline">
                            <div class="form-group">
                                <input name="search" class="form-control" placeholder="">
                            </div>
                            <button class="btn btn-primary" type="button" onclick="gnrProject.searching()"><i class="fa fa-fw fa-search"></i>搜索</button>
                        </form>
                    </div>
                    <div class="table-search-right">
                        <button class="btn btn-primary" type="button" data-url="/gnrproject/new" onclick="modal($(this))"><i class="fa fa-plus"></i>新增</button>
                    </div>
                </div>
                <div>
                    <table id="tGnrProject" class="table table-striped compact">
                        <thead>
                        <tr>
                            <th class="table-check"><input type="checkbox" /></th>
                            <th>项目名称</th>
                            <th>创建时间</th>
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
                                <button id="gnrproject_multidel_btn" class="btn btn-danger" type="button"><i class="fa fa-trash"></i>批量删除</button>
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
            var gnrProject = {};
        
            gnrProject.searching = function() {
                // 重置Table中Checkbox的状态
                $("table.table input[type=checkbox]").prop({checked: false});
        
                params = $("#gnrproject_search_form").serializeArray();
                gnrProject.dataTablesUtil.searching(params);
            };
        
            $(function() {
        
                gnrProject.dataTablesUtil = new DatatablesUtil();
                gnrProject.dataTablesUtil.init({
                    container: "#tGnrProject",
                    serverUrl: "/gnrproject/page",
                    columnNames: ["projectName","createTimeString","modifiedTimeString"],
                    operate: {
                    "data": "id",
                        "className": "table-operate",
                        "render": function (data, type, full, meta) {
                            return ' <button class="btn btn-default" type="button" data-url="/gnrproject/view?id=' + data + '" onclick="modal($(this))"><i class="fa fa-fw fa-eye"></i>查看</button>' +
                                    ' <button class="btn btn-primary" type="button" data-url="/gnrdatatype/rel?projectId=' + data + '" onclick="goto($(this))"><i class="fa fa-fw fa-file-code-o"></i>类型映射</button>' +
                                    ' <button class="btn btn-primary" type="button" data-url="/gnrtemplate/rel?projectId=' + data + '" onclick="goto($(this))"><i class="fa fa-fw fa-file-code-o"></i>模板列表</button>' +
                                    ' <button class="btn btn-primary" type="button" data-url="/gnrproject/edit?id=' + data + '" onclick="modal($(this))"><i class="fa fa-fw fa-edit"></i>编辑</button>' +
                                    ' <button class="btn btn-danger" type="button" data-url="/gnrproject/del?ids=' + data + '" onclick=""><i class="fa fa-fw fa-trash"></i>删除</button>';
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