public abstract class StatusEffect {
    protected String nome;
    protected int duracao;
    
    public StatusEffect(String nome, int duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }
    
    public abstract void aplicarEfeito(Personagem alvo);
    public void reduzirDuracao() {
        if (duracao > 0) duracao--;
    }
    public boolean estaAtivo() { return duracao > 0; }
    public String getNome() { return nome; }
}