package com.projeto.meu_projeto.service;

import com.projeto.meu_projeto.model.Cliente;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class PoliticaFreteService {
    private static final BigDecimal LIMITE_FRETE_COMUM = new BigDecimal("500.00");
    private static final BigDecimal LIMITE_FRETE_PREMIUM = new BigDecimal("200.00");

    public BigDecimal calcularFrete(BigDecimal total, Cliente cliente) {
        BigDecimal limite = cliente.isPremium() ? LIMITE_FRETE_PREMIUM : LIMITE_FRETE_COMUM;
        if (total.compareTo(limite) >= 0) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal("50.00"); 
    }
}
