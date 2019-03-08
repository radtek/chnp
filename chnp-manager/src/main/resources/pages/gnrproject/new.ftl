<div class="modal fade" id="gnrproject_new_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/gnrproject/save">
                    <div class="form-group">
                        <label for="projectName" class="col-sm-3 control-label">项目名称<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="projectName" name="projectName" type="text" class="form-control" placeholder="" value="${gnrProject.projectName!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="projectDesc" class="col-sm-3 control-label">项目描述<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="projectDesc" name="projectDesc" type="text" class="form-control" placeholder="" value="${gnrProject.projectDesc!}">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="gnrproject_new_btn_save" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var gnrProjectNew = {};

            $('#gnrproject_new_modal').on('shown.bs.modal', function() {

                $("#gnrproject_new_btn_save").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#gnrproject_new_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#gnrproject_new_modal").modal("hide");
                                if (typeof gnrProject !== "undefined") {
                                    gnrProject.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#gnrproject_new_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete gnrProjectNew;
            });
        })
    </script>
</div>