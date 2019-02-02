<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>首页</title>
        <#include "./common/styles.ftl" />
        <link rel="stylesheet" href="/assets/css/CHNPFrame.css">
    </head>
    <body>
        <div class="view-frame">
            <div class="view-frame-left">
                <div class="view-frame-left-user">
                    <img src="/assets/img/default_user.jpg">
                    <div>
                        <div>Super Admin</div>
                        <div><a href="/logout">[注销]</a></div>
                    </div>
                </div>
                <div class="view-frame-left-menu"></div>
            </div>
            <div class="view-frame-right">
                <div class="view-frame-right-tool">
                    <div><i class="fa fa-backward"></i></div>
                    <div class="view-frame-right-tabs"></div>
                    <div><i class="fa fa-forward"></i></div>
                    <div></div>
                </div>
                <div class="view-frame-right-page"></div>
            </div>
        </div>

        <#include "./common/scripts.ftl"/>
        <script src="/assets/js/CHNPFrame.js"></script>
        <script>
            $(function() {
                new CHNPFrame({
                    container: "div.view-frame-left-menu",
                    isServer: true,
                    serverUrl: "/tsmodule/data",
                    rootPid: 1
                });
            })
        </script>
    </body>
</html>