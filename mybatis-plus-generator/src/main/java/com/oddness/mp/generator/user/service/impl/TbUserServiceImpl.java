package com.oddness.mp.generator.user.service.impl;

import com.oddness.mp.generator.user.entity.TbUser;
import com.oddness.mp.generator.user.mapper.TbUserMapper;
import com.oddness.mp.generator.user.service.ITbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zimu
 * @since 2020-10-30
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

}
