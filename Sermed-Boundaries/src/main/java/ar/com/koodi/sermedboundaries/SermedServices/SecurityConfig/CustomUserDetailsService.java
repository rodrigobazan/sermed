package ar.com.koodi.sermedboundaries.SermedServices.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserPrincipal loadUserByUsername(String login) throws UsernameNotFoundException {
        final List<UserPrincipal> usuarios = Arrays.asList(
                /*new UserPrincipal(1L, "usuario","usuario", "usuario@sermed.com", passwordEncoder.encode("123456"), roles("USER")),
                new UserPrincipal(2L, "admin","admin", "admin@sermed.com", passwordEncoder.encode("123456"), roles("ADMIN"))*/
                new UserPrincipal(1L, "usuario", passwordEncoder.encode("123456"), roles("ROLE_USER")),
                new UserPrincipal(2L, "admin", passwordEncoder.encode("123456"), roles("ROLE_ADMIN"))
        );
        UserPrincipal buscado = usuarios.stream().filter(userPrincipal -> userPrincipal.getUsername().equals(login)).findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));

        return buscado;
    }


    @Transactional
    public UserDetails loadUserById(Long id) {
        final List<UserPrincipal> usuarios = Arrays.asList(
                /*new UserPrincipal(1L, "userPrincipal","userPrincipal", "userPrincipal@sermed.com", passwordEncoder.encode("123456"), roles("USER")),
                new UserPrincipal(2L, "admin","admin", "admin@sermed.com", passwordEncoder.encode("123456"), roles("ADMIN"))*/
                new UserPrincipal(1L, "userPrincipal", passwordEncoder.encode("123456"), roles("ROLE_USER")),
                new UserPrincipal(2L, "admin", passwordEncoder.encode("123456"), roles("ROLE_ADMIN"))
        );
        UserPrincipal userPrincipal = usuarios.stream().filter(us -> us.getId().floatValue() == id)
                .findFirst().orElseThrow(() -> new UsernameNotFoundException("No existe el userPrincipal"));

        return new User(userPrincipal.getUsername(), userPrincipal.getPassword(), userPrincipal.getAuthorities());
    }

    public List<GrantedAuthority> roles(String nombre) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(nombre));
        return authorities;
    }

}
