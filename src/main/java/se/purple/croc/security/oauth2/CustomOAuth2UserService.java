package se.purple.croc.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import se.purple.croc.domain.Users;
import se.purple.croc.exception.OAuth2AuthenticationProcessingException;
import se.purple.croc.security.UserPrincipal;
import se.purple.croc.repository.UserRepository;
import se.purple.croc.security.oauth2.user.OAuth2UserInfo;
import se.purple.croc.security.oauth2.user.OAuth2UserInfoFactory;
import se.purple.croc.service.UserService;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserService userService;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

		try {
			return processOAuth2User(oAuth2UserRequest, oAuth2User);
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			// Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
		}
	}

//  NOTE: in the future we want multiple providers
//	private void checkProvider(Users user, OAuth2UserRequest oAuth2UserRequest) {
//		if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
//			throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
//					user.getProvider() + " account. Please use your " + user.getProvider() +
//					" account to login.");
//		}
//	}

	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
		if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}

		Optional<Users> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
		String email = oAuth2UserInfo.getEmail();
		Users user;
		if(userOptional.isPresent()) {
			user = userOptional.get();
		} else {
			user = registerNewUser(oAuth2UserInfo);
		}

		return UserPrincipal.create(user, oAuth2User.getAttributes());
	}

	private Users registerNewUser(OAuth2UserInfo oAuth2UserInfo) {
		return userService.registerNewUser(oAuth2UserInfo.getEmail());
	}
}
