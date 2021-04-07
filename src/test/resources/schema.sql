CREATE TABLE IF NOT EXISTS Doctor (
    id bigserial,
    name varchar(100) NOT NULL,
    CONSTRAINT "Doctor_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Medicine (
    id bigserial,
    sku varchar(100) NOT NULL,
    CONSTRAINT "Medicine_pkey" PRIMARY KEY (id)
);