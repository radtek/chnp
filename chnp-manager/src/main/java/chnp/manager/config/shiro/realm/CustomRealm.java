package chnp.manager.config.shiro.realm;

import chnp.common.utils.StringUtils;
import chnp.manager.common.service.UtilService;
import chnp.manager.mvc.model.domain.TsUser;
import chnp.manager.mvc.service.TsUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private UtilService utilService;
    @Autowired
    private TsUserService tsUserService;

    /**<p>用户认证</p>
     * <p>
	 *     当调用Subject.login()时会调用本方法获取指定用户的认证信息，并匹配登陆信息。
     * </p>
     *
     * @param authenticationToken 用户登陆信息
     * @return 用户认证信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

		TsUser tsUser = tsUserService.getByUserName(token.getUsername());
		if (null == tsUser || StringUtils.isEmpty(tsUser.getUserPswd())) throw new UnknownAccountException("账号或密码错误");

		Object vericode = utilService.getSession().getAttribute("verificationCode");

		String pswd;
		try {
			pswd = StringUtils.MD5Encode(tsUser.getUserPswd() + ((String) vericode).toLowerCase());
		}catch (Exception e) {
			throw new AuthenticationException("内部错误");
		}

		return new SimpleAuthenticationInfo(token.getUsername(), pswd, getName());
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
     * @return 当前用户的角色和权限集合
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		Set<String> permissions = new HashSet<>();
		permissions.add("tsmodule");
		permissions.add("tsmodule_query");
		System.out.println("*********************");

		//authorizationInfo.setRoles();
		authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }
}