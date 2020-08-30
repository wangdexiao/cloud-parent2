package com.study.contentmanage.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Liangzhifeng
 * date: 2018/9/4
 */
@Slf4j
@Component
public class LocalProcessor implements BeanPostProcessor {

    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;

//    @Autowired(required = false)
//    private MyOAuth2ClientContextFilter oAuth2ClientContextFilter;

//    private String targetBeanName = "oauth2ClientContextFilter";
    private static Map<String, Class> targetBeanInfo = new HashMap<>();
    static {
        targetBeanInfo.put("oauth2ClientContextFilter", MyOAuth2ClientContextFilter.class);
//        targetBeanInfo.put("jwtTokenEnhancer", AdditionalJwtAccessTokenConverter.class);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

//        if (StringUtils.endsWithIgnoreCase(beanName, targetBeanName)) {
//            boolean containsBean = defaultListableBeanFactory.containsBean(targetBeanName);
//            if (containsBean) {
//                //移除bean的定义和实例
//                defaultListableBeanFactory.removeBeanDefinition(targetBeanName);
//            }
//            //注册新的bean定义和实例
//            defaultListableBeanFactory.registerBeanDefinition(targetBeanName, BeanDefinitionBuilder.genericBeanDefinition(MyOAuth2ClientContextFilter.class).getBeanDefinition());
//            bean = null;
//            return new MyOAuth2ClientContextFilter();
//        }
//        return bean;


        if(targetBeanInfo.containsKey(beanName)){
            Class newBeanClass = targetBeanInfo.get(beanName);
            Object newBean = bean;
            boolean containsBean = defaultListableBeanFactory.containsBean(beanName);
            if (containsBean) {
                //移除bean的定义和实例
                defaultListableBeanFactory.removeBeanDefinition(beanName);
            }
            //注册新的bean定义和实例
            defaultListableBeanFactory.registerBeanDefinition(beanName, BeanDefinitionBuilder.genericBeanDefinition(targetBeanInfo.get(beanName)).getBeanDefinition());
            bean = null;
            try {
                newBean =  newBeanClass.newInstance();
                bean = null;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                log.error(beanName + "bean 替换失败!!!!!!!!!!");

            }
            return newBean;
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
