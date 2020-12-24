DROP  PROCEDURE IF EXISTS dela
go

create procedure dela(
	@name nvarchar(100)
)
as begin
declare @script  nvarchar(100) = '
alter table '+ @name +' nocheck constraint all'
exec (@script)

set @script = 'delete from '+ @name

exec (@script)

set @script = 'alter table '+ @name +' check constraint all'

print (@script)
exec (@script)
end
go

exec dela
@name = '   [sherod01].[User_Interest]     '

