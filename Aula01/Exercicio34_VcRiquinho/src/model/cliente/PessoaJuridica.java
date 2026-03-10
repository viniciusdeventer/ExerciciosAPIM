package model.cliente;

public class PessoaJuridica extends Cliente {

    private String cnpj;

    public PessoaJuridica(String nome, String email, String cnpj) {
        super(nome, email);
        setDocumento(cnpj);
    }

    @Override
    public String getDocumento() {
        return cnpj;
    }

    @Override
    public void setDocumento(String documento) {
        if (!isCNPJValido(documento))
            throw new IllegalArgumentException("CNPJ inválido.");

        this.cnpj = documento.replaceAll("\\D", "");
    }

    @Override
    public String getTipoPessoa() {
        return "PJ";
    }

    @Override
    public double getTaxaServicoInvestimentoAutomatico() {
        return 0.0015; // 0,15%
    }

    private boolean isCNPJValido(String cnpj) {
        if (cnpj == null) return false;

        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14) return false;
        if (cnpj.matches("(\\d)\\1{13}")) return false;

        try {
            int soma = 0;
            int[] pesos1 = {5,4,3,2,9,8,7,6,5,4,3,2};

            for (int i = 0; i < 12; i++)
                soma += (cnpj.charAt(i) - '0') * pesos1[i];

            int dig1 = soma % 11;
            dig1 = dig1 < 2 ? 0 : 11 - dig1;
            if (dig1 != (cnpj.charAt(12) - '0')) return false;

            soma = 0;
            int[] pesos2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};

            for (int i = 0; i < 13; i++)
                soma += (cnpj.charAt(i) - '0') * pesos2[i];

            int dig2 = soma % 11;
            dig2 = dig2 < 2 ? 0 : 11 - dig2;
            if (dig2 != (cnpj.charAt(13) - '0')) return false;

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}