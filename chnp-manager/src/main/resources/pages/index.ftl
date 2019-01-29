<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>首页</title>
        <#include "./common/styles.ftl" />

        <style>
            html, body, .frame{
                height: 100%;
                margin: 0;
            }
            .frame{
                display: flex;
                align-items: stretch;
            }
            .frame-menu{
                flex: 0 0 auto;
                display: flex;
                flex-flow: column nowrap;
                align-items: stretch;
                background-color: #293038;
            }
            .frame-menu-user{
                flex: 0 0 auto;
            }
            .frame-menu-list{
                flex: 1 1 auto;
            }
            .frame-menu-list .menu-item{
                line-height: 40px;
                font-size: 12px;
                cursor: pointer;
                padding-right: 50px;
                display: flex;
                align-items: center;
            }
            .frame-menu-list .menu-item> .menu-item-icon{
                width: 30px;
                text-align: center;
                transition: transform 0.1s;
            }
            .frame-menu-list .menu-item-level1{
                background-color: #37424f;
                border-bottom: 1px solid #414d5c;
                color: white;
                padding-left: 10px;
            }
            .frame-menu-list .menu-item-level1:hover,
            .frame-menu-list .menu-item-level1.active{
                background-color: #43515e;
            }
            .frame-menu-list .menu-item-level1:hover> .menu-item-icon{
                transform: rotate(90deg);
            }
            .frame-menu-list .menu-item-level2{
                color: white;
                padding-left: 20px;
            }
            .frame-menu-list .menu-item-level2> .menu-item-icon{
                color: #aeb9c2;
            }
            .frame-menu-list .menu-item-level2:hover{
                background-color: #37424f;
            }
            .frame-menu-list .menu-item-level2:hover> .menu-item-icon{
                color: white;
            }
            .frame-menu-list .menu-children{
                display: none;
            }
            .frame-borrow{
                flex: 1 1 auto;
                display: flex;
                flex-flow: column nowrap;
                align-items: stretch;
            }
            .frame-borrow-tabs{
                flex: 0 0 auto;
                height: 50px;
            }
            .frame-borrow-content{
                flex: 1 1 auto;
            }
        </style>
    </head>
    <body>
        <div class="frame">
            <div class="frame-menu">
                <div class="frame-menu-user"></div>
                <div class="frame-menu-list">
                    <div>
                        <div class="menu-item menu-item-level1">
                            <span class="menu-item-icon"><i class="fa fa-caret-right"></i></span>
                            <span class="menu-item-title">代码生成管理</span>
                        </div>
                        <div class="menu-children">
                            <div class="menu-item menu-item-level2">
                                <span class="menu-item-icon"><i class="fa fa-file-text-o"></i></span>
                                <span class="menu-item-title">模板管理</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="frame-borrow">
                <div class="frame-borrow-tabs"></div>
                <div class="frame-borrow-content"></div>
            </div>
        </div>

        <#include "./common/scripts.ftl"/>
        <script>
            $(function() {



            })

        </script>
    </body>
</html>