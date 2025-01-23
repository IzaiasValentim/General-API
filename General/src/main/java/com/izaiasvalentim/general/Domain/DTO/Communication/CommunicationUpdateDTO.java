package com.izaiasvalentim.general.Domain.DTO.Communication;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;

public record CommunicationUpdateDTO(long id,String message, int scope, LocalDate endDate) {
}
