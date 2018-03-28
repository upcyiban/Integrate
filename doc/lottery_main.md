#### lottery

- /lottery/
   
   -请求：post
   - 描述： 获取所有正在准备抽奖的抽奖项目
   - 参数 ：Authorization  //就是那个token
   - 返回：  抽奖的列表{
       "code":1,
       "msg":"获取所有满足时间要求的抽奖",
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
        }
-  /lottery/userInfo

    - 请求：　post
    - 描述： 主页的时候获取用户的信息
    - 参数:  Authorization
    - 返回： {
        "code":1,
        "msg":"获取信息成功",
        "Object":{
            "id":用户的id，
            "yibanid":  yibanid,
            "yibanname":用户真实名字
        }
    }
- /lottery/createLottery
    - 请求：　post
参数：String Authorization,
    String userName, //用户的真实名字
    String lotteryName,
    String lotteryIntro,
    Timestamp lotteryTimeBegin,
    Timestamp lotteryTimeEnd
返回值：{
    "code":11,
    "msg":"success",
    "object":[
        lotteryListId,
        passcode,
        creatorId,    //int数组，，直接用object[0] object[2]   获取他的值。。
    ]
}
- /lottery/createPrizes

请求：post
参数：   String prizeName,
        String prizeIntro,
        String prizePercentage,
        long prizeTotalNumber,
        long creatorId,
        long lotteryId
返回：{
    "code":1,
    "msg":"success",
    "object":true
}
- /lottery/ispass
    -  请求：post
    - 描述： 判断用户输入的passcode  是不是和数据库一样。成功就进去抽奖，，否者。。
    - 参数：　Authorization:
             long lotteryId
             int passcode

    - 返回值：
        {
                   "code":1,
                   "mes":"你已经成功退出“，
                   "Object":null
               }  

        return new ResponseBean(-1, "没有该抽奖", false);
        return new ResponseBean(1,"验证码正确",true);
        return new ResponseBean(-1, "验证码有问题，请重新输入", false);

-　/lottery/dolottery

   - 请求: post
   
   - 描述： 抽奖的接口
   
   - 参数：　Ａuthorization
            long  lotteryId 
   - 返回值：　 return new ResponseBean(-1,"你已经抽过奖了",false);　　　//抽过了

                //抽奖成功
                return new ResponseBean(-1, "很遗憾你没有中奖", null);
                return new ResponseBean(1, "恭喜你获奖了", prize);
                中奖后的返回数据解释一下{
                    "code":1,
                    "msg":"恭喜你中奖了",
                    "Object":{
                        //这个就是那个包括中奖信息的数据包
                        "id":id,
                        "creatorId":creator,
                        "prizeName":"nanf",
                        "prizeInfo":"info",
                        "prizePercentage":12121212,
                        "totalNumber":该奖项的名字
                        "lotteryId":抽奖的id
                    }
                

                }
                

- /lottery/prizeList
    - 请求: post
    - 描述： 获取该抽奖的中奖信息
    -　参数：　Authorization
            long lotteryId
    
    - 返回：
    {
        "code":1,
        "msg":"中奖信息",
        "object":[
            {

                "id":id,
                "lotteryid":id,
                "yibanid":"id",
                "yibanname":"真实名字",
               "prizeName":"几等奖",
                "prizeStage":"具体的奖品名字";
            }

            .....
        ]
    }
  
-　/lottery/fuzzy

   -　接口的请求方法：　post
   - 描述：　本来是准备模糊查询，，后来改为通过passcode 进行查找抽奖
   - 参数：　Authorization
            int passcode
   - 返回：　一个迭代器类型的数据
   {
       "code":1,
       "msg":"通过passscode查找",
       "object":
                [
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


   /lottery/prizes
post方法
 参数：  token:
        lotteryId //通过抽奖的ID获取和这个抽奖有关系的奖项

返回： {

    "code":1,
    "msg":"奖项列表",
    "object":[

        {
            "id":id,
            "creatorId":creator,
            "prizeName":"nanf",
            "prizeInfo":"info",
            "prizePercentage":12121212,
            "totalNumber":该奖项的名字
            "lotteryId":抽奖的id
        }
        .....
    ]
}