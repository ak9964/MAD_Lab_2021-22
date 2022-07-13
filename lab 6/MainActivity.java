package com.example.lab6a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> numberlist = new ArrayList<>();
    Button parsexml, parsejson;
    TextView displayResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        displayResult = findViewById(R.id.display_result);

        parsexml = findViewById(R.id.buttonxml);
        parsejson = findViewById(R.id.buttonjson);


        parsexml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputStream is = getAssets().open("city.xml");

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(is);

                    Element element = doc.getDocumentElement();
                    element.normalize();

                    NodeList nList = doc.getElementsByTagName("place");

                    displayResult.setText(" ");
                    for (int i = 0; i < nList.getLength(); i++) {

                        Node node = nList.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element2 = (Element) node;

                            displayResult.setText(displayResult.getText() + "\n Name : " + getValue("name", element2) + "\n");
                            displayResult.setText(displayResult.getText() + " Latitude : " + getValue("lat", element2) + "\n");
                            displayResult.setText(displayResult.getText() + " Longitude : " + getValue("long", element2) + "\n");
                            displayResult.setText(displayResult.getText() + " Temperature : " + getValue("temperature", element2) + "\n");
                            displayResult.setText(displayResult.getText() + " Humidity : " + getValue("humidity", element2) + "\n");
                            displayResult.setText(displayResult.getText() + "----------------------- ");
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        });

        parsejson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String json;

                try
                {
                        InputStream is = getAssets().open("example.json");
                        int size = is.available();
                        byte[] buffer = new byte[size];
                        is.read(buffer);
                        is.close();

                        json = new String(buffer,"UTF-8");
                        JSONArray jsonArray = new JSONArray(json);

                        displayResult.setText(" ");

                        for(int i = 0; i<jsonArray.length(); i++)
                        {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            displayResult.setText(displayResult.getText() + "\n Name : " + obj.getString("name") + "\n");
                            displayResult.setText(displayResult.getText() + " Latitude : " + obj.getString("lat") + "\n");
                            displayResult.setText(displayResult.getText() + " Longitude : " + obj.getString("long") + "\n");
                            displayResult.setText(displayResult.getText() + " Temperature : " + obj.getString("temperature") + "\n");
                            displayResult.setText(displayResult.getText() + " Humidity : " + obj.getString("humidity") + "\n");
                            displayResult.setText(displayResult.getText() + "*********************** ");
                        }

                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        });

    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
