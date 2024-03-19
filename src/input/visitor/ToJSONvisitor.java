package input.visitor;

import java.util.Map;

import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;
import input.parser.JSON_Constants;

/**
 * Class that converts the AST of a geometry back into a JSON Object
 * @author Eleanor Badgett, Sam Nusstein, Ellie Johnson
 */
public class ToJSONvisitor implements ComponentNodeVisitor {


	public JSONObject visitFigureNode(FigureNode node, Object o) {
		//creates base for object
		JSONObject figure = new JSONObject();

		//adds all same level values, des and databases
		figure.put(JSON_Constants.JSON_DESCRIPTION, node.getDescription());

		figure.put(JSON_Constants.JSON_POINT_S, visitPointNodeDatabase(node.getPointsDatabase(),null));

		figure.put(JSON_Constants.JSON_SEGMENTS, visitSegmentDatabaseNode(node.getSegments(),null));


		JSONObject obj = new JSONObject();

		//joins levels together, top level figure with description, and databases
		obj.put(JSON_Constants.JSON_FIGURE, figure);
		obj.put("Figure", figure);
		return obj;
	}


	@Override
	public Object visitSegmentDatabaseNode(SegmentNodeDatabase node, Object o) 
	{

		JSONArray arr = new JSONArray();

		//traverses through adj list, creating array of segments
		Map<PointNode, Set<PointNode>> ls =  node.getAdjList();
		for(Entry<PointNode, Set<PointNode>> EntSet: ls.entrySet()) {
			String s = new String();
			s=(EntSet.getKey().getName())+":";
			s= s+EntSet.getValue();
			arr.put(s);
		}

		return arr;
	}

	@Override
	public Object visitSegmentNode(SegmentNode node, Object o) {
		return null;
	}

	@Override
	public Object visitPointNode(PointNode node, Object o) 
	{
		JSONObject obj = new JSONObject();

		obj.put(JSON_Constants.JSON_NAME, node.getName());
		obj.put(JSON_Constants.JSON_X, node.getX());
		obj.put(JSON_Constants.JSON_Y, node.getY());

		return obj;
	}

	@Override
	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o) 
	{
		JSONArray arr = new JSONArray();
		for(PointNode pn: node.getPoints())
		{
			arr.put(visitPointNode(pn,null));
		}

		return arr;
	}

}
