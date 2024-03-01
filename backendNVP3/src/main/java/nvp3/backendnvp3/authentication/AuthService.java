package nvp3.backendnvp3.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nvp3.backendnvp3.entities.Permision;
import nvp3.backendnvp3.entities.User;
import nvp3.backendnvp3.repositories.UserRepository;
import nvp3.backendnvp3.util.Util;


import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class AuthService implements IAuthService{

    private static Key key = new SecretKeySpec("secret".getBytes(), "AES");

    @EJB
    private UserRepository userRepository;

    public String generateJWT(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setClaims(claims)
                .setExpiration(new Date(new Date().getTime() + 1000 * 60 * 60L))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public boolean isAuthorized(String token,String action) {
        if (!Util.isEmpty(token) && token.contains("Bearer ")) {
            String jwt = token.substring(token.indexOf("Bearer ") + 7);
            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
            int id = claims.getBody().get("id",Integer.class);
            for(Permision permision:userRepository.findById(id).getPermissions())
            {
                if(permision.getName().equalsIgnoreCase(action))
                    return true;
            }
        }
        return false;
    }

    public int getId(String token)
    {
        String jwt = token.substring(token.indexOf("Bearer ") + 7);
        Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
        return claims.getBody().get("id",Integer.class);
    }

}
