package com.example.trackon.entity.refresh_token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    @Id
    private Long userId;

    private String refreshToken;

    public RefreshToken updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;

        return this;
    }
}
