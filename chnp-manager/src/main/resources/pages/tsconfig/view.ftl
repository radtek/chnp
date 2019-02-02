<div class="modal fade" id="tsconfig_view_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalLabel">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="configName" class="col-sm-3 control-label">配置名称<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="configName" type="text" class="form-control" placeholder="" value="${tsConfig.configName}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="configKey" class="col-sm-3 control-label">配置标识<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="configKey" type="text" class="form-control" placeholder="" value="${tsConfig.configKey}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="configVal" class="col-sm-3 control-label">配置内容<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="configVal" type="text" class="form-control" placeholder="" value="${tsConfig.configVal}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="configDesc" class="col-sm-3 control-label">配置描述<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <textarea id="configDesc" class="form-control" rows="3" style="resize: vertical;" placeholder="" disabled>${tsConfig.configDesc!}</textarea>
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
            var tsConfigView = {};

            $('#tsconfig_view_modal').on('shown.bs.modal', function() {

            });

            $('#tsconfig_view_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete tsConfigView;
            });
        })
    </script>
</div>