package com.example.finalprojectapp.scenario.archetype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;
import com.example.finalprojectapp.graphic_utils.archetype.inventory.InventorySpriteSheet;

import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.scenario.Scenario;
import com.example.finalprojectapp.scenario.configuration.Configuration;
import com.example.finalprojectapp.utilities.Android_Utils;
import com.example.finalprojectapp.utilities.Logic_Utils;

public abstract class InventoryScenarioArchetype extends Scenario {

	// Scenario specific definitions.
	public static final String INDEX_OUT_OF_BOUND_EXCEPTION_TEXT = "Exception : The index is out of range.";

	public static enum InventoryItemsEnum{
		Sword,
		Helmet,
		Shild	
	}

	// Scenario dynamic parts.
	private List<Pair<InventoryItemsEnum, Integer>> inventory;

	/**
	 * Randomizes a inventory set.
	 * @param size - Number of items in the inventory
	 * @return Logical representation of the inventory.
	 */
	public List<Pair<InventoryItemsEnum, Integer>> randomizeInventoryItems(int size){

		// Create a new logical nventory.
		List<Pair<InventoryItemsEnum, Integer>> randomInventory = new ArrayList<Pair<InventoryItemsEnum,Integer>>();

		List<Integer> usedAmounts = new ArrayList<Integer>();

		for (int i = 0; i < size; i++) {

			// Randomize an item.
			int randomNumItem = Logic_Utils.randInt(1 , InventoryItemsEnum.values().length);

			// Randomize an amount.
			int randomNumAmount = Logic_Utils.randInt(1 , 99);

			// remove duplications in the amount.
			while (usedAmounts.contains(randomNumAmount))
				randomNumAmount = Logic_Utils.randInt(1 , 99);


			// Add the item to the inventory.
			usedAmounts.add(randomNumAmount);

			switch (randomNumItem) {
			case 1:
				randomInventory.add(i,
						new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(InventoryItemsEnum.Sword, randomNumAmount));
				break;
			case 2:
				randomInventory.add(i,
						new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(InventoryItemsEnum.Helmet, randomNumAmount));
				break;
			case 3:
				randomInventory.add(i,
						new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(InventoryItemsEnum.Shild, randomNumAmount));
				break;

			default:
				break;
			}	
		}

		return randomInventory;
	}
	
	/**
	 * Sort the given inventory.
	 * @param inventory
	 * @return List of items.
	 */
	public List<Pair<InventoryItemsEnum,Integer>> sortInventory(List<Pair<InventoryItemsEnum,Integer>> inventory){
		
		List<Pair<InventoryItemsEnum,Integer>> sortedInventory = new ArrayList<Pair<InventoryItemsEnum,Integer>>();
		
		for (Pair<InventoryItemsEnum,Integer> item : inventory)
			sortedInventory.add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(item.first, item.second));
		
		for (int i = 0; i < sortedInventory.size(); i++) {
			
			int swapIndex = i;
			Integer minAmount = sortedInventory.get(i).second;
			
			for (int j = i + 1; j < sortedInventory.size(); j++) {
				
				if(sortedInventory.get(j).second < minAmount){
					minAmount = sortedInventory.get(j).second;
					swapIndex = j;
				}
			}
			
			Collections.swap(sortedInventory, i, swapIndex);	
		}
		
		return sortedInventory;
	}

	@Override
	public void reset() {
		MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();

		inventory = new ArrayList<Pair<InventoryItemsEnum,Integer>>();

		for (Pair<InventoryItemsEnum,Integer> item : currentConfig.startInventory)
			inventory.add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(item.first, item.second));

	}

	@Override
	public GameSnapshot takeSnapshot() {
		return new MyGameSnapshot(inventory);
	}

	@Override
	public MySurfaceView generateGameView(Context context, int fps,boolean isAnimation) {
		return new SurfaceView_Inventory(context, fps, isAnimation);
	}

	@Override
	public boolean checkWin(Configuration config, GameSnapshot gameSnapshot) {

		MyConfiguration currentConfig = (MyConfiguration) config;
		MyGameSnapshot mgs = (MyGameSnapshot)gameSnapshot;

		for (int i = 0; i < mgs.inventory.size() ; i++) {

			if(mgs.inventory.get(i).first != currentConfig.winInventory.get(i).first || 
					mgs.inventory.get(i).second != currentConfig.winInventory.get(i).second)

				return false;
		}

		return true;
	}

	/******************** Nested classes ********************/

	/********** exception class **********/
	@SuppressWarnings("serial")
	protected class IndexOutOfBoundsException extends MyException{

		public IndexOutOfBoundsException() {
			super(INDEX_OUT_OF_BOUND_EXCEPTION_TEXT);
		}

	}
	/********** exception class **********/


	/********** config class **********/
	/**
	 * Inventory scenario specific Configuration object.
	 * @author daniel portnoy
	 *
	 */
	protected class MyConfiguration extends Configuration{

		private List<Pair<InventoryItemsEnum, Integer>> startInventory;

		private List<Pair<InventoryItemsEnum, Integer>> winInventory;

		private int length;


		public MyConfiguration(List<Pair<InventoryItemsEnum, Integer>> startInventory,
				List<Pair<InventoryItemsEnum, Integer>> winInventory,
				int length) {

			// Copy items to the startInventory.
			this.startInventory = new ArrayList<Pair<InventoryItemsEnum,Integer>>();

			for (Pair<InventoryItemsEnum, Integer> item : startInventory)
				this.startInventory.add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(item.first, item.second));

			// Copy items to the winInventory.
			this.winInventory = new ArrayList<Pair<InventoryItemsEnum,Integer>>();

			for (Pair<InventoryItemsEnum, Integer> item : winInventory)
				this.winInventory.add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(item.first, item.second));	

			this.length = length;
		}

		public List<Pair<InventoryItemsEnum, Integer>> getStartInventory() {
			return startInventory;
		}

		public List<Pair<InventoryItemsEnum, Integer>> getWinInventory() {
			return winInventory;
		}

		public int getLength() {
			return length;
		}	

		@Override
		public Configuration copy() {
			return new MyConfiguration(startInventory, winInventory, length);
		}

	}
	/********** config class **********/


	/********** snapshot class **********/
	/**
	 * Inventory scenario specific GameSnapshot object.
	 * @author daniel portnoy
	 *
	 */
	protected class MyGameSnapshot extends GameSnapshot{

		private List<Pair<InventoryItemsEnum, Integer>> inventory;

		public MyGameSnapshot(MyGameSnapshot other) {

			// Copy items to the inventory.
			this.inventory = new ArrayList<Pair<InventoryItemsEnum,Integer>>();

			for (Pair<InventoryItemsEnum, Integer> item : other.inventory)
				this.inventory.add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(item.first, item.second));
		}

		public MyGameSnapshot(List<Pair<InventoryItemsEnum, Integer>> inventory) {

			// Copy items to the inventory.
			this.inventory = new ArrayList<Pair<InventoryItemsEnum,Integer>>();

			for (Pair<InventoryItemsEnum, Integer> item : inventory)
				this.inventory.add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(item.first, item.second));
		}

		@Override
		public boolean equals(GameSnapshot other) {
			MyGameSnapshot other_mgs = (MyGameSnapshot)other;

			for (int i = 0; i < other_mgs.inventory.size() ; i++) {

				if(other_mgs.inventory.get(i).first != inventory.get(i).first || 
						other_mgs.inventory.get(i).second != inventory.get(i).second)
					return false;
			}

			return true;
		}

		@Override
		public GameSnapshot copy(GameSnapshot other) {
			return new MyGameSnapshot((MyGameSnapshot) other);
		}
	}
	/********** snapshot class **********/


	/********** special nodes class's **********/
	/**
	 * Inventory scenario specific Node object.
	 * @author daniel portnoy
	 *
	 */
	protected class swapNode extends Node{

		private Node left;
		private Node right;

		public swapNode() {
			setType(Type.Statement);
		}

		@Override
		public List<CodeWritingPart> getCodeWritingParts() {

			List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

			res.add(new CodeWritingPart(false, false, "swap( ", null, this));

			if(left == null)
				res.add(new CodeWritingPart(false, false, null, new LeftSetter(this), this));
			else
				res.addAll(left.getCodeWritingParts());

			res.add(new CodeWritingPart(false, false, " , ", null, this));

			if(right == null)
				res.add(new CodeWritingPart(false, false, null, new RightSetter(this), this));
			else			
				res.addAll(right.getCodeWritingParts());

			res.add(new CodeWritingPart(false, false, ");", null, this));

			return res;
		}

		@Override
		public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {

			isHighlighted = target.equals(this) || isHighlighted;
			List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

			res.add(new CodeRunningPart(false, false,isHighlighted, "swap( "));

			res.addAll(left.getCodeRunningParts(target, isHighlighted));

			res.add(new CodeRunningPart(false, false,isHighlighted, " , "));

			res.addAll(right.getCodeRunningParts(target, isHighlighted));

			res.add(new CodeRunningPart(false, false,isHighlighted, ");"));


			return res;
		}

		@Override
		public ReturnObject run() throws MyException {

			LevelManager.getInstance().takeSnapshot(this);

			MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();

			try {
				int leftIndex = left.run().getIntValue();
				int rightIndex = right.run().getIntValue();

				if(leftIndex < 0 || rightIndex > currentConfig.length)
					throw new IndexOutOfBoundsException();
				else
					Collections.swap(inventory, leftIndex, rightIndex);				
			} 
			catch (MyException e) {
				throw e;
			}

			return new ReturnObject();	
		}

		@Override
		public Node getFirstNode() {
			if(left != null)
				return left;
			else if(right != null)
				return right;
			else
				return null;
		}

		@Override
		public Set<String> getDeclaredIdentifiers() {

			HashSet<String> res = new HashSet<String>();
			if(left!=null)
				res.addAll(left.getDeclaredIdentifiers());
			if(right!=null)
				res.addAll(right.getDeclaredIdentifiers());
			return res;
		}

		@Override
		public Set<String> getUsedIdentifiers() {

			HashSet<String> res = new HashSet<String>();
			if(left!=null)
				res.addAll(left.getUsedIdentifiers());
			if(right!=null)
				res.addAll(right.getUsedIdentifiers());
			return res;
		}

		@Override
		public boolean DeleteChildNode(Node childNode) {

			Set<String> used = new HashSet<String>();

			if(childNode.equals(left) && right != null)
				used.addAll(right.getUsedIdentifiers());
			else if(childNode.equals(right))
				used = new HashSet<String>();

			Set<String> intersection = new HashSet<String>(used);
			intersection.retainAll(childNode.getDeclaredIdentifiers());

			if(intersection.isEmpty()){
				if(childNode.equals(left)){
					removeFromScope(left);
					left = null;
				}
				else if(childNode.equals(right)){
					removeFromScope(right);
					right = null;
				}
				return true;
			}
			else
				return false;
		}

		@Override
		public List<Node> getChildNodes() {

			List<Node> res = new ArrayList<Node>();

			res.add(left);
			res.add(right);

			return res;
		}

		@Override
		public boolean addChild(Node child, int order) {

			if(order > getChildNodes().size() - 1)
				return false;
			else{
				if(order == 0)
					left = child;
				else if(order == 1)
					right = child;
			}
			return true;	
		}

		class LeftSetter extends Setter{

			final static int ORDER = 0;

			public LeftSetter(Node parent) {
				super(true, parent, ORDER);	
			}

			@Override
			public void setChildNode(Node toSet) {
				left = toSet;
				toSet.setOrder(ORDER);
				toSet.setParent(getParent());
			}

			@Override
			public List<Type> possibleTypes() {
				List<Type> possibilities = new ArrayList<Type>();
				possibilities.add(Type.Int);

				return possibilities;
			}
		}

		class RightSetter extends Setter{

			final static int ORDER = 1;

			public RightSetter(Node parent) {
				super(true, parent, ORDER);

			}

			@Override
			public void setChildNode(Node toSet) {
				right = toSet;
				toSet.setOrder(ORDER);
				toSet.setParent(getParent());
			}

			@Override
			public List<Type> possibleTypes() {
				List<Type> possibilities = new ArrayList<Type>();
				possibilities.add(Type.Int);

				return possibilities;
			}
		}
	}
	/********** special nodes class's **********/


	/********** special option class's **********/
	/**
	 * Inventory scenario specific Option object.
	 * @author daniel portnoy
	 *
	 */
	protected class SwapOption extends Option{

		public SwapOption() {}

		@Override
		public boolean isType(Type type) {
			return type == Type.Statement;
		}

		@Override
		public void setButton(Context context, Button optionButton, final Setter SETTER) {

			optionButton.setText("swap");

			optionButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					SETTER.setChildNode(new swapNode());
					SETTER.getParent().reOrderScope(SETTER.getOrder(), 1);
					refresh();
				}
			});
		}
	}
	/********** special option class's **********/


	/********** SurfaceView class **********/
	/**
	 * Inventory scenario specific MySurfaceView object.
	 * @author daniel portnoy
	 *
	 */
	protected class SurfaceView_Inventory extends MySurfaceView {

		// Specific definitions.
		private static final int SWAP_CLIMB_NUM_OF_FRAMES = 10;
		private static final int SWAP_MOVE_NUM_OF_FRAMES = 20;
		private static final double SWING_HEIGHT_AMPLITUDE_RELATIVE = 0.30;
		private static final double ITEM_AMOUNT_RELATIVE_X_POS = 0.65, ITEM_AMOUNT_RELATIVE_Y_POS = 0.65;
		private static final double ITEM_AMOUNT_RELATIVE_TEXT_SIZE = 0.6;
		private static final double ITEM_SCALE_HEIGHT_PERCENT = 0.5, ITEM_SCALE_WIDTH_PERCENT = 0.5;

		private boolean isStillAnimating = false;

		// Inventory related
		private InventorySpriteSheet inventorySprite;
		private int inventoryXpos, inventoryYpos;

		private Bitmap inventoryBitmap;

		// Inventory items related
		private List<Item> items;
		private Item SwapingItemLeft,SwapingItemRight;
		private List<Pair<InventoryItemsEnum, Integer>> currentInventory;

		private int currentSwapTopBottomFrame, currentSwapLeftRightFrame; 

		// positions and measurements
		private int screenWidth, screenHeight;
		private int inventoryScaleWidth;
		private int inventoryScaleHeight;
		private int slotWidth;
		private int slotHeight;
		private int itemScaleWidth;
		private int itemScaleHeight;
		private int shiftItemX;
		private int shiftItemY;
		private int swingHeightAmplitude;
		private int swapHeightDistance;
		private int swapHeightInterval;
		private int swapWidthDistance;
		private int swapWidthInterval;

		public SurfaceView_Inventory(Context context, int fps, boolean isAnimating) {
			super(context, fps, isAnimating);

			MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();

			// Initialize inventorySprite.
			inventorySprite = new InventorySpriteSheet(
					BitmapFactory.decodeResource(
							getResources(),
							R.drawable.inventory_items_set,
							Android_Utils.BitmapFactoryOptionsInScaled()
							),
							BitmapFactory.decodeResource(
									getResources(),
									R.drawable.inventory_slot,
									Android_Utils.BitmapFactoryOptionsInScaled()
									),
									currentConfig.length);

			inventoryBitmap = inventorySprite.getInventoryBitmap();

			reset();

		}

		@Override
		public void draw(Canvas canvas) {
			super.draw(canvas);

			render();

			// fill background.
			canvas.drawColor(Color.WHITE);

			// draw inventory.
			canvas.drawBitmap(inventoryBitmap, inventoryXpos, inventoryYpos, null);

			// draw inventory items.
			for (int i = 0; i < items.size(); i++)
				canvas.drawBitmap(items.get(i).bitmap , items.get(i).itemXpos, items.get(i).itemYpos, null);

		}

		@Override
		public void reset() {

			isStillAnimating = false;

			MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();

			currentInventory = new ArrayList<Pair<InventoryItemsEnum,Integer>>();

			for (Pair<InventoryItemsEnum, Integer> item : currentConfig.startInventory)
				currentInventory.add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(item.first, item.second));

			items = new ArrayList<Item>();

			for (int i = 0; i < currentInventory.size() ; i++)
				items.add(i, new Item());

			updateItemsBitmaps();
			updateItems();

		}

		@Override
		public void preCalculation() {

			/***** Calculate screen and board positions and measurements *****/
			screenWidth = getWidth();
			screenHeight = getHeight();

			inventoryScaleWidth = inventoryBitmap.getWidth();
			inventoryScaleHeight = inventoryBitmap.getHeight();

			float imageRatio = (float) inventoryScaleWidth / inventoryScaleHeight;
			float screenRatio = (float) screenWidth / screenHeight;

			if(imageRatio >= screenRatio){
				// Image is wider than the display (ratio)
				inventoryScaleWidth = screenWidth;
				inventoryScaleHeight = (int) (inventoryScaleWidth / imageRatio);
			}
			else{
				// Image is taller than the display (ratio)
				inventoryScaleHeight = screenHeight;
				inventoryScaleWidth = (int) (inventoryScaleHeight * imageRatio);
			}

			int XShift = (screenWidth - inventoryScaleWidth)/2;
			int YShift = (screenHeight - inventoryScaleHeight)/2;	

			inventoryXpos = XShift;
			inventoryYpos = YShift;
			/***** Calculate screen and board positions and measurements *****/

			MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();

			slotHeight = inventoryScaleHeight;
			slotWidth = inventoryScaleWidth  / currentConfig.length;

			/***** Calculate item positions and measurements *****/
			itemScaleWidth = (int) (slotWidth * ITEM_SCALE_WIDTH_PERCENT);
			itemScaleHeight = (int) (slotHeight * ITEM_SCALE_HEIGHT_PERCENT);

			shiftItemX = (slotWidth - itemScaleWidth)/2;
			shiftItemY = (slotHeight - itemScaleHeight)/2;
			/***** Calculate item positions and measurements *****/

			/***** Calculate Climb positions and measurements *****/
			swapHeightDistance = slotHeight;
			//swapBottomHeightDistance = screenHeight - (inventoryYpos + slotHeight - shiftItemY);

			swapHeightInterval = swapHeightDistance / SWAP_CLIMB_NUM_OF_FRAMES;
			//swapBottomHeightInterval = swapBottomHeightDistance / SWAP_CLIMB_NUM_OF_FRAMES;

			swingHeightAmplitude = (int) (( (slotHeight - itemScaleHeight) / 2 ) * SWING_HEIGHT_AMPLITUDE_RELATIVE);
			/***** Calculate Climb positions and measurements *****/

			// randomize starting Y offsets
			if(isAnimating){
				for (int i = 0; i < items.size(); i++){
					items.get(i).itemInitialYPosOffset = Logic_Utils.randInt(- swingHeightAmplitude, swingHeightAmplitude);
					items.get(i).itemYPosOffset = items.get(i).itemInitialYPosOffset;
				}
			}
		}

		@Override
		public void updateAnimated() {

			if(currentSwapLeftRightFrame == SWAP_MOVE_NUM_OF_FRAMES && currentSwapTopBottomFrame == 0){

				currentSwapLeftRightFrame = 0;

				SwapingItemLeft = SwapingItemRight = null;

				for (Item item : items)
					item.reset();

				updateItemsBitmaps();		

				isStillAnimating = false;
			}

			updateItemsOffsets(swingHeightAmplitude);
			updateItems();

		}

		@Override
		public void updateNonAnimated() {

			isStillAnimating = false;

			updateItemsBitmaps();
			updateItems();
		}

		@Override
		public void render() {
			/* scaling bitmaps */	
			inventoryBitmap = Bitmap.createScaledBitmap(inventoryBitmap, inventoryScaleWidth, inventoryScaleHeight, true);

			for (int i = 0; i < items.size(); i++)
				items.get(i).bitmap = Bitmap.createScaledBitmap(items.get(i).bitmap , itemScaleWidth, itemScaleHeight, true);		
			/* scaling bitmaps */
		}

		@Override
		public void loadSnapshot(GameSnapshot gameSnapshot) {

			boolean foundSwaped = false;
			int ItemLeftPos = 0, ItemRightPos = 0;

			SwapingItemLeft = SwapingItemRight = null;
			isStillAnimating = false;

			MyGameSnapshot mgs = (MyGameSnapshot)gameSnapshot;

			for (int i = 0; i < mgs.inventory.size(); i++) {

				if(!mgs.inventory.get(i).second.equals(currentInventory.get(i).second)){

					isStillAnimating = true;

					if(foundSwaped){
						SwapingItemRight = items.get(i);
						ItemLeftPos = i;
					}
					else{
						SwapingItemLeft = items.get(i);
						ItemRightPos = i;
						foundSwaped = true;
					}
				}
			}

			if(SwapingItemLeft != null && SwapingItemRight != null){

				/***** Calculate Move positions and measurements *****/
				swapWidthDistance = slotWidth*(ItemLeftPos - ItemRightPos);
				swapWidthInterval = swapWidthDistance / SWAP_MOVE_NUM_OF_FRAMES;
				/***** Calculate Move positions and measurements *****/
			}

			currentInventory = new ArrayList<Pair<InventoryItemsEnum,Integer>>();

			for (Pair<InventoryItemsEnum,Integer> item : mgs.inventory)				
				currentInventory.add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(item.first, item.second));

		}

		@Override
		public boolean isStillAnimating() {
			return isStillAnimating;
		}

		/**
		 * Update the bitmaps of the inventory items XY positions.
		 */
		private void updateItems(){

			for (int i = 0; i < items.size() ; i++) {
				items.get(i).itemXpos = inventoryXpos + i*slotWidth + shiftItemX + items.get(i).itemXPosOffset;
				items.get(i).itemYpos = inventoryYpos + shiftItemY + items.get(i).itemYPosOffset;
			}
		}

		/**
		 * Update the bitmaps of the inventory items Offsets.
		 * @param amplitude
		 */
		public void updateItemsOffsets(int amplitude){

			for (int i = 0; i < items.size() ; i++) {

				if(amplitude != 0){

					if(!items.get(i).equals(SwapingItemLeft) && !items.get(i).equals(SwapingItemRight)){

						if(Math.abs(items.get(i).itemYPosOffset) > amplitude)
							items.get(i).isMovingDown = !items.get(i).isMovingDown;

						if(items.get(i).isMovingDown)
							items.get(i).itemYPosOffset++;
						else
							items.get(i).itemYPosOffset--;
					}
				}
			}

			// Don't add offsets to swapping items
			if(SwapingItemLeft != null && SwapingItemRight != null){

				SwapingItemLeft.isMovingRight = true;
				SwapingItemRight.isMovingRight = false;

				if(currentSwapLeftRightFrame < SWAP_MOVE_NUM_OF_FRAMES){

					if(currentSwapTopBottomFrame < SWAP_CLIMB_NUM_OF_FRAMES){

						SwapingItemLeft.isMovingDown = true;
						SwapingItemRight.isMovingDown = false;

						currentSwapTopBottomFrame ++;
					}
					else
						currentSwapLeftRightFrame++;
				}
				else{
					if(currentSwapTopBottomFrame > 0)
						currentSwapTopBottomFrame--;
				}

				int upDownXAmount = swapHeightInterval * currentSwapTopBottomFrame;
				int leftRightXAmount = swapWidthInterval * currentSwapLeftRightFrame;


				if(SwapingItemLeft.isMovingDown)
					SwapingItemLeft.itemYPosOffset = upDownXAmount;
				else
					SwapingItemLeft.itemYPosOffset = -upDownXAmount;

				if(SwapingItemLeft.isMovingRight)
					SwapingItemLeft.itemXPosOffset = leftRightXAmount;
				else
					SwapingItemLeft.itemXPosOffset = -leftRightXAmount;


				if(SwapingItemRight.isMovingDown)
					SwapingItemRight.itemYPosOffset = upDownXAmount;
				else
					SwapingItemRight.itemYPosOffset = -upDownXAmount;

				if(SwapingItemRight.isMovingRight)
					SwapingItemRight.itemXPosOffset = leftRightXAmount;
				else
					SwapingItemRight.itemXPosOffset = -leftRightXAmount;
			}
		}

		/**
		 * Update the bitmaps of the inventory items.
		 */
		public void updateItemsBitmaps(){

			List<Bitmap> itemsBitmaps = inventorySprite.getInventoryItemsBitmaps(
					currentInventory, 
					ITEM_AMOUNT_RELATIVE_X_POS,
					ITEM_AMOUNT_RELATIVE_Y_POS,
					ITEM_AMOUNT_RELATIVE_TEXT_SIZE);

			for (int i = 0; i < items.size(); i++) {
				items.get(i).bitmap = itemsBitmaps.get(i);
			}
		}

		/**
		 * Logical Representation of an inventory item bitmap.
		 * @author daniel portnoy
		 *
		 */
		protected class Item{

			private Bitmap bitmap;

			private int itemXpos, itemYpos;

			private boolean isMovingDown, isMovingRight;

			private int itemXPosOffset, itemYPosOffset;

			private int itemInitialXPosOffset, itemInitialYPosOffset;

			public Item() {
				reset();

				this.itemInitialXPosOffset = this.itemInitialYPosOffset = 0;
			}

			/**
			 * reset to default values.
			 */
			private void reset() {
				this.bitmap = null;

				this.itemXpos = this.itemYpos = 0;

				this.itemXPosOffset = itemInitialXPosOffset;
				this.itemYPosOffset = itemInitialYPosOffset;

				this.isMovingDown = this.isMovingRight = false;
			}
		}

	}
	/********** SurfaceView class **********/

	/******************** Nested classes ********************/

}
