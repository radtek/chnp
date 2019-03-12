<div class="modal fade" id="tsdata_edit_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/tsdata/update">
                    <input name="id" type="text" value="${tsData.id}" hidden>
                    <div class="form-group">
                        <label for="dataKey" class="col-sm-3 control-label">字典名称：</label>
                        <div class="col-sm-7">
                            <input id="dataKey" name="dataKey" type="text" class="form-control" placeholder="" value="${tsData.dataKey!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dataName" class="col-sm-3 control-label">中文名：</label>
                        <div class="col-sm-7">
                            <input id="dataName" name="dataName" type="text" class="form-control" placeholder="" value="${tsData.dataName!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="descr" class="col-sm-3 control-label">描述<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <textarea id="descr" name="descr" class="form-control" rows="3" placeholder="">${tsData.descr!}</textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="tsdata_edit_btn_update" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var tsdataedit = {};

            $('#tsdata_edit_modal').on('shown.bs.modal', function() {

                $("#tsdata_edit_btn_update").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#tsdata_edit_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#tsdata_edit_modal").modal("hide");
                                if (typeof tsdata !== "undefined") {
                                    tsdata.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#tsdata_edit_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                tsdataedit = null;
            });
        })
    </script>
</div>