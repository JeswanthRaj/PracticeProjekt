package com.jms.projekt.utility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class JSONUtility {

	public JSONArray toJSONArray(ResultSet rs) {
		JSONArray jArray = new JSONArray();

		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()){
				JSONObject jObj = new JSONObject();
			for (int i = 1; i <= columnCount; i++) {
				String ColumnName = rsmd.getColumnName(i);
				if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
					jObj.put(ColumnName, rs.getInt(ColumnName));
				} else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {
					jObj.put(ColumnName, rs.getString(ColumnName));
					System.out.println(ColumnName+"::"+rs.getString(ColumnName));
				}
			}			
			jArray.put(jObj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jArray;
	}
}
