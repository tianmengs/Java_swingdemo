package com.tianmeng;

import java.awt.Color;
import java.util.Random;

/*
 * ����
 */
public class Diamonds {
	private int x;        //����x
	private int y;        //����y
	private Color color;  //��ɫ
	private int type;     //����
	Random rm = new Random();
	private boolean status=true;//״̬
	private int width;
	
	public Diamonds() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//�����ʼ��
	public Diamonds(int width) {
		super();
		this.width = width;
		this.x = rm.nextInt(width-3);
		this.y = 0;
		//this.color = Color.GREEN;
		this.color = new Color(rm.nextInt(255),rm.nextInt(255),rm.nextInt(255));
		this.type = rm.nextInt(16);
	}

	//�����ƶ�
	public void left(Map map) {
		int[][]map_xy = map.getMap();
		if(x!=0) {
			switch(type){
			case 0:if(map_xy[x-1][y]==0) {x--;} break;
			case 1:if(map_xy[x-1][y]==0 && map_xy[x-1][y+1]==0 && map_xy[x-1][y+2]==0 && map_xy[x-1][y+3]==0) {x--;} break;
			
			case 2:if(map_xy[x][y]==0 && map_xy[x-1][y+1]==0) {x--;} break;
			case 3:if(map_xy[x-1][y]==0 && map_xy[x-1][y+1]==0 && map_xy[x-1][y+2]==0) {x--;} break;
			case 4:if(map_xy[x-1][y]==0 && map_xy[x][y+1]==0) {x--;} break;
			case 5:if(map_xy[x][y]==0 && map_xy[x-1][y+1]==0 && map_xy[x][y+2]==0) {x--;} break;
			
			case 6:if(map_xy[x][y]==0 && map_xy[x-1][y+1]==0) {x--;} break;
			case 7:if(map_xy[x-1][y]==0 && map_xy[x-1][y+1]==0) {x--;} break;
			case 8:if(map_xy[x-1][y]==0 && map_xy[x-1][y+1]==0) {x--;} break;
			case 9:if(map_xy[x-1][y]==0 && map_xy[x][y+1]==0) {x--;} break;
			
			case 10:if(map_xy[x-1][y]==0 && map_xy[x-1][y+1]==0) {x--;} break;
			
			case 11:if(map_xy[x+1][y]==0 && map_xy[x-1][y+1]==0) {x--;} break;
			case 12:if(map_xy[x-1][y]==0 && map_xy[x][y+1]==0 && map_xy[x][y+2]==0) {x--;} break;
			case 13:if(map_xy[x-1][y]==0 && map_xy[x-1][y+1]==0) {x--;} break;
			case 14:if(map_xy[x-1][y]==0 && map_xy[x-1][y+1]==0 && map_xy[x-1][y+2]==0) {x--;} break;
			
			case 15:if(map_xy[x-1][y]==0 && map_xy[x-1][y+1]==0) {x--;} break;
			case 16:if(map_xy[x-1][y]==0 && map_xy[x-1][y+1]==0 && map_xy[x-1][y+2]==0) {x--;} break;
			case 17:if(map_xy[x-1][y]==0 && map_xy[x+1][y+1]==0) {x--;} break;
			case 18:if(map_xy[x][y]==0 && map_xy[x][y+1]==0 && map_xy[x-1][y+2]==0) {x--;} break;
			}
		}
	}
	//�����ƶ�
	public void right(Map map) {
		int[][]map_xy = map.getMap();
		switch(type) {
		case 0: if(x<width-4 && map_xy[x+4][y]==0) {x++;}break;
		
		case 1: if(x<width-1 && map_xy[x+1][y]==0 && map_xy[x+1][y+1]==0 && map_xy[x+1][y+2]==0 && map_xy[x+1][y+3]==0) {x++;}break;
			
		case 2: if(x<width-3 && map_xy[x+2][y]==0 && map_xy[x+3][y+1]==0) {x++;}break;
		
		case 3: if(x<width-2 && map_xy[x+1][y]==0 && map_xy[x+2][y+1]==0 && map_xy[x+1][y+2]==0) {x++;}break;
			
		case 4: if(x<width-3 && map_xy[x+3][y]==0 && map_xy[x+2][y+1]==0) {x++;}break;
			
		case 5: if(x<width-2 && map_xy[x+2][y]==0 && map_xy[x+2][y+1]==0 && map_xy[x+2][y+2]==0) {x++;}break;
			
		case 6: if(x<width-2 && map_xy[x+2][y]==0 && map_xy[x+2][y+1]==0) {x++;}break;
			
		case 7: if(x<width-2 && map_xy[x+1][y]==0 && map_xy[x+2][y+1]==0) {x++;}break;
			
		case 8: if(x<width-2 && map_xy[x+2][y]==0 && map_xy[x+1][y+1]==0) {x++;}break;
			
		case 9: if(x<width-2 && map_xy[x+2][y]==0 && map_xy[x+2][y+1]==0) {x++;}break;
			
		case 10: if(x<width-2 && map_xy[x+2][y]==0 && map_xy[x+2][y+1]==0) {x++;}break;
		
			
		case 11: if(x<width-3 && map_xy[x+3][y]==0 && map_xy[x+3][y+1]==0) {x++;}break;
			
		case 12: if(x<width-2 && map_xy[x+2][y]==0 && map_xy[x+2][y+1]==0 && map_xy[x+2][y+2]==0) {x++;}break;
			
		case 13: if(x<width-3 && map_xy[x+3][y]==0 && map_xy[x+1][y+1]==0) {x++;}break;
		
		case 14: if(x<width-2 && map_xy[x+1][y]==0 && map_xy[x+1][y+1]==0 && map_xy[x+2][y+2]==0) {x++;}break;
		
			
		case 15: if(x<width-3 && map_xy[x+1][y]==0 && map_xy[x+3][y+1]==0) {x++;}break;
			
		case 16: if(x<width-2 && map_xy[x+2][y]==0 && map_xy[x+1][y+1]==0 && map_xy[x+1][y+2]==0) {x++;}break;
			
		case 17: if(x<width-3 && map_xy[x+3][y]==0 && map_xy[x+3][y+1]==0) {x++;}break;
		
		case 18: if(x<width-2 && map_xy[x+2][y]==0 && map_xy[x+2][y+1]==0 && map_xy[x+2][y+2]==0) {x++;}break;
		}
	}
	
	//�����ƶ�
	public void movement(Map map){
		int[][]map_xy = map.getMap();
		Color[][]color_xy = map.getColor();
		//�����ƶ�һ��
		switch(type) {
		case 0:
			if(map_xy[x][y+1]==0 && map_xy[x+1][y+1]==0 && map_xy[x+2][y+1]==0 && map_xy[x+3][y+1]==0){
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x+1][y]=1;
				map_xy[x+2][y]=1;
				map_xy[x+3][y]=1;
				color_xy[x][y]=color;
				color_xy[x+1][y]=color;
				color_xy[x+2][y]=color;
				color_xy[x+3][y]=color;
				status=false;
			}
			break;
		case 1:
			if(map_xy[x][y+4]==0){
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x][y+1]=1;
				map_xy[x][y+2]=1;
				map_xy[x][y+3]=1;
				color_xy[x][y]=color;
				color_xy[x][y+1]=color;
				color_xy[x][y+2]=color;
				color_xy[x][y+3]=color;
				status=false;
			}
			break;
		case 2:
			if(map_xy[x][y+2]==0 && map_xy[x+1][y+2]==0 && map_xy[x+2][y+2]==0) {
				y+=1;
			}else {
				map_xy[x+1][y]=1;
				map_xy[x][y+1]=1;
				map_xy[x+1][y+1]=1;
				map_xy[x+2][y+1]=1;
				color_xy[x+1][y]=color;
				color_xy[x][y+1]=color;
				color_xy[x+1][y+1]=color;
				color_xy[x+2][y+1]=color;
				status=false;
			}
			break;
		case 3:
			if(map_xy[x][y+3]==0 && map_xy[x+1][y+2]==0) {
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x][y+1]=1;
				map_xy[x+1][y+1]=1;
				map_xy[x][y+2]=1;
				color_xy[x][y]=color;
				color_xy[x][y+1]=color;
				color_xy[x+1][y+1]=color;
				color_xy[x][y+2]=color;
				status=false;
			}
			break;
		case 4:
			if(map_xy[x][y+1]==0 && map_xy[x+1][y+2]==0 && map_xy[x+2][y+1]==0) {
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x+1][y]=1;
				map_xy[x+2][y]=1;
				map_xy[x+1][y+1]=1;
				color_xy[x][y]=color;
				color_xy[x+1][y]=color;
				color_xy[x+2][y]=color;
				color_xy[x+1][y+1]=color;
				status=false;
			}
			break;
		case 5:
			if(map_xy[x][y+2]==0 && map_xy[x+1][y+3]==0) {
				y+=1;
			}else {
				map_xy[x+1][y]=1;
				map_xy[x][y+1]=1;
				map_xy[x+1][y+1]=1;
				map_xy[x+1][y+2]=1;
				color_xy[x+1][y]=color;
				color_xy[x][y+1]=color;
				color_xy[x+1][y+1]=color;
				color_xy[x+1][y+2]=color;
				status=false;
			}
			break;
		
			
		case 6:
			if(map_xy[x][y+2]==0 && map_xy[x+1][y+2]==0) {
				y+=1;
			}else {
				map_xy[x+1][y]=1;
				map_xy[x][y+1]=1;
				map_xy[x+1][y+1]=1;
				color_xy[x+1][y]=color;
				color_xy[x][y+1]=color;
				color_xy[x+1][y+1]=color;
				status=false;
			}
			break;
		case 7:
			if(map_xy[x][y+2]==0 && map_xy[x+1][y+2]==0) {
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x][y+1]=1;
				map_xy[x+1][y+1]=1;
				color_xy[x][y]=color;
				color_xy[x][y+1]=color;
				color_xy[x+1][y+1]=color;
				status=false;
			}
			break;
		case 8:
			if(map_xy[x][y+2]==0 && map_xy[x+1][y+1]==0) {
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x+1][y]=1;
				map_xy[x][y+1]=1;
				color_xy[x][y]=color;
				color_xy[x+1][y]=color;
				color_xy[x][y+1]=color;
				status=false;
			}
			break;
		case 9:
			if(map_xy[x][y+1]==0 && map_xy[x+1][y+2]==0) {
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x+1][y]=1;
				map_xy[x+1][y+1]=1;
				color_xy[x][y]=color;
				color_xy[x+1][y]=color;
				color_xy[x+1][y+1]=color;
				status=false;
			}
			break;
			
		case 10:
			if(map_xy[x][y+2]==0 && map_xy[x+1][y+2]==0) {
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x+1][y]=1;
				map_xy[x][y+1]=1;
				map_xy[x+1][y+1]=1;
				color_xy[x][y]=color;
				color_xy[x+1][y]=color;
				color_xy[x][y+1]=color;
				color_xy[x+1][y+1]=color;
				status=false;
			}
			break;
			
		case 11:
			if(map_xy[x][y+2]==0 && map_xy[x+1][y+2]==0 && map_xy[x+2][y+2]==0) {
				y+=1;
			}else {
				map_xy[x+2][y]=1;
				map_xy[x][y+1]=1;
				map_xy[x+1][y+1]=1;
				map_xy[x+2][y+1]=1;
				color_xy[x+2][y]=color;
				color_xy[x][y+1]=color;
				color_xy[x+1][y+1]=color;
				color_xy[x+2][y+1]=color;
				status=false;
			}
			break;
		case 12:
			if(map_xy[x][y+1]==0 && map_xy[x+1][y+3]==0) {
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x+1][y]=1;
				map_xy[x+1][y+1]=1;
				map_xy[x+1][y+2]=1;
				color_xy[x][y]=color;
				color_xy[x+1][y]=color;
				color_xy[x+1][y+1]=color;
				color_xy[x+1][y+2]=color;
				status=false;
			}
			break;
		case 13:
			if(map_xy[x][y+2]==0 && map_xy[x+1][y+1]==0 && map_xy[x+2][y+1]==0) {
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x+1][y]=1;
				map_xy[x+2][y]=1;
				map_xy[x][y+1]=1;
				color_xy[x][y]=color;
				color_xy[x+1][y]=color;
				color_xy[x+2][y]=color;
				color_xy[x][y+1]=color;
				status=false;
			}
			break;
		case 14:
			if(map_xy[x][y+3]==0 && map_xy[x+1][y+3]==0) {
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x][y+1]=1;
				map_xy[x][y+2]=1;
				map_xy[x+1][y+2]=1;
				color_xy[x][y]=color;
				color_xy[x][y+1]=color;
				color_xy[x][y+2]=color;
				color_xy[x+1][y+2]=color;
				status=false;
			}
			break;
		case 15:
			if(map_xy[x][y+2]==0 && map_xy[x+1][y+2]==0 && map_xy[x+2][y+2]==0) {
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x][y+1]=1;
				map_xy[x+1][y+1]=1;
				map_xy[x+2][y+1]=1;
				color_xy[x][y]=color;
				color_xy[x][y+1]=color;
				color_xy[x+1][y+1]=color;
				color_xy[x+2][y+1]=color;
				status=false;
			}
			break;
		case 16:
			if(map_xy[x][y+3]==0 && map_xy[x+1][y+1]==0) {
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x+1][y]=1;
				map_xy[x][y+1]=1;
				map_xy[x][y+2]=1;
				color_xy[x][y]=color;
				color_xy[x+1][y]=color;
				color_xy[x][y+1]=color;
				color_xy[x][y+2]=color;
				status=false;
			}
			break;
		case 17:
			if(map_xy[x][y+1]==0 && map_xy[x+1][y+1]==0 && map_xy[x+2][y+2]==0) {
				y+=1;
			}else {
				map_xy[x][y]=1;
				map_xy[x+1][y]=1;
				map_xy[x+2][y]=1;
				map_xy[x+2][y+1]=1;
				color_xy[x][y]=color;
				color_xy[x+1][y]=color;
				color_xy[x+2][y]=color;
				color_xy[x+2][y+1]=color;
				status=false;
			}
			break;
		case 18:
			if(map_xy[x][y+3]==0 && map_xy[x+1][y+3]==0) {
				y+=1;
			}else {
				map_xy[x+1][y]=1;
				map_xy[x+1][y+1]=1;
				map_xy[x][y+2]=1;
				map_xy[x+1][y+2]=1;
				color_xy[x+1][y]=color;
				color_xy[x+1][y+1]=color;
				color_xy[x][y+2]=color;
				color_xy[x+1][y+2]=color;
				status=false;
			}
			break;
		}
		map.setMap(map_xy);
		map.setColor(color_xy);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
