package com.jamilekerber.bff_agendador_tarefas.infrastructure.client.config;

import com.jamilekerber.bff_agendador_tarefas.infrastructure.exceptions.BusinessException;
import com.jamilekerber.bff_agendador_tarefas.infrastructure.exceptions.ConflictException;
import com.jamilekerber.bff_agendador_tarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.web.client.ResourceAccessException;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        switch (response.status()) {
            case 409:
                return new ConflictException("Erro: Atributo já existente");
            case 403:
                return new ResourceAccessException("Erro: Atributo não encontrado");
            case 401:
                return new UnauthorizedException("Erro: Usuário não autorizado");
            default:
                return new BusinessException("Erro de servidor");
        }
    }
}
