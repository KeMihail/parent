package by.itacademy.keikom.taxi.web.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.Driver;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.services.IDriverServices;
import by.itacademy.keikom.taxi.services.IUserServices;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private IUserServices userService;
	@Autowired
	private IDriverServices driverService;
	private List<String> list = new ArrayList<String>();
	private Boolean choice = false;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getPrincipal() + "";
		String password = authentication.getCredentials() + "";

		list = userService.loadAllEmail();

		for (String str : list) {
			if (str.equals(email)) {
				choice = true;
				break;
			}
		}

		if (choice) {

			User user = userService.loadByLogin(email);

			if (!user.getEmail().equals(email) && !user.getPassword().equals(DigestUtils.md5Hex(password))) {
				throw new BadCredentialsException("1000");
			}
			if (user.getDeleted().equals(true)) { // locked user
				throw new DisabledException("1001");
			}

			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

			Integer userId = user.getId();
			String username = user.getName();
			choice = false;

			return new ExtendedUsernamePasswordAuthenticationToken(userId, email, password, roles, username);
		} else {

			Driver driver = driverService.loadByLogin(email);

			if (!driver.getEmail().equals(email) && !driver.getPassword().equals(DigestUtils.md5Hex(password))) {
				throw new BadCredentialsException("1000");
			}
			if (driver.getDeleted().equals(true)) { // locked user
				throw new DisabledException("1001");
			}

			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(new SimpleGrantedAuthority("ROLE_" + driver.getRole()));

			Integer userId = driver.getId();
			String username = driver.getName();
			return new ExtendedUsernamePasswordAuthenticationToken(userId, email, password, roles, username);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
