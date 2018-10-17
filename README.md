# Integrate
upc yiban integrated app with jwt

####如何是Spring Boot自动更新代码？

1.  以前开发后端代码每一次改动都需要重启整个应用，一般需要  
    20多秒的时间。现在引入了dev tool的依赖#生产环境不要引入  
    否则会引起jar包过大。  
    如何使用dev tool呢？首先在IDEA的设置里：
    
    ```
        搜索：build project
        将build project automacitally选中
        使用快捷键crtl+shift+alt+/选中第一个
        在弹出的对话框中将compiler.automake.alow.when.app.runing选中
    ```
    有任何疑问请到[lei的GitHub](https://github.com/1409070209/Integrate)提问  
    不是考试周会在当天解答，不要在upcyiban的项目里讨论非技术问题
##### 分支使用，规范后端。
- 在开发阶段所有的pullrequest都推送到test分支上。
- 在准备上线后的应用pullrequest到master分之上。
- master作为默认分之。owers在使用的时候请仔细注意分之。
##### 测试问题解决办法。
- 请联系运维人员或是给我pullrequest [liliangbin](https://github.com/liliangbin)
