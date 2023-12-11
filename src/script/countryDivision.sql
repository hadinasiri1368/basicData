DECLARE
@name VARCHAR(50)
DECLARE
@code VARCHAR(50)
DECLARE
@id decimal

  insert into sbd.country_division
select null,
       province_code,
       province_desc,
       0,
       0,
       null,
       null,
       null,
       null
from [way].[sbd].[province_city]
group by province_code, province_desc



DECLARE
db_cursor CURSOR FOR
select id, name, code
from sbd.country_division OPEN db_cursor
FETCH NEXT
FROM db_cursor
INTO @id,@name,@code
    WHILE @@FETCH_STATUS = 0
BEGIN
insert into sbd.country_division
select @id,
       city_code,
       city_desc,
       1,
       0,
       null,
       null,
       null,
       null
from [way].[sbd].[province_city]
where province_code=@code
  and LEN(city_code)=4
    FETCH NEXT
FROM db_cursor
INTO @id,@name,@code
END

CLOSE db_cursor DEALLOCATE db_cursor


DECLARE
db_cursor CURSOR FOR
select id, name, code
from sbd.country_division
where level_to_root = 1 OPEN db_cursor
FETCH NEXT
FROM db_cursor
INTO @id,@name,@code
    WHILE @@FETCH_STATUS = 0
BEGIN
insert into sbd.country_division
select @id,
       city_code,
       city_desc,
       2,
       0,
       null,
       null,
       null,
       null
from [way].[sbd].[province_city]
where LEFT (city_code
    , 4)= @code
  and LEN(city_code)
    >4
    FETCH NEXT
FROM db_cursor
INTO @id,@name,@code
END

CLOSE db_cursor DEALLOCATE db_cursor


