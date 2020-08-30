package com.study.ssoserver.config;

import com.netflix.discovery.converters.Auto;
import com.study.ssoserver.service.MyClientDetailsService;
import com.study.ssoserver.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer //开启授权服务
public class AuthorizationServiceConfig extends AuthorizationServerConfigurerAdapter {

    //认证方式管理器 表示支持password认证模式
    @Autowired
    AuthenticationManager authenticationManager;

    //redis 连接工厂 登陆成功后的token需要存在redis里面，因为redis里面有过期机制
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    //用户详细信息服务 里面存放着用户信息
    @Autowired
    MyUserService userDetailsService;

    @Resource
    private MyClientDetailsService myClientDetailsService;

    @Resource
    private DataSource dataSource;

    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Resource
    private TokenStore tokenStore;

    @Autowired
    private CustomeTokenEnhancer customeTokenEnhancer;

//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("admin"));
//        System.out.println(new BCryptPasswordEncoder().encode("test"));
//    }

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        JdbcClientDetailsServiceBuilder builder = new JdbcClientDetailsServiceBuilder();
        ClientDetailsService clientDetailsService =
                builder.dataSource(dataSource)
                        .passwordEncoder(passwordEncoder)
                        .build();

        clients.withClientDetails(clientDetailsService);
//        clients.withClientDetails(myClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(customeTokenEnhancer);
        enhancers.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancers);

        endpoints.tokenStore(tokenStore)
                .tokenEnhancer(enhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter)
                //身份认证管理
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
//                .authorizationCodeServices()
                ;


    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许客户端表单身份验证
        security
                ///oauth/check_token 端点你需要在授权服务将这个端点暴露出去，以便资源服务可以进行访问，
                .tokenKeyAccess("permitAll()")// /oauth/token_key 安全配置
                .checkTokenAccess("isAuthenticated()") // /oauth/check_token 安全配置
                .allowFormAuthenticationForClients();
    }


//    /oauth/authorize ：申请授权码 code, 涉及的类 AuthorizationEndpoint
//  /oauth/token ：获取令牌 token, 涉及的类 TokenEndpoint
//  /oauth/check_token ：用于资源服务器请求端点来检查令牌是否有效, 涉及的类 CheckTokenEndpoint
//  /oauth/confirm_access ：用户确认授权提交, 涉及的类 WhitelabelApprovalEndpoint
//  /oauth/error ：授权服务错误信息, 涉及的类 WhitelabelErrorEndpoint
//  /oauth/token_key ：提供公有密匙的端点，使用 JWT 令牌时会使用 , 涉及的类 TokenKeyEndpoint


}
