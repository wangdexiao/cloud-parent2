package com.study.ssoserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.ssoserver.entity.OauthClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class MyClientDetailsService implements ClientDetailsService {

    @Autowired
    private IOauthClientDetailsService clientDetailsService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_id", clientId);
        OauthClientDetails clientInfo = clientDetailsService.getOne(queryWrapper);
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        String secret = clientInfo.getClientSecret();
        String[] resourceIds = clientInfo.getResourceIds().split(",");
        String[] authTypes = clientInfo.getAuthorizedGrantTypes().split(",");
        int accessTokenValiditySeconds = clientInfo.getAccessTokenValidity();
        int refreshTokenValiditySeconds = clientInfo.getRefreshTokenValidity();
        String[] scopes = clientInfo.getScope().split(",");
        Boolean autoApprove = clientInfo.isAutoapprove();
        String[] rediectUris = clientInfo.getWebServerRedirectUri().split(",");
        List<String> authorities = Arrays.asList(clientInfo.getAuthorities().split(","));

        baseClientDetails.setClientId(clientId);
        baseClientDetails.setClientSecret(secret);
        baseClientDetails.setResourceIds(Arrays.asList(resourceIds));
        baseClientDetails.setAuthorizedGrantTypes(Arrays.asList(authTypes));
        baseClientDetails.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
        baseClientDetails.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
        baseClientDetails.setScope(Arrays.asList(scopes));
        if(autoApprove){
            baseClientDetails.setAutoApproveScopes(Arrays.asList(scopes));
        }
        baseClientDetails.setRegisteredRedirectUri(new HashSet<>(Arrays.asList(rediectUris)));
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        authorities.forEach(it -> grantedAuthorityList.add(new SimpleGrantedAuthority(it)));
        baseClientDetails.setAuthorities(grantedAuthorityList);
        return baseClientDetails;
    }
}
