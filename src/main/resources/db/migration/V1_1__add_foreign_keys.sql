ALTER TABLE workshops
    ADD CONSTRAINT uq_workshops_manager_id
        UNIQUE (manager_id);

ALTER TABLE workshops
    ADD CONSTRAINT fk_workshops_manager_id_user_id
        FOREIGN KEY (manager_id)
            REFERENCES users(id);

ALTER TABLE cars
    ADD CONSTRAINT uq_cars_workshop_id
        UNIQUE (workshop_id);

ALTER TABLE cars
    ADD CONSTRAINT fk_cars_id_workshop_id
        FOREIGN KEY (workshop_id)
            REFERENCES workshops(id);