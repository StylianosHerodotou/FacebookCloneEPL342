DROP PROCEDURE IF EXISTS updates;
GO

CREATE procedure updates(
	@all bit,
	@userId int,
	@alb bit,	
	@pic bit,
	@vid bit,
	@link bit,
	@event bit,
	@k int
)
as
begin

DECLARE @SCRIPT NVARCHAR(MAX) = ' 
Select Top '+CONVERT(varchar(20),@k) + '*
FROM logs as l
where '

SET @SCRIPT = @SCRIPT + '  l.UserID = ' + CONVERT(varchar(10),@userId) + CHAR(13) 

if @all = 1
begin
EXEC(@SCRIPT)
return
end

DECLARE @BOOL bit = 0;

IF @alb =1 
BEGIN
SET @SCRIPT = @SCRIPT + ' and l.Object_Type = ''album''' + CHAR(13)
EXEC(@SCRIPT)
return
END

IF @pic =1 
BEGIN
SET @SCRIPT = @SCRIPT + ' and l.Object_Type = ''pic''' + CHAR(13)
EXEC(@SCRIPT)
return

END

IF @vid =1 
BEGIN
SET @SCRIPT = @SCRIPT + ' and l.Object_Type = ''vid''' + CHAR(13)
EXEC(@SCRIPT)
return

END

IF @link =1 
BEGIN
SET @SCRIPT = @SCRIPT + ' and l.Object_Type= ''link''' + CHAR(13)
EXEC(@SCRIPT)
return

END


IF @event =1 
BEGIN
SET @SCRIPT = @SCRIPT + ' and l.Object_Type = ''event''' + CHAR(13)
EXEC(@SCRIPT)
return

END

 
print(@SCRIPT)
EXEC(@SCRIPT)
END

GO




EXEC updates
	@all =1,
	@userId =1,
	@alb =0,
	@pic =0,
	@vid =0,
	@link=0,
	@event =0,
	@k = 100
