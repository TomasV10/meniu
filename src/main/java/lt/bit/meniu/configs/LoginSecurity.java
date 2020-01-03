package lt.bit.meniu.configs;

import lt.bit.meniu.repositories.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(
        jsr250Enabled = true,
        securedEnabled = true,
        prePostEnabled = true)

public class LoginSecurity extends WebSecurityConfigurerAdapter {
    private final AccountRepository accountRepository;

    public LoginSecurity(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/mvc/client/newClient*").hasRole("MANAGER")
                .antMatchers("/mvc/client/delete").hasRole("ADMIN")
                .antMatchers("/mvc/products/edit*").hasRole("MANAGER")
                .antMatchers("/mvc/products/delete*").hasRole("ADMIN")
                .antMatchers("/mvc/products/newProduct*").hasRole("MANAGER")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .csrf().disable()

                .formLogin()
                .defaultSuccessUrl("/mvc/products")
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }
}

