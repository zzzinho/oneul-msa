package com.oneul.postcommandservice.infra.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.oneul.postcommandservice.dto.UserResponse;

@Component
public class UserWebClient {
    private final Logger log = LoggerFactory.getLogger(UserWebClient.class);
    
    private final ReactorLoadBalancerExchangeFilterFunction lbFunction;
    private WebClient webClient;

    public UserWebClient(ReactorLoadBalancerExchangeFilterFunction lbFunction){
        this.lbFunction = lbFunction;
        
        this.webClient = WebClient.builder()
                                    .filter(this.lbFunction)
                                    .baseUrl("http://userservice/user/wc")
                                    .build();
    }

    public UserResponse getUserById(Long id){
        UserResponse userResponse = webClient.get()
                        .uri("/" + id)
                        .retrieve()
                        .bodyToMono(UserResponse.class)
                        .block();
        log.info(userResponse.toString());
        return userResponse;
    }
}
