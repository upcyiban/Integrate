package cn.edu.upc.yb.speaktoteacher.model;

public class Student {


    private String name;
    private String age;
    private String height;
    private String weight;

    public Student(String name, String height, String weight) {


        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public void setAge(String age) {

        this.age = age;

    }

    public void out() {

        System.out.println("他的名字是:" + name + "\n" + "年龄是：" + age + "\n" +
                "身高是：" + height + "\n" + "体重是：" + weight + "\n");

    }


    public static void main(String arg[]) {


        Student student = new Student("鄢红霞", "170cm", "56kg");
        student.setAge("18");student.out();

    }


}
