package br.com.project.everest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@ComponentScan
//@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
@PropertySource("application.properties")
//@EnableWebMvc
@EnableJpaRepositories
public class UiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}

	@Bean
	public ViewResolver getViewResolver(){
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/jsp/");
	    resolver.setSuffix(".jsp");
	    resolver.setViewClass(JstlView.class);
	    return resolver;
	}
	
	
//	@Configuration
//	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//		
//		@Autowired
//		@Qualifier("customAuthenticationProvider")
//		CustomAuthenticationProvider customAuthenticationProvider;		
//		
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			// @formatter:off
//			http.httpBasic().and().authorizeRequests()
//					.antMatchers("/index.html", "/home.html", "/login.html", "/signup.html", "/singup", "/registerUser", "/", "/css/**",
//							"/js/**")
//					.permitAll().anyRequest().authenticated()
//					.and()
//					.csrf().disable()// csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//					.logout()
//					.clearAuthentication(true).invalidateHttpSession(true);
//			// @formatter:on
//		}
//
//		@Autowired
//		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//			auth.authenticationProvider(customAuthenticationProvider);
//				/*.inMemoryAuthentication()
//					.withUser("user")
//					.password("password")
//					.roles("USER");*/
//		}
//	}

}
