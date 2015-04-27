# DataCollection
CREATE TABLE field (
  field_id SERIAL PRIMARY KEY NOT NULL ,
  label VARCHAR(255),
  field_type VARCHAR(255),
  required BOOLEAN,
  is_active BOOLEAN
);

CREATE TABLE field_value (
  field_value_id SERIAL PRIMARY KEY NOT NULL,
  field_id INTEGER,
  value VARCHAR(255),
  FOREIGN KEY (field_id) REFERENCES field(field_id)
);

CREATE TABLE form (
  form_id SERIAL PRIMARY KEY NOT NULL
);

CREATE TABLE response (
  response_id SERIAL PRIMARY KEY NOT NULL ,
  form_id INTEGER NOT NULL,
  field_id INTEGER NOT NULL,
  field_value_id INTEGER,
  single_value TEXT,
  FOREIGN KEY (form_id) REFERENCES form(form_id),
  FOREIGN KEY (field_id) REFERENCES field(field_id),
  FOREIGN KEY (field_value_id) REFERENCES field_value(field_value_id)
);
