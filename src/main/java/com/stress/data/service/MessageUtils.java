package com.stress.data.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MessageUtils {

    public static List<String> generateContent(Integer number) {
        // Generate content based on number
        return IntStream.range(1, number + 1)
                .mapToObj(i -> toJSON())
                .collect(Collectors.toList());
    }

    public static String toJSON() {
        return "{\"id_transacao\": \"TESTE-NTT-DATA-18-12-14:15\", \"status\" : \"cobrar\", \"data_cobranca\": \"2024-12-15\", \"tenant\": \"tenantA\", \"produto\": \"prod1\", \"servico\": \"serv1\", \"codigo_cliente\": \"cliente1\", \"codigo_conta\": \"conta1\", \"valor_transacao\": 100.0}";
    }
}
