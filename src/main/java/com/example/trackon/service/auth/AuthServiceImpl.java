package com.example.trackon.service.auth;

import com.example.trackon.entity.refresh_token.RefreshToken;
import com.example.trackon.entity.refresh_token.RefreshTokenRepository;
import com.example.trackon.entity.user.Authority;
import com.example.trackon.entity.user.UserRepository;
import com.example.trackon.jwt.JwtProvider;
import com.example.trackon.payload.request.SignInRequest;
import com.example.trackon.payload.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtProvider jwtProvider;

    @Override
    public TokenResponse signIn(SignInRequest signInRequest) {
        return userRepository.findById(signInRequest.getId())
                .filter(user -> user.getPassword().equals(signInRequest.getPassword()))
                .map(user -> {
                    String accessToken = jwtProvider.generateAccessToken(user.getUserId());
                    String refreshToken = jwtProvider.generateRefreshToken(user.getUserId());

                    refreshTokenRepository.save(
                            RefreshToken.builder()
                                    .refreshToken(refreshToken)
                                    .userId(user.getUserId())
                                    .build()
                    );

                    return TokenResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build();
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public TokenResponse signInAdmin(SignInRequest signInRequest) {
        return userRepository.findById(signInRequest.getId())
                .filter(user -> user.getAuthority().equals(Authority.ADMIN))
                .map(user -> {
                    String accessToken = jwtProvider.generateAccessToken(user.getUserId());
                    String refreshToken = jwtProvider.generateRefreshToken(user.getUserId());

                    refreshTokenRepository.save(
                            RefreshToken.builder()
                                    .refreshToken(refreshToken)
                                    .userId(user.getUserId())
                                    .build()
                    );

                    return TokenResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build();
                }).orElseThrow(RuntimeException::new);
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {
        if(!jwtProvider.isRefreshToken(refreshToken))
            throw new RuntimeException();

        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .map(refreshToken1 -> {
                    String accessToken = jwtProvider.generateAccessToken(refreshToken1.getUserId());
                    String newRefreshToken = jwtProvider.generateRefreshToken(refreshToken1.getUserId());

                    refreshTokenRepository.save(
                            refreshToken1.updateRefreshToken(newRefreshToken)
                    );

                    return TokenResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(newRefreshToken)
                            .build();
                })
                .orElseThrow(RuntimeException::new);
    }
}
