<div class="modal fade" id="tsuser_view_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="userName" class="col-sm-3 control-label">登陆账号：</label>
                        <div class="col-sm-7">
                            <input id="userName" type="text" class="form-control" placeholder="" value="${tsUser.userName!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userNick" class="col-sm-3 control-label">用户昵称：</label>
                        <div class="col-sm-7">
                            <input id="userNick" type="text" class="form-control" placeholder="" value="${tsUser.userNick!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="linkEmail" class="col-sm-3 control-label">对外邮箱：</label>
                        <div class="col-sm-7">
                            <input id="linkEmail" type="text" class="form-control" placeholder="" value="${tsUser.linkEmail!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="registerTime" class="col-sm-3 control-label">注册时间：</label>
                        <div class="col-sm-7">
                            <input id="registerTime" type="text" class="form-control" placeholder="" value="${tsUser.registerTimeString!}" disabled>
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
            var tsuserview = {};

            $('#tsuser_view_modal').on('shown.bs.modal', function() {

            });

            $('#tsuser_view_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                tsuserview = null;
            });
        })
    </script>
</div>