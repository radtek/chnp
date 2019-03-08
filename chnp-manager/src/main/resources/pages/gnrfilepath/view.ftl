<div class="modal fade" id="gnrfilepath_view_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="projectId" class="col-sm-3 control-label">项目ID<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="projectId" type="text" class="form-control" placeholder="" value="${gnrFilepath.projectId!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="outputPath" class="col-sm-3 control-label">输出路径<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="outputPath" type="text" class="form-control" placeholder="" value="${gnrFilepath.outputPath!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="fileSuffix" class="col-sm-3 control-label">文件后缀<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="fileSuffix" type="text" class="form-control" placeholder="" value="${gnrFilepath.fileSuffix!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createUser" class="col-sm-3 control-label">创建者<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="createUser" type="text" class="form-control" placeholder="" value="${gnrFilepath.createUser!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createTime" class="col-sm-3 control-label">创建时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="createTime" type="text" class="form-control" placeholder="" value="${gnrFilepath.createTime!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modifiedTime" class="col-sm-3 control-label">更新时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="modifiedTime" type="text" class="form-control" placeholder="" value="${gnrFilepath.modifiedTime!}" disabled>
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
            var gnrFilepathView = {};

            $('#gnrfilepath_view_modal').on('shown.bs.modal', function() {

            });

            $('#gnrfilepath_view_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete gnrFilepathView;
            });
        })
    </script>
</div>