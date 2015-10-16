create database code;
grant all privileges on code.* to 'code'@'%' identified by 'nexcorecode';
grant all privileges on code.* to 'code'@'localhost' identified by 'nexcorecode';

﻿create database cert;
grant all privileges on cert.* to 'cert'@'%' identified by 'nexcorecert';
grant all privileges on cert.* to 'cert'@'localhost' identified by 'nexcorecert';

﻿create database lic;
grant all privileges on lic.* to 'lic'@'%' identified by 'nexcorelic';
grant all privileges on lic.* to 'lic'@'localhost' identified by 'nexcorelic';

﻿create database project;
grant all privileges on project.* to 'project'@'%' identified by 'nexcoreproject';
grant all privileges on project.* to 'project'@'localhost' identified by 'nexcoreproject';