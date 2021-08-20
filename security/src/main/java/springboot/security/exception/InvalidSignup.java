package springboot.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.security.exception.InvalidUsername;
import springboot.security.model.User;
import springboot.security.model.dto.Signupdto;
import springboot.security.repository.UserRepository;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class InvalidSignup {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    public User findById(String userid) {
//        return userRepository.findById(userid).orElseThrow(() -> new InvalidUsername());
//    }

    public void signupUser(Signupdto requestDto) {
        String username = requestDto.getUsername();

        Pattern usernamePattern = Pattern.compile("(^[a-zA-Z0-9]*$)");
        Matcher usernameMatcher = usernamePattern.matcher(requestDto.getUsername());

        Optional<User> found = Optional.ofNullable(userRepository.findByUsername(username));

        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다.");
        } else if (requestDto.getUsername().length() < 3) {
            throw new IllegalArgumentException("사용자 ID는 최소 3자 이상이어야 합니다");
        } else if (usernameMatcher.find() == false) {
            throw new IllegalArgumentException("사용자 ID는 알파벳 대소문자와 숫자만 사용가능합니다");
        } else if (requestDto.getPassword().length() < 4) {
            throw new IllegalArgumentException("비밀번호는 최소 4자 이상이어야 합니다");
        } else if (requestDto.getPassword().contains(requestDto.getUsername())) {
            throw new IllegalArgumentException("비밀번호에 사용자 ID가 포함되지 않아야 합니다");
        } else if (!(requestDto.getPassword().equals(requestDto.getPasswordck()))) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다");
        }

//        String password = passwordEncoder.encode(requestDto.getPassword());
//        String email = requestDto.getEmail();

//        User user = new User(username, password, email);
//        userRepository.save(user);
    }
}
