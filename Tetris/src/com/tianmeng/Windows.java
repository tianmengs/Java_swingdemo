package com.tianmeng;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

public class Windows extends JFrame {
	private Toolkit took = null;
	private static int width= 470;     //宽
	private static int height = 680;   //高
	private static int width_g = 10;   //游戏区域
	private static int height_g = 22;  //
	private static int size = 30;      //方块大小
	private static int space= 10;      //坐标距边界间隔
	Map map = new Map(width_g,height_g);//地图坐标
	ArrayList <Diamonds> ds = new ArrayList<Diamonds>();//方块数组
	private boolean game=false;        //游戏开始
	private int flag = 0;              //游戏状态【暂停：0，继续：1】
	private JLabel jl2;
	private JButton jb1;
	private int speed = 500;           //速度
	private int score = 0;             //分数
	private int[] scores = new int[4]; //排名
	
	public static void main(String[] args) {
		Windows win = new Windows();
		win.run();
	}

	public void run(){
		init();
		try {
			while(true) {
				if(game&&flag==1) {
					ds.get(ds.size()-2).movement(map);
					if(!ds.get(ds.size()-2).isStatus()) {
						//判断游戏是否失败
						if(ds.get((ds.size()-2)).getY()<=3) {
							game=false;
							//重置游戏参数
							ds = new ArrayList<Diamonds>();
							map = new Map(width_g,height_g);
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), "游戏结束！\n您获得【"+score+"】点分数");

							score=0;
							jl2.setText("分数：   "+score);
							jb1.setEnabled(true);
							jb1.setText("重新开始");
						}else {
							//消除判断
							score=map.dispel(score);
							jl2.setText("分数：   "+score);
			
							//生成新方块
							Diamonds diamonds = new Diamonds(width_g);
							ds.add(diamonds);
						}
					}
				}
				repaint();
				Thread.sleep(speed);
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	//窗口加载
	public void init() {
		//界面设置
		this.setTitle("俄罗斯方块");    //标题
		this.setSize(width, height); //界面大小
		took = Toolkit.getDefaultToolkit();
		Dimension dm = took.getScreenSize();
		int swidth = (dm.width - width)/2;
		int sheight = (dm.height - height)/2;
	    this.setLocation(swidth, sheight);
	    
	    //容器
	    JPanel p1 = new JPanel();   //地图
	    JPanel p2 = new JPanel();   //俄罗斯方块控制界面
	    JPanel p3 = new JPanel();   //按钮
	    JPanel p4 = new JPanel();   //说明
	    
	    //图形绘制容器
	    JPanel contentPane =  new PaintPanel();
	    setContentPane(contentPane);
	    
	   //标签
	    JLabel jl1 = new JLabel("俄罗斯方块控制界面");
	    jl2 = new JLabel("分数：   "+score);
	    JLabel jl3 = new JLabel("游戏说明：");
	    
	    //按钮
	    jb1 = new JButton("游戏开始");
	    JButton jb2 = new JButton("难度选择");
	    JButton jb3 = new JButton("继续/暂停");
	    JButton jb4 = new JButton("游戏退出");
	    JButton jb5 = new JButton("高级");
	    JButton jb6 = new JButton("中级");
	    JButton jb7 = new JButton("低级");
	    JButton jb8 = new JButton("显示排名");
	    
	    //文本
	    JTextArea jta = new JTextArea("1.点击【游戏开始】按钮开始游戏。"
	    		+ "\n2.【难度选择】可以更改游戏速度"
	    		+ "\n3.游戏中可随时暂停，使用方向键可继续游戏"
	    		+ "\n4.……",50,9);
	    jta.setSelectionColor(Color.RED);
	    jta.setEditable(false);
	    jta.setLineWrap(true);
	    
	    //布局
	    this.setLayout(new BorderLayout(5,5));
	    p2.setLayout(new GridLayout(2,1,5,5));
	    p3.setLayout(new GridLayout(10,1,5,5));
	    p4.setLayout(new BorderLayout(5,5));
	    
	    //设置边界
	    p2.setBorder(BorderFactory.createEmptyBorder(20,20,15,15));
	    
	    //背景颜色
	    p1.setBackground(new Color(255,255,255,0));
	    p2.setBackground(new Color(255,255,255,0));
	    p3.setBackground(new Color(255,255,255,0));
	    p4.setBackground(new Color(255,255,255,0));
	    jta.setBackground(Color.WHITE);
	    
	    //添加容器/组件
	    p3.add(jl1);
	    p3.add(jl2);
	    p3.add(jb1);
	    //p3.add(jb2);
	    p3.add(jb3);
	    p3.add(jb4);
	    p3.add(jb8);
	    
	    p4.add(jl3,BorderLayout.NORTH);
	    p4.add(jta,BorderLayout.CENTER);
	    
	    p2.add(p3);
	    p2.add(p4);
	    
	    //主容器
	    this.add(p1,BorderLayout.CENTER);
	    this.add(p2,BorderLayout.EAST);
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setUndecorated(true);
		this.setVisible(true);
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				//游戏开始时按键有效
				if(ds.size()!=0) {
					int type = ds.get(ds.size()-2).getType();
					int x = ds.get(ds.size()-2).getX();
					if(e.getKeyCode()==KeyEvent.VK_LEFT) {
						ds.get(ds.size()-2).left(map);
					}
					if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
						ds.get(ds.size()-2).right(map);
					}
					if(e.getKeyCode()==KeyEvent.VK_UP) {
						if(type<=1) {
							//可能在转换过程发生越界的解决办法
							if(type==1) {
								if(x>=width_g-3) {
									ds.get(ds.size()-2).setX(width_g-4);
								}
							}
							ds.get(ds.size()-2).setType(type==0?1:0);
						}else if(type<=5) {
							if(type==3||type==5) {
								if(x==width_g-2) {
									ds.get(ds.size()-2).setX(width_g-3);
								}
							}
							ds.get(ds.size()-2).setType(type==5?2:++type);
						}else if(type<=9) {
							ds.get(ds.size()-2).setType(type==9?6:++type);
						}else if(type<=10) {
							ds.get(ds.size()-2).setType(10);
						}else if(type<=14) {
							if(type==12||type==14) {
								if(x==width_g-2) {
									ds.get(ds.size()-2).setX(width_g-3);
								}
							}
							ds.get(ds.size()-2).setType(type==14?11:++type);
						}else if(type<=18) {
							if(type==16||type==18) {
								if(x==width_g-2) {
									ds.get(ds.size()-2).setX(width_g-3);
								}
							}
							ds.get(ds.size()-2).setType(type==18?15:++type);
						}
					}
				}
				
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					speed = 100;
				}
			}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					speed = 500;
				}
			}
		});
		
		//游戏开始
		jb1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				requestFocus();
				//生成第一个方块
				Diamonds diamonds = new Diamonds(width_g);
				ds.add(diamonds);
				//生成提示方块
				Diamonds point = new Diamonds(width_g);
				ds.add(point);
				//游戏开始
				game=true;
				flag=1;
				//游戏开始后禁止使用
				jb1.setEnabled(false);
			}
		});
		
		//退出
		jb4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});	
		
		//暂停/继续
		jb3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(flag==0) {
					flag=1;
				}else {
					flag=0;
				}
				requestFocus();
			}
		});
	}
	
	//重写paintComponent(图形绘制)
	private class PaintPanel extends JPanel{
		@Override
		protected void paintComponent(Graphics g) {
			//还原size的值
			size=30;
			//绘制边界
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, width-149, height-1);
			g.setColor(Color.PINK);
			g.fillRect(width-149, 0, 148, height-1);
			g.setColor(Color.BLACK);
			g.drawLine(width-149, 0, width-149, height-1);
			g.drawRect(0, 0, width-1, height-1);
			g.setColor(Color.WHITE);       
			g.fillRect(space, space, width_g*size, height_g*size);
			g.setColor(Color.BLACK);
			g.drawRect(space, space, width_g*size, height_g*size);
			g.drawLine(space, space+3*size, space+width_g*size, space+3*size);
			//提示框
			g.setColor(Color.WHITE);
			g.fillRect(width-135, 222, 4*size, 4*size);
			g.setColor(Color.BLACK);
			g.drawRect(width-135, 222, 4*size, 4*size);
			
			if(game) {
				Color[][] color_xy = map.getColor();
				int[][] map_xy = map.getMap();
				//绘制地图
				for(int i=0;i<width_g;i++) {
					for(int j=0;j<height_g;j++) {
						// 绘制网格线，可注释
						g.drawRect(i*size+space, j*size+space, size, size);
						if(map_xy[i][j]==1) {
							//g.setColor(color_xy[i][j]);
							g.setColor(Color.BLUE);
							g.fillRect(i*size+space, j*size+space, size, size);
							
							g.setColor(Color.BLACK);
							g.drawRect(i*size+space, j*size+space, size, size);
						}
					}
				}
				//绘制可移动的方块
				int type=ds.get(ds.size()-2).getType();
				int x=ds.get(ds.size()-2).getX();
				int y=ds.get(ds.size()-2).getY();
				Color color = ds.get(ds.size()-2).getColor();
				//绘制提示方块
				int typeO=ds.get(ds.size()-1).getType();
				int xO=width-100;
				int yO=260;
				Color colorO = ds.get(ds.size()-1).getColor();
					
					//绘制图形
					//图形一，两种形态
				if(type==0) {
					g.setColor(color);
					g.fillRect(x*size+space, y*size+space, size, size);
					g.fillRect((x+1)*size+space, y*size+space, size, size);
					g.fillRect((x+2)*size+space, y*size+space, size, size);
					g.fillRect((x+3)*size+space, y*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect((x+1)*size+space, y*size+space, size, size);
					g.drawRect((x+2)*size+space, y*size+space, size, size);
					g.drawRect((x+3)*size+space, y*size+space, size, size);
				}
				if(type==1) {
					g.setColor(color);
					g.fillRect(x*size+space, y*size+space, size, size);
					g.fillRect(x*size+space, (y+1)*size+space, size, size);
					g.fillRect(x*size+space, (y+2)*size+space, size, size);
					g.fillRect(x*size+space, (y+3)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect(x*size+space, (y+1)*size+space, size, size);
					g.drawRect(x*size+space, (y+2)*size+space, size, size);
					g.drawRect(x*size+space, (y+3)*size+space, size, size);
				}
					
					//图形二，四种形态
				if(type==2) {
					g.setColor(color);
					g.fillRect((x+1)*size+space, y*size+space, size, size);
					g.fillRect(x*size+space, (y+1)*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.fillRect((x+2)*size+space, (y+1)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect((x+1)*size+space, y*size+space, size, size);
					g.drawRect(x*size+space, (y+1)*size+space, size, size);
					g.drawRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.drawRect((x+2)*size+space, (y+1)*size+space, size, size);
				}
				if(type==3) {
					g.setColor(color);
					g.fillRect(x*size+space, y*size+space, size, size);
					g.fillRect(x*size+space, (y+1)*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.fillRect(x*size+space, (y+2)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect(x*size+space, (y+1)*size+space, size, size);
					g.drawRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.drawRect(x*size+space, (y+2)*size+space, size, size);
				}
				if(type==4) {
					g.setColor(color);
					g.fillRect((x)*size+space, y*size+space, size, size);
					g.fillRect((x+1)*size+space, y*size+space, size, size);
					g.fillRect((x+2)*size+space, y*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect((x+1)*size+space, y*size+space, size, size);
					g.drawRect((x+2)*size+space, y*size+space, size, size);
					g.drawRect((x+1)*size+space, (y+1)*size+space, size, size);
				}
				if(type==5) {
					g.setColor(color);
					g.fillRect((x+1)*size+space, y*size+space, size, size);
					g.fillRect(x*size+space, (y+1)*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+2)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect((x+1)*size+space, y*size+space, size, size);
					g.drawRect(x*size+space, (y+1)*size+space, size, size);
					g.drawRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.drawRect((x+1)*size+space, (y+2)*size+space, size, size);
				}
					
					
				//图形三，四种形态
				if(type==6) {
					g.setColor(color);
					g.fillRect((x+1)*size+space, y*size+space, size, size);
					g.fillRect(x*size+space, (y+1)*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect((x+1)*size+space, y*size+space, size, size);
					g.drawRect(x*size+space, (y+1)*size+space, size, size);
					g.drawRect((x+1)*size+space, (y+1)*size+space, size, size);	
				}
				if(type==7) {
					g.setColor(color);
					g.fillRect(x*size+space, y*size+space, size, size);
					g.fillRect(x*size+space, (y+1)*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect(x*size+space, (y+1)*size+space, size, size);
					g.drawRect((x+1)*size+space, (y+1)*size+space, size, size);	
				}
				if(type==8) {
					g.setColor(color);
					g.fillRect(x*size+space, y*size+space, size, size);
					g.fillRect((x+1)*size+space, y*size+space, size, size);
					g.fillRect(x*size+space, (y+1)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect((x+1)*size+space, y*size+space, size, size);
					g.drawRect(x*size+space, (y+1)*size+space, size, size);	
				}
				if(type==9) {
					g.setColor(color);
					g.fillRect(x*size+space, y*size+space, size, size);
					g.fillRect((x+1)*size+space, y*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect((x+1)*size+space, y*size+space, size, size);
					g.drawRect((x+1)*size+space, (y+1)*size+space, size, size);	
				}
					
				//图形四，一种形态
				if(type==10) {
					g.setColor(color);
					g.fillRect(x*size+space, y*size+space, size, size);
					g.fillRect((x+1)*size+space, y*size+space, size, size);
					g.fillRect(x*size+space, (y+1)*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect((x+1)*size+space, y*size+space, size, size);
					g.drawRect(x*size+space, (y+1)*size+space, size, size);	
					g.drawRect((x+1)*size+space, (y+1)*size+space, size, size);
				}
					
				//图形五，三种形态
				if(type==11) {
					g.setColor(color);
					g.fillRect((x+2)*size+space, y*size+space, size, size);
					g.fillRect(x*size+space, (y+1)*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.fillRect((x+2)*size+space, (y+1)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect((x+2)*size+space, y*size+space, size, size);
					g.drawRect(x*size+space, (y+1)*size+space, size, size);
					g.drawRect((x+1)*size+space, (y+1)*size+space, size, size);	
					g.drawRect((x+2)*size+space, (y+1)*size+space, size, size);
				}
				if(type==12) {
					g.setColor(color);
					g.fillRect(x*size+space, y*size+space, size, size);
					g.fillRect((x+1)*size+space, y*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+2)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect((x+1)*size+space, y*size+space, size, size);
					g.drawRect((x+1)*size+space, (y+1)*size+space, size, size);	
					g.drawRect((x+1)*size+space, (y+2)*size+space, size, size);
				}
				if(type==13) {
					g.setColor(color);
					g.fillRect(x*size+space, y*size+space, size, size);
					g.fillRect((x+1)*size+space, y*size+space, size, size);
					g.fillRect((x+2)*size+space, y*size+space, size, size);
					g.fillRect(x*size+space, (y+1)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect((x+1)*size+space, y*size+space, size, size);
					g.drawRect((x+2)*size+space, y*size+space, size, size);	
					g.drawRect(x*size+space, (y+1)*size+space, size, size);
				}
				if(type==14) {
					g.setColor(color);
					g.fillRect(x*size+space, y*size+space, size, size);
					g.fillRect(x*size+space, (y+1)*size+space, size, size);
					g.fillRect(x*size+space, (y+2)*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+2)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect(x*size+space, (y+1)*size+space, size, size);
					g.drawRect(x*size+space, (y+2)*size+space, size, size);	
					g.drawRect((x+1)*size+space, (y+2)*size+space, size, size);
				}
				
				//图形六，三种形态
				if(type==15) {
					g.setColor(color);
					g.fillRect(x*size+space, y*size+space, size, size);
					g.fillRect(x*size+space, (y+1)*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.fillRect((x+2)*size+space, (y+1)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect(x*size+space, (y+1)*size+space, size, size);
					g.drawRect((x+1)*size+space, (y+1)*size+space, size, size);	
					g.drawRect((x+2)*size+space, (y+1)*size+space, size, size);
				}
				if(type==16) {
					g.setColor(color);
					g.fillRect(x*size+space, y*size+space, size, size);
					g.fillRect((x+1)*size+space, y*size+space, size, size);
					g.fillRect(x*size+space, (y+1)*size+space, size, size);
					g.fillRect(x*size+space, (y+2)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect((x+1)*size+space, y*size+space, size, size);
					g.drawRect(x*size+space, (y+1)*size+space, size, size);	
					g.drawRect(x*size+space, (y+2)*size+space, size, size);
				}
				if(type==17) {
					g.setColor(color);
					g.fillRect(x*size+space, y*size+space, size, size);
					g.fillRect((x+1)*size+space, y*size+space, size, size);
					g.fillRect((x+2)*size+space, y*size+space, size, size);
					g.fillRect((x+2)*size+space, (y+1)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(x*size+space, y*size+space, size, size);
					g.drawRect((x+1)*size+space, y*size+space, size, size);
					g.drawRect((x+2)*size+space, y*size+space, size, size);	
					g.drawRect((x+2)*size+space, (y+1)*size+space, size, size);
				}
				if(type==18) {
					g.setColor(color);
					g.fillRect((x+1)*size+space, y*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.fillRect(x*size+space, (y+2)*size+space, size, size);
					g.fillRect((x+1)*size+space, (y+2)*size+space, size, size);
					g.setColor(Color.BLACK);
					g.drawRect((x+1)*size+space, y*size+space, size, size);
					g.drawRect((x+1)*size+space, (y+1)*size+space, size, size);
					g.drawRect(x*size+space, (y+2)*size+space, size, size);	
					g.drawRect((x+1)*size+space, (y+2)*size+space, size, size);
				}
					
				//绘制提示图形
				size=(int)(size/1.5);
				if(typeO==0) {
					g.setColor(colorO);
					g.fillRect(xO, yO, size, size);
					g.fillRect(xO+size, yO, size, size);
					g.fillRect(xO+size*2, yO, size, size);
					g.fillRect(xO+size*3, yO, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(xO, yO, size, size);
					g.drawRect(xO+size, yO, size, size);
					g.drawRect(xO+size*2, yO, size, size);
					g.drawRect(xO+size*3, yO, size, size);
				}
				if(typeO==1) {
					g.setColor(colorO);
					g.fillRect(xO, yO, size, size);
					g.fillRect(xO, size+yO, size, size);
					g.fillRect(xO, 2*size+yO, size, size);
					g.fillRect(xO, 3*size+yO, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(xO, yO, size, size);
					g.drawRect(xO, size+yO, size, size);
					g.drawRect(xO, 2*size+yO, size, size);
					g.drawRect(xO, 3*size+yO, size, size);
				}
						
				//图形二，四种形态
				if(typeO==2) {
					g.setColor(colorO);
					g.fillRect(size+xO, yO, size, size);
					g.fillRect(xO, size+yO, size, size);
					g.fillRect(size+xO, size+yO, size, size);
					g.fillRect(2*size+xO, size+yO, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(size+xO, yO, size, size);
					g.drawRect(xO, size+yO, size, size);
					g.drawRect(size+xO, size+yO, size, size);
					g.drawRect(2*size+xO, size+yO, size, size);
				}
					if(typeO==3) {
						g.setColor(colorO);
						g.fillRect(xO, yO, size, size);
						g.fillRect(xO, size+yO, size, size);
						g.fillRect(size+xO, size+yO, size, size);
						g.fillRect(xO, 2*size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(xO, yO, size, size);
						g.drawRect(xO, size+yO, size, size);
						g.drawRect(size+xO, size+yO, size, size);
						g.drawRect(xO, 2*size+yO, size, size);
					}
					if(typeO==4) {
						g.setColor(colorO);
						g.fillRect(xO, yO, size, size);
						g.fillRect(size+xO, yO, size, size);
						g.fillRect(2*size+xO, yO, size, size);
						g.fillRect(size+xO, size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(xO, yO, size, size);
						g.drawRect(size+xO, yO, size, size);
						g.drawRect(2*size+xO, yO, size, size);
						g.drawRect(size+xO, size+yO, size, size);
					}
					if(typeO==5) {
						g.setColor(colorO);
						g.fillRect(size+xO, yO, size, size);
						g.fillRect(xO, size+yO, size, size);
						g.fillRect(size+xO, size+yO, size, size);
						g.fillRect(size+xO, 2*size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(size+xO, yO, size, size);
						g.drawRect(xO, size+yO, size, size);
						g.drawRect(size+xO, size+yO, size, size);
						g.drawRect(size+xO, 2*size+yO, size, size);
					}
					
					
					//图形三，四种形态
					if(typeO==6) {
						g.setColor(colorO);
						g.fillRect(size+xO, yO, size, size);
						g.fillRect(xO, size+yO, size, size);
						g.fillRect(size+xO, size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(size+xO, yO, size, size);
						g.drawRect(xO, size+yO, size, size);
						g.drawRect(size+xO, size+yO, size, size);	
					}
					if(typeO==7) {
						g.setColor(colorO);
						g.fillRect(xO, yO, size, size);
						g.fillRect(xO, size+yO, size, size);
						g.fillRect(size+xO, size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(xO, yO, size, size);
						g.drawRect(xO, size+yO, size, size);
						g.drawRect(size+xO, size+yO, size, size);	
					}
					if(typeO==8) {
						g.setColor(colorO);
						g.fillRect(xO, yO, size, size);
						g.fillRect(size+xO, yO, size, size);
						g.fillRect(xO, size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(xO, yO, size, size);
						g.drawRect(size+xO, yO, size, size);
						g.drawRect(xO, size+yO, size, size);	
					}
					if(typeO==9) {
						g.setColor(colorO);
						g.fillRect(xO, yO, size, size);
						g.fillRect(size+xO, yO, size, size);
						g.fillRect(size+xO, size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(xO, yO, size, size);
						g.drawRect(size+xO, yO, size, size);
						g.drawRect(size+xO, size+yO, size, size);	
					}
					
					//图形四，一种形态
					if(typeO==10) {
						g.setColor(colorO);
						g.fillRect(xO, yO, size, size);
						g.fillRect(size+xO, yO, size, size);
						g.fillRect(xO, size+yO, size, size);
						g.fillRect(size+xO, size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(xO, yO, size, size);
						g.drawRect(size+xO, yO, size, size);
						g.drawRect(xO, size+yO, size, size);	
						g.drawRect(size+xO, size+yO, size, size);
					}
					
					//图形五，三种形态
					if(typeO==11) {
						g.setColor(colorO);
						g.fillRect(2*size+xO, yO, size, size);
						g.fillRect(xO, size+yO, size, size);
						g.fillRect(size+xO, size+yO, size, size);
						g.fillRect(2*size+xO, size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(2*size+xO, yO, size, size);
						g.drawRect(xO, size+yO, size, size);
						g.drawRect(size+xO, size+yO, size, size);	
						g.drawRect(2*size+xO, size+yO, size, size);
					}
					if(typeO==12) {
						g.setColor(colorO);
						g.fillRect(xO, yO, size, size);
						g.fillRect(size+xO, yO, size, size);
						g.fillRect(size+xO, size+yO, size, size);
						g.fillRect(size+xO, 2*size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(xO, yO, size, size);
						g.drawRect(size+xO, yO, size, size);
						g.drawRect(size+xO, size+yO, size, size);	
						g.drawRect(size+xO, 2*size+yO, size, size);
					}
					if(typeO==13) {
						g.setColor(colorO);
						g.fillRect(xO, yO, size, size);
						g.fillRect(size+xO, yO, size, size);
						g.fillRect(2*size+xO, yO, size, size);
						g.fillRect(xO, size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(xO, yO, size, size);
						g.drawRect(size+xO, yO, size, size);
						g.drawRect(2*size+xO, yO, size, size);	
						g.drawRect(xO, size+yO, size, size);
					}
					if(typeO==14) {
						g.setColor(colorO);
						g.fillRect(xO, yO, size, size);
						g.fillRect(xO, size+yO, size, size);
						g.fillRect(xO, 2*size+yO, size, size);
						g.fillRect(size+xO, 2*size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(xO, yO, size, size);
						g.drawRect(xO, size+yO, size, size);
						g.drawRect(xO, 2*size+yO, size, size);	
						g.drawRect(size+xO, 2*size+yO, size, size);
					}
					
					//图形六，三种形态
					if(typeO==15) {
						g.setColor(colorO);
						g.fillRect(xO, yO, size, size);
						g.fillRect(xO, size+yO, size, size);
						g.fillRect(size+xO, size+yO, size, size);
						g.fillRect(2*size+xO, size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(xO, yO, size, size);
						g.drawRect(xO, size+yO, size, size);
						g.drawRect(size+xO, size+yO, size, size);	
						g.drawRect(2*size+xO, size+yO, size, size);
					}
					if(typeO==16) {
						g.setColor(colorO);
						g.fillRect(xO, yO, size, size);
						g.fillRect(size+xO, yO, size, size);
						g.fillRect(xO, size+yO, size, size);
						g.fillRect(xO, 2*size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(xO, yO, size, size);
						g.drawRect(size+xO, yO, size, size);
						g.drawRect(xO, size+yO, size, size);	
						g.drawRect(xO, 2*size+yO, size, size);
					}
					if(typeO==17) {
						g.setColor(colorO);
						g.fillRect(xO, yO, size, size);
						g.fillRect(size+xO, yO, size, size);
						g.fillRect(2*size+xO, yO, size, size);
						g.fillRect(2*size+xO, size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(xO, yO, size, size);
						g.drawRect(size+xO, yO, size, size);
						g.drawRect(2*size+xO, yO, size, size);	
						g.drawRect(2*size+xO, size+yO, size, size);
					}
					if(typeO==18) {
						g.setColor(colorO);
						g.fillRect(size+xO, yO, size, size);
						g.fillRect(size+xO, size+yO, size, size);
						g.fillRect(xO, 2*size+yO, size, size);
						g.fillRect(size+xO, 2*size+yO, size, size);
						g.setColor(Color.BLACK);
						g.drawRect(size+xO, yO, size, size);
						g.drawRect(size+xO, size+yO, size, size);
						g.drawRect(xO, 2*size+yO, size, size);	
						g.drawRect(size+xO, 2*size+yO, size, size);
					}
			}		
		}
	}
}
