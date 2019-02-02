var DatatablesUtil = function() {
    var datatables;

    var defaultOptions = {
        container: "table.dataTable",
        columnNames: [],
        idTag: "id",
        chk: {
            enable: true,
            name: "items",
            data: "id"
        },
        operate: null,
        dataTable: {
            dom: 'rt<"ilp"ilp>',
            searching: false,
            serverSide: true,
            ajax: {
                "url": "",
                "type": "POST",
                "data": function(d) {
                    var nd = [];
                    nd.push({name:"search", value:d.search.value});
                    nd.push({name:"draw", value:d.draw});
                    nd.push({name:"start", value:d.start});
                    nd.push({name:"length", value:d.length});
                    //nd.push({name:"order", value:d.order});
                    $.extend(nd, params);
                    return nd;
                }
            },
            language: {
                "processing": "处理中...",
                "lengthMenu": "_MENU_",
                "zeroRecords": "没有匹配结果",
                "info": "显示第 _START_ 至 _END_ 条数据，共 _TOTAL_ 条",
                "infoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "infoFiltered": "(由 _MAX_ 项结果过滤)",
                "infoPostFix": "",
                "search": "搜索:",
                "url": "",
                "emptyTable": "表中数据为空",
                "loadingRecords": "载入中...",
                "infoThousands": ",",
                "paginate": {
                    "first": "<i class='fa fa-angle-double-left'></i>",
                    "previous": "<i class='fa fa-angle-left'></i>",
                    "next": "<i class='fa fa-angle-right'></i>",
                    "last": "<i class='fa fa-angle-double-right'></i>"
                },
                "aria": {
                    "sortAscending": ": 以升序排列此列",
                    "sortDescending": ": 以降序排列此列"
                }
            }
        }
    };

    return {
        init: function(options) {
            var settings = $.extend(true, defaultOptions, options);

            if (settings.chk.enable) {
                $("table.table thead tr th:first-child input[type=checkbox]").on("click", function () {
                    var ischecked = $(this).is(":checked");
                    if (ischecked) {
                        $("table.table tr td:first-child input[type=checkbox]").prop({checked: true});
                    } else {
                        $("table.table tr td:first-child input[type=checkbox]").prop({checked: false});
                    }
                });

                $("table.table tfoot tr td:first-child input[type=checkbox]").on("click", function () {
                    var ischecked = $(this).is(":checked");
                    if (ischecked) {
                        $("table.table thead tr th:first-child input[type=checkbox]").prop({checked: true});
                        $("table.table tbody tr td:first-child input[type=checkbox]").prop({checked: true});
                    } else {
                        $("table.table thead tr th:first-child input[type=checkbox]").prop({checked: false});
                        $("table.table tbody tr td:first-child input[type=checkbox]").prop({checked: false});
                    }
                });
            }
            settings.dataTable.ajax.url = settings.serverUrl;

            settings.dataTable.columns = (function() {
                var cols = [];
                if (settings.chk.enable) {
                    cols.push({
                        "data": settings.chk.data,
                        "render": function(data, type, full, meta) {
                            return '<input name="' + settings.chk.name + '" type="checkbox" data-id="' + data + '"/>';
                        }
                    });
                }

                $.each(settings.columnNames, function(i, e) {
                    cols.push({
                        "data": e
                    });
                });

                if (null !== settings.operate) {
                    cols.push(settings.operate);
                }
                return cols;
            })();

            // 实例化DataTables
            datatables = $(settings.container).DataTable(settings.dataTable);

            // 绑定DataTables的重画事件
            datatables.on("draw", function() {
                if (settings.chk.enable) {
                    // 行选中状态变更操作：
                    //   若行状态操作为取消，则取消全选按钮的选中状态
                    //   若行状态操作为选中，且所有行状态均为选中，则选中全选按钮
                    $("table.table tbody tr td:first-child input[type=checkbox]").on("click", function () {
                        if ($(this).is(":checked")) {
                            var checkedAll = true;
                            $.each($("table.table tbody tr td:first-child input[type=checkbox]"), function (i, e) {
                                checkedAll &= $(e).is(":checked");
                                if (!checkedAll) return false;
                            });
                            if (checkedAll) {
                                $("table.table thead tr th:first-child input[type=checkbox]").prop("checked", true);
                                $("table.table tfoot tr td:first-child input[type=checkbox]").prop("checked", true);
                            }
                        } else {
                            $("table.table thead tr th:first-child input[type=checkbox]").prop("checked", false);
                            $("table.table tfoot tr td:first-child input[type=checkbox]").prop("checked", false);
                        }
                    });
                }

            });
        },
        getDataTable: function () {
            return datatables;
        }
    };
};