package br.com.alura.adopet.api.enumm;

public enum ValidacaoFrases {
    DADOS_JA_CADASTRADOS_PARA_TUTOR(1,"Dados já cadastrados para outro tutor!"),
    DADOS_JA_CADASTRADOS_PARA_ABRIGO(2,"Dados já cadastrados para outro abrigo!"),
    TUTOR_MAXIMO_PERMITIDO(3,"Tutor chegou ao limite máximo de 5 adoções!"),
    PET_COM_ADOCAO_EM_ANDAMENTO(4,"Pet já está aguardando avaliação para ser adotado!"),
    TUTOR_POSSUI_ADOCAO_EM_ANDAMENTO(5,"Tutor já possui outra adoção aguardando avaliação!");
    private Integer value;
    private String label;

    ValidacaoFrases(Integer value, String label) {
        this.label = label;
        this.value = value;
    }
}
