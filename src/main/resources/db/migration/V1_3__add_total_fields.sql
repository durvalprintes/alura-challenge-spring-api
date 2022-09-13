ALTER TABLE IF EXISTS backend.videos
ADD total_aprova NUMERIC DEFAULT 0,
ADD total_rejeita NUMERIC DEFAULT 0;

update backend.videos set total_aprova = 123, total_rejeita = 23 where id = '883c06ae-6edd-4844-b15f-50302b41956a';
update backend.videos set total_aprova = 456, total_rejeita = 100 where id = '2ec1b78b-39b0-4815-a614-06e1c52df1b9';
update backend.videos set total_aprova = 098 where id = 'ee40f4de-0294-4161-a8cb-a4bea70ae321';