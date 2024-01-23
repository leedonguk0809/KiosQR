package org.zerock.wcup.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Component
@RequiredArgsConstructor
@Log4j2
public class QRCodeMaker {

    private final CustomFileUtil fileUtil;



    public String make(String urlStr)throws RuntimeException  {
        int width = 200;
        int height = 200;

        try {
            // QR Code - BitMatrix: qr code 정보 생성
            BitMatrix matrix = new MultiFormatWriter()
                    .encode(urlStr, BarcodeFormat.QR_CODE, width, height);

            // QR Code - Image 생성. : 1회성으로 생성해야 하기 때문에
            // stream으로 Generate(1회성이 아니면 File로 작성 가능.)

            //output Stream
            Path savePath = Paths.get(fileUtil.getUploadPath(), UUID.randomUUID().toString() + ".png");

            //Bitmatrix, file.format, outputStream
            MatrixToImageWriter.writeToPath(matrix, "PNG", savePath);


            return savePath.toFile().getName();

        }catch(Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }
}
