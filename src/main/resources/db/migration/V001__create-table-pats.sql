CREATE TABLE pets (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(80) NOT NULL,
        years_old VARCHAR(80) NOT NULL,
        animal_size VARCHAR(20) NOT NULL,
        description VARCHAR(80) NOT NULL,
        adopted BIT NOT NULL,
        image VARCHAR(255) NOT NULL,
        shelter_id BIGINT,

        PRIMARY KEY(id)
);