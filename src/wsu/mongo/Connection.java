
package wsu.mongo;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;


import com.mongodb.DB;
import com.mongodb.MongoClient;
public class Connection {

	private static DB db = null;
	public static final String DBNAME = "dbairbnb";
	public static final String LISTING = "sms";
	public static DB getDataBaseInstance() {
		if(db == null)
		{
			try {
				MongoClient mongoClient = new MongoClient("localhost");
				
		         db = mongoClient.getDB(DBNAME);
		         
		                 
		               
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return db;
	}
	public static void main(String[] args) {
		
	}
}
