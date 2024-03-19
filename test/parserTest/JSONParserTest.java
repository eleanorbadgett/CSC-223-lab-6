package parserTest;


import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import input.builder.GeometryBuilder;
import input.components.ComponentNode;
import input.components.FigureNode;
import input.exception.ParseException;
import input.parser.JSONParser;


class JSONParserTest
{
	//protected DefaultBuilder gb = new GeometryBuilder();
	
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
	void single_triangle_test()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("single_triangle.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		//node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	@Test
	void star()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("star.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		//node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	@Test
	void new_box_triangle()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("box_with_triangle.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		//node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	@Test
	void fullyconnectedPolygon()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("fully_connected_irregular_polygon.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		//node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	@Test
	void crossingSymmetric()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("crossing_symmetric_triangle.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		//node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	@Test
	void collinearLineSegments()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("collinear_line_segments.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		//node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
}
