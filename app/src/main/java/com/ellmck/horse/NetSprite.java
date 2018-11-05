package com.ellmck.horse;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class NetSprite
{
	private static final int RIM_RADIUS = 10;
	private double HOLE_IN_NET;
	private static final double SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
	private static final double SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
	private static final double LEFT_RIM_X = SCREEN_WIDTH - SCREEN_WIDTH * 0.4;
	private static final double LEFT_RIM_Y = SCREEN_HEIGHT - SCREEN_HEIGHT * 0.1;
	private static Paint rimPaint = new Paint();


	protected NetSprite(double holeInNet)
	{
		System.out.println(SCREEN_WIDTH);
		System.out.println(SCREEN_HEIGHT);
		this.HOLE_IN_NET = holeInNet;
		rimPaint.setColor(Color.rgb(170, 27, 44));
	}


	protected void drawLeftRim(Canvas canvas)
	{
		//blue
		Paint p = new Paint();
		p.setColor(Color.rgb(66, 134, 244));
		canvas.drawCircle((int)LEFT_RIM_X, (int) LEFT_RIM_Y, RIM_RADIUS, p);
	}

	protected void drawRightRim(Canvas canvas)
	{
		//yellow
		Paint p = new Paint();
		p.setColor(Color.rgb(214, 244, 65));
		canvas.drawCircle((int)LEFT_RIM_X, (int)LEFT_RIM_Y - (float)HOLE_IN_NET, RIM_RADIUS, p);
	}

	protected void drawBackBoard(Canvas canvas)
	{
		double BACKBOARD_HEIGHT = LEFT_RIM_X + HOLE_IN_NET * 1.5;
		canvas.drawCircle((int)BACKBOARD_HEIGHT, (int)LEFT_RIM_Y, RIM_RADIUS, rimPaint);
	}

	protected void draw(Canvas canvas)
	{
		drawLeftRim(canvas);
		drawRightRim(canvas);
		drawBackBoard(canvas);
	}
}
