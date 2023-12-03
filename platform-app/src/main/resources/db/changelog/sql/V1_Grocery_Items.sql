-- Grocery Items
CREATE TABLE IF NOT EXISTS t_grocery_item (
    id uuid NOT NULL,
    name text NOT NULL,
    price float4 NOT NULL,
    description text default null,
    quantity int4 NOT NULL,
    created_on timestamp DEFAULT NULL,
    created_by uuid NOT NULL,
    updated_on timestamp DEFAULT NULL,
    updated_by uuid NOT NULL,
    last_action_type text default NULL,
    CONSTRAINT t_grocery_item_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS t_grocery_item_audit (
    rev int4 NOT NULL,
    revtype int2 NULL,
    id uuid NOT NULL,
    name text NOT NULL,
    price float4 NOT NULL,
    description text default null,
    quantity int4 NOT NULL,
    created_on timestamp DEFAULT NULL,
    created_by uuid NOT NULL,
    updated_on timestamp DEFAULT NULL,
    updated_by uuid NOT NULL,
    last_action_type text default NULL,
    CONSTRAINT t_grocery_item_audit_pkey PRIMARY KEY (id, rev),
    constraint t_grocery_item_audit_rev_fkey_n FOREIGN KEY (rev) REFERENCES revinfo(rev)
);
