CREATE TABLE IF NOT EXISTS t_order_item (
    id uuid NOT NULL,
    order_id uuid NOT NULL,
    item_id uuid NOT NULL,
    quantity int4 NOT NULL,
    price float4 default NULL,
    created_on timestamp DEFAULT NULL,
    created_by uuid NOT NULL,
    updated_on timestamp DEFAULT NULL,
    updated_by uuid NOT NULL,
    last_action_type text default NULL,
    CONSTRAINT t_order_item_pkey PRIMARY KEY (id)
);

ALTER TABLE t_order_item ADD CONSTRAINT t_order_item_order_id FOREIGN KEY (order_id) REFERENCES t_order (id);
ALTER TABLE t_order_item ADD CONSTRAINT t_order_item_item_id FOREIGN KEY (item_id) REFERENCES t_grocery_item (id);

CREATE TABLE IF NOT EXISTS t_order_item_audit (
    rev int4 NOT NULL,
    revtype int2 NULL,
    id uuid NOT NULL,
    order_id uuid NOT NULL,
    item_id uuid NOT NULL,
    quantity int4 NOT NULL,
    price float4 default NULL,
    created_on timestamp DEFAULT NULL,
    created_by uuid NOT NULL,
    updated_on timestamp DEFAULT NULL,
    updated_by uuid NOT NULL,
    last_action_type text default NULL,
    CONSTRAINT t_order_item_audit_pkey PRIMARY KEY (id, rev),
    constraint t_order_item_audit_rev_fkey_n FOREIGN KEY (rev) REFERENCES revinfo(rev)
);
