package springbootwebsecurity.sbwebsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springbootwebsecurity.sbwebsecurity.dao.UserDao;
import springbootwebsecurity.sbwebsecurity.model.User;

@Service("userDetailsServiceImp")
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    private springbootwebsecurity.sbwebsecurity.model.User user;

    public User getUser() {
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        this.user = userDao.loadUserByUsername(username);
        return userDao.loadUserByUsername(username);
    }

}
