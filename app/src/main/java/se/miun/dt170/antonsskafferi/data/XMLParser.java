package se.miun.dt170.antonsskafferi.data;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    //private String urlString = "http://10.250.121.124:8080/antonapi/webresources/entities.foods";
    //private String urlString = "https://entresundsvall.se/arrangemang/feed/";
    private String urlString = "https://www.bokman.nu/xmldata.xml";
    private XmlPullParserFactory xmlFactoryObject;
    private List<MenuItem> menuitemlist;
    private MenuItem menuobject;

    public void fetchXML() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL(urlString);
                    HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                    connect.setReadTimeout(10000);
                    connect.setConnectTimeout(15000);
                    connect.setRequestMethod("GET");
                    connect.setDoInput(true);
                    connect.setDoOutput(true);
                    connect.connect();

                    InputStream stream = connect.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = xmlFactoryObject.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(stream, null);
                    parseXMLAndStoreIt(parser);
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


    public void parseXMLAndStoreIt(XmlPullParser myParser) {

        menuitemlist = new ArrayList<>();
        menuobject = new MenuItem();

        int event;
        String tag = "";
        String text = "";

        try {
            event = myParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT)
            {
                tag = myParser.getName();
                switch (event)
                {
                    case XmlPullParser.START_TAG:
                        switch(tag){
                            case "foodCategory":
                                    //Log.d(TAG, "TOUCHDOWN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
                                    break;
                            case "foodId":
                                    //Log.d(TAG, "TOUCHDOWN IDIDIDIDIDIDIDIDIDIDIDIDIDIDIIDIDIDIDID ");
                                    break;
                        }

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        switch (tag) {
                            case "foodCategory":
                                menuobject.setCategory(text);
                                Log.d("FOODCATEGORY!!!!!!!!!", text);
                                break;
                            case "foodName":
                                menuobject.setTitle(text);
                                Log.d("FOODNAME TITLE!!!!!!", text);
                                break;
                            case "foodPrice":
                                menuobject.setCost(text);
                                Log.d("FOODNAME PRICE!!!!", text);
                                break;
                            case "foods":
                                if (menuobject != null)
                                    getMenuitemlist().add(menuobject);
                                break;
                        }
                        break;

                }
                event = myParser.next();

            }
            Log.d("SIIIIIIIZE!!!!", String.valueOf(getMenuitemlist().size()));

            //RETURN menuitemlist till MenuItemRepository!!!!!!!!!!!!!!!!!!!!!!!!


        } catch (XmlPullParserException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //return getMenuitemlist();

    }


    public List<MenuItem> getMenuitemlist() {
        return menuitemlist;
    }
}
