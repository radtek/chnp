package chnp.manager.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomRealm extends AuthorizingRealm {

//    @Autowired
//    private TsUserService tsUserService;

    /**<p>用户认证</p>
     * <p></p>
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        return null;
    }

    /**<p>权限验证</p>
     * <p>
     * （从数据库或缓存中）获取当前用户的角色和权限并集成到{@code AuthorizationInfo}实例中，
     * 当需要验证用户是否存在指定角色或权限时，会调用本方法获取用户的角色集合与权限集合。
     * </p>
     *
     * <p>以下情况会调用本方法：</p>
     * <ul>
     *     <li>调用Subject.hasRole(String)</li>
     *     <li>调用Subject.isPermitted(String)</li>
     *     <li>注解@RequiresRoles(String...)</li>
     *     <li>标签shiro:hasPermission</li>
     * </ul>
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}