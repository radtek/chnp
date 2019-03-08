<div class="modal fade" id="gnrproject_edit_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/gnrproject/update">
                    <input name="id" type="text" value="${gnrProject.id}" hidden>
                    <div class="form-group">
                        <label for="projectName" class="col-sm-3 control-label">项目名称<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="projectName" name="projectName" type="text" class="form-control" placeholder="" value="${gnrProject.projectName!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createUser" class="col-sm-3 control-label">创建者<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="createUser" name="createUser" type="text" class="form-control" placeholder="" value="${gnrProject.createUser!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createTime" class="col-sm-3 control-label">创建时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="createTime" name="createTime" type="text" class="form-control" placeholder="" value="${gnrProject.createTime!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modifiedTime" class="col-sm-3 control-label">更新时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="modifiedTime" name="modifiedTime" type="text" class="form-control" placeholder="" value="${gnrProject.modifiedTime!}">
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
                <button id="gnrproject_edit_btn_update" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var gnrProjectEdit = {};

            $('#gnrproject_edit_modal').on('shown.bs.modal', function() {

                $("#gnrproject_edit_btn_update").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#gnrproject_edit_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#gnrproject_edit_modal").modal("hide");
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

            $('#gnrproject_edit_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete gnrProjectEdit;
            });
        })
    </script>
</div>