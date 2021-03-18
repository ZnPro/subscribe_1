package com.cos.reactorex02;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitResult;

@RestController
public class TestController2 {
	
	Sinks.Many<String> sink; //프로세서임(지속적응답 역할)
	
	//multicast() 새로 들어온 데이터만 응답받음 hot 시퀀스= 스트림
	//replay() 기존 데이터 + 새로운 데이터 응답 cold 시퀀스
	
	public TestController2() {
		this.sink= Sinks.many().multicast().onBackpressureBuffer();
	}
	
	@GetMapping("/send")
	public void send() {
		EmitResult result= sink.tryEmitNext("Hello World");
	}
	
	//data: 실제값 \n\n
	@GetMapping(value = "/sse")
	public Flux<ServerSentEvent<String>> sse() { //ServerSentEvent의 ContentType은 text event stream 
		return sink.asFlux().map(e->ServerSentEvent.builder(e).build()).doOnCancel(()->{
			System.out.println("SSE 종료됨");
		}); //구독
	}

}
