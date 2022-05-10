package com.thiendeptraivc.engine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameContainer implements Runnable 
{
	//tao mot thread cho may thuc hien
	private Thread thread;
	private Window window;
	private Renderer renderer;
	private Input input;
	
	private boolean running = false;
	private final double UPDATE_CAP = 1.0/60.0;
	private int width =320, height = 240;
	private float scale = 1f;
	private String titile = "vccc";
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
	public String getTitile() {
		return titile;
	}
	public void setTitile(String titile) {
		this.titile = titile;
	}
	public Window getWindow() {
		return window;
	}
	public void setWindow(Window window) {
		this.window = window;
	}
	public GameContainer()
	{
		
	}
	//chay Thread
	public void start()
	{
		//window phai de o dau
		window = new Window(this);
		input = new Input(this);
		
		renderer = new Renderer(this);
		thread = new Thread(this);
		thread.run();
	}
	//chay chuong trinh
	public void run()
	{
		running = true;
		boolean render = false;
		//deo biet nua tutorial no go the
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessTime = 0;
		
		double frameTime = 0;
		int frames = 0;
		int fps = 0;
		
		while(running)
		{
			render = false;
			
			firstTime = System.nanoTime() /1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessTime += passedTime;
			frameTime += passedTime;
			
			while(unprocessTime >= UPDATE_CAP) 
			{
				unprocessTime -= UPDATE_CAP;
				render = true;
		///		System.out.println("update");
				//TODO: update game
				//if(input.isButton(MouseEvent.BUTTON1))
				//{
					System.out.println("x: " + input.getMouseX() + "y: "+ input.getMouseY());
				//}
				
					
				if(frameTime >= 1.0)
				{
					frameTime = 0;
					fps = frames;
					frames = 0;
					System.out.println("FPS: "+fps);
				}
			}
			
			if(render)
			{
				renderer.clear();
				//TODO: render game
				window.update();
				frames++;
			}
			else 
			{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		dispose();
	}
	
	public void stop()
	{
		
	}
	
	private void dispose()
	{
		
	}
	
	public static void main( String args[]) 
	{
		GameContainer gc = new GameContainer();
		gc.start();
	}

}
