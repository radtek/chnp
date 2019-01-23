<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>登陆</title>
        <style type="text/css">
            .login{
                display: flex;
                flex-flow: column nowrap;
                align-items: center;
            }
            .login-title{
                height: 100px;
            }
            .login-body{
                width: 20%;
                border: 1px solid RGBA(0, 0, 0, 0.3);
                box-shadow: RGBA(0, 0, 0, 0.5) 0px 0px 10px;
                padding: 20px 50px;
            }
            .login-body> div{
                display: flex;
                flex-flow: column nowrap;
                align-items: stretch;
                padding: 10px 0;
            }
            .login-body> div> label{
                font-size: 14px;
                font-weight: bold;
                color: RGBA(0, 0, 0, 0.7);
            }
            .login-footer{}
            input, button{
                line-height: 30px;
                outline: none;
                border: 1px solid;
            }
            input{
                border-color: RGBA(0, 0, 0, 0.3);
                padding-left: 10px;
            }
            button.normal{
                background-color: RGBA(40, 96, 144, 1);
                border-color: RGBA(40, 96, 144, 1);
                color: white;
            }
            img{
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div class="login">
            <div class="login-title"></div>
            <div class="login-body">
                <div>
                    <label>登陆名：</label>
                    <input name="username" type="text" title="" />
                </div>
                <div>
                    <label>登陆密码：</label>
                    <input name="password" type="password" title="" />
                </div>
                <div>
                    <label>验证码：</label>
                    <div style="display: flex;">
                        <input style="flex: 1 1 auto;" name="verifycode" type="text" title="" />
                        <img onclick="this.src='http://127.0.0.1:8088/verification?width=80&height=30&d=' + Math.random();" src="http://127.0.0.1:8088/verification?width=80&height=30"/>
                    </div>
                </div>
                <div>
                    <button id="btnLogining" class="normal">登陆</button>
                </div>
            </div>
            <div></div>
        </div>

        <script>

        </script>
    </body>
</html>