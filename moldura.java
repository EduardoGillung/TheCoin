package INVENTARIO;
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class moldura extends JFrame {
	
	private String Titulo = "CAPITULO 09 - AULA 09 - CENARIOS";
	
	moldura()
	{
		//moldura sempre em primeiro plano.
		this.setAlwaysOnTop(true);
		//nao pode redimesionar a moldura.
		this.setResizable(false);
		//titulo.
		this.setTitle(Titulo);
		//define a borda como BorderLayout.
		this.setLayout(new BorderLayout());
		
		//PAINEIS
		painel P1 = new painel("centro");
		painel P2 = new painel("sul");
		this.add(P1, BorderLayout.CENTER);
		this.add(P2, BorderLayout.SOUTH);
		//PAINEIS
		this.pack();
		//libera memoria depois que fecha a moldura.
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		//centraliza a moldura.
		this.setLocationRelativeTo(null); 
		this.setVisible(true);
	}
}
