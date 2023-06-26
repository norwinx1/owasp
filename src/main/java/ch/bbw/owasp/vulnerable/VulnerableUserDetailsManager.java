package ch.bbw.owasp.vulnerable;

import ch.bbw.owasp.ImmutableUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class VulnerableUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    private final Set<ImmutableUser> users = new HashSet<>();

    public VulnerableUserDetailsManager(UserDetails... users) {
        for (UserDetails user : users) {
            createUser(user);
        }
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return user;
    }

    @Override
    public void createUser(UserDetails user) {
        users.add(new ImmutableUser(user.getUsername(), user.getPassword(), user.getAuthorities()));
    }

    @Override
    public void updateUser(UserDetails user) {
    }

    @Override
    public void deleteUser(String username) {
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
    }

    @Override
    public boolean userExists(String username) {
        return users.stream().anyMatch(userDetails -> userDetails.getUsername().equals(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = users.stream().filter(userDetails -> userDetails.getUsername().equals(username)).findFirst().orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        log.info("Password of user '{}': {}", user.getUsername(), user.getPassword());
        return user;
    }
}
