class ColorGraphics {
	
// Parte 1  //
	
// Função auxiliar que calcula o valor máximo existente em um vetor  // 
// Esta função é utilizada para saber qual o height da imagem do gráfico  //
	
	static int valueMax(int[] v){
		int Max=v[0];
		for(int i=0; i<v.length;i++)
			if(v[i]>Max)
				Max=v[i];
		return Max;
	}
  
// Função que devolve a imagem de um grafico de colunas//
	
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
  
// Função auxiliar que devolve uma nova cor  //
// Na função Blurred são usadas diferentes cores para dar a ideia de suavizar os limites 
// de uma coluna. Esta função certifica-se que são sempre que todas as cores são válidas, ou seja,
// os valores que compõem a cor estão no intrevlao [0,255]. //
  
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
  
 // Função auxiliar que verifica a tonalidade da cor, (por exemplo se é mais vermelha,azul 
 // ou verde)     //
 // Esta função é utilizada na função Blurred para tentar evitar que a coluna perca largura
 // quando é suavizada   //
  
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
			  
 // Função auxiliar que devolve a cor mais escura  //
 // Esta função é utilizada na função Blurred para que os cantos superirores das colunas
 // também estejam suavizados. //
  
  static Color darker(Color color,Color color1){
	  Color c=new Color(0,0,0);
	  if(color.getLuminance()<color1.getLuminance())
		  c=color;
	  else
		  c=color1;
	  return c;
  }
  
 //Função que devolve uma imagem de um grafico com colunas suavizadas  //
  
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
  
 // Função que devolve a imagem de um gráfico de dispersão  //
  
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
   
 // Função que roda uma imagem de um gráfico 90º no sentido horário  //
 
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