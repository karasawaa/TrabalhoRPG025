public class Queimadura extends StatusEffect {
    private static final int DANO_POR_TURNO = 10;
    
    public Queimadura(int duracao) {
        super("Queimadura", duracao);
    }
    
    @Override
    public void aplicarEfeito(Personagem alvo) {
        alvo.receberDano(DANO_POR_TURNO);
        System.out.println(alvo.getNome() + " sofre " + DANO_POR_TURNO + " de dano por queimadura!");
    }
}