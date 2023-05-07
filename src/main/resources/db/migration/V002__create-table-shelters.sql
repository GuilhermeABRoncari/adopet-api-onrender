CREATE TABLE shelters (
        id BIGINT NOT NULL AUTO_INCREMENT,
        shelter_name VARCHAR(90) NOT NULL,
        email VARCHAR(90) NOT NULL UNIQUE,
        phone VARCHAR(20) NOT NULL,
        password VARCHAR(255) NOT NULL,
        about TEXT NOT NULL,
        shelter_profile_image TEXT NOT NULL,
        cep INT(8) NOT NULL,
        state VARCHAR(2) NOT NULL,
        city VARCHAR(36) NOT NULL,
        neighborhood VARCHAR(90) NOT NULL,
        street VARCHAR(90) NOT NULL,
        number VARCHAR(36) NOT NULL,
        pet_id BIGINT,

        PRIMARY KEY(id)
);

ALTER TABLE shelters ADD CONSTRAINT fk_pet FOREIGN KEY (pet_id) REFERENCES pets (id);
ALTER TABLE pets ADD CONSTRAINT fk_shelter FOREIGN KEY (shelter_id) REFERENCES shelters (id);