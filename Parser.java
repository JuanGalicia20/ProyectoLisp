public class Parser {
    Vector <ParseTree> statements = new Vector <ParseTree> ();

    public Parser(Vector <String> tokens) throws Exception{
		Vector <String> t;
		int i = 0;
		int k, l;

		while ( i < tokens.size() && i >= 0 ){
			// The variable l must always hold the last location of the current statement!!!
			k = tokens.indexOf("(", i);
			if ( k == i ){
				// There is a new statement to add - starting at i
				l = endOfExpression(new Vector <String> (tokens.subList(i, tokens.size()))) + i;
			} else if ( k > i ){
				// The next statement is after some non-parenthetical stuff
				l = k - 1;
			} else {
				// There are no more parenthetical statements. Parse the rest of the program
				l = tokens.size() - 1;
			}

			t = convertToDotNotation( new Vector <String> (tokens.subList(i, l+1)));
			statements.add(new ParseTree(t));

			i = l + 1;
		}
	}

}