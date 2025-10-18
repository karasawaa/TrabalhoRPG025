public class Arqueiro extends Personagem {
    public Arqueiro(String nome) {
        super(nome, 90, 80, 8, 15, 7);
    }
    
    @Override
    public boolean podeUsarArma(Arma arma) {
        return arma instanceof ArcoElfico || arma instanceof AdagaSombria;
    }
    
    @Override
    public void aplicarPassiva() {
        // Esquiva implementada no receberDano
    }
    
    @Override
    public void receberDano(int dano) {
        if (random.nextDouble() < 0.25) {
            System.out.println(nome + " esquivou do ataque!");
            return;
        }
        super.receberDano(dano);
    }
}