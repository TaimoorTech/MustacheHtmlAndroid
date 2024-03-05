package com.example.liquidtemplateapplication;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    WebView webView;
    String renderedHtml = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }

        ArrayList<String[]> list = templateList.listOfData;

        ArrayList<Map<String, String>> dataList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> data = new HashMap<>();
            data.put("date",list.get(i)[0]);
            data.put("time",list.get(i)[1]);
            data.put("joints",list.get(i)[2]);
            data.put("pi_psi",list.get(i)[3]);
            data.put("pd_psi",list.get(i)[4]);
            data.put("ti_psi",list.get(i)[5]);
            data.put("tm_f",list.get(i)[6]);
            data.put("vx_f",list.get(i)[7]);
            data.put("vz_g",list.get(i)[8]);
            data.put("vt_mA",list.get(i)[9]);
            data.put("ct_mA",list.get(i)[10]);
            data.put("merger",list.get(i)[11]);
            data.put("phase",list.get(i)[12]);
            data.put("comments",list.get(i)[13]);
            dataList.add(data);
        }


        renderedHtml = renderHtmlWithMustache(dataList);
        webView.loadDataWithBaseURL(null, renderedHtml, "text/html", "UTF-8", null);


        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageCommitVisible (WebView view, String url){
                saveAsPDF();
            }


        });


    }

    private void saveAsPDF() {
        // Create a bitmap of the WebView content
        // Create a PDF document
        PdfDocument document = new PdfDocument();

        for (int i = 1; i <= 2; i++) {
            Bitmap bitmap = Bitmap.createBitmap(webView.getWidth(), webView.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            webView.draw(canvas);
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(),
                    i).create();
            PdfDocument.Page page = document.startPage(pageInfo);

            // Draw the bitmap onto the PDF page
            Canvas pdfCanvas = page.getCanvas();
            pdfCanvas.drawBitmap(bitmap, 0, 0, null);

            // Finish the page
            document.finishPage(page);
        }

        // Save the PDF document
        File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "webview_content.pdf");
        try {
            document.writeTo(new FileOutputStream(pdfFile));
            Toast.makeText(this, "PDF saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save PDF", Toast.LENGTH_SHORT).show();
        }

        // Close the PDF document
        document.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, reload the WebView to trigger PDF creation
                webView.reload();
            } else {
                // Permission denied, show a message or handle it accordingly
                Toast.makeText(this, "Permission denied to write to external storage", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private String loadHtmlTemplate() {
        try {
            // Load HTML template from assets folder
            InputStream inputStream = getAssets().open("secondHTML.html");
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

    private String renderHtmlWithMustache(List<Map<String, String>> itemList) {
        try {
            // Load HTML template from assets folder
            InputStream inputStream = getAssets().open("secondHTML.html");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String templateString = new String(buffer, StandardCharsets.UTF_8);

            // Compile the template
            Template template = Mustache.compiler().compile(templateString);

            // Render the template with data
            Map<String, Object> data = new HashMap<>();
            data.put("items", itemList);
            return template.execute(data);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}