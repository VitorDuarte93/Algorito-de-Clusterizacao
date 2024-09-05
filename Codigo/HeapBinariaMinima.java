public class HeapBinariaMinima
{
	private int n;               /* Numero de elementos no heap. */   
	private int tam;             /* Tamanho m�ximo do heap. */
	private Distancia[] vetor;  // modificado para criar um vetor de distancia para a clusterização

	
	public HeapBinariaMinima(int tamanho)
	{
		n = 0;
		tam = tamanho;
		vetor = new Distancia[tamanho+1];
	}

	//necessario modificar, pois na hora da clusterização no momento de criar uma heap
	//sem as distancias que ja foram usadas, causa erro de index, ja que o tamanho é diferente
	//soluões com "break if null" e "try-catch" causam estouro na callstack

	public HeapBinariaMinima(int tamanho, int tamanho2, Distancia[] v)
	{
		tam = tamanho;
		vetor = new Distancia[tamanho+1];
		n = tamanho2;

		for( int i = 0; i < tamanho; i++ )
			vetor[ i + 1 ] = v[ i ];

		constroiHeap();
	}

	
	public boolean vazia()
	{
		return n == 0;
	}

	
	public void fazVazia()
	{
		n = 0;
	}

	
	public void imprime()
	{
		System.out.print("Conte�do da heap: ");
		
		for(int i = 1; i <= n; i++)
			System.out.print(vetor[i] + " ");

		System.out.println();
	}

	
	public Distancia min()
	{
		if (this.vazia())
		{
			System.out.println("Fila de prioridades vazia!");
			return null;
		}

		return vetor[1];
	}


	public Distancia removeMin()
	{
		Distancia itemMin;
		
		if(this.vazia())
		{
			System.out.println("Fila de prioridades vazia!");
			return null;
		}

		itemMin = vetor[1];
		vetor[1] = vetor[n];
		n--;
		refaz(1);
		
		return itemMin;
	}

	
	private void constroiHeap()
	{
		for( int i = n / 2; i > 0; i-- )
			refaz(i);
	}

	
	private void refaz(int i)
	{
		Distancia x = vetor[ i ];

		while(2*i <= n)
		{
			int filhoEsq, filhoDir, menorFilho;
			
			filhoEsq = 2*i;
			filhoDir = 2*i + 1;
			
			
			menorFilho = filhoEsq;
			
		
			if(filhoDir <= n)
			{
			
				if(vetor[ filhoDir ].compareTo(vetor[ filhoEsq ] )< 0)
					menorFilho = filhoDir;
			}

			
			if(vetor[ menorFilho ].compareTo(x) < 0)
				vetor [ i ] = vetor[ menorFilho ];
			else
				break;
			
		
			i = menorFilho;
		}
		
		vetor[ i ] = x;
	}

	
	public void insere(Distancia x)
	{
		if (tam == n)
		{
			System.out.println("Fila de prioridades cheia!");
			return;
		}

	
		n++;
		int pos = n;
		
		
		vetor[0] = x;

		
		while(x.compareTo(vetor[pos/2]) < 0)
		{
			vetor[pos] = vetor[ pos/2 ];
			pos /= 2;
		}
		
		vetor[pos] = x;
	}


	public int getN() {
		return n;
	}


	public Distancia[] getVetor() {
		return vetor;
	}

	
}