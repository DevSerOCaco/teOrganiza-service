package com.teOrganiza_service.shared.integration.events;

import java.util.UUID;

/**
 * Evento publicado quando um novo usuário é criado com sucesso.
 * Este record atua como um contrato de dados público entre o módulo de identidade e outros módulos.
 */
public record UserCreatedEvent(UUID userId, String name, String email) {
}
