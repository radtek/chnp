var DatatablesUtil = function() {
    var datatables;
    var additionalParams = [];

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
                "type": "POST"
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
            defaultOptions = $.extend(true, defaultOptions, options);

            if (defaultOptions.chk.enable) {
                $(defaultOptions.container).find("thead tr th:first-child input[type=checkbox]").on("click", function () {
                    var ischecked = $(this).is(":checked");
                    if (ischecked) {
                        $(defaultOptions.container).find("tr td:first-child input[type=checkbox]").prop({checked: true});
                    } else {
                        $(defaultOptions.container).find("tr td:first-child input[type=checkbox]").prop({checked: false});
                    }
                });

                $(defaultOptions.container).find("tfoot tr td:first-child input[type=checkbox]").on("click", function () {
                    var ischecked = $(this).is(":checked");
                    if (ischecked) {
                        $(defaultOptions.container).find("thead tr th:first-child input[type=checkbox]").prop({checked: true});
                        $(defaultOptions.container).find("tbody tr td:first-child input[type=checkbox]").prop({checked: true});
                    } else {
                        $(defaultOptions.container).find("thead tr th:first-child input[type=checkbox]").prop({checked: false});
                        $(defaultOptions.container).find("tbody tr td:first-child input[type=checkbox]").prop({checked: false});
                    }
                });
            }
            defaultOptions.dataTable.ajax.url = defaultOptions.serverUrl;
            defaultOptions.dataTable.ajax.data = function(d) {
                var nd = [];
                nd.push({name:"search", value:d.search.value});
                nd.push({name:"draw", value:d.draw});
                nd.push({name:"pageNo", value:d.start / d.length + 1});
                nd.push({name:"length", value:d.length});
                if (defaultOptions.filter instanceof Function) {
                    $.extend(nd, defaultOptions.filter());
                }
                $.extend(nd, additionalParams);
                return nd;
            };

            defaultOptions.dataTable.columns = (function() {
                var cols = [];
                if (defaultOptions.chk.enable) {
                    cols.push({
                        "data": defaultOptions.chk.data,
                        "render": function(data, type, full, meta) {
                            return '<input name="' + defaultOptions.chk.name + '" type="checkbox" data-id="' + data + '"/>';
                        }
                    });
                }

                $.each(defaultOptions.columnNames, function(i, e) {
                    cols.push({
                        "data": e
                    });
                });

                if (null !== defaultOptions.operate) {
                    cols.push(defaultOptions.operate);
                }
                return cols;
            })();

            // 实例化DataTables
            datatables = $(defaultOptions.container).DataTable(defaultOptions.dataTable);

            // 绑定DataTables的重画事件
            datatables.on("draw", function() {
                if (defaultOptions.chk.enable) {
                    // 行选中状态变更操作：
                    //   若行状态操作为取消，则取消全选按钮的选中状态
                    //   若行状态操作为选中，且所有行状态均为选中，则选中全选按钮
                    $(defaultOptions.container).find("tbody tr td:first-child input[type=checkbox]").on("click", function () {
                        if ($(this).is(":checked")) {
                            var checkedAll = true;
                            $.each($(defaultOptions.container).find("tbody tr td:first-child input[type=checkbox]"), function (i, e) {
                                checkedAll &= $(e).is(":checked");
                                if (!checkedAll) return false;
                            });
                            if (checkedAll) {
                                $(defaultOptions.container).find("thead tr th:first-child input[type=checkbox]").prop("checked", true);
                                $(defaultOptions.container).find("tfoot tr td:first-child input[type=checkbox]").prop("checked", true);
                            }
                        } else {
                            $(defaultOptions.container).find("thead tr th:first-child input[type=checkbox]").prop("checked", false);
                            $(defaultOptions.container).find("tfoot tr td:first-child input[type=checkbox]").prop("checked", false);
                        }
                    });
                }

            });
        },
        getDataTable: function() {
            return datatables;
        },
        searching: function(params) {
            additionalParams = params;
            datatables.ajax.reload();
            additionalParams = [];
        },
        getSelectedItems: function() {
            var items = "";
            $.each($(defaultOptions.container).find("tbody tr td:first-child input[type=checkbox]:checked"), function(i, e) {
                items += "," + $(this).data("id");
            });
            return items.length > 0 ? items.substr(1) : null;
        }
    };
};