package com.ellmck.horse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
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
	private Background background;
	private ScoreRecording scoreRecording;
	private static Bitmap basketballImage;

	public GameView(Context context)
	{
		super(context);
		init(context);

	}

	public GameView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context);
	}

	public GameView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context)
	{

		getHolder().addCallback(this);
		thread = new MainThread(getHolder(), this);
		setFocusable(true);
		setZOrderOnTop(false);
		basketballImage = BitmapFactory.decodeResource(context.getResources(),	R.drawable.basketball);
		background = new Background();
		ball = new BallSprite(basketballImage);
		ballPhysics = new BallPhysics();
		net = new NetSprite(ball.getWidth() * 2);
		aimSprite = new AimSprite();
		scoreRecording = new ScoreRecording(context);

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{

	}


	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
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
		if(scoreRecording.isBallInMotion())
		{
			return false;
		}

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
				scoreRecording.getCurrentPlayer().setStartingX(x);
				scoreRecording.getCurrentPlayer().setStartingY(y);
				if (!scoreRecording.getOtherPlayer().isScored())
				{
					ball.setX(x);
					ball.setY(y);
					aimSprite.setStartX(x);
					aimSprite.setStartY(y);
				}
				else
				{

				}
				scoreRecording.setScored(false);
				scoreRecording.setBallTouchedFloor(0);
				ball.setMoving(false);
				break;
			case MotionEvent.ACTION_MOVE:
				ball.setX(x);
				ball.setY(y);
				aimSprite.setStopX(x);
				aimSprite.setStopY(y);
				aimSprite.setLinePaint(aimSprite.getLineColor());
				break;
			case MotionEvent.ACTION_UP:
				double xDiff = -(x - aimSprite.getStartX())*0.2;
				double yDiff = -(y - aimSprite.getStartY())*0.2;
				ball.setxVelocity((int)xDiff);
				ball.setyVelocity((int)yDiff);
				aimSprite.setLinePaint(0);

				if (xDiff < -10 || xDiff > 10 || yDiff < -10 || yDiff > 10 )
				{
					ball.setMoving(true);
					scoreRecording.setPlayerHasChanged(false);
					scoreRecording.setBallInMotion(true);
				}
				break;

			default:

				break;
		}
		return true;
	}


	public void update()
	{
		ballPhysics.ballAnimation(ball, net, background, scoreRecording);
	}

	@Override
	public void draw (Canvas canvas)
	{
		if (canvas == null)
		{
			return;
		}
		super.draw(canvas);
		background.draw(canvas);
		ball.draw(canvas);
		net.draw(canvas);
		aimSprite.draw(canvas);
		scoreRecording.draw(canvas);

	}
}
