package cn.edu.upc.yb.speaktoteacher.ctroller;



import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.speaktoteacher.dao.MessageRepository;
import cn.edu.upc.yb.speaktoteacher.dao.TeacherRepository;
import cn.edu.upc.yb.speaktoteacher.model.Message;
import cn.edu.upc.yb.speaktoteacher.model.Teacher;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.PAData;

@RestController
@RequestMapping("/speaktoteacher")


public class MessageCtroller {

    @Autowired
    public MessageRepository messageRepository;


    @Autowired
    public TeacherRepository teacherRepository;



    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.AppName, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "yibanId", value = "用户的易班id", required = true,dataType = "String")})
    @RequestMapping(value = "/getteacher",method = RequestMethod.GET)
    public Object showTeacher( @RequestParam(value = "yibanId",defaultValue = "123")int yibanId){
//验证人用户是否是学生

return teacherRepository.findFirstByYibanId(yibanId);

}

@ApiImplicitParams({
        @ApiImplicitParam(paramType = "query" ,name = SwaggerParameter.Authorization,dataType = "String"),
        @ApiImplicitParam(paramType = "query",name = SwaggerParameter.AppName,dataType = "String"),
        @ApiImplicitParam(paramType = "query",name = "content",value = "是否创建",required = true,dataType = "String"),
        @ApiImplicitParam(paramType = "query",name = "teacherYBId",value = "老师的易班id",required = true,dataType = "int")
})

@RequestMapping(value = "/createmessage",method = RequestMethod.GET)
    public Object test(String content,@RequestParam(value = "teacherYBId",defaultValue = "123")int teacherYBId){


return  "hello world";
}

@ApiImplicitParams({
        @ApiImplicitParam(paramType = "query",name = SwaggerParameter.Authorization,dataType = "String"),
        @ApiImplicitParam(paramType = "query", name = SwaggerParameter.AppName,dataType = "String"),
        @ApiImplicitParam(paramType = "query" , name = "messages",required = true,dataType = "String"),
        @ApiImplicitParam(paramType = "query",name = "yibanId",value = "用于查询的id",required = true,dataType = "int")
})

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public  Object a( String messages , int yibanId){
Teacher teacher = (Teacher) teacherRepository.findFirstByYibanId(yibanId);
teacher.setName("我是牛逼啊哈哈");

return teacherRepository.save(teacher);
}
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.AppName,dataType = "String"),
            @ApiImplicitParam(paramType = "query" , name = "name",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query" , name = "imgurl",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query" , name = "recommend",required = true,dataType = "String")
    })
    @RequestMapping(value = "/b",method = RequestMethod.GET)
    public Object b(String name,String imgurl, String recommend ){

        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setImgurl(imgurl);
        teacher.setRecommend(recommend);
        teacherRepository.save(teacher);
        Teacher teacher1 = (Teacher) teacherRepository.findByName(name);
        int id = teacher1.getId();
        return teacher ;

    }



}
