package com.study.contentmanage.controller;

import com.study.contentmanage.bean.Content;
import com.study.contentmanage.service.ContentManageService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/content")
@RestController
public class ContentManageController {


    @Autowired
    private ContentManageService contentService;

    @RequestMapping(method = RequestMethod.POST,path = "/add")
    public int addContent(Content content){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getPrincipal();
        return contentService.addContent(content);
    }


    @RequestMapping(method = RequestMethod.POST,path = "/del")
    public int delContent(Integer id){
        return contentService.delContent(id);
    }


    @RequestMapping(method = RequestMethod.POST,path = "/update")
    public int updateContent(Content content){
        return contentService.updateContent(content);
    }

//    @PreAuthorize("hasAnyAuthority('sdfasdfasdfasdfasdfasdf')")
    @RequestMapping(method = RequestMethod.POST,path = "/query")
    public List<Content> selectContent(){
        return contentService.queryContent();
    }

    @RequestMapping(method = RequestMethod.POST,path = "/query/{id}")
    public Content selectContentById(@PathVariable(name = "id") String id){
        return contentService.queryContentById(id);
    }
}
