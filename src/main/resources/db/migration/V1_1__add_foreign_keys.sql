ALTER TABLE workshops
    ADD CONSTRAINT uq_workshops_manager_id
        UNIQUE (manager_id);

ALTER TABLE workshops
    ADD CONSTRAINT fk_workshops_manager_id_users_id
        FOREIGN KEY (manager_id)
            REFERENCES users(id);
