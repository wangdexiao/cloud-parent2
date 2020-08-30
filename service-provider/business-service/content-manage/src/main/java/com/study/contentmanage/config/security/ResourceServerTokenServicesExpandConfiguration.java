package com.study.contentmanage.config.security;

import com.study.base.mybatisplus.entity.UserInfoEntity;
import com.study.contentmanage.security.AdditionalJwtAccessTokenConverter;
import com.study.contentmanage.security.PrincipalConverter;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterRestTemplateCustomizer;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ResourceServerTokenServicesExpandConfiguration {

    /**
     * 默认Principal 只包含用户名
     * @return
     */
//    @Bean
//    @ConditionalOnMissingBean(PrincipalConverter.class)
//    public PrincipalConverter defaultPrincipalConverter() {
//        return new PrincipalConverter() {};
//    }

    @Bean
    public PrincipalConverter principalConverter() {
        return new PrincipalConverter() {
            @Override
            public Object converter(Map<String, ?> map) {
                return ProcessPrincipalHelper.converter(map);
            }
        };
    }

    public static class ProcessPrincipalHelper{
        private static final String USER_NAME_KEY = "username";

        static Logger logger = LoggerFactory.getLogger(ProcessPrincipalHelper.class);
        public static Object converter(Map<String, ?> map) {
            Map<String, Object> params = new HashMap<String, Object>();
            for(String key : map.keySet()) {
                params.put(key, map.get(key));
            }

            UserInfoEntity userInfo = new UserInfoEntity((String) map.get(UserAuthenticationConverter.USERNAME));
            try {
                 BeanUtils.populate(userInfo, params);
                logger.info("Principal 组装成功!");
            } catch (IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
                logger.error("Principal 组装失败,只返回username");
            }
            return userInfo;
        }
    }




    private final ResourceServerProperties resource;

    private final List<JwtAccessTokenConverterConfigurer> configurers;

    private final List<JwtAccessTokenConverterRestTemplateCustomizer> customizers;

    public ResourceServerTokenServicesExpandConfiguration(ResourceServerProperties resource,
                                         ObjectProvider<List<JwtAccessTokenConverterConfigurer>> configurers,
                                         ObjectProvider<List<JwtAccessTokenConverterRestTemplateCustomizer>> customizers) {
        this.resource = resource;
        this.configurers = configurers.getIfAvailable();
        this.customizers = customizers.getIfAvailable();
    }

//    @Bean
//    @ConditionalOnMissingBean(ResourceServerTokenServices.class)
//    public DefaultTokenServices jwtTokenServices(TokenStore jwtTokenStore) {
//        DefaultTokenServices services = new DefaultTokenServices();
//        services.setTokenStore(jwtTokenStore);
//        return services;
//    }

    @Primary
    @Bean("expandJwtTokenStore")
    @ConditionalOnMissingBean(TokenStore.class)
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Primary
    @Bean("expandJwtTokenEnhancer")
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter = new AdditionalJwtAccessTokenConverter(principalConverter());
        String keyValue = this.resource.getJwt().getKeyValue();
        if (!StringUtils.hasText(keyValue)) {
            keyValue = getKeyFromServer();
        }
        if (StringUtils.hasText(keyValue) && !keyValue.startsWith("-----BEGIN")) {
            converter.setSigningKey(keyValue);
        }
        if (keyValue != null) {
            converter.setVerifierKey(keyValue);
        }
        if (!CollectionUtils.isEmpty(this.configurers)) {
            AnnotationAwareOrderComparator.sort(this.configurers);
            for (JwtAccessTokenConverterConfigurer configurer : this.configurers) {
                configurer.configure(converter);
            }
        }
        return converter;
    }

    private String getKeyFromServer() {
        RestTemplate keyUriRestTemplate = new RestTemplate();
        if (!CollectionUtils.isEmpty(this.customizers)) {
            for (JwtAccessTokenConverterRestTemplateCustomizer customizer : this.customizers) {
                customizer.customize(keyUriRestTemplate);
            }
        }
        HttpHeaders headers = new HttpHeaders();
        String username = this.resource.getClientId();
        String password = this.resource.getClientSecret();
        if (username != null && password != null) {
            byte[] token = Base64.getEncoder()
                    .encode((username + ":" + password).getBytes());
            headers.add("Authorization", "Basic " + new String(token));
        }
        HttpEntity<Void> request = new HttpEntity<>(headers);
        String url = this.resource.getJwt().getKeyUri();
        return (String) keyUriRestTemplate
                .exchange(url, HttpMethod.GET, request, Map.class).getBody()
                .get("value");
    }


}
