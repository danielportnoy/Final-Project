package com.example.finalprojectapp.graphic_utils.archetype.inventory;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Pair;

import com.example.finalprojectapp.graphic_utils.SpriteSheet;
import com.example.finalprojectapp.scenario.archetype.InventoryScenarioArchetype.InventoryItemsEnum;
import com.example.finalprojectapp.utilities.Logic_Utils;

public class InventorySpriteSheet extends SpriteSheet {

	private final int ITEM_WIDTH = 48, ITEM_HEIGHT = 48;

	private final int STROKE_WIDTH = 2;

	private  int slotWidth, slotHeight;

	private int boardWidth, boardHeight;

	private int length;

	private Bitmap sword = getBitmapByCoords(96, 336, ITEM_WIDTH, ITEM_HEIGHT);
	private Bitmap helmet = getBitmapByCoords(96, 432, ITEM_WIDTH, ITEM_HEIGHT);
	private Bitmap shild = getBitmapByCoords(384, 528, ITEM_WIDTH, ITEM_HEIGHT);

	private Bitmap[] inventorySlots;

	public InventorySpriteSheet(Bitmap sourceBitmap, Bitmap inventorySlot, int length) {
		super(sourceBitmap);

		slotWidth = inventorySlot.getWidth();
		slotHeight = inventorySlot.getHeight();

		this.length = length;

		boardWidth = slotWidth*length;
		boardHeight = slotHeight;		

		inventorySlots = new Bitmap[length];

		for (int i = 0; i < inventorySlots.length ; i++)
			inventorySlots[i] = inventorySlot;
	}

	public Bitmap getInventoryBitmap() {

		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
		Bitmap inventory = Bitmap.createBitmap(boardWidth, boardHeight, conf);

		Canvas canvas = new Canvas(inventory);
		canvas.setDensity(DisplayMetrics.DENSITY_DEFAULT);

		for (int i = 0; i < length ; i++)
			canvas.drawBitmap(inventorySlots[i], i*slotWidth, 0, null);

		return inventory;
	}

	public List<Bitmap> getInventoryItemsBitmaps(List<Pair<InventoryItemsEnum,
			Integer>> inventory, double amountWidthRelative, double amountHeightRelative,
			double amountTextRelative) {

		List<Bitmap> inventoryItems = new ArrayList<Bitmap>();

		for (int i = 0; i < inventory.size() ; i++){

			switch (inventory.get(i).first) {
			case Sword:
				inventoryItems.add(i, Bitmap.createBitmap(sword));
				break;
			case Helmet:
				inventoryItems.add(i, Bitmap.createBitmap(helmet));
				break;
			case Shild:
				inventoryItems.add(i, Bitmap.createBitmap(shild));
				break;

			default:
				break;
			}

			addAmountTag(inventoryItems.get(i),
					inventory.get(i).second.toString(),
					0xfff5b800,
					Color.BLACK,
					0xff002bad,
					amountWidthRelative,
					amountHeightRelative,
					amountTextRelative
					);		
		}

		return inventoryItems;
	}

	private void addAmountTag(Bitmap source, String amount, int TagColor, int TagStrokeColor, int TextColor ,double RelativWidthPos
			, double RelativHeightPos, double amountTextRelative){

		int itemWidth = source.getWidth();
		int itemHeight = source.getHeight();

		int amountSquareWidth = (int)(itemWidth * (1 - RelativWidthPos));
		int amountSquareHeight = (int)(itemHeight * (1 - RelativHeightPos));

		int amountSquareXPos = itemWidth - amountSquareWidth;
		int amountSquareYPos = itemHeight - amountSquareHeight;

		Paint amountSquarePaint = new Paint();
		amountSquarePaint.setColor(TagColor);
		amountSquarePaint.setStyle(Paint.Style.FILL);

		Paint amountSquareStrokePaint = new Paint();
		amountSquareStrokePaint.setColor(TagStrokeColor);
		amountSquareStrokePaint.setStyle(Paint.Style.STROKE);
		amountSquareStrokePaint.setStrokeWidth(STROKE_WIDTH/2);

		Paint amountTextPaint = new Paint();
		amountTextPaint.setColor(TextColor);

		int amountTextWidth = (int) (amountSquareWidth * amountTextRelative);
		int amountTextHeight = (int) (amountSquareHeight * amountTextRelative);

		amountTextPaint.setTextSize(Logic_Utils.determineMaxTextSize(amount, amountTextWidth , amountTextHeight));

		/* handle amount square */
		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
		Bitmap itemAmount = Bitmap.createBitmap(amountSquareWidth, amountSquareWidth, conf);

		Canvas canvas = new Canvas(itemAmount);
		canvas.setDensity(DisplayMetrics.DENSITY_DEFAULT);

		Rect amountTextBounds = new Rect();

		canvas.drawRect(STROKE_WIDTH/2, STROKE_WIDTH/2, amountSquareWidth - STROKE_WIDTH/2 - 1, amountSquareHeight - STROKE_WIDTH/2 - 1, amountSquarePaint);
		/* handle amount square */

		/* handle amount square stroke */
		//canvas.drawCircle(amountSquareWidth/2, amountSquareHeight/2, amountSquareWidth/2, amountSquareStrokePaint);
		canvas.drawRect(STROKE_WIDTH/2, STROKE_WIDTH/2, amountSquareWidth - STROKE_WIDTH/2 - 1, amountSquareHeight - STROKE_WIDTH/2 - 1, amountSquareStrokePaint);
		/* handle amount square stroke*/

		/* handle amount text */
		amountTextPaint.getTextBounds(amount, 0, amount.length(), amountTextBounds);

		int amountTextXPos = (amountSquareWidth / 2) - (amountTextBounds.width() / 2);
		int amountTextYPos = (amountSquareHeight / 2)  + (amountTextBounds.height() / 2);

		canvas.drawText(
				amount,
				amountTextXPos - 1,
				amountTextYPos,
				amountTextPaint
				);
		/* handle amount text */

		canvas = new Canvas(source);
		canvas.setDensity(DisplayMetrics.DENSITY_DEFAULT);

		canvas.drawBitmap(itemAmount, amountSquareXPos, amountSquareYPos , null);			
	}

}
