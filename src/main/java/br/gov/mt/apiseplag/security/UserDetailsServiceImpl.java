package br.gov.mt.apiseplag.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aqui você deve buscar o usuário no banco de dados
        if ("admin".equals(username)) {
            return new User("admin", "{noop}password", new ArrayList<>()); // Senha sem encode
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }
}
