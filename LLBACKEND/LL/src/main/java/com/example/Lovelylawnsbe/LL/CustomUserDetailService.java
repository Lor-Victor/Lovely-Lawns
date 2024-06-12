//package com.example.Lovelylawnsbe.LL;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//    @Autowired
//    private UserRep userRep;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRep.findByUsername(username).orElseThrow(()
//                -> new UsernameNotFoundException(username + "not found"));
//        ArrayList<SimpleGrantedAuthority> authList = new ArrayList<>();
//        authList.add(new SimpleGrantedAuthority(user.getRole()));
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(), user.getPassword(), authList);
//    }
//    }
//}
