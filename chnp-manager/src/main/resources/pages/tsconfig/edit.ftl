<div class="modal fade" id="tsconfig_edit_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalLabel">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/tsconfig/update">
                    <input name="id" type="text" value="${tsConfig.id}" hidden>
                    <div class="form-group">
                        <label for="configName" class="col-sm-3 control-label">配置名称<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="configName" name="configName" type="text" class="form-control" placeholder="" value="${tsConfig.configName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="configKey" class="col-sm-3 control-label">配置标识<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="configKey" name="configKey" type="text" class="form-control" placeholder="" value="${tsConfig.configKey}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="configVal" class="col-sm-3 control-label">配置内容<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="configVal" name="configVal" type="text" class="form-control" placeholder="" value="${tsConfig.configVal}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="configDesc" class="col-sm-3 control-label">配置描述<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <textarea id="configDesc" name="configDesc" class="form-control" rows="3" style="resize: vertical;" placeholder="">${tsConfig.configDesc!}</textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="tsconfig_edit_btn_save" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var tsConfigEdit = {};

            $('#tsconfig_edit_modal').on('shown.bs.modal', function() {

                $("#tsconfig_edit_btn_save").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#tsconfig_edit_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#tsconfig_edit_modal").modal("hide");
                                if (typeof tsConfig.dataTablesUtil !== "undefined") {
                                    tsconfig.dataTablesUtil.
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#tsconfig_edit_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete tsConfigEdit;
            });
        })
    </script>
</div>