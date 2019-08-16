package se.purple.croc.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import se.purple.croc.exception.BadRequestException;
import se.purple.croc.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import se.purple.croc.service.TokenAuthenticationService;
import se.purple.croc.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static se.purple.croc.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Value("${app.oauth2.authorizedRedirectUris}")
	String redirectUri;


	private TokenAuthenticationService tokenProvider;
	private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

	@Autowired
	OAuth2AuthenticationSuccessHandler(TokenAuthenticationService tokenProvider,
									   HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
		this.tokenProvider = tokenProvider;
//		this.appProperties = appProperties;
		this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(request, response, authentication);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		clearAuthenticationAttributes(request, response);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
				.map(Cookie::getValue);

		if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
			throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
		}

		String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

		String token = tokenProvider.createToken(authentication);

		return UriComponentsBuilder.fromUriString(targetUrl)
				.queryParam("token", token)
				.build().toUriString();
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
		super.clearAuthenticationAttributes(request);
		httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
	}

	private boolean isAuthorizedRedirectUri(String uri) {
		URI clientRedirectUri = URI.create(uri);

		// TODO get from app.props
		List<String> redirectUris = new ArrayList<>();
//		redirectUris.add("http://localhost:3000/oauth2/redirect");
		redirectUris.add(redirectUri);

		// return appProperties.getOauth2().getAuthorizedRedirectUris()
		return redirectUris
				.stream()
				.anyMatch(authorizedRedirectUri -> {
					// Only validate host and port. Let the clients use different paths if they want to
					URI authorizedURI = URI.create(authorizedRedirectUri);
					if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
							&& authorizedURI.getPort() == clientRedirectUri.getPort()) {
						return true;
					}
					return false;
				});
	}
}
