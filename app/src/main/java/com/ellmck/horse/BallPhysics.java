package com.ellmck.horse;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;

public class BallPhysics {

	private static final double ACCELERATION = 2;
	// What proportion of the velocity is retained on a bounce?  if 1.0, no energy
	// is lost, and the ball will bounce infinitely.
	private static final double COEFFICIENT_OF_RESTITUTION = 0.7;
	// While the ball is rolling along the bottom of the screen, its x velocity
	// is multiplied by this amount each frame.
	private static final double COEFFICIENT_OF_FRICTION = 0.9;

	private boolean playerChanged = false;

	public void ballAnimation(BallSprite ball, NetSprite net, Background background, ScoreRecording scoreRecording)
	{
		if (ball == null || !ball.isMoving())
		{
			return;
		}

		//landscape
		int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
		int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

		//this line determines direction of gravity
		ball.setyVelocity((int)ACCELERATION + ball.getyVelocity());
		ball.update();

		int ballX = ball.getX();
		int ballY = ball.getY();
		int radius = ball.getRadius();
		int maxY = 0 + radius - screenHeight * 2;
		int maxX = screenWidth + 400 - radius;
		int minY = screenHeight - background.getFloorThickness();
		int minX = 0 + radius;
		double xRolling = COEFFICIENT_OF_FRICTION * ball.getxVelocity();

		// Ball is out of bounds in x dimension
		if (ballX > maxX)
		{
			ball.setX(maxX);
			ballXCollision(ball);
		}
		else if (ballX < minX)
		{
			ball.setX(minX);
			ballXCollision(ball);
		}

		// Ball hits the floor
		if (ballY > minY)
		{
			ball.setY(minY);
			ballYCollision(ball);
			scoreRecording.setBallTouchedFloor(scoreRecording.getBallTouchedFloor() + 1);
		}
		else if (ballY < maxY )
		{
			ball.setY(maxY);
			ballYCollision(ball);
		}

		//left and right rim physics
		checkRimCollision(ball, net.getLeftRimX(), (int)net.RIGHT_RIM_Y, 20);
		checkRimCollision(ball, (int)net.RIGHT_RIM_X, (int)net.RIGHT_RIM_Y, 20);

		//backboard physics
		checkObjectCollision(ball, net.getBackboard());
		checkObjectCollision(ball, net.getBackboardConnector());


		//SCORED!
		if(ball.getyVelocity() > 0 &&
			ballX > net.getLeftRimX() &&
			ballX < net.RIGHT_RIM_X &&
			ballY - radius < net.RIGHT_RIM_Y &&
			ballY + radius > net.RIGHT_RIM_Y)
		{
			scoreRecording.setScored(true);
		}
		//reset game for next player
		if (scoreRecording.getBallTouchedFloor() > 2)
		{
			if(scoreRecording.isScored())
			{
				ball.setX(scoreRecording.getCurrentPlayer().getStartingX());
				ball.setY(scoreRecording.getCurrentPlayer().getStartingY());
				ball.setxVelocity(0);
				ball.setyVelocity(0);
			}

			if(!scoreRecording.isPlayerHasChanged())
			{
				scoreRecording.setBallInMotion(false);
				scoreRecording.changePlayer();
				scoreRecording.setPlayerHasChanged(true);
			}
		}

		// ball is rolling along the bottom
		if (ball.getY() == minY)
		{
			ball.setxVelocity((int)xRolling);
		}
	}

	public boolean isMovingTowardsObject(BallSprite ball, int objectX, int objectY)
	{
		int xDist = ball.getX() - objectX;
		int yDist = ball.getY() - objectY;
		double xVelocity = 0 - ball.getxVelocity();
		double yVelocity = 0 - ball.getyVelocity();

		return 0 < xDist*xVelocity + yDist*yVelocity;
	}

	public void checkObjectCollision(BallSprite ball, Rect object)
	{
		int ballRadius = ball.getRadius();
		int ballX = ball.getX();
		int ballY = ball.getY();

		//left and right of object physics
		if 	(((ballX + ballRadius > object.left) &&
				(ballX - ballRadius < object.right) &&
				(ballY - ballRadius < object.top) &&
				(ballY + ballRadius > object.bottom)) &&
				(isMovingTowardsObject(ball, object.left, object.bottom) ||
						isMovingTowardsObject(ball, object.right,object.top) ||
						isMovingTowardsObject(ball, (int)object.exactCenterX(), (int)object.exactCenterY())))
		{
			if (ball.getxVelocity() > 0)
			{
				ball.setX(object.left - ballRadius);
			}
			else
			{
				ball.setY(object.right + ballRadius);
			}
			ballXCollision(ball);
		}

		//TODO
//		if ((ballX + ballRadius > object.right) &&
//				(ballX - ballRadius < object.left)&&
//				(ballY - ballRadius < object.bottom) &&
//				(ballY + ballRadius > object.top) &&
//				isMovingTowardsObject(ball, object.left, object.bottom) ||
//				isMovingTowardsObject(ball, object.right,object.top) ||
//				isMovingTowardsObject(ball, (int)object.exactCenterX(), (int)object.exactCenterY()))
//		{
//			if (ball.getyVelocity() > 0)
//			{
//				ball.setY(object.top);
//			}
//			else
//			{
//				ball.setY(object.bottom);
//			}
//
//			ballYCollision(ball);
//		}
	}


	public void checkRimCollision(BallSprite ball, int rimX, int rimY, int rimRadius)
	{

		int ballRadius = ball.getRadius();
		int ballX = ball.getX();
		int ballY = ball.getY();

		if ((ballX + ballRadius > rimX - rimRadius) &&
			(ballX - ballRadius < rimX  + rimRadius) &&
			(ballY + ballRadius > rimY) &&
			(ballY - ballRadius < rimY)&&
			isMovingTowardsObject(ball, rimX, rimY))
		{
			if (ball.getxVelocity() > 0)
			{
				ball.setX(rimX - ballRadius);
			}
			else
			{
				ball.setY(rimY + ballRadius);
			}
			ballXCollision(ball);
		}

		if ((ballX + ballRadius > rimX) &&
				(ballX - ballRadius < rimX)&&
				(ballY - ballRadius < rimY + rimRadius) &&
				(ballY + ballRadius > rimY - rimRadius) &&
				isMovingTowardsObject(ball, rimX, rimY))
		{
			if (ball.getyVelocity() > 0)
			{
				ball.setY(rimY - ballRadius);
			}
			else
			{
				ball.setY(rimY + ballRadius);
			}

			ballYCollision(ball);
		}
	}

	public void ballXCollision(BallSprite ball)
	{
		double newVelocity = -COEFFICIENT_OF_RESTITUTION * ball.getxVelocity();
		ball.setxVelocity((int)newVelocity);
	}

	public void ballYCollision(BallSprite ball)
	{
		double newVelocity = -COEFFICIENT_OF_RESTITUTION * ball.getyVelocity();
		ball.setyVelocity((int)newVelocity);
	}
}
