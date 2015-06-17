package com.example.finalprojectapp.codewriting.managment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingLine;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.codewriting.option.concrete.identifier.BoolIdentifierOption;
import com.example.finalprojectapp.codewriting.option.concrete.identifier.IntIdentifierOption;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.Scope;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;

public class CodeWritingLogicUnit {

	private List<CodeWritingLine> codeWritingLines;
	private List<CodeWritingPart> codeWritingParts;
	private List<Option> currentOptions;

	private boolean editMode = false;

	private Node currentMakerNode = null;

	public CodeWritingLogicUnit() {
		codeWritingLines = new ArrayList<CodeWritingLine>();
		currentOptions = new ArrayList<Option>();
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public Node getCurrentMakerNode() {
		return currentMakerNode;
	}

	public void setCurrentMakerNode(Node currentMakerNode) {
		this.currentMakerNode = currentMakerNode;
	}

	public List<CodeWritingLine> getWritingCodeLines() {
		return codeWritingLines;
	}

	public List<Option> getCurrentOptions() {
		return currentOptions;
	}

	public void updateCodeWritingLines() {

		codeWritingLines.clear();

		CodeWritingLine temp = new CodeWritingLine();

		codeWritingParts = LevelManager.getInstance().getRootNode().getCodeWritingParts();

		markEditable();

		for (CodeWritingPart codePart : codeWritingParts) {
			if(!codePart.isNewline())
				temp.getCodeWritingParts().add(codePart);
			else{
				codeWritingLines.add(temp);
				temp = new CodeWritingLine();
			}
		}	

		codeWritingLines.add(temp);
	}

	public void markEditable(){

		if(currentMakerNode != null){

			for (CodeWritingPart part : codeWritingParts) {
				if(part.getMakerNode().equals(currentMakerNode))
					part.setEditable(true);
				else{
					Node parent = part.getMakerNode().getParent();

					while(parent!=null){
						if(parent.equals(currentMakerNode)){
							part.setEditable(true);
							break;
						}
						else
							parent = parent.getParent();
					}
				}
			}

		}
	}

	public void loadOptionsForSetter(Setter setter) {

		currentOptions.clear();

		loadStaticOptions(setter);
		loadIdentifierOptions(setter);
	}

	private void loadStaticOptions(Setter setter) {

		for (Option option : LevelManager.getInstance().getScenario().getAvailableOptions()) {
			for (Type type : setter.possibleTypes()) {
				if(option.isType(type))
					currentOptions.add(option);
			}		
		}
	}

	private void loadIdentifierOptions(Setter setter) {

		List<String> identifiers;

		if(setter.possibleTypes().contains(Type.Bool)){
			identifiers = Scope.getPrevBooleanIdentifiersRecursive(setter.getParent(), setter.getOrder());
			for (String id : identifiers)
				currentOptions.add(new BoolIdentifierOption(id));
		}
		if(setter.possibleTypes().contains(Type.Int)){
			identifiers = Scope.getPrevIntegerIdentifiersRecursive(setter.getParent(), setter.getOrder());
			for (String id : identifiers)
				currentOptions.add(new IntIdentifierOption(id));
		}
	}

	/*public boolean isCodeLinesValid() {
		for (CodeWritingPart codePart : LevelManager.getInstance().getRootNode().getCodeWritingParts())
			if(codePart.getSetter()!=null && codePart.getSetter().isMandatory())
				return false;

		return true;
	}*/

	// TODO
	public void loadEditingOptions(){
		currentOptions.add(new Option() {

			@Override
			public void setButton(Context context, Button optionButton, Setter setter) {
				optionButton.setText("expand");

				optionButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						if(currentMakerNode.getParent() != null){
							LevelManager.getInstance().setEditable(currentMakerNode.getParent());
							LevelManager.getInstance().refrashWritingScreen();
						}
					}
				});
			}

			@Override
			public boolean isType(Type type) {return false;}
		});

		currentOptions.add(new Option() {

			@Override
			public void setButton(Context context, Button optionButton, Setter setter) {
				optionButton.setText("contract");

				optionButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if(currentMakerNode.getFirstNode() != null){
							LevelManager.getInstance().setEditable(currentMakerNode.getFirstNode());
							LevelManager.getInstance().refrashWritingScreen();
						}
					}
				});
			}

			@Override
			public boolean isType(Type type) {return false;}
		});

		currentOptions.add(new Option() {

			@Override
			public void setButton(final Context CONTEXT, Button optionButton, Setter setter) {
				optionButton.setText("delete");

				optionButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if(currentMakerNode.getParent() != null){
							
							if(!currentMakerNode.isErasable()){
								LevelManager.getInstance().setEditMode(false);
								LevelManager.getInstance().setEditable(null);
								LevelManager.getInstance().refrashWritingScreen();
							}
							else if(currentMakerNode.getParent().DeleteChildNode(currentMakerNode)){							
								LevelManager.getInstance().setEditMode(false);
								LevelManager.getInstance().setEditable(null);
								LevelManager.getInstance().refrashWritingScreen();
							}
							else
								Toast.makeText(CONTEXT, "Failed - invalid removal attempt.", Toast.LENGTH_LONG).show(); // TODO	
						}
						else{
							LevelManager.getInstance().clearCode();

							LevelManager.getInstance().setEditMode(false);
							LevelManager.getInstance().setEditable(null);
							LevelManager.getInstance().refrashWritingScreen();
						}
					}
				});
			}

			@Override
			public boolean isType(Type type) {return false;}

		});

		currentOptions.add(new Option() {

			@Override
			public void setButton(Context context, Button optionButton, Setter setter) {

				optionButton.setText("cancel");

				optionButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						LevelManager.getInstance().setEditMode(false);
						LevelManager.getInstance().setEditable(null);
						LevelManager.getInstance().refrashWritingScreen();
					}
				});
			}

			@Override
			public boolean isType(Type type) {return false;}

		});

	}
}
