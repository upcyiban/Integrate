
#### /lottery/user

- /lottery/user/pass
      GET方法
    -　描述：　获取所有创建的抽奖
    - 参数：　Authorization
    - 返回　：{
       "code":1,
       "msg":"所有过的抽奖",
       "object":[
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
                ."prizes":[
                                {
                                "id":id,
                                "creatorId":creator,
                                "prizeName":"nanf",
                                "prizeInfo":"info",
                                "prizePercentage":12121212,
                                "totalNumber":该奖项的名字
                                "lotteryId":抽奖的id
                                }

                ]
                "prizes":[
                    {
                            "id":id,
                            "lotteryid":id,
                            "yibanid":"id",
                            "yibanname":"真实名字",
                            "prizeName":"几等奖",
                            "prizeStage":"具体的奖品名字";
                    },
                    {


                            "id":id,
                            "lotteryid":id,
                            "yibanid":"id",
                            "yibanname":"真实名字",
                            "prizeName":"几等奖",
                            "prizeStage":"具体的奖品名字";
                    }
                ]
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
        }
    
- /lottery/user/notPass
   GET 请求
   授权的token
   
 返回值：
   
- /lottery/user/warning 

post方法

- Authorization
- long lotteryId
- String feedback
- 描述　：　用户发现抽奖不好，进行举报的接口。
- 返回　：{
    "code":1,
    "msg":"举报成功",
    "object":null
}
   　