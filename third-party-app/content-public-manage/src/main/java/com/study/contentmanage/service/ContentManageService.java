package com.study.contentmanage.service;

import com.study.contentmanage.bean.Content;

import java.util.List;

public interface ContentManageService {


    /**
     * 添加内容
     * @return
     */
    public default int addContent(Content content){
        return -1;
    }

    /**
     * 删除内容
     * @return
     */
    public default int delContent(int id){
        return -1;
    }


    /**
     * 修改内容
     * @return
     */
    public default int updateContent(Content content) {
        return -1;
    }

    /**
     * 查寻内容
     * @return
     */
    public default List<Content> queryContent(){
        return null;
    }




}
