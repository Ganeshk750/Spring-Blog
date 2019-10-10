package com.ganeshk750.security;


import javax.annotation.PostConstruct;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.security.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;

import com.ganeshk750.model.User;

@Service
public class JwtProvider {
	private Key key;
	@PostConstruct
	public void init() {
		key = Key.secretKeyFor(SignatureAlgorithm.HS512);
	}
	
	  public String generateToken(Authentication authentication) {
	    User principal = (User)authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUserName())
                .signWith(key)
                .compact();
	    }
	

}
