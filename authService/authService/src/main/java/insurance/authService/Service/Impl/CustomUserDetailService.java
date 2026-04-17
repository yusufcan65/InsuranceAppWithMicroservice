package insurance.authService.Service.Impl;

import insurance.authService.Entity.AuthUser;
import insurance.authService.Entity.CustomUserDetails;
import insurance.authService.Repository.AuthUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AuthUserRepository repository;

    public CustomUserDetailService(AuthUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> user = repository.findByUsername(username);
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

}
