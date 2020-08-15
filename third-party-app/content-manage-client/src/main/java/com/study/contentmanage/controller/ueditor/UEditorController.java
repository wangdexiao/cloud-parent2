package com.study.contentmanage.controller.ueditor;

import com.alibaba.fastjson.JSONException;
import com.baidu.ueditor.ActionEnter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/ueditor")
public class UEditorController {


    @Value("${web.upload-path}")
    private String uploadRootDir;

    @CrossOrigin
    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
//        response.addHeader("X-Content-Type-Options", "nosniff");
        String callback = request.getParameter("callback");
//        String rootPath = request.getSession().getServletContext().getRealPath("/");
//        String rootPath = "D:/";

        try {

            String exec = new ActionEnter(request, uploadRootDir).exec();

            PrintWriter writer = response.getWriter();

//            writer.write(callback + "("+exec+")");
            writer.write(exec);

            writer.flush();

            writer.close();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
