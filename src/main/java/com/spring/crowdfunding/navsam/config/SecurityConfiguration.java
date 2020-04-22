
package com.spring.crowdfunding.navsam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.filter.CorsFilter;

import com.spring.crowdfunding.navsam.security.jwt.JWTConfigurer;
import com.spring.crowdfunding.navsam.security.jwt.TokenProvider;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
@EnableWebSecurity
@Getter
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private TokenProvider tokenProvider;

	private CorsFilter corsFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	public void configure(final WebSecurity web) {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/app/**/*.{js,html}");
//	            .antMatchers("/i18n/**")
//	            .antMatchers("/content/**")
//	            .antMatchers("/h2-console/**")
//	            .antMatchers("/swagger-ui/index.html")
//	            .antMatchers("/test/**");
	}

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		// @formatter:off
		http.csrf().disable().addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().and().headers()
				.contentSecurityPolicy(
						"default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:")
				.and().referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN).and()
				.featurePolicy(
						"geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'self'; payment 'none'")
				.and().frameOptions().deny().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/api/authenticate").permitAll()
				.antMatchers("/user/**").authenticated()
//				.antMatchers("/api/register").permitAll()
//				.antMatchers("/api/activate").permitAll().antMatchers("/api/account/reset-password/init").permitAll()
//				.antMatchers("/api/account/reset-password/finish").permitAll().antMatchers("/api/**").authenticated()
//				.antMatchers("/management/health").permitAll().antMatchers("/management/info").permitAll()
//				.antMatchers("/management/prometheus").permitAll().antMatchers("/management/**")
//				.hasAuthority(AuthoritiesConstants.ADMIN)
				.and().httpBasic().and().apply(securityConfigurerAdapter());
		// @formatter:on
	}

	private JWTConfigurer securityConfigurerAdapter() {
		return new JWTConfigurer(tokenProvider);
	}
	@Autowired
	public void setTokenProvider(final TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Autowired
	public void setCorsFilter(final CorsFilter corsFilter) {
		this.corsFilter = corsFilter;
	}

}
