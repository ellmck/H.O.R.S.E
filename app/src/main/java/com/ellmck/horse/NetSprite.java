package com.ellmck.horse;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class NetSprite
{
	private static final int RIM_RADIUS = 10;
	private double HOLE_IN_NET;
	private static final double SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
	private static final double SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
	private static final double RIGHT_RIM_X = SCREEN_WIDTH - SCREEN_WIDTH * 0.4;
	private static final double RIGHT_RIM_Y = SCREEN_HEIGHT - SCREEN_HEIGHT * 0.1;
	private static final double BB_HEIGHT_MULTIPLIER = 1.5;
	private static final double BB_WIDTH = 10;
	private static final int RIM_COLOR = Color.rgb(170, 27, 44);
	private static final Paint rimPaint = new Paint();
	private static double backBoardHeight;
	private static Rect backboard = new Rect();

	protected NetSprite(double holeInNet)
	{
		this.HOLE_IN_NET = holeInNet;
		this.backBoardHeight = holeInNet * BB_HEIGHT_MULTIPLIER;
		backboard.set((int)RIGHT_RIM_X,
					  (int)(RIGHT_RIM_Y),
					  (int)(RIGHT_RIM_X + backBoardHeight),
					  (int)(RIGHT_RIM_Y + BB_WIDTH));

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
		canvas.drawCircle((int) RIGHT_RIM_X, (int) RIGHT_RIM_Y - (float)HOLE_IN_NET, RIM_RADIUS, p);
	}

	protected void drawBackBoard(Canvas canvas)
	{
		canvas.drawRect(backboard, rimPaint);
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
}
