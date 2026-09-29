INSERT INTO departments(name,description) VALUES ('Roads','Road and pothole maintenance'),('Solid Waste','Garbage and sanitation'),('Water','Water supply and leakage'),('Drainage','Drain and sewer maintenance'),('Street Lighting','Street lighting maintenance') ON CONFLICT(name) DO NOTHING;
INSERT INTO categories(name,department_id,default_sla_hours) SELECT 'Pothole',department_id,24 FROM departments WHERE name='Roads' ON CONFLICT(name) DO NOTHING;
INSERT INTO categories(name,department_id,default_sla_hours) SELECT 'Garbage Accumulation',department_id,24 FROM departments WHERE name='Solid Waste' ON CONFLICT(name) DO NOTHING;
INSERT INTO categories(name,department_id,default_sla_hours) SELECT 'Water Leakage',department_id,12 FROM departments WHERE name='Water' ON CONFLICT(name) DO NOTHING;
INSERT INTO categories(name,department_id,default_sla_hours) SELECT 'Drainage Blockage',department_id,24 FROM departments WHERE name='Drainage' ON CONFLICT(name) DO NOTHING;
INSERT INTO categories(name,department_id,default_sla_hours) SELECT 'Street Light Failure',department_id,48 FROM departments WHERE name='Street Lighting' ON CONFLICT(name) DO NOTHING;
