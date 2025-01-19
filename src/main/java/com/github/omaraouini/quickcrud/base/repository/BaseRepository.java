package com.github.omaraouini.quickcrud.base.repository;

import com.github.omaraouini.quickcrud.base.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.io.Serializable;

/**
 * base repository interface used to define the common methods of all database repositories
 * that need to be used in the application <br>
 * example usage:
 * <pre>
 *     {@code
 * @Repository
 * public interface PersonRepository extends BaseRepository<Person, Integer> {
 *  }
 * }
 * </pre>
 *
 * @author aouin
 * Date: 04/03/2023
 * Time: 16:23
 */
@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity<I>, I extends Serializable> extends JpaRepository<E, I>, QueryByExampleExecutor<E>, JpaSpecificationExecutor<E> {

}
