package com.study.contentmanage.util.qrcode;

import com.google.zxing.common.BitMatrix;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QrCodeTest {

    //测试网络logo图片生成二维码
    @Test
    public void fun01() throws IOException {
        Path path = Paths.get("D:/a.png");
//    URL url = new URL("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4142156350,2539270243&fm=26&gp=0.jpg");
        URL url = new URL("https://www.baidu.com/img/PCfb_5bf082d29588c07f842ccde3f97243ea.png");
        BitMatrix matrix = BitMatrixBuilder.create().setContent("https://blog.csdn.net/zzzgd_666").build();
        QrcodeUtil.createLogoQrCodeToPath(matrix,path,url);
        System.out.println("ok");
    }

    //测试本地logo图片生成二维码
    @Test
    public void fun02() throws IOException {
        Path path = Paths.get("D:/a2.png");
        Path path2 = Paths.get("D:/logo2.png");
//        BitMatrix matrix = BitMatrixBuilder.create().setContent("http://192.168.1.100:8099/content-preview/645abba00e23a9c60279578b0d756f8d").build();
        BitMatrix matrix = BitMatrixBuilder.create().setContent("http://192.168.1.100:8100/content-preview/9a09ffa52998726eebc2779c0a9dd2c9").build();
        QrcodeUtil.createLogoQrCodeToPath(matrix,path,path2);
        System.out.println("ok");
    }

    //测试修改背景色
    @Test
    public void fun03() throws IOException {
        Path path = Paths.get("D:/a3.png");
        Path path2 = Paths.get("D:/logo.jpg");
        BitMatrix matrix = BitMatrixBuilder.create().setContent("https://blog.csdn.net/zzzgd_666").build();
        QrcodeUtil.createLogoQrCodeToPath(matrix,path,path2, Color.GRAY,Color.ORANGE);
        System.out.println("ok");
    }
}
