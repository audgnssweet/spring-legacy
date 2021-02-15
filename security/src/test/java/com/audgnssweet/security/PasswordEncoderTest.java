package com.audgnssweet.security;

import com.audgnssweet.config.ApplicationConfig;
import com.audgnssweet.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class PasswordEncoderTest {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordEncoderTest(PasswordEncoder encoder) {
        this.passwordEncoder = encoder;
    }

    @Test
    void encodeTest() {
        final String encoded = passwordEncoder.encode("1234");
        System.out.println(encoded);
    }

    @Test
    void matchTest() {
        final String match = "$2a$10$1vOTEtZ0mB2Ewr7uZoqwDuQ1VugdtgqPWSLYV948daS8Axjuqos/O";
        final String own = "1234";
        if (passwordEncoder.matches(own, match)) {
            System.out.println("맞습니다");
        } else {
            System.out.println("아닙니다");
        }
    }
}
