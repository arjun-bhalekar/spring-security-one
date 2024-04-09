package com.learn.security.springsecurityone.security;

import com.learn.security.springsecurityone.entity.User;
import com.learn.security.springsecurityone.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUseDetailService implements UserDetailsService {

    private final static Logger logger = LoggerFactory.getLogger(CustomUseDetailService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("inside loadUserByUsername with username : {} ", username);

        Optional<User> optionalUser =  userRepository.findByUsername(username);
        return optionalUser.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
