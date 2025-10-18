public class Paladino extends Personagem {
    public Paladino(String nome) {
        super(nome, 100, 100, 12, 10, 12);
    }
    
    @Override
    public boolean podeUsarArma(Arma arma) {
        return arma instanceof EspadaLonga || arma instanceof MachadoDeGuerra;
    }
    
    @Override
    public void aplicarPassiva() {
        int cura = (int)(vidaMaxima * 0.05);
        vida += cura;
        if (vida > vidaMaxima) vida = vidaMaxima;
        System.out.println(nome + " cura " + cura + " de vida pela luz divina! Vida: " + vida + "/" + vidaMaxima);
    }
    
    @Override
    protected double getReducaoDano() {
        return 0.1;
    }
}