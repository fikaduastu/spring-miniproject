//package com.example.apigatewayservice.config;
//
//import com.example.apigatewayservice.security.JwtAuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GetwayConfig {
//	@Autowired
//	private JwtAuthenticationFilter filter;
//
//	@Bean
//	public RouteLocator routes(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://ACCOUNT-SERVICE"))
//				.route("api", r -> r.path("/api/user/**").filters(f -> f.filter(filter)).uri("lb://ACCOUNT-SERVICE"))
//				.route("CREDITCARD-SERVICE", r -> r.path("/api/**").filters(f -> f.filter(filter)).uri("lb://CREDITCARD-SERVICE"))
//				.route("MASTERCARD-SERVICE", r -> r.path("/api/**").filters(f -> f.filter(filter)).uri("lb://MASTERCARD-SERVICE"))
//				.route("PAYMENT-SERVICE", r -> r.path("/payments/**").filters(f -> f.filter(filter)).uri("lb://PAYMENT-SERVICE"))
//				.route("ORDER-SERVICE", r -> r.path("/api/**").filters(f -> f.filter(filter)).uri("lb://ORDER-SERVICE"))
//				.route("PRODUCT-SERVICE", r -> r.path("/products/**").filters(f -> f.filter(filter)).uri("lb://PRODUCT-SERVICE"))
//				.build();
//
//	}}
