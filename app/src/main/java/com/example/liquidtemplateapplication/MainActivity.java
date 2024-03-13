package com.example.liquidtemplateapplication;
import android.Manifest;
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
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    boolean pageLoaded = false;

    float oldDist = 1f;
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId = INVALID_POINTER_ID;

    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    WebView webView;
    String renderedHtml = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabZoomOut = findViewById(R.id.fab_zoom_out);
        webView = findViewById(R.id.webView);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript for proper functionality
        webView.setWebViewClient(new CustomWebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                // Check if the page has finished loading
                if (newProgress == 100) {
                    pageLoaded = true;
                }
            }
        });

        // Getting Storage Permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }

        ArrayList<String[]> list = templateList.listOfData;

        ArrayList<Map<String, String>> itemsDataList = new ArrayList<>();
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
            itemsDataList.add(data);
        }

        // Assigning All Variables
        Map<String, Object> finalDataList = new HashMap<>();
        finalDataList.put("title", "RIH Sheet");
        finalDataList.put("company", "#REF!");
        finalDataList.put("field_name", "#REF!");
        finalDataList.put("well_name", "#REF!");
        finalDataList.put("job_no", "#REF!");
        finalDataList.put("sensor_no", "51090019");
        finalDataList.put("checked_by", "Jaber");
        finalDataList.put("meggered_at", "1 K");
        finalDataList.put("rih_pane", "15220284");
        finalDataList.put("items", itemsDataList);
        finalDataList.put("aramco_rep", "");
        finalDataList.put("apc_rep", "Jaber Alhajri");


        renderedHtml = renderHtmlWithMustache(finalDataList);
        webView.loadDataWithBaseURL(null, renderedHtml, "text/html", "UTF-8", null);



        fabZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset the zoom level to its original view
                if (pageLoaded) {
                    // Reset the zoom level to its original view
                    webView.getSettings().setLoadWithOverviewMode(true);
                    webView.getSettings().setUseWideViewPort(true);
                    webView.loadDataWithBaseURL(null, renderedHtml, "text/html", "UTF-8", null);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        saveAsPDF();
                    }
                }, 2000);

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


    private String renderHtmlWithMustache(Map<String, Object> itemList) {
        try {
            // Load HTML template from assets folder
            InputStream inputStream = getAssets().open("rihSheet.html");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String templateString = new String(buffer, StandardCharsets.UTF_8);

            // Compile the template
            Template template = Mustache.compiler().compile(templateString);

            return template.execute(itemList);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();

        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                final float x = ev.getX();
                final float y = ev.getY();
                mActivePointerId = ev.getPointerId(0);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final int pointerIndex = ev.findPointerIndex(mActivePointerId);
                final float x = ev.getX(pointerIndex);
                final float y = ev.getY(pointerIndex);

                if (ev.getPointerCount() == 2) {
                    float newDist = spacing(ev);
                    if (newDist > 10f) {
                        float scale = newDist / oldDist;
                        webView.zoomBy(scale);
                        oldDist = newDist;
                    }
                }
                break;
            }

            case MotionEvent.ACTION_UP: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {
                final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)
                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                final int pointerId = ev.getPointerId(pointerIndex);
                if (pointerId == mActivePointerId) {
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mActivePointerId = ev.getPointerId(newPointerIndex);
                }
                break;
            }
        }
        return true;
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

}