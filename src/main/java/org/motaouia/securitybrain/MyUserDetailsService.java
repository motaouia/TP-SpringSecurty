package org.motaouia.securitybrain;

import java.util.Optional;

import org.motaouia.securitybrain.models.MyUserDetails;
import org.motaouia.securitybrain.models.User;
import org.motaouia.securitybrain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//return new MyUserDetails("nwAdmin");
		Optional<User> user = userRepository.findByUsername(username);
		
		user.orElseThrow(() -> new UsernameNotFoundException("User not Found : " + username));
		
		return user.map(MyUserDetails::new).get();
	}

}
