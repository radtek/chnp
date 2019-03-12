<div class="modal fade" id="tsrolemodule_view_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="roleId" class="col-sm-3 control-label">角色ID<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="roleId" type="text" class="form-control" placeholder="" value="${tsRoleModule.roleId!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="moduleId" class="col-sm-3 control-label">模块ID<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="moduleId" type="text" class="form-control" placeholder="" value="${tsRoleModule.moduleId!}" disabled>
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
            var tsrolemoduleview = {};

            $('#tsrolemodule_view_modal').on('shown.bs.modal', function() {

            });

            $('#tsrolemodule_view_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                tsrolemoduleview = null;
            });
        })
    </script>
</div>