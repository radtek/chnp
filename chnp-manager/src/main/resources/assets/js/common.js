
function goto(obj) {
    window.location.href = obj.data("url");
}


/**<p>公共脚本</p>
 *
 * @author chngzhen@outlook.com
 * @date 2019-02-22
 *
 */

function drag(target) {
    var oevent = event;

    var offset = $(target).offset();
    var distanceX = oevent.clientX - offset.left;
    var distanceY = oevent.clientY - offset.top;

    document.onmousemove = function(ev) {
        var oevent = ev || event;
        $(target).css('left', oevent.clientX - distanceX + 'px');
        $(target).css('top', oevent.clientY - distanceY + 'px');
    };

    document.onmouseup = function() {
        document.onmousemove = null;
        document.onmouseup = null;
    };
}


/**<p>表单Enter事件触发按钮单击事件</p>
 * <p>
 *     注意：表单中存在多个按钮时，可设置表单的data-cmt属性指向需要触发的按钮。
 * </p>
 *
 * @attribute data-cmt 提交按钮的ID
 */
$('form').on('submit', function(e) {
    e.preventDefault();

    var btnCommit = $(this).data('cmt');
    if (btnCommit === undefined)
        $(this).find('button')[0].click();
    else $('#' + btnCommit).click();
});

/**<p>请求并显示模态框</p>
 * <p>
 *     注意：该方法依赖Bootstrap或Modal。
 * </p>
 *
 * @param obj DOM对象。须存在data-url属性
 */
function modal(obj) {
    if (obj.data("url")) {
        $.get(obj.data("url"), null, function(html) {
            $(html).modal();
        }, "html");
    }
}