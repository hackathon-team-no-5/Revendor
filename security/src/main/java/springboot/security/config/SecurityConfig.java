package springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springboot.security.config.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity //spring security filter -> spring filter chain
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean // 해당 메서도의 리턴되는 오브젝트를 IOC로 등록
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/myPage/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/loginForm")
//                .usernameParameter("username")
                    .loginProcessingUrl("/login")//login 주소 호출 -> security
                    .defaultSuccessUrl("/")
                    .and()
                .logout()
                    .logoutUrl("/doLogout")
                    .logoutSuccessUrl("/loginForm")
                .and()
                .oauth2Login()
                .loginPage("/loginForm")
                .userInfoEndpoint()
                .userService(principalOauth2UserService)
                .and();
        // 구글 로그인이 완료된 후의 후처리 필요 -> TIP. no code (access token + user profile info)
        // 1. receive code(certification) 2. access token(authorization) 3. user profile
        // 4-1. automatic sign-up 4-2. (email, phone, name, id) shoppingmall -> (address), mall -> (vip, general)
    }
}