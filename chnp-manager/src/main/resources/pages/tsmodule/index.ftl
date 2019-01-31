<!DOCTYPE html>
<html>
    <head>
        <#include "../common/styles.ftl" />
        <link rel="stylesheet" href="/assets/plugins/zTree/css/zTreeStyle.css">
        <style>
            .page{
                padding: 0 20px;
            }
            .page-head{
                height: 50px;
                font-size: 12px;
                border-bottom: 1px solid #eeeeee;
                display: flex;
                align-items: stretch;
            }
            .page-head> .page-head-left{
                flex: 0 0 auto;
                display: flex;
                align-items: center;
            }
            .page-head> .page-head-left> .page-head-left-title{
                border-left: 2px solid #88b7e0;
                padding-left: 10px;
            }
            .page-head> .page-head-right{
                flex: 1 1 auto;
                justify-content: flex-end;
            }

            .ztree .tree-btn-new> *,
            .ztree .tree-btn-edit> *,
            .ztree .tree-btn-del> *{
                font-family: FontAwesome, sans-serif;
            }
            .ztree .tree-btn-new,
            .ztree .tree-btn-edit,
            .ztree .tree-btn-del{
                margin-left: 2px;
                padding: 0 3px;
                border: none;
                color: white;
                cursor: pointer;
            }
            .ztree .tree-btn-new,
            .ztree .tree-btn-edit{
                color: black;
            }
            .ztree .tree-btn-del{
                background-color: #d73925;
            }
        </style>
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
            $(function() {

                $.ajax({
                    type: "GET",
                    url: "/tsmodule/data",
                    dataType: "json",
                    success: function(json) {
                        // 展开父节点
                        $.each(json, function(i, e) {
                            if (e.type < 2) e.open = true;
                        });
                        initTree(json);
                    }
                });


            });
            
            function initTree(data) {
                var zTreeObj = $.fn.zTree.init($("#moduleTree"), {
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
                                addBtnNew(treeNode);
                            }else if (treeNode.type === 3) {
                                addBtnEdit(treeNode);
                                addBtnDel(treeNode);
                            }else {
                                addBtnNew(treeNode);
                                addBtnEdit(treeNode);
                                addBtnDel(treeNode);
                            }
                        },
                        removeHoverDom: function(treeId, treeNode) {
                            $("#btnNew_" + treeNode.id).unbind().remove();
                            $("#btnEdit_" + treeNode.id).unbind().remove();
                            $("#btnDel_" + treeNode.id).unbind().remove();
                        }
                    }
                }, data);
            }

            function addBtnNew(treeNode) {
                if ($("#btnNew_" + treeNode.id).length === 0) {
                    $("#" + treeNode.tId + "_a").append(
                            '<button id="btnNew_' + treeNode.id + '" class="tree-btn-new" onfocus="this.blur();" data-url="/tsmodule/new?parentId=' + treeNode.parentId + '" onclick="modal($(this))">' +
                            '<i class="fa fa-plus"></i>新增</button>'
                    );
                }
            }

            function addBtnEdit(treeNode) {
                if ($("#btnEdit_" + treeNode.id).length === 0) {
                    $("#" + treeNode.tId + "_a").append(
                            '<button id="btnEdit_' + treeNode.id + '" class="tree-btn-edit" onfocus="this.blur();" data-url="/tsmodule/edit?id="' + treeNode.id + '" onclick="modal($(this))">' +
                            '<i class="fa fa-edit"></i>编辑</button>'
                    );
                }
            }

            function addBtnDel(treeNode) {
                if ($("#btnDel_" + treeNode.id).length === 0) {
                    $("#" + treeNode.tId + "_a").append(
                            '<button id="btnDel_' + treeNode.id + '" class="tree-btn-del" onfocus="this.blur();" data-url="/tsmodule/del?ids="' + treeNode.id + '">' +
                            '<i class="fa fa-trash"></i>编辑</button>'
                    );
                }
            }
            
            function modal(obj) {
                $.ajax({
                    type: "GET",
                    url: obj.data("url"),
                    dataType: "html",
                    success: function (html) {
                        $(html).modal();
                    },
                    error: function () {
                        alert("请求失败");
                    }
                })
            }
        </script>
    </body>
</html>