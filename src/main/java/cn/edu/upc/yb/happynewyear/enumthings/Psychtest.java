package cn.edu.upc.yb.happynewyear.enumthings;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2018/12/31  23:42
 **/
public enum Psychtest {
    PSYCHTEST_FIRST("你希望自己在爱情中收获的是什么呢？", "爱人", "快乐", "信任", 2, 4, 3, 1),
    PSYCHTEST_SECOND("你的爱情没有收获你会选择放弃吗？", "绝对不会放弃", "还是放弃吧", "看心情", 4, 3, 6, 2),
    PSYCHTEST_THIRD("你目前在爱情中收获得最多的是什么呢？", "信任", "改变", "宽容", 4, 7, 5, 3),
    PSYCHTEST_FOUR("你对爱情一直是期待着的吗？", "一直期待", "偶尔期待", "看心情", 6, 7, 8, 4),
    PSYCHTEST_FIVE("爱情中有哪些收获是你不想要的呢？", "教训", "妥协", "隐瞒", 7, 8, 6, 5),
    PSYCHTEST_SIX("你能够为自己的爱情做主吗？", "完全做不了主", "对方在做主", "父母在做主", 9, 7, 10, 6),
    PSYCHTEST_SEVEN("你想要的爱情是什么样子的呢？", "相互理解", "无话不说", "爱意不退", 8, 10, 9, 7),
    PSYCHTEST_EIGHT("你愿意为了爱情做什么改变呢？", "收敛脾气", "培养能力", "看情况", 9, -1, 10, 8),
    PSYCHTEST_NIGHT("你会因为什么对爱情失去信心呢？", "失败", "背叛", "父母反对", -3, -2, 10, 9),
    PSYCHTEST_TEN("你觉得自己能够为爱情勇敢几次呢？", "一直勇敢", "勇敢一次", "没有办法勇敢", -3, -2, -4, 10);

    public String title;
    public String A;
    public String B;
    public String C;
    public int a;
    public int b;
    public int c;
    public int self;

    Psychtest(String title, String a, String b, String c, int a1, int b1, int c1, int self) {
        this.title = title;
        A = a;
        B = b;
        C = c;
        this.a = a1;
        this.b = b1;
        this.c = c1;
        this.self = self;
    }


    public static Psychtest getfromValue(String value) {

        for (Psychtest searchTypeEnum : Psychtest.values()) {
            if (searchTypeEnum.title.equals(value)) {
                return searchTypeEnum;
            }
        }
        return null;
    }

    public static Psychtest getself(int self) {
        for (Psychtest searchTypeEnum : Psychtest.values()) {
            if (searchTypeEnum.self == self) {
                return searchTypeEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Psychtest{" +
                "title='" + title + '\'' +
                ", A='" + A + '\'' +
                ", B='" + B + '\'' +
                ", C='" + C + '\'' +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", self=" + self +
                '}';
    }
}
/*
* 　4、你对爱情一直是期待着的吗？
　　一直期待→6
　　偶尔期待→7
　　看心情→8

　　5、爱情中有哪些收获是你不想要的呢？
　　教训→7
　　妥协→8
　　隐瞒→6爱情测试
　　6、你能够为自己的爱情做主吗？
　　完全做不了主的→9
　　对方在做主→7
　　父母在做主→10

　　7、你想要的爱情是什么样子的呢？
　　相互理解→8
　　无话不说→10
　　爱意不退→9

　　8、你愿意为了爱情做什么改变呢？
　　收敛脾气→9
　　培养能力→A
　　看情况→10

　　9、你会因为什么对爱情失去信心呢？
　　失败→C
　　背叛→B
　　父母反对→10

　　10、你觉得自己能够为爱情勇敢几次呢？
　　一直勇敢→C
　　勇敢一次→D
　　没有办法勇敢→B*/