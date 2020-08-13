package com.study.ssoserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.ssoserver.entity.OauthClientDetails;
import com.study.ssoserver.mapper.OauthClientDetailsMapper;
import com.study.ssoserver.service.IOauthClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wadexi
 * @since 2020-07-06
 */
@Service
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {


}
