package com.study.contentmanage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenConfig {

//    /**
//     * jdbc 令牌
//     * 1. 创建相关数据表
//     * 2. 添加jdbc相关依赖
//     * 3. 配置数据源信息
//     */
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource(){
//        return new DruidDataSource();
//    }


//    /**
//     * jwt令牌
//     */
    private static final String SIGNING_KEY = "my-secret";

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //堆成密钥来签署我们的令牌，资源服务器也将使用此密钥来验证准确性
        converter.setSigningKey(SIGNING_KEY);
//        ClassPathResource classPathResource = new ClassPathResource("public.txt");
//        String publicKey = "";
//
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(classPathResource.getInputStream(), StandardCharsets.UTF_8));
//
//            String tempString;
//
//            while (!StringUtils.isEmpty(tempString = bufferedReader.readLine())) {
//                publicKey += tempString;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public TokenStore tokenStore(){
        //redis 管理令牌
//        return new RedisTokenStore(RedisConnectionFactory)
        //jdbc管理令牌
//        return new JdbcTokenStore(dataSource());
        //jwt 管理令牌
        return new JwtTokenStore(jwtAccessTokenConverter());
    }




}
