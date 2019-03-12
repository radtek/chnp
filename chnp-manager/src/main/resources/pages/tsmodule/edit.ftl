<div class="modal fade" id="tsmodule_edit_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/tsmodule/update">
                    <input name="id" type="text" value="${tsModule.id}" hidden>
                    <div class="form-group">
                        <label for="parentId" class="col-sm-2 control-label">父节点：</label>
                        <div class="col-sm-4">
                            <select id="parentId" name="parentId" class="form-control">
                            <#list parentTree as parent>
                                <option value="${parent.id}" <#if parent.id == tsModule.parentId>selected="selected"</#if>>${parent.name}</option>
                            </#list>
                            </select>
                        </div>
                        <label for="type" class="col-sm-2 control-label">模块类型：</label>
                        <div class="col-sm-4">
                            <select id="type" name="type" class="form-control">
                            <#list moduleTypes as type>
                                <option value="${type.name}" <#if type.name == tsModule.type?c></#if>>${type.chinese}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">模块名称：</label>
                        <div class="col-sm-4">
                            <input id="name" name="name" type="text" class="form-control" placeholder="" value="${tsModule.name!}">
                        </div>
                        <label for="authName" class="col-sm-2 control-label">权限名称：</label>
                        <div class="col-sm-4">
                            <input id="authName" name="authName" type="text" class="form-control" placeholder="" value="${tsModule.authName!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="url" class="col-sm-2 control-label">链接地址：</label>
                        <div class="col-sm-4">
                            <input id="url" name="url" type="text" class="form-control" placeholder="" value="${tsModule.url!}">
                        </div>
                        <label for="icon" class="col-sm-2 control-label">图标：</label>
                        <div class="col-sm-4">
                            <input id="icon" name="icon" type="text" class="form-control" placeholder="" value="${tsModule.icon!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sort" class="col-sm-2 control-label">排序：</label>
                        <div class="col-sm-4">
                            <input id="sort" name="sort" type="text" class="form-control" placeholder="" value="${tsModule.sort!}">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="tsmodule_edit_btn_update" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var tsmoduleedit = {};

            $('#tsmodule_edit_modal').on('shown.bs.modal', function() {

                $("#tsmodule_edit_btn_update").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#tsmodule_edit_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#tsmodule_edit_modal").modal("hide");
                                if (typeof tsmodule !== "undefined") {
                                    tsmodule.initTree();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#tsmodule_edit_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                tsmoduleedit = null;
            });
        })
    </script>
</div>