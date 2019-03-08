<div class="modal fade" id="gnrdatatype_new_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/gnrdatatype/save">
                    <input name="projectId" value="${gnrDatatype.projectId}" hidden>
                    <div class="form-group">
                        <label for="columnType" class="col-sm-3 control-label">字段类型<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="columnType" name="columnType" type="text" class="form-control" placeholder="" value="${gnrDatatype.columnType!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="targetType" class="col-sm-3 control-label">转换类型<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="targetType" name="targetType" type="text" class="form-control" placeholder="" value="${gnrDatatype.targetType!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="targetEnvir" class="col-sm-3 control-label">转换环境<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="targetEnvir" name="targetEnvir" type="text" class="form-control" placeholder="" value="${gnrDatatype.targetEnvir!}">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="gnrdatatype_new_btn_save" type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var gnrDatatypeNew = {};

            $('#gnrdatatype_new_modal').on('shown.bs.modal', function() {

                $("#gnrdatatype_new_btn_save").on("click", function(e) {
                    e.preventDefault();

                    var form = $("#gnrdatatype_new_modal form");
                    $.ajax({
                        type: "POST",
                        url: form.prop("action"),
                        data: form.serializeArray(),
                        dataType: "json",
                        success: function(json) {
                            if (1 === json.returnCode) {
                                $("#gnrdatatype_new_modal").modal("hide");
                                if (typeof gnrDatatype !== "undefined") {
                                    gnrDatatype.searching();
                                }
                            }else alert(json.msg);
                        },
                        error: function() {
                            alert("请求失败");
                        }
                    })
                });

            });

            $('#gnrdatatype_new_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete gnrDatatypeNew;
            });
        })
    </script>
</div>