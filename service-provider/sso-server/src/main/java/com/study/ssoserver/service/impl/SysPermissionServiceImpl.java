package com.study.ssoserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.ssoserver.entity.SysPermission;
import com.study.ssoserver.mapper.SysPermissionMapper;
import com.study.ssoserver.service.ISysPermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author wadexi
 * @since 2020-07-07
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

}
