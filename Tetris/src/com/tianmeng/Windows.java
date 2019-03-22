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
	private static int width= 470;     //��
	private static int height = 680;   //��
	private static int width_g = 10;   //��Ϸ����
	private static int height_g = 22;  //
	private static int size = 30;      //�����С
	private static int space= 10;      //�����߽���
	Map map = new Map(width_g,height_g);//��ͼ����
	ArrayList <Diamonds> ds = new ArrayList<Diamonds>();//��������
	private boolean game=false;        //��Ϸ��ʼ
	private int flag = 0;              //��Ϸ״̬����ͣ��0��������1��
	private JLabel jl2;
	private JButton jb1;
	private int speed = 500;           //�ٶ�
	private int score = 0;             //����
	private int[] scores = new int[4]; //����
	
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
						//�ж���Ϸ�Ƿ�ʧ��
						if(ds.get((ds.size()-2)).getY()<=3) {
							game=false;
							//������Ϸ����
							ds = new ArrayList<Diamonds>();
							map = new Map(width_g,height_g);
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), "��Ϸ������\n����á�"+score+"�������");

							score=0;
							jl2.setText("������   "+score);
							jb1.setEnabled(true);
							jb1.setText("���¿�ʼ");
						}else {
							//�����ж�
							score=map.dispel(score);
							jl2.setText("������   "+score);
			
							//�����·���
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
	//���ڼ���
	public void init() {
		//��������
		this.setTitle("����˹����");    //����
		this.setSize(width, height); //�����С
		took = Toolkit.getDefaultToolkit();
		Dimension dm = took.getScreenSize();
		int swidth = (dm.width - width)/2;
		int sheight = (dm.height - height)/2;
	    this.setLocation(swidth, sheight);
	    
	    //����
	    JPanel p1 = new JPanel();   //��ͼ
	    JPanel p2 = new JPanel();   //����˹������ƽ���
	    JPanel p3 = new JPanel();   //��ť
	    JPanel p4 = new JPanel();   //˵��
	    
	    //ͼ�λ�������
	    JPanel contentPane =  new PaintPanel();
	    setContentPane(contentPane);
	    
	   //��ǩ
	    JLabel jl1 = new JLabel("����˹������ƽ���");
	    jl2 = new JLabel("������   "+score);
	    JLabel jl3 = new JLabel("��Ϸ˵����");
	    
	    //��ť
	    jb1 = new JButton("��Ϸ��ʼ");
	    JButton jb2 = new JButton("�Ѷ�ѡ��");
	    JButton jb3 = new JButton("����/��ͣ");
	    JButton jb4 = new JButton("��Ϸ�˳�");
	    JButton jb5 = new JButton("�߼�");
	    JButton jb6 = new JButton("�м�");
	    JButton jb7 = new JButton("�ͼ�");
	    JButton jb8 = new JButton("��ʾ����");
	    
	    //�ı�
	    JTextArea jta = new JTextArea("1.�������Ϸ��ʼ����ť��ʼ��Ϸ��"
	    		+ "\n2.���Ѷ�ѡ�񡿿��Ը�����Ϸ�ٶ�"
	    		+ "\n3.��Ϸ�п���ʱ��ͣ��ʹ�÷�����ɼ�����Ϸ"
	    		+ "\n4.����",50,9);
	    jta.setSelectionColor(Color.RED);
	    jta.setEditable(false);
	    jta.setLineWrap(true);
	    
	    //����
	    this.setLayout(new BorderLayout(5,5));
	    p2.setLayout(new GridLayout(2,1,5,5));
	    p3.setLayout(new GridLayout(10,1,5,5));
	    p4.setLayout(new BorderLayout(5,5));
	    
	    //���ñ߽�
	    p2.setBorder(BorderFactory.createEmptyBorder(20,20,15,15));
	    
	    //������ɫ
	    p1.setBackground(new Color(255,255,255,0));
	    p2.setBackground(new Color(255,255,255,0));
	    p3.setBackground(new Color(255,255,255,0));
	    p4.setBackground(new Color(255,255,255,0));
	    jta.setBackground(Color.WHITE);
	    
	    //�������/���
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
	    
	    //������
	    this.add(p1,BorderLayout.CENTER);
	    this.add(p2,BorderLayout.EAST);
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setUndecorated(true);
		this.setVisible(true);
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				//��Ϸ��ʼʱ������Ч
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
							//������ת�����̷���Խ��Ľ���취
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
		
		//��Ϸ��ʼ
		jb1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				requestFocus();
				//���ɵ�һ������
				Diamonds diamonds = new Diamonds(width_g);
				ds.add(diamonds);
				//������ʾ����
				Diamonds point = new Diamonds(width_g);
				ds.add(point);
				//��Ϸ��ʼ
				game=true;
				flag=1;
				//��Ϸ��ʼ���ֹʹ��
				jb1.setEnabled(false);
			}
		});
		
		//�˳�
		jb4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});	
		
		//��ͣ/����
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
	
	//��дpaintComponent(ͼ�λ���)
	private class PaintPanel extends JPanel{
		@Override
		protected void paintComponent(Graphics g) {
			//��ԭsize��ֵ
			size=30;
			//���Ʊ߽�
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
			//��ʾ��
			g.setColor(Color.WHITE);
			g.fillRect(width-135, 222, 4*size, 4*size);
			g.setColor(Color.BLACK);
			g.drawRect(width-135, 222, 4*size, 4*size);
			
			if(game) {
				Color[][] color_xy = map.getColor();
				int[][] map_xy = map.getMap();
				//���Ƶ�ͼ
				for(int i=0;i<width_g;i++) {
					for(int j=0;j<height_g;j++) {
						// ���������ߣ���ע��
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
				//���ƿ��ƶ��ķ���
				int type=ds.get(ds.size()-2).getType();
				int x=ds.get(ds.size()-2).getX();
				int y=ds.get(ds.size()-2).getY();
				Color color = ds.get(ds.size()-2).getColor();
				//������ʾ����
				int typeO=ds.get(ds.size()-1).getType();
				int xO=width-100;
				int yO=260;
				Color colorO = ds.get(ds.size()-1).getColor();
					
					//����ͼ��
					//ͼ��һ��������̬
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
					
					//ͼ�ζ���������̬
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
					
					
				//ͼ������������̬
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
					
				//ͼ���ģ�һ����̬
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
					
				//ͼ���壬������̬
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
				
				//ͼ������������̬
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
					
				//������ʾͼ��
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
						
				//ͼ�ζ���������̬
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
					
					
					//ͼ������������̬
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
					
					//ͼ���ģ�һ����̬
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
					
					//ͼ���壬������̬
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
					
					//ͼ������������̬
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
