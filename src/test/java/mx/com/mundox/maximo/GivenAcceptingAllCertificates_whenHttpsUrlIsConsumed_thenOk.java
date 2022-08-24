package mx.com.mundox.maximo;

import static org.junit.Assert.*;

import java.security.GeneralSecurityException;

import javax.net.ssl.SSLSocketFactory;

import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class GivenAcceptingAllCertificates_whenHttpsUrlIsConsumed_thenOk {

	@Test
	public final void givenAcceptingAllCertificates_whenHttpsUrlIsConsumed_thenOk() 
			  throws GeneralSecurityException {
//		String urlOverHttps = " https://api.eurobits.mx/eurobits/production-mx/login/me";
//			    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//			    CloseableHttpClient httpClient = (CloseableHttpClient) requestFactory.getHttpClient();
//			 
//			    TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
//			    SSLSocketFactory sf = new SSLSocketFactory(acceptingTrustStrategy, ALLOW_ALL_HOSTNAME_VERIFIER);
//			    httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 8443, sf));
//			 
//			    ResponseEntity<String> response = new RestTemplate(requestFactory).
//			      exchange(urlOverHttps, HttpMethod.GET, null, String.class);
//			    assertThat(response.getStatusCode().value(), equalTo(200));
			}

}
