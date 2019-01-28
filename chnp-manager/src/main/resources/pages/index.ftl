<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>首页</title>
        <style type="text/css">
            html, body{
                height: 100%;
                margin: 0;
            }
            .view{
                display: flex;
                align-items: stretch;
            }
            .view-menu{
                flex: 0 0 auto;
            }
        </style>
    </head>
    <body>
        <div class="view">
            <div class="view-menu">
            </div>
            <div class="view-borrow">
                <div class="view-borrow-tabs"></div>
                <div class="view-borrow-frame">
                </div>
            </div>
        </div>

        <#include "./common/scripts.ftl"/>
        <script>
            $(function() {
            })

        </script>
    </body>
</html>