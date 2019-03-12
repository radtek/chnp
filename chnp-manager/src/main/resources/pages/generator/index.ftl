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
                        <div id="tab1" class="row active">
                            <div class="table-search">
                                <div class="table-search-left">
                                    <form id="template_search_form" class="form-inline">
                                        <div class="form-group">
                                            <label class="control-label">项目：</label>
                                            <select name="projectId" class="form-control">
                                                <option value="">-- 请选择项目 --</option>
                                                <#list projects as project>
                                                <option value="${project.id}" title="${project.projectName}"><#if (project.projectName)?length gt 10>${project.projectName?substring(0, 10)}...<#else>${project.projectName}</#if></option>
                                                </#list>
                                            </select>
                                        </div>
                                    </form>
                                </div>
                                <div class="table-search-right"></div>
                            </div>
                            <div>
                                <table id="tTemplate" class="table table-striped">
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
                                                <button class="btn btn-danger" type="button"><i class="fa fa-trash"></i>批量删除</button>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                        <div id="tab2" class="row">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label for="orgType" class="col-sm-2 control-label">组织类型简写<i class="fa fa fa-question-circle" title="如：org、com、cn、edu等"></i>：</label>
                                    <div class="col-sm-3">
                                        <input id="orgType" name="orgType" type="text" class="form-control" placeholder="例如：com、cn">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="orgName" class="col-sm-2 control-label">组织名称简写<i class="fa fa fa-question-circle" title="如：baidu、sina、tencent等"></i>：</label>
                                    <div class="col-sm-3">
                                        <input id="orgName" name="orgName" type="text" class="form-control" placeholder="例如：tencent、alibaba">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="proName" class="col-sm-2 control-label">项目名称简写<i class="fa fa fa-question-circle" title="如：ai、weibo、wechat等"></i>：</label>
                                    <div class="col-sm-3">
                                        <input id="proName" name="proName" type="text" class="form-control" placeholder="例如：wechat、taobao">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="moduleName" class="col-sm-2 control-label">模块名称简写<i class="fa fa fa-question-circle" title="如：blog、manager等"></i>：</label>
                                    <div class="col-sm-3">
                                        <input id="moduleName" name="moduleName" type="text" class="form-control" placeholder="项目划分的具体模块">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div id="tab3" class="row">
                            <div class="col-sm-5">
                                <form class="form-horizontal" action="/generator/test/db">
                                    <div class="form-group">
                                        <label for="dbHost" class="col-sm-3 control-label">数据库<i class="fa fa fa-question-circle" title=""></i>：</label>
                                        <div class="col-sm-4">
                                            <input id="dbHost" name="dbHost" type="text" class="form-control" placeholder="数据库的主机名或IP" value="127.0.0.1">
                                        </div>
                                        <div class="col-sm-2" style="padding: 0 8px;">
                                            <input id="dbPort" name="dbPort" type="text" class="form-control" placeholder="端口" value="3306">
                                        </div>
                                        <div class="col-sm-3">
                                            <input id="dbName" name="dbName" type="text" class="form-control" placeholder="库名">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="dbPswd" class="col-sm-3 control-label">表集合<i class="fa fa fa-question-circle" title=""></i>：</label>
                                        <div class="col-sm-9">
                                            <textarea id="tables" name="tables" type="text" class="form-control" rows="3" placeholder="待生成的表集合"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="dbUser" class="col-sm-3 control-label">用户名称<i class="fa fa fa-question-circle" title=""></i>：</label>
                                        <div class="col-sm-9">
                                            <input id="dbUser" name="dbUser" type="text" class="form-control" placeholder="数据库的合法用户" value="root">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="dbPswd" class="col-sm-3 control-label">用户密码<i class="fa fa fa-question-circle" title=""></i>：</label>
                                        <div class="col-sm-9">
                                            <input id="dbPswd" name="dbPswd" type="password" class="form-control" placeholder="用户对应的密码">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-9">
                                            <button class="btn btn-primary" type="button" onclick="generator.testDB($(this))"><i class="fa fa-link"></i>连接</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="col-sm-7"></div>
                        </div>
                        <div id="tab2" class="row">

                        </div>
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
        <script src="/assets/js/DatatablesUtil.js"></script>
        <script>
            var generator = {};

            generator.getFilter = function() {
                return $("#template_search_form").serializeArray();
            };

            generator.searching = function() {
                // 重置Table中Checkbox的状态
                $("#tTemplate input[type=checkbox]").prop({checked: false});

                generator.dataTablesUtil.searching(generator.getFilter());
            };

            $(function() {

                $("#template_search_form").find('select[name="projectId"]').on("change", function(e) {
                    generator.searching();
                });

                CHNPStepTab({
                    containerSelector: "div.m-steptab",
                    beforeNext: function(index) {
                        // FROM 1 To 2
                        var selectItems = generator.dataTablesUtil.getSelectedItems();
                        if (0 === index &&  selectItems === null) {
                            alert("请至少选择一个代码模板！");
                            return false;
                        }

                        // FROM 2 TO 3
                        if (1 === index) {}

                        // FROM 3 TO 4
                        if (2 === index) {
                            // TODO: 显示所有的配置
                            var html = "";
                        }
                    },
                    beforeFinish: function() {
                        var params = $('#tab1').find('form').serializeArray();
                        // FROM 1 To 2
                        var selectItems = generator.dataTablesUtil.getSelectedItems();
                        if (selectItems === null) {
                            alert("请至少选择一个代码模板！");
                            return false;
                        }
                        params.push({name:"templateIds", value:selectItems});
                        // FROM 2 TO 3
                        $.merge(params, $("#tab2").find("form").serializeArray());
                        // FROM 3 TO 4
                        $.merge(params, $("#tab3").find("form").serializeArray());

                        $.ajax({
                            type: "POST",
                            url: "/generator/generate",
                            data: params,
                            dataType: "json",
                            success: function(json) {
                                if (1 === json.returnCode) alert("代码生成成功！");
                                else alert(json.msg);
                            },
                            error: function() {
                                alert("请求失败！");
                            }
                        });
                    }
                });

                generator.dataTablesUtil = new DatatablesUtil();
                generator.dataTablesUtil.init({
                    container: "#tTemplate",
                    serverUrl: "/gnrtemplate/page",
                    columnNames: ["templateName","createTimeString","modifiedTimeString"],
                    operate: {
                        "data": "id",
                        "className": "table-operate",
                        "render": function (data, type, full, meta) {
                            return '&nbsp;<button class="btn-default op-btn-view" type="button" data-url="/gnrtemplate/view?id=' + data + '" onclick="modal($(this))"><i class="fa fa-eye"></i>查看</button>' +
                                    '&nbsp;<button class="btn-primary op-btn-edit" type="button" data-url="/gnrtemplate/edit?id=' + data + '" onclick="modal($(this))"><i class="fa fa-edit"></i>编辑</button>' +
                                    '&nbsp;<button class="btn-danger op-btn-del" type="button" data-url="/gnrtemplate/del?ids=' + data + '" onclick=""><i class="fa fa-trash"></i>删除</button>';
                        }
                    },
                    filter: generator.getFilter,
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

            generator.testDB = function(obj) {
                var form = obj.parents("form");
                $.ajax({
                    type: "POST",
                    url: form.prop("action"),
                    data: form.serializeArray(),
                    dataType: "json",
                    success: function(json) {
                        if (1 === json.returnCode) alert("连接成功！");
                        else alert(json.msg);
                    },
                    error: function() {
                        alert("请求失败！");
                    }
                });
            };
        </script>
    </body>
</html>