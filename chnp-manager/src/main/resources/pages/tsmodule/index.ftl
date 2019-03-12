<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/assets/plugins/zTree/css/zTreeStyle.css">
        <#include "../common/styles.ftl" />
    </head>
    <body>
        <div class="page">
            <div class="page-head">
                <div class="page-head-left">
                    <span class="page-head-left-title">菜单管理</span>
                </div>
                <div class="page-head-right"></div>
            </div>

            <div>
                <ul id="moduleTree" class="ztree"></ul>
            </div>
        </div>

        <#include "../common/scripts.ftl"/>
        <script src="/assets/plugins/zTree/js/jquery.ztree.all-3.min.js"></script>
        <script>
            var tsmodule = {
                ztree: {
                    settings: {
                        data: {
                            key: {
                                url: "xUrl"
                            },
                            simpleData: {
                                enable: true,
                                pIdKey: "parentId",
                                rootPId: 0
                            }
                        },
                        view: {
                            addHoverDom: function(treeId, treeNode) {
                                // 根节点只允许新增；叶子结点只允许编辑和删除；其他节点均可新增、编辑、删除
                                if (treeNode.type === 1 && treeNode.parentId === 0) {
                                    tsmodule.addBtnNew(treeNode);
                                }else if (treeNode.type === 3) {
                                    tsmodule.addBtnEdit(treeNode);
                                    tsmodule.addBtnDel(treeNode);
                                }else {
                                    tsmodule.addBtnNew(treeNode);
                                    tsmodule.addBtnEdit(treeNode);
                                    tsmodule.addBtnDel(treeNode);
                                }
                            },
                            removeHoverDom: function(treeId, treeNode) {
                                $("#btnNew_" + treeNode.id).unbind().remove();
                                $("#btnEdit_" + treeNode.id).unbind().remove();
                                $("#btnDel_" + treeNode.id).unbind().remove();
                            }
                        }
                    }
                }
            };

            $(function() {

                tsmodule.initTree();

            });
            
            tsmodule.initTree = function() {
                $.ajax({
                    type: "GET",
                    url: "/tsmodule/data",
                    dataType: "json",
                    success: function(json) {
                        // 展开父节点
                        $.each(json, function(i, e) {
                            if (e.type < 2) e.open = true;
                        });
                        tsmodule.zTreeObj = $.fn.zTree.init($("#moduleTree"), tsmodule.ztree.settings, json);
                    }
                });

            };

            tsmodule.addBtnNew = function(treeNode) {
                if ($("#btnNew_" + treeNode.id).length === 0) {
                    $("#" + treeNode.tId + "_a").append(
                            '<button id="btnNew_' + treeNode.id + '" class="tree-btn-new" onfocus="this.blur();" data-url="/tsmodule/new?parentId=' + treeNode.id + '" onclick="modal($(this))">' +
                            '<i class="fa fa-plus"></i>新增</button>'
                    );
                }
            };

            tsmodule.addBtnEdit = function(treeNode) {
                if ($("#btnEdit_" + treeNode.id).length === 0) {
                    $("#" + treeNode.tId + "_a").append(
                            '<button id="btnEdit_' + treeNode.id + '" class="btn-primary tree-btn-edit" onfocus="this.blur();" data-url="/tsmodule/edit?id=' + treeNode.id + '" onclick="modal($(this))">' +
                            '<i class="fa fa-edit"></i>编辑</button>'
                    );
                }
            };

            tsmodule.addBtnDel = function(treeNode) {
                if ($("#btnDel_" + treeNode.id).length === 0) {
                    $("#" + treeNode.tId + "_a").append(
                            '<button id="btnDel_' + treeNode.id + '" class="btn-danger tree-btn-del" onfocus="this.blur();" data-url="/tsmodule/del?ids=' + treeNode.id + '" onclick="tsmodule.del($(this))">' +
                            '<i class="fa fa-trash"></i>删除</button>'
                    );
                }
            };

            tsmodule.del = function(obj) {
                var url = obj.data('url');

                bootbox.confirm({
                    message: '确定要<strong style="color: red;">删除</strong>选中的记录吗？',
                    buttons: {
                        confirm: {
                            label: '<i class="fa fa-fw fa-trash"></i>删除',
                            className: 'btn btn-danger'
                        },
                        cancel: {
                            label: '取消'
                        }
                    },
                    callback: function(result) {
                        if (result) {
                            $.ajax({
                                type: "post",
                                url: url,
                                dataType: "json",
                                success: function(data) {
                                    if (1 === data.returnCode) {
                                        tsmodule.initTree();
                                    }else {
                                        bootbox.alert(data.msg);
                                    }
                                },
                                error: function() {
                                    bootbox.alert("请求失败");
                                }
                            })
                        }
                    }
                });
            };
        </script>
    </body>
</html>