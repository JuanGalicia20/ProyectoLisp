import java.util.*;

class ParseTree{

	private TreeNode root;

    public ParseTree(Vector <String> s) throws Exception{
		root = TreeNode.create(s);
	}

    protected String evaluate() throws Exception{
		String rtn = root.evaluate().toString();
		return rtn;
	}

    protected String print(){
		String result = root.toString();
		return result;
	}
}