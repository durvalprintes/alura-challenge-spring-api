ALTER TABLE IF EXISTS backend.videos
ADD thumbnail_url VARCHAR(100);

update backend.videos set thumbnail_url = 'https://img.youtube.com/vi/nLPssMXOExM/0.jpg' where id = '883c06ae-6edd-4844-b15f-50302b41956a';
update backend.videos set thumbnail_url = 'https://img.youtube.com/vi/94yuIVdoevc/0.jpg' where id = '2ec1b78b-39b0-4815-a614-06e1c52df1b9';
update backend.videos set thumbnail_url = 'https://img.youtube.com/vi/ABjV1bObFW8/0.jpg' where id = 'ee40f4de-0294-4161-a8cb-a4bea70ae321';