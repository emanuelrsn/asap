package br.com.prova.asap.api.abstracts;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Service;


@Service
@Data
public abstract class AModel {
    @Id
    @Field("_id")
    private int id;
}
