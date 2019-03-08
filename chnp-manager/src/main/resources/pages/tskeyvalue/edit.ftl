<div class="modal fade" id="tskeyvalue_edit_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/tskeyvalue/update">
                    <input name="id" type="text" value="${tsKeyValue.id}" hidden>
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">值<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="name" name="name" type="text" class="form-control" placeholder="" value="${tsKeyValue.name!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="chinese" class="col-sm-3 control-label">中文名<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="chinese" name="chinese" type="text" class="form-control" placeholder="" value="${tsKeyValue.chinese!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sort" class="col-sm-3 control-label">排序<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="sort" name="sort" type="text" class="form-control" placeholder="" value="${tsKeyValue.sort!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="descr" class="col-sm-3 control-label">描述<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <textarea id="descr" name="descr" type="text" class="form-control" placeholder="" rows="3">${tsKeyValue.descr!}</textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="tskeyvalue_edit_btn_update" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var tsKeyValueEdit = {};

            $('#tskeyvalue_edit_modal').on('shown.bs.modal', function() {

                $("#tskeyvalue_edit_btn_update").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#tskeyvalue_edit_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#tskeyvalue_edit_modal").modal("hide");
                                if (typeof tsKeyValue !== "undefined") {
                                tsKeyValue.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#tskeyvalue_edit_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete tsKeyValueEdit;
            });
        })
    </script>
</div>