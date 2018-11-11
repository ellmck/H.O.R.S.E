package com.ellmck.horse;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

public class ScoreRecording
{
	private static final float SCREEN_HEIGHT = (float)Resources.getSystem().getDisplayMetrics().heightPixels;
	private static final float SCREEN_WIDTH = (float)Resources.getSystem().getDisplayMetrics().widthPixels;
	private static final int CENTRE_FONT_SIZE = 400;
	private static final int PLAYER_FONT_SIZE = 100;
	Paint centreTextPaint = new Paint();
	Paint playerTextPaint = new Paint();
	private static String centreText;

	private static Player currentPlayer, player1, player2;
	private int ballTouchedFloor;
	private static boolean togglePlayer = false;
	private boolean playerHasChanged = false;

	public ScoreRecording(Context context)
	{
		player1 = new Player("Player 1");
		player2 = new Player("Player 2");
		currentPlayer = player1;
		Typeface typeface = context.getResources().getFont(R.font.kirby_no_kira_kizzu_brk_regular);
		centreTextPaint.setColor(context.getColor(R.color.colorCentreText));
		centreTextPaint.setTextSize(CENTRE_FONT_SIZE);
		centreTextPaint.setTypeface(typeface);
		centreTextPaint.setTextAlign(Paint.Align.CENTER);
		playerTextPaint.setColor(context.getColor(R.color.colorCentreText));
		playerTextPaint.setTextSize(PLAYER_FONT_SIZE);
		playerTextPaint.setTypeface(typeface);
		playerTextPaint.setTextAlign(Paint.Align.LEFT);
	}

	public boolean isScored()
	{
		return currentPlayer.isScored() ;
	}

	public void setScored(boolean scored)
	{
		currentPlayer.setScored(scored) ;
	}

	public String getCentreText()
	{
		return centreText;
	}

	public void setCentreText(String centreText)
	{
		this.centreText = centreText;
	}

	public int getBallTouchedFloor()
	{
		return ballTouchedFloor;
	}

	public void setBallTouchedFloor(int ballTouchedFloor)
	{
		this.ballTouchedFloor = ballTouchedFloor;
	}


	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Player getOtherPlayer() {
		Player other;
		changePlayer();
		other = currentPlayer;
		changePlayer();
		return other;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void changePlayer()
	{
		currentPlayer = togglePlayer ? player1 : player2;
		togglePlayer = !togglePlayer;
	}

	public boolean isPlayerHasChanged()
	{
		return playerHasChanged;
	}

	public void setPlayerHasChanged(boolean playerHasChanged)
	{
		this.playerHasChanged = playerHasChanged;
	}

	public void draw(Canvas canvas)
	{
		canvas.drawText(currentPlayer.getName(), 0 + 100, SCREEN_HEIGHT - 100, playerTextPaint);
		if (currentPlayer.isScored() && getBallTouchedFloor() == 0)
		{
			setCentreText("SCORED!");
			canvas.drawText(getCentreText(), (float)(SCREEN_WIDTH * 0.5), (float)(SCREEN_HEIGHT * 0.5), centreTextPaint);
		}
		else
		{
			setCentreText("MISS");
		}
	}
}
