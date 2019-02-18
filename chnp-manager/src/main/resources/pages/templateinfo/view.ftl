<div class="modal fade" id="template_view_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalLabel">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="configName" class="col-sm-3 control-label">模板名称<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="configName" type="text" class="form-control" placeholder="" value="${templateInfo.name!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="configKey" class="col-sm-3 control-label">模板路径<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="configKey" type="text" class="form-control" placeholder="" value="${templateInfo.filePath!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="configVal" class="col-sm-3 control-label">模板引擎<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="configVal" type="text" class="form-control" placeholder="" value="${templateInfo.engine!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="configDesc" class="col-sm-3 control-label">模板内容<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <textarea id="configDesc" class="form-control" wrap="off" rows="10" style="resize: vertical;overflow: auto;" placeholder="" disabled>${templateInfo.template!}</textarea>
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
            var templateView = {};

            $('#template_view_modal').on('shown.bs.modal', function() {

            });

            $('#template_view_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete templateView;
            });
        })
    </script>
</div>