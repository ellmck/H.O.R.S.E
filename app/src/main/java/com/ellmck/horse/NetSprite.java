package com.ellmck.horse;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class NetSprite
{
	private static final int RIM_RADIUS = 10;
	private static final float SCREEN_HEIGHT = (float)Resources.getSystem().getDisplayMetrics().heightPixels;
	private static final float SCREEN_WIDTH = (float)Resources.getSystem().getDisplayMetrics().widthPixels;
	public static final double RIGHT_RIM_X = SCREEN_WIDTH - SCREEN_WIDTH * 0.1;
	public static final double RIGHT_RIM_Y = SCREEN_HEIGHT - SCREEN_HEIGHT * 0.6;
	private static final double BB_HEIGHT_MULTIPLIER = 1.5;
	private static final double BB_WIDTH_MULTIPLIER = 0.333;
	private static final int RIM_COLOR = Color.rgb(170, 27, 44);
	private static final Paint rimPaint = new Paint();
	private static double backBoardHeight;
	private static double bbWidth;
	private static Rect backboard = new Rect();
	private static Rect test = new Rect();
	private static double holeInNet;
	private static int leftRimX;

	protected NetSprite(double holeInNet)
	{
		this.holeInNet = holeInNet;
		this.backBoardHeight = holeInNet * BB_HEIGHT_MULTIPLIER;
		bbWidth = holeInNet * BB_WIDTH_MULTIPLIER;

		backboard.set((int)(RIGHT_RIM_X + bbWidth),
					  (int)(RIGHT_RIM_Y + bbWidth),
					  (int)(RIGHT_RIM_X + bbWidth + 20),
					  (int)(RIGHT_RIM_Y - backBoardHeight));

		setLeftRimX((int)RIGHT_RIM_X  - (int)holeInNet);
		rimPaint.setColor(RIM_COLOR);
	}


	protected void drawRightRim(Canvas canvas)
	{
		//blue
		Paint p = new Paint();
		p.setColor(Color.rgb(66, 134, 244));
		canvas.drawCircle((int) RIGHT_RIM_X, (int) RIGHT_RIM_Y, RIM_RADIUS, p);
	}

	protected void drawLeftRim(Canvas canvas)
	{
		//yellow
		Paint p = new Paint();
		p.setColor(Color.rgb(214, 244, 65));
		canvas.drawCircle(getLeftRimX(), (int)RIGHT_RIM_Y, RIM_RADIUS, p);
	}

	protected void drawBackBoard(Canvas canvas)
	{

		canvas.drawRect(backboard, rimPaint);
		canvas.drawRect(test, rimPaint);

	}

	protected void draw(Canvas canvas)
	{
		drawLeftRim(canvas);
		drawRightRim(canvas);
		drawBackBoard(canvas);
	}

	public Rect getBackboard()
	{
		return backboard;
	}

	public static double getHoleInNet()
	{
		return holeInNet;
	}

	public static void setHoleInNet(double holeInNet)
	{
		NetSprite.holeInNet = holeInNet;
	}

	public static int getLeftRimX()
	{
		return leftRimX;
	}

	public static void setLeftRimX(int leftRimX)
	{
		NetSprite.leftRimX = leftRimX;
	}
}
