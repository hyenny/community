package org.example.community.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtTokenProvider;
  private final UserDetailsService userDetailsService;


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String token = resolveToken(request);
    if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
      authenticate(token);
    }

    filterChain.doFilter(request, response);
  }

  private void authenticate(String token) {
    Authentication authentication = jwtTokenProvider.getAuthentication(token);
    try {
      // DB에 해당 인증 정보가 있는지 확인
      userDetailsService.loadUserByUsername(authentication.getName());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (UsernameNotFoundException e) {
      log.warn(e.getMessage());
    }
  }

  private String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }

    return null;
  }
}
