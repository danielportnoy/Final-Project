package final_2.main;

public class Main {

	public static void main(String[] args){

		//TEST 1
		/*

		Int_Literal ilOne = new Int_Literal(1);
		Int_Literal ilTwo = new Int_Literal(2);
		Bool_Literal bl = new Bool_Literal(true);

		Int_Bin_Expr_For_Int oPo = new Int_Bin_Expr_For_Int(ilOne, new Plus_For_Int(), ilOne);

		Bool_Bin_Expr_For_Int oPoEt = new Bool_Bin_Expr_For_Int(oPo, new Equals_For_Int(), ilTwo);

		System.out.println(ilOne.run());
		System.out.println(ilTwo.run());

		System.out.println(bl.run());

		System.out.println(oPoEt.run());

		 */

		//TEST 2
		/*

		LinkedList<Stmt> stmts = new LinkedList<Stmt>();

		// int i = 1;
		Int_Idnt i = new Int_Idnt("i");

		Simple_Asgn_Stmt_For_Int iE1 = new Simple_Asgn_Stmt_For_Int(i, new Simple_Asgn_For_Int(), new Int_Literal(1));
		Var_Dec_For_Int IntiE1 = new Var_Dec_For_Int(iE1);

		stmts.add(IntiE1);
		// int i = 1;

		// i++;
		Int_Un_Expr_For_Int_Idnt ipp = new Int_Un_Expr_For_Int_Idnt(new Int_Idnt("i"), new Increment_For_Int_Idnt());	

		stmts.add(new Stmt_Expr_For_Int(ipp));
		// i++;

		// int j = i ++;
		Int_Idnt j = new Int_Idnt("j");

		Simple_Asgn_Stmt_For_Int jEipp = new Simple_Asgn_Stmt_For_Int(j, new Simple_Asgn_For_Int(), ipp);
		Var_Dec_For_Int IntjEipp = new Var_Dec_For_Int(jEipp);

		stmts.add(IntjEipp);
		// int j = i ++;

		// int k;
		Int_Idnt k = new Int_Idnt("k");

		Var_Dec_Stmt_For_Int_Idnt Intk = new Var_Dec_Stmt_For_Int_Idnt(j);

		stmts.add(Intk);
		// int k;

		Block_Stmt bs = new Block_Stmt(stmts);

		bs.run();

		System.out.println(bs);

		 */

		/*
		LinkedList<Stmt> forInit = new LinkedList<Stmt>();
		LinkedList<Stmt> forUpdate = new LinkedList<Stmt>();


		Int_Idnt i = new Int_Idnt("i");
		Int_Literal zero = new Int_Literal(0);
		Simple_Asgn_Stmt_For_Int iEzero = new Simple_Asgn_Stmt_For_Int(i,zero);
		Var_Dec_Asgn_For_Int IntiEzero = new Var_Dec_Asgn_For_Int(iEzero);
		forInit.add(IntiEzero);

		Int_Literal three = new Int_Literal(3);
		Bool_Bin_Expr_For_Int iLT3 = new Bool_Bin_Expr_For_Int(i, new Less_Than_For_Int(), three);

		Int_Un_Expr_For_Int_Idnt ipp = new Int_Un_Expr_For_Int_Idnt(i, new Increment_For_Int_Idnt());
		Stmt_Expr_For_Int ippS = new Stmt_Expr_For_Int(ipp);
		forUpdate.add(ippS);

		Level1 l = new Level1(5, 5, "G", new Coordinate(1,1), new Coordinate(1,4));

		For_Stmt forS = new For_Stmt(forInit, iLT3, forUpdate, new GoRightStmt(l));

		Block_Stmt bs= new Block_Stmt();
		bs.getStatements().add(forS);

		l.RunCode(bs);
		l.reset();
		l.RunCode(bs);
		 */
	}
}