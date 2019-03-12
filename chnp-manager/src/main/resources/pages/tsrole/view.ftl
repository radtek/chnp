<div class="modal fade" id="tsrole_view_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">中文名称：</label>
                        <div class="col-sm-7">
                            <input id="name" type="text" class="form-control" placeholder="" value="${tsRole.name!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="orgId" class="col-sm-3 control-label">隶属组织：</label>
                        <div class="col-sm-7">
                            <input id="orgId" type="text" class="form-control" placeholder="" value="${tsRole.orgId!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="status" class="col-sm-3 control-label">角色状态：</label>
                        <div class="col-sm-7">
                            <input id="status" type="text" class="form-control" placeholder="" value="${tsRole.status!}" disabled>
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
            var tsroleview = {};

            $('#tsrole_view_modal').on('shown.bs.modal', function() {

            });

            $('#tsrole_view_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                tsroleview = null;
            });
        })
    </script>
</div>