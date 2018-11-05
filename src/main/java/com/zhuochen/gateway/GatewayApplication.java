package com.zhuochen.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


/**
 * Route: Route the basic building block of the gateway. It is defined by an ID, a destination URI, a collection of predicates and a collection of filters. A route is matched if aggregate predicate is true.
 * Predicate: This is a Java 8 Function Predicate. The input type is a Spring Framework ServerWebExchange. This allows developers to match on anything from the HTTP request, such as headers or parameters.
 * Filter: These are instances Spring Framework GatewayFilter constructed in with a specific factory. Here, requests and responses can be modified before or after sending the downstream request.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("um_route", r -> r
                        .path("/session/**")
                        .uri("lb://session/")
                )
                .build();
    }

//    @Bean
//    public DiscoveryClientRouteDefinitionLocator discoveryClientRouteLocator(DiscoveryClient discoveryClient) {
//        return new DiscoveryClientRouteDefinitionLocator(discoveryClient);
//    }

//    @Bean
//    public RouterFunction<ServerResponse> testWhenMetricPathIsNotMeet() {
//        return RouterFunctions.route(
//                RequestPredicates.path("/**"),
//                request -> ServerResponse.ok().body(BodyInserters.fromObject("hello")));
//    }

//    @Bean
//    KeyResolver userKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
//    }

//    public static final String HELLO_FROM_FAKE_ACTUATOR_METRICS_GATEWAY_REQUESTS = "hello from fake /actuator/metrics/gateway.requests";
////    @Value("${test.uri:http://httpbin.org:80}")
//    String uri = "http://localhost:9080";
//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.host("**.abc.org").and().path("/anything/png")
//                        .filters(f ->
//                                f.prefixPath("/httpbin")
//                                        .addResponseHeader("X-TestHeader", "foobar"))
//                        .uri(uri)
//                )
//                .route("read_body_pred", r -> r.host("*.readbody.org")
//                        .and().readBody(String.class,
//                                s -> s.trim().equalsIgnoreCase("hi"))
//                        .filters(f -> f.prefixPath("/httpbin")
//                                .addResponseHeader("X-TestHeader", "read_body_pred")
//                        ).uri(uri)
//                )
//                .route("rewrite_request_upper", r -> r.host("*.rewriterequestupper.org")
//                        .filters(f -> f.prefixPath("/httpbin")
//                                .addResponseHeader("X-TestHeader", "rewrite_request_upper")
//                                .modifyRequestBody(String.class, String.class,
//                                        (exchange, s) -> {
//                                            return Mono.just(s.toUpperCase()+s.toUpperCase());
//                                        })
//                        ).uri(uri)
//                )
//                .route("rewrite_response_upper", r -> r.host("*.rewriteresponseupper.org")
//                        .filters(f -> f.prefixPath("/httpbin")
//                                .addResponseHeader("X-TestHeader", "rewrite_response_upper")
//                                .modifyResponseBody(String.class, String.class,
//                                        (exchange, s) -> {
//                                            return Mono.just(s.toUpperCase());
//                                        })
//                        ).uri(uri)
//                )
//                .route("rewrite_response_obj", r -> r.host("*.rewriteresponseobj.org")
//                        .filters(f -> f.prefixPath("/httpbin")
//                                .addResponseHeader("X-TestHeader", "rewrite_response_obj")
//                                .modifyResponseBody(Map.class, String.class, MediaType.TEXT_PLAIN_VALUE,
//                                        (exchange, map) -> {
//                                            Object data = map.get("data");
//                                            return Mono.just(data.toString());
//                                        })
//                                .setResponseHeader("Content-Type", MediaType.TEXT_PLAIN_VALUE)
//                        ).uri(uri)
//                )
//                .route(r -> r.path("/image/webp")
//                        .filters(f ->
//                                f.prefixPath("/httpbin")
//                                        .addResponseHeader("X-AnotherHeader", "baz"))
//                        .uri(uri)
//                )
//                .route(r -> r.order(-1)
//                        .host("**.throttle.org").and().path("/get")
//                        .filters(f -> f.prefixPath("/httpbin")
//                                .filter(new ThrottleGatewayFilter(1,1,10,TimeUnit.SECONDS)))
//                        .uri(uri)
//                )
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> testWhenMetricPathIsNotMeet() {
//        return RouterFunctions.route(
//                RequestPredicates.path("/actuator/metrics/gateway.requests"),
//                request -> ServerResponse.ok().body(BodyInserters.fromObject("hello")));
//    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
