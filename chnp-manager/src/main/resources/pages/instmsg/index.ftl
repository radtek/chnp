<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>IMFW</title>
        <#include "../common/styles.ftl" />
        <link rel="stylesheet" href="../assets/css/common.css">
        <link rel="stylesheet" href="../assets/css/IMFW.css">
    </head>
    <body>
        <div>
            <div id="chnp" class="imfw-main"></div>

        </div>

        <#include "../common/scripts.ftl"/>
        <script src="/assets/js/IMFW.js"></script>
        <script>
            $(function() {
                var imfw = new IMFW({
                    container: 'chnp',
                    data: {
                        userId: 0,
                        users: [
                            {
                                id: 0,
                                name: '我师父是霍元甲',
                                decl: '但我不会武功',
                                img: '../assets/img/imfw-user-1.jpg'
                            }, {
                                id: 1,
                                name: '许三多',
                                decl: '跟着班长走',
                                img: '../assets/img/imfw-user-1.jpg'
                            }, {
                                id: 2,
                                name: '高城',
                                decl: '听党指挥，能打胜仗，作风优良',
                                img: '../assets/img/imfw-user-1.jpg'
                            }
                        ],
                        userGroups:[
                            {
                                id: 1,
                                name: '钢七连',
                                userList: [1, 2]
                            }
                        ],
                        talkGroups:[]
                    }
                });
                console.log(imfw);
            })
        </script>
    </body>
</html>