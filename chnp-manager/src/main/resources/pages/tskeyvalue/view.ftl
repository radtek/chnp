<div class="modal fade" id="tskeyvalue_view_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">键名<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="" value="${(tsKeyValue.tsKey.chinese)!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">值<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="name" type="text" class="form-control" placeholder="" value="${tsKeyValue.name!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="chinese" class="col-sm-3 control-label">中文名<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="chinese" type="text" class="form-control" placeholder="" value="${tsKeyValue.chinese!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sort" class="col-sm-3 control-label">排序<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <input id="sort" type="text" class="form-control" placeholder="" value="${tsKeyValue.sort!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="descr" class="col-sm-3 control-label">描述<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <textarea id="descr" type="text" class="form-control" placeholder="" rows="3" disabled>${tsKeyValue.descr!}</textarea>
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
            var tsKeyValueView = {};

            $('#tskeyvalue_view_modal').on('shown.bs.modal', function() {

            });

            $('#tskeyvalue_view_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete tsKeyValueView;
            });
        })
    </script>
</div>