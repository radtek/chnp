<div class="modal fade" id="tsmodule_edit_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalLabel">模态框（Modal）标题</h4>
            </div>
            <div class="modal-body">在这里添加一些文本</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">提交更改</button>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            var tsModuleEdit = {};

            $('#tsmodule_edit_modal').on('shown.bs.modal', function() {
            });

            $('#tsmodule_edit_modal').on('hidden.bs.modal', function() {
                $(this).remove();

                delete tsModuleEdit;
            });
        })
    </script>
</div>