<div class="modal fade" id="tsuser_edit_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/tsuser/update">
                    <input name="id" type="text" value="${tsUser.id}" hidden>
                    <div class="form-group">
                        <label for="userName" class="col-sm-3 control-label">登陆账号<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="userName" name="userName" type="text" class="form-control" placeholder="" value="${tsUser.userName!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userNick" class="col-sm-3 control-label">用户昵称<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="userNick" name="userNick" type="text" class="form-control" placeholder="" value="${tsUser.userNick!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="linkEmail" class="col-sm-3 control-label">对外邮箱<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="linkEmail" name="linkEmail" type="text" class="form-control" placeholder="" value="${tsUser.linkEmail!}">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="tsuser_edit_btn_update" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var tsuseredit = {};

            $('#tsuser_edit_modal').on('shown.bs.modal', function() {

                $("#tsuser_edit_btn_update").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#tsuser_edit_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#tsuser_edit_modal").modal("hide");
                                if (typeof tsuser !== "undefined") {
                                    tsuser.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#tsuser_edit_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                tsuseredit = null;
            });
        })
    </script>
</div>