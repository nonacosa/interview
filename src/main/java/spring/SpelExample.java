package spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author wenda.zhuang
 * @Date 2020/8/13 23:49
 * @Description SPEL 表达式   maven: org.springframework - spring-expression
 * @E-mail sis.nonacosa@gmail.com
 */
public class SpelExample {

	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();

		//字符串解析
		String str = (String) parser.parseExpression("'你好'").getValue();
		System.out.println(str);

		//整型解析
		int intVal = (Integer) parser.parseExpression("0x2F").getValue();
		System.out.println(intVal);

		//双精度浮点型解析
		double doubleVal = (Double) parser.parseExpression("4329759E+22").getValue();
		System.out.println(doubleVal);


		//布尔型解析
		boolean booleanVal = (boolean) parser.parseExpression("true").getValue();
		System.out.println(booleanVal);

		//运算符解析
		int intExpression = (int) parser.parseExpression(" 1 + 2 * (1 + 2)").getValue();
		System.out.println(intExpression);
	}


	@Test
	public void testParserContext() {
		ExpressionParser parser = new SpelExpressionParser();
		ParserContext parserContext = new ParserContext() {
			@Override
			public boolean isTemplate() {
				return true;
			}
			@Override
			public String getExpressionPrefix() {
				return "#{";
			}
			@Override
			public String getExpressionSuffix() {
				return "}";
			}
		};
		String template = "#{'Hello '}#{'World!'}";
		Expression expression = parser.parseExpression(template, parserContext);
		Assert.assertEquals("Hello World!", expression.getValue());
	}

}
