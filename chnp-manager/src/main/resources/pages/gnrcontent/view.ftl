<div class="modal fade" id="gnrcontent_view_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="content" class="col-sm-3 control-label">模板内容<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="content" type="text" class="form-control" placeholder="" value="${gnrContent.content!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createTime" class="col-sm-3 control-label">创建时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="createTime" type="text" class="form-control" placeholder="" value="${gnrContent.createTime!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modifiedTime" class="col-sm-3 control-label">更新时间<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="modifiedTime" type="text" class="form-control" placeholder="" value="${gnrContent.modifiedTime!}" disabled>
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
            var gnrContentView = {};

            $('#gnrcontent_view_modal').on('shown.bs.modal', function() {

            });

            $('#gnrcontent_view_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete gnrContentView;
            });
        })
    </script>
</div>