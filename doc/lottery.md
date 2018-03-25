### lottery的接口文档

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
                   ”Object":null
               }   
                          
- /lottery/admin/getlotterylist
    - 请求：get
        - 描述： 获取所有被举报的抽奖
        - 参数 ： 
        - 返回 ： [

            {
            
                "id": id,
                
                "creator":creator,
                
                "lotteryname":fsdfsdfs
                .
                .
                .
            },
            {
            
                "id": id,
                
                "creator":creator,
                
                "lotteryname":fsdfsdfs
                .
                .
                
            }....
        ]
        - 返回的数据主要是这些：       
            private long creatorid;
            
            private String lotteryname;
            
            private String lotteryintro;
            
            private Timestamp lotterytimebegin;
        
            private Timestamp lotterytimeend;
        
            private Date createtime;
        
            private int ispass; //是否审核通过.未审核为0,通过为1,不通过为2.默认的时候为
        
            private int passcode;
        
            private String feedback;//假设这个抽奖没有过的时候就会给用户一个反馈信息
        
            private Date feedbackTime;
        - 。。。。一个一个写实在是太麻烦，，，我直接粘代码吧。。。最多就是把数据变成json的形式

- /lottery/admin/dochoice

    -请求：Post
    - 接口描述： 对受到警告的抽奖进行审核。。
    - 参数： String lotteryId
            int ispass //1为通过  ， 0 为警告  2  为不过
            String feedback  //反馈信息
    - 返回： new ResponseBean(1,"选择成功", null);


#### lottery

- /lottery/
   
   -请求：post
   - 描述： 获取所有正在准备抽奖的抽奖项目
   - 参数 ：Authorization  //就是那个token
   - 返回：  抽奖的列表  。[

       {

           抽奖1

       }，
       {

           抽奖2

       }，
       {

           抽奖n。。。。

       }
   ]
    - 抽奖这个类  。  
        private long id;

        private long creatorid;
        private String lotteryname;

        private String lotteryintro;

        private Timestamp 

        lottery　timebegin;

        private Timestamp lotterytimeend;

        private Date createtime;
        
        private int ispass; //是否审核通过.未审核为0,通过为1,不通过为2.默认的时候为
        private int passcode;

        private String feedback;//假设这个抽奖没有过的时候就会给用户一个反馈信息，，，
        private Date feedbackTime;
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
- /lottery/create
    - 请求：　post
   - 描述： 用户创建抽奖，，(太麻烦不想写，直接给你说吧)
- /lottery/ispass
    -  请求：post
    - 描述： 判断用户输入的passcode  是不是和数据库一样。成功就进去抽奖，，否者。。
    - 参数：　Ａuthorization:
             long lotteryId
             int passcode

    - 返回值：
        
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
                        ”id":id,
                        "creatorId":creator,
                        "prizeName":"nanf",
                        "prizeInfo":"info",
                        "prizePercentage":12121212
                    }
                

                }
                

- /lottery/prizeList
    - 请求: post
    - 描述： 获取该抽奖的中奖信息
    -　参数：　Ａuthorization
            long lotteryId
    
    - 返回：　　[

        {中奖信息１}，{中奖信息２}．。。。。
    ]
    　
    中奖信息：　
        private long id;

        private long lotteryid;

        private long yibanid;

        private String yibanname;

        private String prizeName;

        private String prizeStage;


-　/lottery/fuzzy

   -　接口的请求方法：　post
   - 描述：　本来是准备模糊查询，，后来改为通过passcode 进行查找抽奖
   - 参数：　Authorization
            int passcode
   - 返回：　一个迭代器类型的数据
    　　[
        {抽奖１}，{抽奖２}．。。。。。
    ]

#### /lottery/user

- /lottery/user/pass
    -　描述：　获取所有创建的抽奖
    - 参数：　Authorization
    - 返回　：　迭代器的抽奖，，，和fuzzy的返回类型一样。。还有　　/lottery  也是一样的
    
- /lottery/user/notPass
   //和上面一样
   
   - 描述：　　获取所有的没有过的抽奖
   
- /lottery/user/warning 
    - 描述　：　用户发现抽奖不好，进行举报的接口。
    
    - 返回　：new ResponseBean(1,"举报成功",null);
   　