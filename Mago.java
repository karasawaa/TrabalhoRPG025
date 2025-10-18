public class Mago extends Personagem {
    public Mago(String nome) {
        super(nome, 70, 150, 5, 7, 18);
    }
    
    @Override
    public boolean podeUsarArma(Arma arma) {
        return arma instanceof CajadoArcano || arma instanceof AdagaSombria;
    }
    
    @Override
    public void aplicarPassiva() {
        restaurarMana(10);
        System.out.println(nome + " regenera 10 de mana! Mana: " + mana + "/" + manaMaxima);
    }
}