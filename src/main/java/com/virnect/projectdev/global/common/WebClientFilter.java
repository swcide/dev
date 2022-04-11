package com.virnect.projectdev.global.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import reactor.core.publisher.Mono;

public class WebClientFilter {

	private static final Logger LOG = LoggerFactory.getLogger(WebClientFilter.class);

	public static ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(request -> {
			logMethodAndUrl(request);

			return Mono.just(request);
		});
	}

	public static ExchangeFilterFunction logResponse() {
		return ExchangeFilterFunction.ofResponseProcessor(response -> {
			logStatus(response);

			return logBody(response);
		});
	}

	private static void logStatus(ClientResponse response) {

		HttpStatus status = response.statusCode();

		LOG.info("response ===", response.releaseBody());
		LOG.info("Returned staus code {} ({})", status.value(), status.getReasonPhrase());

	}

	private static Mono<ClientResponse> logBody(ClientResponse response) {
		if (response.statusCode() != null && (response.statusCode().is4xxClientError() || response.statusCode()
			.is5xxServerError())) {
			return response.bodyToMono(String.class)
				.flatMap(body -> {
					LOG.info("Body is {}", body);
					return Mono.just(response);
				});
		} else {
			return Mono.just(response);
		}
	}

	private static void logHeaders(ClientResponse response) {
		LOG.info("response headers ", response.headers());
		response.headers().asHttpHeaders().forEach((name, values) -> {
			values.forEach(value -> {
				logNameAndValuePair(name, value);
			});
		});
	}

	private static void logHeaders(ClientRequest request) {
		request.headers().forEach((name, values) -> {
			values.forEach(value -> {
				logNameAndValuePair(name, value);
			});
		});
		LOG.info(String.valueOf(request.url()));
		System.out.println("request.body() = " + request.body());
	}

	private static void logNameAndValuePair(String name, String value) {
		LOG.info("request headers");
		LOG.info("{}={}", name, value);
		LOG.info("-------------------------------------");

	}

	private static void logMethodAndUrl(ClientRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append(request.method().name());
		sb.append(" to ");
		sb.append(request.url());

		LOG.info("logMethodAndUrl");
		LOG.info(sb.toString());
		LOG.info("----------------------------------");
	}
}