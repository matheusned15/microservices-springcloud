package io.github.matheusned.msavaliadorcredito.application;

import io.github.matheusned.msavaliadorcredito.domain.model.CartaoCliente;
import io.github.matheusned.msavaliadorcredito.domain.model.DadosCliente;
import io.github.matheusned.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.matheusned.msavaliadorcredito.infra.clients.CartoesResourceClient;
import io.github.matheusned.msavaliadorcredito.infra.clients.ClientResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClientResourceClient clientclient;
    private final CartoesResourceClient cartoesClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) {
        ResponseEntity<DadosCliente> dadosClienteResponse = clientclient.dadosCliente(cpf);
        ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .cartoes(cartoesResponse.getBody())
                .build();
    }
}
