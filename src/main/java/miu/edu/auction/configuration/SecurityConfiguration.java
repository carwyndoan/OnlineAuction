package miu.edu.auction.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/assets/**").permitAll()
                .antMatchers("/registration/**","/login", "/security/**", "/h2-console/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                //.antMatchers("/bidding/**","/bid/**").hasAuthority("ROLE_USER")
//                .antMatchers("/seller/**").hasAuthority("ROLE_USER")
//                .antMatchers("/customer/**").hasAuthority("ROLE_USER")
//                .antMatchers("/category/**").hasAuthority("ROLE_USER")
//                .antMatchers("/Product/**").hasAuthority("ROLE_USER")
//                .antMatchers("/customer/**").hasAuthority("ROLE_USER")
//                .antMatchers("/seller/**").hasAuthority("ROLE_USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .permitAll()
                .failureUrl("/login-error")
                .defaultSuccessUrl("/home2")
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and().logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //.logoutUrl("/doLogout")
                .logoutSuccessUrl("/login").permitAll();

        //Those two settings below is to enable access h2 database via browser
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}
