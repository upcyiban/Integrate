package cn.edu.upc.yb.common.util;

/*
 * 二维码的一个小包
 * 最后返回一个ＵＲＬ给前端
 *下载图片 http://qr.liantu.com/api.php?text=x 进行引用
 * x 必须用UTF8编码格式，x内容出现 & 符号时，请用 %26 代替,
 *换行符使用 %0A
 *
 * 此处使用的是只有与一个参数的构建。。
 * 有问题请访问那个网址，URL
 * */
public  class QRcode {


    private static String URL = "http://qr.liantu.com/api.php?text=";

    public String generateUrl(String messages) {
        URL = URL + messages;
        return URL;
    }

}
