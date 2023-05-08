CREATE TABLE adopet_messages (
        id BIGINT NOT NULL AUTO_INCREMENT,
        message TEXT NOT NULL,
        date_time DATETIME NOT NULL,
        tutor_id BIGINT NOT NULL,
        shelter_id BIGINT NOT NULL,
        pet_id BIGINT NOT NULL,

        PRIMARY KEY(id)
);
ALTER TABLE adopet_messages ADD CONSTRAINT fk_tutor_message FOREIGN KEY (tutor_id) REFERENCES tutors (id);
ALTER TABLE adopet_messages ADD CONSTRAINT fk_shelter_message FOREIGN KEY (shelter_id) REFERENCES shelters (id);
ALTER TABLE adopet_messages ADD CONSTRAINT fk_pet_message FOREIGN KEY (pet_id) REFERENCES pets (id);

ALTER TABLE tutors ADD COLUMN message_id BIGINT;
ALTER TABLE tutors ADD CONSTRAINT fk_message_tutor FOREIGN KEY (message_id) REFERENCES adopet_messages (id);
ALTER TABLE shelters ADD COLUMN message_id BIGINT;
ALTER TABLE shelters ADD CONSTRAINT fk_message_shelter FOREIGN KEY (message_id) REFERENCES adopet_messages (id);