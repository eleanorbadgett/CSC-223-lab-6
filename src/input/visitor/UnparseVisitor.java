package input.visitor;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

import input.components.*;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;
import utilities.StringUtilities;


/**
 *  This file implements a Visitor (design pattern) 
 *  with the intent of building an unparsed, 
 *  String representation of a geometry figure.
 *  @author Eleanor Badgett, Sam Nusstein, Ellie Johnson
 */

public class UnparseVisitor implements ComponentNodeVisitor
{
	@Override
	//method that visits and creates the FigureNode
	public Object visitFigureNode(FigureNode node, Object o)
	{
		// Unpack the input object containing a Stringbuilder and an indentation level
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		//Sets up the structure of the string
		sb.append(StringUtilities.indent(level)+ "Figure" +"\n");
		sb.append(StringUtilities.indent(level)+"{"+"\n");
		sb.append(StringUtilities.indent(level+1)+ "Description: " +node.getDescription()+ "\n");
		
		sb.append(StringUtilities.indent(level+1) + "Points: "+"\n");
		sb.append(StringUtilities.indent(level+1) + "{");

		//unpacks object then visits the PointNode Database
		o = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, level+1);
		node.getPointsDatabase().accept(this,o);
		sb.append(StringUtilities.indent(level+1)+"\n");
		sb.append(StringUtilities.indent(level+1)+"}"+"\n");
		
		sb.append(StringUtilities.indent(level+1) + "Segments: " + "\n");
		sb.append(StringUtilities.indent(level+1) + "{");
		
		//unpacks object then visits the SegmentNode Database
		o=new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, level+2);
		node.getSegments().accept(this,o);
		sb.append("\n");
		sb.append(StringUtilities.indent(level+1) + "}" + "\n");
		sb.append(StringUtilities.indent(level) + "}" + "\n");

		return pair.getKey();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	//uses the adjacency list representation to get all the segment nodes
	public Object visitSegmentDatabaseNode(SegmentNodeDatabase node, Object o)
	{
		// Unpack the input object containing a Stringbuilder and an indentation level
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();
		
		Map<PointNode, Set<PointNode> > snd = node.getAdjList();

		Set<PointNode> keyset = snd.keySet();
		
		for(PointNode key:keyset) 
		{
			sb.append(StringUtilities.indent(level)+"\n");
			sb.append(StringUtilities.indent(level)+key.getName()+" : ");
			
			Set<PointNode> pnd = snd.get(key);
			for(PointNode pn: pnd) {
			{
				sb.append(pn.getName()+" ");
			}
		}
		}
		return null;
	}

	/**
	 * This method should NOT be called since the segment database
	 * uses the Adjacency list representation
	 */
	@Override
	public Object visitSegmentNode(SegmentNode node, Object o)
	{
		return null;
	}

	@Override
	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o)
	{
		// Unpack the input object containing a Stringbuilder and an indentation level
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();
		
		o = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, level+1);
		
		for(PointNode pointNode : node.getPoints())
		{
			//gets all points in the database
			sb.append(StringUtilities.indent(level)+"\n");
			pointNode.accept(this, o);
		}
		return null;
	}

	@Override
	public Object visitPointNode(PointNode node, Object o)
	{
		//Unpacks the input object containing a Stringbuilder and an indentation level 
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();
		
		//makes each point readable in the format
		sb.append(StringUtilities.indent(level)+"Point("+node.getName()+")("
				+node.getX()+","+node.getY()+")");
		
		return null;
	}
}