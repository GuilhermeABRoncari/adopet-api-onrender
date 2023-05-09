CREATE TABLE adoptions (
        id BIGINT NOT NULL AUTO_INCREMENT,
        comment VARCHAR(255),
        pet_id BIGINT NOT NULL,

        PRIMARY KEY(id)
);

ALTER TABLE adoptions ADD CONSTRAINT fk_adoption_pet FOREIGN KEY (pet_id) REFERENCES pets (id);

ALTER TABLE shelters ADD COLUMN adoption_id BIGINT, ADD CONSTRAINT fk_shelter_adoption FOREIGN KEY (adoption_id) REFERENCES adoptions (id);