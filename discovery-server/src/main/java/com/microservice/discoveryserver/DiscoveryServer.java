package com.microservice.discoveryserver;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.glassfish.hk2.api.Factory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServer {
public static void main(String[] args) {
	
	SpringApplication.run(DiscoveryServer.class, args);
	
	List<Integer> asList = Arrays.asList(10,40,20,30,40,10);
	
	asList.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
	.entrySet().stream().filter(t ->t.getValue()>1 ).map(t ->t.getKey() )
	.forEach(System.out::println);
}
}
