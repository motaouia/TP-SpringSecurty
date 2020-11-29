package org.motaouia.securitybrain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	//@Autowired
	//private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.inMemoryAuthentication().withUser("mota").password(getPasswordEncoder().encode("123")).roles("ADMIN");
		auth.inMemoryAuthentication().withUser("usr").password(getPasswordEncoder().encode("123")).roles("USER");
		*/
		//Jdbc Configurarion
		/* auth.jdbcAuthentication().dataSource(dataSource); */
		
		//Jdbc Configurarion
		/*auth.jdbcAuthentication()
			  .dataSource(dataSource)
			   .withDefaultSchema()
			      .withUser(
			    	  User.withUsername("myUser")
			    	  .password("pass")
			    	  .roles("USER")
			       )
			      .withUser(
					  User.withUsername("myAdmin")
					  .password("pass")
					  .roles("ADMIN")
				   );
		*/		
		
		//Jdbc Configurarion
		/*auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("SELECT username, password, enabled"
				+ "FROM users WHERE username = ?")
		.authoritiesByUsernameQuery("SELECT username, authority"
				+ "FROM authorities WHERE username = ?");
				*/
		
		//auth.userDetailsService(userDetailsService);
		
		//Ldap
		auth
	      .ldapAuthentication()
	        .userDnPatterns("uid={0},ou=people")
	        .groupSearchBase("ou=groups")
	        .contextSource()
	          .url("ldap://localhost:8389/dc=springframework,dc=org")
	          .and()
	        .passwordCompare()
	          .passwordEncoder(new LdapShaPasswordEncoder())
	          .passwordAttribute("userPassword");
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		//return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//http.authorizeRequests().antMatchers("/admin","/user", "/").hasRole("ADMIN");
		//http.authorizeRequests().antMatchers("/user", "/")./*hasRole("USER")*/hasAnyRole("ADMIN", "USER");
		//http.authorizeRequests().antMatchers("/", "/static/js", "/static/css").permitAll().and().formLogin();
		
		/*http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/admin", "/user").hasAnyRole("ADMIN", "USER")
			.antMatchers("/").permitAll()
			.and().formLogin();
		*/
		//Ldap
		http
	      .authorizeRequests()
	        .anyRequest().fullyAuthenticated()
	      .and()
	         .formLogin();
	}

}
