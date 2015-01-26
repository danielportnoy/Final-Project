package com.example.finalproject;

import java.util.LinkedList;

import final_2.logics.game.level1.Level1;
import final_2.logics.game.level1.infrastructure.stmt.GoRightStmt;
import final_2.logics.game.scenario.Scenario;
import final_2.logics.grammar.expression.binary.concrete.Bool_Bin_Expr_For_Int;
import final_2.logics.grammar.expression.identifier.Int_Idnt;
import final_2.logics.grammar.expression.unary.concrete.Int_Un_Expr_For_Int_Idnt;
import final_2.logics.grammar.literal.Int_Literal;
import final_2.logics.grammar.operator.binary.concrete.Less_Than_For_Int;
import final_2.logics.grammar.operator.unary.idnt.concrete.Increment_For_Int_Idnt;
import final_2.logics.grammar.statement.Block_Stmt;
import final_2.logics.grammar.statement.For_Stmt;
import final_2.logics.grammar.statement.Stmt;
import final_2.logics.grammar.statement.asgn.concrete.Simple_Asgn_Stmt_For_Int;
import final_2.logics.grammar.statement.statement_expression.Stmt_Expr_For_Int;
import final_2.logics.grammar.statement.varDec.asgn.concrete.Var_Dec_Asgn_For_Int;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CodeFragment extends Fragment {

	private TextView codeTextView;

	private Block_Stmt codeBlock;
	
	private Level1 level1; 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View myFragmentView = inflater.inflate(R.layout.code_fragment_layout,container,false);

		codeBlock = new Block_Stmt();

		// -------------------
		
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

		For_Stmt forS = new For_Stmt(forInit, iLT3, forUpdate, new GoRightStmt(level1));
		
		// -------------------
		
		codeBlock.getStatements().add(forS);

		codeTextView = (TextView) myFragmentView.findViewById(R.id.textView_Code);		
		codeTextView.setText(codeBlock.toString());

		return myFragmentView;
	}

	public Block_Stmt getCode() {
		return codeBlock;
	}

	public void setLevel(Scenario level) {
		level1 = (Level1) level;	// TODO
	}
}
