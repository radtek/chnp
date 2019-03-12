<div class="modal fade" id="tsdataitem_new_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/tsdataitem/save">
                    <input name="dataId" value="${tsDataItem.dataId}" hidden>
                    <div class="form-group">
                        <label for="itemKey" class="col-sm-3 control-label">键名：</label>
                        <div class="col-sm-7">
                            <input id="itemKey" name="itemKey" type="text" class="form-control" placeholder="" value="${tsDataItem.itemKey!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="chinese" class="col-sm-3 control-label">键值：</label>
                        <div class="col-sm-7">
                            <input id="itemValue" name="itemValue" type="text" class="form-control" placeholder="" value="${tsDataItem.itemValue!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sort" class="col-sm-3 control-label">排序：</label>
                        <div class="col-sm-7">
                            <input id="itemSort" name="itemSort" type="text" class="form-control" placeholder="" value="${tsDataItem.itemSort!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="itemDesc" class="col-sm-3 control-label">描述<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <textarea id="itemDesc" name="itemDesc" type="text" class="form-control" placeholder="" rows="3">${tsDataItem.itemDesc!}</textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="tsdataitem_new_btn_save" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var tsdataitemnew = {};

            $('#tsdataitem_new_modal').on('shown.bs.modal', function() {

                $("#tsdataitem_new_btn_save").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#tsdataitem_new_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#tsdataitem_new_modal").modal("hide");
                                if (typeof tsdataitem !== "undefined") {
                                    tsdataitem.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#tsdataitem_new_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                tsdataitemnew = null;
            });
        })
    </script>
</div>