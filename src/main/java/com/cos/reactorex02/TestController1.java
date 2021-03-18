package com.cos.reactorex02;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//Netty 서버는 비동기 서버, Tomcat서버는 스레드 서버

@RestController
public class TestController1 {

	@GetMapping("/flux1")
	public Flux<Integer> flux1() {
		return Flux.just(1,2,3,4);//request(unbounded)
	}
	
	@GetMapping(value = "/flux2", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Integer> flux2() {
		return Flux.just(1,2,3,4).delayElements(Duration.ofSeconds(3)).log();//request(unbounded)
	}
	
	@GetMapping(value = "/flux3", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Long> flux3() {
		return Flux.interval(Duration.ofSeconds(1)).log();
	}
	
	@GetMapping(value = "/mono2")
	public Mono<Integer> mono2() {
		return Mono.just(1).log();//request(unbounded)
	}
}
