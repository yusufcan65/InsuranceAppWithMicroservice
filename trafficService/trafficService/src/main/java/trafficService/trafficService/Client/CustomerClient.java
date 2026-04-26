package trafficService.trafficService.Client;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import trafficService.trafficService.Dto.CustomerResponse;
import trafficService.trafficService.Exception.ServiceUnavailableException;

import java.util.UUID;

@FeignClient(name = "customerService")
public interface CustomerClient {

    Logger logger = LoggerFactory.getLogger(CustomerClient.class);

    @GetMapping("/v1/customer/internal/feign/{id}")
    @Retry(name = "customerRetry")
    @CircuitBreaker( name = "customerServiceCB" ,fallbackMethod = "customerFallBack")
    CustomerResponse getCustomerForFeign(@PathVariable UUID id);

    default CustomerResponse customerFallBack(UUID id, Throwable e){
        logger.error("Customer service is not found with id :{} | message : {}",id,e.getMessage());

        throw new ServiceUnavailableException("Customer Service is not have a response");

    }
}