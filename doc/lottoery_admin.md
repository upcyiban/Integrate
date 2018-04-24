
#### admin
 - /lottery/admin/login 
    - 请求：　post
       - 参数：String  username ,String  password
              
       - 返回值： 成功：    {
           "code":1,
            "msg":"登陆成功",
            "Object":"登录成功"
       }
             
       失败:  {

         "code":-1, 
          "mes":"密码或用户名有问题，请确认",
          "Object":null
           }

 - /lottery/admin/logout
    - 请求：　get
         - 参数  ：
         - 返回  ： 成功：
               {
                   "code":1,
                   "mes":"你已经成功退出“，
                   "Object":null
               }   
                          
- /lottery/admin/getlotterylist
    - 请求：get
        - 描述： 获取所有被举报的抽奖
        - 参数 ： 
        - 返回 ： [
            {
                "id": id,
                "creatorid":id,
                "lotteryname":"抽奖的名字（几等奖）",
                "lotteryintro":"具体的奖品名字",
                "lotterytimebegin":"开始的时间",
                "lotterytimeend":"结束的时间",
                "createtime":"创建的时间",
                "ispass":1/0 ， //1就是该抽奖没问题。
                "passcode":123456  , //抽奖验证用的
                " feedback":"反馈信息",
                "feedbackTime":"反馈的时间"
                .
                .
                .
            },
            {
                "id": id,
                "creatorid":id,
                "lotteryname":"抽奖的名字（几等奖）",
                "lotteryintro":"具体的奖品名字",
                "lotterytimebegin":"开始的时间",
                "lotterytimeend":"结束的时间",
                .
                .
            }....
        ]
        - 返回的数据主要是这些：   

 
- /lottery/admin/dochoice

    -请求：Post
    - 接口描述： 对受到警告的抽奖进行审核。。
    - 参数:   String lotteryId
              int ispass //1为通过  ， 0 为警告  2  为不过
              String feedback  //反馈信息
    - 返回：{
        "code":1",
        "msg":"选择成功",
        "object":null
    }

