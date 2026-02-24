package model.cliente;

public class PessoaFisica extends Cliente {

    private String cpf;

    public PessoaFisica(String nome, String email, String cpf) {
        super(nome, email);
        setDocumento(cpf);
    }

    @Override
    public String getDocumento() {
        return cpf;
    }

    @Override
    public void setDocumento(String documento) {
        if (!isCPFValido(documento))
            throw new IllegalArgumentException("CPF inválido.");

        this.cpf = documento.replaceAll("\\D", "");
    }

    @Override
    public String getTipoPessoa() {
        return "PF";
    }

    @Override
    public double getTaxaServicoInvestimentoAutomatico() {
        return 0.001; // 0,1%
    }

    private boolean isCPFValido(String cpf) {
        if (cpf == null) return false;

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) return false;
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++)
                soma += (cpf.charAt(i) - '0') * (10 - i);

            int dig1 = 11 - (soma % 11);
            if (dig1 >= 10) dig1 = 0;
            if (dig1 != (cpf.charAt(9) - '0')) return false;

            soma = 0;
            for (int i = 0; i < 10; i++)
                soma += (cpf.charAt(i) - '0') * (11 - i);

            int dig2 = 11 - (soma % 11);
            if (dig2 >= 10) dig2 = 0;
            if (dig2 != (cpf.charAt(10) - '0')) return false;

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}