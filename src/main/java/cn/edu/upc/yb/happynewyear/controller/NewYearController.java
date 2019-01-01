package cn.edu.upc.yb.happynewyear.controller;

import cn.edu.upc.yb.happynewyear.dto.Ning;
import cn.edu.upc.yb.happynewyear.dto.Result;
import cn.edu.upc.yb.happynewyear.enumthings.Psychtest;
import cn.edu.upc.yb.happynewyear.enumthings.ResultEnum;
import cn.edu.upc.yb.happynewyear.repository.BlessingRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2018/12/31  23:40
 **/
@RestController
@RequestMapping("/happy_new_year")
public class NewYearController {


    @Autowired
    private BlessingRespository blessingRespository;

    @GetMapping("/")
    public Object index() {

        Ning ning = new Ning();
        ning.title = Psychtest.PSYCHTEST_FIRST.title;
        ning.A = Psychtest.PSYCHTEST_FIRST.A;
        ning.B = Psychtest.PSYCHTEST_FIRST.B;
        ning.C = Psychtest.PSYCHTEST_FIRST.C;
        ning.a = Psychtest.PSYCHTEST_FIRST.a;
        ning.b = Psychtest.PSYCHTEST_FIRST.b;
        ning.c = Psychtest.PSYCHTEST_FIRST.c;
        ning.self = Psychtest.PSYCHTEST_FIRST.self;
        return ning;
    }

    @PostMapping("/start")
    public Object start(String title, String chose) {

        Psychtest psychtest = Psychtest.getfromValue(title);

        if (psychtest.A.equals(chose)) {
            if (psychtest.a > 0) {
                /*不能使用的数据*/
                Ning ning = new Ning();
                ning.title = Psychtest.getself(psychtest.a).title;
                ning.A = Psychtest.getself(psychtest.a).A;
                ning.B = Psychtest.getself(psychtest.a).B;
                ning.C = Psychtest.getself(psychtest.a).C;
                ning.a = Psychtest.getself(psychtest.a).a;
                ning.b = Psychtest.getself(psychtest.a).b;
                ning.c = Psychtest.getself(psychtest.a).c;
                ning.self = Psychtest.getself(psychtest.a).self;
                return ning;
            } else {
                Result result = new Result();
                result.value = ResultEnum.getInstance(psychtest.a).value;
                result.disc = ResultEnum.getInstance(psychtest.a).disc;
                result.detail = ResultEnum.getInstance(psychtest.a).detail;
                int Min = 1;

                int Max = 1047;

                long res = Min + (int) (Math.random() * ((Max - Min) + 1));
                result.hua = blessingRespository.findOne(res).getBlessing();
                return result;
            }
        } else if (psychtest.B.equals(chose)) {

            if (psychtest.b > 0) {
                /*不能使用的数据*/
                Ning ning = new Ning();
                ning.title = Psychtest.getself(psychtest.b).title;
                ning.A = Psychtest.getself(psychtest.b).A;
                ning.B = Psychtest.getself(psychtest.b).B;
                ning.C = Psychtest.getself(psychtest.b).C;
                ning.a = Psychtest.getself(psychtest.b).a;
                ning.b = Psychtest.getself(psychtest.b).b;
                ning.c = Psychtest.getself(psychtest.b).c;
                ning.self = Psychtest.getself(psychtest.b).self;
                return ning;
            } else {

                Result result = new Result();
                result.value = ResultEnum.getInstance(psychtest.b).value;
                result.disc = ResultEnum.getInstance(psychtest.b).disc;
                result.detail = ResultEnum.getInstance(psychtest.b).detail;
                int Min = 1;

                int Max = 1047;

                long res = Min + (int) (Math.random() * ((Max - Min) + 1));
                result.hua = blessingRespository.findOne(res).getBlessing();
                return result;
            }
        } else if (psychtest.C.equals(chose)) {
            if (psychtest.c > 0) {
                /*不能使用的数据*/
                Ning ning = new Ning();
                ning.title = Psychtest.getself(psychtest.c).title;
                ning.A = Psychtest.getself(psychtest.c).A;
                ning.B = Psychtest.getself(psychtest.c).B;
                ning.C = Psychtest.getself(psychtest.c).C;
                ning.a = Psychtest.getself(psychtest.c).a;
                ning.b = Psychtest.getself(psychtest.c).b;
                ning.c = Psychtest.getself(psychtest.c).c;
                ning.self = Psychtest.getself(psychtest.c).self;
                return ning;
            } else {

                Result result = new Result();
                result.value = ResultEnum.getInstance(psychtest.c).value;
                result.disc = ResultEnum.getInstance(psychtest.c).disc;
                result.detail = ResultEnum.getInstance(psychtest.c).detail;
                int Min = 1;

                int Max = 1047;

                long res = Min + (int) (Math.random() * ((Max - Min) + 1));
                result.hua = blessingRespository.findOne(res).getBlessing();
                return result;
            }
        }

        return null;
    }


}
