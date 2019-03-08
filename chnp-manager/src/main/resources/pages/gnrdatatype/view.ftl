<div class="modal fade" id="gnrdatatype_view_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="projectId" class="col-sm-3 control-label">项目<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="projectId" type="text" class="form-control" placeholder="" value="${gnrDatatype.projectId!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="columnType" class="col-sm-3 control-label">字段类型<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="columnType" type="text" class="form-control" placeholder="" value="${gnrDatatype.columnType!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="targetType" class="col-sm-3 control-label">转换类型<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="targetType" type="text" class="form-control" placeholder="" value="${gnrDatatype.targetType!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="targetEnvir" class="col-sm-3 control-label">转换环境<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="targetEnvir" type="text" class="form-control" placeholder="" value="${gnrDatatype.targetEnvir!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createUser" class="col-sm-3 control-label">创建者<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="createUser" type="text" class="form-control" placeholder="" value="${gnrDatatype.createUser!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createTime" class="col-sm-3 control-label">创建时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="createTime" type="text" class="form-control" placeholder="" value="${gnrDatatype.createTime!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modifiedTime" class="col-sm-3 control-label">更新时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="modifiedTime" type="text" class="form-control" placeholder="" value="${gnrDatatype.modifiedTime!}" disabled>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var gnrDatatypeView = {};

            $('#gnrdatatype_view_modal').on('shown.bs.modal', function() {

            });

            $('#gnrdatatype_view_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete gnrDatatypeView;
            });
        })
    </script>
</div>