package input.components.point;

import input.components.ComponentNode;
import input.visitor.ComponentNodeVisitor;
import utilities.MathUtilities;
import utilities.StringUtilities;

/**
 * A 2D Point (x, y).
 * 
 * @author eleanorbadgett, sam nusstein, ellie johnson
 */
public class PointNode implements ComponentNode {
	protected static final String ANONYMOUS = "__UNNAMED";

	protected double _x;

	public double getX() {
		return this._x;
	}

	protected double _y;

	public double getY() {
		return this._y;
	}

	protected String _name;

	public String getName() {
		return _name;
	}

	/**
	 * Create a new Point with the specified coordinates.
	 * 
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 */
	public PointNode(double x, double y) {
		this(ANONYMOUS, x, y);
	}

	/**
	 * Create a new Point with the specified coordinates.
	 * 
	 * @param name -- The name of the point. (Assigned by the UI)
	 * @param x    -- The X coordinate
	 * @param y    -- The Y coordinate
	 */
	public PointNode(String name, double x, double y) {
		_x = x;
		_y = y;
		_name = name;
	}

	@Override
	public int hashCode() {
		return Double.valueOf(_x).hashCode() + Double.valueOf(_y).hashCode();
	}

	// checks to see if x,y points are close enough to be considered the same
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PointNode))
			return false;
		PointNode that = (PointNode) obj;

		return MathUtilities.doubleEquals(_x, that._x) && MathUtilities.doubleEquals(_y, that._y);
	}

	// string that gives the name of PointNode with the coordinates
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(_name + ":(" + _x + ", " + _y + ")");

		return str.toString();
	}

	public Object accept(ComponentNodeVisitor visitor, Object o) {
		return visitor.visitPointNode(this, o);
	}

//	@Override
//	public void unparse(StringBuilder sb, int level)
//	{
//		sb.append(StringUtilities.indent(level)+"Point("+getName()+")("
//				+getX()+","+getY()+")");
//	}

}