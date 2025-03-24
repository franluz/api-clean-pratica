package br.com.alura.adopet.api.enumm;

public enum ValidacaoFrases {
    DADOS_JA_CADASTRADOS_PARA_TUTOR(1,"Dados já cadastrados para outro tutor!"),
    DADOS_JA_CADASTRADOS_PARA_ABRIGO(2,"Dados já cadastrados para outro abrigo!");
    private Integer value;
    private String label;

    ValidacaoFrases(Integer value, String label) {
        this.label = label;
        this.value = value;
    }
}
