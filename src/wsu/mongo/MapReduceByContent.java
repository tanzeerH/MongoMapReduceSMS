package wsu.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;

public class MapReduceByContent {

	public static String sampleMapReduce(String content) {
           // String price = "1";
           
            if(content.length() == 0)
               content = "no content";
            //amenities=amenities.toUpperCase();
		DB db = Connection.getDataBaseInstance();
		DBCollection listing = db.getCollection(Connection.LISTING);
		String map = "function() { " +
                           //"var n = amenities.search("+amenities+");"+
				"if (  this.content.search('"+content+"')  > -1 ) { " +

				"category = 'Result'; emit('Total number of SMS: ', 1);}" +

				" }";
		String reduce = "function(key, values) { " +

				"var total = 0; for(var i = 0; i < values.length; i++) {total += values[i];}"+

				"return total;} ";

		MapReduceCommand cmd = new MapReduceCommand(listing, map, reduce,

				null, MapReduceCommand.OutputType.INLINE, null);

		MapReduceOutput out = listing.mapReduce(cmd);

                String res= " keyword "+ content +" : ";
		for (DBObject o : out.results()) {
                       
                     res += o.get("_id").toString()+" : "+o.get("value").toString()+"\n";
			System.out.println(o.toString());
                       

		}
                if(res.length() == 0)
                    res= " No results found.";
                return res;

	}
	public static void main(String[] args) {
		//sampleMapReduce();
	}

}
