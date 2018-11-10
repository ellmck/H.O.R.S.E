package com.ellmck.horse;

import android.content.res.Resources;

public class BallPhysics {

	private static final double ACCELERATION = 2;
	// What proportion of the velocity is retained on a bounce?  if 1.0, no energy
	// is lost, and the ball will bounce infinitely.
	private static final double COEFFICIENT_OF_RESTITUTION = 0.7;
	// While the ball is rolling along the bottom of the screen, its x velocity
	// is multiplied by this amount each frame.
	private static final double COEFFICIENT_OF_FRICTION = 0.9;

	private BallSprite ball;

	public BallPhysics(BallSprite ball)
	{
		this.ball = ball;
	}

	public void ballAnimation(NetSprite net, Background background)
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
		int minY = screenHeight - radius  - background.getFloorThickness();
		int minX = 0 + radius;
		double xRolling = COEFFICIENT_OF_FRICTION * ball.getxVelocity();

		// Ball is out of bounds in x dimension
		if (ballX > maxX)
		{
			ball.setX(maxX);
			ballXCollision();
		}
		else if (ballX < minX)
		{
			ball.setX(minX);
			ballXCollision();
		}

		// Ball hits the floor
		if (ballY > minY)
		{
			ball.setY(minY);
			ballYCollision();
		}
		else if (ballY < maxY )
		{
			ball.setY(maxY);
			ballYCollision();
		}

		//left and right rim physics
		checkRimCollision(ballX, ballY, radius, net.getLeftRimX(), (int)net.RIGHT_RIM_Y, 20);
		checkRimCollision(ballX, ballY, radius, (int)net.RIGHT_RIM_X, (int)net.RIGHT_RIM_Y, 20);

		//backboard physics
		//left and right of object physics
		if 	(((ballX + radius > net.getBackboard().left) &&
			(ballX - radius < net.getBackboard().right) &&
			(ballY - radius < net.getBackboard().top) &&
			(ballY + radius >net.getBackboard().bottom)) &&
			(isMovingTowardsObject(net.getBackboard().left, net.getBackboard().bottom) ||
					isMovingTowardsObject(net.getBackboard().right, net.getBackboard().top) ||
					isMovingTowardsObject((int)net.getBackboard().exactCenterX(), (int)net.getBackboard().exactCenterY())))
		{
			ballXCollision();
		}

		if 	(((ballX + radius > net.getBackboardConnector().left) &&
				(ballX - radius < net.getBackboardConnector().right) &&
				(ballY - radius < net.getBackboardConnector().top) &&
				(ballY + radius >net.getBackboardConnector().bottom)) &&
				(isMovingTowardsObject(net.getBackboardConnector().left, net.getBackboardConnector().bottom) ||
						isMovingTowardsObject(net.getBackboardConnector().right, net.getBackboardConnector().top) ||
						isMovingTowardsObject((int)net.getBackboardConnector().exactCenterX(), (int)net.getBackboardConnector().exactCenterY())))
		{
			ballXCollision();
		}



		//TODO
//		// bottom and top of object physics
//		if (((ballX + radius > net.getBackboard().left) &&
//			(ballX - radius < net.getBackboard().right) &&
//			(ballY - radius < net.getBackboard().top) &&
//			(ballY + radius > net.getBackboard().bottom) &&
//			(isMovingTowardsObject(net.getBackboard().left, net.getBackboard().bottom) ||
//					isMovingTowardsObject((int)net.getBackboard().exactCenterX(), (int)net.getBackboard().exactCenterY()) ||
//					isMovingTowardsObject(net.getBackboard().right, net.getBackboard().top))))
//
//		{
//			ballYCollision();
//		}

		// ball is rolling along the bottom
		if (ball.getY() == minY)
		{
			ball.setxVelocity((int)xRolling);
		}
	}

	public boolean isMovingTowardsObject(int objectX, int objectY)
	{
		int xDist = ball.getX() - objectX;
		int yDist = ball.getY() - objectY;
		double xVelocity = 0 - ball.getxVelocity();
		double yVelocity = 0 - ball.getyVelocity();

		return 0 < xDist*xVelocity + yDist*yVelocity;
	}

	public void checkRimCollision(int ballX, int ballY, int ballRadius, int rimX, int rimY, int rimRadius)
	{
		if ((ballX + ballRadius > rimX - rimRadius) &&
			(ballX - ballRadius < rimX  + rimRadius) &&
			(ballY + ballRadius > rimY) &&
			(ballY - ballRadius < rimY)&&
			isMovingTowardsObject(rimX, rimY))
		{
			ballXCollision();
		}

		if ((ballX + ballRadius > rimX) &&
			(ballX - ballRadius < rimX)&&
			(ballY - ballRadius < rimY + rimRadius) &&
			(ballY + ballRadius > rimY - rimRadius) &&
			isMovingTowardsObject(rimX, rimY))
		{
			ballYCollision();
		}
	}

	public void ballXCollision()
	{
		double newVelocity = -COEFFICIENT_OF_RESTITUTION * ball.getxVelocity();
		ball.setxVelocity((int)newVelocity);
	}

	public void ballYCollision()
	{
		double newVelocity = -COEFFICIENT_OF_RESTITUTION * ball.getyVelocity();
		ball.setyVelocity((int)newVelocity);
	}
}
