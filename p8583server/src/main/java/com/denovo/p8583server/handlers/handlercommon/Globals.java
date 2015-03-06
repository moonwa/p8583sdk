package com.denovo.p8583server.handlers.handlercommon;

import com.denovo.p8583server.memweb.IMemberWebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by Administrator on 2015/1/30.
 */
public class Globals {

    static Map<String,List<KeysEntry>> _keys=new HashMap<String,List<KeysEntry>>();

    public static KeysEntry SetKeyEntry(String shopName, String posName, String key1, String key2)
    {

        KeysEntry key = GetKeyEntry(shopName, posName);

        if (key == null)
        {
            KeysEntry keyEntry=new KeysEntry();
            keyEntry.setKey1(key1);
            keyEntry.setKey2(key2);
            keyEntry.setShopName(shopName);
            keyEntry.setPosName(posName);
            GetKeyEntry(keyEntry);
        }
        else
        {
            key.setKey1(key1); ;
            key.setKey2(key2);
        }
        return key;

    }


    public static KeysEntry GetKeyEntry(String shopName, String posName)  {
        if(_keys.containsKey(posName)) {
            return  (KeysEntry)_keys.get(posName).get(0);
        }
        return null;
    }

    public static Boolean GetKeyEntry(KeysEntry p1)  {

        boolean flag;
        String key=p1.getPosName();
        if(_keys.containsKey(key)){
            List<KeysEntry> value=_keys.get(key);
            value.add(p1);
            _keys.put(key, value);
            flag=true;
        }else{
            List<KeysEntry> value=new ArrayList<KeysEntry>();
            value.add(p1);
            _keys.put(key, value);
            flag=false;
        }
        return flag;

    }
    public static final String aaa = GetPortOrIp("webServiceIp");

    public static final String GetPortOrIp(String key){
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("config"));
            Properties p = new Properties();
            p.load(in);
            return  p.getProperty(key);
        }catch (Exception ex){
            return "";
        }

    }
    public  static final IMemberWebService GetMemberWebService() throws MalformedURLException {
        URL wsdlUrl = new URL(GetPortOrIp("webServiceIp"));
        Service s = Service.create(wsdlUrl, new QName("http://service.ws.crm.ce.com/", "IMemberWebServiceService"));
        return s.getPort(new QName("http://service.ws.crm.ce.com/", "IMemberWebServicePort"), IMemberWebService.class);
    }

}