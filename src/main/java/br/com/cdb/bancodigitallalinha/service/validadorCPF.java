package br.com.cdb.bancodigitallalinha.service;

import java.util.ArrayList;

public class validadorCPF {

    //VALIDAÇÃO DE CPF
    private static final String FORMATO_CPF = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
    // Formato em regex para comparar se o campo CPF foi digitado da maneira correta.


    //Digito 1 do CPF
    static Integer primeiroDigito = null;

    //Digito 2 do CPF
    static Integer segundoDigito = null;

    //lista para armazenar 9 primeiros digitos
    static ArrayList<Integer> cpfValid1 = new ArrayList<>();


    public boolean validarCPF(String cpf)
    {
        //valida o formato do cpf passado pelo cliente.
        if(!cpf.matches(FORMATO_CPF) )
        {
            System.out.println("Formato do CPF inválido. Usar: xxx.xxx.xxx-xx");
            return false;
        }

        String cpfF = cpf.strip().replace(".", "").replace("-", "");

        //9 primeiros digitos do CPF
        String vCpf1 = cpfF.substring(0, 9);

        //primeiro digito do cpf
        String dCpf1 = cpfF.substring(9, 10);

        //segundo digito do cpf
        String dCpf2 = cpfF.substring(10, 11);



        //Fasendo conversão para inteiro da primeira parte do cpf, os nove digitos;
        try {


            for(char num : vCpf1.toCharArray())
            {
                //Converte o num char para um inteiro e assim ser armazenado na lista
                int number = num - '0';

                cpfValid1.add(number);

            }
        }catch (Exception e) {
            System.out.println("Erro de cast fase 1.");
        }


        //Convertendo primeiro Digito do Cpf
        int dCpf1_ = 0;


        try {
            dCpf1_ = Integer.parseInt(dCpf1.strip());
        }catch (Exception e) {
            System.out.println("Erro de cast fase 2.");
        }

        //Convertendo segundo Digito do cpf
        int dCpf2_ = 0;

        try {
            dCpf2_ = Integer.parseInt(dCpf2.strip());
        }catch (Exception e) {
            System.out.println("Erro de cast fase 3.");
        }

        primeiroDigito = calculoDig1();
        segundoDigito = calculoDig2();

        //Compara se os dois divitos estão corretos
        if ( primeiroDigito == dCpf1_ && segundoDigito == dCpf2_ )
        {
            return true;
        }

        return false;
    }


    //Calcuculo primeiro digito


    private static int calculoDig1()
    /**
     * Valida o primeiro digito do CPF. Conforme calculo do algoritmo de validação de CPF.
     * Usa ArrayList cpfValid1 para armazenar os 9 primeiros digito
     * do cpf.
     *
     * @param rest, Armazena o resto da digisão.
     * @param tot1, armazena o total do digito do CPF * multiplicador
     * que se inicia em 2 e acresce +1 a cada interação.
     * @return retorna o primeiro digito (int)
     */
    {

        int tot1 = 0;

        //resto da divisão
        int rest =0;
        int multiplcador = 10;

        //Collections.reverse(cpfValid1);
        /**
         * Reverte a lista, pois o multiplicador é feito da direita para esquerda.
         */



        for(int j= 0; j < cpfValid1.size(); j++)
        {

            //pega o digito atual no ArrayList
            int digitoAtual = cpfValid1.get(j);

            tot1 += digitoAtual * multiplcador;
            multiplcador -= 1;
            // tot1 += Character.getNumericValue(cpfValid1.indexOf(j)) * multiplcador--;
        }
        rest = tot1 % 11;
        /**
         * Aqui ele comparara se o  resto do calculo for menor que 2
         * então logo o primeiro digito  é igual a 0. Caso contrario
         * o primeito digito é número de digitos do CPF menos o resto da divisão.
         */

        if( rest < 2)
        {
            primeiroDigito = 0;
            return primeiroDigito;
        }
        primeiroDigito = 11 - rest;

        //Adiciona o primeiro digito ao final da lista.
        cpfValid1.add(primeiroDigito);
        return primeiroDigito;
    }



    //CALCULO SEGUNDO DIGITO

    /**
     * Valida o segundo digito do CPF. Conforme calculo do algoritmo de validação de CPF.
     * Usa ArrayList cpfValid1 para armazenar os 9 primeiros digito mais o preimeiro digito
     * o segundo digito é número de digitos do CPF menos o resto da divisão.
     * @return Retorna o segundo digito do CPF
     *
     * @param tot2, total da somo do calculo do algorito de CPF
     * @param rest: Caluco de total (tot2) obtendo do resto da digsão pelo total dos 11 digitos
     * do CPF.
     * @param multiplicador que será acrescido.
     */

    private  static int calculoDig2()
    {
        int tot2 = 0;

        //resto da divisão
        int rest =0;
        int multiplcador2 = 11;


        for(int t= 0; t< cpfValid1.size(); t++)
        {


            //pega o digito atual no ArrayList
            int digitoAtual2 = cpfValid1.get(t);

            tot2 += digitoAtual2 * multiplcador2;
            multiplcador2 -= 1;
        }


        rest = tot2 % 11;

        if( rest < 2)
        {
            segundoDigito = 0;
            return segundoDigito;
        }
        segundoDigito = 11 - rest;

        cpfValid1.clear();
        return segundoDigito;
    }
}
