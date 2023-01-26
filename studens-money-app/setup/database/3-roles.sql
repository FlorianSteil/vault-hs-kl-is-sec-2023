CREATE ROLE vault_db_user LOGIN SUPERUSER PASSWORD 'vault_db_password';
CREATE ROLE readonly  LOGIN SUPERUSER PASSWORD 'vault_db_password';

GRANT ALL ON ALL TABLES IN SCHEMA public TO "readonly";
Grant ALL ON ALL SEQUENCES IN SCHEMA public to "readonly"

