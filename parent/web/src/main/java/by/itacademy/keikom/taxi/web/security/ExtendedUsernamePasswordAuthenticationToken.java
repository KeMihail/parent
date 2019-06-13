package by.itacademy.keikom.taxi.web.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ExtendedUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private Integer id;
	String username;

	public ExtendedUsernamePasswordAuthenticationToken(Integer id, Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities, String username) {
		super(principal, credentials, authorities);
		setId(id);

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
