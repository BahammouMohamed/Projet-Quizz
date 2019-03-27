package projet.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired(required=true)
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	protected void globalConfig(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// AUTORISATION WEBSOCKET 
		 
		 http.authorizeRequests().antMatchers("/socketCompetition/**").permitAll();
		 http.authorizeRequests().antMatchers("/socketSolo/**").permitAll();
		 http.authorizeRequests().antMatchers("/login/**").permitAll();
		 http.authorizeRequests().antMatchers("/inscription/**").permitAll();
		 http.authorizeRequests().antMatchers("/users/username/**").permitAll();
		 http.authorizeRequests().antMatchers("/users/{id}/**").hasAnyAuthority("ADMIN","ENSEIGNANT","ELEVE").anyRequest().authenticated();
		 http.authorizeRequests().antMatchers("/users/{id}").hasAnyAuthority("ADMIN","ENSEIGNANT","ELEVE").anyRequest().authenticated();
		 http.authorizeRequests().antMatchers("/users/**").hasAuthority("ADMIN").anyRequest().authenticated();
		 // AUTORISATIONS ENSEINGNANT
		 http.authorizeRequests().antMatchers("/quizzs/**").hasAuthority("ENSEIGNANT").anyRequest().authenticated();
		 http.authorizeRequests().antMatchers("/indices/**").hasAuthority("ENSEIGNANT").anyRequest().authenticated();
		 http.authorizeRequests().antMatchers("/reponses/**").hasAuthority("ENSEIGNANT").anyRequest().authenticated();
		 http.authorizeRequests().antMatchers("/medias/**").hasAuthority("ENSEIGNANT").anyRequest().authenticated();
		 http.authorizeRequests().antMatchers("/questions/**").hasAuthority("ENSEIGNANT").anyRequest().authenticated();
		 
		 
		// AUTORISATIONS ELEVE
		 http.authorizeRequests().antMatchers(HttpMethod.GET,"/quizzs/**").hasAuthority("ELEVE");
		 
		 
		
		

		
		 http.authorizeRequests().anyRequest().authenticated();
		 
		 http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		 http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
       
	}
	
}
