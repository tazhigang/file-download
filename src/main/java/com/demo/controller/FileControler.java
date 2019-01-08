package com.demo.controller;

import com.demo.config.ReadTxtFile;
import com.demo.dto.response.MobilesMsgResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @Author: ittzg
 * @CreateDate: 2018/12/30 11:54
 * @Description:
 */
@RestController
@RequestMapping(value = "file")
public class FileControler {
    @RequestMapping(value = "checktxt")
    public MobilesMsgResponse checktxt(MultipartFile txtfile, HttpSession session) throws IOException {
        MobilesMsgResponse mobilesMsgResponse = new MobilesMsgResponse();
        InputStream inputStream = txtfile.getInputStream();
        Map<String, List<String>> mobilesMsg = ReadTxtFile.getMobilesMsg(inputStream);
        List<String> mobileMsgs = mobilesMsg.get("mobileMsgs");
//        System.out.println(mobileMsgs.toString());
        System.out.println(mobileMsgs.size());
        System.out.println("==========================");
        List<String> errorMsgs = mobilesMsg.get("errorMsgs");
        System.out.println(errorMsgs.toString());
        mobilesMsgResponse.setMoblesMsgs(mobileMsgs);
        mobilesMsgResponse.setErrorMsgs(errorMsgs);
        return mobilesMsgResponse;
    }
    @RequestMapping( value = "/download", method = RequestMethod.GET )
    public void testDownload(HttpServletResponse res,String fileName) {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        String realPath = "/root/java/file";
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File(realPath+"/" + fileName)));
            int i = bis.read(buff);

            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("export file finish");
    }
}
