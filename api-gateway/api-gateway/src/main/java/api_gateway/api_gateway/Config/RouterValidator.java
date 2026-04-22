package api_gateway.api_gateway.Config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {


    public static final List<String> openApiEndpoints = List.of(
            "/v1/auth/login",
            "/v1/auth/register",
            "/v1/auth/refresh-token",
            "/swagger-ui",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/v3/api-docs",
            "/v1/auth/v3/api-docs",
            "/v1/user/v3/api-docs",
            "/v1/customer/v3/api-docs",
            "/webjars",
            "/actuator"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}