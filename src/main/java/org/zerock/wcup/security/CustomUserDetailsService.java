package org.zerock.wcup.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.wcup.domain.Store;
import org.zerock.wcup.dto.StoreSecurityDTO;
import org.zerock.wcup.repository.StoreRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log4j2

public class CustomUserDetailsService implements UserDetailsService {

    private final StoreRepository storeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername......................" + username);


        Optional<Store>  result =  storeRepository.findById( Long.parseLong(username));

        Store store = result.orElseThrow();

        return new StoreSecurityDTO( Long.valueOf(username), store.getTitle(), store.getPw());
    }
}
