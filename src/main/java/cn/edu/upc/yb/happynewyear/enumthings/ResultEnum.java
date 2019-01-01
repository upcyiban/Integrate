package cn.edu.upc.yb.happynewyear.enumthings;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/1/1  0:13
 **/
public enum ResultEnum {
    RESULT_ENUM_A(-1, "2019年你的爱情会有收获", "2019年你的爱情是让人羡慕的，你收获了自己想要的爱情，得到了自己最爱的那个人，对你来说这一年是充满甜蜜的。爱情有了收获之后你在任何方面都会有起色的，拥有爱情的你一直在改变中，你的生活会因为有了甜蜜的爱情而变得更加的美好。"),
    RESULT_ENUM_B(-2, "2019年你的爱情小有起色", "2019年你的爱情还是值得你好好的期待的，你可能会拥有自己想要的爱情，虽然并不是立马就拥有，但是你一旦是看到了希望，你就不会放弃的。小有起色的爱情对你来说已经是一种鼓励了，这样一来你在面对爱情的时候就更加的有信心了。"),
    RESULT_ENUM_C(-3, "2019年你的爱情没有发展", "2019年你的爱情没有任何的发展，对你来说是好事也是坏事，你觉得可能是时机还没有到，也可能是属于自己的缘分还没有到。面对这样的局面，你还是能够乐观的对待的，你并不会因为爱情没有发展而变得特别的慌张，你觉得是自己的总会来的。"),
    RESULT_ENUM_D(-4, "2019年你的爱情随时夭折", "2019年里的你就不要对自己的爱情抱有太大的希望了，希望越多失望也就会越多的。这一年里，你的爱情很危险，随时都有夭折的可能，虽然你很认真的去爱，但是你的爱并不能够被人珍惜。这一年里你太用力的爱了之后，受到的伤害反而是更大的。");

    public int value;
    public String disc;
    public String detail;

    ResultEnum(int value, String disc, String detail) {
        this.value = value;
        this.disc = disc;
        this.detail = detail;
    }

    ;

    public static ResultEnum getInstance(int value) {

        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (resultEnum.value == value) {
                return resultEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ResultEnum{" +
                "value=" + value +
                ", disc='" + disc + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
