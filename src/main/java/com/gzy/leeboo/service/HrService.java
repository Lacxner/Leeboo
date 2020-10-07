package com.gzy.leeboo.service;

import com.gzy.leeboo.dto.AuthenticateHr;
import com.gzy.leeboo.dto.BasicHr;
import com.gzy.leeboo.dto.ChatHr;
import com.gzy.leeboo.dto.ResetPassword;
import com.gzy.leeboo.entity.Hr;
import com.gzy.leeboo.mapper.HrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CacheConfig(cacheNames = "hr")
@Service
public class HrService implements UserDetailsService {
    private HrMapper hrMapper;

    @Autowired
    public void setHrMapper(HrMapper hrMapper){
        this.hrMapper = hrMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.getHrByUsername(username);
        // 这里的 UsernameNotFoundException 默认会被封装成 BadCredentialsException 异常，最终被 ExceptionTranslationFilter 拦截处理
        if (hr == null) {
            throw new UsernameNotFoundException("用户名或密码错误！");
        }
        return hr;
    }

    @Cacheable(key = "'allHrs'")
    public List<BasicHr> getAllHrs() {
        return hrMapper.getAllHrs();
    }

    public Integer getHrCountsByRoleId(Integer roleId) {
        return hrMapper.getHrCountsByRoleId(roleId);
    }

    public Integer getHrCountsByRoleIds(List<Integer> roleIds) {
        return hrMapper.getHrCountsByRoleIds(roleIds);
    }

    public List<ChatHr> getAllHrsWithoutMyself(Integer id) {
        return hrMapper.getAllHrsWithoutMyself(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allHrs'")
    public Boolean addHr(AuthenticateHr authenticateHr) {
        return hrMapper.addHr(authenticateHr);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allHrs'")
    public Boolean updateHr(Hr hr) {
        return hrMapper.updateHr(hr);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean updateHrPasswordByAdmin(ResetPassword resetPassword) {
        return hrMapper.updateHrPasswordByAdmin(resetPassword);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allHrs'")
    public Boolean updateHrEnabled(BasicHr basicHr) {
        return hrMapper.updateHrEnabled(basicHr);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allHrs'")
    public Boolean deleteHrById(Integer id) {
        return hrMapper.deleteHrById(id);
    }
}
