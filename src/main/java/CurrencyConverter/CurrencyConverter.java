package CurrencyConverter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("type Currency to Convert from");
        String convertFrom=scanner.nextLine();
        System.out.println("type Currency to Convert to");
        String covertTo=scanner.nextLine();
        System.out.println("type quantity to convert");
        BigDecimal quantity = scanner.nextBigDecimal();

        String urlstring= "https://api.exchangerate.host/latest?base=" + convertFrom.toUpperCase();


        OkHttpClient client= new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlstring)
                .get()
                .build();


        Response response = client.newCall(request).execute();
        String stringResponse = response.body().string();
        JSONObject jsonObject = new JSONObject(stringResponse);
        JSONObject rateobject = jsonObject.getJSONObject("rates");
        BigDecimal rate = rateobject.getBigDecimal(covertTo.toUpperCase());


        BigDecimal result= rate.multiply(quantity);
        System.out.println(result);




    }
}
