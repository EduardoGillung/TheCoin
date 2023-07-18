package INVENTARIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class gameloop extends Thread implements Runnable, ActionListener {
	
	int FPS = 60;
	Timer ControleDoTempoDoJogo;
	long contadorDeFPS;
	painel cenaDoJogo;
	escutarTeclado EscutaTecl;
	
	
	gameloop(painel P, escutarTeclado eT)
	{
		this.cenaDoJogo = P;
		this.EscutaTecl = eT;
		//System.out.println("GAMELOOP INSTANCIADO");
	}
	
	//metodo obrigatario devido a interface  Runnable 
	@Override
	public void run()
	{
		verificadorDeColisao colisao = new verificadorDeColisao(); //cria o objeto para verificar a colisao.
		
		//inicia o contado de prints.
		this.contadorDeFPS = 0;
		
		//intancia o Controle de Tempo do Jogo.
		//o qual vai disparar um evento a cada 1000 milisegundos
		this.ControleDoTempoDoJogo = new  Timer(1000, this);
		
		//inicia o controle do tempo.
		this.ControleDoTempoDoJogo.start();
		
				//define o framerate do jogo sendo 60FPS.
			double FrameRate = 1000000000/this.FPS; //nanosegundos divididos pelo numero de frames.
			//define o tempo decorrido do jogo
				//todas as vezes que o valor desta variavvel passar de 1,
				//a frase deve ser "printada" no console 
			double TempoDecorrido = 0;
			//Armazena o ultimo momento (em Nanosegundos) que o GameLoop aconteceu
			long TempoUltimaMedidaDoLoop = System.nanoTime();
			//vai armazenar o tempo atual do jogo em cada  GameLoop
			long TempoAtualDoLoop;
		while (this.isAlive())
		{
			//Captura o tempo atual do jogo  (em nanosegundos) a cada loop realizado pelo computador.
			TempoAtualDoLoop = System.nanoTime();
			TempoDecorrido = TempoDecorrido + (TempoAtualDoLoop - TempoUltimaMedidaDoLoop)/FrameRate;
			//atualiza o valor do ultimo momento em que o Loop aconteceu
			TempoUltimaMedidaDoLoop = TempoAtualDoLoop;
	
			if (TempoDecorrido >= 1)
			{
				
				
				//atualiza status do componentes do jogo.
				String direcao = "cima";
				if (EscutaTecl.movePraCima) direcao = "cima";
				if (EscutaTecl.movePraBaixo) direcao = "baixo";
				if (EscutaTecl.movePraDir) direcao = "direita";
				if (EscutaTecl.movePraEsq) direcao = "esquerda";
				
				boolean bateu = colisao.VerificaColisao(this.cenaDoJogo.cenario, this.cenaDoJogo.jogador, direcao);
				
				if (bateu == false)
				//ATUALIZA OS STATUS DOS COMPONENTES DO JOGO
				cenaDoJogo.jogador.atualizaPosicao(EscutaTecl.movePraEsq, EscutaTecl.movePraCima, EscutaTecl.movePraDir, EscutaTecl.movePraBaixo);
				
				//TESTE DE COLISAO COM OBJETO
				for (int num_obj = 0; num_obj < this.cenaDoJogo.obj.length; num_obj++)
				{
					//CASO O OBJETO DA CENA ESTEJA VISIVEL 
					if(this.cenaDoJogo.obj[num_obj].visivel)
					{
						if(this.cenaDoJogo.obj[num_obj].cenario == this.cenaDoJogo.cenario.getCenaValida())
						{
							//VERIFICA SE CASO OCORRE A COLISAO DO JOGADOR COM A COLISAO DO OBJETO
							boolean bateuEmObj = colisao.VerificaColisao(this.cenaDoJogo.jogador.areaSolida, this.cenaDoJogo.obj[num_obj].areaSolida);
							//SE CASO BATEU 
							if(bateuEmObj == true)
							{
								//PARA CONFIRMAÇÃO DE QUE O OBJETO RECONHECEU A COLISAO
								System.out.println("bateu");
								//CASO DETECTE RETORNA O VALOR 
								this.cenaDoJogo.obj[num_obj].visivel = false;
								//DETECTA CHAVE QUANDO PEGA NO INVETARIO
								this.cenaDoJogo.jogador.capturaObjeto(this.cenaDoJogo.obj[num_obj].nomeObj);
								System.out.println("NUMERO DE CHAVES : " + this.cenaDoJogo.jogador.invetarioJogador[0].qtdeOjbeto);
								System.out.println("NUMERO DE MOEDAS : " + this.cenaDoJogo.jogador.invetarioJogador[1].qtdeOjbeto);
							}
						}
					}
				}
				//renderiza a cena do jogo
				cenaDoJogo.repaint();
				//System.out.println("O Loop esta ativo");
				//incrementa o contador de prints em uma unidade 
				this.contadorDeFPS ++;
				TempoDecorrido = 0;
			}
		}
	}
	
	//METODO OBRIGATORIO DEVIDO A INTERFACE ACTIONLISTENER
	//ESSE METODO SERÁ CHAMADO TODA VEZ QUE O TIMER COMPLETAR 60 SEGUNDOS
	//SERVE PARA MOSTRAR QUAL O FRAMERATE EM FPS
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("FPS calculado: " + this.contadorDeFPS);
		this.contadorDeFPS = 0;
	}

}
