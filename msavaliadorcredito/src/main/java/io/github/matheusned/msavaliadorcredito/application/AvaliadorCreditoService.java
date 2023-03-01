package io.github.matheusned.msavaliadorcredito.application;

import io.github.matheusned.msavaliadorcredito.domain.model.DadosCliente;
import io.github.matheusned.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.matheusned.msavaliadorcredito.infra.clients.ClientResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClientResourceClient client;

    public SituacaoCliente obterSituacaoCliente(String cpf) {

        ResponseEntity<DadosCliente> dadosClienteResponse = client.dadosCliente(cpf);

        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .build();
    }
}
