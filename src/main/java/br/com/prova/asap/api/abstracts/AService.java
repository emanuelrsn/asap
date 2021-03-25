/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.abstracts;

import br.com.prova.asap.api.infra.exception.GenericException;
import br.com.prova.asap.api.infra.exception.ObjectNotFoundException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Santana
 * @param <D>
 * @param <T>
 */
public abstract class AService<D extends AModel, T extends ADTO, S extends ADataBaseSequence> {

    public final MongoRepository mongoRepository;

    @Autowired
    private MongoOperations mongoOperations;

    private final T t;
    private final D d;
    private final S s;


    public AService(MongoRepository mongoRepository, T t, D d, S s) {
        this.mongoRepository = mongoRepository;
        this.t = t;
        this.d = d;
        this.s = s;
    }

    public int generateSequence() {
        try {
            ADataBaseSequence counter = this.mongoOperations.findAndModify(query(where("_id").is(this.s.SEQUENCE_NAME)),
                    new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                    s.getClass());
            return !Objects.isNull(counter) ? counter.getSeq() : 1;
        } catch (Exception ex) {
            throw new GenericException("Não foi possível concluir a operação");
        }
    }

    public T insert(T object) {
        this.validationsBeforeInsert(object);
        D entity = (D) object.create();
        entity.setId(generateSequence());
        return (T) object.create(this.mongoRepository.save(entity));
    }

    public T insert(D object) {
        this.validationsBeforeInsert((T) t.create(object));
        object.setId(generateSequence());
        return (T) t.create(this.mongoRepository.save(object));
    }

    private D populateDataDtoToEntitiy(D clazz, ADTO object) {
        try {
            Field[] allFields = clazz.getClass().getDeclaredFields();
            Field[] allFieldsDto = object.getClass().getDeclaredFields();

            for (Field fd : allFieldsDto) {
                for (Field f : allFields) {
                    if (f.getName().equals(fd.getName())) {
                        f.setAccessible(true);
                        fd.setAccessible(true);
                        if (Objects.nonNull(fd.get(object))) {
                            fd.getType();
                            f.set(clazz, fd.get(object));
                            f.get(clazz);
                        }
                    }
                }
            }
            return clazz;
        } catch (ObjectNotFoundException | IllegalAccessException | IllegalArgumentException | SecurityException ex) {
            throw new GenericException("Não foi possível concluir a operação");
        }
    }

    public Object update(Integer id, ADTO object) {
        try {
            D clazz = (D) this.mongoRepository.findById(id).
                    orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado."));
            return object.create(this.mongoRepository.save(populateDataDtoToEntitiy(clazz, object)));

        } catch (Throwable ex) {
            throw new GenericException(ex.getMessage());
        }

    }

    public boolean delete(Integer id) {
        this.mongoRepository.deleteById(id);
        return true;
    }

    public T getById(Integer id) {
        try {
            return (T) this.t.create(this.mongoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado.")));
        } catch (Throwable ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }

    public D getEntityById(Integer id) {
        try {
            return (D) this.mongoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado."));
        } catch (Throwable ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }

    public List<T> getAll() {
        return (List<T>) this.mongoRepository.findAll().stream().map(f -> this.t.create(f)).collect(Collectors.toList());
    }

    public abstract void validationsBeforeInsert(T t);

    public abstract void validationsBeforeUpdate(T t);

}
