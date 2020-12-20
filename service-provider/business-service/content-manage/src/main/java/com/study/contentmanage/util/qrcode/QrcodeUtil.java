package com.study.contentmanage.util.qrcode;

import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Path;

@Slf4j
public class QrcodeUtil {


    private final static String FILE_FORMAT = "png";

    /**
     * 输出到指定路径
     * @author zgd
     */
    public static void createQrCodeToPath(BitMatrix bitMatrix, Path path) throws IOException {
        MatrixToImageWriter.writeToPath(bitMatrix, FILE_FORMAT, path);
        log.info("createQrCodeToPath ok...");
    }


    /**
     * 输出到指定路径
     * @author zgd
     */
    public static void createQrCodeToPath(BitMatrix bitMatrix, Path path, Color front, Color background) throws IOException {
        MatrixToImageConfig config = new MatrixToImageConfig(front.getRGB(), background.getRGB());
        MatrixToImageWriter.writeToPath(bitMatrix, FILE_FORMAT, path,config);
        log.info("createQrCodeToPath ok...");
    }

    /**
     * 输出到流
     * @author zgd
     * @date 2019/8/16 14:31
     */
    public static void createQrCodeToStream(BitMatrix bitMatrix, OutputStream stream) throws IOException {
        MatrixToImageWriter.writeToStream(bitMatrix, FILE_FORMAT, stream);
        log.info("createQrCodeTo ok...");
    }

    /**
     * 输出到流
     * @author zgd
     * @date 2019/8/16 14:31
     */
    public static void createQrCodeToStream(BitMatrix bitMatrix, OutputStream stream,Color front,Color background) throws IOException {
        MatrixToImageConfig config = new MatrixToImageConfig(front.getRGB(), background.getRGB());
        MatrixToImageWriter.writeToStream(bitMatrix, FILE_FORMAT, stream,config);
        log.info("createQrCodeTo ok...");
    }

    /**
     * 创建附带本地路径的logo的二维码，到指定路径
     * @throws IOException
     */
    public static void createLogoQrCodeToPath(BitMatrix bitMatrix,Path codePath,Path logoPath)throws IOException{
        MatrixToImageWriterWithLogo.writeToPath(bitMatrix,FILE_FORMAT,codePath,logoPath);
    }

    /**
     * 创建附带url路径的logo的二维码，到指定路径
     * @throws IOException
     */
    public static void createLogoQrCodeToPath(BitMatrix bitMatrix, Path codePath, URL logoUrl)throws IOException{
        MatrixToImageWriterWithLogo.writeToPath(bitMatrix,FILE_FORMAT,codePath,logoUrl);
    }


    /**
     * 创建附带本地路径的logo的二维码，到输出流
     * @author zgd
     * @date 2019/8/16 15:35
     * @return void
     */
    public static void createLogoQrCodeToStream(BitMatrix bitMatrix,OutputStream os,Path logoPath)throws IOException{
        MatrixToImageWriterWithLogo.writeToStream(bitMatrix,FILE_FORMAT,os,logoPath);
    }

    /**
     * 创建附带本地路径的logo的二维码，到指定路径
     * @author zgd
     * @date 2019/8/16 15:35
     * @return void
     */
    public static void createLogoQrCodeToPath(BitMatrix bitMatrix,Path codePath,Path logoPath,Color front,Color background)throws IOException{
        MatrixToImageConfig config = new MatrixToImageConfig(front.getRGB(), background.getRGB());
        MatrixToImageWriterWithLogo.writeToPath(bitMatrix,FILE_FORMAT,codePath,logoPath,config);
    }

    /**
     * 创建附带url路径的logo的二维码，到指定路径
     * @author zgd
     * @date 2019/8/16 15:35
     * @return void
     */
    public static void createLogoQrCodeToPath(BitMatrix bitMatrix, Path codePath, URL logoUrl, Color front, Color background)throws IOException{
        MatrixToImageConfig config = new MatrixToImageConfig(front.getRGB(), background.getRGB());
        MatrixToImageWriterWithLogo.writeToPath(bitMatrix,FILE_FORMAT,codePath,logoUrl,config);
    }

    /**
     * 创建附带本地路径的logo的二维码，到输出流
     * @author zgd
     * @date 2019/8/16 15:35
     * @return void
     */
    public static void createLogoQrCodeToStream(BitMatrix bitMatrix,OutputStream os,Path logoPath,Color front,Color background)throws IOException{
        MatrixToImageConfig config = new MatrixToImageConfig(front.getRGB(), background.getRGB());
        MatrixToImageWriterWithLogo.writeToStream(bitMatrix,FILE_FORMAT,os,logoPath,config);
    }


}

