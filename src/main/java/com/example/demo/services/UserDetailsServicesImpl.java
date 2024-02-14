package com.example.demo.services;

import com.example.demo.models.Rol;
import com.example.demo.models.Usuario;
import com.example.demo.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario appUser = usuarioRepository.findByUsername(username);
        Set<GrantedAuthority> grandList = new HashSet<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUser.getRol().getName());
        grandList.add(grantedAuthority);
        UserDetails user = null;
        user = (UserDetails) new User(username, appUser.getPassword(), grandList);
        return user;
    }
}
