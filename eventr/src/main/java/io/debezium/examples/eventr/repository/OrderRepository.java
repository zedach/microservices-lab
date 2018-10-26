package io.debezium.examples.eventr.repository;

import java.util.List;

import javax.ejb.Stateful;

import io.debezium.examples.eventr.model.Event;
import io.debezium.examples.eventr.model.Order;

@Stateful
public class OrderRepository extends Repository<Order> {

    public OrderRepository() {
        super(Order.class);
    }

    @Override
    public Order create(Order entity) {
        entity.setEvent(entityManager.find(Event.class, entity.getEvent().getId()));
        entityManager.persist(entity);
        return entity;
    }

    public List<Order> getByEventId(int eventId) {
        return entityManager.createQuery("FROM Order o WHERE o.event.id=?1", Order.class).setParameter(1, eventId).getResultList();
    }

    public List<Order> getByCustomerOrEventName(String term) {
        return entityManager
                .createQuery( "FROM Order o WHERE o.customer LIKE :term OR o.event.name LIKE :term", Order.class )
                .setParameter( "term", "%" + term + "%" )
                .getResultList();
    }

    @Override
    public Order save(Order entity) {
        entity.setEvent(entityManager.find(Event.class, entity.getEvent().getId()));
        return entityManager.merge(entity);
    }
}
