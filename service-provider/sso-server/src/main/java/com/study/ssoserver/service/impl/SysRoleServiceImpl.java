package com.study.ssoserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.ssoserver.entity.SysRole;
import com.study.ssoserver.mapper.SysRoleMapper;
import com.study.ssoserver.service.ISysRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wadexi
 * @since 2020-07-07
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

}
