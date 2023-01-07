package com.katanemimena.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.katanemimena.project.entity.User;
import com.katanemimena.project.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));

        return UserDetailsImpl.build(user);
    }

}