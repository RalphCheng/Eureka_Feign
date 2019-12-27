package com.cl.agree.springcloud.feign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/12/6.
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String file(){
        return "fileDownload.html";
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(){
        System.out.println("文件上传");
    }
    
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void download(){
        System.out.println("文件下载");
    }
}
