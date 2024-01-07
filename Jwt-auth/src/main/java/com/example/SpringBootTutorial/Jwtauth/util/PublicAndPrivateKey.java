package com.example.SpringBootTutorial.Jwtauth.util;

import com.example.SpringBootTutorial.Jwtauth.model.properties.security.SecurityProperties;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class PublicAndPrivateKey {
    //Learn:
    // There is already keys in my app.properties file why we need this class?
    // Now, regarding your question about keys already being present in the app.properties file:
    // The PublicAndPrivateKey class seems to be designed to dynamically load the keys from
    // the SecurityProperties class and prepare them during the application startup.
    // This approach allows for more flexibility, as the keys can be configured externally
    // (e.g., through properties files or environment variables) and changed without modifying the source code.
    // If you already have the keys directly configured in your app.properties file and they don't change dynamically,
    // you might not need the PublicAndPrivateKey class.
    // You could directly use the configured values from your properties file where needed.
    // The class seems to provide a more dynamic approach for handling keys that might change during the application lifecycle.

    private final SecurityProperties securityProperties;

    @Getter
    private static PrivateKey privateKey;
    @Getter
    private static PublicKey publicKey;

    public PublicAndPrivateKey(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
        privateKey = preparePrivateKey();
        publicKey = preparePublicKey();
    }


    private PrivateKey preparePrivateKey() {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(securityProperties.getJwt().getPrivateKey()));
            return kf.generatePrivate(keySpecPKCS8);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PublicKey preparePublicKey() {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(securityProperties.getJwt().getPublicKey()));
            return kf.generatePublic(keySpecX509);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
}
