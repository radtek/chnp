<!DOCTYPE html>
<html>
    <head>
        <#include "../common/styles.ftl" />
        <link rel="stylesheet" href="/assets/css/CHNPStepTab.css">
    </head>
    <body>
        <div class="page">
            <div class="page-head">
                <div class="page-head-left">
                    <span class="page-head-left-title">代码生成器</span>
                </div>
                <div class="page-head-right"></div>
            </div>

            <div class="page-body">
                <div class="m-steptab">
                    <div class="steps">
                        <div class="active"><span>第1步 选择模板</span></div>
                        <div><span>第2步 项目信息</span></div>
                        <div><span>第3步 配置数据源</span></div>
                        <div><span>第4步 确认配置</span></div>
                    </div>
                    <div class="content">
                        <div class="active">
                            <!--
                            <div class="table-search">
                                <div class="table-search-left">
                                    <form id="tsuser_search_form" class="form-inline">
                                        <div class="form-group">
                                            <input name="search" class="form-control" placeholder="输入用户名">
                                        </div>
                                        <button class="btn btn-primary" type="button" onclick="searching()"><i class="fa fa-search"></i>搜索</button>
                                    </form>
                                </div>
                                <div class="table-search-right">
                                </div>
                            </div>
                            <div>
                                <table id="tTemplate" class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th class="table-check"><input type="checkbox" /></th>
                                        <th>模板文件</th>
                                        <th>模板引擎</th>
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
                            -->
                        </div>
                        <div>
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label for="orgType" class="col-sm-2 control-label">组织类型简写<i class="fa fa fa-question-circle" title="如：org、com、cn、edu等"></i>：</label>
                                    <div class="col-sm-3">
                                        <input id="orgType" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="orgName" class="col-sm-2 control-label">组织名称简写<i class="fa fa fa-question-circle" title="如：baidu、sina、tencent等"></i>：</label>
                                    <div class="col-sm-3">
                                        <input id="orgName" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="proName" class="col-sm-2 control-label">项目名称简写<i class="fa fa fa-question-circle" title="如：ai、weibo、wechat等"></i>：</label>
                                    <div class="col-sm-3">
                                        <input id="proName" type="text" class="form-control">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label for="dbHost" class="col-sm-2 control-label">数据库<i class="fa fa fa-question-circle" title=""></i>：</label>
                                    <div class="col-sm-2">
                                        <input id="dbHost" type="text" class="form-control" placeholder="数据库的主机名或IP" value="localhost">
                                    </div>
                                    <div class="col-sm-1">
                                        <input id="dbPort" type="text" class="form-control" placeholder="端口" value="3306">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="dbUser" class="col-sm-2 control-label">用户名称<i class="fa fa fa-question-circle" title=""></i>：</label>
                                    <div class="col-sm-3">
                                        <input id="dbUser" type="text" class="form-control" placeholder="数据库的合法用户" value="root">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="dbPswd" class="col-sm-2 control-label">用户密码<i class="fa fa fa-question-circle" title=""></i>：</label>
                                    <div class="col-sm-3">
                                        <input id="dbPswd" type="password" class="form-control" placeholder="用户对应的密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-3">
                                        <button class="btn btn-primary" type="button"><i class="fa fa-link"></i>连接</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>Panel 4</div>
                    </div>
                    <div class="operates">
                        <button type="button" class="btnPrevStep" disabled="disabled"><i class="fa fa-fw fa-angle-left"></i>上一步</button>
                        <button type="button" class="btnNextStep">下一步<i class="fa fa-fw fa-angle-right"></i></button>
                        <button type="button" class="btnFinish" disabled="disabled"><i class="fa fa-fw fa-upload"></i>提交</button>
                    </div>
                </div>
            </div>
        </div>

        <#include "../common/scripts.ftl"/>
        <script src="/assets/js/CHNPStepTab.js"></script>
        <script>
            var template = {};
            $(function() {
                CHNPStepTab({
                    containerSelector: "div.m-steptab",
                    beforeNext: function(index) {
                        console.log(index);
                    },
                    beforeFinish: function() {
                        console.log("finish");
                    }
                });

                /*template.dataTablesUtil = new DatatablesUtil();
                template.dataTablesUtil.init({
                    container: "#tTemplate",
                    columnNames: ["fileName", "engine"],
                    operate: {
                        "data": "id",
                        "className": "table-operate",
                        "render": function (data, type, full, meta) {
                            return '&nbsp;<button class="op-btn-view" type="button" data-url="/tsuser/view?id=' + data + '" onclick="modal($(this))"><i class="fa fa-eye"></i>查看</button>' +
                                    '&nbsp;<button class="op-btn-edit" type="button" data-url="/tsuser/edit?id=' + data + '" onclick="modal($(this))"><i class="fa fa-edit"></i>编辑</button>' +
                                    '&nbsp;<button class="op-btn-del" type="button" data-url="/tsuser/del?ids=' + data + '" onclick=""><i class="fa fa-trash"></i>删除</button>';
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
                });*/

            })
        </script>
    </body>
</html>