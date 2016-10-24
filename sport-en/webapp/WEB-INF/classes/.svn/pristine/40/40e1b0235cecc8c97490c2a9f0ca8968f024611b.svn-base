package com.appscomm.sport.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.XmlAwareFormHttpMessageConverter;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;

/**继承sping的RestTemplate的实现，改变内部使用utf-8进行编码，处理传中文乱码问题。针对spring-web.3.0.5版本上的问题
 * @author william
 *
 */
public class RestTemplate extends org.springframework.web.client.RestTemplate {

	public RestTemplate() {
		super();
		replateStringHttpMessageConverter();
	}

	public RestTemplate(ClientHttpRequestFactory requestFactory) {
		super(requestFactory);
	}

	/**
	 * 替换StringHttpMessageConverter的默认编码，解决中文提交会乱码的问题
	 */
	private void replateStringHttpMessageConverter() {
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(new MediaType("text", "plain", Charset
				.forName("utf-8")));
		list.add(MediaType.ALL);
		
		for (HttpMessageConverter<?> convertor : this.getMessageConverters()) {
			if (convertor instanceof StringHttpMessageConverter) {
				((StringHttpMessageConverter) convertor)
						.setSupportedMediaTypes(list);
			} else if (convertor instanceof XmlAwareFormHttpMessageConverter) {
				XmlAwareFormHttpMessageConverter xmc = (XmlAwareFormHttpMessageConverter) convertor;
				xmc.setCharset(Charset.forName("utf-8"));
				
				List<HttpMessageConverter<?>> partConverters = new ArrayList<HttpMessageConverter<?>>();
				
				partConverters.add(new ByteArrayHttpMessageConverter());
				
				StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
				stringHttpMessageConverter.setWriteAcceptCharset(false);
				stringHttpMessageConverter.setSupportedMediaTypes(list);
				partConverters.add(stringHttpMessageConverter);
				
				
				partConverters.add(new ResourceHttpMessageConverter());
				
				xmc.setPartConverters(partConverters);
			}

		}
	}
	
	/**
	 * 覆盖父类的方法，增加统一的access_token头
	 * @param url the fully-expanded URL to connect to
	 * @param method the HTTP method to execute (GET, POST, etc.)
	 * @param requestCallback object that prepares the request (can be <code>null</code>)
	 * @param responseExtractor object that extracts the return value from the response (can be <code>null</code>)
	 * @return an arbitrary object, as returned by the {@link ResponseExtractor}
	 */
	protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor) throws RestClientException {

		ClientHttpResponse response = null;
		try {
			ClientHttpRequest request = createRequest(url, method);
			//添加统一的access_token头
			request.getHeaders().add("access_token", "928e5543c3388dbd15565f8227e8de2d");
			if (requestCallback != null) {
				requestCallback.doWithRequest(request);
			}
			response = request.execute();
			if (!getErrorHandler().hasError(response)) {
				logResponseStatus(method, url, response);
			}
			else {
				handleResponseError(method, url, response);
			}
			if (responseExtractor != null) {
				return responseExtractor.extractData(response);
			}
			else {
				return null;
			}
		}
		catch (IOException ex) {
			throw new ResourceAccessException("I/O error: " + ex.getMessage(), ex);
		}
		finally {
			if (response != null) {
				response.close();
			}
		}
	}
	
	private void logResponseStatus(HttpMethod method, URI url, ClientHttpResponse response) {
		if (logger.isDebugEnabled()) {
			try {
				logger.debug(
						method.name() + " request for \"" + url + "\" resulted in " + response.getStatusCode() + " (" +
								response.getStatusText() + ")");
			}
			catch (IOException e) {
				// ignore
			}
		}
	}

	private void handleResponseError(HttpMethod method, URI url, ClientHttpResponse response) throws IOException {
		if (logger.isWarnEnabled()) {
			try {
				logger.warn(
						method.name() + " request for \"" + url + "\" resulted in " + response.getStatusCode() + " (" +
								response.getStatusText() + "); invoking error handler");
			}
			catch (IOException e) {
				// ignore
			}
		}
		getErrorHandler().handleError(response);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		RestTemplate rt = new RestTemplate();

		//MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
		//formData.add("personId", "1");
		//formData.add("content", "xxd这里是内容");
		String str = rt.getForObject("http://localhost:9080/appscomm-platform/api/group/1", String.class);
	System.out.println(str);
	}

}
