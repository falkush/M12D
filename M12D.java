import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class M12D extends JPanel implements KeyListener, FocusListener {
	private static final long serialVersionUID = 1L;
	static public int n=12;
	 static public char c;
	 static public boolean focus;
	 
	 static BufferedImage invcursimg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	 static Cursor invcurs = Toolkit.getDefaultToolkit().createCustomCursor(invcursimg, new Point(0, 0), "");
	 
	 static final JFrame frame = new JFrame("Press ESC to stop, click to resume");
	 static final JLabel label = new JLabel();
	 
	 static int centralx, centraly;
	 
	 static boolean holdw=false;
	 static boolean holdq=false;
	 static boolean holde=false;
	 static boolean holda=false;
	 static boolean holds=false;
	 static boolean holdd=false;
	 static boolean holdr=false;
	 static boolean holdf=false;
	 static boolean holdz=false;
	 static boolean holdx=false;
	 static boolean holdc=false;
	 static boolean holdv=false;
	 static boolean holdb=false;
	 static boolean holdm=false;
	 static boolean holdn=false;
	 static boolean holdk=false;
	 static boolean holdl=false;
	 static boolean resetcm2=false;
	 static boolean resetcm=false;
	 static boolean resettot=false;

	public static void main(String[] args) throws AWTException, InterruptedException {
		
		int height=180;
		int width=320;
		
		float dist=(float)1;
		float sqsz=(float)0.01;
		
		int tmp2,tmp3;
		int i,j,k,pw,ph,tmp;
		
		int mult=1;
		
		int height2=height*mult;
		int width2=width*mult;
		
		int currentpix;
		
		focus=true;
		
		final Robot robot = new Robot();
	    
	    
	    BufferedImage image = new BufferedImage(width2,height2,BufferedImage.TYPE_4BYTE_ABGR);
	    
		float msqsz=-sqsz;
		float multy=((float)(1-width))*sqsz/(float)2;
		float multz=((float)(height-1))*sqsz/(float)2;
		
		float[] addy = new float[n];
		float[] addz = new float[n];
		float[] vectmp = new float[n];
		float[] vecn = new float[n];
		float[][] vecl = new float[height][width];
		
			
		float[] pos = new float[n];
		float[] inv = new float[n];
		
		
		int fin = 1;
		int nbframe=0;
		
		int mousx=0;
		int mousy=0;
		
		boolean fblock;
		
		float[][] x = new float[n][n];
		
		int[] tmpvec = new int[n];
		int[] tmpvec2 = new int[n];
		
		float[][] newx = new float[n][n];
		
		float[] vec = new float[n];
		
		int min;
		int tmpm=0;
		
		int n2 = (int)Math.pow(3, n);
		

		float[] coll = new float[n];
		
		boolean[][] bounds = new boolean[n][n2];
		byte[] tmpcolor = new byte[3];
		byte[][] colors = new byte[n2][3];
		int[] current = new int[n];
		int[] currentini = new int[n];
		
		int[] index = new int[n];
		int[] ngt = new int[n];
		int[] ngt2 = new int[n];
		
		float rsin = (float)Math.sin(Math.PI/32.0);
		float rcos = (float)Math.cos(Math.PI/32.0);
		
		for(i=0;i<n;i++) tmpvec[i]=1;
		fin=convind(tmpvec);

		boolean gg=false;
		

		for(i=0;i<n;i++) x[i][i]=1f;

		
		
		for(i=0;i<n2;i++)
		{
						for(j=0;j<n;j++)
						{
							tmpvec=invind(i);
							tmp=tmpvec[j];
							if(tmp==0 || tmp==2)
							{
								
							}
							else
							{
								bounds[j][i]=!((int)(Math.random()*3)==0);
							}
						}
						tmpcolor[0]=(byte)(Math.random()*256);
						tmpcolor[1]=(byte)(Math.random()*256);
						tmpcolor[2]=(byte)(Math.random()*256);
						while(tmpcolor[0]>=0 && tmpcolor[1]>=0 && tmpcolor[2]>=0) {
							tmpcolor[0]=(byte)(Math.random()*256);
							tmpcolor[1]=(byte)(Math.random()*256);
							tmpcolor[2]=(byte)(Math.random()*256);
						}
						colors[i][0]=tmpcolor[0];
						colors[i][1]=tmpcolor[1];
						colors[i][2]=tmpcolor[2];

		}
		
		tmpvec=perm(n);
		for(i=0;i<n;i++)
		{
			tmpvec2[tmpvec[i]]=1;
			bounds[tmpvec[i]][convind(tmpvec2)]=true;
		}
		
		colors[0][0]=(byte)255;
		colors[0][1]=(byte)255;
		colors[0][2]=(byte)255;

		for(i=0;i<n;i++)
		{
			vec[i]=dist*x[0][i]+multy*x[1][i]+multz*x[2][i];
			addy[i]=sqsz*x[1][i];
			addz[i]=msqsz*x[2][i];
			pos[i]=0.5f;
		}
		
		for(ph=0;ph<height;ph++)
		{
			for(i=0;i<n;i++) vectmp[i]=vec[i];
			for(pw=0;pw<width;pw++)
			{
				vecl[ph][pw]=1f/veclgt(vec);
				for(i=0;i<n;i++) vec[i]+=addy[i];
			}
			for(i=0;i<n;i++) vec[i]=vectmp[i]+addz[i];
		}
	  
	    frame.setResizable(false);
	    frame.getContentPane().add(new M12D());
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.setSize(width2, height2);
	    
	    frame.getContentPane().addMouseListener(new MouseAdapter() {            
	    	   @Override
	    	   public void mouseClicked(MouseEvent e) {
	    		  
	    	      focus=true;
	    	      centralx=frame.getX()+(width2/2);
	    	      centraly=frame.getY()+(height2/2);
	    	      frame.getContentPane().setCursor(invcurs);
	    	   }
	    	});
	    
	    frame.getContentPane().setCursor(invcurs);
	    frame.getContentPane().setBackground( Color.BLACK );
	    
	    final byte[] pixels =((DataBufferByte) image.getRaster().getDataBuffer()).getData();

       label.setIcon(new ImageIcon(image));
       frame.getContentPane().add(label,BorderLayout.CENTER);
       frame.setLocationRelativeTo(null);
       frame.pack();
       frame.setVisible(true);
	   
       centralx=frame.getX()+(width2/2);
       centraly=frame.getY()+(height2/2);
       
       
	    while(true)
	    {
	    	Thread.sleep(10);
	    	if(gg) nbframe++;
	    	
	    	if(focus)
	    	{
			    	if(convind(currentini)==fin)
			    	{
			    		gg=true;
			    	}
			    	if(resetcm)
			    	{
			    		for(i=0;i<n;i++) for(j=0;j<n;j++) if(i==j) x[i][j]=1; else x[i][j]=0;
			    		resetcm=false;
			    	}
			    	if(resetcm2)
			    	{
			    		for(i=0;i<n;i++) for(j=0;j<n;j++) if(i==j) x[i][j]=1; else x[i][j]=0;
			    		for(i=0;i<n;i++) x[n-1][i]=1f/(float)Math.sqrt(n);
			    		for(i=n-2;i>=0;i--)
			    		{
			    			for(j=i+1;j<n;j++)
			    			{
			    				x[i]= vecsub(x[i],svec(x[j],vecprod(x[i],x[j])));
			    			}
			    			x[i]=vecnorm(x[i]);
			    		}
			    		resetcm2=false;
			    	}
			    	if(resettot)
			    	{
			    		for(i=0;i<n;i++) {pos[i]=0.5f; for(j=0;j<n;j++) if(i==j) x[i][j]=1; else x[i][j]=0;}
			    		resettot=false;
			    	}
		    		if(holdw)
		    		{
		    			fblock=true;
		    			for(i=0;i<n;i++)
		 				{
		 					current[i]=currentini[i];
		 					ngt[i]=(2*(Float.floatToIntBits(x[0][i])>>>31)-1)*-1;
		 					ngt2[i]=((Float.floatToIntBits(x[0][i])>>>31)+1)%2;
		 					inv[i]=1/x[0][i];
		 					coll[i]=inv[i]*((current[i]+ngt2[i])-pos[i]);
		 				}
		 				
		 				
		 				if(coll[0]<coll[1]) min=0;
		 				else min=1;
		 				for(i=2;i<n;i++) if(coll[i]<coll[min]) min=i;
		 				
		 				for(i=0;i<n;i++) index[i]=current[i];
		 				
		 				index[min]+=ngt2[min];
		 				
		 				while(coll[min]<0.05f)
		 				{
		 					if(!bounds[min][convind(index)])
		 					{
		 						fblock=false;
		 						coll[min]+=1.0f;
		 					}
		 					else
		 					{
			 					current[min]+=ngt[min];
			 					index[min]+=ngt[min];
			 					index[min]-=ngt2[min];
			 					
			 					coll[min]+=1.0;
			 					
			 					if(coll[0]<coll[1]) min=0;
			 					else min=1;
			 					for(i=2;i<n;i++) if(coll[i]<coll[min]) min=i;
			 					
			 					index[min]+=ngt2[min];
		 					}
		 				}
		 				
		    			if(fblock) for(i=0;i<n;i++) pos[i]+=x[0][i]/100.0;
		    		}
		    		if(holds)
		    		{
		    			fblock=true;
		    			for(i=0;i<n;i++)
		 				{
		 					current[i]=currentini[i];
		 					ngt[i]=2*(Float.floatToIntBits(x[0][i])>>>31)-1;
		 					ngt2[i]=(Float.floatToIntBits(x[0][i])>>>31)%2;
		 					inv[i]=-1/x[0][i];
		 					coll[i]=inv[i]*((current[i]+ngt2[i])-pos[i]);
		 				}
		 				
		 				
		 				if(coll[0]<coll[1]) min=0;
		 				else min=1;
		 				for(i=2;i<n;i++) if(coll[i]<coll[min]) min=i;
		 				
		 				for(i=0;i<n;i++) index[i]=current[i];
		 				
		 				index[min]+=ngt2[min];
		 				
		 				while(coll[min]<0.05)
		 				{
		 					if(!bounds[min][convind(index)])
		 					{
		 						fblock=false;
		 						coll[min]+=1.0;
		 					}
		 					else
		 					{
			 					current[min]+=ngt[min];
			 					index[min]+=ngt[min];
			 					index[min]-=ngt2[min];
			 					
			 					coll[min]+=1.0;
			 					
			 					if(coll[0]<coll[1]) min=0;
			 					else min=1;
			 					for(i=2;i<n;i++) if(coll[i]<coll[min]) min=i;
			 					
			 					index[min]+=ngt2[min];
		 					}
		 				}
		 				
		    			if(fblock) for(i=0;i<n;i++) pos[i]-=x[0][i]/100.0;
		    		}
		    		if(holda)
		    		{
		    			fblock=true;
		    			for(i=0;i<n;i++)
		 				{
		 					current[i]=currentini[i];
		 					ngt[i]=2*(Float.floatToIntBits(x[1][i])>>>31)-1;
		 					ngt2[i]=(Float.floatToIntBits(x[1][i])>>>31)%2;
		 					inv[i]=-1/x[1][i];
		 					coll[i]=inv[i]*((current[i]+ngt2[i])-pos[i]);
		 				}
		 				
		 				
		 				if(coll[0]<coll[1]) min=0;
		 				else min=1;
		 				for(i=2;i<n;i++) if(coll[i]<coll[min]) min=i;
		 				
		 				for(i=0;i<n;i++) index[i]=current[i];
		 				
		 				index[min]+=ngt2[min];
		 				
		 				while(coll[min]<0.05)
		 				{
		 					if(!bounds[min][convind(index)])
		 					{
		 						fblock=false;
		 						coll[min]+=1.0;
		 					}
		 					else
		 					{
			 					current[min]+=ngt[min];
			 					index[min]+=ngt[min];
			 					index[min]-=ngt2[min];
			 					
			 					coll[min]+=1.0;
			 					
			 					if(coll[0]<coll[1]) min=0;
			 					else min=1;
			 					for(i=2;i<n;i++) if(coll[i]<coll[min]) min=i;
			 					
			 					index[min]+=ngt2[min];
		 					}
		 				}
		    			if(fblock) for(i=0;i<n;i++) pos[i]-=x[1][i]/100.0;
		    		}
		    		if(holdd) 
		    		{
		    			fblock=true;
		    			for(i=0;i<n;i++)
		 				{
		 					current[i]=currentini[i];
		 					ngt[i]=(2*(Float.floatToIntBits(x[1][i])>>>31)-1)*-1;
		 					ngt2[i]=((Float.floatToIntBits(x[1][i])>>>31)+1)%2;
		 					inv[i]=1/x[1][i];
		 					coll[i]=inv[i]*((current[i]+ngt2[i])-pos[i]);
		 				}
		 				
		 				
		 				if(coll[0]<coll[1]) min=0;
		 				else min=1;
		 				for(i=2;i<n;i++) if(coll[i]<coll[min]) min=i;
		 				
		 				for(i=0;i<n;i++) index[i]=current[i];
		 				
		 				index[min]+=ngt2[min];
		 				
		 				while(coll[min]<0.05)
		 				{
		 					if(!bounds[min][convind(index)])
		 					{
		 						fblock=false;
		 						coll[min]+=1.0;
		 					}
		 					else
		 					{
			 					current[min]+=ngt[min];
			 					index[min]+=ngt[min];
			 					index[min]-=ngt2[min];
			 					
			 					coll[min]+=1.0;
			 					
			 					if(coll[0]<coll[1]) min=0;
			 					else min=1;
			 					for(i=2;i<n;i++) if(coll[i]<coll[min]) min=i;
			 					
			 					index[min]+=ngt2[min];
		 					}
		 				}
		    			
		    			if(fblock) for(i=0;i<n;i++) pos[i]+=x[1][i]/100.0;
		    		}
		    		
		    		
		    		
		    		mousx=MouseInfo.getPointerInfo().getLocation().x-centralx;
		    		mousy=MouseInfo.getPointerInfo().getLocation().y-centraly;
		    		
		    		if(mousx>0)
		    		{
		    			
		    			if(holdz || holdx || holdc || holdv || holdb || holdn || holdm || holdk || holdl)
		    			{
		    				//
		    			}
		    			else
		    			{
			    			for(i=0;i<n;i++)
			    			{
			    				newx[0][i]=x[0][i]*rcos+rsin*x[1][i];
			    				newx[1][i]=x[1][i]*rcos-rsin*x[0][i];
			    				x[0][i]=newx[0][i];
			    				x[1][i]=newx[1][i];
			    			}
		    			}
		    		}
		    		else if(mousx<0)
		    		{
		    			
		    			if(holdz || holdx || holdc || holdv || holdb || holdn || holdm || holdk || holdl)
		    			{
		    				//
		    			}
		    			else
		    			{
			    			for(i=0;i<n;i++)
			    			{
			    				newx[0][i]=x[0][i]*rcos-rsin*x[1][i];
			    				newx[1][i]=x[1][i]*rcos+rsin*x[0][i];
			    				x[0][i]=newx[0][i];
			    				x[1][i]=newx[1][i];
			    			}
		    			}
		    		}
		    		
		    		if(mousy<0)
		    		{
		    			if(holdz) tmpm=3;
		    			else if(holdx) tmpm=4;
		    			else if(holdc) tmpm=5;
		    			else if(holdv) tmpm=6;
		    			else if(holdb) tmpm=7;
		    			else if(holdn) tmpm=8;
		    			else if(holdm) tmpm=9;
		    			else if(holdk) tmpm=10;
		    			else if(holdl) tmpm=11;
		    			else tmpm=2;
		    		
			    			for(i=0;i<n;i++)
			    			{
			    				newx[0][i]=x[0][i]*rcos+rsin*x[tmpm][i];
			    				newx[tmpm][i]=x[tmpm][i]*rcos-rsin*x[0][i];
			    				x[0][i]=newx[0][i];
			    				x[tmpm][i]=newx[tmpm][i];
			    			}
		    			
		    		}
		    		else if(mousy>0)
		    		{
		    			if(holdz) tmpm=3;
		    			else if(holdx) tmpm=4;
		    			else if(holdc) tmpm=5;
		    			else if(holdv) tmpm=6;
		    			else if(holdb) tmpm=7;
		    			else if(holdn) tmpm=8;
		    			else if(holdm) tmpm=9;
		    			else if(holdk) tmpm=10;
		    			else if(holdl) tmpm=11;
		    			else tmpm=2;
		    			
			    			for(i=0;i<n;i++)
			    			{
			    				newx[0][i]=x[0][i]*rcos-rsin*x[tmpm][i];
			    				newx[tmpm][i]=x[tmpm][i]*rcos+rsin*x[0][i];
			    				x[0][i]=newx[0][i];
			    				x[tmpm][i]=newx[tmpm][i];
			    			}
		    			
		    		}
			    	 
			    	currentpix=0;
			    	for(i=0;i<n;i++)
			    	{
			    		vec[i]=dist*x[0][i]+multy*x[1][i]+multz*x[2][i];
			    		addy[i]=sqsz*x[1][i];
			    		addz[i]=msqsz*x[2][i];
			    		current[i]=(int)pos[i];
			    		currentini[i]=current[i];
			    	}
			   
				    	
			 	    for(ph=0;ph<height;ph++)
			 	    {
			 	    	for(i=0;i<n;i++) vectmp[i]=vec[i];
			 	    	for(pw=0;pw<width;pw++)
			 	    	{
			 	    		if(gg) {
			 	    		for(i=0;i<n;i++)
			 	    		{
			 	    			vec[i]+=(1-(float)Math.cos(0.1*nbframe))*0.02f*(float)Math.sin(0.5*0.25*(float)nbframe+0.4*(float)pw/6f)*x[1][i];
			 	    			vec[i]+=(1-(float)Math.cos(0.1*nbframe))*0.02f*(float)Math.sin(0.5*0.25*(float)nbframe+0.25*(float)ph/6f)*x[2][i];
			 	    			vec[i]+=(1-(float)Math.cos(0.1*nbframe))*0.02f*(float)Math.sin(0.5*0.25*(float)nbframe+0.6*(float)ph/6f)*x[3][i];
			 	    			vec[i]+=(1-(float)Math.cos(0.1*nbframe))*0.02f*(float)Math.sin(0.5*0.25*(float)nbframe+0.7*(float)pw/6f)*x[4][i];
			 	    		}
			 	    		}
			 	    		
			 	    		
			 	    		for(i=0;i<n;i++)
			 				{
			 					vecn[i]=vec[i]*vecl[ph][pw];
			 					current[i]=currentini[i];
			 					ngt[i]=(2*(Float.floatToIntBits(vecn[i])>>>31)-1)*-1;
			 					ngt2[i]=((Float.floatToIntBits(vecn[i])>>>31)+1)%2;
			 					inv[i]=1/vecn[i];
			 					coll[i]=inv[i]*((current[i]+ngt2[i])-pos[i]);
			 					inv[i]*=ngt[i];
			 				}
			 				
			 				
			 				if(coll[0]<coll[1]) min=0;
			 				else min=1;
			 				for(i=2;i<n;i++) if(coll[i]<coll[min]) min=i;
			 				
			 				for(i=0;i<n;i++) index[i]=current[i];
			 				
			 				index[min]+=ngt2[min];
			 				
			 	
			 				
			 				while(bounds[min][convind(index)])
			 				{
			 					current[min]+=ngt[min];
			 					index[min]+=ngt[min];
			 					index[min]-=ngt2[min];
			 					
			 					coll[min]+=inv[min];
			 					
			 					if(coll[0]<coll[1]) min=0;
			 					else min=1;
			 					for(i=2;i<n;i++) if(coll[i]<coll[min]) min=i;
				 				
			 					
			 					index[min]+=ngt2[min];
			 				}

			 				
			 				
			 				tmp=convind(current);

			 				if(tmp==fin)
			 				{
			 					tmp2=0;
			 					for(i=0;i<n;i++) 
			 					{
			 						if(i!=min) tmp2+=Math.floor(7*(vecn[i]*coll[min]+pos[i]));
			 					}

			 					if(tmp2%2==0)
			 					{
			 						if(coll[min]>2.55) pixels[currentpix]=(byte)0;
					 	    		else pixels[currentpix]=(byte)(255-100*coll[min]);
					 	    		currentpix++;
					 	    		pixels[currentpix]=(byte)255;
					 	    		currentpix++;
					 	    		pixels[currentpix]=(byte)255;
					 	    		currentpix++;
					 	    		pixels[currentpix]=(byte)255;
					 	    		currentpix++;
			 					}
			 					else
			 					{
			 						if(coll[min]>2.55) pixels[currentpix]=(byte)0;
					 	    		else pixels[currentpix]=(byte)(255-100*coll[min]);
					 	    		currentpix++;
					 	    		pixels[currentpix]=(byte)0;
					 	    		currentpix++;
					 	    		pixels[currentpix]=(byte)0;
					 	    		currentpix++;
					 	    		pixels[currentpix]=(byte)0;
					 	    		currentpix++;
			 					}
			 					
			 				}
			 				else
			 				{
				 	    		if(coll[min]>2.55) pixels[currentpix]=(byte)0;
				 	    		else pixels[currentpix]=(byte)(255-100*coll[min]);
				 	    		currentpix++;
				 	    		pixels[currentpix]=colors[tmp][0];
				 	    		currentpix++;
				 	    		pixels[currentpix]=colors[tmp][1];
				 	    		currentpix++;
				 	    		pixels[currentpix]=colors[tmp][2];
				 	    		currentpix++;
			 				}
			 	    		
			 				if(gg) {
			 				for(i=0;i<n;i++)
			 	    		{
			 	    			vec[i]-=(1-(float)Math.cos(0.1*nbframe))*0.02f*(float)Math.sin(0.5*0.25*(float)nbframe+0.4*(float)pw/6f)*x[1][i];
			 	    			vec[i]-=(1-(float)Math.cos(0.1*nbframe))*0.02f*(float)Math.sin(0.5*0.25*(float)nbframe+0.25*(float)ph/6f)*x[2][i];
			 	    			vec[i]-=(1-(float)Math.cos(0.1*nbframe))*0.02f*(float)Math.sin(0.5*0.25*(float)nbframe+0.6*(float)ph/6f)*x[3][i];
			 	    			vec[i]-=(1-(float)Math.cos(0.1*nbframe))*0.02f*(float)Math.sin(0.5*0.25*(float)nbframe+0.7*(float)pw/6f)*x[4][i];
			 	    		}
			 				}
			 	    		
			 	    		for(i=0;i<n;i++) vec[i]+=addy[i];
			 	    	}
			 	    	
			 	    	for(i=0;i<n;i++) vec[i]=vectmp[i]+addz[i];
			 	    }
			 	    
			 	    tmp=4*(171*320+233);
			 	    for(j=0;j<9;j++)
			 	    {
				 	    tmp2=tmp;
				 	    for(i=0;i<86;i++)
				 	    {
				 	    	pixels[tmp2]=0;
				 	    	tmp2++;
				 	    	pixels[tmp2]=0;
				 	    	tmp2++;
				 	    	pixels[tmp2]=0;
				 	    	tmp2++;
				 	    	pixels[tmp2]=(byte)255;
				 	    	tmp2++;
				 	    }
				 	    tmp+=320*4;
			 	    }
			 	    
			 	   tmp=4*(173*320+235);
			 	    for(i=0;i<n;i++)
			 	    {
			 	    	if(pos[i]<1)
			 	    	{
			 	    		tmp2=tmp;
			 	    		for(j=0;j<5;j++)
			 	    		{
			 	    			tmp3=tmp2;
			 	    			for(k=0;k<5;k++)
			 	    			{
			 	    				pixels[tmp3]=(byte)255;
						 	    	tmp3++;
						 	    	pixels[tmp3]=(byte)255;
						 	    	tmp3++;
						 	    	pixels[tmp3]=(byte)255;
						 	    	tmp3++;
						 	    	pixels[tmp3]=(byte)255;
						 	    	tmp3++;
			 	    			}
			 	    			tmp2+=320*4;
			 	    		}
			 	    	}
			 	    	else
			 	    	{
			 	    		tmp2=tmp;
			 	    		for(j=0;j<5;j++)
			 	    		{
			 	    			tmp3=tmp2;
			 	    			for(k=0;k<5;k++)
			 	    			{
			 	    				pixels[tmp3]=(byte)255;
						 	    	tmp3++;
						 	    	pixels[tmp3]=(byte)0;
						 	    	tmp3++;
						 	    	pixels[tmp3]=(byte)255;
						 	    	tmp3++;
						 	    	pixels[tmp3]=(byte)0;
						 	    	tmp3++;
			 	    			}
			 	    			tmp2+=320*4;
			 	    		}
			 	    	}
			 	    	tmp+=4*7;
			 	    }
			 	    
			 	    if(gg)
			 	    {
			 	    	pixels[4*(2*320+5)]=(byte)0;
			 	    	pixels[4*(2*320+6)]=(byte)0;
			 	    	pixels[4*(2*320+7)]=(byte)0;
			 	    	pixels[4*(3*320+4)]=(byte)0;
			 	    	pixels[4*(3*320+5)]=(byte)0;
			 	    	pixels[4*(3*320+7)]=(byte)0;
			 	    	pixels[4*(3*320+8)]=(byte)0;
			 	    	pixels[4*(4*320+2)]=(byte)0;
			 	    	pixels[4*(4*320+3)]=(byte)0;
			 	    	pixels[4*(4*320+4)]=(byte)0;
			 	    	pixels[4*(4*320+8)]=(byte)0;
			 	    	pixels[4*(4*320+9)]=(byte)0;
			 	    	pixels[4*(4*320+10)]=(byte)0;
			 	    	pixels[4*(5*320+2)]=(byte)0;
			 	    	pixels[4*(5*320+10)]=(byte)0;
			 	    	pixels[4*(6*320+2)]=(byte)0;
			 	    	pixels[4*(6*320+3)]=(byte)0;
			 	    	pixels[4*(6*320+4)]=(byte)0;
			 	    	pixels[4*(6*320+8)]=(byte)0;
			 	    	pixels[4*(6*320+9)]=(byte)0;
			 	    	pixels[4*(6*320+10)]=(byte)0;
			 	    	pixels[4*(7*320+3)]=(byte)0;
			 	    	pixels[4*(7*320+6)]=(byte)0;
			 	    	pixels[4*(7*320+9)]=(byte)0;
			 	    	pixels[4*(8*320+3)]=(byte)0;
			 	    	pixels[4*(8*320+5)]=(byte)0;
			 	    	pixels[4*(8*320+6)]=(byte)0;
			 	    	pixels[4*(8*320+7)]=(byte)0;
			 	    	pixels[4*(8*320+9)]=(byte)0;
			 	    	pixels[4*(9*320+3)]=(byte)0;
			 	    	pixels[4*(9*320+4)]=(byte)0;
			 	    	pixels[4*(9*320+5)]=(byte)0;
			 	    	pixels[4*(9*320+7)]=(byte)0;
			 	    	pixels[4*(9*320+8)]=(byte)0;
			 	    	pixels[4*(9*320+9)]=(byte)0;
			 	    	
			 	    	pixels[4*(3*320+6)]=(byte)255;
			 	    	pixels[4*(3*320+6)+1]=(byte)0;
			 	    	pixels[4*(3*320+6)+2]=(byte)255;
			 	    	pixels[4*(3*320+6)+3]=(byte)255;
			 	    	
			 	    	pixels[4*(4*320+5)]=(byte)255;
			 	    	pixels[4*(4*320+5)+1]=(byte)0;
			 	    	pixels[4*(4*320+5)+2]=(byte)255;
			 	    	pixels[4*(4*320+5)+3]=(byte)255;

			 	    	pixels[4*(4*320+6)]=(byte)255;
			 	    	pixels[4*(4*320+6)+1]=(byte)0;
			 	    	pixels[4*(4*320+6)+2]=(byte)255;
			 	    	pixels[4*(4*320+6)+3]=(byte)255;

			 	    	pixels[4*(4*320+7)]=(byte)255;
			 	    	pixels[4*(4*320+7)+1]=(byte)0;
			 	    	pixels[4*(4*320+7)+2]=(byte)255;
			 	    	pixels[4*(4*320+7)+3]=(byte)255;
			 	    	
			 	    	for(i=3;i<10;i++) {
			 	    	pixels[4*(5*320+i)]=(byte)255;
			 	    	pixels[4*(5*320+i)+1]=(byte)0;
			 	    	pixels[4*(5*320+i)+2]=(byte)255;
			 	    	pixels[4*(5*320+i)+3]=(byte)255;}
			 	    	
			 	    	for(i=5;i<8;i++) {
				 	    	pixels[4*(6*320+i)]=(byte)255;
				 	    	pixels[4*(6*320+i)+1]=(byte)0;
				 	    	pixels[4*(6*320+i)+2]=(byte)255;
				 	    	pixels[4*(6*320+i)+3]=(byte)255;}
			 	    	
			 	    	for(i=4;i<6;i++) {
				 	    	pixels[4*(7*320+i)]=(byte)255;
				 	    	pixels[4*(7*320+i)+1]=(byte)0;
				 	    	pixels[4*(7*320+i)+2]=(byte)255;
				 	    	pixels[4*(7*320+i)+3]=(byte)255;}
			 	    	
			 	    	for(i=7;i<8;i++) {
				 	    	pixels[4*(7*320+i)]=(byte)255;
				 	    	pixels[4*(7*320+i)+1]=(byte)0;
				 	    	pixels[4*(7*320+i)+2]=(byte)255;
				 	    	pixels[4*(7*320+i)+3]=(byte)255;}
			 	    	
			 	    	pixels[4*(8*320+4)]=(byte)255;
			 	    	pixels[4*(8*320+4)+1]=(byte)0;
			 	    	pixels[4*(8*320+4)+2]=(byte)255;
			 	    	pixels[4*(8*320+4)+3]=(byte)255;
			 	    	
			 	    	pixels[4*(8*320+8)]=(byte)255;
			 	    	pixels[4*(8*320+8)+1]=(byte)0;
			 	    	pixels[4*(8*320+8)+2]=(byte)255;
			 	    	pixels[4*(8*320+8)+3]=(byte)255;

			 	    }

			       label.setIcon(new ImageIcon(image));
			       frame.getContentPane().add(label,BorderLayout.CENTER);
			       robot.mouseMove(centralx,centraly);
	    	}
	    }
	}
	
	public static float vecprod(float[] v1, float[] v2)
	{
		float ret=0;
		for(int i=0;i<n;i++) ret+=v1[i]*v2[i];
		return ret;
	}
	
	public static float[] svec(float[] v, float s)
	{
		float[] ret = new float[n];
		for(int i=0;i<n;i++) ret[i]=v[i]*s;
		return ret;
	}
	
	public static float[] vecsub(float[] v1,float[] v2)
	{
		float[] ret = new float[n];
		for(int i=0;i<n;i++) ret[i]=v1[i]-v2[i];
		return ret;
	}
	
	public static float veclgt(float[] v)
	{
		return (float)Math.sqrt(vecprod(v,v));
	}
	
	public static float[] vecnorm(float[] v)
	{
		float prod = veclgt(v);
		float[] ret = new float[n];
		for(int i=0;i<n;i++) ret[i]=v[i]/prod;
		return ret;
	}
	
	public static void displayvec(int[] v)
	{
		int i;
		for(i=0;i<n-1;i++) System.out.print(v[i]+",");
		System.out.println(v[i]);
	}
	
	public static int convind(int[] index)
	{
		int ret=0;
		int mult=1;
		
		for(int i=0;i<n;i++)
		{
			ret+=index[i]*mult;
			mult*=3;
		}
		
		return ret;
	}
	
	public static int[] invind(int index)
	{
		int tmp;
		int[] ret=new int[n];
		
		for(int i=0;i<n;i++)
		{
			tmp=index%3;
			ret[i]=tmp;
			index-=tmp;
			index/=3;
		}
		
		return ret;
	}
	
	
	public static int fact(int n)
	{
		int ret=1;
		for(int i=2;i<=n;i++) ret*=i;
		return ret;
	}
	
	 public static int[] perm(int n)  
	 {  
		  int i,j;
		  boolean newn;
	      int[] ret = new int[n];  
	     
	      ret[0]=(int)(Math.random()*n);
	      
	      for(i=1;i<n;i++)
	      {
	    	  do {
	    	  ret[i]=(int)(Math.random()*n);
	    	  newn=false;
	    	  for(j=0;j<i;j++)
	    	  {
	    		  if(ret[i]==ret[j]) newn=true;
	    	  }
	    	  } while(newn);
	      }
	      
	      return ret;  
	 } 
	
	@Override
	public void focusLost(FocusEvent e) {
        focus=false;
        frame.getContentPane().setCursor(Cursor.getDefaultCursor());
    }
	
	public M12D() {
        addKeyListener(this);
        addFocusListener(this);
    }
	
	public void addNotify() {
        super.addNotify();
        requestFocus();
    }
	
	@Override
	public void keyPressed(KeyEvent e) { }
	@Override
	public void keyReleased(KeyEvent e) {
		c = e.getKeyChar();
		if(c==119) {holdw=false;}
		else if(c==97) {holda=false;}
		else if(c==115) {holds=false;}
		else if(c==100) {holdd=false;}
		else if(c==122) {holdz=false;}
		else if(c==120) {holdx=false;}
		else if(c==99) {holdc=false;}
		else if(c==118) {holdv=false;}
		else if(c==98) {holdb=false;}
		else if(c==110) {holdn=false;}
		else if(c==109) {holdm=false;}
		else if(c==107) {holdk=false;}
		else if(c==108) {holdl=false;}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		c = e.getKeyChar();
		if(c==27) { focus=false;frame.getContentPane().setCursor(Cursor.getDefaultCursor());}
		else if(c==119) {holdw=true;}
		else if(c==97) {holda=true;}
		else if(c==115) {holds=true;}
		else if(c==100) {holdd=true;}
		else if(c==121) {resetcm=true;}
		else if(c==112) {resettot=true;}
		else if(c==117) {resetcm2=true;}
		else if(c==122) {holdz=true;}
		else if(c==120) {holdx=true;}
		else if(c==99) {holdc=true;}
		else if(c==118) {holdv=true;}
		else if(c==98) {holdb=true;}
		else if(c==110) {holdn=true;}
		else if(c==109) {holdm=true;}
		else if(c==107) {holdk=true;}
		else if(c==108) {holdl=true;}
	}

	@Override
	public void focusGained(FocusEvent arg0) {}
}