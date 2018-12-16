package wsu.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;

public class MapReduce {

	public static String sampleMapReduce(String length) {
           // String price = "1";
           if(length.length() == 0)
               length = "0";
		DB db = Connection.getDataBaseInstance();
		DBCollection listing = db.getCollection(Connection.LISTING);
		String map = "function() { " +

				"if ( this.length >= " +length+" ) { " +

				"category = 'Long'; emit('Long', 1); emit('Short', 0);}" +

				"else {" +
                               " emit('Long', 0); emit('Short', 1);"+
				"} }";
		String reduce = "function(key, values) { " +

				"var total = 0; for(var i = 0; i < values.length; i++) {total += values[i];}"+

				"return total;} ";

		MapReduceCommand cmd = new MapReduceCommand(listing, map, reduce,

				null, MapReduceCommand.OutputType.INLINE, null);

		MapReduceOutput out = listing.mapReduce(cmd);

                String res= "";
		for (DBObject o : out.results()) {
                       
                     res += "    "+o.get("_id").toString()+" : "+o.get("value").toString()+"\n";
			System.out.println(o.toString());
                       

		}
                return res;

	}
	public static void main(String[] args) {
		//sampleMapReduce();
	}

}
