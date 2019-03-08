<div class="modal fade" id="tskey_new_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/templateinfo/save">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">键名<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="name" name="name" type="text" class="form-control" placeholder="" value="${tsKey.name!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="chinese" class="col-sm-3 control-label">中文名<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="chinese" name="chinese" type="text" class="form-control" placeholder="" value="${tsKey.chinese!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="descr" class="col-sm-3 control-label">描述<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <textarea id="descr" name="descr" class="form-control" rows="3" placeholder="">${tsKey.descr!}</textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="tskey_new_btn_save" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var tsKeyNew = {};

            $('#tskey_new_modal').on('shown.bs.modal', function() {

                $("#tskey_new_btn_save").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#tskey_new_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#tskey_new_modal").modal("hide");
                                if (typeof tsKey !== "undefined") {
                                tsKey.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#tskey_new_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete tsKeyNew;
            });
        })
    </script>
</div>