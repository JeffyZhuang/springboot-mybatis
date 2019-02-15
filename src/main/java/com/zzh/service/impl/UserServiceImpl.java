package com.zzh.service.impl;

import com.zzh.RedisKeyPrefix;
import com.zzh.dto.RoleRulesDTO;
import com.zzh.mapper.RolePremissionMapper;
import com.zzh.mapper.UserMapper;
import com.zzh.po.User;
import com.zzh.redis.RedisHandle;
import com.zzh.service.UserService;
import com.zzh.vo.RegisterUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: zzh
 * @Description:
 * @Date: 2018/9/29
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolePremissionMapper rolePremissionMapper;

    @Autowired
    private RedisHandle redisHandle;

    @Override
    public User selectByPrimaryKey(int id) {
        User user = (User) redisHandle.getObject(RedisKeyPrefix.USER + id);
        if (user == null) {
            log.info("-------------调数据库-------------");
            user = userMapper.selectByPrimaryKey(id);
            redisHandle.setObject(RedisKeyPrefix.USER + id, user);
        }
        return user;
    }

    @Override
    public User selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public Map<String, String> getRules() {
        Map<String, String> rules;
        rules = redisHandle.getStringMap(RedisKeyPrefix.SHIRO_RULES);
        if (!rules.isEmpty()) {
            log.info("从缓存获取权限成功");
            return rules;
        }
        log.info("从缓存中获取权限失败");
        Map<String, String> rulesMap = new HashMap<>();
        List<RoleRulesDTO> roleRules = rolePremissionMapper.getRoleRules();
        if (!CollectionUtils.isEmpty(roleRules)) {
            for (RoleRulesDTO roleRulesDTO : roleRules) {
                String url = roleRulesDTO.getUrl();
                String roleCode = roleRulesDTO.getRoleCode();
                if (roleCode.contains("anon")) {
                    rulesMap.put(url, "anon");
                } else {
                    rulesMap.put(url, "roles[" + roleCode + "]");
                }
            }
        }
        log.info("rules数据为：" + rulesMap.toString());
        redisHandle.setMap(RedisKeyPrefix.SHIRO_RULES, rulesMap);
        return rulesMap;
    }

    @Override
    public int registerUser(RegisterUserVO registerUserVO) {
        User user = new User();
        user.setUsername(registerUserVO.getUserName());
        user.setPassword(registerUserVO.getPassword());
        user.setMail(registerUserVO.getEmail());
        user.setActiveCode(registerUserVO.getActiveCode());
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUserStatus(String activeCode) {
        return userMapper.updateUserStatus(activeCode);
    }
}
