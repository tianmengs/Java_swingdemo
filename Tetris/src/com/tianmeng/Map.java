package com.tianmeng;

import java.awt.Color;
/**
 * 1.��������
 * @author w8.1
 *
 */
public class Map {
	private int width;      //1.��
	private int height;     //2.��
	private int[][]map;     //3.��������
	private Color[][]color; //4.��ɫ����
	//���ɵ�ͼwidth*height
	public void Map(){}
	
	public Map(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		//��ʼ��
		map=new int[width][height+1];
		color=new Color[width][height];
		//���
		for(int i=0;i<width;i++) {
			map[i][height]=1;
		}
	}
	//�����ж�
	public int dispel(int score) {
		//���������Ľ�����
		int award=0;
		for(int j=0;j<height;j++) {
			int count=0;
			for(int i=0;i<width;i++) {
				count+=map[i][j];
			}
			if(count==width) {
				award++;
				//��������
				for(int i=0;i<width;i++) {
					map[i][j]=0;
				}
				//�����ƶ���������
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
