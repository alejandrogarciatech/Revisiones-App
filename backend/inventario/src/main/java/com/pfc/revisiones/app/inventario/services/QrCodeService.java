package com.pfc.revisiones.app.inventario.services;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.nio.file.Path;

@Service
public class QrCodeService {

    private static final int BARCODE_WIDTH = 300;
    private static final int BARCODE_HEIGHT = 150;
    private static final String IMAGE_FORMAT = "PNG";

    public String generateBarCode(String id) throws Exception {
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(id, BarcodeFormat.QR_CODE, BARCODE_WIDTH, BARCODE_HEIGHT);
            Path path = Paths.get("src/main/resources/static/barcodes/" + id + ".png");
            MatrixToImageWriter.writeToPath(bitMatrix, IMAGE_FORMAT, path);
            return path.toString();
        } catch (Exception e) {
            throw new Exception("Error al generar el código de barras", e);
        }
    }

    public BufferedImage generateBarCodeImage(String barCodeText) throws Exception {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(barCodeText, BarcodeFormat.QR_CODE, BARCODE_WIDTH,
                    BARCODE_HEIGHT);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (Exception e) {
            throw new Exception("Error al generar la imagen del código QR", e);
        }
    }
}
