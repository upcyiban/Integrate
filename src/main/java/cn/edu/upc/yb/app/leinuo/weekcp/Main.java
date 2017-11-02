package cn.edu.upc.yb.app.leinuo.weekcp;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(21);
        integers.add(21);
        integers.add(2123123);
        integers.add(21);
        integers.add(21);
        System.out.println(integers.toString());
        integers.remove(2);
        System.out.println(integers.toString());
    }
}
