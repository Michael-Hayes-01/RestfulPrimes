package rbs;

import io.netty.handler.codec.http.HttpMethod;

import org.restexpress.RestExpress;

public abstract class Routes
{
	public static void define(Configuration config, RestExpress server)
    {
		// This route supports GET, POST, PUT, DELETE echoing the 'echo' query-string parameter in the response.
		// GET and DELETE are also supported but require an 'echo' header or query-string parameter.
		server.uri("/primes/{prime_id}", config.getPrimesController())
				.noSerialization();

//		// Waits the delay_ms number of milliseconds and responds with a 200.
//		// Supports GET, PUT, POST, DELETE methods.
//		server.uri("/success/{delay_ms}.{format}", config.getSuccessController());
//
//		// Waits the delay_ms number of milliseconds and responds with the
//		// specified HTTP response code.
//		// Supports GET, PUT, POST, DELETE methods.
//		server.uri("/status/{delay_ms}/{http_response_code}.{format}", config.getStatusController());


//		//routes
//		server.uri("/primes/{maxPrime}", config.getPrimesController())
//			.method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
//			.name(Constants.Routes.SINGLE_SAMPLE);
//
//		server.uri("/primes/here.{format}", config.getPrimesController())
//			.action("readAll", HttpMethod.GET)
//			.method(HttpMethod.POST)
//			.name(Constants.Routes.SAMPLE_COLLECTION);
// or...
//		server.regex("/some.regex", config.getRouteController());
    }
}
