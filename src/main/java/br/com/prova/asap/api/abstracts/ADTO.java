/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.abstracts;

import br.com.prova.asap.api.interfaces.IDTO;
import java.lang.reflect.ParameterizedType;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 *
 * @author Santana
 * @param <T>
 * @param <D>
 */
public class ADTO<T, D> implements IDTO<T, D> {

    @Override
    public T create() {
        ModelMapper modelMapper = new ModelMapper();
         modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(this, ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public D create(T o) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(o, (Class<D>) this.getClass());

    }

}
