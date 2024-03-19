package input.components.segment;
/**
 * Creates SegmentNodeDatabase, links points with all adjacent points
 * @author eleanorbadgett, sam nusstein, ellie johnson
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import input.components.ComponentNode;
import input.components.point.PointNode;
import input.visitor.ComponentNodeVisitor;
import utilities.StringUtilities;

public class SegmentNodeDatabase implements ComponentNode
{

	protected Map<PointNode, Set<PointNode>> _adjLists;

	public SegmentNodeDatabase() {_adjLists =  new LinkedHashMap<PointNode, Set<PointNode>>();}

	public SegmentNodeDatabase(Map<PointNode, Set<PointNode>> m) 
	{
		_adjLists =  new LinkedHashMap<PointNode, Set<PointNode>>(m);
	}

	public int numUndirectedEdges() 
	{
		int sum =0;
		//adds number of directed edges each point has
		for (Set<PointNode> set : _adjLists.values()) 
		{
			sum += set.size();
		}
		//two directed edges make one undirected edge
		return sum/2;
	}

	private void addDirectedEdge(PointNode node1, PointNode node2) 
	{
		if(!(_adjLists.containsKey(node1))) 
		{
			//creates new point and the connected edge if not in database
			Set<PointNode> node2set = new HashSet<>(Arrays.asList(node2));
			_adjLists.put(node1, node2set);
		}
		else{_adjLists.get(node1).add(node2);}
	}

	public void addUndirectedEdge(PointNode node1, PointNode node2) 
	{
		addDirectedEdge(node1,node2);
		addDirectedEdge(node2,node1);
	}

	public void addAdjacencyList(PointNode node1, List<PointNode> nodelist)
	{
		Set<PointNode> nodelistset = new HashSet<>((nodelist));
		_adjLists.put(node1, nodelistset);
	}

	public List<SegmentNode> asSegmentList()
	{
		List<SegmentNode> snl = new LinkedList<SegmentNode>();

		for(PointNode firstVal:_adjLists.keySet()) 
		{
			for(PointNode secondVal: _adjLists.get(firstVal))
			{
				snl.add(new SegmentNode(firstVal, secondVal));
			}
		}
		return snl;
	}

	public List<SegmentNode> asUniqueSegmentList()
	{
		List<SegmentNode> snl = new LinkedList<SegmentNode>();

		for(PointNode firstVal:_adjLists.keySet()) 
		{
			for(PointNode secondVal: _adjLists.get(firstVal)) 
			{
				SegmentNode compare = new SegmentNode(secondVal, firstVal);
				SegmentNode sn = new SegmentNode(firstVal, secondVal);

				if(!(snl.contains(compare))) 
				{
					snl.add(sn);
				}
			}
		}
		return snl;
	}

	public String toString() {return _adjLists.toString();}


	public Map<PointNode, Set<PointNode>> getAdjList() {
		return _adjLists;
	}
	public Object accept(ComponentNodeVisitor visitor, Object o) {
		return visitor.visitSegmentDatabaseNode(this, o);
	}

//	@Override
//	public void unparse(StringBuilder sb, int level) 
//	{	
//		Set<PointNode> keyset = _adjLists.keySet();
//
//		for(PointNode key:keyset) 
//		{
//			sb.append(StringUtilities.indent(level)+"\n");
//			sb.append(StringUtilities.indent(level)+key.getName()+" : ");
//
//			for(PointNode node:_adjLists.get(key))
//			{
//				sb.append(node.getName()+" ");
//			}
//		}
//	}
}
