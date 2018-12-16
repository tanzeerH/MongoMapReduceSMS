/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsu.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author hossa
 */
public class Util {
    
    public static void insertion() {
        // database_new
        ArrayList<SMS>  list = new ArrayList<>();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader("C:\\Users\\hossa\\OneDrive\\Documents\\NetBeansProjects\\SMSTool\\data\\database_new.txt");
            br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                //list.add(sCurrentLine);
                String[] parts = sCurrentLine.split("###");
                SMS sms = new SMS();
                sms.sender = parts[0];
                sms.date = parts[1];
                sms.length = parts[2];
               // sms.isReceived = parts[3];
                sms.isSpam = parts[4];
                sms.content = parts[5];
                list.add(sms);
                //System.out.println(sCurrentLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }

        for (SMS sms : list) {
            try {

                DBObject obj = new BasicDBObject();

                /*sms.sender = parts[0];
                                sms.date = parts[1];
                                sms.length = Integer.parseInt(parts[2]);
                                sms.isReceived = parts[3];
                                sms.isSpam = parts[4];
                                sms.content = parts[5];
                                smsList.add(sms);*/
                obj.put("sender", sms.sender);
                obj.put("date", sms.date);
                obj.put("length", sms.length);
               // obj.put("isReceived", sms.i);
                obj.put("isSpam", sms.isSpam);
                obj.put("content", sms.content);

               Connection.getDataBaseInstance().getCollection(Connection.LISTING).insert(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static ArrayList<SMS> getAllList()
    {
     // int MAX_LIMIT = 100;
        ArrayList<SMS> smsList = new ArrayList<>();
        DB db = Connection.getDataBaseInstance();
        DBCursor cursor = db.getCollection(Connection.LISTING).find();
        if(cursor.hasNext())
            cursor.next();
        int count  = 0;
	while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            SMS sms = new SMS();
            sms._id=obj.get("_id").toString();
            sms.sender=obj.get("sender").toString();
            sms.date=obj.get("date").toString();
            sms.length=obj.get("length").toString();
            sms.isSpam=obj.get("isSpam").toString();
            sms.content=obj.get("content").toString();
            smsList.add(sms);
            count++;
           // if(count > MAX_LIMIT)
              //  break;
	    
	}
        Collections.reverse(smsList);
        return smsList;
       
    }
}
