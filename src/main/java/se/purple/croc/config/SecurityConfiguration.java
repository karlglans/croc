package se.purple.croc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;
import se.purple.croc.security.TokenAuthenticationFilter;
import se.purple.croc.security.TokenAuthenticationProvider;
import se.purple.croc.security.oauth2.CustomOAuth2UserService;
import se.purple.croc.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import se.purple.croc.security.oauth2.OAuth2AuthenticationFailureHandler;
import se.purple.croc.security.oauth2.OAuth2AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private TokenAuthenticationProvider provider;
	private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
	private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
	private final CustomOAuth2UserService customOAuth2UserService;

	@Autowired
	SecurityConfiguration(final TokenAuthenticationProvider provider, OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler, OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler, CustomOAuth2UserService customOAuth2UserService) {
		super();
		this.provider = provider;
		this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
		this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
		this.customOAuth2UserService = customOAuth2UserService;
	}

	private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
			new AntPathRequestMatcher("/graphql")
	);


	/*
      By default, Spring OAuth2 uses HttpSessionOAuth2AuthorizationRequestRepository to save
      the authorization request. But, since our service is stateless, we can't save it in
      the session. We'll save the request in a Base64 encoded cookie instead.
    */
	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}

	@Bean
	public CustomFilter customFilter() {
		return new CustomFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// TODO: find some way of removing DefaultLoginPageGeneratingFilter
		// it produces a login page on /login
		http
				.cors()
					.and()
				.csrf()
					.disable()
				.formLogin()
					.disable()
				.httpBasic()
					.disable()
				.exceptionHandling()
					.defaultAuthenticationEntryPointFor(forbiddenEntryPoint(), PROTECTED_URLS)
					.and()
				.sessionManagement()
					.sessionCreationPolicy(STATELESS)
					.and()
				.authorizeRequests()
					.antMatchers("/",
						"/favicon.ico", "/manifest.json",
						"/**/*.png",
						"/**/*.gif",
						"/**/*.svg",
						"/**/*.jpg",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js")
						.permitAll()
//					.antMatchers("/auth/**", "/oauth2/**")
//						.permitAll()
					.antMatchers("/ex")
						.permitAll()
					.anyRequest()
						.authenticated()
					.and()
				.oauth2Login()
					.authorizationEndpoint()
					.baseUri("/oauth2/authorize")
					.authorizationRequestRepository(cookieAuthorizationRequestRepository())
					.and()
				.redirectionEndpoint()
					.baseUri("/oauth2/callback/*")
					.and()
				.userInfoEndpoint()
					.userService(customOAuth2UserService)
					.and()
				.successHandler(oAuth2AuthenticationSuccessHandler)
				.failureHandler(oAuth2AuthenticationFailureHandler);


		http.addFilterBefore(restAuthenticationFilter(), AnonymousAuthenticationFilter.class);
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(provider);
	}


	private TokenAuthenticationFilter restAuthenticationFilter() throws Exception {
		final TokenAuthenticationFilter filter = new TokenAuthenticationFilter(PROTECTED_URLS);
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(successHandler());
		return filter;
	}

	@Bean
	SimpleUrlAuthenticationSuccessHandler successHandler() {
		final SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
		successHandler.setRedirectStrategy(new NoRedirectStrategy());
		return successHandler;
	}

	@Bean
	AuthenticationEntryPoint forbiddenEntryPoint() {
		return new HttpStatusEntryPoint(FORBIDDEN);
	}

	public class CustomFilter extends GenericFilterBean {

		@Override
		public void doFilter(
				ServletRequest request,
				ServletResponse response,
				FilterChain chain) throws IOException, ServletException {
			chain.doFilter(request, response);
		}
	}
}
