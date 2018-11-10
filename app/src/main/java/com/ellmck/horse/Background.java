package com.ellmck.horse;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Background {
	private static final float SCREEN_HEIGHT = (float)Resources.getSystem().getDisplayMetrics().heightPixels;
	private static final float SCREEN_WIDTH = (float)Resources.getSystem().getDisplayMetrics().widthPixels;
	private static final int FLOOR_THICKNESS = 50;
	private static final int BACKGROUND_COLOR = Color.rgb(135,206,235);
	private static final int FLOOR_COLOR = Color.rgb(205,133,63);
	private static final Paint backgroundPaint = new Paint();
	private static Rect backgroundRect = new Rect();
	private static final Paint floorPaint = new Paint();


	public Background()
	{
		floorPaint.setColor(FLOOR_COLOR);
		floorPaint.setStrokeWidth(FLOOR_THICKNESS);
		backgroundPaint.setColor(BACKGROUND_COLOR);
		backgroundRect.set(0,0, (int)SCREEN_WIDTH, (int)SCREEN_HEIGHT);
	}

	protected void draw(Canvas canvas)
	{
		canvas.drawRect(backgroundRect, backgroundPaint);
		canvas.drawLine(0, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT, floorPaint);
	}

	public static int getFloorThickness()
	{
		return FLOOR_THICKNESS;
	}
}
