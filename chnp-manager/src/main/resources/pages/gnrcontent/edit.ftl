<div class="modal fade" id="gnrcontent_edit_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/gnrcontent/update">
                    <input name="id" type="text" value="${gnrContent.id}" hidden>
                    <div class="form-group">
                        <label for="content" class="col-sm-3 control-label">模板内容<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="content" name="content" type="text" class="form-control" placeholder="" value="${gnrContent.content!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createTime" class="col-sm-3 control-label">创建时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="createTime" name="createTime" type="text" class="form-control" placeholder="" value="${gnrContent.createTime!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modifiedTime" class="col-sm-3 control-label">更新时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="modifiedTime" name="modifiedTime" type="text" class="form-control" placeholder="" value="${gnrContent.modifiedTime!}">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="gnrcontent_edit_btn_update" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var gnrContentEdit = {};

            $('#gnrcontent_edit_modal').on('shown.bs.modal', function() {

                $("#gnrcontent_edit_btn_update").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#gnrcontent_edit_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#gnrcontent_edit_modal").modal("hide");
                                if (typeof gnrContent !== "undefined") {
                                gnrContent.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#gnrcontent_edit_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete gnrContentEdit;
            });
        })
    </script>
</div>