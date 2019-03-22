package com.tianmeng;

import java.awt.Color;
/**
 * 1.坐标设置
 * @author w8.1
 *
 */
public class Map {
	private int width;      //1.宽
	private int height;     //2.高
	private int[][]map;     //3.坐标数组
	private Color[][]color; //4.颜色数组
	//生成地图width*height
	public void Map(){}
	
	public Map(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		//初始化
		map=new int[width][height+1];
		color=new Color[width][height];
		//封底
		for(int i=0;i<width;i++) {
			map[i][height]=1;
		}
	}
	//消除判断
	public int dispel(int score) {
		//多行消除的奖励分
		int award=0;
		for(int j=0;j<height;j++) {
			int count=0;
			for(int i=0;i<width;i++) {
				count+=map[i][j];
			}
			if(count==width) {
				award++;
				//消除本行
				for(int i=0;i<width;i++) {
					map[i][j]=0;
				}
				//向下移动上面行数
				for(int y=j;y>0;y--) {
					for(int x=0;x<width;x++) {
						map[x][y]=map[x][y-1];
						color[x][y]=color[x][y-1];
					}
				}
			}
		}
		for(int i=1;i<=award;i++) {
			score+=50*i;
		}
		return score;
	}

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

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public Color[][] getColor() {
		return color;
	}

	public void setColor(Color[][] color) {
		this.color = color;
	}
	
}
