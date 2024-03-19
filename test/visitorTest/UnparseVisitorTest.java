package visitorTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.AbstractMap;

import org.junit.jupiter.api.Test;

import input.builder.GeometryBuilder;
import input.components.ComponentNode;
import input.components.FigureNode;
import input.exception.ParseException;
import input.parser.JSONParser;
import input.visitor.ToJSONvisitor;
import input.visitor.UnparseVisitor;

public class UnparseVisitorTest {
	
	public static ComponentNode runFigureParseTest(String filename)
	{
		GeometryBuilder db = new GeometryBuilder();
		
		JSONParser parser = new JSONParser(db);

		String figureStr = utilities.FileUtilities.readFileFilterComments(filename);
		
		return parser.parse(figureStr);
	}
	
	@Test
	void empty_json_string_test()
	{
		GeometryBuilder gb = new GeometryBuilder();
		JSONParser parser = new JSONParser(gb);

		assertThrows(ParseException.class, () -> { parser.parse("{}"); });
	}

	@Test
	void star()
	{
		ComponentNode node = UnparseVisitorTest.runFigureParseTest("star.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		Object o = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		
		unparser.visitFigureNode((FigureNode)node, o);
		
		System.out.println(sb.toString());
		
		System.out.println("JSONObject Version: ");
		ToJSONvisitor visitor = new ToJSONvisitor();
		System.out.println(visitor.visitFigureNode((FigureNode) node,o).toString(3));
	}
	
	@Test
	void single_triangle_test()
	{
		ComponentNode node = UnparseVisitorTest.runFigureParseTest("single_triangle.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		Object o = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		
		unparser.visitFigureNode((FigureNode)node, o);
		
		System.out.println(sb.toString());
		
		System.out.println("JSONObject Version: ");
		ToJSONvisitor visitor = new ToJSONvisitor();
		System.out.println(visitor.visitFigureNode((FigureNode) node,o).toString(3));
	}
	
	@Test
	void new_box_triangle()
	{
		ComponentNode node = UnparseVisitorTest.runFigureParseTest("box_with_triangle.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		Object o = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		
		unparser.visitFigureNode((FigureNode)node, o);
		
		System.out.println(sb.toString());
		
		System.out.println("JSONObject Version: ");
		ToJSONvisitor visitor = new ToJSONvisitor();
		System.out.println(visitor.visitFigureNode((FigureNode) node,o).toString(3));
	}
	
	@Test
	void fullyconnectedPolygon()
	{
		ComponentNode node = UnparseVisitorTest.runFigureParseTest("fully_connected_irregular_polygon.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		Object o = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		
		unparser.visitFigureNode((FigureNode)node, o);
		
		System.out.println(sb.toString());
		
		System.out.println("JSONObject Version: ");
		ToJSONvisitor visitor = new ToJSONvisitor();
		System.out.println(visitor.visitFigureNode((FigureNode) node,o).toString(3));
	}
	
	@Test
	void crossingSymmetric()
	{
		ComponentNode node = UnparseVisitorTest.runFigureParseTest("crossing_symmetric_triangle.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		Object o = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		
		unparser.visitFigureNode((FigureNode)node, o);
		
		System.out.println(sb.toString());
		
		System.out.println("JSONObject Version: ");
		ToJSONvisitor visitor = new ToJSONvisitor();
		System.out.println(visitor.visitFigureNode((FigureNode) node,o).toString(3));
	}
	
	@Test
	void collinearLineSegments()
	{
		ComponentNode node = UnparseVisitorTest.runFigureParseTest("collinear_line_segments.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		Object o = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		
		unparser.visitFigureNode((FigureNode)node, o);
		
		System.out.println(sb.toString());
		
		System.out.println("JSONObject Version: ");
		ToJSONvisitor visitor = new ToJSONvisitor();
		System.out.println(visitor.visitFigureNode((FigureNode) node,o).toString(3));
	}
	
}
