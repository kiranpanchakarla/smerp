package com.smerp.jwt.config;

import static com.smerp.jwt.models.Constants.SIGNING_KEY;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.smerp.jwt.models.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(TokenProvider.class);

	public String getUsernameFromToken(String token) throws Exception {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) throws io.jsonwebtoken.ExpiredJwtException {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
			throws io.jsonwebtoken.ExpiredJwtException {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) throws io.jsonwebtoken.ExpiredJwtException {
		return Jwts.parser().setSigningKey(Constants.SIGNING_KEY).parseClaimsJws(token).getBody();
	}

	public boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(Authentication authentication) {
		final String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		// logger.info("issued at"+System.currentTimeMillis());
		logger.info("issued  at   " + new Date(System.currentTimeMillis()));
		logger.info("expired   at  " + System.currentTimeMillis() + Constants.ACCESS_TOKEN_VALIDITY_SECONDS * 10);
		return Jwts.builder().setSubject(authentication.getName()).claim(Constants.AUTHORITIES_KEY, authorities)
				.signWith(SignatureAlgorithm.HS256, Constants.SIGNING_KEY)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Constants.ACCESS_TOKEN_VALIDITY_SECONDS * 10))
				.compact();

	}

	public Boolean validateToken(String token, UserDetails userDetails) throws Exception {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth,
			final UserDetails userDetails) {

		final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);

		final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

		final Claims claims = claimsJws.getBody();

		final Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(Constants.AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		System.out.println("authorities details" + authorities.toString());
		return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
	}

}
