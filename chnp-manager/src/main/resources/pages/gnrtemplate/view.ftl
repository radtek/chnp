<div class="modal fade" id="gnrtemplate_view_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
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
                            <input id="projectId" type="text" class="form-control" placeholder="" value="${gnrTemplate.gnrProject.projectName!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="templateName" class="col-sm-3 control-label">模板名称<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="templateName" type="text" class="form-control" placeholder="" value="${gnrTemplate.templateName!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createTime" class="col-sm-3 control-label">创建时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="createTime" type="text" class="form-control" placeholder="" value="${gnrTemplate.createTimeString!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modifiedTime" class="col-sm-3 control-label">更新时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="modifiedTime" type="text" class="form-control" placeholder="" value="${gnrTemplate.modifiedTimeString!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="contentId" class="col-sm-3 control-label">模板内容<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <textarea id="content" type="text" class="form-control" placeholder="" rows="10" disabled>${(gnrTemplate.gnrContent.content)!}</textarea>
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
            var gnrTemplateView = {};

            $('#gnrtemplate_view_modal').on('shown.bs.modal', function() {

            });

            $('#gnrtemplate_view_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete gnrTemplateView;
            });
        })
    </script>
</div>