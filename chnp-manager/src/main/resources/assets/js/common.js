
/**<p>请求并显示模态框</p>
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