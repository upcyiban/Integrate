### 后端快速开发文档

这篇文档适合已经熟悉[原来那种模式](https://github.com/upcyiban/IntegrateApplication)的后端开发人员阅读。

以下我以"石大易班应用反馈"为例介绍如何使用该系统。
 
首先申请一个轻应用，然后把appid，appkey，appname三个东西插到数据库的integrate_app表中，这一步骤在开发的过程中请自己实现，在部署的过程中由有权限的管理员实现。  

在 cn.edu.upc.yb 包下先建一个 feedback的包。
以下所有的活动均在该包下完成。

创建model类FeedbackMessage.java
```
@Entity
@Table(name = "feedback")
public class FeedbackMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private int ybid; //发送人的id
    private String  message;
    private String appname;
    private Timestamp sendTime;
    private int ispass;
    
    //seter and getter
    //construct
}

```
创建对应的repository 
```
public interface FeedBackRepository extends CrudRepository<FeedbackMessage,Integer>{

}

```
创建控制控制器 FeedBackController.java
```
@RestController
@RequestMapping("/feedback")
public class FeedBackController {

    @Autowired
    private FeedBackService feedBackService;

    @PostMapping("/create")
    public ResponseEntity<?> createFeedback(HttpServletRequest request,String message, String appname){
        return ResponseEntity.ok(feedBackService.doFeedBack(request,message,appname));
    }
}
```
一直到这里和以前都是一样的。

下面介绍 FeedBackService.java中的doFeedBack方法的实现。
```
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private FeedBackRepository feedBackRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
```
自动装载token的工具类
```
   public Object doFeedBack(HttpServletRequest request,String message, String appname){

        //从请求头中获取token
        String authToken = request.getHeader(this.tokenHeader);

        //从token中获取id
        String ybid = jwtTokenUtil.getYBidFromTocken(authToken);

        return feedBackRepository.save(new FeedbackMessage(Integer.valueOf(ybid),message,appname));
    }

```
然后从token中获取易班id，然后存数据库。


和以前不一样的地方就是以前的易班基本信息都存在session中，现在id，和access_token存在token中，可以通过工具类中的getYBidFromTocken和getYbaccessToken从token中获取。其他的基本信息可以通过易班id从我们的数据库中获取。

还有一点不一样的地方就是不用手动进行是否登陆的验证了，一切的验证系统都帮忙做了。

当然这只是简单的应用，还有稍微复杂一点的带权限的用法，你可以参考一下 manage 应用。也很简单这里就不具体说了。

