package com.example.trackon.service.user;

import com.example.trackon.entity.user.Authority;
import com.example.trackon.entity.user.User;
import com.example.trackon.entity.user.UserRepository;
import com.example.trackon.error.exceptions.AlreadySignedException;
import com.example.trackon.error.exceptions.DoNotHaveAuthorityException;
import com.example.trackon.error.exceptions.UserNotFoundException;
import com.example.trackon.jwt.JwtProvider;
import com.example.trackon.payload.request.SignUpRequest;
import com.example.trackon.payload.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        User user = userRepository.findById(signUpRequest.getId())
                        .orElse(null);

        if(user != null)
            throw new AlreadySignedException();

        userRepository.save(
                User.builder()
                        .id(signUpRequest.getId())
                        .name(signUpRequest.getNickName())
                        .password(signUpRequest.getPassword())
                        .age(signUpRequest.getAge())
                        .authority(Authority.USER)
                        .phoneNumber(signUpRequest.getPhoneNumber())
                        .build()
        );
    }

    @Override
    public void makeAdmin(String token, Long userId) {
        User user = userRepository.findByUserId(jwtProvider.getUserId(token))
                .orElseThrow(UserNotFoundException::new);

        if(user.getAuthority().equals(Authority.ADMIN))
            throw new DoNotHaveAuthorityException();

        User target = userRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);

        userRepository.save(target.updateAdmin());
    }

    @Override
    public UserResponse getUserInfo(String token) {
        User user = userRepository.findByUserId(jwtProvider.getUserId(token))
                .orElseThrow(UserNotFoundException::new);

        return UserResponse.builder()
                .userId(user.getUserId())
                .nickName(user.getName())
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    @Override
    public UserResponse getTargetInfo(String token, Long userId) {
        userRepository.findByUserId(jwtProvider.getUserId(token))
                .orElseThrow(UserNotFoundException::new);

        User target = userRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);

        return UserResponse.builder()
                .userId(target.getUserId())
                .phoneNumber(target.getPhoneNumber())
                .age(target.getAge())
                .nickName(target.getName())
                .build();
    }
}
