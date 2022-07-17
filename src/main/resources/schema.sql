create table category (
    id  NUMBER(38) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
    description VARCHAR2(4000),
    CONSTRAINT category_pk PRIMARY KEY (id)
);

create table Unit_Of_Measure (
    id NUMBER(38) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
    description VARCHAR2(32),
    constraint uom_pk primary key (id)
);

create table recipe (
    id NUMBER(38) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
    description VARCHAR2(1000),
    prep_time NUMBER(6),
    cook_time NUMBER(6),
    servings NUMBER(6),
    source VARCHAR2(256),
    url VARCHAR2(256),
    directions VARCHAR2(256),
    difficulty VARCHAR2(30),
    image BLOB,
    constraint recipe_pk primary key (id)
);

create table notes (
    --id NUMBER(38) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
    recipe_id NUMBER(38) NOT NULL,
    recipe_notes BLOB,
    --constraint notes_pk primary key (id),
    constraint notes_pk primary key (recipe_id),
    constraint notes_fk_recipe
                   foreign key (recipe_id)
                   references recipe(id)
);

create table ingredient (
    id NUMBER(38) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
    recipe_id NUMBER(38) NOT NULL,
    uom_id NUMBER(38) NOT NULL,
    description VARCHAR2(2000),
    amount NUMBER(19, 2),
    constraint ingredient_pk primary key (id),
    constraint ingredient_fk_recipe
                        foreign key (recipe_id)
                        references recipe(id),
    constraint ingredient_fk_uom
                        foreign key (uom_id)
                        references Unit_Of_Measure(id)
);

create table recipe_category (
    recipe_id NUMBER(38) NOT NULL,
    category_id NUMBER(38) NOT NULL,
    constraint rc_pk primary key (recipe_id, category_id),
    constraint rc_fk_recipe
                             foreign key (recipe_id)
                             references recipe(id),
    constraint rc_fk_category
                             foreign key (category_id)
                             references category(id)
)