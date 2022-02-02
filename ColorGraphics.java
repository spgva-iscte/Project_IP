class ColorGraphics {
	
// Parte 1  //
	
// Fun��o auxiliar que calcula o valor m�ximo existente em um vetor  // 
// Esta fun��o � utilizada para saber qual o height da imagem do gr�fico  //
	
	static int valueMax(int[] v){
		int Max=v[0];
		for(int i=0; i<v.length;i++)
			if(v[i]>Max)
				Max=v[i];
		return Max;
	}
  
// Fun��o que devolve a imagem de um grafico de colunas//
	
  public static ColorImage columns2D(int[] v,int width,int space, Color color){
	   ColorImage graphic=new ColorImage((v.length*width)+((v.length+1)*space),valueMax(v));   
	   for(int x=0;x<graphic.getWidth();x++)
		   for(int y=0;y<graphic.getHeight();y++){
			   if(x<space)
				   graphic.setColor(x,y,Color.Black);
			   else
				   for(int i=1;i<v.length+1;i++){
					   if(x<(i*width)+(space*i))
							   while(y<graphic.getHeight()){
								   if(y<v[i-1])
									   graphic.setColor(x,graphic.getHeight()-y-1,color);
								   else
									   graphic.setColor(x,graphic.getHeight()-y-1,Color.Black);
								   y++;
							   }
					   else
						   while(x<(i*width)+(space*i)+space){
							   graphic.setColor(x,graphic.getHeight()-y-1,Color.Black);
							   x++;
						   }
				   }
		   }
	   return graphic;
   }
  
// Fun��o auxiliar que devolve uma nova cor  //
// Na fun��o Blurred s�o usadas diferentes cores para dar a ideia de suavizar os limites 
// de uma coluna. Esta fun��o certifica-se que s�o sempre que todas as cores s�o v�lidas, ou seja,
// os valores que comp�em a cor est�o no intrevlao [0,255]. //
  
  static Color newColor(Color color,int i){
	  Color c=new Color(0,0,0);
	  if(color.getR()-i+1>0)
		  if(color.getG()-i+1>0)
			  if(color.getB()-i+1>0)
				  c=new Color(color.getR()-i+1,color.getG()-i+1,color.getB()-i+1);
			  else
				  c=new Color(color.getR()-i+1,color.getG()-i+1,1);
		  else
			  if(color.getB()-i>0)
				  c=new Color(color.getR()-i+1,1,color.getB()-i+1);
			  else
				  c=new Color(color.getR()-i+1,1,1);
	  else
		  if(color.getG()-i>0)
			  if(color.getB()-i>0)
				  c=new Color(1,color.getG()-i+1,color.getB()-i+1);
			  else
				  c=new Color(1,color.getG()-i+1,1);
		  else
			  if(color.getB()-i>0)
				  c=new Color(1,1,color.getB()-i+1);
			  else
				  c=new Color(1,1,1);
	  return c;
  }
  
 // Fun��o auxiliar que verifica a tonalidade da cor, (por exemplo se � mais vermelha,azul 
 // ou verde)     //
 // Esta fun��o � utilizada na fun��o Blurred para tentar evitar que a coluna perca largura
 // quando � suavizada   //
  
  static int tone(Color color){
	  int tone=0;
	  if(color.getR()>color.getG())
		  if(color.getR()>color.getB())
			  tone=color.getR();
		  else
			  tone=color.getB();
	  else
		  if(color.getG()>color.getB())
			  tone=color.getG();
		  else
			  tone=color.getB();
	  return tone;
  }
			  
 // Fun��o auxiliar que devolve a cor mais escura  //
 // Esta fun��o � utilizada na fun��o Blurred para que os cantos superirores das colunas
 // tamb�m estejam suavizados. //
  
  static Color darker(Color color,Color color1){
	  Color c=new Color(0,0,0);
	  if(color.getLuminance()<color1.getLuminance())
		  c=color;
	  else
		  c=color1;
	  return c;
  }
  
 //Fun��o que devolve uma imagem de um grafico com colunas suavizadas  //
  
  static ColorImage columns2DBlurred(int[] v,int width,int space, Color color,int gradient){
	  ColorImage graphic=new ColorImage((v.length*width)+((v.length+1)*space),valueMax(v));
	  for(int x=0; x<graphic.getWidth();x++)
		  for(int y=0;y<graphic.getHeight();y++)
			  if(x<space)
				  graphic.setColor(x,y,Color.Black);
			  else
				  for(int i=1;i<v.length+1;i++){
					  int count=0;
					  while(x<(width*(i-1))+(space*i)+gradient){
						  Color c=newColor(color,tone(color)/(count+1));
						  while(y<v[i-1]-gradient){
							  graphic.setColor(x,graphic.getHeight()-y-1,c);
							  y++;
						  }
						  int count1=gradient;
						  while(y<v[i-1]){
							  Color c1=newColor(color,tone(color)/count1);
							  graphic.setColor(x,graphic.getHeight()-y-1,darker(c,c1));
							  y++;
							  count1--;
						  }
						  while(y<graphic.getHeight()){
							  graphic.setColor(x,graphic.getHeight()-y-1,Color.Black);
							  y++;
						  }
						  count++;
						  x++;
						  y=0;
					  }
					  while(x<(width*i)+(space*i)-gradient){
						  while(y<v[i-1]-gradient){
							  graphic.setColor(x,graphic.getHeight()-y-1,color);
							  y++;
						  }
						  int count1=gradient;
						  while(y<v[i-1]){
							  Color c=newColor(color,tone(color)/count1);
							  graphic.setColor(x,graphic.getHeight()-y-1,c);
							  y++;
							  count1--;
						  }
						  while(y<graphic.getHeight()){
							  graphic.setColor(x,graphic.getHeight()-y-1,Color.Black);
							  y++;
						  }
						  x++;
						  y=0;
					  }
					  int count2=gradient;
					  while(x<(width*i)+(space*i)){
						  Color c=newColor(color,tone(color)/count2);
						  while(y<v[i-1]-gradient){
							  graphic.setColor(x,graphic.getHeight()-y-1,c);
							  y++;
						  }
						  int count1=gradient;
						  while(y<v[i-1]){
							  Color c1=newColor(color,tone(color)/count1);
							  graphic.setColor(x,graphic.getHeight()-y-1,darker(c,c1));
							  y++;
							  count1--;
						  }
						  while(y<graphic.getHeight()){
							  graphic.setColor(x,graphic.getHeight()-y-1,Color.Black);
							  y++;
						  }
						  count2--;
						  x++;
						  y=0;
					  }
					  while(x<(i*width)+(space*i)+space){
						  while(y<graphic.getHeight()){
							  graphic.setColor(x,graphic.getHeight()-y-1,Color.Black);
							  y++;
						  }
						  x++;
						  y=0;
					  }
				  }
	  return graphic;
  }
  
 // Fun��o que devolve a imagem de um gr�fico de dispers�o  //
  
   static ColorImage scatterPlot(int []v,int radius,int space,Color color){
	   ColorImage graphic=new ColorImage(((radius*2)+space)*v.length+space,valueMax(v)+radius);
	   for(int x=0;x<graphic.getWidth();x++)
		   for(int y=0;y<graphic.getHeight();y++)
			   if(x<space)
				   graphic.setColor(x,y,Color.Black);
			   else
				 for(int i=1;i<v.length+1;i++){
					if(x<((radius*2)+space)*i)
						while(y<graphic.getHeight()){
							if((x-((space+radius*2)*(i-1)+radius+space))*(x-((space+radius*2)*(i-1)+radius+space))+(y-(graphic.getHeight()-v[i-1]))*(y-(graphic.getHeight()-v[i-1]))<radius*radius)
								graphic.setColor(x,y,color);
						   	else
						   		graphic.setColor(x,y,Color.Black);
						   	y++;
						 }
					else
						while(x<((radius*2)+space)*i+space){
							graphic.setColor(x,y,Color.Black);
							x++;
						}
				}
	   return graphic;
   }
   
 // Fun��o que roda uma imagem de um gr�fico 90� no sentido hor�rio  //
 
   static ColorImage rotation(ColorImage img){
	   ColorImage img1=new ColorImage(img.getHeight(),img.getWidth());
	   for(int x=0;x<img1.getWidth();x++)
		   for(int y=0;y<img1.getHeight();y++)
			   img1.setColor(x,y,img.getColor(y,img.getHeight()-x-1));
	   return img1;
   }
   
 // Testes  //
   
   static void testPart1(){
		int[] v={100,0,50,70};
		int width= 40;
		int space=30;
		int gradient=12;
		int radius=15;
		Color color=new Color(200,60,60);
		Color color1=new Color(60,60,200);
		Color color2=new Color(60,200,60);
		columns2D(v,width,space,color);
		scatterPlot(v,radius,space,color1);
		columns2DBlurred(v,width,space,color2,gradient);
		rotation(scatterPlot(v,radius,space,color1));
   }

}