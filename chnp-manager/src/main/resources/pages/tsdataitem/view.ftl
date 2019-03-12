<div class="modal fade" id="tsdataitem_view_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalLabel">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="dataName" class="col-sm-3 control-label">字典名称：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="" value="${(tsDataItem.tsData.dataName)!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="itemKey" class="col-sm-3 control-label">键值：</label>
                        <div class="col-sm-7">
                            <input id="itemKey" type="text" class="form-control" placeholder="" value="${tsDataItem.itemKey!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="itemValue" class="col-sm-3 control-label">键值：</label>
                        <div class="col-sm-7">
                            <input id="itemValue" type="text" class="form-control" placeholder="" value="${tsDataItem.itemValue!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="itemSort" class="col-sm-3 control-label">排序：</label>
                        <div class="col-sm-7">
                            <input id="itemSort" type="text" class="form-control" placeholder="" value="${tsDataItem.itemSort!}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="itemDesc" class="col-sm-3 control-label">描述<i class="fa fa fa-question-circle" title=""></i>：</label>
                        <div class="col-sm-7">
                            <textarea id="itemDesc" type="text" class="form-control" placeholder="" rows="3" disabled>${tsDataItem.itemDesc!}</textarea>
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
            var tsdataitemView = {};

            $('#tsdataitem_view_modal').on('shown.bs.modal', function() {

            });

            $('#tsdataitem_view_modal').on('hidden.bs.modal', function() {
                $(this).remove();
                delete tsdataitemView;
            });
        })
    </script>
</div>