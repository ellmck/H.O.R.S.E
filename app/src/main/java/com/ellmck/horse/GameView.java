package com.ellmck.horse;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
	private MainThread thread;
	private BallSprite ball;
	private NetSprite net;
	private BallPhysics ballPhysics;
	private AimSprite aimSprite;

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
//		ball = new BallSprite(BitmapFactory.decodeResource(getResources(), R.drawable.basketball));
		ball = new BallSprite();
		ballPhysics = new BallPhysics(ball);
		net = new NetSprite(ball.getWidth() * 1.885);
		aimSprite = new AimSprite();
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
		int action = e.getAction();
		int x = (int) e.getX();
		int y = (int) e.getY();
		switch(action)
		{
			case MotionEvent.ACTION_DOWN:
				//todo for reset button
//				if (x >= xOfYourBitmap && x < (xOfYourBitmap + yourBitmap.getWidth())
//						&& y >= yOfYourBitmap && y < (yOfYourBitmap + yourBitmap.getHeight())) {
//					//tada, if this is true, you've started your click inside your bitmap
//				}
				ball.setMoving(false);
				ball.setX(x);
				ball.setY(y);
				aimSprite.setStartX(x);
				aimSprite.setStartY(y);
				break;
			case MotionEvent.ACTION_MOVE:
				ball.setX(x);
				ball.setY(y);
				aimSprite.setStopX(x);
				aimSprite.setStopY(y);
				aimSprite.setLineColor(Color.WHITE);
				break;
			case MotionEvent.ACTION_UP:
				double xDiff = -(x - aimSprite.getStartX())*0.2;
				double yDiff = -(y - aimSprite.getStartY())*0.2;
				ball.setxVelocity((int)xDiff);
				ball.setyVelocity((int)yDiff);
				aimSprite.setLineColor(0);

				if (xDiff < -10 || xDiff > 10 || yDiff < -10 || yDiff > 10 )
				{
					ball.setMoving(true);
				}

				break;

			default:

				break;
		}
		return true;
	}


	public void update()
	{
		ballPhysics.ballAnimation(net);
	}

	@Override
	public void draw (Canvas canvas)
	{
		if (canvas == null)
		{
			return;
		}
		super.draw(canvas);
		ball.draw(canvas);
		net.draw(canvas);
		aimSprite.draw(canvas);
	}
}
