DECLARE @fname nvarchar(50)= 'Julius';
DECLARE @lname nvarchar(50) = '';
DECLARE @bday date =DATEFROMPARTS (1982, 6, 8)
DECLARE @currloc int = 22;


DECLARE @SCRIPT NVARCHAR(MAX) = 'SELECT * 
FROM USERS AS U
WHERE 1=1 '

IF @fname != ''
SET @SCRIPT = @SCRIPT + ' and u.First_Name = ''' + @fname + ''''

IF @lname != ''
SET @SCRIPT = @SCRIPT + ' and u.Last_Name = ''' + @lname + ''

IF @bday is not NULL
SET @SCRIPT = @SCRIPT + ' and datediff(day,u.BIRTHDAY, Cast(''' + CONVERT(VARCHAR(10),@bday,23) + ''' as date)) =0 '

IF @currloc != -1
SET @SCRIPT = @SCRIPT + ' and u.Current_LOC_ID = ' + CONVERT(varchar(10),@currloc)


print(@script)
exec(@script)