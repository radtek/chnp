<div class="modal fade" id="gnrfilepath_new_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/templateinfo/save">
                    <div class="form-group">
                        <label for="projectId" class="col-sm-3 control-label">项目ID<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="projectId" name="projectId" type="text" class="form-control" placeholder="" value="${gnrFilepath.projectId!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="outputPath" class="col-sm-3 control-label">输出路径<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="outputPath" name="outputPath" type="text" class="form-control" placeholder="" value="${gnrFilepath.outputPath!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="fileSuffix" class="col-sm-3 control-label">文件后缀<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="fileSuffix" name="fileSuffix" type="text" class="form-control" placeholder="" value="${gnrFilepath.fileSuffix!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createUser" class="col-sm-3 control-label">创建者<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="createUser" name="createUser" type="text" class="form-control" placeholder="" value="${gnrFilepath.createUser!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createTime" class="col-sm-3 control-label">创建时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="createTime" name="createTime" type="text" class="form-control" placeholder="" value="${gnrFilepath.createTime!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modifiedTime" class="col-sm-3 control-label">更新时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="modifiedTime" name="modifiedTime" type="text" class="form-control" placeholder="" value="${gnrFilepath.modifiedTime!}">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="gnrfilepath_new_btn_save" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var gnrFilepathNew = {};

            $('#gnrfilepath_new_modal').on('shown.bs.modal', function() {

                $("#gnrfilepath_new_btn_save").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#gnrfilepath_new_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#gnrfilepath_new_modal").modal("hide");
                                if (typeof gnrFilepath !== "undefined") {
                                    gnrFilepath.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#gnrfilepath_new_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete gnrFilepathNew;
            });
        })
    </script>
</div>