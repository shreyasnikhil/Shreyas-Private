create table IncidentTypes(
id int primary key,
type int,
ExpectedSLAInDays int
);
create table Incident (
IncidentID varchar(10) primary key ,
IncidentData date check (IncidentData <= current_date),
ReportDate date check (ReportDate <= current_date),
IncidentReportedByUserID int,
IncidentTypeid int,
ResolutionETA date check (ResolutionETA <= current_date),
InvestigatedByUserId int,
IncidentSummary varchar(50),
IncidentDetails varchar(500),
BookingID int,
Status varchar(10) default 'Pending' check(status in ('Pending','closed')),
foreign key (IncidentTypeid) references IncidentTypes(id)
);
create table InvestigationDetails(
id int primary key,
Findings varchar(500),
Suggestions varchar(500),
InvestigationDate Date,
IncidentsIncidentID varchar(10),
foreign key (IncidentsIncidentID) references Incident(IncidentID)
);
