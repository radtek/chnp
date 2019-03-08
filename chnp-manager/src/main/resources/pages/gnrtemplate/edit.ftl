<div class="modal fade" id="gnrtemplate_edit_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/gnrtemplate/update">
                    <input name="id" type="text" value="${gnrTemplate.id}" hidden>
                    <div class="form-group">
                        <label for="templateName" class="col-sm-3 control-label">模板名称<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="templateName" name="templateName" type="text" class="form-control" placeholder="" value="${gnrTemplate.templateName!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="templateName" class="col-sm-3 control-label">模板内容<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <textarea id="content" name="content" type="text" class="form-control" placeholder="" rows="10">${(gnrTemplate.gnrContent.content)!}</textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="gnrtemplate_edit_btn_update" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var gnrTemplateEdit = {};

            $('#gnrtemplate_edit_modal').on('shown.bs.modal', function() {

                $("#gnrtemplate_edit_btn_update").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#gnrtemplate_edit_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#gnrtemplate_edit_modal").modal("hide");
                                if (typeof gnrTemplate !== "undefined") {
                                    gnrTemplate.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#gnrtemplate_edit_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete gnrTemplateEdit;
            });
        })
    </script>
</div>