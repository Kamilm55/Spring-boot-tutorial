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


    // Learn: True format of keys => https://www.javainuse.com/rsagenerator
    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
    //Prepared keys
    //SunRsaSign RSA private CRT key, 2048 bits
    //  params: null
    //  modulus: 25949963895825081718077713366795082759757054542276383997046835319602270004614624916694151909977490680858400854661548934200167805313510200828122157499696027028839601330607864690236910451237580675343240495497777582567291084622324303719536629203586942935504458891215520229351152626194049479743376907324824085057372558099235052047262527900064484865158422839748021667825719386774097410759477662261989961334169094312783092006040434159811809023884521886507916577761602069311809596486506867425625490752137066741099638803642394030711361737308366089334769059202006676844646912001424378112504444685004692838682572693190029869699
    //  private exponent: 12446376705058481562860610084611032453863060163795180071885678288885941593146677637348453912481154970730465893541111104005080713311903549105555302161629233343165381516176546298130245886582792806481861393033391696928894278170740665592994104613744147134644237736805515283417145369416827964684276637874709826169045927101676952240919717976840212836426874964988979904517175891127487939054930014931902339857982641801493328628541482475867425474432350419493874073902743056421357488364875148659014684830916081577649036377045253651402836445594261930696332405074456488317754153101252881468313140087684370541185979139383967786133
    //Sun RSA public key, 2048 bits
    //  params: null
    //  modulus: 25949963895825081718077713366795082759757054542276383997046835319602270004614624916694151909977490680858400854661548934200167805313510200828122157499696027028839601330607864690236910451237580675343240495497777582567291084622324303719536629203586942935504458891215520229351152626194049479743376907324824085057372558099235052047262527900064484865158422839748021667825719386774097410759477662261989961334169094312783092006040434159811809023884521886507916577761602069311809596486506867425625490752137066741099638803642394030711361737308366089334769059202006676844646912001424378112504444685004692838682572693190029869699
    //  public exponent: 65537

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
            PrivateKey prKey = kf.generatePrivate(keySpecPKCS8);

            return prKey;
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
