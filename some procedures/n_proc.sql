DROP PROCEDURE IF EXISTS handle_events;
GO

CREATE procedure handle_events(

	@venue nvarchar(50),
	@name nvarchar(50),
	@start datetime,
	@end datetime,
	@Description nvarchar(500),
	@creatorid int,
	@locid int

	

)
as
begin


DECLARE @SCRIPT NVARCHAR(MAX) = ' 
Select *
FROM gathering as g
where 1 =2 ' 



DECLARE @BOOL bit = 0;


IF @venue != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or ( g.Privacy_Name != ''CLOSED'' and Lower( g.venue ) like Lower(''' + @venue + ''')+''%'')' + CHAR(13)
SET @BOOL = 1;
END

IF @name != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or ( g.Privacy_Name != ''CLOSED'' and Lower( g.name) like Lower(''' + @name + ''')+''%'')' + CHAR(13)
SET @BOOL = 1;
END

IF @start is not null
BEGIN
SET @SCRIPT = @SCRIPT + ' or ( datediff(day,g.start_time, Cast(''' + CONVERT(VARCHAR(10),@start,23) + ''' as date)) =0 '+ CHAR(13)
SET @BOOL = 1;
END

IF @end is not null
BEGIN
SET @SCRIPT = @SCRIPT + ' or ( datediff(day,g.end_time, Cast(''' + CONVERT(VARCHAR(10),@end,23) + ''' as date)) =0 '+ CHAR(13)
SET @BOOL = 1;
END


IF @Description != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or ( g.Privacy_Name != ''CLOSED'' and Lower( g.venue ) like Lower(''' + @Description + ''')+''%'')' + CHAR(13)
SET @BOOL = 1;
END

IF @creatorid != -1
BEGIN
SET @SCRIPT = @SCRIPT + ' or ( g.Privacy_Name != ''CLOSED'' and g.creator_id = ' + CONVERT(varchar(10),@creatorid)+')' + CHAR(13)
SET @BOOL = 1;
END

IF @locid != -1
BEGIN
SET @SCRIPT = @SCRIPT + ' or ( g.Privacy_Name != ''CLOSED'' and g.loc_id = ' + CONVERT(varchar(10),@locid)+')' + CHAR(13)
SET @BOOL = 1;
END


print(@script)
EXEC(@SCRIPT)
END

GO


EXEC handle_events
	@venue = 'home',
	@name = 'test_event_u2',
	@start = null,
	@end  = null,
	@Description  = 'testing event to see trigger works',
	@creatorid  = 3,
	@locid  = -4;
	
go
