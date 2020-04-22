
package com.spring.crowdfunding.navsam.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * Nr.   Name       Date         Release/Description
 *---------------------------------------------------
 * 02
 * 01   Sarat     22 04 2020      New Class
 *
 * @author Sarat
 *
 *
 */
@Component
@Slf4j
@Getter
@NoArgsConstructor
public class TokenProvider implements InitializingBean {
	private static final String AUTHORITIES_KEY = "auth";

	private static final String SECRET_KEY_PATH = "spring.security.secret";
	private static final String BASE64_SECRET_KEY_PATH = "spring.security.base64-secret";
	private static final String RESERT_TIME_PATH = "spring.security.token-validity-in-seconds";
	private static final String RESERT_TIME_REMEMBER_ME_PATH = "spring.security.token-validity-in-seconds-for-remember-me";

	private Key key;

	private long tokenValidityInMilliseconds;

	private long tokenValidityInMillisecondsForRememberMe;

	private Environment env;

	public TokenProvider(final Environment env) {
		this.env = env;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		byte[] keyBytes;

		String secret = getEnv().getProperty(SECRET_KEY_PATH);
		if (!StringUtils.isEmpty(secret)) {
			log.warn("Warning: the JWT key used is not Base64-encoded. "
					+ "We recommend using the `.jwt.base64-secret` key for optimum security.");
			keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		} else {
			log.debug("Using a Base64-encoded JWT secret key");
			keyBytes = Decoders.BASE64.decode(getEnv().getProperty(BASE64_SECRET_KEY_PATH));
		}
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.tokenValidityInMilliseconds = 1000 * (Long.valueOf(getEnv().getProperty(RESERT_TIME_PATH)));

		this.tokenValidityInMillisecondsForRememberMe = 1000
				* (Long.valueOf(getEnv().getProperty(RESERT_TIME_REMEMBER_ME_PATH)));
	}

	public String createToken(final Authentication authentication, final boolean rememberMe) {
		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		long now = (new Date()).getTime();

		Date validity;

		if (rememberMe) {
			validity = new Date(now + getTokenValidityInMillisecondsForRememberMe());
		} else {
			validity = new Date(now + getTokenValidityInMilliseconds());
		}

		return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities)
				.signWith(key, SignatureAlgorithm.HS512).setExpiration(validity).compact();
	}

	public Authentication getAuthentication(final String token) {
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

	public boolean validateToken(final String token) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			log.info("Invalid JWT token.");
			log.trace("Invalid JWT token trace.", e);
		}
		return false;
	}

	@Autowired
	public void setEnv(final Environment env) {
		this.env = env;
	}

}
