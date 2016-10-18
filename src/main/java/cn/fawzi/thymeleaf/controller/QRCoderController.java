package cn.fawzi.thymeleaf.controller;

import cn.fawzi.thymeleaf.service.QRCodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * <p>链接地址：https://github.com/zxing/zxing</p>
 * @author wenqi
 */

@Controller
@RequestMapping("/qr")
public class QRCoderController {

    @Autowired
    private QRCodeService qrCodeService;

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
        StringBuilder sb = qrCodeService.decoder(file);
        model.addAttribute("result","分析结果是："+sb.toString());
        return "QR";
    }

}
