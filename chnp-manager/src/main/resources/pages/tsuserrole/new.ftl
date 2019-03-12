<div class="modal fade" id="tsuserrole_new_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/templateinfo/save">
                    <div class="form-group">
                        <label for="userId" class="col-sm-3 control-label">用户ID<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="userId" name="userId" type="text" class="form-control" placeholder="" value="${tsUserRole.userId!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="roleId" class="col-sm-3 control-label">角色ID<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="roleId" name="roleId" type="text" class="form-control" placeholder="" value="${tsUserRole.roleId!}">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="tsuserrole_new_btn_save" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var tsuserrolenew = {};

            $('#tsuserrole_new_modal').on('shown.bs.modal', function() {

                $("#tsuserrole_new_btn_save").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#tsuserrole_new_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#tsuserrole_new_modal").modal("hide");
                                if (typeof tsuserrole !== "undefined") {
                                    tsuserrole.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#tsuserrole_new_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                tsuserrolenew;
            });
        })
    </script>
</div>