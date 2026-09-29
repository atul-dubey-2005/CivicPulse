-- LOCAL DEMO DATA ONLY. All demo accounts use password: password
INSERT INTO users(user_id,full_name,email,phone,password_hash,role) VALUES
('00000000-0000-0000-0000-000000000001','Demo Citizen','citizen@civicpulse.local','9000000001',crypt('password',gen_salt('bf')),'CITIZEN'),
('00000000-0000-0000-0000-000000000002','Demo Officer','officer@civicpulse.local','9000000002',crypt('password',gen_salt('bf')),'OFFICER'),
('00000000-0000-0000-0000-000000000003','Demo Worker','worker@civicpulse.local','9000000003',crypt('password',gen_salt('bf')),'FIELD_WORKER'),
('00000000-0000-0000-0000-000000000004','Demo Admin','admin@civicpulse.local','9000000004',crypt('password',gen_salt('bf')),'ADMIN') ON CONFLICT(email) DO NOTHING;
INSERT INTO complaints(complaint_id,citizen_id,department_id,category_id,title,description,latitude,longitude,address,priority,status,due_at)
SELECT '00000000-0000-0000-0000-000000000101','00000000-0000-0000-0000-000000000001',d.department_id,c.category_id,'Large pothole near college gate','Deep pothole causing two-wheelers to slow down.',19.0760,72.8777,'Main Road, Mumbai','HIGH','REPORTED',now()+interval '24 hours'
FROM departments d JOIN categories c ON c.department_id=d.department_id WHERE d.name='Roads' AND c.name='Pothole' ON CONFLICT DO NOTHING;
