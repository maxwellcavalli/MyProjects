package br.com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.security.CustomAuthenticationProvider;

@RestController

@ComponentScan
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
@PropertySource("application.properties")
public class UiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}

	
	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		
		@Autowired
		@Qualifier("customAuthenticationProvider")
		CustomAuthenticationProvider customAuthenticationProvider;		
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http.httpBasic().and().authorizeRequests()
					.antMatchers("/index.html", "/home.html", "/login.html", "/signup.html", "/singup", "/registerUser", "/", "/css/**",
							"/js/**")
					.permitAll().anyRequest().authenticated()
					.and()
					.csrf().disable()// csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
					.logout()
					.clearAuthentication(true).invalidateHttpSession(true);
			// @formatter:on
		}

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(customAuthenticationProvider);
				/*.inMemoryAuthentication()
					.withUser("user")
					.password("password")
					.roles("USER");*/
		}
	}

}
