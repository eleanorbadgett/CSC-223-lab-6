/**
 * parser for JSONfigures using builder
 * @author eleanorbadgett, Sam Nusstein, Ellie Johnson
 */
package input.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import input.builder.DefaultBuilder;
import input.builder.GeometryBuilder;
import input.components.*;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;
import input.exception.ParseException;

public class JSONParser
{
	protected ComponentNode  _astRoot;
	protected GeometryBuilder _builder = new GeometryBuilder();

	public JSONParser(DefaultBuilder builder)
	{
		_astRoot = null;
	}

	private void error(String message)
	{
		throw new ParseException("Parse error: " + message);
	}

	public ComponentNode parse(String str) throws ParseException
	{ 
		JSONTokener tokenizer = new JSONTokener(str);
		
		//calls an error if there is no JSON figure
		if(!str.contains(JSON_Constants.JSON_FIGURE) && !str.contains(JSON_Constants.JSON_FIGURE_S)){
			error("nothing");
		}
		
		JSONObject  JSONroot = (JSONObject)tokenizer.nextValue();
		JSONObject figure = JSONroot.getJSONObject(JSON_Constants.JSON_FIGURE);
		
		return handleFigure(figure);
	}

	private ComponentNode handleFigure(JSONObject figure) 
	{
		String des = handleDescription(figure);
		PointNodeDatabase pnd = handlePointNodeDatabase(figure);
		SegmentNodeDatabase snd = handleSegmentNodeDatabase(figure, pnd);
		
		return _builder.buildFigureNode(des, pnd, snd);
	}

	private String handleDescription(JSONObject root) 
	{
		return root.getString(JSON_Constants.JSON_DESCRIPTION); 	
	}

	private PointNodeDatabase handlePointNodeDatabase(JSONObject root) 
	{  	
		JSONArray jsonArray = root.getJSONArray(JSON_Constants.JSON_POINT_S);

		List<PointNode> pn = new ArrayList<PointNode>();
		
		for(int i = 0; i<jsonArray.length();i++) {

			pn.add( handlePointNode((JSONObject)jsonArray.getJSONObject(i)));
		}
		
		return _builder.buildPointDatabaseNode(pn);
	}

	private PointNode handlePointNode(JSONObject point) {
		String name = point.getString(JSON_Constants.JSON_NAME);
		
		double X = point.getInt(JSON_Constants.JSON_X);
		double Y = point.getInt(JSON_Constants.JSON_Y);
		return _builder.buildPointNode(name, X, Y);
	}

	private SegmentNodeDatabase handleSegmentNodeDatabase(JSONObject root, PointNodeDatabase pd) 
	
	{
		JSONArray array = root.getJSONArray(JSON_Constants.JSON_SEGMENTS);
		SegmentNodeDatabase segmentDatabase = _builder.buildSegmentNodeDatabase();
		
		for(int i = 0; i < array.length() ; i++) 
		{
			JSONObject temp = array.getJSONObject(i);
			JSONArray arr = temp.names();
			
			//uses the key pointNode to get the rest of the array of point node
			String key = arr.getString(0);
			PointNode origin = pd.nodeFromName(key);
			
			
			List<PointNode> list = handleListNodes(temp.getJSONArray(key), pd);
			segmentDatabase.addAdjacencyList(origin, list);
		}
		
		return segmentDatabase;
	}

	//processes and returns PointNodes for the SegmentNode Database
	private List<PointNode> handleListNodes(JSONArray segment, PointNodeDatabase pdatabase) {
		List<PointNode> nodes = new ArrayList<PointNode>();
		
		for(int i = 0; i < segment.length(); i++)
		{
			nodes.add(pdatabase.nodeFromName(segment.getString(i)));
		}
		
		return nodes;
	}

}