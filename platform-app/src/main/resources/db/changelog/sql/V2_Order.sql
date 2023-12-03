-- Orders Table
CREATE TABLE IF NOT EXISTS t_order (
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    order_date TIMESTAMP NOT NULL,
    total_amount float4 default NULL,
    created_on timestamp DEFAULT NULL,
    created_by uuid NOT NULL,
    updated_on timestamp DEFAULT NULL,
    updated_by uuid NOT NULL,
    last_action_type text default NULL,
    CONSTRAINT t_orders_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS t_order_audit (
    rev int4 NOT NULL,
    revtype int2 NULL,
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    order_date TIMESTAMP NOT NULL,
    total_amount float4 default NULL,
    created_on timestamp DEFAULT NULL,
    created_by uuid NOT NULL,
    updated_on timestamp DEFAULT NULL,
    updated_by uuid NOT NULL,
    last_action_type text default NULL,
    CONSTRAINT t_order_audit_pkey PRIMARY KEY (id, rev),
    constraint t_order_audit_rev_fkey_n FOREIGN KEY (rev) REFERENCES revinfo(rev)
);
