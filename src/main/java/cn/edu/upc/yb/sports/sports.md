#### yb.upc.edu.cn:8084/sports/add
接口描述： 负责用户采集的人进行参赛人员的成绩排名录入
POST
参数：
    String project,//参加的项目名字。 女教工甲组100米(决赛)  女教工乙组100米(决赛)   。。。
    String username,//参赛人员的名字
    String ranking ,//排名，第一名啊，第二名啊。。。。
    String score ,//成绩，他800米跑了多少时间
    long scoreOrder,//对应ranking。 第一名就是1  第二名就是2 .。。。后端用这个来进行排序
返回： 
    "添加成功"
#### yb.upc.edu.cn:8084/sports/user_ranking
接口描述： 用户通过自己的名字，查找所有参加过的项目，返回所有的排名，成绩情况。
POST
参数：  
    String username  ：参赛人员的名字

返回： 
    [
        {
        "id": 2,
        "project": "name",
        "username": "用户名",
        "ranking": "排名1",
        "score": "成绩",
        "ord": 2
        }
        。。。。。。
    ]
#### yb.upc.edu.cn:8084/sports/all_ranking
接口描述： 某个项目的所有排名
POST
参数：
    String project //项目名
返回：
[
            {
        "id": 2,
        "project": "name",
        "username": "用户名",
        "ranking": "排名1",
        "score": "成绩",
        "ord": 2
        }
        ......
]
#### yb.upc.edu.cn:8084/sports/record

GET
获取所有的破纪录成绩

返回：
[
{
    private String username;//用户名
    private String projectName;//项目名，，男子300米
    private String score;//成绩
    private String ranking;//排名
    private String recordInfo;//记录的简介。。省级，，校级

}
......
]


##### yb.upc.edu.cn:8084/sports/add_record
Post
参数：username   projectName  score  ranking   recordInfo

返回：
    添加成功
##### yb.upc.edu.cn:8084/sports/delete_one
参数：id(某个数据的Id）
方法： Post

返回： "删除成功"