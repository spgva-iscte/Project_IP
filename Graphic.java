class Graphic {
	
//   Parte 2     //
	
// Metodos Construtores //
	
	final ColorImage graphic;
	String tittle;
	String tittleX;
	String tittleY;
	
	Graphic(ColorImage graphic){
		this.graphic=graphic;
	}
	
	Graphic(ColorImage graphic,String tittle,String tittleX,String tittleY){
		this.graphic=graphic;
		this.tittle=tittle;
		this.tittleX=tittleX;
		this.tittleY=tittleY;
	}
	
// Função que adiciona ou modifica o titulo do gráfico   //
	
	void tittle(String tittle){
		this.tittle=tittle;
	}
	
// Função que adiciona ou modifica o titulo do eixo das abcissas   //
	
	void tittleX(String tittleX){
		this.tittleX=tittleX;
	}
	
// Função que adiciona ou modifica o titulo do eixo das ordenadas  //
	
	void tittleY(String tittleY){
		this.tittleY=tittleY;
	}
	
// Função que define como transparente    //
	
	void transparent(){
		for(int x=0;x<graphic.getWidth();x++)
			for(int y=0;y<graphic.getHeight();y++){
				if(x%2!=0 && y%2==0)
						graphic.setColor(x,y,Color.Black);
				if(x%2==0 && y%2!=0)
					graphic.setColor(x,y,Color.Black);
			}
	}
	
// Função que devolve a imagem do gráfico   //
	
	ColorImage image(){
		ColorImage c=this.graphic;
		return c;
	}
	
// Função que devolve toda a informação textual do gráfico //	
	
	String writtenInfo(){
		String info=new String();
		info="titulo="+this.tittle+" titulo X="+this.tittleX+" titulo Y="+this.tittleY;
		return info;	
	}
	
//testes//
	
	static void testPart2(){
		int[] v={50,60,70,80};
		int width=15;
		int space=10;
		Color color=new Color(200,60,60);
		ColorImage img=ColorGraphics.columns2D(v,width,space,color);
		Graphic graphic=new Graphic(img);
		graphic.tittle("tittle");
		graphic.tittleX("tittleX");
		graphic.tittleY("tittleY");
		graphic.transparent();
		graphic.image();
		graphic.writtenInfo();
	}

}