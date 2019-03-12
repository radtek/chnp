<div class="modal fade" id="tsplugin_view_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">插件名称<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="name" type="text" class="form-control" placeholder="" value="${tsPlugin.name!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="version" class="col-sm-3 control-label">插件版本<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="version" type="text" class="form-control" placeholder="" value="${tsPlugin.version!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="updateTime" class="col-sm-3 control-label">更新时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="updateTime" type="text" class="form-control" placeholder="" value="${tsPlugin.updateTime!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-3 control-label">插件类型：1-前端，2-后端<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="type" type="text" class="form-control" placeholder="" value="${tsPlugin.type!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="descr" class="col-sm-3 control-label">插件描述<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="descr" type="text" class="form-control" placeholder="" value="${tsPlugin.descr!}" disabled>
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
            var tspluginview = {};

            $('#tsplugin_view_modal').on('shown.bs.modal', function() {

            });

            $('#tsplugin_view_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                tspluginview = null;
            });
        })
    </script>
</div>