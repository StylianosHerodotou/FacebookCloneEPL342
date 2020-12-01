CREATE PROC simpleSearch(
@fname nvarchar(50),
@lname nvarchar(50),
@bday date,
@currloc int
)

AS
BEGIN

DECLARE @SCRIPT NVARCHAR(MAX) = 'SELECT * 
FROM USERS AS U
WHERE 1=1'

IF @fname != ''
SET @SCRIPT = @SCRIPT + ' and u.First_Name = @fname '

IF @lname != ''
SET @SCRIPT = @SCRIPT + ' and u.Last_Name = @lname '

IF @bday is not NULL
SET @SCRIPT = @SCRIPT + ' and u.Birthday = @bday '

IF @currloc != -1
SET @SCRIPT = @SCRIPT + ' and u.Current_LOC_ID = @currloc '


END