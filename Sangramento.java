public class Sangramento extends StatusEffect {
    private static final int DANO_POR_TURNO = 5;
    
    public Sangramento(int duracao) {
        super("Sangramento", duracao);
    }
    
    @Override
    public void aplicarEfeito(Personagem alvo) {
        alvo.receberDano(DANO_POR_TURNO);
        System.out.println(alvo.getNome() + " sofre " + DANO_POR_TURNO + " de dano por sangramento!");
    }
}