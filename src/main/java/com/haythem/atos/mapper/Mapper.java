package com.haythem.atos.mapper;

/**
 * 
 * @author HJEBEL
 *
 * @param <I> Entity
 * @param <O> DTO model 
 */
public interface Mapper<I, O> {
	O fromIToO(I i);
	I fromOToI(O o);
}

