/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.apolice.util;

import br.com.prova.asap.api.apolice.interfaces.DataBaseSequence;
import java.util.Objects;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.data.mongodb.core.query.Update;

/**
 *
 * @author Santana
 * @param <T>
 * @param <mongoOperations>
 */
public class GenerateSequence<T extends DataBaseSequence, mongoOperations extends MongoOperations> {
    private final T t;
    private final MongoOperations mongoOperations;
    
    public GenerateSequence(T t, MongoOperations mongoOperations){
        this.t = t;
        this.mongoOperations = mongoOperations;
    }
    
    public int generateSequence(String seqName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DataBaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                t.getClass());
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
