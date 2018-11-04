package com.ellmck.horse;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
	private MainThread thread;
	private BallSprite ballSprite;
	private BallPhysics ballPhysics;

	public GameView(Context context)
	{
		super(context);

		getHolder().addCallback(this);

		thread = new MainThread(getHolder(), this);
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		ballSprite = new BallSprite(BitmapFactory.decodeResource(getResources(), R.drawable.basketball));
		ballPhysics = new BallPhysics(ballSprite);
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		boolean retry = true;
		while (retry)
		{
			try
			{
				thread.setRunning(false);
				thread.join();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			retry = false;
		}
		thread.setRunning(false);

	}

	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		// MotionEvent reports input details from the touch screen
		// and other input controls. In this case, you are only
		// interested in events where the touch position changed.
		if (ballSprite == null)
		{
			return false;
		}
		ballSprite.setX((int)e.getX());
		ballSprite.setY((int)e.getY());

		return true;

	}

	public void update()
	{
		ballPhysics.ballAnimation();
	}

	@Override
	public void draw (Canvas canvas)
	{
		super.draw(canvas);
		if (canvas != null)
		{
			ballSprite.draw(canvas);
		}
	}


}
