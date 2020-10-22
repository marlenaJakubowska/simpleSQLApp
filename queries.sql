SELECT first_name, last_name from mentors;
SELECT nick_name from mentors where city='Miskolc';
SELECT concat(first_name, ' ',  last_name, ' ' , phone_number) AS full_name from applicants WHERE first_name='Carol';
SELECT concat(first_name, ' ',  last_name, ' ' , phone_number) AS full_name from applicants WHERE email LIKE '%adipiscingenimmi.edu';
SELECT * from applicants WHERE application_code=54823;
UPDATE applicants SET phone_number='003670/223-7459' WHERE first_name='Jemima' AND last_name='Foreman';
SELECT phone_number from applicants WHERE first_name='Jemima' AND last_name='Foreman';
DELETE from applicants WHERE email LIKE '%@mauriseu.net';