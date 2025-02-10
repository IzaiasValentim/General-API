package com.izaiasvalentim.general.Service;

import com.izaiasvalentim.general.Common.CustomExceptions.RefreshTokenExpiredException;
import com.izaiasvalentim.general.Common.CustomExceptions.ResourceAlreadyExistsException;
import com.izaiasvalentim.general.Common.CustomExceptions.ResourceNotFoundException;
import com.izaiasvalentim.general.Domain.BaseUser;
import com.izaiasvalentim.general.Domain.RefreshToken;
import com.izaiasvalentim.general.Repository.BaseUserRepository;
import com.izaiasvalentim.general.Repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

import static com.izaiasvalentim.general.Common.utils.TimeUtils.getCurrentTimeTruncatedInSeconds;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final BaseUserRepository baseUserRepository;
    @Value("${tokens.expiration.refresh.exp}")
    private Integer refreshTokenExpiresInSeconds;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, BaseUserRepository baseUserRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.baseUserRepository = baseUserRepository;
    }

    public RefreshToken validateTokenRenewal(String token) {
        RefreshToken refreshToken = getRefreshToken(token);
        Instant now = getCurrentTimeTruncatedInSeconds();

        if (refreshToken.getExpiryDate().compareTo(now) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenExpiredException("Refresh token expired. Please make a new log in request.");
        }
        return refreshToken;
    }

    private RefreshToken getRefreshToken(String refreshToken) {
        return refreshTokenRepository
                .findByToken(refreshToken)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));
    }

    public String createRefreshToken(String username) {
        try {
            BaseUser baseUser = baseUserRepository
                    .findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            RefreshToken refreshToken = refreshTokenRepository
                    .findByUser(baseUser)
                    .orElse(null);

            Instant now = getCurrentTimeTruncatedInSeconds();

            if (refreshToken == null) {
                refreshToken = new RefreshToken();
                refreshToken.setUser(baseUser);
                refreshToken.setExpiryDate(now.plusSeconds(refreshTokenExpiresInSeconds));
                refreshToken.setToken(UUID.randomUUID().toString().toUpperCase());

                refreshToken = refreshTokenRepository.save(refreshToken);
            }
            return refreshToken.getToken();
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceAlreadyExistsException("The user still has a valid refresh token.");
        }
    }
}
