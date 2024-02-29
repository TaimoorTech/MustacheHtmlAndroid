package com.example.liquidtemplateapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;

import android.webkit.WebView;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
//            String htmlTemplate = loadTemplateFromAssets("Equipment_Trending.html");
//        String htmlTemplate = loadTemplateFromAssets("template.html");
//        String htmlTemplate = loadTemplateFromAssets("example.html");
        String htmlTemplate = loadHtmlTemplate();

//            String fn = "John";
//            String ln = "Doe";
            // Create a map to hold dynamic data
            Map<String, String> data = new HashMap<>();
//        data.put("firstName", fn);
//        data.put("lastName", ln);

            data.put("title", title);
            data.put("date", date);
            data.put("name", name);
            data.put("category", category);
            data.put("manufacturer", manufacturer);
            data.put("country", country);
//        data.put("amounts1", "4,312");
//        data.put("amounts2", "2,949");
//        data.put("amounts3", "1,363");

//        Map<String, Object> context = new HashMap<>();
//        context.put("title", "Dynamic HTML with Mustache");
//        context.put("heading", "Welcome to my App");
//        context.put("content", "This is some dynamic content rendered with Mustache tags.");
//        context.put("background_color", "#f0f0f0");
//        context.put("text_color", "#333333");


            // Render HTML template with dynamic data
        String renderedHtml = renderHtmlTemplate(htmlTemplate, data);

        webView.loadDataWithBaseURL(null, renderedHtml, "text/html", "UTF-8", null);

        // Load rendered HTML into WebView
//            webView.loadData(renderedHtml, "text/html", "UTF-8");

//        webView.loadUrl("file:///android_asset/Equipment_Trending.html");
    }


//    private String loadTemplateFromAssets(String fileName) {
//        try {
//            InputStream inputStream = getAssets().open(fileName);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            StringBuilder stringBuilder = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//            reader.close();
//            return stringBuilder.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

//    private String renderHtmlTemplate(String template, Map<String, Object> data) {
//        return Mustache.compiler().compile(template).execute(data);
//    }

    private String loadHtmlTemplate() {
        try {
            // Load HTML template from assets folder
            InputStream inputStream = getAssets().open("Equipment_Trending.html");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String renderHtmlTemplate(String template, Map<String, String> context) {
        Template tmpl = Mustache.compiler().compile(template);
        return tmpl.execute(context);
    }

}