public class ResultadoAtaque {
    public int dano;
    public String mensagem;
    public StatusEffect efeito;
    
    public ResultadoAtaque(int dano, String mensagem, StatusEffect efeito) {
        this.dano = dano;
        this.mensagem = mensagem;
        this.efeito = efeito;
    }
}