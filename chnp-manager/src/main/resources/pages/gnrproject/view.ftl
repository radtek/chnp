<div class="modal fade" id="gnrproject_view_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="projectName" class="col-sm-3 control-label">项目名称<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="projectName" type="text" class="form-control" placeholder="" value="${gnrProject.projectName!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createUser" class="col-sm-3 control-label">创建者<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="createUser" type="text" class="form-control" placeholder="" value="${gnrProject.createUser!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createTime" class="col-sm-3 control-label">创建时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="createTime" type="text" class="form-control" placeholder="" value="${gnrProject.createTime!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modifiedTime" class="col-sm-3 control-label">更新时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="modifiedTime" type="text" class="form-control" placeholder="" value="${gnrProject.modifiedTime!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="projectDesc" class="col-sm-3 control-label">项目描述<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="projectDesc" type="text" class="form-control" placeholder="" value="${gnrProject.projectDesc!}" disabled>
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
            var gnrProjectView = {};

            $('#gnrproject_view_modal').on('shown.bs.modal', function() {

            });

            $('#gnrproject_view_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete gnrProjectView;
            });
        })
    </script>
</div>