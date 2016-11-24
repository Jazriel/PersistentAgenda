DROP TABLE IF EXISTS Contacts;
CREATE TABLE Contacts (
  id INT NOT NULL PRIMARY KEY IDENTITY,
  name VARCHAR(50) NULL,
  surname VARCHAR(50) NULL,
  title VARCHAR(50) NULL,
  address VARCHAR(255) NULL,
  city VARCHAR(50) NULL,
  province VARCHAR(20) NULL,
  postal_code VARCHAR(20) NULL,
  region VARCHAR(50) NULL,
  country VARCHAR(50) NULL,
  company_name VARCHAR(50) NULL,
  workstation VARCHAR(50) NULL,
  work_phone VARCHAR(30) NULL,
  work_extension VARCHAR(20) NULL,
  mobile_phone VARCHAR(30) NULL,
  fax_number VARCHAR(30) NULL,
  email VARCHAR(50) NULL,
  contact_type_id INT NULL,
  notes VARCHAR(512) NULL
);

DROP TABLE IF EXISTS Calls;
CREATE TABLE Calls (
  id INT NOT NULL PRIMARY KEY IDENTITY,
  contact_id INT NOT NULL,
  call_date TIMESTAMP NOT NULL,
  subject VARCHAR(255) NOT NULL,
  notes VARCHAR(255) NULL,
);

DROP TABLE IF EXISTS ContactsTypes;
CREATE TABLE ContactsTypes (
  id INT NOT NULL PRIMARY KEY IDENTITY,
  contact_type VARCHAR(50) NOT NULL,
);

INSERT INTO ContactsTypes ( id, contact_type )
VALUES
(1, 'Tipo001'),
(2, 'Tipo002'),
(3, 'Tipo003'),
(4, 'Tipo004'),
(5, 'Tipo005'),
(6, 'Tipo006'),
(7, 'Tipo007'),
(8, 'Tipo008'),
(9, 'Tipo009'),
(10, 'Tipo010');

INSERT INTO  Contacts ( id ,  name ,  surname ,  title ,  address ,  city ,  province ,  postal_code ,  region ,  country ,  company_name ,  workstation ,  work_phone ,  work_extension ,  mobile_phone ,  fax_number ,  email ,  contact_type_id  ,  notes )
VALUES
(1, 'Nombre001', 'Apellidos001', 'Estimado001', ' Direccion001', 'Ciudad001', 'Prov001', 'CodPostal0001', 'Region0001', 'Pais0001', 'NombreCompania0001', 'Cargo001', 'TelefonoTrabajo001', 'ExtensionTrabajo001', 'TelefonoMovil001', 'NumFax001', ' NomCorreoElectronico001@ubu.es', 1, 'Notas001'),
(2, 'Nombre002', 'Apellidos002', 'Estimado002', ' Direccion002', 'Ciudad002', 'Prov002', 'CodPostal0002', 'Region0002', 'Pais0002', 'NombreCompania0002', 'Cargo002', 'TelefonoTrabajo002', 'ExtensionTrabajo002', 'TelefonoMovil002', 'NumFax002', ' NomCorreoElectronico002@ubu.es', 1, 'Notas002'),
(3, 'Nombre003', 'Apellidos003', 'Estimado003', ' Direccion003', 'Ciudad003', 'Prov003', 'CodPostal0003', 'Region0003', 'Pais0003', 'NombreCompania0003', 'Cargo003', 'TelefonoTrabajo003', 'ExtensionTrabajo003', 'TelefonoMovil003', 'NumFax003', ' NomCorreoElectronico003@ubu.es', 2,  'Notas003'),
(4, 'Nombre004', 'Apellidos004', 'Estimado004', ' Direccion004', 'Ciudad004', 'Prov004', 'CodPostal0004', 'Region0004', 'Pais0004', 'NombreCompania0004', 'Cargo004', 'TelefonoTrabajo004', 'ExtensionTrabajo004', 'TelefonoMovil004', 'NumFax004', ' NomCorreoElectronico004@ubu.es', 2,  'Notas004'),
(5, 'Nombre005', 'Apellidos005', 'Estimado005', ' Direccion005', 'Ciudad005', 'Prov005', 'CodPostal0005', 'Region0005', 'Pais0005', 'NombreCompania0005', 'Cargo005', 'TelefonoTrabajo005', 'ExtensionTrabajo005', 'TelefonoMovil005', 'NumFax005', ' NomCorreoElectronico005@ubu.es', 2,  'Notas005'),
(6, 'Nombre006', 'Apellidos006', 'Estimado006', ' Direccion006', 'Ciudad006', 'Prov006', 'CodPostal0006', 'Region0006', 'Pais0006', 'NombreCompania0006', 'Cargo006', 'TelefonoTrabajo006', 'ExtensionTrabajo006', 'TelefonoMovil006', 'NumFax006', ' NomCorreoElectronico006@ubu.es', 3,  'Notas006'),
(7, 'Nombre007', 'Apellidos007', 'Estimado007', ' Direccion007', 'Ciudad007', 'Prov007', 'CodPostal0007', 'Region0007', 'Pais0007', 'NombreCompania0007', 'Cargo007', 'TelefonoTrabajo007', 'ExtensionTrabajo007', 'TelefonoMovil007', 'NumFax007', ' NomCorreoElectronico007@ubu.es', 3,  'Notas007'),
(8, 'Nombre008', 'Apellidos008', 'Estimado008', ' Direccion008', 'Ciudad008', 'Prov008', 'CodPostal0008', 'Region0008', 'Pais0008', 'NombreCompania0008', 'Cargo008', 'TelefonoTrabajo008', 'ExtensionTrabajo008', 'TelefonoMovil008', 'NumFax008', ' NomCorreoElectronico008@ubu.es', 3,  'Notas008'),
(9, 'Nombre009', 'Apellidos009', 'Estimado009', ' Direccion009', 'Ciudad009', 'Prov009', 'CodPostal0009', 'Region0009', 'Pais0009', 'NombreCompania0009', 'Cargo009', 'TelefonoTrabajo009', 'ExtensionTrabajo009', 'TelefonoMovil009', 'NumFax009', ' NomCorreoElectronico009@ubu.es', 3,  'Notas009'),
(10, 'Nombre010', 'Apellidos010', 'Estimado010', ' Direccion010', 'Ciudad010', 'Prov010', 'CodPostal0010', 'Region0010', 'Pais0010', 'NombreCompania0010', 'Cargo010', 'TelefonoTrabajo010', 'ExtensionTrabajo010', 'TelefonoMovil010', 'NumFax010', ' NomCorreoElectronico010@ubu.es', 4,  'Notas010');

INSERT INTO  Calls ( id ,  contact_id ,  call_date ,   subject ,  notes )
VALUES
(1,1, '2016-10-18 01:00:00',  'AsuntoLlamada001', 'NotaLlamada001'),
(2,1, '2016-10-18 02:00:00',   'AsuntoLlamada002', 'NotaLlamada002'),
(3,1, '2016-10-18 03:00:00',   'AsuntoLlamada003', 'NotaLlamada003'),
(4,1, '2016-10-18 04:00:00',   'AsuntoLlamada004', 'NotaLlamada004'),
(5,1, '2016-10-18 05:00:00',   'AsuntoLlamada005', 'NotaLlamada005');

INSERT INTO  Calls ( id ,  contact_id ,  call_date ,   subject ,  notes )
VALUES
(6,10, '2016-10-18 06:00:00',  'AsuntoLlamada006', 'NotaLlamada006'),
(7,10, '2016-10-18 07:00:00',   'AsuntoLlamada007', 'NotaLlamada007'),
(8,10, '2016-10-18 08:00:00',   'AsuntoLlamada008', 'NotaLlamada008'),
(9,10, '2016-10-18 09:00:00',   'AsuntoLlamada009', 'NotaLlamada009'),
(10,10, '2016-10-18 10:00:00',   'AsuntoLlamada010', 'NotaLlamada010');
