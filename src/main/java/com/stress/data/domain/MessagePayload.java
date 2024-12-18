package com.stress.data.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessagePayload {
    private int number;
    private String description;
}
