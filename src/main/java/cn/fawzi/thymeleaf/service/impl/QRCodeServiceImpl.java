package cn.fawzi.thymeleaf.service.impl;

import cn.fawzi.thymeleaf.service.QRCodeService;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import com.google.zxing.multi.MultipleBarcodeReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

/**
 * @author wenqi
 */
@Service
public class QRCodeServiceImpl implements QRCodeService {
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
    @Override
    public StringBuilder decoder(MultipartFile file) throws IOException {
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
        return sb;
    }
}
