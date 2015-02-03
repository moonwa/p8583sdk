package com.denovo.p8583server.handlers.handlercommon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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




}
