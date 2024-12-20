package com.stress.data.service;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class MessageUtils {

    public static List<String> generateContent(Integer number) {
        return IntStream.range(1, number + 1).mapToObj(i -> toJSON()).toList();
    }

    public static List<String> generateRandomContent(Integer number) {
        return IntStream.range(1, number + 1).mapToObj(i -> toRandomJSON()).toList();
    }

    private static String toJSON() {
        return "{\"id_transacao\": \"TESTE-NTT-DATA-18-12-14:15\", \"status\" : \"cobrar\", \"data_cobranca\": \"2024-12-15\", \"tenant\": \"tenantA\", \"produto\": \"prod1\", \"servico\": \"serv1\", \"codigo_cliente\": \"cliente1\", \"codigo_conta\": \"conta1\", \"valor_transacao\": 100.0}";
    }

    private static String toRandomJSON() {
        Faker faker = new Faker();
        Random random = new Random();

        String idTransacao = faker.bothify("TESTE-NTT-DATA-??-##-##:##", true);
        String codigoCliente = faker.internet().uuid();
        double valorTransacao = 50.0 + (150.0 - 50.0) * random.nextDouble();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataCobranca = dateFormat.format(calendar.getTime());

        return String.format(
                "{\"id_transacao\": \"%s\", \"status\" : \"cobrar\", \"data_cobranca\": \"%s\", \"tenant\": \"tenantA\", \"produto\": \"prod1\", \"servico\": \"serv1\", \"codigo_cliente\": \"%s\", \"codigo_conta\": \"conta1\", \"valor_transacao\": %.2f}",
                idTransacao, dataCobranca, codigoCliente, valorTransacao
        );
    }
}
