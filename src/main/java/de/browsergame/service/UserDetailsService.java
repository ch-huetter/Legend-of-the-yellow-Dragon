package de.browsergame.service;

import de.browsergame.model.entity.User;
import de.browsergame.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByloginName(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Kein Benutzer mit diesem Namen gefunden");
        }
        return user.get();

    }
}
