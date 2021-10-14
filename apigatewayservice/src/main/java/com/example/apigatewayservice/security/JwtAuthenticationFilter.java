package com.example.apigatewayservice.security;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

@Component
public class JwtAuthenticationFilter implements GatewayFilterFactory<JwtAuthenticationFilter.Config> {

	@Autowired
	private JwtTokenProvider jwtUtil;

	@Override
	public GatewayFilter apply(Config config) {
		// TODO Auto-generated method stub
		return (exchange, chain) -> {

			ServerHttpRequest request = exchange.getRequest();
			ServerHttpResponse response = exchange.getResponse();

			final List<String> apiEndpoints = List.of("/register", "/login");

			Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
					.noneMatch(uri -> r.getURI().getPath().contains(uri));

			System.out.println(request.getPath());
			if (isApiSecured.test(request)) {
				if (!request.getHeaders().containsKey("Authorization")) {
					response.setStatusCode(HttpStatus.UNAUTHORIZED);
					return response.setComplete();
				}

				String token = request.getHeaders().getOrEmpty("Authorization").get(0);
				System.out.println(token);
				if (token.startsWith("Bearer ")) {
					token = token.substring(7);
				} else {
					response.setStatusCode(HttpStatus.BAD_REQUEST);
					return response.setComplete();
				}

				try {
					jwtUtil.validateToken(token);
					Claims claims = jwtUtil.getClaims(token);
					exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
					System.out.println("gateway passed with token");
				} catch (Exception e) {
					System.out.println("wjt errore");
					response.setStatusCode(HttpStatus.BAD_REQUEST);
					return response.setComplete();
				}

			}

			return chain.filter(exchange);
		};
	}

	@Override
	public Config newConfig() {
		return new Config("JwtAuthenticationFilter");
	}

	@Override
	public Class<Config> getConfigClass() {
		return Config.class;
	}

	public static class Config {

		public Config(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}