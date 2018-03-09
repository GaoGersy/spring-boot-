package com.piesat.project.shiro;

import com.piesat.project.common.utils.ShiroUtils;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class UserRealm extends AuthorizingRealm {
//    @Autowired
//    private SysAuthorizeService sysAuthorizeService;
//    @Autowired
//    private SysUserService sysUserService;
//    @Autowired
//    private SysSessionService sysSessionService;
//    private RedisSessionDAO sessionDAO;
//
//    public void setSessionDAO(RedisSessionDAO sessionDAO) {
//        this.sessionDAO = sessionDAO;
//    }

    // 权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String userId = ShiroUtils.getUserId();
//        List<?> list = sysAuthorizeService.queryPermissionByUserId(userId);
//        for (Object permission : list) {
//            if (StringUtils.isNotBlank((String) permission)) {
//                // 添加基于Permission的权限信息
//                info.addStringPermission((String) permission);
//            }
//        }
        // 添加用户权限
//        info.addStringPermission("user");
        return info;
    }

    // 登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("enable", 1);
        params.put("account", token.getUsername());
//        List<?> list = sysUserService.queryList(params);
//        if (list.size() == 1) {
//            SysUser user = (SysUser) list.get(0);
//            StringBuilder sb = new StringBuilder(100);
//            for (int i = 0; i < token.getPassword().length; i++) {
//                sb.append(token.getPassword()[i]);
//            }
//            if (user.getPassword().equals(sb.toString())) {
//                WebUtil.saveCurrentUser(user.getId());
//                saveSession(user.getAccount(), token.getHost());
//                AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(),
//                        user.getUserName());
//                return authcInfo;
//            }
//            SuperLogger.warn("USER ["+token.getUsername()+"] PASSWORD IS WRONG: "+ sb.toString());
//            return null;
//        } else {
//            logger.warn("No user: {}", token.getUsername());
//            return null;
//        }
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("aaa", "aaa",
                "aaa");
        return authcInfo;
//        throw new RuntimeException("用户名或密码错误");
    }

//    /** 保存session */
//    private void saveSession(String account, String host) {
//        // 踢出用户
//        SysSession record = new SysSession();
//        record.setAccount(account);
//        List<?> sessionIds = sysSessionService.querySessionIdByAccount(record);
//        Subject currentUser = SecurityUtils.getSubject();
//        Session session = currentUser.getSession();
//        String currentSessionId = session.getId().toString();
//        if (sessionIds != null) {
//            for (Object sessionId : sessionIds) {
//                record.setSessionId((String) sessionId);
//                sysSessionService.deleteBySessionId(record);
//                if (!currentSessionId.equals(sessionId)) {
//                    sessionDAO.delete((String) sessionId);
//                }
//            }
//        }
//        // 保存用户
//        record.setSessionId(currentSessionId);
//        record.setIp(StringUtils.isBlank(host) ? session.getHost() : host);
//        record.setStartTime(session.getStartTimestamp());
//        sysSessionService.update(record);
//    }
}
