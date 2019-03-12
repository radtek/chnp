<div class="modal fade" id="tsplugin_new_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/templateinfo/save">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">插件名称<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="name" name="name" type="text" class="form-control" placeholder="" value="${tsPlugin.name!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="version" class="col-sm-3 control-label">插件版本<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="version" name="version" type="text" class="form-control" placeholder="" value="${tsPlugin.version!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="updateTime" class="col-sm-3 control-label">更新时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="updateTime" name="updateTime" type="text" class="form-control" placeholder="" value="${tsPlugin.updateTime!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-3 control-label">插件类型：1-前端，2-后端<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="type" name="type" type="text" class="form-control" placeholder="" value="${tsPlugin.type!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="descr" class="col-sm-3 control-label">插件描述<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="descr" name="descr" type="text" class="form-control" placeholder="" value="${tsPlugin.descr!}">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="tsplugin_new_btn_save" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var tspluginnew = {};

            $('#tsplugin_new_modal').on('shown.bs.modal', function() {

                $("#tsplugin_new_btn_save").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#tsplugin_new_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#tsplugin_new_modal").modal("hide");
                                if (typeof tsplugin !== "undefined") {
                                    tsplugin.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#tsplugin_new_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                tspluginnew;
            });
        })
    </script>
</div>