package cn.fawzi.thymeleaf.controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * <p>链接地址：https://github.com/zxing/zxing</p>
 * @author wenqi
 */

@Controller
@RequestMapping("/qr")
public class QRCoderController {

    private static final Map<DecodeHintType,Object> HINTS;
    private static final Map<DecodeHintType,Object> HINTS_PURE;

    static {
        HINTS = new EnumMap<>(DecodeHintType.class);
        HINTS.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        HINTS.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));
        HINTS_PURE = new EnumMap<>(HINTS);
        HINTS_PURE.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
        HINTS.put(DecodeHintType.CHARACTER_SET,"utf-8");
    }

    @RequestMapping("/create")
    public void createQRImage(HttpServletResponse response,String word) throws WriterException, IOException {
        BitMatrix bitMatrix;
        String trans=new String(word.getBytes("UTF-8"),"ISO-8859-1");
        bitMatrix=new QRCodeWriter().encode(trans, BarcodeFormat.QR_CODE,200,300);
        ByteArrayOutputStream pngOut = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOut);
        byte[] pngData = pngOut.toByteArray();
        response.setContentType("image/png");
        response.setContentLength(pngData.length);
        response.setHeader("Cache-Control", "public");
        response.getOutputStream().write(pngData);
    }

    @RequestMapping("/dp")
    public String decoderPage(){
        return "QR";
    }

    @RequestMapping(value = "/decoder",method = RequestMethod.POST)
    public String decoder(@RequestParam("image")MultipartFile file, Model model) throws IOException {
        BufferedImage bufferedImage= ImageIO.read(file.getInputStream());
        LuminanceSource luminanceSource=new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(luminanceSource));

        Reader reader = new MultiFormatReader();
        ReaderException savedException = null;
        Collection<Result> results = new ArrayList<>(1);
        try {
            // Look for multiple barcodes
            MultipleBarcodeReader multiReader = new GenericMultipleBarcodeReader(reader);
            Result[] theResults = multiReader.decodeMultiple(bitmap, HINTS);
            if (theResults != null) {
                results.addAll(Arrays.asList(theResults));
            }
        } catch (ReaderException re) {
            savedException = re;
        }

        if (results.isEmpty()) {
            try {
                // Look for pure barcode
                Result theResult = reader.decode(bitmap, HINTS_PURE);
                if (theResult != null) {
                    results.add(theResult);
                }
            } catch (ReaderException re) {
                savedException = re;
            }
        }

        if (results.isEmpty()) {
            try {
                // Look for normal barcode in photo
                Result theResult = reader.decode(bitmap, HINTS);
                if (theResult != null) {
                    results.add(theResult);
                }
            } catch (ReaderException re) {
                savedException = re;
            }
        }

        if (results.isEmpty()) {
            try {
                // Try again with other binarizer
                BinaryBitmap hybridBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
                Result theResult = reader.decode(hybridBitmap, HINTS);
                if (theResult != null) {
                    results.add(theResult);
                }
            } catch (ReaderException re) {
                savedException = re;
            }
        }

        if (results.isEmpty()) {
            try {
                throw savedException == null ? NotFoundException.getNotFoundInstance() : savedException;
            } catch (FormatException | ChecksumException e) {
                e.printStackTrace();
            } catch (ReaderException e) { // Including NotFoundException
               e.printStackTrace();
            }
        }
        StringBuilder sb=new StringBuilder();
            for (Result result : results) {
                sb.append(result.getText());
                sb.append('\n');
            }

        model.addAttribute("result",sb.toString());
        return "QR";
    }


}
