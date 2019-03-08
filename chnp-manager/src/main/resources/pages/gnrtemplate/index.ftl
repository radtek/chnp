<!DOCTYPE html>
<html>
    <head>
        <#include "../common/styles.ftl" />
    </head>
    <body>
        <div class="page">
            <div class="page-head">
                <div class="page-head-left">
                    <span class="page-head-left-title">模板列表</span>
                    <button class="btn btn-primary" style="margin-left: 20px;height: auto; padding: 0 5px;" onclick="window.history.back()"><i class="fa fa-fw fa-arrow-circle-left"></i>返回</button>
                </div>
                <div class="page-head-right">
                    <button class="btn btn-default" type="button"><i class="fa fa-refresh"></i>刷新</button>
                </div>
            </div>
        
            <div class="page-body">
                <div class="table-search">
                    <div class="table-search-left">
                        <form id="gnrtemplate_search_form" class="form-inline">
                            <input name="projectId" value="${templateQuery.projectId}" hidden>
                            <div class="form-group">
                                <input name="search" class="form-control" placeholder="">
                            </div>
                            <button class="btn btn-primary" type="button" onclick="gnrTemplate.searching()"><i class="fa fa-search"></i>搜索</button>
                        </form>
                    </div>
                    <div class="table-search-right">
                        <button class="btn btn-primary" type="button" data-url="/gnrtemplate/new?projectId=${templateQuery.projectId}" onclick="modal($(this))"><i class="fa fa-plus"></i>新增</button>
                    </div>
                </div>
                <div>
                    <table id="tGnrTemplate" class="table table-striped compact">
                        <thead>
                        <tr>
                            <th class="table-check"><input type="checkbox" /></th>
                            <th>模板名称</th>
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
                                <button id="gnrtemplate_multidel_btn" class="btn btn-danger" type="button"><i class="fa fa-trash"></i>批量删除</button>
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
            var gnrTemplate = {};
        
            gnrTemplate.searching = function() {
                // 重置Table中Checkbox的状态
                $("table.table input[type=checkbox]").prop({checked: false});
        
                params = $("#gnrtemplate_search_form").serializeArray();
                gnrTemplate.dataTablesUtil.searching(params);
            };
        
            $(function() {
        
                gnrTemplate.dataTablesUtil = new DatatablesUtil();
                gnrTemplate.dataTablesUtil.init({
                    container: "#tGnrTemplate",
                    serverUrl: "/gnrtemplate/page?projectId=${templateQuery.projectId}",
                    columnNames: ["templateName","createTimeString","modifiedTimeString"],
                    operate: {
                    "data": "id",
                        "className": "table-operate",
                        "render": function (data, type, full, meta) {
                            return ' <button class="btn btn-default" type="button" data-url="/gnrtemplate/view?id=' + data + '" onclick="modal($(this))"><i class="fa fa-eye"></i>查看</button>' +
                                    ' <button class="btn btn-primary" type="button" data-url="/gnrtemplate/edit?id=' + data + '" onclick="modal($(this))"><i class="fa fa-edit"></i>编辑</button>' +
                                    ' <button class="btn btn-danger" type="button" data-url="/gnrtemplate/del?ids=' + data + '" onclick=""><i class="fa fa-trash"></i>删除</button>';
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