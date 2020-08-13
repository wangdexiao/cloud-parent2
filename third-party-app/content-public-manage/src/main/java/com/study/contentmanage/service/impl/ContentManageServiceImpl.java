package com.study.contentmanage.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.study.contentmanage.bean.Content;
import com.study.contentmanage.mapper.ContentMapper;
import com.study.contentmanage.service.ContentManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentManageServiceImpl implements ContentManageService {


    @Autowired
    private ContentMapper contentMapper;

    @Override
    public int addContent(Content content) {
        return contentMapper.insert(content);
    }

    @Override
    public int delContent(int id) {
        return contentMapper.deleteById(id);
    }

    @Override
    public int updateContent(Content content) {
        return contentMapper.updateById(content);
    }

    @Override
    public List<Content> queryContent() {
        return contentMapper.selectList(Wrappers.emptyWrapper());
    }
}
