package com.example.liquidtemplateapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import com.github.spullara.mustache.DefaultMustacheFactory;
import com.samskivert.mustache.Mustache;
import com.github.spullara.mustache.MustacheFactory;

import android.webkit.WebView;

import com.samskivert.mustache.Mustache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript

        String title = "Equipment Trending Page";
        String name = "Shahab";
        String date = "9 jan 2024";
        String category = "Pump";
        String manufacturer = "APC";
        String country = "Columbia";
        String amounts1 = "4,312";
        String amounts2 = "2,949";
        String amounts3 = "1,363";

        // Load Mustache template from assets folder
        try {
//            String htmlTemplate = loadTemplateFromAssets("Equipment_Trending.html");
//        String htmlTemplate = loadTemplateFromAssets("template.html");
            String htmlTemplate = template.liquidTemplate;
//
//            String fn = "John";
//            String ln = "Doe";
            // Create a map to hold dynamic data
            Map<String, String> data = new HashMap<>();
//        data.put("firstName", fn);
//        data.put("lastName", ln);
//        data.put("title", "Equipment Trending Page");
            data.put("date", "9 jan 2024");
            data.put("name", "Shahab");
            data.put("category", "Pump");
            data.put("manufacturer", "APC");
            data.put("country", "Columbia");
//        data.put("amounts1", "4,312");
//        data.put("amounts2", "2,949");
//        data.put("amounts3", "1,363");


            // Render HTML template with dynamic data
            String renderedHtml = renderHtmlTemplate(htmlTemplate, data);

            // Load rendered HTML into WebView
            webView.loadData(renderedHtml, "text/html", "UTF-8");
        }
        catch (Exception e){
            System.out.println(e);
        }
//        webView.loadUrl("file:///android_asset/Equipment_Trending.html");
    }


    private String loadTemplateFromAssets(String fileName) {
        try {
            InputStream inputStream = getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String renderHtmlTemplate(String template, Map<String, String> data) {
        return Mustache.compiler().compile(template).execute(data);
    }


}