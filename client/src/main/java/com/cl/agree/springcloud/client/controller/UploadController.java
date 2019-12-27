package com.cl.agree.springcloud.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/12/11.
 */
@Controller
public class UploadController {
    @GetMapping("/")
    public String uploladPage(){
        return "upload";
    }
    
    @PostMapping("/fileupload")
    public String fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest req, Model model){
        System.out.println("文件上传。。。。。。。。。。。。。。。。");
        try {
            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
//            String destFileName=req.getServletContext().getRealPath("")+"uploaded"+ File.separator+fileName;
            String destFileName="G:/upload" + File.separator+fileName;
            
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            System.out.println(destFile);
            file.transferTo(destFile);
            model.addAttribute("fileName",fileName);
            model.addAttribute("path",destFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }
        return "OK";
    }
    
    @RequestMapping("/download")
    public void download(HttpServletResponse response){
        try {
            // 文件地址，真实环境是存放在数据库中的
            File file=new File("G:\\upload\\1576064257499新一代超网服务场景清单V1.1.xlsx");
            // 创建输入流，传入文件对象
            FileInputStream fis=new FileInputStream(file);
            // 设置相关格式
            response.setContentType("application/force-download");
            // 设置下载后的文件名以及header
            response.addHeader("Content-disposition", "attachment;filename=1576064257499新一代超网服务场景清单V1.1.xlsx");
            OutputStream os = response.getOutputStream();
            // 常规操作
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            os.close();
            fis.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
