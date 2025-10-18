public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        super(nome, 120, 50, 15, 8, 5);
    }
    
    @Override
    public boolean podeUsarArma(Arma arma) {
        return arma instanceof EspadaLonga || arma instanceof MachadoDeGuerra;
    }
    
    @Override
    public void aplicarPassiva() {
        if (random.nextDouble() < 0.2) {
            System.out.println(nome + " ativa Pele Dura!");
        }
    }
    
    @Override
    protected double getReducaoDano() {
        return 0.2;
    }
}