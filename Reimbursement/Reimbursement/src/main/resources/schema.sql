create table types(
 id int primary key,
 type varchar(25)
);
create table requests(
id int auto_increment primary key ,
travelrequestid int,
requestraisedbyemployeeid int,
requestdate date default current_date,
reimbursementtypeid int,
invoiceno varchar(20),
invoicedate date check (invoicedate <= current_date),
invoiceamount int,
documenturl varchar(100),
requestprocessedon date check (requestprocessedon >= current_date),
requestprocessedbyemployeeid int,
status varchar(10) default 'New' check(status in ('New','Approved','Rejected')),
remarks varchar(100),
foreign key (reimbursementtypeid) references types(id) 
);
