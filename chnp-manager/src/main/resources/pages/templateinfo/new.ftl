<div class="modal fade" id="template_new_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/templateinfo/save">
                    <div class="form-group">
                        <label for="configName" class="col-sm-3 control-label">模板名称<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="name" name="name" type="text" class="form-control" placeholder="" value="${templateInfo.name!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="configKey" class="col-sm-3 control-label">模板路径<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="filePath" name="filePath" type="text" class="form-control" placeholder="" value="${templateInfo.filePath!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="configVal" class="col-sm-3 control-label">模板引擎<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="engine" name="engine" type="text" class="form-control" placeholder="" value="${templateInfo.engine!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="configDesc" class="col-sm-3 control-label">模板内容<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <textarea id="templateInfo" name="templateInfo" class="form-control" wrap="off" rows="10" style="resize: vertical;overflow: auto;" placeholder="">${templateInfo.templateInfo!}</textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="template_new_btn_save" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var templateInfoNew = {};

            $('#template_new_modal').on('shown.bs.modal', function() {

                $("#template_new_btn_save").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#template_new_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#template_new_modal").modal("hide");
                                if (typeof templateInfo !== "undefined") {
                                    templateInfo.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#template_new_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete templateInfoNew;
            });
        })
    </script>
</div>