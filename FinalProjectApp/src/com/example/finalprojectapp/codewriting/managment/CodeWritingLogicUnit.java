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

/**
 * Manages all code writing logical data and functions.
 * @author daniel portnoy
 *
 */
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

	/**
	 * Rearranges and preparing the codeWritingLines list to be displayed.
	 */
	public void updateCodeWritingLines() {

		codeWritingLines.clear();

		CodeWritingLine temp = new CodeWritingLine();

		codeWritingParts = LevelManager.getInstance().getRootNode().getCodeWritingParts();

		markEditable();

		for (CodeWritingPart codePart : codeWritingParts) {
			if(!codePart.isNewline())
				temp.getCodeWritingParts().add(codePart);
			else{

				// create new code line after newLine.
				codeWritingLines.add(temp);
				temp = new CodeWritingLine();
			}
		}	

		codeWritingLines.add(temp);
	}

	/**
	 * Marks the current editable chosen Node on the screen.
	 */
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

	/**
	 * Loads all of the possible options that their type is the same as the setters type. 
	 * @param setter
	 */
	public void loadOptionsForSetter(Setter setter) {

		// clear old options.
		currentOptions.clear();

		// load pre-defines static options.
		loadStaticOptions(setter);
		// load dynamic identifiers.
		loadIdentifierOptions(setter);
	}

	/**
	 * Loads the pre-defines static options of the scenario.
	 * @param setter
	 */
	private void loadStaticOptions(Setter setter) {

		for (Option option : LevelManager.getInstance().getScenario().getAvailableOptions()) {
			for (Type type : setter.possibleTypes()) {
				if(option.isType(type))
					currentOptions.add(option);
			}		
		}
	}

	/**
	 * Loads the current dynamic possible identifiers.
	 * @param setter
	 */
	private void loadIdentifierOptions(Setter setter) {

		List<String> identifiers;

		// Load boolean identifiers.
		if(setter.possibleTypes().contains(Type.Bool)){
			identifiers = Scope.getPrevBooleanIdentifiersRecursive(setter.getParent(), setter.getOrder());
			for (String id : identifiers)
				currentOptions.add(new BoolIdentifierOption(id));
		}

		// Load integer identifiers.
		if(setter.possibleTypes().contains(Type.Int)){
			identifiers = Scope.getPrevIntegerIdentifiersRecursive(setter.getParent(), setter.getOrder());
			for (String id : identifiers)
				currentOptions.add(new IntIdentifierOption(id));
		}
	}

	/**
	 * Loads the options for editing (delete)
	 */
	public void loadEditingOptions(){

		// Add the expand button.
		currentOptions.add(new Option() {

			@Override
			public void setButton(Context context, Button optionButton, Setter setter) {
				optionButton.setText("expand");

				optionButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						// Set the makerNode to be the parent Node.
						if(currentMakerNode.getParent() != null){
							LevelManager.getInstance().setEditable(currentMakerNode.getParent());

							// Update screen.
							LevelManager.getInstance().refrashWritingScreen();
						}
					}
				});
			}

			@Override
			public boolean isType(Type type) {return false;}
		});

		// Add the contract button.
		currentOptions.add(new Option() {

			@Override
			public void setButton(Context context, Button optionButton, Setter setter) {
				optionButton.setText("contract");

				optionButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						// Set the makerNode to be the first child Node.
						if(currentMakerNode.getFirstNode() != null){
							LevelManager.getInstance().setEditable(currentMakerNode.getFirstNode());

							// Update screen.
							LevelManager.getInstance().refrashWritingScreen();
						}
					}
				});
			}

			@Override
			public boolean isType(Type type) {return false;}
		});

		// Add the delete button.
		currentOptions.add(new Option() {

			@Override
			public void setButton(final Context CONTEXT, Button optionButton, Setter setter) {
				optionButton.setText("delete");

				optionButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						// Delete a certain Node.
						if(currentMakerNode.getParent() != null){

							// Delete only if the Node is erasable.
							if(!currentMakerNode.isErasable()){
								LevelManager.getInstance().setEditMode(false);
								LevelManager.getInstance().setEditable(null);

								// Update screen.
								LevelManager.getInstance().refrashWritingScreen();
							}
							else if(currentMakerNode.getParent().DeleteChildNode(currentMakerNode)){	

								// After a successful delete, return to normal writing mode.
								LevelManager.getInstance().setEditMode(false);
								LevelManager.getInstance().setEditable(null);

								// Update screen.
								LevelManager.getInstance().refrashWritingScreen();
							}
							else
								Toast.makeText(CONTEXT, "Failed - invalid removal attempt.", Toast.LENGTH_LONG).show();
						}
						else{

							// Delete entire code.
							LevelManager.getInstance().clearCode();

							LevelManager.getInstance().setEditMode(false);
							LevelManager.getInstance().setEditable(null);

							// Update screen.
							LevelManager.getInstance().refrashWritingScreen();
						}
					}
				});
			}

			@Override
			public boolean isType(Type type) {return false;}

		});

		// Add the cencel button.
		currentOptions.add(new Option() {

			@Override
			public void setButton(Context context, Button optionButton, Setter setter) {

				optionButton.setText("cancel");

				optionButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						// Return to normal writing mode.
						LevelManager.getInstance().setEditMode(false);
						LevelManager.getInstance().setEditable(null);

						// Update screen.
						LevelManager.getInstance().refrashWritingScreen();
					}
				});
			}

			@Override
			public boolean isType(Type type) {return false;}

		});

	}
}
