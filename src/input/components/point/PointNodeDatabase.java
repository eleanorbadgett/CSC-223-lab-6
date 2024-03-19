package input.components.point;

/**
 * Creates a database for pointnodes
 * @author eleanorbadgett, sam nusstein, ellie johnson
 */
import java.util.LinkedHashSet;
import java.util.Set;

import input.components.ComponentNode;
import input.visitor.ComponentNodeVisitor;
import utilities.StringUtilities;

public class PointNodeDatabase implements ComponentNode {

	protected Set<PointNode> _points;

	public PointNodeDatabase() {
		_points = new LinkedHashSet<PointNode>();
	}

	public Set<PointNode> getPoints() {
		return _points;
	};

	// adds values into the database, making values with many decimals meet the
	// equals requirement
	public void put(PointNode node) {
		if (!(contains(node))) {
			_points.add(node);
		} else {
			for (PointNode n : _points) {
				if (getPoint(n).equals(node)) {
					n._name = node.getName();
				}
			}
		}

	}

	// sees if a node is already in database by being given the node
	public boolean contains(PointNode node) {
		return _points.contains(node);
	}

	// sees if a PointNode is already in database by being given the coordinates
	public boolean contains(double x, double y) {
		return (contains(new PointNode(x, y)));
	}

	// gets the name of a PointNode given a PointNode
	public String getName(PointNode pointNode) {
		return pointNode.getName();
	}

	// gets name of a PointNode given coordinates
	public String getName(double x, double y) {
		PointNode pn = new PointNode(x, y);
		for (PointNode pointN : _points) {
			if ((pn.equals(pointN))) {
				return pointN.getName();
			}
		}
		return null;
	}

	// gets the coordinates of a PointNode given the PointNode
	public PointNode getPoint(PointNode pointNode) {
		for (PointNode pn : _points) {
			if (pn.equals(pointNode)) {
				return pn;
			}
		}
		return null;
	}

	// getPoints should call the other
	// gets the coordinates of a PointNode given coordinates
	public PointNode getPoint(double x, double y) {
		PointNode pn = new PointNode(x, y);

		for (PointNode pointN : _points) {
			if ((pn.equals(pointN))) {
				return pointN;
			}
		}
		return null;
	}

	// getPoints should call the other
	// gets the coordinates of a PointNode given coordinates
	public PointNode nodeFromName(String name) {
		for (PointNode pointN : _points) {
			if ((name.equals(pointN.getName()))) {
				return pointN;
			}
		}
		return null;
	}

	public String toString() {
		return _points.toString();
	}

	public Object accept(ComponentNodeVisitor visitor, Object o) {
		return visitor.visitPointNodeDatabase(this, o);
	}

//	@Override
//	public void unparse(StringBuilder sb, int level) {
//		for(PointNode pointNode : _points)
//		{
//			sb.append(StringUtilities.indent(level)+"\n");
//			pointNode.unparse(sb, level);
//		}
//	}

}