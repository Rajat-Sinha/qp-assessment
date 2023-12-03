package com.grocery.mgmt.platform.app.repository.impl;

import com.grocery.mgmt.platform.app.repository.IGroceryItemRepository;
import com.grocery.mgmt.platform.common.entity.GroceryItem;
import com.grocery.mgmt.platform.common.entity.Order;
import com.grocery.mgmt.platform.common.entity.OrderItem;
import com.grocery.mgmt.platform.common.exception.DataCreationFailedException;
import com.grocery.mgmt.platform.common.exception.DataUpdationFailedException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Repository
public class GroceryItemRepository implements IGroceryItemRepository {

    @Autowired
    EntityManager em;

    @Override
    @Transactional
    public GroceryItem getGroceryItemByName(String name, UUID id) {

        if(ObjectUtils.isEmpty(name)) {
            return null;
        }

        try {

            var cb = em.getCriteriaBuilder();
            var criteriaQuery = cb.createQuery(GroceryItem.class);
            var root = criteriaQuery.from(GroceryItem.class);

            List<Predicate> conditions = new ArrayList<>();
            conditions.add(cb.equal(root.get("name"), name));
            if(ObjectUtils.isNotEmpty(id)) {
                conditions.add(cb.notEqual(root.get("id"), id));
            }
            criteriaQuery.where(conditions.toArray(new Predicate[] {}));
            criteriaQuery.select(root);

            var typedQuery = em.createQuery(criteriaQuery);
            return typedQuery.getSingleResult();

        }catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void saveGroceryItem(GroceryItem groceryItem) {

        try {

            em.persist(groceryItem);
            em.flush();
            em.clear();

        } catch (Exception e){
            throw new DataCreationFailedException("Failed to save grocery");
        }

    }

    @Override
    @Transactional
    public GroceryItem getGroceryItemById(UUID id) {

        if(ObjectUtils.isEmpty(id)) {
            return null;
        }

        try {

            var cb = em.getCriteriaBuilder();
            var criteriaQuery = cb.createQuery(GroceryItem.class);
            var root = criteriaQuery.from(GroceryItem.class);

            List<Predicate> conditions = new ArrayList<>();
            conditions.add(cb.equal(root.get("id"), id));
            criteriaQuery.where(conditions.toArray(new Predicate[] {}));
            criteriaQuery.select(root);

            var typedQuery = em.createQuery(criteriaQuery);
            return typedQuery.getSingleResult();

        }catch (Exception e) {
            return null;
        }

    }

    @Override
    @Transactional
    public void updateGroceryItem(GroceryItem groceryItem) {

        try {

            em.merge(groceryItem);
            em.flush();
            em.clear();

        } catch (Exception e){
            throw new DataUpdationFailedException("Failed to update grocery");
        }

    }

    @Override
    @Transactional
    public List<GroceryItem> getGroceryList(Integer offset, Integer limit, List<UUID> itemIds, Boolean isQuantityAvailable) {

        List<GroceryItem> groceryItems = new LinkedList<>();

        try {

            var cb = em.getCriteriaBuilder();
            var criteriaQuery = cb.createQuery(GroceryItem.class);
            var root = criteriaQuery.from(GroceryItem.class);

            List<Predicate> conditions = new LinkedList<>();
            if(ObjectUtils.isNotEmpty(itemIds)) {
                conditions.add(cb.in(root.get("id")).value(itemIds));
            }
            if(ObjectUtils.isNotEmpty(isQuantityAvailable)) {
                if(isQuantityAvailable) {
                    conditions.add(cb.ge(root.get("quantity"), 1));
                }
            }
            criteriaQuery.where(conditions.toArray(new Predicate[] {}));
            criteriaQuery.select(root);
            criteriaQuery.orderBy(cb.asc(root.get("name")));

            var typedQuery = em.createQuery(criteriaQuery);

            if(ObjectUtils.isNotEmpty(offset) && ObjectUtils.isNotEmpty(limit)) {
                typedQuery.setMaxResults(limit);
                typedQuery.setFirstResult(offset);
            }

            return typedQuery.getResultList();

        }catch (Exception e) {

        }

        return groceryItems;
    }

    @Override
    @Transactional
    public Integer getTotalCountOfGrocery() {
        return null;
    }

    @Override
    @Transactional
    public void saveGroceryItemOrder(Order orderItem, List<GroceryItem> groceryItemUpdates, List<OrderItem> orderItems) {

        try {

            em.persist(orderItem);
            groceryItemUpdates.forEach(g -> em.merge(g));
            orderItems.forEach(o -> em.persist(o));

            em.flush();
            em.clear();

        } catch (Exception e){
            throw new DataUpdationFailedException("Failed to update grocery");
        }

    }

    @Override
    @Transactional
    public List<Order> getOrderDataByUserId(UUID userId) {

        List<Order> orders = new LinkedList<>();

        try {

            var cb = em.getCriteriaBuilder();
            var criteriaQuery = cb.createQuery(Order.class);
            var root = criteriaQuery.from(Order.class);

            List<Predicate> conditions = new LinkedList<>();
            if(ObjectUtils.isNotEmpty(userId)) {
                conditions.add(cb.equal(root.get("userId"), userId));
            }
            criteriaQuery.where(conditions.toArray(new Predicate[] {}));
            criteriaQuery.select(root);

            var typedQuery = em.createQuery(criteriaQuery);

            return typedQuery.getResultList();

        }catch (Exception e) {

        }

        return orders;
    }

    @Override
    @Transactional
    public List<OrderItem> getOrderItemsList(List<UUID> orderIds) {

        List<OrderItem> orderItems = new LinkedList<>();

        try {

            var cb = em.getCriteriaBuilder();
            var criteriaQuery = cb.createQuery(OrderItem.class);
            var root = criteriaQuery.from(OrderItem.class);

            List<Predicate> conditions = new LinkedList<>();
            if(ObjectUtils.isNotEmpty(orderIds)) {
                conditions.add(cb.in(root.get("orderId")).value(orderIds));
            }
            criteriaQuery.where(conditions.toArray(new Predicate[] {}));
            criteriaQuery.select(root);

            var typedQuery = em.createQuery(criteriaQuery);

            return typedQuery.getResultList();

        }catch (Exception e) {

        }

        return orderItems;
    }
}
