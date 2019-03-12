<div class="modal fade" id="tsuser_new_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/tsuser/save">
                    <div class="form-group">
                        <label for="userName" class="col-sm-3 control-label">登陆账号<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="userName" name="userName" type="text" class="form-control" placeholder="" value="${tsUser.userName!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userPswd" class="col-sm-3 control-label">登陆密码<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="userPswd" name="userPswd" type="text" class="form-control" placeholder="" value="${tsUser.userPswd!}">
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
                <button id="tsuser_new_btn_save" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var tsusernew = {};

            $('#tsuser_new_modal').on('shown.bs.modal', function() {

                $("#tsuser_new_btn_save").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#tsuser_new_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#tsuser_new_modal").modal("hide");
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

            $('#tsuser_new_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                tsusernew = null;
            });
        })
    </script>
</div>