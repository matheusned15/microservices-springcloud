package io.github.matheusned.msavaliadorcredito.application;

import feign.FeignException;
import io.github.matheusned.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import io.github.matheusned.msavaliadorcredito.application.ex.ErroComunicacaoMicroserviceException;
import io.github.matheusned.msavaliadorcredito.domain.model.CartaoCliente;
import io.github.matheusned.msavaliadorcredito.domain.model.DadosCliente;
import io.github.matheusned.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.matheusned.msavaliadorcredito.infra.clients.CartoesResourceClient;
import io.github.matheusned.msavaliadorcredito.infra.clients.ClientResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClientResourceClient clientclient;
    private final CartoesResourceClient cartoesClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException {
        try {

            ResponseEntity<DadosCliente> dadosClienteResponse = clientclient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroserviceException(e.getMessage(), status);
        }
    }
}
