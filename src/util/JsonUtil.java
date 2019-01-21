package util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	public static JSONArray formatRsToJsonArray(ResultSet rs)throws Exception{
		ResultSetMetaData md=rs.getMetaData();
		int num=md.getColumnCount();
		JSONArray array=new JSONArray();
		while(rs.next()){
			JSONObject mapOfColValues=new JSONObject();
			for(int i=1;i<=num;i++){
				Object o=rs.getObject(i);
				if(o instanceof Date){
					mapOfColValues.put(md.getColumnName(i), DateUtil.formatDate((Date)o, "yyyy-MM-dd hh:mm:ss"));
				}else{
					if(rs.getObject(i)==null){
						mapOfColValues.put(md.getColumnName(i), "");
					}
					else{
						mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
					}
				}
			}
			array.add(mapOfColValues);
		}
		
		return array;
	}
	
	public static JSONObject getJsonHeadFromRs(ResultSet rs)throws Exception{
		ResultSetMetaData md=rs.getMetaData();
		JSONObject result=new JSONObject();
		int columnNum=md.getColumnCount();
		for(int i=1;i<=columnNum;++i){
			result.put(md.getColumnName(i), md.getColumnName(i));
		}
		return result;
	}
	
	public static JSONArray getJsonbodyFromRs(ResultSet rs)throws Exception{
		ResultSetMetaData md=rs.getMetaData();
		JSONArray array=new JSONArray();
		int columnNum=md.getColumnCount();
		
		while(rs.next()){
		JSONObject map=new JSONObject();
		for(int i=1;i<=columnNum;++i){
			Object object=rs.getObject(i);
			if(object instanceof Date)
				map.put(md.getColumnName(i), DateUtil.formatDate((Date)object, "yyyy-MM-dd"));
			else
				map.put(md.getColumnName(i), rs.getObject(i));
		    }
		array.add(map);
		}
		return array;
	}
}