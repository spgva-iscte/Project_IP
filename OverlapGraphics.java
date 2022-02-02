class OverlapGraphics {
	Graphic[] graphics;
	
// Parte 3       //
	
	OverlapGraphics(Graphic[] graphics){
		this.graphics=graphics;
	}
	
	OverlapGraphics(Graphic graphic){
		Graphic[] graphics=new Graphic[0];
		graphics[0]=graphic;
		this.graphics=graphics;
	}
	
// Função que cria uma pilha de gráficos  //
	
	OverlapGraphics pile(){
		OverlapGraphics n=new OverlapGraphics(graphics);
		int notNull=0;
		for(int i=0;i<graphics.length;i++)
			if(graphics[i]!=null)
				notNull++;
		Graphic[] graphics1=new Graphic[notNull];
		int a=0;
		for(int i=0;i<graphics.length;i++)
			if(graphics[i]!=null){
				graphics1[a]=graphics[i];
				a++;
			}
		n.graphics=graphics1;
		return n;
	}
	
// Função que adiciona um gráfico ao topo da pilha de gráficos     //
	
	void addTop(Graphic graphic){
		Graphic[] graphics1=new Graphic[this.pile().graphics.length+1];
		for(int i=0;i<graphics1.length;i++)
			if(i<this.pile().graphics.length)
				graphics1[i]=this.pile().graphics[i];
			else
				graphics1[i]=graphic;
		this.graphics=graphics1;
	}
	
// Função que remove o gráfico que está no topo da pilha        //
	
	void remove(){
		Graphic[] graphics1=new Graphic[this.pile().graphics.length-1];
		for(int i=0;i<graphics1.length;i++)
			graphics1[i]=this.pile().graphics[i];
		this.graphics=graphics1;
	}
	
// Função que devolve o grafico que está no topo da pilha de gráficos    //
	
	Graphic top(){
		ColorImage img=this.graphics[this.pile().graphics.length-1].image();
		String tittle=this.graphics[this.pile().graphics.length-1].tittle;
		String tittleX=this.graphics[this.pile().graphics.length-1].tittleX;
		String tittleY=this.graphics[this.pile().graphics.length-1].tittleY;
		return new Graphic(img,tittle,tittleX,tittleY) ;
	}
	
// Função que adiciona um grafico a uma certa posição da pilha    //
	
	void add(Graphic graphic,int position){
		Graphic[] graphics1=new Graphic[this.pile().graphics.length+1];
		for(int i=0;i<graphics1.length;i++){
			if(i<position)
				graphics1[i]=this.pile().graphics[i];
			if(i==position)
				graphics1[i]=graphic;
			if(i>position)
				graphics1[i]=this.pile().graphics[i-1];
		}
		this.graphics=graphics1;
	}
	
// Função que troca de posição dois gráficos //
	
	void switchPositions(int position1,int position2){
		Graphic[] graphics1=new Graphic[this.pile().graphics.length];
		for(int i=0;i<graphics1.length;i++){
			if(i==position1)
				graphics1[i]=this.pile().graphics[position2];
			else{
				if(i==position2)
					graphics1[i]=this.pile().graphics[position1];
				else
					graphics1[i]=this.pile().graphics[i];
			}
		}
		this.graphics=graphics1;
	}
	
// Função que remove o titutlo aos gráficos    //
	
	void noTittle(){
		Graphic[] graphics1=new Graphic[this.graphics.length];
		for(int i=0;i<graphics1.length;i++){
			graphics[i].tittle=null;
			graphics1[i]=graphics[i];
		}
		this.graphics=graphics1;
	}
	
// Função que ordena os graficos por ordem alfabética dos títulos   //
	
	void alphabeticalOrder(){
		Graphic[] graphics1=this.pile().graphics;
		for(int j=0;j<graphics1.length;j++)
			for(int i=0;i<graphics1.length-1;i++){
				if(graphics1[i].tittle==null){
					Graphic g=graphics1[graphics1.length-1];
					graphics1[graphics1.length-1]=graphics1[i];
					for(int j1=i;j1<graphics1.length-2;j1++){
						graphics1[j1]=graphics[j1+1];
						graphics[graphics.length-2]=g;
					}
				}else{
					if(graphics1[i].tittle.compareToIgnoreCase(graphics1[i+1].tittle)<=0)
						graphics1[i]=graphics1[i];
					else{
						Graphic g=graphics1[i];
						graphics1[i]=graphics1[i+1];
						graphics1[i+1]=g;
					}
				}
			}
		this.graphics=graphics1;
	}
	
// Função que devolve a imagem da sobreposição dos gráficos da pilha //
	
	ColorImage overlap(){
		int height=0;
		int width=0;
		for(int i=0;i<this.pile().graphics.length;i++)
			if(height<this.pile().graphics[i].graphic.getHeight())
				height=this.pile().graphics[i].graphic.getHeight();
		for(int i=0;i<this.pile().graphics.length;i++)
			if(width<this.pile().graphics[i].graphic.getWidth())
				width=this.pile().graphics[i].graphic.getWidth();
		ColorImage overlap=new ColorImage(width,height);
		for(int i=0;i<this.pile().graphics.length;i++)
			for(int x=0;x<this.pile().graphics[i].graphic.getWidth();x++)
				for(int y=0;y<this.pile().graphics[i].graphic.getHeight();y++){
					Color c=this.pile().graphics[i].graphic.getColor(x,y);
					if(c.getLuminance()==Color.Black.getLuminance())
						if(overlap.getColor(x,y+(height-this.pile().graphics[i].graphic.getHeight())).getLuminance()==Color.Black.getLuminance())
							overlap.setColor(x,y+(height-this.pile().graphics[i].graphic.getHeight()),Color.Black);
						else
							overlap.setColor(x,y+(height-this.pile().graphics[i].graphic.getHeight()),overlap.getColor(x,y+(height-this.pile().graphics[i].graphic.getHeight())));
					else
						overlap.setColor(x,y+(height-this.pile().graphics[i].graphic.getHeight()),this.pile().graphics[i].graphic.getColor(x,y));
				}
		return overlap;
	}
	
// Função que devolve a imagem rodada 90º no sentido horário da imagem que reulta da sobreposição
// dos gráficos da pilha   //
	
	ColorImage overlapRotation(){
		ColorImage img=ColorGraphics.rotation(this.pile().overlap());
		return img;
	}
	
// Testes //
	
	static void testPart3(){
		int[] v={50,60,70,80};
		int width=15;
		int width1=10;
		int space=5;
		int space1=5;
		int radius=7;
		Color color=new Color(200,60,60);
		Color color1=new Color(80,200,80);
		Color color2=new Color(60,60,200);
		ColorImage img=ColorGraphics.columns2D(v,width,space,color);
		ColorImage img2=ColorGraphics.columns2D(v,width1,space1,color2);
		ColorImage img1=ColorGraphics.scatterPlot(v,radius,space,color1);
		Graphic graphic=new Graphic(img,"tittle","tittleX","tittleY");
		Graphic graphic1=new Graphic(img1,null,"tituloX","tituloY");
		Graphic graphic2=new Graphic(img2,"titular","titularX","titularY");
		Graphic[] graphics=new Graphic[3];
		graphics[0]=graphic;
		graphics[1]=null;
		graphics[2]=graphic1;
		graphic1.transparent();
		OverlapGraphics n=new OverlapGraphics(graphics);
		n.pile();
		n.addTop(graphic2);
		n.remove();
		n.top();
		n.add(graphic2,1);
		n.switchPositions(2,0);
		n.noTittle();
		n.alphabeticalOrder();
		n.overlap();
		n.overlapRotation();
	}

}