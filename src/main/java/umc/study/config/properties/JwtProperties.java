package umc.study.config.properties;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("jwt.token")
public class JwtProperties {
    private String secretKey="";
    private Expiration expiration = new Expiration();

    @Getter
    @Setter
    public static class Expiration{
        private Long access;
        // TODO: refreshToken
    }

    @PostConstruct
    public void checkConfig() {
        System.out.println("JWT Secret Key: " + secretKey);
        System.out.println("Access Token Expiration: " + expiration.getAccess());
    }
}