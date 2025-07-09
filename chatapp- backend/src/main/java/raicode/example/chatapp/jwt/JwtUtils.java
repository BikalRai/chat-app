package raicode.example.chatapp.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
	private final String secretKey;
	
	public JwtUtils(String jwtSecret) {
		this.secretKey = jwtSecret;
	}
	
	public String getSecretKey() {
		return secretKey;
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}
	
	public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
		claims.put("roles", userDetails.getAuthorities());
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)).signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}
	
	// check to see if the token is valid based on the username extracted from the token and the userdetails that are being used
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		boolean isValid = (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		System.out.println("Token validation: Username from the token: " + username + ", UserDetails username: " + userDetails.getUsername() + ", Is Valid: " + isValid);
		return isValid;
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
