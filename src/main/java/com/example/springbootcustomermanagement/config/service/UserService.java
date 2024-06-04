    package com.example.springbootcustomermanagement.config.service;
    
    import com.example.springbootcustomermanagement.config.UserPrinciple;
    import com.example.springbootcustomermanagement.model.Role;
    import com.example.springbootcustomermanagement.model.User;
    import com.example.springbootcustomermanagement.repository.IUserRepository;
    import jakarta.transaction.Transactional;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.HashSet;
    import java.util.Set;
    
    @Service
    @Transactional
    public class UserService implements UserDetailsService {
        @Autowired
        private IUserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }

        public User findByUsername(String name) {
            return userRepository.findByUsername(name);
        }
    
        public UserDetails loadUserByUsername(String username) {
            User user = userRepository.findByUsername(username);
            Set<Role> roleSet = user.getRoles();
            return UserPrinciple.build(user);
        }

        public boolean existsByUsername(String username) {
            return userRepository.existsByUsername(username);
        }

        public User save(User user) {
            // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            Role role = new Role(1L, "ROLE_ADMIN");
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);

            user.setRoles(roleSet);
            // Lưu người dùng vào cơ sở dữ liệu
            return userRepository.save(user);
        }
    }
