<div class="modal fade" id="tsrole_new_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/tsrole/save">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">中文名称：</label>
                        <div class="col-sm-7">
                            <input id="name" name="name" type="text" class="form-control" placeholder="" value="${tsRole.name!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="orgId" class="col-sm-3 control-label">隶属组织：</label>
                        <div class="col-sm-7">
                            <input id="orgId" name="orgId" type="text" class="form-control" placeholder="" value="${tsRole.orgId!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="status" class="col-sm-3 control-label">角色状态：</label>
                        <div class="col-sm-7">
                            <input id="status" name="status" type="text" class="form-control" placeholder="" value="${tsRole.status!}">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="tsrole_new_btn_save" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var tsrolenew = {};

            $('#tsrole_new_modal').on('shown.bs.modal', function() {

                $("#tsrole_new_btn_save").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#tsrole_new_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#tsrole_new_modal").modal("hide");
                                if (typeof tsrole !== "undefined") {
                                    tsrole.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#tsrole_new_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                tsrolenew;
            });
        })
    </script>
</div>