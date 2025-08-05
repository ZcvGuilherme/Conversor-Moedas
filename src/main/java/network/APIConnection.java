package network;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import elements.Coin;
import elements.Flag;

public class APIConnection {
	private static Dotenv dotenv = Dotenv.load();
	private static final String API_KEY = dotenv.get("API_KEY");
	private static final Gson gson = new Gson();
	private static final HttpClient client = HttpClient.newHttpClient();
	
	private Flag baseCurrency;
	
	public APIConnection(Flag baseCurrency) {
		setBase(baseCurrency);
	}
	
	public void setBase(Flag baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	
	public Flag getBase() {
		return baseCurrency;
	}
	public Coin connect(Flag coin) {
		String isoPair = String.format("%s-%s", coin.getCode(), baseCurrency.getCode()) ;
		String URL = String.format("https://economia.awesomeapi.com.br/json/last/%s?token=%s", isoPair, API_KEY);
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(URL))
				.GET()
				.build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			if (response.statusCode() == 200) {
				Map<String, Coin> map = gson.fromJson(response.body(),
						new TypeToken<Map<String, Coin>>(){}.getType());
				
				String key = isoPair.replace("-", "");
				return map.get(key);
			} else {
				//System.err.println("Erro ao buscar cotação: " + response.statusCode());
			}
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
