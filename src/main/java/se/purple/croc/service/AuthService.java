package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.Users;
import se.purple.croc.models.AuthenticatedUser;
import se.purple.croc.repository.UserRepository;
import se.purple.croc.service.exceptions.MissingData;

import java.util.Optional;

@Service
public class AuthService {
	@Autowired
	UserRepository userRepo;

	public AuthenticatedUser getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (AuthenticatedUser) authentication.getPrincipal();
	}

	Users getUser(int userId) {
		Optional<Users> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			throw new MissingData(String.format("cant find user %u", userId));
		}
		return user.get();
	}

	Users getUser(AuthenticatedUser authUser) {
		Optional<Users> user = userRepo.findById((int)authUser.getUserId());
		if (!user.isPresent()) {
			throw new MissingData(String.format("cant find user %u", authUser.getUserId()));
		}
		return user.get();
	}
}
