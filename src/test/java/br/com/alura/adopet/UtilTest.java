package br.com.alura.adopet;

import java.util.Random;

public class UtilTest {
  public static   String gerarNome() {
        String[] primeirosNomes = {
                "Ana", "Carlos", "Beatriz", "Eduardo", "Fernanda", "João", "Juliana", "Marcelo", "Ricardo", "Vanessa"
        };

        String[] sobrenomes = {
                "Silva", "Santos", "Oliveira", "Costa", "Pereira", "Rodrigues", "Almeida", "Souza", "Mendes", "Gomes"
        };

        Random random = new Random();

        String primeiroNome = primeirosNomes[random.nextInt(primeirosNomes.length)];
        String sobrenome = sobrenomes[random.nextInt(sobrenomes.length)];
        return primeiroNome + sobrenome;
    }

   public static String gerarTelefone() {
        Random random = new Random();
        int ddd = random.nextInt(90) + 11;
        StringBuilder telefone = new StringBuilder();
        telefone.append("9");
        for (int i = 0; i < 8; i++) {
            telefone.append(random.nextInt(10));
        }
        return ddd + telefone.toString();
    }

  public static   String gerarEmail() {
        String[] dominios = {
                "gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "live.com", "aol.com"
        };
        String nomeDeUsuario = gerarNome();
        Random random = new Random();
        String dominio = dominios[random.nextInt(dominios.length)];
        return nomeDeUsuario + "@" + dominio;
    }
}
