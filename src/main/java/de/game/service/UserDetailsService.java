package de.game.service;

import de.game.model.entity.User;
import de.game.model.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepo;

    @Transactional
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByloginNameWithUserRoles(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Kein Benutzer mit diesem Namen gefunden");
        }
        return user.get();
    }
}
