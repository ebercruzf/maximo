package mx.com.mundox.maximo.auth.client.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationUtils {
	public static List<String> getScopesList(String... scopes) {
		List<String> scopesList = new ArrayList<String>(Arrays.asList(scopes));
		return scopesList;
	}
}
