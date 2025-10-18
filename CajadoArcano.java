public class CajadoArcano implements Arma {
    @Override
    public int getDanoBase() { return 8; }
    
    @Override
    public String getNome() { return "Cajado Arcano"; }
    
    @Override
    public int getCustoMana() { return 25; }
    
    @Override
    public boolean podeUsar(Personagem personagem) {
        return personagem.getInteligencia() >= 12;
    }
    
    @Override
    public ResultadoAtaque atacar(Personagem atacante, Personagem alvo) {
        int dano = getDanoBase();
        StatusEffect efeito = new Queimadura(2);
        String mensagem = atacante.getNome() + " lança Bola de Fogo com " + getNome() + " causando " + dano + " de dano!";
        mensagem += " Inimigo queimando por 2 turnos!";
        
        return new ResultadoAtaque(dano, mensagem, efeito);
    }
}