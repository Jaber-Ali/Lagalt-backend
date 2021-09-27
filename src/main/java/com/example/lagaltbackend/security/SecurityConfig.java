package com.example.lagaltbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // endpoints we want everyone to be able to access
/*    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/v1/categories/**");
        web.ignoring().antMatchers("/api/v1/skills/");
        web.ignoring().antMatchers(HttpMethod.GET,"/api/v1/projects/**");
        web.ignoring().antMatchers(HttpMethod.GET,"/api/v1/projects/filter");
        web.ignoring().antMatchers(HttpMethod.GET,"/api/v1/discussionBoard/**");
        web.ignoring().antMatchers(HttpMethod.GET,"/api/v1/discussionMessages");
        web.ignoring().antMatchers(HttpMethod.GET,"/api/v1/users/**");


        //web.ignoring().antMatchers("/api/v1/**");
    }*/

    // https://www.baeldung.com/spring-security-expressions
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Custom converter to show roles instead of scopes
        final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(this.jwtGrantedAuthoritiesConverter());

        //web.ignoring().antMatchers("/api/v1/signup");

        http.cors()
                .and()
                .authorizeRequests()
                // need to be logged in to access these endpoints
                .antMatchers("/api/v1/categories",
                        "/api/v1/categories/*",
                        "/api/v1/skills/*",
                        "/api/v1/chatmessages/**",
                        "/api/v1/projects/**",
                        "/api/v1/portfolio/**",
                        "/api/v1/projects/filter",
                        "/api/v1/projectstatus/*",
                        "/api/v1/projectrole/*",
                        "/api/v1/discussionBoard/**",
                        "/api/v1/discussionMessages/*",
                        "/api/v1/roles/*",
                        "/api/v1/users",
                        "/api/v1/users/*",
                        "/api/v1/users/i/*",
                        "/api/v1/projectstatus",
                        "/api/v1/portfolio/users/*"
                ).permitAll()

                /*
                 Need to find a way to authorize via roles - because we are not making the
                 principal ourselves, this is tricky. As authorities is the scope from keycloak.
                 You can see what is created at the /user/info/principal endpoint by passing a token.
                 Having a look at some documentation may help: https://docs.spring.io/spring-security/site/docs/current/reference/html5/#oauth2login-advanced-map-authorities
                 https://stackoverflow.com/questions/55609083/how-to-set-user-authorities-from-user-claims-return-by-an-oauth-server-in-spring/56259665
                 For now, we replaced our scope with our roles: https://stackoverflow.com/questions/58205510/spring-security-mapping-oauth2-claims-with-roles-to-secure-resource-server-endp/58234971#58234971
                */
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                // Using our converter
                .jwtAuthenticationConverter(jwtAuthenticationConverter);
    }

    // Implementation of replacing authorities with our roles
    @Bean
    public JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter() {
        // You can use setAuthoritiesClaimName method
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        return jwtGrantedAuthoritiesConverter;
    }
}
